package com.vistalis.numbersystem.OctalModules;

public class OctalToDecimal {
    public static String convert(long n)
    {
        int num = (int) n;
        int dec_value = 0;
        int base = 1;

        int temp = num;

        while( temp > 0 ) {
            int last_digit = temp % 10;
            temp /= 10;

            dec_value += last_digit * base;
            base = base * 8;
        }

        return String.valueOf(dec_value);

    }
}
