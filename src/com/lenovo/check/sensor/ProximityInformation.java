package com.lenovo.check.sensor;

import java.text.DecimalFormat;

import com.lenovo.check.R;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.TextView;

public class ProximityInformation extends Activity implements SensorEventListener{
	
	private TextView proximity_value;
	private TextView proximity_accuracy;
	private TextView proximity_name;
	private TextView proximity_company;
	private TextView proximity_sample_rate;
	private TextView proximity_version;
	private TextView proximity_power;
	private TextView proximity_max_range;
	private TextView proximity_resolution;
	
	private Sensor proximitySensor;
	private SensorManager sensorManager;
	private String sampleData = "0.000";
	private DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.proximity);
		
		proximity_value = (TextView)findViewById(R.id.proximity_value);
		proximity_accuracy = (TextView)findViewById(R.id.proximity_accuracy);
		proximity_name = (TextView)findViewById(R.id.proximity_name);
		proximity_company = (TextView)findViewById(R.id.proximity_company);
		proximity_sample_rate = (TextView)findViewById(R.id.proximity_sample_rate);
		proximity_version = (TextView)findViewById(R.id.proximity_version);
		proximity_power = (TextView)findViewById(R.id.proximity_power);
		proximity_max_range = (TextView)findViewById(R.id.proximity_max_range);
		proximity_resolution = (TextView)findViewById(R.id.proximity_resolution);
		
		df = new DecimalFormat(sampleData);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		
		proximity_name.setText(proximitySensor.getName());
		proximity_company.setText(proximitySensor.getVendor());
	//	proximity_sample_rate.setText("¸ß");
	    proximity_version.setText(String.valueOf(proximitySensor.getVersion()));
		proximity_power.setText(String.valueOf(proximitySensor.getPower()) + "mA");
		proximity_max_range.setText(String.valueOf(proximitySensor.getMaximumRange()) + "cm");
		proximity_resolution.setText(String.valueOf(proximitySensor.getResolution()) + "cm");
		
		sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
			proximity_value.setText(df.format(event.values[0]) + " cm");
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
		sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		super.onStop();
		sensorManager.unregisterListener(this);
	}
}
