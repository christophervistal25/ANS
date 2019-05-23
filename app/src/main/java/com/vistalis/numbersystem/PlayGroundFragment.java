package com.vistalis.numbersystem;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vistalis.numbersystem.Converter.Convert;
import com.vistalis.numbersystem.Helpers.SharedPreferenceHelper;
import com.vistalis.numbersystem.Validation.Validate;


public class PlayGroundFragment extends Fragment {


    private EditText input;
    private TextView output;


    public PlayGroundFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        input = view.findViewById(R.id.input);
        output = view.findViewById(R.id.result);

        Button btnConvert = view.findViewById(R.id.btnConvert);
        Button btnSwitch = view.findViewById(R.id.btnSwitch);
        Button btnCopy = view.findViewById(R.id.btnCopy);

        // Redirect to MechanismActivity
        view.findViewById(R.id.btnMechanism).setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(),MechanismActivity.class);
            startActivity(intent);
        });


        // Text changed listener for input
        this.inputTextChangedListener();

        // Method event for converting
        this.processConvertion(btnConvert);

        // Event for Vice versa of the current action.
        this.switchAction(btnSwitch);

        // Add event for copy to clipboard
        this.copyClipboard(btnCopy);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_playground, container, false);
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
            String system = SharedPreferenceHelper
                    .getSharedPreferenceString(getContext(), "FROM",null);

            String convertTo = SharedPreferenceHelper
                    .getSharedPreferenceString(getContext(),"TO",null);

            // User input
            String value = input.getText().toString();

            Validate validator = new Validate(system,value);

            if ( !validator.isPass() ) {
                Toast.makeText(getContext(), validator.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }

            Convert convert = new Convert();
            String result = convert.processor(system,convertTo,value).getResult();
            output.setText(result);

        });
    }

    private void switchAction(Button btnSwitch) {

        btnSwitch.setOnClickListener(view -> {
            String system = SharedPreferenceHelper
                    .getSharedPreferenceString(getContext(), "FROM",null);

            String convertTo = SharedPreferenceHelper
                    .getSharedPreferenceString(getContext(),"TO",null);

            SharedPreferenceHelper.setSharedPreferenceString(getContext(),"FROM",convertTo);
            SharedPreferenceHelper.setSharedPreferenceString(getContext(),"TO", system);

            String reverseAction = convertTo + " to " + system;

            Toast.makeText(getContext(), "You change the action to " + reverseAction + " please double check your current input.", Toast.LENGTH_LONG).show();
        });
    }

    private void copyClipboard(Button btnCopy) {
        btnCopy.setOnClickListener(view -> {
            if ( output.getText().toString().length() > 0 ) {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text",output.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getContext(), "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
