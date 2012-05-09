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

public class OrientationInformation extends Activity implements SensorEventListener{
	
	private TextView orientation_x;
	private TextView orientation_y;
	private TextView orientation_z;
	private TextView orientation_accuracy;
	private TextView orientation_name;
	private TextView orientation_company;
	private TextView orientation_sample_rate;
	private TextView orientation_version;
	private TextView orientation_power;
	private TextView orientation_max_range;
	private TextView orientation_resolution;
	
	private Sensor orientationSensor;
	private SensorManager sensorManager;
	private String sampleData = "0.0";
	private DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orientation);
		
		orientation_x = (TextView)findViewById(R.id.orientation_x);
		orientation_y = (TextView)findViewById(R.id.orientation_y);
		orientation_z = (TextView)findViewById(R.id.orientation_z);
		orientation_accuracy = (TextView)findViewById(R.id.orientation_accuracy);
		orientation_name = (TextView)findViewById(R.id.orientation_name);
		orientation_company = (TextView)findViewById(R.id.orientation_company);
		orientation_sample_rate = (TextView)findViewById(R.id.orientation_sample_rate);
		orientation_version = (TextView)findViewById(R.id.orientation_version);
		orientation_power = (TextView)findViewById(R.id.orientation_power);
		orientation_max_range = (TextView)findViewById(R.id.orientation_max_range);
		orientation_resolution = (TextView)findViewById(R.id.orientation_resolution);
		
		df = new DecimalFormat(sampleData);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		
		orientation_name.setText(orientationSensor.getName());
		orientation_company.setText(orientationSensor.getVendor());
		orientation_sample_rate.setText("╕▀");
		orientation_version.setText(String.valueOf(orientationSensor.getVersion()));
		orientation_power.setText(String.valueOf(orientationSensor.getPower()) + "mA");
		orientation_max_range.setText(String.valueOf(orientationSensor.getMaximumRange()) + "бу");
		orientation_resolution.setText(String.valueOf(orientationSensor.getResolution()) + "бу");
		
		sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
		    orientation_x.setText(df.format(event.values[0]) + "бу");
		    orientation_y.setText(df.format(event.values[1]) + "бу");
		    orientation_z.setText(df.format(event.values[2]) + "бу");
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
		sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	protected void onStop(){
		sensorManager.unregisterListener(this);
		super.onStop();
	}
}
