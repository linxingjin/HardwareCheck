package com.lenovo.check.os;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.lenovo.check.R;
import com.lenovo.check.ui.SystemVerification;

public class OSInformation extends Activity {
	
   private TextView android_build_manufacturer;
   private TextView android_build_model;
   private TextView android_build_product;
   private TextView android_build_version_release;
   private TextView android_build_version_sdk;
   private TextView android_build_board;
   private TextView android_build_bootloader;
   private TextView android_build_brand;
   private TextView android_build_cpu_abi;
   private TextView android_build_cpu_abi2;
   private TextView android_build_device;
   private TextView android_build_display;
   private TextView android_build_fingerprint;
   private TextView android_build_hardware;
   private TextView android_build_host;
   private TextView android_build_id;
   private TextView android_build_radio;
   private TextView android_build_tags;
   private TextView android_build_time;
   private TextView android_build_type;
   private TextView android_build_user;
   private TextView android_build_serial;
   private TextView android_build_version_codename;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_os);
		
	   android_build_manufacturer = (TextView)findViewById(R.id.android_build_manufacturer);
	   android_build_model = (TextView)findViewById(R.id.android_build_model);
	   android_build_product = (TextView)findViewById(R.id.android_build_product);
	   android_build_version_release = (TextView)findViewById(R.id.android_build_version_release);
	   android_build_version_sdk = (TextView)findViewById(R.id.android_build_version_sdk);
	   android_build_board = (TextView)findViewById(R.id.android_build_board);
	   android_build_bootloader = (TextView)findViewById(R.id.android_build_bootloader);
	   android_build_brand = (TextView)findViewById(R.id.android_build_brand);
	   android_build_cpu_abi = (TextView)findViewById(R.id.android_build_cpu_abi);
	   android_build_cpu_abi2 = (TextView)findViewById(R.id.android_build_cpu_abi2);
	   android_build_device = (TextView)findViewById(R.id.android_build_device);
	   android_build_display = (TextView)findViewById(R.id.android_build_display);
	   android_build_fingerprint = (TextView)findViewById(R.id.android_build_fingerprint);
	   android_build_hardware = (TextView)findViewById(R.id.android_build_hardware);
	   android_build_host = (TextView)findViewById(R.id.android_build_host);
	   android_build_id = (TextView)findViewById(R.id.android_build_id);
	   android_build_radio = (TextView)findViewById(R.id.android_build_radio);
	   android_build_tags = (TextView)findViewById(R.id.android_build_tags);
	   android_build_time = (TextView)findViewById(R.id.android_build_time);
	   android_build_type = (TextView)findViewById(R.id.android_build_type);
	   android_build_user = (TextView)findViewById(R.id.android_build_user);
	   android_build_serial = (TextView)findViewById(R.id.android_build_serial);
	   android_build_version_codename = (TextView)findViewById(R.id.android_build_version_codename);
	   
	   android_build_manufacturer.setText(android.os.Build.MANUFACTURER);
	   android_build_model.setText(android.os.Build.MODEL);
	   android_build_product.setText(android.os.Build.PRODUCT);
	   android_build_version_release.setText(android.os.Build.VERSION.RELEASE);
	   android_build_version_sdk.setText(android.os.Build.VERSION.SDK);
	   android_build_board.setText(android.os.Build.BOARD);
	   android_build_bootloader.setText(android.os.Build.BOOTLOADER);
	   android_build_brand.setText(android.os.Build.BRAND);
	   android_build_cpu_abi.setText(android.os.Build.CPU_ABI);
	   android_build_cpu_abi2.setText(android.os.Build.CPU_ABI2);
	   android_build_device.setText(android.os.Build.DEVICE);
	   android_build_display.setText(android.os.Build.DISPLAY);
	   android_build_fingerprint.setText(android.os.Build.FINGERPRINT);
	   android_build_hardware.setText(android.os.Build.HARDWARE );
	   android_build_host.setText(android.os.Build.HOST);
	   android_build_id.setText(android.os.Build.ID);
	   android_build_radio.setText(android.os.Build.RADIO);
	   android_build_tags.setText(android.os.Build.TAGS);
	   android_build_time.setText(String.valueOf(android.os.Build.TIME));
	   android_build_type.setText(android.os.Build.TYPE);
	   android_build_user.setText(android.os.Build.USER );
	   android_build_serial.setText(android.os.Build.SERIAL);
	   android_build_version_codename.setText(android.os.Build.VERSION.CODENAME);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
			Intent intent = new Intent(OSInformation.this, SystemVerification.class);
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
