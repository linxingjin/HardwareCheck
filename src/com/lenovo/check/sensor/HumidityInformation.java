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

public class HumidityInformation extends Activity implements SensorEventListener{
	
	private TextView humidity_value;
	private TextView humidity_accuracy;
	private TextView humidity_name;
	private TextView humidity_company;
	private TextView humidity_sample_rate;
	private TextView humidity_version;
	private TextView humidity_power;
	private TextView humidity_max_range;
	private TextView humidity_resolution;
	
	private Sensor humiditySensor;
	private SensorManager sensorManager;
	private String sampleData = "0.00";
	private DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.humidity);
		
		humidity_value = (TextView)findViewById(R.id.humidity_value);
		humidity_accuracy = (TextView)findViewById(R.id.humidity_accuracy);
		humidity_name = (TextView)findViewById(R.id.humidity_name);
		humidity_company = (TextView)findViewById(R.id.humidity_company);
		humidity_sample_rate = (TextView)findViewById(R.id.humidity_sample_rate);
		humidity_version = (TextView)findViewById(R.id.humidity_version);
		humidity_power = (TextView)findViewById(R.id.humidity_power);
		humidity_max_range = (TextView)findViewById(R.id.humidity_max_range);
		humidity_resolution = (TextView)findViewById(R.id.humidity_resolution);
		
		df = new DecimalFormat(sampleData);
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
	//	humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
		
		humidity_name.setText(humiditySensor.getName());
		humidity_company.setText(humiditySensor.getVendor());
		humidity_sample_rate.setText("高");//怎么判断采样的高低 d
		humidity_version.setText(String.valueOf(humiditySensor.getVersion()));
		humidity_power.setText(String.valueOf(humiditySensor.getPower()) + "mA");
		humidity_max_range.setText(String.valueOf(humiditySensor.getMaximumRange()) + " %");
		humidity_resolution.setText(String.valueOf(humiditySensor.getResolution()) + " %");
		
		sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		/*if(event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
			humidity_value.setText(df.format(event.values[0]) + " %");
		}*/
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		super.onStop();
		sensorManager.unregisterListener(this);
	}
}
