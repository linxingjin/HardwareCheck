package com.lenovo.check.ui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lenovo.check.R;
import com.lenovo.check.altavoz.AltavozInformation;
import com.lenovo.check.battery.BatteryInformation;
import com.lenovo.check.bluetooth.BluetoothInformation;
import com.lenovo.check.camera.BackCameraInformation;
import com.lenovo.check.camera.FrontCameraInformation;
import com.lenovo.check.cpu.CPUInformation;
import com.lenovo.check.flash.FlashInformation;
import com.lenovo.check.gsm.GSMInformation;
import com.lenovo.check.memory.MemoryInformation;
import com.lenovo.check.microphone.MicrophoneInformation;
import com.lenovo.check.os.OSInformation;
import com.lenovo.check.screen.DisplayInformation;
import com.lenovo.check.sensor.GyroscopeInformation;
import com.lenovo.check.sensor.LightSensorInformation;
import com.lenovo.check.sensor.LinearAccInformation;
import com.lenovo.check.sensor.OrientationInformation;
import com.lenovo.check.sensor.PressureInformation;
import com.lenovo.check.sensor.ProximityInformation;
import com.lenovo.check.sensor.RotationInformation;
import com.lenovo.check.sensor.SpeedAccInformation;
import com.lenovo.check.sensor.TemperatureInformation;
import com.lenovo.check.sensor.compass.CompassInformation;
import com.lenovo.check.sensor.gravity.GravityInformation;
import com.lenovo.check.vibrate.VibratorInformation;
import com.lenovo.check.wifi.WifiInformation;

public class SystemVerification extends Activity implements View.OnClickListener, OnViewChangeListener{
	/**
	 * 共30个
	 */
	private Button btn_wifi;//无线
	private Button btn_bluetooth;//蓝牙
	private Button btn_gsm;//通信网络模式（GPRS等）
	private Button btn_back_camera;//后置摄像头
	private Button btn_front_camera;//前置摄像头
	private Button btn_screen;//屏幕坏点
	private Button btn_flash;//是否支持flashlight
	private Button btn_battery;//电池
	private Button btn_cpu;//cpu信息
	private Button btn_memory; //内存信息  
	private Button btn_altavoz;//聲音
	private Button btn_vibrator;//振动器
	private Button btn_microphone;//话筒
	private Button btn_android_os;//系统信息
	private Button btn_light_sensor;//光线传感器
	private Button btn_gravity;//重力传感器
	private Button btn_orientation;//方向传感器
	private Button btn_speed_accelerate;//加速度传感器
	private Button btn_gyroscope;//陀螺仪传感器
	private Button btn_rotation;//旋转矢量传感器
	private Button btn_linear_accelerate;//线性加速传感器
	private Button btn_compass;//指南针     
	private Button btn_pressure;//压力传感器
	private Button btn_temperature;//温度传感器
	private Button btn_proximity;//距离传感器  
	
	private ImageView icon_wifi_exist;
	private ImageView icon_bluetooth_exist;
	private ImageView icon_gsm_exist;
	private ImageView icon_back_camera_exist;
	private ImageView icon_front_camera_exist;
	private ImageView icon_screen_exist;
	private ImageView icon_flash_exist;
	private ImageView icon_battery_exist;
	private ImageView icon_cpu_exist;
	private ImageView icon_memory_exist;
	private ImageView icon_altavoz_exist;
	private ImageView icon_vibrador_exist;
	private ImageView icon_microphone_exist;
	private ImageView icon_android_os_exist;
	private ImageView icon_light_sensor_exist;
	private ImageView icon_gravity_exist;
	private ImageView icon_orientation_exist;
	private ImageView icon_speed_accelerate_exist;
	private ImageView icon_gyroscope_exist;
	private ImageView icon_rotation_exist;
	private ImageView icon_linear_accelerate_exist;
	private ImageView icon_compass_exist;
	private ImageView icon_pressure_exist;
	private ImageView icon_temperature_exist;
	private ImageView icon_proximity_exist;
	
	private Camera camera;
	
	private MyViewGroup viewGroup;
	private ImageView[] mImageViews;	
	private int mViewCount;	
	private int mCurSel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
      //  viewGroup = (MyViewGroup) findViewById(R.id.ScrollLayout) ;    
        
