package com.lenovo.check.battery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.check.R;

public class BatteryInformation extends Activity{
	
	private BroadcastReceiver batteryReceiver;
	private ImageView image_battery;
	private TextView text_battery_level_on_image;
	private TextView text_battery_state;
	private TextView text_battery_health;
	private TextView text_battery_plugged;
	private TextView text_battery_scale;
	private TextView text_battery_level;
	private TextView text_battery_voltage;
	private TextView text_battery_temperature;
	private TextView text_battery_style;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battery);
		
		image_battery = (ImageView) findViewById(R.id.image_battery);
		text_battery_level_on_image = (TextView)findViewById(R.id.text_battery_level_on_image);
		text_battery_state = (TextView) findViewById(R.id.text_battery_state);
		text_battery_health = (TextView) findViewById(R.id.text_battery_health);
		text_battery_plugged = (TextView) findViewById(R.id.text_battery_plugged);
		text_battery_scale = (TextView) findViewById(R.id.text_battery_scale);
		text_battery_level = (TextView) findViewById(R.id.text_battery_level);
		text_battery_voltage = (TextView) findViewById(R.id.text_battery_voltage);
		text_battery_temperature = (TextView) findViewById(R.id.text_battery_temperature);
		text_battery_style =  (TextView) findViewById(R.id.text_battery_style);
		
//		registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));  //ע��
		this.getBatteryInfo();         
	}
	 
	/**
	 * ͨ������BroadcastReceiver�����ϵͳ���йص��Intent(ACTION_BATTERY_CHANGED)�ı仯��
	 * һ���н��յ�����¼��������ȡ��ǰ�����������ͨ��TextViews��ʾ�ڵ�ǰ��Ļ��
	 */
    private void getBatteryInfo(){
	        batteryReceiver = new BroadcastReceiver(){
			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();  
	            if (Intent.ACTION_BATTERY_CHANGED.equals(action)){                //���в�Ҫ����Ҳ����
	                int status = intent.getIntExtra("status", 0);  
	                int health = intent.getIntExtra("health", 1);  
//	                boolean present = intent.getBooleanExtra("present", false);  
	                int level = intent.getIntExtra("level", 0);  
	                int scale = intent.getIntExtra("scale", 0);  
	                int plugged = intent.getIntExtra("plugged", 0);  
	                int voltage = intent.getIntExtra("voltage", 0);    //��λmV
	                double temperature = intent.getIntExtra("temperature", 0);  //��λ0.1�ȣ����磬��ʾ197ʱ����ʾ19.7�ȡ�
	                String technology = intent.getStringExtra("technology");  
	                
	                String statusString = "δ֪";  
	                switch (status) {  
	                case BatteryManager.BATTERY_STATUS_UNKNOWN:  
	                    statusString = "δ֪";  
	                    break;  
	                case BatteryManager.BATTERY_STATUS_CHARGING:  
	                    statusString = "�����";  
	                    break;  
	                case BatteryManager.BATTERY_STATUS_DISCHARGING:    //��Щ���Ⱑ����ô��ʾ�Ķ��Ƿŵ��У���
	                    statusString = "�ŵ���";  
	                    break; 
	                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:  
	                    statusString = "δ���";  
	                    break;  
	                case BatteryManager.BATTERY_STATUS_FULL:  
	                    statusString = "�ѳ���";  
	                    break;  
	                default: break;
	            }                        
	            String healthString = "δ֪";         
	            switch (health) {  
	                case BatteryManager.BATTERY_HEALTH_UNKNOWN:  
	                	healthString = "δ֪";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_GOOD:  
	                    healthString = "����";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_OVERHEAT:  
	                    healthString = "����";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_DEAD:  
	                    healthString = "û��";     //��֪������Ϊ�ĸ����ıȽϺ���
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:  
	                    healthString = "��ѹ";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:  
	                    healthString = "δ֪����";  
	                    break;  
	                default: break;
	            } 
	            String acString = "��";              
	            switch (plugged) {  
	                case BatteryManager.BATTERY_PLUGGED_AC:  
	                    acString = "AC�����";  
	                    break;  
	                case BatteryManager.BATTERY_PLUGGED_USB:  
	                    acString = "USB";  
	                    break;  
	                default: break;
	            }  
	            image_battery.getDrawable().setLevel(level);
	            text_battery_level_on_image.setText(level + "%");
	            
	            text_battery_health.setText(healthString);
	            text_battery_level.setText(level + "%");
	            text_battery_plugged.setText(acString);
	            text_battery_scale.setText(scale + "%");
	            text_battery_state.setText(statusString);
	            text_battery_style.setText(technology);
	            text_battery_temperature.setText(temperature/10 + "��");
	            text_battery_voltage.setText(voltage + "mV");              
	            }  
	        }  
		};
    }  
    
    @Override  
    protected void onResume() {  
        super.onResume();  
        registerReceiver(batteryReceiver,   
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));  
      }   
     @Override  
    protected void onPause() {  
         super.onPause();  
         unregisterReceiver(batteryReceiver);   //Ϊ��ʡ��
    }  
}
