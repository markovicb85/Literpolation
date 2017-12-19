package com.example.crni.literpolation;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private String number = "";
    private TextView display;
    private TextView x1, x, x2;
    private TextView y1, y, y2;
    private String position = "x1";
    private double result;
    private double x_0, x_1, x_2;
    private double y_1, y_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        if(b.getText().equals("-") && number.length() > 0){
            //Ne radi nista
        }else{
            number += b.getText();
            updateDisplay(number);
        }
    }

    private void updateDisplay(String number) {
        display.setText(number);
    }

    private void clearDisplay(){
        number = "";
        display.setText("0");
    }

    public void onClickClear(View v){
        if(number.length() > 1){
            number = number.substring(0, number.length() - 1);
            updateDisplay(number);
        }else{
            clearDisplay();
        }
    }

    public void onClickEqual(View v){
        if(number.equals("") || number.equals(".") || number.equals("-")){
            number = "0";
        }
        switch (position){
            case "x1":
                x1.setText(number);
                x1.setTextColor(Color.BLACK);
                x_1 = Double.parseDouble(number);
                clearDisplay();
                position = "x2";
                break;
            case "x2":
                x.setText(number);
                x.setTextColor(Color.BLACK);
                x_0 = Double.parseDouble(number);
                clearDisplay();
                position = "x3";
                break;
            case "x3":
                x2.setText(number);
                x2.setTextColor(Color.BLACK);
                x_2 = Double.parseDouble(number);
                clearDisplay();
                position = "y1";
                break;
            case "y1":
                y1.setText(number);
                y1.setTextColor(Color.BLACK);
                y_1 = Double.parseDouble(number);
                clearDisplay();
                position = "y2";
                break;
            case "y2":
                y2.setText(number);
                y2.setTextColor(Color.BLACK);
                y_2 = Double.parseDouble(number);
                clearDisplay();
                position = "y3";
                break;
            default:
                result = calculation();
                y.setTextColor(Color.BLACK);
                y.setText(Double.toString(result));
                position = "x1";
                break;
        }

    }

    public void onClickSelect(View v){
        TextView tv= (TextView) v;
        number = (String) tv.getText();
        updateDisplay(number);
        tv.setTextColor(Color.RED);
        position = (String) tv.getTag();
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
        position = "x1";
    }
}
