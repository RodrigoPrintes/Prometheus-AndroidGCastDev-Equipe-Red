package com.prometheus.egp_tpv.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prometheus.egp_tpv.R;

public class ProgramaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("TITULO");
        String descricao = intent.getStringExtra("DESCRICAO");
        String imageUrl = intent.getStringExtra("IMAGEM_URL");

        TextView txtNome = findViewById(R.id.txtNome);
        TextView txtDescricao = findViewById(R.id.txtDescricao);
        ImageView imgBanner = findViewById(R.id.imgBanner);

        txtNome.setText(titulo);
        txtDescricao.setText(descricao);

        Glide.with(this)
                .load(imageUrl)
                .into(imgBanner);

    }
}