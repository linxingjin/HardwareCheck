package com.lenovo.check.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.lenovo.check.R;
import com.lenovo.check.R.id;

public class BluetoothInformation extends Activity{
	
	private TextView text_bluetooth;
	private TextView text_bluetooth_hint;
	private TextView text_bluetooth_info;
	private TextView text_bluetooth_chinese_name;
	private TextView text_bluetooth_chinese_mac;
	private TextView text_bluetooth_chinese_search_mode;
	private TextView text_bluetooth_name;
	private TextView text_bluetooth_mac;
	private TextView text_bluetooth_search_mode;
	private Button btn_bluetooth_switch;
	private BluetoothAdapter bluetooth;
	private String isScaned = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth);
		text_bluetooth = (TextView) findViewById(R.id.text_bluetooth);
		text_bluetooth_chinese_name = (TextView) findViewById(R.id.text_bluetooth_chinese_name);
		text_bluetooth_chinese_mac = (TextView) findViewById(R.id.text_bluetooth_chinese_mac);
		text_bluetooth_chinese_search_mode = (TextView) findViewById(R.id.text_bluetooth_chinese_search_mode);
		text_bluetooth_hint = (TextView) findViewById(R.id.text_bluetooth_hint);
		text_bluetooth_name = (TextView) findViewById(R.id.text_bluetooth_name);
		text_bluetooth_search_mode = (TextView) findViewById(R.id.text_bluetooth_search_mode);
		text_bluetooth_mac = (TextView) findViewById(R.id.text_bluetooth_mac);
		text_bluetooth_info = (TextView) findViewById(R.id.text_bluetooth_info);
		btn_bluetooth_switch = (Button) findViewById(R.id.btn_bluetooth_switch);
		
		bluetooth = BluetoothAdapter.getDefaultAdapter();  //��ȡ��������������
		
		btn_bluetooth_switch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(bluetooth.isEnabled()) {			  	
					bluetooth.disable(); 
		    	} else {
		    		bluetooth.enable(); 
		    	}
			}
		});
		
		handler.post(r);
	}
	Handler handler = new Handler();
	Runnable r = new Runnable() {
		public void run() {
			// TODO Auto-generated method stub
			//System.out.println("this is a new thread!");
			if (bluetooth.isEnabled()) {
				text_bluetooth_hint.setText("�ѿ���");
				text_bluetooth_info.setVisibility(View.GONE);
				btn_bluetooth_switch.setBackgroundResource(R.drawable.switch_on_normal);
				switch(bluetooth.getScanMode())
		    	{
		    		case BluetoothAdapter.SCAN_MODE_NONE:
		    			isScaned = "���������������豸���Ӻ����������豸";
		    			break;
		    		case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
		    			isScaned = "�������������豸���ӵ����豸";
		    			break;
		    		case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
		    			isScaned = "�������������豸���Ӻ����������豸";
		    			break;
		    		default: break;
		    	}
				
				text_bluetooth_chinese_name.setText("��������: ");
				text_bluetooth_chinese_mac.setText("������ַ: ");
				text_bluetooth_chinese_search_mode.setText("����ģʽ: ");
				text_bluetooth_name.setText(bluetooth.getName());
				text_bluetooth_mac.setText(bluetooth.getAddress());
				text_bluetooth_search_mode.setText(isScaned);
			} else {
				text_bluetooth_hint.setText("δ����");
				text_bluetooth_info.setVisibility(View.VISIBLE);
				btn_bluetooth_switch.setBackgroundResource(R.drawable.switch_off_normal);
				text_bluetooth_chinese_name.setText("");
				text_bluetooth_chinese_mac.setText("");
				text_bluetooth_chinese_search_mode.setText("");
				text_bluetooth_name.setText("");
				text_bluetooth_mac.setText("");
				text_bluetooth_search_mode.setText("");
			}
			handler.postDelayed(r, 500);  
		}	
    };
}
