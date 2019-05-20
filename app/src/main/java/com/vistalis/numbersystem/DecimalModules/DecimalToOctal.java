package com.vistalis.numbersystem.DecimalModules;

public class DecimalToOctal {

    public static String convert(long decimal)
    {
        int[] octal = new int[100];
        int i = 0;
        StringBuilder form = new StringBuilder();

        while( decimal != 0 ) {
            octal[i] = (int) (decimal % 8);
            decimal /= 8;
            i++;
        }

        for(int j = i - 1; j>=0; j--) {
            form.append(octal[j]);
        }

        return form.toString();

    }

}
