/*
 * @author     ucchy
 * @license    LGPLv3
 * @copyright  Copyright ucchy 2014
 */
package com.github.ucchyocean.bjm;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import com.github.ucchyocean.lc.japanize.JapanizeType;

/**
 * BungeeJapanizeMessengerのコンフィグクラス
 * @author ucchy
 */
public class BJMConfig {

    private String defaultFormatForPrivateMessage;
    private JapanizeType japanizeType;
    private String japanizeLine1Format;
    private boolean broadcastChat;
    private String broadcastChatFormat;
    private boolean broadcastChatLocalJapanize;

    /**
     * コンストラクタ
     * @param parent
     */
    public BJMConfig(BungeeJapanizeMessenger parent) {

        // Fileを取得
        File folder = new File(
                parent.getProxy().getPluginsFolder(),
                "BungeeJapanizeMessenger");
        if ( !folder.exists() ) {
            folder.mkdirs();
        }

        File file = new File(folder, "config.yml");
        if ( !file.exists() ) {
            Utility.copyFileFromJar(parent.getFile(), file, "config.yml", false);
        }

        // コンフィグ取得
        initByDefault();
        ConfigurationProvider provider =
                ConfigurationProvider.getProvider(YamlConfiguration.class);
        try {
            Configuration config = provider.load(file);

            defaultFormatForPrivateMessage =
                    config.getString("defaultFormatForPrivateMessage",
                            defaultFormatForPrivateMessage);

            String temp =
                    config.getString("japanizeType", japanizeType.toString());
            temp = temp.toUpperCase();
            japanizeType = JapanizeType.fromID(temp);
            if ( japanizeType == null ) {
                japanizeType = JapanizeType.GOOGLE_IME;
            }

            japanizeLine1Format =
                    config.getString("japanizeLine1Format", japanizeLine1Format);

            broadcastChat = config.getBoolean("broadcastChat", broadcastChat);

            broadcastChatFormat =
                    config.getString("broadcastChatFormat", broadcastChatFormat);

            broadcastChatLocalJapanize =
                    config.getBoolean("broadcastChatLocalJapanize", broadcastChatLocalJapanize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 全てのコンフィグを初期値で初期化する
     */
    private void initByDefault() {

        defaultFormatForPrivateMessage =
                "&7[%sender@%senderserver > %reciever@%recieverserver] %msg";
        japanizeType = JapanizeType.GOOGLE_IME;
        japanizeLine1Format = "%msg &7(%japanize)";
        broadcastChat = false;
        broadcastChatFormat = "&d<%sender@%senderserver> &f&msg";
        broadcastChatLocalJapanize = true;
    }

    /**
     * @return defaultFormatForPrivateMessage
     */
    public String getDefaultFormatForPrivateMessage() {
        return defaultFormatForPrivateMessage;
    }

    /**
     * @return japanizeType
     */
    public JapanizeType getJapanizeType() {
        return japanizeType;
    }

    /**
     * @return japanizeLine1Format
     */
    public String getJapanizeLine1Format() {
        return japanizeLine1Format;
    }

    /**
     * @return broadcastChat
     */
    public boolean isBroadcastChat() {
        return broadcastChat;
    }

    /**
     * @return broadcastChatFormat
     */
    public String getBroadcastChatFormat() {
        return broadcastChatFormat;
    }

    /**
     * @return broadcastChatLocalJapanize
     */
    public boolean isBroadcastChatLocalJapanize() {
        return broadcastChatLocalJapanize;
    }
}
