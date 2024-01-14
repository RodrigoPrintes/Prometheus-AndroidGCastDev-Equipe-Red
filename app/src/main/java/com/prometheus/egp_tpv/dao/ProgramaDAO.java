package com.prometheus.egp_tpv.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.prometheus.egp_tpv.db.Database;
import com.prometheus.egp_tpv.model.Programa;

import java.util.ArrayList;

public class ProgramaDAO {

    private SQLiteDatabase database;
    private Context context;

    public ProgramaDAO(Context context) {
        this.context = context;
        this.database = (new Database(context)).getWritableDatabase();
    }

    public ArrayList<Programa> getList() {
        ArrayList<Programa> list = new ArrayList<>();
        String sql = "SELECT * FROM programas";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int mediaId = cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            String webMediaTitleId = cursor.getString(3);
            int startTime = cursor.getInt(4);
            int endTime = cursor.getInt(5);
            String humanStartTime = cursor.getString(6);
            int durationInMinutes = cursor.getInt(7);
            String date = cursor.getString(8);
            list.add(new Programa( mediaId, title, description, webMediaTitleId, startTime,
                    endTime, humanStartTime, durationInMinutes, date));
        }
        return list;
    }
    private boolean check_if_already_exits(String  title, String date){

        String sql = "SELECT * FROM programas";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null){
            while (cursor.moveToNext() ) {
                if (cursor.getString(1).equals(title) && cursor.getString(8).equals(date)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean add (Programa programa) {

        if (!check_if_already_exits(programa.getTitle(),programa.getDate() )){
        check_if_already_exits(programa.getTitle(),programa.getDate());
            String sql = "INSERT INTO programas VALUES (NULL, "
                    + "'" + programa.getTitle() + "', "
                    + "'" + programa.getDescription() + "', "
                    + "'" + programa.getWebmediaTitleId() + "', "
                    + programa.getStartTime() + ", "
                    + programa.getEndTime() + ", "
                    + "'" + programa.getHuman_start_time() + "', "
                    + programa.getDurationInMinutes() + ","
                    +"'" +programa.getDate()+"')";

            try {
                database.execSQL(sql);
                Log.d("Database", "Programa salvo!");
                return true;
            } catch (SQLException e) {
                Log.e("Error Database", e.getMessage());
                return false;
            }
        }
        return false;

    }
}