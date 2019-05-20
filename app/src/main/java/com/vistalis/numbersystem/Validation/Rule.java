package com.vistalis.numbersystem.Validation;

import android.util.Log;

class Rule {

    static boolean number(String number) {
        return number.matches("[0-9]+");
    }

    static boolean binary(String number) {

        if ( number.matches("[2-9]+"))  {
            return false;
        } else {
            int binary = Integer.parseInt(number);

            while(binary != 0) {
                if ( binary  % 10 > 1) {
                    return false;
                }
                binary /= 10;
            }

            return true;
        }

    }
}
