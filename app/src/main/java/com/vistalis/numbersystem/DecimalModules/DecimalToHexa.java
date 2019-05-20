package com.vistalis.numbersystem.DecimalModules;

public class DecimalToHexa {

    private static char[] hex = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    public static String convert(long decimal)
    {
        String hexa = "";

        int remainder;

        while( decimal != 0 ) {
            remainder = (int) (decimal % 16);
            hexa = hex[remainder] + hexa;
            decimal = decimal / 16;
        }

        return hexa;

    }
}
