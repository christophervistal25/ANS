package com.vistalis.numbersystem.BinaryModules;

public class BinaryToHexa {

    public static String convert(long binary) {
        StringBuilder form = new StringBuilder();
        int[] hex = new int[100];
        int i = 1, rem , dec = 0;

        while( binary > 0 ) {
            rem = (int) (binary % 2);
            dec = dec + rem * i;
            i = i * 2;
            binary /= 10;
        }

        i = 0;

        while( dec != 0 ) {
            hex[i] = dec % 16;
            dec /= 16;
            i++;
        }

        for(int j = i - 1; j>= 0; j--) {
            if (hex[j] > 9) {
                form.append((char) (hex[j] + 55));
            } else {
                form.append(hex[j]);
            }
        }

        return form.toString();

    }
}
