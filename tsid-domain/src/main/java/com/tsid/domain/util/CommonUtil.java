package com.tsid.domain.util;

import java.util.Arrays;
import java.util.StringTokenizer;

public class CommonUtil {

    public static Integer stringConvertToIntVersion(String version){
        int intVersion = 0;

        String [] verTemp = version.split("\\.");

        int scale = 10000;
        for (int i = 0; i < verTemp.length; i++) {

            if (!verTemp[i].equals("0")) {
                intVersion += scale * Integer.parseInt(verTemp[i]);
            }
            scale = scale / 100;
        }

        return intVersion;
    }

    public static String telConvertMasking(String tel) {
        String maskingTel;
        StringTokenizer st = new StringTokenizer(tel, "-");

        if (st.countTokens() == 3) {
            String [] telArr = tel.split("-");

            char[] c = new char[telArr[1].length()];
            Arrays.fill(c, '*');

            maskingTel = telArr[0] + "-" + String.valueOf(c) + "-" + telArr[2];
        } else {
            String start = tel.substring(0, 3);
            String end = tel.substring(tel.length() - 4);

            maskingTel = start + "-****-" + end;
        }

        return maskingTel;
    }

    public static String nameConvertMasking(String name){
        if (name.length() == 1) {
            return name;
        } else if (name.length() == 2) {
            return name.substring(0,1) + "*";
        } else if (name.length() > 2 && name.length() < 6) {

            String first = name.substring(0, 1);
            String last = name.substring(name.length()-1);

            char[] c = new char[name.length() - 2];
            Arrays.fill(c, '*');

            return first + String.valueOf(c) + last;
        } else if (name.length() > 6 && name.length() < 20) {
            String[] foreignArray = name.split(" ");

            String result = "";
            for(int i=0; i<foreignArray.length; i++){
                if(i==0){
                    result = foreignArray[i];
                } else {
                    int length = foreignArray[i].length();
                    String temp = new String();
                    for(int j=0; j<length; j++){
                        temp += "*";
                    }
                    result += " "+ temp;
                }
            }
            return result;
        } else if(name.length()>20){
            String first = name.substring(0, 1);
            String last = name.substring(name.length()-1);

            char[] c = new char[name.length() - 2];
            Arrays.fill(c, '*');

            return first + String.valueOf(c) + last;
        } else {
            return "";
        }
    }

}
