package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener{

    private GestureDetectorCompat detector;
    TextView numText;
    CheckBox negNumChkBox;
    Boolean negativeNumbers = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detector = new GestureDetectorCompat(this,this);
        numText = (TextView) findViewById(R.id.numText);
        negNumChkBox = (CheckBox) findViewById(R.id.negativeNumbersCheckBox);
        ConstraintLayout Clayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        Clayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

    }

    public void showInfo(View view)
    {
        Toast.makeText(this, "Increment - Single-Tap\nDecrement - Double-Tap\nReset - Long-Press", Toast.LENGTH_LONG).show();
    }

    public void negativeNumFunction(View view)
    {
        if(negNumChkBox.isChecked())
        {
            Log.i("Info : ", "Checkbox : True");
            negativeNumbers = true;
        }
        else
        {
            Log.i("Info : ","Checkbox : False");
            negativeNumbers = false;
            if(Integer.parseInt(numText.getText().toString()) < 0)
            {
                numText.setText("0");
            }
        }
    }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.i("Info : ","Single-Tap!!!");
        int increment = Integer.parseInt(numText.getText().toString()) + 1;
        numText.setText(String.valueOf(increment));
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i("Info : ","Double-Tap!!!");
        int decrement = Integer.parseInt(numText.getText().toString()) - 1;
        if(decrement < 0 && !negativeNumbers)
        {
            Toast.makeText(this, "Not less than 0!", Toast.LENGTH_SHORT).show();
        }
        else {
            numText.setText(String.valueOf(decrement));
        }
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {return false; }

    @Override
    public boolean onDown(MotionEvent e) { return false; }

    @Override
    public void onShowPress(MotionEvent e) { }

    @Override
    public boolean onSingleTapUp(MotionEvent e) { return false; }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { return false; }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i("Info : ","Long-Press!!!");
        numText.setText("0");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { return false; }
}