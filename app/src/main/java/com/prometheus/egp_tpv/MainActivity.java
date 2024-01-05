package com.prometheus.egp_tpv;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.titleTextView);
        TextView textView1 = findViewById(R.id.descriptionTextView);

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
                System.out.println("Response: " + response);
                Log.d("HTTP Request", "TY: " + response);
                String jsonString = response;
                String outTest = "";
                JSONObject jsonData = new JSONObject(jsonString);
                JSONObject programme = jsonData.getJSONObject("programme");
                JSONArray entries = programme.getJSONArray("entries");

                List<Programa> programas = new ArrayList<>();
                Programa.Graficos graficos = new Programa.Graficos();
                // Itera sobre as entradas e cria objetos ProgramaTV
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
                for (Programa programa : programas) {


                    if( programa.getTitle() != "null"){
                        Log.d("HTTP Request", "Title: " + programa.getTitle());
                        Log.d("HTTP Request", "grf: " + graficos.getLogoURL());

                        Log.d("HTTP Request", "logoURL: " + programa.getImageURL());
                    }
                }

                textView.setText(programas.get(2).getTitle());
            }
            @Override
            public void onError(Exception e) {
                // Handle error here
                Log.e("HTTP Request", "Error: " + e.getMessage());
                textView.setText("deu errado");
            }
        });



        textView.setText("TESTEETETEET");


        
    }

}