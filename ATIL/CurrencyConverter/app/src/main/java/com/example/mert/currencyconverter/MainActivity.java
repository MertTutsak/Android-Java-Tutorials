package com.example.mert.currencyconverter;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private EditText editText;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadData downloadData = new DownloadData();

                try {
                    String url = "https://api.fixer.io/latest?base=";
                    String choosenBase = editText.getText().toString();

                    if (choosenBase != ""){
                        downloadData.execute(url + choosenBase);
                    }else{
                        downloadData.execute(url + "try");
                    }


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Başlatma hatası :" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class DownloadData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("base");
                System.out.println("base :" + base);
                String date = jsonObject.getString("date");
                System.out.println("date :" + date);
                String rates = jsonObject.getString("rates");
                System.out.println("rates :" + rates);

                JSONObject jsonObject1 = new JSONObject(rates);
                String chf = jsonObject1.getString("CHF");
                textView2.setText("CHF :" + chf);
                String czk = jsonObject1.getString("CZK");
                textView3.setText("CZK :" + czk);
                String TRY = jsonObject1.getString("TRY");
                textView4.setText("TRY :" + TRY);

                Toast.makeText(getApplicationContext(), "BAŞARIYLA YAZDI", Toast.LENGTH_LONG).show();

            } catch (Exception e) {

                Toast.makeText(getApplicationContext(), "POST hatası :" + e.getMessage(), Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "BAŞLADI", Toast.LENGTH_LONG).show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {
                url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data > 0) {

                    char character = (char) data;
                    result += character;

                    data = inputStreamReader.read();
                }

                return result;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Bakground hatası :" + e.getMessage(), Toast.LENGTH_LONG).show();
                return null;
            }


        }
    }
}
