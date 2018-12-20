package com.mobprog18.rrs.a1607479reskyramadhandiuasmobprog2018;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dbSensor.db";
    public static final String TABLE_CREATE = "CREATE TABLE SENSOR (ID INTEGER PRIMARY KEY AUTOINCREMENT, STATUS TEXT, TIMESTAMP TEXT)";

    public DBHelper(@Nullable Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(TABLE_CREATE);
        }catch (SQLException ex){
            throw ex;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {

            db.execSQL("DROP TABLE IF EXISTS SENSOR");
        }catch (SQLException ex){
            throw ex;
        }
    }
}
