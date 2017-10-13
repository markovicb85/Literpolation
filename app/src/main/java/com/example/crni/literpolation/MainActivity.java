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
    private int position = 0;
    private double result;
    private double x_0, x_1, x_2;
    private double y_1, y_2;


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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onClickNumber(View v){
        Button b = (Button) v;
        number += b.getText();
        updateDisplay();

    }

    private void updateDisplay() {
        display.setText(number);
    }

    private void clearDisplay(){
        number = "";
        display.setText("0");
    }

    public void onClickClear(View v){
        if(number.length() > 1){
            number = number.substring(0, number.length() - 1);
            updateDisplay();
        }else{
            clearDisplay();
        }
    }

    public void onClickEqual(View v){
        if(number.equals("") || number.equals(".") || number.equals("-")){
            number = "0";
        }
        switch (position){
            case 0:
                x1.setText(number);
                x_1 = Double.parseDouble(number);
                clearDisplay();
                position = 1;
                break;
            case 1:
                x.setText(number);
                x_0 = Double.parseDouble(number);
                clearDisplay();
                position = 2;
                break;
            case 2:
                x2.setText(number);
                x_2 = Double.parseDouble(number);
                clearDisplay();
                position = 3;
                break;
            case 3:
                y1.setText(number);
                y_1 = Double.parseDouble(number);
                clearDisplay();
                position = 4;
                break;
            case 4:
                y2.setText(number);
                y_2 = Double.parseDouble(number);
                clearDisplay();
                position = 5;
                break;
            default:
                result = calculation();
                y.setText(Double.toString(result));
                position = 0;
                break;
        }

    }

    private double calculation() {
        result = y_1 - (x_1-x_0)*(y_1-y_2)/(x_1-x_2);
        return result;
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
        position = 0;
    }
}
