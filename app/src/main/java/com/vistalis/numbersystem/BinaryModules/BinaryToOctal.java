package com.vistalis.numbersystem.BinaryModules;

public class BinaryToOctal {

    public static String convert(long binary)
    {
        int  remainder, decimal = 0, quotient , i = 1;
        int[] octnum = new int[100];

        StringBuilder form = new StringBuilder();


        while( binary > 0 ) {
            remainder = (int) (binary % 10);
            decimal = decimal + remainder*i;
            i = i*2;
            binary /= 10;
        }

        i = 1;
        quotient = decimal;

        while( quotient > 0 ) {
            octnum[i++] = quotient % 8;
            quotient /= 8;
        }

        for(int j = i - 1; j>0; j--) {
            form.append(octnum[j]);
        }
        return form.toString();
    }
}
