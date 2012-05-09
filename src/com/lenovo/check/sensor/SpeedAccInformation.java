package com.lenovo.check.sensor;

import java.text.DecimalFormat;

import com.lenovo.check.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SpeedAccInformation extends Activity implements SensorEventListener{
	
	private TextView speed_acc_x;
	private TextView speed_acc_y;
	private TextView speed_acc_z;
	private TextView speed_acc_accuracy;
	private TextView speed_acc_name;
	private TextView speed_acc_company;
	private TextView speed_acc_sample_rate;
	private TextView speed_acc_version;
	private TextView speed_acc_power;
	private TextView speed_acc_max_range;
	private TextView speed_acc_resolution;
	
	private Sensor speedAccSensor;
	private SensorManager sensorManager;
	private String sampleData = "0.000";
	private DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speed_acc);
		
		speed_acc_x = (TextView)findViewById(R.id.speed_acc_x);
		speed_acc_y = (TextView)findViewById(R.id.speed_acc_y);
		speed_acc_z = (TextView)findViewById(R.id.speed_acc_z);
		speed_acc_accuracy = (TextView)findViewById(R.id.speed_acc_accuracy);
		speed_acc_name = (TextView)findViewById(R.id.speed_acc_name);
		speed_acc_company = (TextView)findViewById(R.id.speed_acc_company);
		speed_acc_sample_rate = (TextView)findViewById(R.id.speed_acc_sample_rate);
		speed_acc_version = (TextView)findViewById(R.id.speed_acc_version);
		speed_acc_power = (TextView)findViewById(R.id.speed_acc_power);
		speed_acc_max_range = (TextView)findViewById(R.id.speed_acc_max_range);
		speed_acc_resolution = (TextView)findViewById(R.id.speed_acc_resolution);
		
		df = new DecimalFormat(sampleData);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		speedAccSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		speed_acc_name.setText(speedAccSensor.getName());
		speed_acc_company.setText(speedAccSensor.getVendor());
		speed_acc_sample_rate.setText("高");
		speed_acc_version.setText(String.valueOf(speedAccSensor.getVersion()));
		speed_acc_power.setText(String.valueOf(speedAccSensor.getPower()) + "mA");
		speed_acc_max_range.setText(String.valueOf(speedAccSensor.getMaximumRange()) + " m/s" + "²");
		speed_acc_resolution.setText(String.valueOf(speedAccSensor.getResolution()) + " m/s" + "²");
		
		sensorManager.registerListener(this, speedAccSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
		    speed_acc_x.setText(df.format(event.values[0]) + " m/s" + "²");
		    speed_acc_y.setText(df.format(event.values[1]) + " m/s" + "²");
		    speed_acc_z.setText(df.format(event.values[2]) + " m/s" + "²");
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
		sensorManager.registerListener(this, speedAccSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	protected void onStop(){
		sensorManager.unregisterListener(this);
		super.onStop();
	}
}
