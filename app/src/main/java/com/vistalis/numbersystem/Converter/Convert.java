package com.vistalis.numbersystem.Converter;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.vistalis.numbersystem.BuildConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Convert {

    private String result;
    private static final String MODULES = "Modules";
    private static final String TO = "To";
    private static final String SEPERATOR = ".";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Convert processor(String fromSystem, String toSystem, String value)
    {
        try {

            String className = BuildConfig.APPLICATION_ID + SEPERATOR +
                                fromSystem + MODULES + SEPERATOR + fromSystem + TO + toSystem;

            Class<?> c = Class.forName(className);

            Object object = c.newInstance();

            String result = null;

            if ( fromSystem.equals("Hexa") ) {
                Method method = c.getMethod("convert",String.class);
                result = (String) method.invoke(object, value);
            } else {
                Method method = c.getMethod("convert",Long.TYPE);
                result = (String) method.invoke(object,Long.parseLong(value));
            }

            this.setResult(result);

        } catch (ClassNotFoundException | NoSuchMethodException  | IllegalAccessException | InvocationTargetException  | InstantiationException e ) {
            e.printStackTrace();
        }
        return this;
    }

    private void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return this.result;
    }

}
