package ca.gbc.comp3074.LabTest1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Sensor illumSensor;
    private TextView tempText;
    private TextView illumText;
    private Boolean isTempSensorAvailable;
    private Boolean isIllumSensorAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

         tempText = findViewById(R.id.temperature_text);
        illumText = findViewById(R.id.illuminance_text);
        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //check if the temperature sensor is available on device
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTempSensorAvailable = true;

        } else {
            tempText.setText("Temperature sensor is not available!");
            isTempSensorAvailable = false;
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            illumSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            isIllumSensorAvailable = true;

        } else {
            illumText.setText("Illuminance sensor is not available!");
            isIllumSensorAvailable = false;
        }


    }

    @Override
    public final void onSensorChanged(SensorEvent sensorEvent) {
//updating temperature values
 if (sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            tempText.setText(sensorEvent.values[0] + "°C");
        }
//updating illuminance values
   if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
          illumText.setText(sensorEvent.values[0] + "lx");
       }
//Changing the color
   if(sensorEvent.values[0]  <0){
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }else if(sensorEvent.values[0] >=0 && sensorEvent.values[0] < 20 ){
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        }else if(sensorEvent.values[0] >=20 && sensorEvent.values[0] < 40){
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
        }else if(sensorEvent.values[0] >=40 ){
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {

     }



    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        if(isTempSensorAvailable){
            sensorManager.registerListener(this, tempSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(isIllumSensorAvailable){
            sensorManager.registerListener(this, illumSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onPause() {
        // Unregister the sensor when the activity pauses.
        super.onPause();
        if(isTempSensorAvailable){
            sensorManager.unregisterListener(this);
        }

        if(isIllumSensorAvailable){
            sensorManager.unregisterListener(this);
        }
    }
}