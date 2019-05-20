package com.vistalis.numbersystem;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vistalis.numbersystem.Converter.Convert;
import com.vistalis.numbersystem.Validation.Validate;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        String[] listAction = {
                "Decimal to Binary",
                "Decimal to Octal",
                "Decimal to Hexa",
                "Binary to Decimal",
                "Binary to Octal",
                "Binary to Hexa",
                "Octal to Binary",
                "Octal to Decimal",
                "Octal to Hexa",
                "Hexa to Decimal",
                "Hexa to Binary",
                "Hexa to Octal",
        };

        private boolean isConverted = false;
        private String currentAction;

        private EditText input;
        private TextView output;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivityToFullScreen();
        setContentView(R.layout.activity_main);

        Spinner actions = findViewById(R.id.actions);
        input = findViewById(R.id.input);
        output = findViewById(R.id.result);

        Button btnConvert = findViewById(R.id.btnConvert);
        Button btnSwitch = findViewById(R.id.btnSwitch);
        Button btnCopy = findViewById(R.id.btnCopy);


        actions.setOnItemSelectedListener(this);

        // Creating the ArrayAdapter instance having the action list.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listAction);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Setting the array adapter on the Spinner.
        actions.setAdapter(arrayAdapter);

        // Display the first item in the Spinner.
        actions.setSelection(0);

        // Text changed listener for input
        this.inputTextChangedListener();

        // Method event for converting
        this.processConvertion(btnConvert);

        // Event for Vice versa of the current action.
        this.switchAction(actions, btnSwitch, arrayAdapter);

        // Add event for copy to clipboard
        this.copyClipboard(btnCopy);

    }

    private void inputTextChangedListener() {

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ( editable.toString().isEmpty() ) {
                      output.setText("");
                }
            }
        });

    }

    private void processConvertion(Button btnConvert) {
        btnConvert.setOnClickListener((View v) -> {
            // Get the system
            String system = currentAction.split(" ")[0];

            // Convert to what system.
            String convertTo = currentAction.split(" ")[2];


            // User input
            String value = input.getText().toString();

            Validate validator = new Validate(system,value);

            if ( !validator.isPass() ) {
                Toast.makeText(this, validator.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }

            Convert convert = new Convert();
            String result = convert.processor(system,convertTo,value).getResult();
            output.setText(result);
        });
    }

    private void switchAction(Spinner actions, Button btnSwitch, ArrayAdapter<String> arrayAdapter) {
        btnSwitch.setOnClickListener(view -> {

            // Split current action
            String[] splitted = currentAction.split(" ");

            // Get the vice versa of current action
            String reverseAction = splitted[2] + " " + splitted[1] + " " + splitted[0];

            // Get the current Position
            int currentActionPosition = arrayAdapter.getPosition(reverseAction);

            // Set new position for Spinner
            actions.setSelection(currentActionPosition);

            Toast.makeText(this, "You change the action from " + currentAction + " to " + reverseAction + " please double check your current input.", Toast.LENGTH_LONG).show();
        });
    }

    private void copyClipboard(Button btnCopy) {
            btnCopy.setOnClickListener(view -> {
                if ( output.getText().toString().length() > 0 ) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copied Text",output.getText().toString());
                    clipboard.setPrimaryClip(clip);

                    Toast.makeText(this, "Text Copied", Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // Get the current action
        currentAction = listAction[position];

        ((TextView)adapterView.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView)adapterView.getChildAt(0)).setTypeface(Typeface.DEFAULT_BOLD);
        ((TextView)adapterView.getChildAt(0)).setTextSize(18);

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void setActivityToFullScreen()
    {
        Window window = getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        winParams.flags &= ~WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        window.setAttributes(winParams);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