        btn_wifi = (Button)findViewById(R.id.btn_wifi);
        btn_bluetooth = (Button)findViewById(R.id.btn_bluetooth);
        btn_gsm = (Button)findViewById(R.id.btn_gsm);
        btn_back_camera = (Button)findViewById(R.id.btn_back_camera);
        btn_front_camera = (Button)findViewById(R.id.btn_front_camera);
        btn_screen = (Button)findViewById(R.id.btn_screen);
        btn_flash = (Button)findViewById(R.id.btn_flash);
        btn_battery = (Button)findViewById(R.id.btn_battery);
        btn_cpu = (Button)findViewById(R.id.btn_cpu);
        btn_memory = (Button) findViewById(R.id.btn_memory);
        btn_altavoz = (Button)findViewById(R.id.btn_altavoz);
        btn_vibrator = (Button)findViewById(R.id.btn_vibrator);
        btn_microphone = (Button)findViewById(R.id.btn_microphone);
        btn_android_os = (Button)findViewById(R.id.btn_android_os);
        btn_light_sensor = (Button)findViewById(R.id.btn_light_sensor);
        btn_gravity = (Button)findViewById(R.id.btn_gravity);
        btn_orientation = (Button)findViewById(R.id.btn_orientation);
        btn_speed_accelerate = (Button)findViewById(R.id.btn_speed_accelerate);
        btn_gyroscope = (Button)findViewById(R.id.btn_gyroscope);
        btn_rotation = (Button)findViewById(R.id.btn_rotation);
        btn_linear_accelerate = (Button)findViewById(R.id.btn_linear_accelerate);
        btn_compass = (Button)findViewById(R.id.btn_compass);
        btn_pressure = (Button)findViewById(R.id.btn_pressure);
        btn_temperature = (Button)findViewById(R.id.btn_temperature);
        btn_proximity = (Button)findViewById(R.id.btn_proximity);
        
        btn_wifi.setOnClickListener(this);
        btn_bluetooth.setOnClickListener(this);
        btn_gsm.setOnClickListener(this);
        btn_back_camera.setOnClickListener(this);
        btn_front_camera.setOnClickListener(this);
        btn_screen.setOnClickListener(this);
        btn_flash.setOnClickListener(this);
        btn_battery.setOnClickListener(this);
        btn_cpu.setOnClickListener(this);
        btn_memory.setOnClickListener(this);
        btn_altavoz.setOnClickListener(this);
        btn_vibrator.setOnClickListener(this);
        btn_microphone.setOnClickListener(this);
        btn_android_os.setOnClickListener(this);
        btn_light_sensor.setOnClickListener(this);
        btn_gravity.setOnClickListener(this);
        btn_orientation.setOnClickListener(this);
        btn_speed_accelerate.setOnClickListener(this);
        btn_gyroscope.setOnClickListener(this);
        btn_rotation.setOnClickListener(this);
        btn_linear_accelerate.setOnClickListener(this);
        btn_compass.setOnClickListener(this);
        btn_pressure.setOnClickListener(this);
        btn_temperature.setOnClickListener(this);
        btn_proximity.setOnClickListener(this);
        
