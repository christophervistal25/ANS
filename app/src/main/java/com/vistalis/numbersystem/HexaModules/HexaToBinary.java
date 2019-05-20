package com.vistalis.numbersystem.HexaModules;

public class HexaToBinary {

    public static String convert(String hexadecimal)
    {
        StringBuilder hexdec_num = new StringBuilder();
        int dec_num, i = 1;
        int bin_num[] = new int[100];

        dec_num = Integer.parseInt(HexaToDecimal.convert(hexadecimal));

        while( dec_num != 0 ) {
            bin_num[i++] = dec_num % 2;
            dec_num /= 2;
        }
        for(int j = i - 1; j>0; j--) {
            hexdec_num.append(bin_num[j]);
        }

        return String.valueOf(hexdec_num);
    }
}
