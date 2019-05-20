package com.vistalis.numbersystem.OctalModules;

public class OctalToHexa {

    public static String convert(long octal) {
        int dec_num = Integer.parseInt(String.valueOf(octal),8);
        return Integer.toHexString(dec_num);
    }
}
