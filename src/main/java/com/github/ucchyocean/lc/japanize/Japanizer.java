/*
 * @author     ucchy
 * @license    LGPLv3
 * @copyright  Copyright ucchy 2014
 */
package com.github.ucchyocean.lc.japanize;

/**
 * ローマ字表記を漢字変換して返すユーティリティ
 * @author ucchy
 */
public class Japanizer {

    private static final String REGEX_URL = "https?://[\\w/:%#\\$&\\?\\(\\)~\\.=\\+\\-]+";

    public static String japanize(String org) {

        // 変換不要なら何もしないで返す
        if ( !isNeedToJapanize(org) ) {
            return org;
        }

        // URL削除
        String deletedURL = org.replaceAll(REGEX_URL, " ");

        // カナ変換
        String japanized = KanaConverter.conv(deletedURL);

        // IME変換
        japanized = IMEConverter.convByGoogleIME(japanized);

        // 返す
        return org + " (" + japanized + ")";
    }

    private static boolean isNeedToJapanize(String org) {
        return ( org.getBytes().length == org.length()
                && !org.matches("[ \\uFF61-\\uFF9F]+") );
    }
}
