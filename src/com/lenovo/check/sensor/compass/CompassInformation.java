package com.lenovo.check.sensor.compass;

import java.text.DecimalFormat;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.TextView;

import com.lenovo.check.R;

public class CompassInformation extends Activity implements SensorEventListener{
	private TextView compass_x;
	private TextView compass_y;  
	private TextView compass_z;  
	private TextView compass_accuracy;
	private TextView compass_name;
	private TextView compass_company;
	private TextView compass_sample_rate;
	private TextView compass_version;
	private TextView compass_power;
	private TextView compass_max_range;
	private TextView compass_resolution;

	private String sampleData = "0.000";
	private DecimalFormat df;
	private GLSurfaceView glSurfaceView;
	private CompassView compassView;
	private SensorManager sensorManager = null;
	private Sensor magneticSensor = null;
	private Sensor accSensor = null;
	private float[] mValues = new float[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compass);

		compass_x = (TextView)findViewById(R.id.compass_x);
		compass_y = (TextView)findViewById(R.id.compass_y);
		compass_z = (TextView)findViewById(R.id.compass_z);
		compass_accuracy = (TextView)findViewById(R.id.compass_accuracy);
		compass_name = (TextView)findViewById(R.id.compass_name);
		compass_company = (TextView)findViewById(R.id.compass_company);
		compass_version = (TextView)findViewById(R.id.compass_version);
		compass_power = (TextView)findViewById(R.id.compass_power);
		compass_max_range = (TextView)findViewById(R.id.compass_max_range);
		compass_resolution = (TextView)findViewById(R.id.compass_resolution);
		compassView = (CompassView)findViewById(R.id.compass_view);

		df = new DecimalFormat(sampleData);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	    magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
	    accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //加速感应器

	    compass_name.setText(magneticSensor.getName());
	    compass_company.setText(magneticSensor.getVendor());
	    compass_version.setText(String.valueOf(magneticSensor.getVersion()));
	    compass_power.setText(magneticSensor.getPower() + " mA");
	    compass_max_range.setText(magneticSensor.getMaximumRange() + "μT");
	    compass_resolution.setText("±" + magneticSensor.getResolution() + "μT");

	    sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
	    this.sensorManager.registerListener(this, accSensor,SensorManager.SENSOR_DELAY_NORMAL);
//	    glSurfaceView = (GLSurfaceView)findViewById(R.id.compass_opengl);
//		glSurfaceView.setRenderer(this);


	}

//	@Override
//	public void onDrawFrame(GL10 gl) {
//		gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
//	    gl.glClear(16640);
//	    gl.glLoadIdentity();
//	    gl.glTranslatef(0.0F, 0.0F, -5.0F);
//	    this.compassPlate.drawCompassPlate(gl);
//	    gl.glLoadIdentity();
//	    gl.glTranslatef(0.0F, 0.0F, -4.0F);
//	    gl.glScalef(0.7F, 0.7F, 0.7F);
//	    gl.glMultMatrixf(this.t, 0);
//	    this.compassPointer.drawCompassPointer(gl);
//	}
//
//	@Override
//	public void onSurfaceChanged(GL10 gl, int width, int height) {
//		gl.glViewport(0, 0, width, height);
//	    gl.glMatrixMode(5889);
//	    gl.glLoadIdentity();
//	    GLU.gluPerspective(gl, 25.0F, width / height, 0.1F, 100.0F);
//	    gl.glMatrixMode(5888);
//	    gl.glLoadIdentity();
//	}
//
//	@Override
//	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//		GLES10.glGetString(7939);
//	    gl.glDisable(3024);
//	    gl.glHint(3152, 4353);
//	    gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
//	    gl.glEnable(2884);
//	    gl.glShadeModel(7425);
//	    gl.glEnable(2929);
//	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
			compass_x.setText(df.format(event.values[0]) + " μT");
		    compass_y.setText(df.format(event.values[1]) + " μT");
		    compass_z.setText(df.format(event.values[2]) + " μT");
		    mValues[0] = event.values[0];
		    mValues[1] = event.values[1];
		    mValues[2] = event.values[2];
		}
		compassView.setValue(mValues);
		if(compassView != null){
			compassView.invalidate();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
//		this.glSurfaceView.onPause();
		this.sensorManager.unregisterListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
//		this.glSurfaceView.onResume();
		this.sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
		this.sensorManager.registerListener(this, accSensor,SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		super.onStop();
		this.sensorManager.unregisterListener(this);
	}
}
