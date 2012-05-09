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

public class GyroscopeInformation extends Activity implements SensorEventListener{
	
	private TextView gyroscope_x;
	private TextView gyroscope_y;
	private TextView gyroscope_z;
	private TextView gyroscope_accuracy;
	private TextView gyroscope_name;
	private TextView gyroscope_company;
	private TextView gyroscope_sample_rate;
	private TextView gyroscope_version;
	private TextView gyroscope_power;
	private TextView gyroscope_max_range;
	private TextView gyroscope_resolution;
	
	private Sensor gyroscopeSensor;
	private SensorManager sensorManager;
	private String sampleData = "0.0";
	private DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gyroscope);
		
		gyroscope_x = (TextView)findViewById(R.id.gyroscope_x);
		gyroscope_y = (TextView)findViewById(R.id.gyroscope_y);
		gyroscope_z = (TextView)findViewById(R.id.gyroscope_z);
		gyroscope_accuracy = (TextView)findViewById(R.id.gyroscope_accuracy);
		gyroscope_name = (TextView)findViewById(R.id.gyroscope_name);
		gyroscope_company = (TextView)findViewById(R.id.gyroscope_company);
		gyroscope_sample_rate = (TextView)findViewById(R.id.gyroscope_sample_rate);
		gyroscope_version = (TextView)findViewById(R.id.gyroscope_version);
		gyroscope_power = (TextView)findViewById(R.id.gyroscope_power);
		gyroscope_max_range = (TextView)findViewById(R.id.gyroscope_max_range);
		gyroscope_resolution = (TextView)findViewById(R.id.gyroscope_resolution);
		
		df = new DecimalFormat(sampleData);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		
		gyroscope_name.setText(gyroscopeSensor.getName());
		gyroscope_company.setText(gyroscopeSensor.getVendor());
		gyroscope_sample_rate.setText("╕▀");
		gyroscope_version.setText(String.valueOf(gyroscopeSensor.getVersion()));
		gyroscope_power.setText(String.valueOf(gyroscopeSensor.getPower()) + "mA");
		gyroscope_max_range.setText(String.valueOf(gyroscopeSensor.getMaximumRange()) + "бу");
		gyroscope_resolution.setText(String.valueOf(gyroscopeSensor.getResolution()) + "бу");
		
		sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
		    gyroscope_x.setText(df.format(event.values[0]) + "бу");
		    gyroscope_y.setText(df.format(event.values[1]) + "бу");
		    gyroscope_z.setText(df.format(event.values[2]) + "бу");
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
		sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	protected void onStop(){
		sensorManager.unregisterListener(this);
		super.onStop();
	}

}
