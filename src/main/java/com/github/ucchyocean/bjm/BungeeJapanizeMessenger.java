/*
 * @author     ucchy
 * @license    LGPLv3
 * @copyright  Copyright ucchy 2014
 */
package com.github.ucchyocean.bjm;

import java.util.HashMap;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import com.github.ucchyocean.lc.japanize.Japanizer;

/**
 * サーバー間tellコマンドおよびJapanize化プラグイン
 * @author ucchy
 */
public class BungeeJapanizeMessenger extends Plugin implements Listener {

    private HashMap<String, String> history;
    private BJMConfig config;

    /**
     * プラグインが有効かされたときに呼び出されるメソッド
     * @see net.md_5.bungee.api.plugin.Plugin#onEnable()
     */
    @Override
    public void onEnable() {

        // 初期化
        history = new HashMap<String, String>();

        // コマンド登録
        for ( String command : new String[]{
                "tell", "msg", "message", "m", "w", "t"}) {
            getProxy().getPluginManager().registerCommand(
                    this, new TellCommand(this, command));
        }
        for ( String command : new String[]{"reply", "r"}) {
            getProxy().getPluginManager().registerCommand(
                    this, new ReplyCommand(this, command));
        }

        // コンフィグ取得
        config = new BJMConfig(this);

        // リスナー登録
        getProxy().getPluginManager().registerListener(this, this);
    }

    /**
     * コンフィグを返す
     * @return コンフィグ
     */
    public BJMConfig getConfig() {
        return config;
    }

    /**
     * プライベートメッセージの送信履歴を記録する
     * @param sender 送信者
     * @param reciever 受信者
     */
    protected void putHistory(String sender, String reciever) {
        history.put(sender, reciever);
    }

    /**
     * プライベートメッセージの送信履歴を取得する
     * @param sender 送信者
     * @return 受信者
     */
    protected String getHistory(String sender) {
        return history.get(sender);
    }

    @EventHandler
    public void onChat(ChatEvent event) {

        // 設定が無効なら、そのまま無視する
        if ( !config.isBroadcastChat() ) {
            return;
        }

        // コマンド実行の場合は、そのまま無視する
        if ( event.isCommand() ) {
            return;
        }

        // プレイヤーの発言ではない場合は、そのまま無視する
        if ( !(event.getSender() instanceof ProxiedPlayer) ) {
            return;
        }

        // 発言者と発言サーバーの取得
        ProxiedPlayer sender = (ProxiedPlayer)event.getSender();
        String senderServer = sender.getServer().getInfo().getName();

        // メッセージのjapanize変換
        String message = Japanizer.japanize(event.getMessage(),
                config.getJapanizeType(), config.getJapanizeLine1Format());

        // フォーマットの置き換え処理
        String result = config.getBroadcastChatFormat();
        result = result.replace("%senderserver", senderServer);
        result = result.replace("%sender", sender.getName());
        result = result.replace("%msg", message);
        result = Utility.replaceColorCode(result);

        // 発言したプレイヤーがいるサーバー"以外"のサーバーに、
        // 発言内容を送信する。
        for ( String server : getProxy().getServers().keySet() ) {

            if ( server.equals(senderServer) ) {
                continue;
            }

            ServerInfo info = getProxy().getServerInfo(server);
            for ( ProxiedPlayer player : info.getPlayers() ) {
                sendMessage(player, result);
            }
        }

        // ローカルも置き換える処理なら、置換えを行う
        if ( config.isBroadcastChatLocalJapanize() ) {
            event.setMessage(message);
        }
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
