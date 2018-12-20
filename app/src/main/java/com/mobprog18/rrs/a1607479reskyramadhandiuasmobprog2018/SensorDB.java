package com.mobprog18.rrs.a1607479reskyramadhandiuasmobprog2018;
import com.mobprog18.rrs.a1607479reskyramadhandiuasmobprog2018.DBHelper;
import com.mobprog18.rrs.a1607479reskyramadhandiuasmobprog2018.Model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class SensorDB {


    private SQLiteDatabase db;
    private final DBHelper dbHelper;

    public SensorDB(Context c){
        dbHelper = new DBHelper(c);
    }

    public void open(){
        db  = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }


    public boolean insertSensor(Model sensor) {
        ContentValues newValues = new ContentValues();
        newValues.put("STATUS", sensor.status);
        newValues.put("TIMESTAMP", sensor.timestamp);

        try{
            db.insert("SENSOR", null, newValues);
            return true;
        }catch (SQLException ex){
            throw ex;
        }

    }

    public ArrayList<Model> getAllSensor() {
        Cursor cur = null;
        ArrayList<Model> output = new ArrayList<>();
        cur = db.rawQuery("SELECT * FROM SENSOR", null);

        if (cur.moveToFirst()) {
            do {
                Model m = new Model();
                m.id = Integer.valueOf(cur.getString(0));
                m.status = cur.getString(1);
                m.timestamp = cur.getString(2);
                output.add(m);
            } while (cur.moveToNext());
        }
        cur.close();
        return output;
    }


    public boolean deleteAllSensor(){
        try {

            db.execSQL("DELETE FROM SENSOR");
            return true;
        }catch (SQLException ex){
            return false;
        }
    }
}

