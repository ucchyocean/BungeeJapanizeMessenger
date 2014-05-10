/*
 * @author     ucchy
 * @license    LGPLv3
 * @copyright  Copyright ucchy 2014
 */
package com.github.ucchyocean.bjm;

import java.util.HashMap;

import net.md_5.bungee.api.plugin.Plugin;

/**
 * サーバー間tellコマンドおよびJapanize化プラグイン
 * @author ucchy
 */
public class BungeeJapanizeMessenger extends Plugin {

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
}
