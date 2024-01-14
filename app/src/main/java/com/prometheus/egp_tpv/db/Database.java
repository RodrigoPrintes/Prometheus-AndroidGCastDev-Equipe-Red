package com.prometheus.egp_tpv.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =4;
    public static final String DATABASE_NAME = "egp.db";

    public static final String SQL_CREATE_PASS = "CREATE TABLE programas (" +
            "mediaId INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title TEXT," +
            "description TEXT," +
            "webmediaTitleId TEXT," +
            "startTime INTEGER," +
            "endTime INTEGER," +
            "humanStartTime TEXT," +
            "durationInMinutes INTEGER," +
            "date TEXT)" ;

    private static final String SQL_DELETE_PASS = "DROP TABLE IF EXISTS programas";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PASS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PASS);
        onCreate(db);
    }
}