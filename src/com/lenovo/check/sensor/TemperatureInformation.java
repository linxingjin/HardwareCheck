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

public class TemperatureInformation extends Activity implements SensorEventListener{
	
	private TextView temperature_value;
	private TextView temperature_accuracy;
	private TextView temperature_name;
	private TextView temperature_company;
	private TextView temperature_sample_rate;
	private TextView temperature_version;
	private TextView temperature_power;
	private TextView temperature_max_range;
	private TextView temperature_resolution;
	
	private Sensor temperatureSensor;
	private SensorManager sensorManager;
	private String sampleData = "0.0";
	private DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temperature);
		
		temperature_value = (TextView)findViewById(R.id.temperature_value);
		temperature_accuracy = (TextView)findViewById(R.id.temperature_accuracy);
		temperature_name = (TextView)findViewById(R.id.temperature_name);
		temperature_company = (TextView)findViewById(R.id.temperature_company);
		temperature_sample_rate = (TextView)findViewById(R.id.temperature_sample_rate);
		temperature_version = (TextView)findViewById(R.id.temperature_version);
		temperature_power = (TextView)findViewById(R.id.temperature_power);
		temperature_max_range = (TextView)findViewById(R.id.temperature_max_range);
		temperature_resolution = (TextView)findViewById(R.id.temperature_resolution);
		
		df = new DecimalFormat(sampleData);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
		
		temperature_name.setText(temperatureSensor.getName());
		temperature_company.setText(temperatureSensor.getVendor());
		temperature_sample_rate.setText("高");//怎么判断采样的高低 d
		temperature_version.setText(String.valueOf(temperatureSensor.getVersion()));
		temperature_power.setText(String.valueOf(temperatureSensor.getPower()) + "mA");
		temperature_max_range.setText(String.valueOf(temperatureSensor.getMaximumRange()) + " °C");
		temperature_resolution.setText(String.valueOf(temperatureSensor.getResolution()) + " °C");
		
		sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_TEMPERATURE){
			temperature_value.setText(df.format(event.values[0]) + " °C");
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
		sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		super.onStop();
		sensorManager.unregisterListener(this);
	}
}
