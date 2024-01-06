package com.prometheus.egp_tpv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Locale;

import com.prometheus.egp_tpv.model.Programa;
import com.prometheus.egp_tpv.model.ProgrammeResponse;
import com.prometheus.egp_tpv.network.EGPGloboApi;
import com.prometheus.egp_tpv.network.EGPGloboService;
import com.prometheus.egp_tpv.view.ProgramaAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String data;
    List<Programa> programas;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        programas = new ArrayList<Programa>();

//        textView = findViewById(R.id.titleTextView);
//        TextView textView1 = findViewById(R.id.descriptionTextView);
//        ImageView imageView = findViewById(R.id.ICON);
        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);

        Log.d("CurrentDate", "Data : " + formattedDate);

        EGPGloboApi api = EGPGloboService.getInstance();
        Call<ProgrammeResponse> call = api.getProgramByDate("2024-01-06");
        call.enqueue(new OnResult(getApplicationContext()));
    }

    class OnResult implements Callback<ProgrammeResponse> {

        private Context context;

        OnResult(Context context) {
            this.context = context;
        }

        public void onResponse(
                @NonNull Call<ProgrammeResponse> call,
                @NonNull Response<ProgrammeResponse> response
        ) {
            if (response.body() != null) {
                List<Programa> entries = response.body().getProgramme().getEntries();
                programas.addAll(entries);
            }
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);

            ProgramaAdapter adapter = new ProgramaAdapter(programas);
            recyclerView.setAdapter(adapter);
        }

        public void onFailure(@NonNull Call<ProgrammeResponse> call, Throwable t) {
            Log.e("HTTP Request", "Error: " + t.getMessage());
            textView.setText(R.string.request_fail);
        }
    }

}