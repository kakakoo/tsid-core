package com.tsid.common.util;

public class CommonUtil {

    public static String maskingTel(String mobile) {
        if (mobile.length() == 10) {
            return mobile.substring(0, 3) + "***" + mobile.substring(6);
        }
        if (mobile.length() == 11) {
            return mobile.substring(0, 3) + "****" + mobile.substring(7);
        }
        return "***-****-****";
    }

    public static String maskingName(String name) {
        if (name.length() == 2) {
            return name.charAt(0) + "*";
        }
        if (name.length() > 3) {
            String mid = "";
            for (int i = 0; i <= name.length() - 2; i++) {
                mid += '*';
            }
            return name.charAt(0) + mid + name.substring(mid.length());
        }
        return name.charAt(0) + "*" + name.substring(2);
    }

    public static String halfMasking(String data) {
        int length = data.length() / 2;
        String last = data.substring(length + 1);

        String masking = "";

        for (int i = 0; i < length; i++) {
            masking += "*";
        }
        return masking + last;

    }

    public static String setStringFormatInteger(int num) {
        return setStringFormatInteger(String.valueOf(num));
    }

    public static String setStringFormatInteger(String number) {

        String underNum = "";
        if (number.contains(".")) {
            underNum = number.substring(number.indexOf("."));
            number = number.substring(0, number.indexOf("."));
        }
        int length = number.length();
        if (length < 4)
            return number + underNum;

        String prefix = number.substring(0, length - 3);
        String suffix = number.substring(length - 3);

        return setStringFormatInteger(prefix) + "," + suffix + underNum;
    }

}
