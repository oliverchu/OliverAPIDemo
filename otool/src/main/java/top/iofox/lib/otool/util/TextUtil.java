package top.iofox.lib.otool.util;

/**
 * Created by [Oliver Chu] on 2018/12/4 11:42
 */
public class TextUtil {

    /**
     * 例子：如果您想实现135****6810 这样的效果，您可以这样使用 getMaskText("13579246810",3,"****");
     *
     * @param src   待添加遮盖的字符串
     * @param start 遮盖开始的地方
     * @param mask  遮盖文本
     * @return 遮盖后的文本
     */
    public static String getMaskText(String src, int start, String mask) {
        if (src != null && start < src.length()) {
            if (mask != null && mask.length() > 0) {
                if (start + mask.length() < src.length()) {
                    return src.substring(0, start) + mask + src.substring(start + mask.length());
                } else {
                    return src.substring(0, start) + mask;
                }
            }
        }
        return src;
    }

    /**
     * 如果字符串为字符null也是空的
     *
     * @param sequence 字符串输入
     * @return 是否为空
     */
    public static boolean isEmpty(String sequence) {
        return sequence == null || sequence.length() == 0 || sequence.equalsIgnoreCase("null");
    }

    /**
     * src为空的时候输出""
     *
     * @param src 字符串输入
     * @return src或者""
     */
    public static String emptyIfNull(String src) {
        return isEmpty(src) ? "" : src;
    }


}
