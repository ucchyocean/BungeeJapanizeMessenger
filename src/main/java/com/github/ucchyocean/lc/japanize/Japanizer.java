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

    /**
     * メッセージの日本語化をする
     * @param org
     * @param type
     * @param japanizeFormat
     * @return
     */
    public static String japanize(String org, JapanizeType type, String japanizeFormat) {

        // 変換不要なら何もしないで返す
        if ( type == JapanizeType.NONE || !isNeedToJapanize(org) ) {
            return org;
        }

        // URL削除
        String deletedURL = org.replaceAll(REGEX_URL, " ");

        // カナ変換
        String japanized = KanaConverter.conv(deletedURL);

        // IME変換
        if ( type == JapanizeType.GOOGLE_IME ) {
            japanized = IMEConverter.convByGoogleIME(japanized);
        } else if ( type == JapanizeType.SOCIAL_IME ) {
            japanized = IMEConverter.convBySocialIME(japanized);
        }

        // 返す
        String result = japanizeFormat.replace("%msg", org);
        result = result.replace("%japanize", japanized);

        return result;
    }

    /**
     * 日本語化が必要かどうかを判定する
     * @param org
     * @return
     */
    private static boolean isNeedToJapanize(String org) {
        return ( org.getBytes().length == org.length()
                && !org.matches("[ \\uFF61-\\uFF9F]+") );
    }
}
