package com.prometheus.egp_tpv;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Locale;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.titleTextView);
        TextView textView1 = findViewById(R.id.descriptionTextView);
        ImageView imageView = findViewById(R.id.ICON);
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);

        Log.d("CurrentDate", "Data : " + formattedDate);

        // Usage:
        URL_Conection urlConnection = new URL_Conection();
        urlConnection.getAPIProgramation(this,formattedDate, new Callback<String>() {
            @Override
            public void onSuccess(String response) throws JSONException {
                // Handle successful response here

                Log.d("HTTP Request", "TY: " + response);
                String jsonString = response;
                String outTest = "";
                JSONObject jsonData = new JSONObject(jsonString);
                JSONObject programme = jsonData.getJSONObject("programme");
                JSONArray entries = programme.getJSONArray("entries");

                List<Programa> programas = new ArrayList<>();


                for (int i = 0; i < entries.length(); i++) {
                    JSONObject entry = entries.getJSONObject(i);
                    Programa programa = new Programa();



                    programa.setTitle(entry.optString("title"));
                    programa.setDescription(entry.optString("description"));
                    programa.setStartTime(entry.optLong("start_time"));
                    programa.setEndTime(entry.optLong("end_time"));

                    JSONObject customInfo = entry.getJSONObject("custom_info");
                    JSONObject graphics = customInfo.getJSONObject("Graficos");

                    String logoURL = graphics.optString("LogoURL");
                    programa.setImageURL(logoURL);

                    programas.add(programa);
                }
                int i=0;
                for (Programa programa : programas) {


                    if( programa.getTitle() != "null"){
                        Log.d("HTTP Request", "Title: " + programa.getTitle());
                        Log.d("HTTP Request", "logoURL: " + programa.getImageURL());
                        Log.d("HTTP Request","id: "+ i);


                    }
                    i++;
                }


                textView.setText(programas.get(2).getTitle());
                Glide.with(MainActivity.this)
                        .load(programas.get(2).getImageURL())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
            }
            @Override
            public void onError(Exception e) {
                // Handle error here
                Log.e("HTTP Request", "Error: " + e.getMessage());
                textView.setText("deu errado");
            }
        });

        
    }



}