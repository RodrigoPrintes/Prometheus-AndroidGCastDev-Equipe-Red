package com.example.questao12;

import static com.example.questao12.DatabaseHelper.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome, editTextNumero, editTextEndereco;
    private Button btnSalvar, btnCarregar;
    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextEndereco = findViewById(R.id.editTextEndereco);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnCarregar = findViewById(R.id.btnCarregar);

        databaseHelper = new DatabaseHelper(this);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarDados();
            }
        });

        btnCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarDados();
            }
        });

    }

    private void salvarDados() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, editTextNome.getText().toString());
        values.put(COLUMN_NUMERO, editTextNumero.getText().toString());
        values.put(COLUMN_ENDERECO, editTextEndereco.getText().toString());

        db.insert(TABLE_NAME, null, values);

        // Limpar os campos após a inserção
        editTextNome.getText().clear();
        editTextNumero.getText().clear();
        editTextEndereco.getText().clear();

        db.close();
    }

    private void carregarDados() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] columns = {DatabaseHelper.COLUMN_NOME, DatabaseHelper.COLUMN_NUMERO, DatabaseHelper.COLUMN_ENDERECO};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);

        // Processar os dados do cursor, por exemplo, exibir em um ListView
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOME));
                String numero = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NUMERO));
                String endereco = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ENDERECO));

                // Faça o que quiser com os dados (ex: exibir em um log ou atualizar uma lista)
                Log.d("MainActivity", "Nome: " + nome + ", Número: " + numero + ", Endereço: " + endereco);
                Toast.makeText(this, "Nome: " + nome + ", Numero: " + numero + ", Endereco: " + endereco, Toast.LENGTH_SHORT).show();

            } while (cursor.moveToNext());
            cursor.close();
        }




        db.close();
    }
}
