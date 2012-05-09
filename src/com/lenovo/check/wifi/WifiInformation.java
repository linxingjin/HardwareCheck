package com.lenovo.check.wifi;

import java.util.List;

import android.app.Activity;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.lenovo.check.R;

public class WifiInformation extends Activity {
	private TextView text_wifi_info;  
	private TextView text_wifi_hint;
	private TextView text_scan_result;
	private Button btn_wifi_switch;
	private WifiManager wifiManager;
	private WifiInfo wifiInfo;
	private List<ScanResult> scanResults;
	private String wifiScanResults = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifi); 
		wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		wifiInfo = wifiManager.getConnectionInfo();
		text_wifi_hint = (TextView) findViewById(R.id.text_wifi_hint);
		text_wifi_info = (TextView)findViewById(R.id.text_wifi_info);  
		text_scan_result = (TextView) findViewById(R.id.text_scan_result);
		btn_wifi_switch = (Button) findViewById(R.id.btn_wifi_switch);
		
		handler.post(r);
		
		btn_wifi_switch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(wifiManager.isWifiEnabled()) {			  	
					wifiManager.setWifiEnabled(false);  
					text_wifi_hint.setText("正在关闭......");
		    		btn_wifi_switch.setBackgroundResource(R.drawable.switch_off_disable);
		    	} else {
		    		wifiManager.setWifiEnabled(true);  
		    		text_wifi_hint.setText("正在开启......");
		    		btn_wifi_switch.setBackgroundResource(R.drawable.switch_on_disable);
		    	}
			}
		});
		
		
	}
	Handler handler = new Handler();
	Runnable r = new Runnable() {
         public void run() { 
        	 if (wifiManager.isWifiEnabled()) {  
        		 btn_wifi_switch.setBackgroundResource(R.drawable.switch_on_normal);
        		 int ipInt = wifiInfo.getIpAddress();	
	    	     String ipString = (ipInt & 0xFF) + "." + ((ipInt >> 8) & 0xFF) + "." + 
	    	    		 		   ((ipInt >> 16) & 0xFF) + "." + ((ipInt >> 24) & 0xFF);  //怎么实现的？？？有些乱了。
	    	     text_wifi_hint.setText("已开启");
	    	     text_wifi_info.setText("\n当前网络连接情况: \n" + 
	    	    		 				"\n热点地址: " + wifiInfo.getBSSID() +
					 	                "\n隐藏SSID: " + wifiInfo.getHiddenSSID() +
	    	    		 				"\n物理地址: " + wifiInfo.getMacAddress() +
	    	    		 				"\n网络ID: " + wifiInfo.getNetworkId() +
								 	    "\n网络名称(SSID): " + wifiInfo.getSSID() +      //SSID代表网络名称
								 	    "\nIP地址: " + ipString + 
								 	    "\n连接速度: " + wifiInfo.getLinkSpeed() + "Mbps" +
								 	    "\n信号强度: " + wifiInfo.getRssi() + "db");	
	    	    scanResults = wifiManager.getScanResults();
	    	    if (scanResults != null) {
		    	    wifiScanResults = "共检测到" + scanResults.size() + "个可用网络: ";
		     		for(int i = 0; i < scanResults.size(); i++) {
		     			ScanResult result = scanResults.get(i);
		        		wifiScanResults += "\n\n网络名称: " + result.SSID + "\n热点地址: " + result.BSSID + "\n性能: " 
		        		 					   + result.capabilities + "\n频率: " + result.frequency + "KHz" + "\n信号强度: " + result.level + "dB";
		     	   	}
		     		text_scan_result.setText(wifiScanResults);
	    	    }
        	 } else {  
        		 btn_wifi_switch.setBackgroundResource(R.drawable.switch_off_normal);
        		 text_wifi_hint.setText("未开启");
        		 text_wifi_info.setText("未连接到任何热点，请确保已开启Wifi"); 
        		 text_scan_result.setText("未检测到任何网络，请确保已开启Wifi");
 			 }
        	 
        	 handler.postDelayed(r, 4000); 
 		}	
    };
}
