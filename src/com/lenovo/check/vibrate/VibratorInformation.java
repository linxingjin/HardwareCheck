package com.lenovo.check.vibrate;

import com.lenovo.check.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;

public class VibratorInformation extends Activity {
	private Vibrator vibrator = null;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vibrator);
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
     		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);  
			vibrator.vibrate(100);  //振动时间
			//vibrator.cancel();    //关闭振动器
		}
		return false;
	}
}
