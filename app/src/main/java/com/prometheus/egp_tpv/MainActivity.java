package com.prometheus.egp_tpv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prometheus.egp_tpv.model.Programa;
import com.prometheus.egp_tpv.model.ProgrammeResponse;
import com.prometheus.egp_tpv.network.EGPGloboApi;
import com.prometheus.egp_tpv.network.EGPGloboService;
import com.prometheus.egp_tpv.view.ProgramaActivity;
import com.prometheus.egp_tpv.view.ProgramaAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final MainActivity currentActivity = this;
    List<Programa> programas;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        programas = new ArrayList<>();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);

        EGPGloboApi api = EGPGloboService.getInstance();
        Call<ProgrammeResponse> call = api.getProgramByDate(formattedDate);
        call.enqueue(new OnResult(getApplicationContext()));
    }

    private void onSelectItem(Programa programa) {
        Intent intent = new Intent(this, ProgramaActivity.class);
        intent.putExtra("TITULO", programa.getProgram().getName());
        intent.putExtra("DESCRICAO", programa.getDescription());
        intent.putExtra("IMAGEM_URL", programa.getCustomInfo().getGraficos().getImagemURL());
        startActivity(intent);
    }

    class OnResult implements Callback<ProgrammeResponse> {

        private final Context context;

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

            ProgramaAdapter adapter = new ProgramaAdapter(programas, currentActivity::onSelectItem);
            recyclerView.setAdapter(adapter);
        }

        public void onFailure(@NonNull Call<ProgrammeResponse> call, Throwable t) {
            Log.e("HTTP Request", "Error: " + t.getMessage());
            textView.setText(R.string.request_fail);
        }
    }

}