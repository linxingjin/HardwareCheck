package com.lenovo.check.sensor;

import java.text.DecimalFormat;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lenovo.check.R;
import com.lenovo.check.util.ProgressView;

public class LightSensorInformation extends Activity implements SensorEventListener{
	
	private TextView light_sensor_strength;
	private TextView light_sensor_name;
//	private ProgressView light_sensor_strength_progress;
	private ProgressBar light_sensor_strength_progress;
	private TextView light_sensor_company;
	private TextView light_sensor_version;
	private TextView light_sensor_power;
	private TextView light_sensor_max_range;
	private TextView light_sensor_resolution;
	
	private String dataType = "0.0";
	private DecimalFormat df;
	private float h;
	
	private SensorManager sensorManager = null;
	private Sensor lightSensor = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light_senesor);
		
		light_sensor_strength = (TextView)findViewById(R.id.light_sensor_strength);
//		light_sensor_strength_progress = (ProgressView)findViewById(R.id.light_sensor_strength_progress);
		light_sensor_strength_progress = (ProgressBar)findViewById(R.id.light_sensor_strength_progress);
		light_sensor_name = (TextView)findViewById(R.id.light_sensor_name);
		light_sensor_company = (TextView)findViewById(R.id.light_sensor_company);
		light_sensor_version = (TextView)findViewById(R.id.light_sensor_version);
		light_sensor_power = (TextView)findViewById(R.id.light_sensor_power);
		light_sensor_max_range = (TextView)findViewById(R.id.light_sensor_max_range);
		light_sensor_resolution = (TextView)findViewById(R.id.light_sensor_resolution);
		
		light_sensor_strength_progress.setMax(1000);
		df = new DecimalFormat(dataType);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	    lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);  
	    sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
	    
	    light_sensor_name.setText(lightSensor.getName());
	    light_sensor_company.setText(lightSensor.getVendor());
	    light_sensor_version.setText(String.valueOf(lightSensor.getVersion()));
	    light_sensor_power.setText(lightSensor.getPower() + " mA");
	    light_sensor_max_range.setText(lightSensor.getMaximumRange() + "μT");
	    light_sensor_resolution.setText(lightSensor.getResolution() + "μT");
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_LIGHT) {
			//光线感应器只需要values[0]的值，其他两个都为0.
			light_sensor_strength.setText(df.format(event.values[0]) + " luxes");
			Float localFloat1 = Float.valueOf(event.values[0]);
			 WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
		      localLayoutParams.screenBrightness = -1.0F;
		      getWindow().setAttributes(localLayoutParams);
		      Float localFloat2 = Float.valueOf(this.h);
		      light_sensor_strength_progress.setProgress((int)event.values[0]);
//		      light_sensor_strength_progress.drawProgress(localFloat2.intValue(), localFloat1.intValue(), "%");
		}
	}
	
	@Override
	 protected void onPause()
	  {
	    super.onPause();
	    this.sensorManager.unregisterListener(this);
	  }

	@Override
	  protected void onResume()
	  {
	    super.onResume();
	    this.sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
	  }
	
	@Override
	 protected void onStop()
	  {
	    super.onStop();
	    this.sensorManager.unregisterListener(this);
	    WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
	    localLayoutParams.screenBrightness = -1.0F;
	    getWindow().setAttributes(localLayoutParams);
	  }
}
