package com.lenovo.check.baseband;

import java.lang.reflect.Method;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.lenovo.check.R;

public class BasebandInformation extends Activity {
	
	private TextView text_baseband;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baseband);
		
		text_baseband = (TextView)findViewById(R.id.text_baseband);
		text_baseband.setText(getBaseBandInfo());
	}
	
	public String getBaseBandInfo(){
		Object result = null;
		try{
			Class bandClass = Class.forName("android.os.SystemProperties");
			Object invoker = bandClass.newInstance();
			Method method = bandClass.getMethod("get", new Class[]{String.class, String.class});
			result = method.invoke(invoker, new Object[]{"gsm.version.baseband","no message"});
			System.out.println(result.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(result != null && result.toString() != ""){
			return result.toString();
		}
		else{
			result = "无法获取基带信息";
			return result.toString();
		}
	}
}
