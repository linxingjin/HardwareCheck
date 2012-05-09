package com.lenovo.check.sensor.gravity;

import java.text.DecimalFormat;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.Bundle;
import android.widget.TextView;

import com.lenovo.check.R;

public class GravityInformation extends Activity implements SensorEventListener, Renderer{

	private TextView gravity_x;
	private TextView gravity_y;
	private TextView gravity_z;
	private TextView gravity_accuracy;
	private TextView gravity_name;
	private TextView gravity_company;
	private TextView gravity_sample_rate;
	private TextView gravity_version;
	private TextView gravity_power;
	private TextView gravity_max_range;
	private TextView gravity_resolution;

	private Sensor gravitySensor;
	private Sensor magneticSensor;
	private SensorManager sensorManager;
	private GLSurfaceView glSurfaceView;
	private String sampleData = "0.000";
	private DecimalFormat df;

	private float[] swapMatrix = new float[16];//转换矩阵，将磁场数据转换进实际的重力坐标中
	private float[] rotatMatrix = new float[16];//旋转矩阵，用来保存磁场和加速度的数据
	private float[] accMatrix = new float[3];//表示从加速度感应器获取来的数据
	private float[] magMatrix = new float[3];//表示从磁场感应器获取来的数据
	private float magAngle = 0.0F;
	private ColorPointer redPointer;
	private ColorPointer greedPointer;
	private ColorPointer bluePointer;
	float[] gravity = new float[3];
    float[] linear_acceleration = new float[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gravity);

		gravity_x = (TextView)findViewById(R.id.gravity_x);
		gravity_y = (TextView)findViewById(R.id.gravity_y);
		gravity_z = (TextView)findViewById(R.id.gravity_z);
		gravity_accuracy = (TextView)findViewById(R.id.gravity_accuracy);
		gravity_name = (TextView)findViewById(R.id.gravity_name);
		gravity_company = (TextView)findViewById(R.id.gravity_company);
		gravity_sample_rate = (TextView)findViewById(R.id.gravity_sample_rate);
		gravity_version = (TextView)findViewById(R.id.gravity_version);
		gravity_power = (TextView)findViewById(R.id.gravity_power);
		gravity_max_range = (TextView)findViewById(R.id.gravity_max_range);
		gravity_resolution = (TextView)findViewById(R.id.gravity_resolution);
		redPointer = new ColorPointer();
		greedPointer = new ColorPointer();
		bluePointer = new ColorPointer();

		df = new DecimalFormat(sampleData);

		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		gravity_name.setText(gravitySensor.getName());
		gravity_company.setText(gravitySensor.getVendor());
		gravity_sample_rate.setText("高");
		gravity_version.setText(String.valueOf(gravitySensor.getVersion()));
		gravity_power.setText(String.valueOf(gravitySensor.getPower()) + "mA");
		gravity_max_range.setText(String.valueOf(gravitySensor.getMaximumRange()) + "m/s" + "²");
		gravity_resolution.setText(String.valueOf(gravitySensor.getResolution()) + "m/s" + "²");

		sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
		glSurfaceView = (GLSurfaceView)findViewById(R.id.gravity_opengl);
		//设置背景透明
		glSurfaceView.setZOrderOnTop(true);
		glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		glSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		glSurfaceView.setRenderer(this);



	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {//精度发生改变时调用
	}

	@Override
	public void onSensorChanged(SensorEvent event) {//方向发生改变时调用
		switch (event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			accMatrix = event.values.clone();

		case Sensor.TYPE_MAGNETIC_FIELD:
			magMatrix = event.values.clone();
			break;
		default:
			break;
		}
		if(accMatrix != null && magMatrix != null){
			SensorManager.getRotationMatrix(rotatMatrix, swapMatrix, accMatrix, magMatrix);
		}

//		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//			final float alpha = 0.8f;
            // Isolate the force of gravity with the low-pass filter.
//            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
//            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
//            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

            // Remove the gravity contribution with the high-pass filter.
//            linear_acceleration[0] = event.values[0] - gravity[0];
//            linear_acceleration[1] = event.values[1] - gravity[1];
//            linear_acceleration[2] = event.values[2] - gravity[2];
//            gravity_x.setText(df.format(linear_acceleration[0]) + " m/s" + "²");
//		    gravity_y.setText(df.format(linear_acceleration[1]) + " m/s" + "²");
//		    gravity_z.setText(df.format(linear_acceleration[2]) + " m/s" + "²");
//			arrayofFloat2 = accMatrix;
//			float x = event.values[0];//横向翻滚
//		    float y = event.values[1];//纵向翻滚
//		    float z = event.values[2];//z>0 屏幕朝上 z<0 屏幕朝下
		    gravity_x.setText(df.format(accMatrix[0]) + " m/s" + "²");
		    gravity_y.setText(df.format(accMatrix[1]) + " m/s" + "²");
		    gravity_z.setText(df.format(accMatrix[2]) + " m/s" + "²");
//		}
	}

	@Override
	public void onDrawFrame(GL10 gl) {
//		gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);//设置当前色
		gl.glLoadIdentity();
		gl.glTranslatef(0.0F, 0.0F, -5.0F);//移入屏幕5.0
		gl.glMultMatrixf(rotatMatrix,0);
		gl.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
		this.redPointer.drawPointer(gl);
		gl.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
	    gl.glLoadIdentity();
	    gl.glTranslatef(0.0F, 0.0F, -5.0F);
	    gl.glMultMatrixf(rotatMatrix, 0);
	    gl.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
	    this.greedPointer.drawPointer(gl);
	    gl.glColor4f(0.0F, 0.0F, 1.0F, 1.0F);
	    gl.glLoadIdentity();
	    gl.glTranslatef(0.0F, 0.0F, -5.0F);
	    gl.glMultMatrixf(rotatMatrix, 0);
	    gl.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
	    this.bluePointer.drawPointer(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
	    gl.glMatrixMode(GL10.GL_PROJECTION);
	    gl.glLoadIdentity();
	    GLU.gluPerspective(gl, 25.0F, width / height, 0.1F, 100.0F);
	    gl.glMatrixMode(GL10.GL_MODELVIEW);
	    gl.glLoadIdentity();//重置投影矩阵
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glShadeModel(GL10.GL_SMOOTH);//启用阴影平滑
		gl.glEnable(GL10.GL_BLEND);
//		gl.glClearColor(1.0F, 1.0F, 1.0F, 0F);//清除背景时所用颜色,不需要，为了设置背景透明
		gl.glEnable(GL10.GL_DEPTH_TEST);
//		gl.glDisable(GL10.GL_DEPTH_TEST);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);//告诉系统对透视进行修正
	}

	@Override
	protected void onPause() {
		super.onPause();
		glSurfaceView.onPause();
		sensorManager.unregisterListener(this);
	}
	@Override
	protected void onResume() {
		super.onResume();
		glSurfaceView.onResume();
		sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	@Override
	protected void onStop(){
		sensorManager.unregisterListener(this);
		super.onStop();
	}
}
