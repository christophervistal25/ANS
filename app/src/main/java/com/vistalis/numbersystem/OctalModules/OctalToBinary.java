package com.vistalis.numbersystem.OctalModules;



public class OctalToBinary {
    public static String convert(long octal)
    {
        int[] octal_num_values = {0,1,10,11,100,101,110,111};
        long  tempoctal_number, binary_num, place;
        int rem;
        tempoctal_number = octal;
        binary_num = 0;
        place = 1;

        while( tempoctal_number != 0 ) {
            rem = (int) (tempoctal_number % 10);
            binary_num = octal_num_values[rem] * place + binary_num;
            tempoctal_number /= 10;
            place *= 1000;
        }
        return String.valueOf(binary_num);
    }
}
