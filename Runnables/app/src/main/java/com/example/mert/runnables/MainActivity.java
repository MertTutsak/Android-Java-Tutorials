package com.example.mert.runnables;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private int number;
    private Handler handler;
    private Runnable run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        number = 0;

        handler = new Handler();


        run = new Runnable() {
            @Override
            public void run() {
                number++;
                textView.setText("Time : " + number);
                handler.postDelayed(this, 1000);

            }
        };
    }

    public void start(View view) {
        handler.post(run);
    }

    public void stop(View view) {
        handler.removeCallbacks(run);
    }
}
