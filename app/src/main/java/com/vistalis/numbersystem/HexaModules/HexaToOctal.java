package com.vistalis.numbersystem.HexaModules;

public class HexaToOctal {
    public static String convert(String hex)
    {
        StringBuilder octal = new StringBuilder();
        int dec_num, i = 1;
        int[] octal_num = new int[100];

        dec_num = Integer.parseInt(HexaToDecimal.convert(hex));

        while( dec_num != 0 ) {
            octal_num[i++] = dec_num % 8;
            dec_num /= 8;
        }
        for(int j = i -1; j>0; j--) {
            octal.append(octal_num[j]);
        }
        return String.valueOf(octal);
    }
}
