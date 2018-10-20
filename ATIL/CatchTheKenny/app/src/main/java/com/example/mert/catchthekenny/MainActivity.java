package com.example.mert.catchthekenny;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView scoretext;
    private TextView timetext;

    private ImageView[] imageViews;

    private int score;

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoretext = (TextView) findViewById(R.id.textView2);
        timetext = (TextView) findViewById(R.id.textView);

        imageViews = new ImageView[9];

        imageViews[0] = (ImageView) findViewById(R.id.imageView1);
        imageViews[1] = (ImageView) findViewById(R.id.imageView2);
        imageViews[2] = (ImageView) findViewById(R.id.imageView3);
        imageViews[3] = (ImageView) findViewById(R.id.imageView4);
        imageViews[4] = (ImageView) findViewById(R.id.imageView5);
        imageViews[5] = (ImageView) findViewById(R.id.imageView6);
        imageViews[6] = (ImageView) findViewById(R.id.imageView7);
        imageViews[7] = (ImageView) findViewById(R.id.imageView8);
        imageViews[8] = (ImageView) findViewById(R.id.imageView9);

        score = 0;

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timetext.setText("Time :" + l / 1000);
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                timetext.setText("Time's off");

            }
        }.start();

        handler = new Handler();

        hideImages();
    }

    public void increaseScore(View view) {

        score++;

        scoretext.setText("Score :" + score);
    }

    public void hideImages() {
        runnable = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();

                int randomSayi = random.nextInt(8);

                for (int i = 0; i < imageViews.length; i++) {
                    if (i == randomSayi) {
                        imageViews[i].setVisibility(View.VISIBLE);
                    } else {
                        imageViews[i].setVisibility(View.INVISIBLE);
                    }
                }

                handler.postDelayed(this, 500);
            }
        };

        handler.post(runnable);
    }
}
