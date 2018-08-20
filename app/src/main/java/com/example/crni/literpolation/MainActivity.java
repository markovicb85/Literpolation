package com.example.crni.literpolation;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {

    private static int startPosition = 0;
    private String number = "";
    private TextView display;
    private TextView tv_1, tv_2, tv_3;
    private TextView tv_4, tv_5, tv_6;

    private double[] values = new double[6];
    private ArrayList<TextView> tv = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.textView);
        tv_1 = (TextView) findViewById(R.id.et_x1);
        tv_2 = (TextView) findViewById(R.id.et_x2);
        tv_3 = (TextView) findViewById(R.id.et_x3);
        tv_4 = (TextView) findViewById(R.id.et_y1);
        tv_5 = (TextView) findViewById(R.id.et_y2);
        tv_6 = (TextView) findViewById(R.id.et_y3);

        tv.addAll(Arrays.asList(tv_1, tv_2, tv_3, tv_4, tv_6));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onClickNumber(View v){
        Button b = (Button) v;
        number += b.getText();
        switch (number){
            case "0":
                updateDisplay(number);
                break;
            case ".":
                updateDisplay(number);
                break;
            case "-":
                updateDisplay(number);
                break;
            case "00":
                number = "0";
                updateDisplay(number);
                break;
            default:
                if(NumberUtils.isCreatable(number)){
                    updateDisplay(number);
                }else{
                    number = number.substring(0, number.length() - 1);
                }
                break;
        }
    }

    private void updateDisplay(String number) {
        display.setText(number);
    }

    private void clearDisplay(){
        number = "";
        display.setText("");
        display.setHint("0");
    }

    public void onClickClear(View v){
        if(number.length() > 1){
            number = number.substring(0, number.length() - 1);
            updateDisplay(number);
        }else{
            clearDisplay();
        }
    }

    public void onClickEqual(View v, int position){

        boolean validation = false;

        if(number.equals("")){
            number = "0";
        }else if(number.equals(".") || number.equals("-")){
            number = "";
        }


        setHintTextColor();

        for (int i = position; i < tv.size(); i++) {
            if (tv.get(i).getText() == ""){
                tv.get(i).setText(number);
                clearDisplay();

                for (TextView item : tv) {
                    if(item.getText().equals("")){
                        validation = false;
                        break;
                    }else{ validation = true; }
                }
                if(validation){
                    calculation(tv);
                    tv_5.setTextColor(Color.RED);
                }
                break;
            }
        }
        clearDisplay();
    }

    public void onClickEqual(View view){
        onClickEqual(view, startPosition);
    }

    public void onClickSelect(View view){

        if(view.getTag() != "6"){
            TextView tv = (TextView) view;
            startPosition = Integer.parseInt(tv.getTag().toString()) - 1;
            clearDisplay();
            tv.setText("");
            tv.setHintTextColor(Color.RED);
            tv_5.setTextColor(Color.GRAY);
        }
    }

    public void showDialogResult(View view){
        AlertDialog.Builder myBulder = new AlertDialog.Builder(MainActivity.this);
        @SuppressLint("InflateParams")
        View myView = getLayoutInflater().inflate(R.layout.dialog_info, null);

        TextView x1_value = (TextView) myView.findViewById(R.id.x1_value);
        TextView x2_value = (TextView) myView.findViewById(R.id.x2_value);
        TextView x3_value = (TextView) myView.findViewById(R.id.x3_value);
        TextView y1_value = (TextView) myView.findViewById(R.id.y1_value);
        TextView y2_value = (TextView) myView.findViewById(R.id.y2_value);
        TextView y3_value = (TextView) myView.findViewById(R.id.y3_value);

        x1_value.setText(tv_1.getText());
        x2_value.setText(tv_2.getText());
        x3_value.setText(tv_3.getText());
        y1_value.setText(tv_4.getText());
        y2_value.setText(tv_5.getText());
        y3_value.setText(tv_6.getText());

        myBulder.setView(myView);
        AlertDialog dialog = myBulder.create();
        dialog.show();
    }

    private void calculation(ArrayList<TextView> list){
        int i = 0;
        for (TextView v : list) {
            values[i] = Double.parseDouble((String) v.getText());
            i++;
        }
        double result = values[3] - (values[0] - values[1])*(values[3] - values[4])/(values[0] - values[2]);
        tv_5.setText(String.valueOf(result));
    }

    public void onClickClearAll(View v){
        tv_1.setText(null);
        tv_2.setText(null);
        tv_3.setText(null);
        tv_4.setText(null);
        tv_5.setText(null);
        tv_6.setText(null);
        display.setText("0");
        number = "";
        startPosition = 0;
        setHintTextColor();
    }

    public void setHintTextColor(){
        for (TextView text : tv) {
            text.setHintTextColor(Color.GRAY);
        }
        tv_5.setHintTextColor(Color.GRAY);
    }

    //TODO Blokirati digitron kada su sva polja popunjena ili ga vratioti na prvo...
}