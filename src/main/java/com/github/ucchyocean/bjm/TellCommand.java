/*
 * @author     ucchy
 * @license    LGPLv3
 * @copyright  Copyright ucchy 2014
 */
package com.github.ucchyocean.bjm;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import com.github.ucchyocean.lc.japanize.Japanizer;

/**
 * BungeeJapanizeMessengerのtellコマンド実装クラス
 * @author ucchy
 */
public class TellCommand extends Command {

    private BungeeJapanizeMessenger parent;

    /**
     * コンストラクタ
     * @param BungeeJapanizeMessenger
     * @param name コマンド
     */
    public TellCommand(BungeeJapanizeMessenger parent, String name) {
        super(name);
        this.parent = parent;
    }

    /**
     * コマンドが実行された時に呼び出されるメソッド
     * @param sender コマンド実行者
     * @param args コマンド引数
     * @see net.md_5.bungee.api.plugin.Command#execute(net.md_5.bungee.api.CommandSender, java.lang.String[])
     */
    @Override
    public void execute(CommandSender sender, String[] args) {

        // 引数が足らないので、Usageを表示して終了する。
        if ( args.length <= 1 ) {
            sendMessage(sender, ChatColor.RED +
                    "実行例： /" + this.getName() + " <player> <message>");
            return;
        }

        // 自分自身には送信できない。
        if ( args[0].equals(sender.getName()) ) {
            sendMessage(sender, ChatColor.RED +
                    "自分自身にはプライベートメッセージを送信することができません。");
            return;
        }

        // 送信先プレイヤーの取得。取得できないならエラーを表示して終了する。
        ProxiedPlayer reciever = parent.getProxy().getPlayer(args[0]);
        if ( reciever == null ) {
            sendMessage(sender, ChatColor.RED +
                    "メッセージ送信先 " + args[0] + " が見つかりません。");
            return;
        }

        // 送信メッセージの作成
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
                str.append(args[i] + " ");
        }
        String message = str.toString().trim();

        // Japanizeの付加
        message = Japanizer.japanize(message,
                parent.getConfig().getJapanizeType(),
                parent.getConfig().getJapanizeLine1Format());

        // フォーマットの適用
        String senderServer = "";
        if ( sender instanceof ProxiedPlayer ) {
            senderServer = ((ProxiedPlayer)sender).getServer().getInfo().getName();
        }
        String result = new String(
                parent.getConfig().getDefaultFormatForPrivateMessage());
        result = result.replace("%senderserver", senderServer);
        result = result.replace("%sender", sender.getName());
        result = result.replace("%recieverserver",
                reciever.getServer().getInfo().getName());
        result = result.replace("%reciever", reciever.getName());
        result = result.replace("%msg", message);

        // カラーコードの置き換え
        result = Utility.replaceColorCode(result);

        // メッセージ送信
        sendMessage(sender, result);
        sendMessage(reciever, result);

        // 送信履歴を記録
        parent.putHistory(reciever.getName(), sender.getName());
    }

    /**
     * 指定した対象にメッセージを送信する
     * @param reciever 送信先
     * @param message メッセージ
     */
    private void sendMessage(CommandSender reciever, String message) {
        reciever.sendMessage(TextComponent.fromLegacyText(message));
    }
}
