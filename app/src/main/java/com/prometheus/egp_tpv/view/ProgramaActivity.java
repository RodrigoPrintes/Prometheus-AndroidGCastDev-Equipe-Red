package com.prometheus.egp_tpv.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.prometheus.egp_tpv.MainActivity;
import com.prometheus.egp_tpv.R;

import java.util.Calendar;


public class ProgramaActivity extends AppCompatActivity {
    private static final int PROGRAM_NOTIFICATION_ID = 1;
    private String titulo;
    private String descricao;
    private static final String CHANNEL_ID = "ReminderChannel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa);

        Intent intent = getIntent();
        titulo = intent.getStringExtra("TITULO");
        descricao = intent.getStringExtra("DESCRICAO");
        String imageUrl = intent.getStringExtra("IMAGEM_URL");
        String time = intent.getStringExtra("TIME");

        TextView txtNome = findViewById(R.id.txtNome);
        TextView txtDescricao = findViewById(R.id.txtDescricao);
        ImageView imgBanner = findViewById(R.id.imgBanner);
        Button btnLembrete = findViewById(R.id.btnFechar);

        txtNome.setText(titulo);
        txtDescricao.setText(descricao);

        Glide.with(this)
                .load(imageUrl)
                .into(imgBanner);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){

            if (ContextCompat.checkSelfPermission(ProgramaActivity.this,
                    Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(ProgramaActivity.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }

        btnLembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeNotification(titulo,descricao);
                finish();
                onBackPressed();
            }
        });

    }
    public void makeNotification(String title, String description){
        String channel = "CHANNEL_ID_NOTIFICATION";

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(),channel);
        builder.setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Programa: "+ title )
                .setContentTitle(description)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 56);
        calendar.set(Calendar.SECOND, 00);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        builder.setWhen(calendar.getTimeInMillis());

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0,intent, PendingIntent.FLAG_IMMUTABLE);

        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel =
                    notificationManager.getNotificationChannel(channel);
            if (notificationChannel == null){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channel,
                        "Description",importance);

                notificationChannel.setLightColor(Color.BLUE);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        builder.setWhen(calendar.getTimeInMillis());
        notificationManager.notify(0, builder.build());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        Log.d("MENU","menu pass");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.calendar) {
            Log.d("MENUUU","menu pass");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}