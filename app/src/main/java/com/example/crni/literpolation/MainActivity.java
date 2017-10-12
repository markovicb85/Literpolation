package com.example.crni.literpolation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String number = "";
    private TextView display;
    private TextView x1, x, x2;
    private TextView y1, y, y2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.textView);
        x1 = (TextView) findViewById(R.id.et_x1);
        x = (TextView) findViewById(R.id.et_x2);
        x2 = (TextView) findViewById(R.id.et_x3);
        y1 = (TextView) findViewById(R.id.et_y1);
        y = (TextView) findViewById(R.id.et_y2);
        y2 = (TextView) findViewById(R.id.et_y3);
    }

    public void onClickNumber(View v){
        Button b = (Button) v;
        number += b.getText();
        updateDisplay();

    }

    private void updateDisplay() {
        display.setText(number);
    }

    public void onClickClear(View v){
        if(number.length() > 1){
            number = number.substring(0, number.length() - 1);
            updateDisplay();
        }else{
            number = "";
            display.setText("0");
        }
    }

    public void onClickEqual(View v){
        x1.setText(number);
    }

    public void onClickClearAll(View v){
        x1.setText(null);
        x.setText(null);
        x2.setText(null);
        y1.setText(null);
        y.setText(null);
        y2.setText(null);
        display.setText("0");
        number = "";
    }
}