        icon_wifi_exist = (ImageView)findViewById(R.id.icon_wifi_exist);
    	icon_bluetooth_exist = (ImageView)findViewById(R.id.icon_bluetooth_exist);
    	icon_gsm_exist = (ImageView)findViewById(R.id.icon_gsm_exist);
    	icon_back_camera_exist = (ImageView)findViewById(R.id.icon_back_camera_exist);
    	icon_front_camera_exist = (ImageView)findViewById(R.id.icon_front_camera_exist);
    	icon_screen_exist = (ImageView)findViewById(R.id.icon_screen_exist);
    	icon_flash_exist = (ImageView)findViewById(R.id.icon_flash_exist);
    	icon_battery_exist = (ImageView)findViewById(R.id.icon_battery_exist);
    	icon_cpu_exist = (ImageView)findViewById(R.id.icon_cpu_exist);
    	icon_memory_exist = (ImageView)findViewById(R.id.icon_memory_exist);
    	icon_altavoz_exist = (ImageView)findViewById(R.id.icon_altavoz_exist);
    	icon_vibrador_exist = (ImageView)findViewById(R.id.icon_vibrator_exist);
    	icon_microphone_exist = (ImageView)findViewById(R.id.icon_microphone_exist);
    	icon_android_os_exist = (ImageView)findViewById(R.id.icon_android_os_exist);
    	icon_light_sensor_exist = (ImageView)findViewById(R.id.icon_light_sensor_exist);
    	icon_gravity_exist = (ImageView)findViewById(R.id.icon_gravity_exist);
    	icon_orientation_exist = (ImageView)findViewById(R.id.icon_orientation_exist);
    	icon_speed_accelerate_exist = (ImageView)findViewById(R.id.icon_speed_accelerate_exist);
    	icon_gyroscope_exist = (ImageView)findViewById(R.id.icon_gyroscope_exist);
    	icon_rotation_exist = (ImageView)findViewById(R.id.icon_rotation_exist);
    	icon_linear_accelerate_exist = (ImageView)findViewById(R.id.icon_linear_accelerate_exist);
    	icon_compass_exist = (ImageView)findViewById(R.id.icon_compass_exist);
    	icon_pressure_exist = (ImageView)findViewById(R.id.icon_pressure_exist);
    	icon_temperature_exist = (ImageView)findViewById(R.id.icon_temperature_exist);
    	icon_proximity_exist = (ImageView)findViewById(R.id.icon_proximity_exist);
    	
