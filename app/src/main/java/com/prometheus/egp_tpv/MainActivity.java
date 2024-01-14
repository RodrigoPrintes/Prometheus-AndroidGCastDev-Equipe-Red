package com.prometheus.egp_tpv;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prometheus.egp_tpv.dao.ProgramaDAO;
import com.prometheus.egp_tpv.model.Programa;
import com.prometheus.egp_tpv.model.ProgrammeResponse;
import com.prometheus.egp_tpv.network.EGPGloboApi;
import com.prometheus.egp_tpv.network.EGPGloboService;
import com.prometheus.egp_tpv.utils.Utils;
import com.prometheus.egp_tpv.view.ProgramaActivity;
import com.prometheus.egp_tpv.view.ProgramaAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final MainActivity currentActivity = this;
    List<Programa> programas;
    TextView textView;
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm", Locale.US);
    ProgramaDAO programaDAO;
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

        programaDAO = new ProgramaDAO(MainActivity.this);


    }

    private void onSelectItem(Programa programa) {
        Intent intent = new Intent(this, ProgramaActivity.class);
        intent.putExtra("TITULO", programa.getProgram().getName());
        intent.putExtra("DESCRICAO", programa.getDescription());
        intent.putExtra("IMAGEM_URL", programa.getCustomInfo().getGraficos().getImagemURL());
        intent.putExtra("TIME", Utils.formatTimeString(programa.getHuman_start_time()));

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


            try {
                for(Programa p : programas) {
                    //check item
                    p.setDate(response.body().getProgramme().getDate());
                    programaDAO.add(p);
                }
            }catch (Exception e){
                Log.e("Error Database", e.getMessage());
            }

        }

        public void onFailure(@NonNull Call<ProgrammeResponse> call, Throwable t) {
            Log.e("HTTP Request", "Error: " + t.getMessage());
            textView.setText(R.string.request_fail);
        }
    }

    public void showDatePickerDialog() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (datePicker, year1, month1, day) -> {

                    String selectedDate = day + "/" + (month1 + 1) + "/" + year1;
                    Toast.makeText(MainActivity.this, "Data selecionada: " + selectedDate, Toast.LENGTH_SHORT).show();
                }, year, month, dayOfMonth);


        datePickerDialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}