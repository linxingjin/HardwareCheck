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
					text_wifi_hint.setText("���ڹر�......");
		    		btn_wifi_switch.setBackgroundResource(R.drawable.switch_off_disable);
		    	} else {
		    		wifiManager.setWifiEnabled(true);  
		    		text_wifi_hint.setText("���ڿ���......");
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
	    	    		 		   ((ipInt >> 16) & 0xFF) + "." + ((ipInt >> 24) & 0xFF);  //��ôʵ�ֵģ�������Щ���ˡ�
	    	     text_wifi_hint.setText("�ѿ���");
	    	     text_wifi_info.setText("\n��ǰ�����������: \n" + 
	    	    		 				"\n�ȵ��ַ: " + wifiInfo.getBSSID() +
					 	                "\n����SSID: " + wifiInfo.getHiddenSSID() +
	    	    		 				"\n�����ַ: " + wifiInfo.getMacAddress() +
	    	    		 				"\n����ID: " + wifiInfo.getNetworkId() +
								 	    "\n��������(SSID): " + wifiInfo.getSSID() +      //SSID������������
								 	    "\nIP��ַ: " + ipString + 
								 	    "\n�����ٶ�: " + wifiInfo.getLinkSpeed() + "Mbps" +
								 	    "\n�ź�ǿ��: " + wifiInfo.getRssi() + "db");	
	    	    scanResults = wifiManager.getScanResults();
	    	    if (scanResults != null) {
		    	    wifiScanResults = "����⵽" + scanResults.size() + "����������: ";
		     		for(int i = 0; i < scanResults.size(); i++) {
		     			ScanResult result = scanResults.get(i);
		        		wifiScanResults += "\n\n��������: " + result.SSID + "\n�ȵ��ַ: " + result.BSSID + "\n����: " 
		        		 					   + result.capabilities + "\nƵ��: " + result.frequency + "KHz" + "\n�ź�ǿ��: " + result.level + "dB";
		     	   	}
		     		text_scan_result.setText(wifiScanResults);
	    	    }
        	 } else {  
        		 btn_wifi_switch.setBackgroundResource(R.drawable.switch_off_normal);
        		 text_wifi_hint.setText("δ����");
        		 text_wifi_info.setText("δ���ӵ��κ��ȵ㣬��ȷ���ѿ���Wifi"); 
        		 text_scan_result.setText("δ��⵽�κ����磬��ȷ���ѿ���Wifi");
 			 }
        	 
        	 handler.postDelayed(r, 4000); 
 		}	
    };
}
