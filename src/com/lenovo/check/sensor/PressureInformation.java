package com.lenovo.check.sensor;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.lenovo.check.R;

public class PressureInformation extends Activity implements SensorEventListener{
	
	private TextView pressure_value;
    private TextView pressure_name;
    private TextView pressure_company;
    private TextView pressure_sample_rate;
    private TextView pressure_version;
    private TextView pressure_power;
    private TextView pressure_max_range;
    private TextView pressure_resolution;
    private TextView pressure_accuracy;
    private Sensor pressureSensor;
    private SensorManager sensorManager;
    private String dataType = "0.0000";
    private DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pressure);
		
		pressure_value = (TextView)findViewById(R.id.pressure_value);
	    pressure_name = (TextView)findViewById(R.id.pressure_name);
	    pressure_company = (TextView)findViewById(R.id.pressure_company);
	    pressure_sample_rate = (TextView)findViewById(R.id.pressure_sample_rate);
	    pressure_version = (TextView)findViewById(R.id.pressure_version);
	    pressure_power = (TextView)findViewById(R.id.pressure_power);
	    pressure_max_range = (TextView)findViewById(R.id.pressure_max_range);
	    pressure_resolution = (TextView)findViewById(R.id.pressure_resolution);
	    pressure_accuracy = (TextView)findViewById(R.id.pressure_accuracy);
	    
	    df = new DecimalFormat(dataType);
	    sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
	    pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
	    
	    pressure_name.setText(pressureSensor.getName());
		pressure_company.setText(pressureSensor.getVendor());
		pressure_sample_rate.setText("¸ß");
		pressure_version.setText(String.valueOf(pressureSensor.getVersion()));
		pressure_power.setText(String.valueOf(pressureSensor.getPower()) + "mA");
		pressure_max_range.setText(String.valueOf(pressureSensor.getMaximumRange()) + " hPa");
		pressure_resolution.setText(String.valueOf(pressureSensor.getResolution()) + " hPa");
		
		sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_PRESSURE){
			pressure_value.setText(df.format(event.values[0]) + " hPa");
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		super.onStop();
		sensorManager.unregisterListener(this);
	}
}
