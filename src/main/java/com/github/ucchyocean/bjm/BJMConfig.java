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

//            japanizeDisplayLine =
//                    config.getInt("japanizeDisplayLine", 1);
//            if ( japanizeDisplayLine <= 0 ) {
//                japanizeDisplayLine = 1;
//            } else if ( japanizeDisplayLine >= 3 ) {
//                japanizeDisplayLine = 2;
//            }

            japanizeLine1Format =
                    config.getString("japanizeLine1Format", japanizeLine1Format);
//            japanizeLine2Format =
//                    config.getString("japanizeLine2Format", japanizeLine2Format);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 全てのコンフィグを初期値で初期化する
     */
    private void initByDefault() {

        defaultFormatForPrivateMessage = "&7[%player > %to] %msg";
        japanizeType = JapanizeType.GOOGLE_IME;
//        japanizeDisplayLine = 1;
        japanizeLine1Format = "%msg &7(%japanize)";
//        japanizeLine2Format = "&6[JP] %japanize";
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
}
