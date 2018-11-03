package com.example.mert.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private TextView resultTextView;

    private int a;
    private int b;

    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        resultTextView = (TextView) findViewById(R.id.resultText);
    }

    public void sum(View view) {
        a = Integer.parseInt(editText1.getText().toString());
        b = Integer.parseInt(editText2.getText().toString());

        result = a + b;

        resultTextView.setText(String.valueOf(result));
    }

    public void deduct(View view) {
        a = Integer.parseInt(editText1.getText().toString());
        b = Integer.parseInt(editText2.getText().toString());

        result = a - b;

        resultTextView.setText(String.valueOf(result));
    }

    public void multiply(View view) {
        a = Integer.parseInt(editText1.getText().toString());
        b = Integer.parseInt(editText2.getText().toString());

        result = a * b;

        resultTextView.setText(String.valueOf(result));
    }

    public void divide(View view) {
        a = Integer.parseInt(editText1.getText().toString());
        b = Integer.parseInt(editText2.getText().toString());

        result = a / b;

        resultTextView.setText(String.valueOf(result));
    }
}
