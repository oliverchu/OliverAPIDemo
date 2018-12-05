package top.iofox.lib.otool.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by [Oliver Chu] on 2018/12/5 15:33
 */
public class RegexUtil {
    public static boolean isChinesePhoneNumber(String phoneNumer) {
        if (TextUtil.isEmpty(phoneNumer)) {
            Pattern pattern = Pattern.compile("^(13|14|15|17|18)\\d{9}$");
            Matcher matcher = pattern.matcher(phoneNumer);
            return matcher.matches();
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        if (!TextUtil.isEmpty(str)) {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);
            return isNum.matches();
        }
        return false;
    }

    public static boolean checkBankCard(String cardNo) {
        if (cardNo.length() < 15 || cardNo.length() > 19) {
            return false;
        }
        char bit = getBankCardCheckCode(cardNo.substring(0, cardNo.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardNo.charAt(cardNo.length() - 1) == bit;
    }

    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0 || !nonCheckCodeCardId
                .matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    public static boolean isEmail(String email) {
        if (!TextUtil.isEmpty(email)) {
            String str =
                    "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            Pattern pattern = Pattern.compile(str);
            Matcher m = pattern.matcher(email);
            return m.matches();
        }
        return false;
    }

}
