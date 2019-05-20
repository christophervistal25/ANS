package com.vistalis.numbersystem.Validation;

import android.util.Log;

import static com.vistalis.numbersystem.Validation.Rule.*;

public class Validate {

    private boolean result = true;
    private String message;

    public Validate(String system, String value)
    {
        system = system.toLowerCase();

        switch(system) {

            case "decimal" :
                if ( !number(value) ) {
                    this.setMessage(system + " only accept a number");
                    this.setPass(false);
                }
                break;

            case "binary" :
                if ( !binary(value) ) {
                    this.setMessage(system + " only accept a number from 0 to 1");
                    this.setPass(false);
                }
                break;

            case "octal" :
                if ( !number(value) ) {
                    this.setMessage(system + " only accept a number");
                    this.setPass(false);
                }
                break;

        }
    }

    private void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }

    private void setPass(boolean result)
    {
        this.result = result;
    }

    public boolean isPass()
    {
            return this.result;
    }
}
