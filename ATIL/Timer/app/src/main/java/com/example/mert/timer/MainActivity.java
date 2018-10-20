package com.example.mert.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);

        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Left :" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                textView.setText("Left : 0");

                Toast.makeText(MainActivity.this, "Time's Done", Toast.LENGTH_LONG).show();
            }
        }.start();
    }
}
