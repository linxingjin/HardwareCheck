package com.lenovo.check.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.lenovo.check.R;

public class DisplayInformation extends Activity{
	private Button btn_screen_color_test;
	private TextView  text_screen_resolution;;
	private TextView text_screen_height_pixel;
	private TextView text_screen_width_pixel;
	private TextView text_screen_density;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		
		btn_screen_color_test = (Button) findViewById(R.id.btn_screen_color_test);
		text_screen_resolution = (TextView) findViewById(R.id.text_screen_resolution);
		text_screen_height_pixel = (TextView) findViewById(R.id.text_screen_height_pixel);
		text_screen_width_pixel = (TextView) findViewById(R.id.text_screen_width_pixel);
		text_screen_density = (TextView) findViewById(R.id.text_screen_density);
		
		btn_screen_color_test.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent screenIntent = new Intent(DisplayInformation.this, ScreenColor.class);
				startActivity(screenIntent);
			}
		});
		
		this.getDisplayInfo();
	}
	
	public void getDisplayInfo() {  
    	 DisplayMetrics metrics = new DisplayMetrics();
    	 getWindowManager().getDefaultDisplay().getMetrics(metrics);
    	 int height = metrics.heightPixels;
    	 int width = metrics.widthPixels;
    	 text_screen_resolution.setText(height + "*" + width);
    	 text_screen_height_pixel.setText(height + "");
    	 text_screen_width_pixel.setText(width + "");
    	 text_screen_density.setText(metrics.densityDpi + "dpi");
    }
}
