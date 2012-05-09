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

public class RotationInformation extends Activity implements SensorEventListener{
	
	private TextView rotation_x;
	private TextView rotation_y;
	private TextView rotation_z;
	private TextView rotation_accuracy;
	private TextView rotation_name;
	private TextView rotation_company;
	private TextView rotation_sample_rate;
	private TextView rotation_version;
	private TextView rotation_power;
	private TextView rotation_max_range;
	private TextView rotation_resolution;
	
	private Sensor rotationSensor;
	private SensorManager sensorManager;
	private String sampleData = "0.0";
	private DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rotation);
		
		rotation_x = (TextView)findViewById(R.id.rotation_x);
		rotation_y = (TextView)findViewById(R.id.rotation_y);
		rotation_z = (TextView)findViewById(R.id.rotation_z);
		rotation_accuracy = (TextView)findViewById(R.id.rotation_accuracy);
		rotation_name = (TextView)findViewById(R.id.rotation_name);
		rotation_company = (TextView)findViewById(R.id.rotation_company);
		rotation_sample_rate = (TextView)findViewById(R.id.rotation_sample_rate);
		rotation_version = (TextView)findViewById(R.id.rotation_version);
		rotation_power = (TextView)findViewById(R.id.rotation_power);
		rotation_max_range = (TextView)findViewById(R.id.rotation_max_range);
		rotation_resolution = (TextView)findViewById(R.id.rotation_resolution);
		
		df = new DecimalFormat(sampleData);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
		
		rotation_name.setText(rotationSensor.getName());
		rotation_company.setText(rotationSensor.getVendor());
		rotation_sample_rate.setText("╕▀");
		rotation_version.setText(String.valueOf(rotationSensor.getVersion()));
		rotation_power.setText(String.valueOf(rotationSensor.getPower()) + "mA");
		rotation_max_range.setText(String.valueOf(rotationSensor.getMaximumRange()) + "бу");
		rotation_resolution.setText(String.valueOf(rotationSensor.getResolution()) + "бу");
		
		sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
		    rotation_x.setText(df.format(event.values[0]) + "бу");
		    rotation_y.setText(df.format(event.values[1]) + "бу");
		    rotation_z.setText(df.format(event.values[2]) + "бу");
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
		sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	protected void onStop(){
		sensorManager.unregisterListener(this);
		super.onStop();
	}
}
