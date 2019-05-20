package com.vistalis.numbersystem.DecimalModules;

import android.util.Log;

public class DecimalToBinary {

    public static String convert(long decimal)
    {
        int binary[] = new int[100];
        int i = 1;
        StringBuilder form = new StringBuilder();

        while( decimal != 0) {
            binary[i++] = (int) (decimal % 2);
            decimal /= 2;
        }

        for (int j = i - 1; j>0; j--) {
                form.append(binary[j]);
        }


        return form.toString();
    }

}
