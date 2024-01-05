package com.prometheus.egp_tpv;

import java.io.InputStreamReader;
import java.net.URL;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

interface Callback<T> {
    void onSuccess(T result) throws JSONException;
    void onError(Exception e);
}
public class URL_Conection  {
    public  URL_Conection(){

    }

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void getAPIProgramation(Context context, String date, Callback<String> callback) {

        executorService.submit(() -> {
            try {
                String uri = "https://epg-api.video.globo.com/programmes/1337?date=" + date;
                URL url = new URL(uri);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line + "\n");
                }
                bufferedReader.close();
                urlConnection.disconnect();

                // Handle the response here
                Log.d("HTTP Request OK", response.toString());
                String responseString = response.toString();
                callback.onSuccess(responseString);

                return response.toString();
            } catch (IOException e) {
                Log.e("HTTP Request ERROR", "Error: " + e.getMessage());
                callback.onError(e);
                return null;
            }
        });
    }


}
