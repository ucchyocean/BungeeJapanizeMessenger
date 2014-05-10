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

    /**
     * プラグインが有効かされたときに呼び出されるメソッド
     * @see net.md_5.bungee.api.plugin.Plugin#onEnable()
     */
    @Override
    public void onEnable() {

        // 初期化
        history = new HashMap<String, String>();

        // コマンド登録
        getProxy().getPluginManager().registerCommand(this, new TellCommand(this, "tell"));
        getProxy().getPluginManager().registerCommand(this, new TellCommand(this, "msg"));
        getProxy().getPluginManager().registerCommand(this, new TellCommand(this, "message"));
        getProxy().getPluginManager().registerCommand(this, new TellCommand(this, "m"));
        getProxy().getPluginManager().registerCommand(this, new TellCommand(this, "w"));
        getProxy().getPluginManager().registerCommand(this, new TellCommand(this, "t"));
        getProxy().getPluginManager().registerCommand(this, new ReplyCommand(this, "reply"));
        getProxy().getPluginManager().registerCommand(this, new ReplyCommand(this, "r"));
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
