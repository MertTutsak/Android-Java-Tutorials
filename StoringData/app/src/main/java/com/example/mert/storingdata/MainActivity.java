package com.example.mert.storingdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private int age = 40;

    private int savedAge;
    private int savedAge2;
    private int savedAge3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.mert.storingdata", Context.MODE_PRIVATE);

        //KAYDETTI
        sharedPreferences.edit().putInt("userAge",age).apply();
        
        savedAge = sharedPreferences.getInt("userAge",0);
        System.out.println("userAge :" + savedAge);

        //GUNCELLEDI
        age = 10;
        sharedPreferences.edit().putInt("userAge",age).apply();

        savedAge2 = sharedPreferences.getInt("userAge",0);
        System.out.println("userAge :" + savedAge2);

        //SILDI
        sharedPreferences.edit().remove("userAge").apply();

        savedAge3 = sharedPreferences.getInt("userAge",0);
        System.out.println("userAge :" + savedAge3);



    }
}
