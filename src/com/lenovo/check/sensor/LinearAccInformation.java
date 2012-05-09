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

public class LinearAccInformation extends Activity implements SensorEventListener{
	
	private TextView linear_acc_x;
	private TextView linear_acc_y;
	private TextView linear_acc_z;
	private TextView linear_acc_accuracy;
	private TextView linear_acc_name;
	private TextView linear_acc_company;
	private TextView linear_acc_sample_rate;
	private TextView linear_acc_version;
	private TextView linear_acc_power;
	private TextView linear_acc_max_range;
	private TextView linear_acc_resolution;
	
	private Sensor linearAccSensor;
	private SensorManager sensorManager;
	private String sampleData = "0.000";
	private DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linear_acc);
		
		linear_acc_x = (TextView)findViewById(R.id.linear_acc_x);
		linear_acc_y = (TextView)findViewById(R.id.linear_acc_y);
		linear_acc_z = (TextView)findViewById(R.id.linear_acc_z);
		linear_acc_accuracy = (TextView)findViewById(R.id.linear_acc_accuracy);
		linear_acc_name = (TextView)findViewById(R.id.linear_acc_name);
		linear_acc_company = (TextView)findViewById(R.id.linear_acc_company);
		linear_acc_sample_rate = (TextView)findViewById(R.id.linear_acc_sample_rate);
		linear_acc_version = (TextView)findViewById(R.id.linear_acc_version);
		linear_acc_power = (TextView)findViewById(R.id.linear_acc_power);
		linear_acc_max_range = (TextView)findViewById(R.id.linear_acc_max_range);
		linear_acc_resolution = (TextView)findViewById(R.id.linear_acc_resolution);
		
		df = new DecimalFormat(sampleData);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		linearAccSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		
		linear_acc_name.setText(linearAccSensor.getName());
		linear_acc_company.setText(linearAccSensor.getVendor());
		linear_acc_sample_rate.setText("高");
		linear_acc_version.setText(String.valueOf(linearAccSensor.getVersion()));
		linear_acc_power.setText(String.valueOf(linearAccSensor.getPower()) + "mA");
		linear_acc_max_range.setText(String.valueOf(linearAccSensor.getMaximumRange()) + " m/s" + "²");
		linear_acc_resolution.setText(String.valueOf(linearAccSensor.getResolution()) + " m/s" + "²");
		
		sensorManager.registerListener(this, linearAccSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
		    linear_acc_x.setText(df.format(event.values[0]) + "m/s^2");
		    linear_acc_y.setText(df.format(event.values[1]) + "m/s^2");
		    linear_acc_z.setText(df.format(event.values[2]) + "m/s^2");
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
		sensorManager.registerListener(this, linearAccSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	protected void onStop(){
		sensorManager.unregisterListener(this);
		super.onStop();
	}
}
