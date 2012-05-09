package com.lenovo.check.screen;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.lenovo.check.R;

public class ScreenColor extends Activity{
	private LinearLayout bgWhite = null;
	private LinearLayout bgRed = null;
	private LinearLayout bgGreen = null;
	private LinearLayout bgBlue = null;
	private LinearLayout bgBlack = null;
	private int touchCount = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen);
		bgWhite = (LinearLayout) findViewById(R.id.background_white);
		bgRed = (LinearLayout) findViewById(R.id.background_red);
		bgGreen = (LinearLayout) findViewById(R.id.background_green);
		bgBlue = (LinearLayout) findViewById(R.id.background_blue);
		bgBlack = (LinearLayout) findViewById(R.id.background_black);
	}
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {	  //此行必须要加，不加就乱了, 但实际上效果不能滑动
			switch (++touchCount)
			{
				case 1: 
					bgWhite.setVisibility(View.GONE);
					bgRed.setVisibility(View.VISIBLE);
					break;
				case 2: 
					bgRed.setVisibility(View.GONE);
					bgGreen.setVisibility(View.VISIBLE);
					break;
				case 3: 
					bgGreen.setVisibility(View.GONE);
					bgBlue.setVisibility(View.VISIBLE);
					break;
				case 4: 
					bgBlue.setVisibility(View.GONE);
					bgBlack.setVisibility(View.VISIBLE);
					break;
				case 5: 
					bgBlack.setVisibility(View.VISIBLE);
					bgWhite.setVisibility(View.VISIBLE);
					break;
				default:
					break;
			}
		}
		if (touchCount == 5) 
			touchCount = 0;
		return true; 
	}
}
