package com.vistalis.numbersystem;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vistalis.numbersystem.Helpers.SharedPreferenceHelper;

import java.util.ArrayList;

public class MechanismActivity extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener {

    LinearLayout fromSystem;
    LinearLayout toSystem;
    FrameLayout mainLayout;

    ImageView btnBinary;
    ImageView btnDecimal;
    ImageView btnOctal;
    ImageView btnHexa;

    ArrayList<View> draggedItem = new ArrayList<>();
    ArrayList<View> viewAlreadySet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanism);

        fromSystem = findViewById(R.id.fromSystem);
        toSystem = findViewById(R.id.toSystem);


         btnBinary = findViewById(R.id.btnBinary);
         btnDecimal = findViewById(R.id.btnDecimal);
         btnOctal = findViewById(R.id.btnOctal);
         btnHexa = findViewById(R.id.btnHexa);

        btnBinary.setTag("Binary");
        btnDecimal.setTag("Decimal");
        btnOctal.setTag("Octal");
        btnHexa.setTag("Hexa");



        btnBinary.setOnTouchListener(this);
        btnDecimal.setOnTouchListener(this);
        btnOctal.setOnTouchListener(this);
        btnHexa.setOnTouchListener(this);

        //Set Drag Event Listeners for defined layouts
        findViewById(R.id.fromSystem).setOnDragListener(this);
        findViewById(R.id.toSystem).setOnDragListener(this);

        mainLayout = findViewById(R.id.mainLayout);

        // Placing the two drag-item container to front
        fromSystem.bringToFront();
        toSystem.bringToFront();


        this.resetEventClick();

        this.isMechanismAlreadySet();

    }

    private void resetEventClick() {
        findViewById(R.id.btnReset).setOnClickListener(view -> {
            final DisplayMetrics DISPLAY_METRICS = (this).getResources().getDisplayMetrics();
            final int DISPLAY_HEIGHT = DISPLAY_METRICS.heightPixels;
            final int DISPLAY_WIDTH = DISPLAY_METRICS.widthPixels;

            if ( fromSystem.getChildCount() >= 1 && toSystem.getChildCount() >= 1 ) {
                 for(View imageView : draggedItem) {

                     ViewGroup parent = (ViewGroup) imageView.getParent();

                     if ( parent != null ) {
                         parent.removeAllViews();
                         mainLayout.addView(imageView);
                     }

                 }

                 // Rebase
                draggedItem.clear();

            }

        });
    }

    // If mechanism is already set.
    private void isMechanismAlreadySet() {
        String fromMechanism = SharedPreferenceHelper.getSharedPreferenceString(this,"FROM",null);
        String toMechanism = SharedPreferenceHelper.getSharedPreferenceString(this,"TO",null);

        if ( fromMechanism != null && toMechanism != null ) {
            int index = 0;

            String fromMechanismId = "btn" + fromMechanism;
            String toMechanismId = "btn" + toMechanism;

            viewAlreadySet.add(
                    findViewById(getResources().getIdentifier(fromMechanismId, "id", getPackageName()))
            );

            viewAlreadySet.add(
                    findViewById(getResources().getIdentifier(toMechanismId, "id", getPackageName()))
            );

            for( View view : viewAlreadySet ) {
                draggedItem.add(view);
                mainLayout.removeView(view);

                if  ( index == 0 ) {
                    fromSystem.addView(view);
                } else {
                    toSystem.addView(view);
                }
                view.setVisibility(View.VISIBLE);

                index++;
            }
        }
    }


    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        switch(action) {
            case DragEvent.ACTION_DRAG_STARTED :
                if ( dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) ) {
                    return true;
                }
                return false;

            case DragEvent.ACTION_DROP :
                ClipData.Item item = dragEvent.getClipData().getItemAt(0);
                String dragData = item.getText().toString();
                LinearLayout container = (LinearLayout) view;
                if ( (!(container.getChildCount() >= 1)) ) {

                    View vw = (View) dragEvent.getLocalState();
                    draggedItem.add(vw);
                    ViewGroup owner = (ViewGroup) vw.getParent();
                    owner.removeView(vw);

                    container.addView(vw);
                    vw.setVisibility(View.VISIBLE);

                    SharedPreferenceHelper.setSharedPreferenceString(this, (String) container.getTag(), (String) vw.getTag());

                }

                return true;

            default :
                Log.d("DRAG_AND_DROP_EXAMPLE","Unknown action type");
                break;
        }
        return false;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
        View.DragShadowBuilder dragShadow = new View.DragShadowBuilder(view);
        view.startDrag(data, dragShadow, view, 0);
        return true;
    }
}