        init();
    	this.getExistSystemHardwares();
    }
    
    @Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_wifi:
			Intent wifiIntent = new Intent(SystemVerification.this, WifiInformation.class);
			startActivity(wifiIntent);
			break;
		case R.id.btn_bluetooth:
			Intent bluetoothIntent = new Intent(SystemVerification.this, BluetoothInformation.class);
			startActivity(bluetoothIntent);
			break;
		case R.id.btn_gsm:
			Intent gsmIntent = new Intent(SystemVerification.this, GSMInformation.class);
			startActivity(gsmIntent);
			break;
		case R.id.btn_back_camera:
			Intent bcameraIntent = new Intent(SystemVerification.this, BackCameraInformation.class);
			startActivity(bcameraIntent);
			break;
		case R.id.btn_front_camera:
			Intent fcameraIntent = new Intent(SystemVerification.this, FrontCameraInformation.class);
			startActivity(fcameraIntent);
			break;
		case R.id.btn_screen:
			Intent screenIntent = new Intent(SystemVerification.this, DisplayInformation.class);
			startActivity(screenIntent);
			break;
		case R.id.btn_flash:
			Intent flashIntent = new Intent(SystemVerification.this, FlashInformation.class);
			startActivity(flashIntent);
			break;
		case R.id.btn_battery:
			Intent batteryIntent = new Intent(SystemVerification.this, BatteryInformation.class);
			startActivity(batteryIntent);
			break;
		case R.id.btn_cpu:
			Intent cpuIntent = new Intent(SystemVerification.this, CPUInformation.class);
			startActivity(cpuIntent);
			break;
		case R.id.btn_memory:
			Intent memoryIntent = new Intent(SystemVerification.this, MemoryInformation.class);
			startActivity(memoryIntent);
			break;
		case R.id.btn_altavoz:
			Intent altavozIntent = new Intent(SystemVerification.this, AltavozInformation.class);
			startActivity(altavozIntent);
			break;
		case R.id.btn_vibrator:
			Intent vibratorIntent = new Intent(SystemVerification.this, VibratorInformation.class);
			startActivity(vibratorIntent);
			break;
		case R.id.btn_microphone:
			Intent microphoneIntent = new Intent(SystemVerification.this, MicrophoneInformation.class);
			startActivity(microphoneIntent);
			break;
		case R.id.btn_android_os:
			Intent osIntent = new Intent(SystemVerification.this, OSInformation.class);
			startActivity(osIntent);
			break;
		case R.id.btn_light_sensor:
			Intent lightIntent = new Intent(SystemVerification.this, LightSensorInformation.class);
			startActivity(lightIntent);
			break;
		case R.id.btn_gravity:
			Intent gravityIntent = new Intent(SystemVerification.this, GravityInformation.class);
			startActivity(gravityIntent);
			break;
		case R.id.btn_orientation:
			Intent orientateIntent = new Intent(SystemVerification.this, OrientationInformation.class);
			startActivity(orientateIntent);
			break;
		case R.id.btn_speed_accelerate:
			Intent speedAccIntent = new Intent(SystemVerification.this, SpeedAccInformation.class);
			startActivity(speedAccIntent);
			break;
		case R.id.btn_gyroscope:
			Intent gyroscopeIntent = new Intent(SystemVerification.this, GyroscopeInformation.class);
			startActivity(gyroscopeIntent);
			break;
		case R.id.btn_rotation:
			Intent rotationIntent = new Intent(SystemVerification.this, RotationInformation.class);
			startActivity(rotationIntent);  
			break;
		case R.id.btn_linear_accelerate:
			Intent linearAccIntent = new Intent(SystemVerification.this, LinearAccInformation.class);
			startActivity(linearAccIntent);
			break;
		case R.id.btn_compass:
			Intent compassIntent = new Intent(SystemVerification.this, CompassInformation.class);
			startActivity(compassIntent);
			break;
		case R.id.btn_pressure:
			Intent pressureIntent = new Intent(SystemVerification.this, PressureInformation.class);
			startActivity(pressureIntent);
			break;
		case R.id.btn_temperature:
			Intent temperatureIntent = new Intent(SystemVerification.this, TemperatureInformation.class);
			startActivity(temperatureIntent);
			break;
		case R.id.btn_proximity:
			Intent proximityIntent = new Intent(SystemVerification.this, ProximityInformation.class);
			startActivity(proximityIntent);
			break;
		default:
			int pos = (Integer)(v.getTag());
			setCurPoint(pos);
			viewGroup.snapToScreen(pos);
			break;
		}
	}
    
    public void getExistSystemHardwares(){
    	WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    	if(wifiManager != null){
    		icon_wifi_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_wifi_exist.setImageResource(R.drawable.error);
    		btn_wifi.setClickable(false);
    	}
    	
    	BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    	if(bluetoothAdapter != null){
    		icon_bluetooth_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_bluetooth_exist.setImageResource(R.drawable.error);
    		btn_bluetooth.setClickable(false);
    	}
    	
    	TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
    	if(telephonyManager != null){
    		icon_gsm_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_gsm_exist.setImageResource(R.drawable.error);
    		btn_gsm.setClickable(false);
    	}
    	
    	int cameraCount = Camera.getNumberOfCameras();
    	switch (cameraCount) {
		case 0:
			icon_back_camera_exist.setImageResource(R.drawable.error);
    		icon_front_camera_exist.setImageResource(R.drawable.error);
    		btn_front_camera.setClickable(false);
    		btn_back_camera.setClickable(false);
			break;
		case 1:
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(0, info);
			if(info.facing == CameraInfo.CAMERA_FACING_BACK){
				icon_back_camera_exist.setImageResource(R.drawable.ok);
				icon_front_camera_exist.setImageResource(R.drawable.error);
				btn_front_camera.setClickable(false);
			}
			else if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
				icon_front_camera_exist.setImageResource(R.drawable.ok);
				icon_back_camera_exist.setImageResource(R.drawable.error);
				btn_back_camera.setClickable(false);
			}
			break;
		case 2:
			icon_back_camera_exist.setImageResource(R.drawable.ok);
			icon_front_camera_exist.setImageResource(R.drawable.ok);
			break;
		default:
			break;
		}
    	
    /*	LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	if(locationManager != null){
    		icon_gps_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_gps_exist.setImageResource(R.drawable.error);
    		btn_gps.setClickable(false);
    	}*/
		
		//屏幕坏点检查
		icon_screen_exist.setImageResource(R.drawable.ok);
		//屏幕信息
		//是否支持flashlight
		camera = Camera.open();
		if(camera != null){
			Parameters parameters = camera.getParameters();
			if (parameters.getFlashMode() != null) {
				icon_flash_exist.setImageResource(R.drawable.ok);
				camera.release();
			} else {
				icon_flash_exist.setImageResource(R.drawable.error);
				btn_flash.setClickable(false);
				camera.release();
			}
		}
		
		//电池信息
		icon_battery_exist.setImageResource(R.drawable.ok);
		//cpu
		icon_cpu_exist.setImageResource(R.drawable.ok);
		//memory
		icon_memory_exist.setImageResource(R.drawable.ok);
		//聲音
		icon_altavoz_exist.setImageResource(R.drawable.ok);
		//振动
		Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		if(vibrator != null){
			icon_vibrador_exist.setImageResource(R.drawable.ok);
		}else{
			icon_vibrador_exist.setImageResource(R.drawable.error);
			btn_vibrator.setClickable(false);
		}
		//话筒
		icon_microphone_exist.setImageResource(R.drawable.ok);

		//系统信息
		icon_android_os_exist.setImageResource(R.drawable.ok);
		
		//传感器
		SensorManager sensor = (SensorManager) getSystemService(SENSOR_SERVICE);
    	if(sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
    		icon_speed_accelerate_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_speed_accelerate_exist.setImageResource(R.drawable.error);
    		btn_speed_accelerate.setClickable(false);
    	}
    	if(sensor.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
    		icon_gravity_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_gravity_exist.setImageResource(R.drawable.error);
    		btn_gravity.setClickable(false);
    	}
    	if(sensor.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null){
    		icon_gyroscope_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_gyroscope_exist.setImageResource(R.drawable.error);
    		btn_gyroscope.setClickable(false);
    	}
    	if(sensor.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
    		icon_light_sensor_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_light_sensor_exist.setImageResource(R.drawable.error);
    		btn_light_sensor.setClickable(false);
    	}
    	if(sensor.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null){
    		icon_linear_accelerate_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_linear_accelerate_exist.setImageResource(R.drawable.error);
    		btn_linear_accelerate.setClickable(false);
    	}
    	if(sensor.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
    		icon_compass_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_compass_exist.setImageResource(R.drawable.error);
    		btn_compass.setClickable(false);
    	}
    	if(sensor.getDefaultSensor(Sensor.TYPE_ORIENTATION) != null){
    		icon_orientation_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_orientation_exist.setImageResource(R.drawable.error);
    		btn_orientation.setClickable(false);
    	}
    	if(sensor.getDefaultSensor(Sensor.TYPE_PRESSURE) != null){
    		icon_pressure_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_pressure_exist.setImageResource(R.drawable.error);
    		btn_pressure.setClickable(false);
    	}
    	if(sensor.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null){
    		icon_proximity_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_proximity_exist.setImageResource(R.drawable.error);
    		btn_proximity.setClickable(false);
    	}
    	if(sensor.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) != null){
    		icon_rotation_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_rotation_exist.setImageResource(R.drawable.error);
    		btn_rotation.setClickable(false);
    	}
    	if(sensor.getDefaultSensor(Sensor.TYPE_TEMPERATURE) != null){
    		icon_temperature_exist.setImageResource(R.drawable.ok);
    	}else{
    		icon_temperature_exist.setImageResource(R.drawable.error);
    		btn_temperature.setClickable(false);
    	}
    }

	@Override
	protected void onPause() {
		super.onPause();
		if(camera != null){
			camera.release();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
//		if(camera != null){
//			camera.stopPreview();
//			camera.release();
//			camera = Camera.open();
//		}
//		else{
//			camera = Camera.open();
//		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if(camera != null){
			camera.release();
		}
	}

	private void init()
    {
    	viewGroup = (MyViewGroup) findViewById(R.id.scroll_layout); 	
    	LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout_bottom_image);   	
    	mViewCount = viewGroup.getChildCount();
    	mImageViews = new ImageView[mViewCount];   	
    	for(int i = 0; i < mViewCount; i++)    	{
    		mImageViews[i] = (ImageView) linearLayout.getChildAt(i);
    		mImageViews[i].setEnabled(true);
    		mImageViews[i].setOnClickListener(this);
    		mImageViews[i].setTag(i);
    	}    	
    	mCurSel = 0;
    	mImageViews[mCurSel].setEnabled(false);    	
    	viewGroup.SetOnViewChangeListener(this);   
    }

    private void setCurPoint(int index)
    {
    	if (index < 0 || index > mViewCount - 1 || mCurSel == index) {
    		return ;
    	}    	
    	mImageViews[mCurSel].setEnabled(true);
    	mImageViews[index].setEnabled(false);    	
    	mCurSel = index;
    }

    public void OnViewChange(int view) {
		// TODO Auto-generated method stub
		setCurPoint(view);
	}
}