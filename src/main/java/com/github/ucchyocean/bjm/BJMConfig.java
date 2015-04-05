/*
 * @author     ucchy
 * @license    LGPLv3
 * @copyright  Copyright ucchy 2014
 */
package com.github.ucchyocean.bjm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
    private int japanizeDisplayLine;
    private String japanizeLine1Format;
    private String japanizeLine2Format;
    private String noneJapanizeMarker;
    private boolean broadcastChat;
    private String broadcastChatFormat;
    private boolean broadcastChatLocalJapanize;
    private ArrayList<Pattern> ngwordCompiled;

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
        ConfigurationProvider provider =
                ConfigurationProvider.getProvider(YamlConfiguration.class);
        try {
            Configuration config = provider.load(file);

            defaultFormatForPrivateMessage =
                    config.getString("defaultFormatForPrivateMessage",
                            "&7[%sender@%senderserver > %reciever@%recieverserver] %msg");

            japanizeType = JapanizeType.fromID(
                    config.getString("japanizeType"), JapanizeType.GOOGLE_IME);

            japanizeDisplayLine =
                    config.getInt("japanizeDisplayLine", 1);
            if ( japanizeDisplayLine != 1 && japanizeDisplayLine != 2 ) {
                japanizeDisplayLine = 1;
            }

            japanizeLine1Format =
                    config.getString("japanizeLine1Format", "%msg &6(%japanize)");
            japanizeLine2Format =
                    config.getString("japanizeLine2Format", "&6[JP] %japanize");

            noneJapanizeMarker = config.getString("noneJapanizeMarker", "#");

            broadcastChat = config.getBoolean("broadcastChat", true);

            broadcastChatFormat =
                    config.getString("broadcastChatFormat",
                            "%date %time &d<%sender@%senderserver> &f%msg");

            broadcastChatLocalJapanize =
                    config.getBoolean("broadcastChatLocalJapanize", true);

            ngwordCompiled = new ArrayList<Pattern>();
            for ( String word : config.getStringList("ngword") ) {
                ngwordCompiled.add(Pattern.compile(word));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * @return japanizeDisplayLine
     */
    public int getJapanizeDisplayLine() {
        return japanizeDisplayLine;
    }

    /**
     * @return japanizeLine1Format
     */
    public String getJapanizeLine1Format() {
        return japanizeLine1Format;
    }

    /**
     * @return japanizeLine2Format
     */
    public String getJapanizeLine2Format() {
        return japanizeLine2Format;
    }

    /**
     * @return noneJapanizeMarker
     */
    public String getNoneJapanizeMarker() {
        return noneJapanizeMarker;
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

    /**
     * @return ngwordCompiled
     */
    public ArrayList<Pattern> getNgwordCompiled() {
        return ngwordCompiled;
    }
}
