package com.lenovo.check.flash;

import java.util.List;

import com.lenovo.check.R;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FlashInformation extends Activity{
	private Camera camera = Camera.open();
	private Parameters parameters = camera.getParameters();
	private Button flashLightButton = null;  
	private TextView flashLightHint = null;
	private TextView text_flashlight_mode = null;
	private TextView text_support_flashlight_mode = null;	
	private boolean isLightOn = false;
	private String supportedFlashModes = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flash_light);
		flashLightButton = (Button) findViewById(R.id.btn_flashlight);
		flashLightHint = (TextView) findViewById(R.id.text_flashlight_hint);
		text_flashlight_mode = (TextView) findViewById(R.id.text_flashlight_mode);
		text_support_flashlight_mode = (TextView) findViewById(R.id.text_support_flashlight_mode);
		flashLightButton.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub  
				// 打开闪关灯
				if (isLightOn) {
					parameters.setFlashMode(Parameters.FLASH_MODE_OFF);    
					camera.setParameters(parameters);	
					flashLightButton.setBackgroundResource(R.drawable.flash_off);
					flashLightHint.setText("点击图片打开闪关灯");
					text_flashlight_mode.setText("off");
					isLightOn = false;
				} else { 
					parameters.setFlashMode(Parameters.FLASH_MODE_TORCH); //FLASH_MODE_ON 无法打开
					camera.setParameters(parameters);	
					flashLightButton.setBackgroundResource(R.drawable.flash_on);
					flashLightHint.setText("点击图片关闭闪关灯");
					text_flashlight_mode.setText("torch");
					isLightOn = true;
				}  
			}
		});	
		List<String> supportedFlashModesList = parameters.getSupportedFlashModes();
		for (int i = 0; i < supportedFlashModesList.size(); i++) {
			supportedFlashModes += supportedFlashModesList.get(i);
			if (i < supportedFlashModesList.size() - 1)
				supportedFlashModes += "/";
		}
		text_support_flashlight_mode.setText(supportedFlashModes);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		// 关闭闪光灯
		super.onPause();
		camera.release();   //此句不能少，否则退出再次进入时会提示无法连接相机服务器。
	}
}

