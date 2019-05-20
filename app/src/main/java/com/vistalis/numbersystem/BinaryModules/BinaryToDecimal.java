package com.vistalis.numbersystem.BinaryModules;

public class BinaryToDecimal {
    public static String convert(long binary)
    {
        return String.valueOf(Integer.parseInt(String.valueOf(binary),2));
    }
}
