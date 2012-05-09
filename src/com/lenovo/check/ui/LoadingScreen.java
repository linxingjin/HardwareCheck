package com.lenovo.check.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.lenovo.check.R;   

public class LoadingScreen extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading); 
        new Handler().postDelayed(new Runnable(){ 
	         @Override 
	         public void run() { 
	             Intent mainIntent = new Intent(LoadingScreen.this, SystemVerification.class); 
	             startActivity(mainIntent); 
	             finish(); 
	         }          
        }, 2000);   
	} 
}
