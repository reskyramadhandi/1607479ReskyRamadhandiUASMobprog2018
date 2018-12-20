package com.mobprog18.rrs.a1607479reskyramadhandiuasmobprog2018;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements SensorEventListener,View.OnClickListener {
    private SensorManager sm;
    private Sensor senAccel;
    private TextView tvHasil;
    private Button clear,save,load;
    private TextView tes;

    @Override
    protected void onResume() {
        super.onResume();

        sm.registerListener(this, senAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    ListAdapter adapter;
    private ArrayList<Model> data;
    private RecyclerView rv;
    private SensorDB dbSensor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorDetect();
        clear = (Button) findViewById(R.id.clear);
        save = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);

        clear.setOnClickListener(this);
        save.setOnClickListener(this);
        load.setOnClickListener(this);


        tvHasil = (TextView) findViewById(R.id.tv_hasil);
        rv = (RecyclerView)findViewById(R.id.rv_status);
        rv.setHasFixedSize(true);

        data = new ArrayList<>();

        dbSensor = new SensorDB(getApplicationContext());
        dbSensor.open();
        dbSensor.deleteAllSensor();
        dbSensor.close();


        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAdapter(this);
        adapter.setListSensor(data);
        rv.setAdapter(adapter);

    }

    public void sensorDetect(){
        sm = (SensorManager)    getSystemService(getApplicationContext().SENSOR_SERVICE);
        senAccel = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        if (senAccel != null){
            // ada sensor accelerometer!
        }
        else {
            // gagal, tidak ada sensor accelerometer.
            Toast.makeText(this,"Tidak ada sensor", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double ax=0,ay=0,az=0;

        // menangkap nilai sensor pada setiap perubahan
        if (sensorEvent.sensor.getType()==Sensor.TYPE_LINEAR_ACCELERATION) {
            ax=sensorEvent.values[0];
            ay=sensorEvent.values[1];
            az=sensorEvent.values[2];
        }

        Model model = new Model();

        // Jika sensor mendeteksi adanya pergerakan yang cukup kuat dengan kenaikan threshold z > 5 atau < -5 maka aplikasi akan mendeteksinya
        if(az>5 || ay<-5){
            model.status = "Pengereman Mendadak!";
            model.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            data.add(model);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }

    @Override
    protected void onDestroy(){
        dbSensor.close();
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.clear){
            data.clear();
            adapter.notifyDataSetChanged();
        }else if(v.getId() == R.id.save){
            dbSensor.open();
            for (Model m :data) {
                dbSensor.insertSensor(m);
            }
            Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show();
            dbSensor.close();

        }else if(v.getId() == R.id.load){

            dbSensor.open();
            data = dbSensor.getAllSensor();
            dbSensor.close();

            rv.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ListAdapter(this);
            adapter.setListSensor(data);
            rv.setAdapter(adapter);

            if(data.size()>0){

                Toast.makeText(this,"Loaded", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Empty", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
