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
		
//		registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));  //注册
		this.getBatteryInfo();         
	}
	 
	/**
	 * 通过创建BroadcastReceiver来侦测系统中有关电池Intent(ACTION_BATTERY_CHANGED)的变化，
	 * 一旦有接收到相关事件，将会读取当前电量情况，并通过TextViews显示在当前屏幕。
	 */
    private void getBatteryInfo(){
	        batteryReceiver = new BroadcastReceiver(){
			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();  
	            if (Intent.ACTION_BATTERY_CHANGED.equals(action)){                //此行不要好像也可以
	                int status = intent.getIntExtra("status", 0);  
	                int health = intent.getIntExtra("health", 1);  
//	                boolean present = intent.getBooleanExtra("present", false);  
	                int level = intent.getIntExtra("level", 0);  
	                int scale = intent.getIntExtra("scale", 0);  
	                int plugged = intent.getIntExtra("plugged", 0);  
	                int voltage = intent.getIntExtra("voltage", 0);    //单位mV
	                double temperature = intent.getIntExtra("temperature", 0);  //单位0.1度，例如，显示197时，表示19.7度。
	                String technology = intent.getStringExtra("technology");  
	                
	                String statusString = "未知";  
	                switch (status) {  
	                case BatteryManager.BATTERY_STATUS_UNKNOWN:  
	                    statusString = "未知";  
	                    break;  
	                case BatteryManager.BATTERY_STATUS_CHARGING:  
	                    statusString = "充电中";  
	                    break;  
	                case BatteryManager.BATTERY_STATUS_DISCHARGING:    //有些问题啊，怎么显示的都是放电中？？
	                    statusString = "放电中";  
	                    break; 
	                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:  
	                    statusString = "未充电";  
	                    break;  
	                case BatteryManager.BATTERY_STATUS_FULL:  
	                    statusString = "已充满";  
	                    break;  
	                default: break;
	            }                        
	            String healthString = "未知";         
	            switch (health) {  
	                case BatteryManager.BATTERY_HEALTH_UNKNOWN:  
	                	healthString = "未知";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_GOOD:  
	                    healthString = "健康";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_OVERHEAT:  
	                    healthString = "过热";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_DEAD:  
	                    healthString = "没电";     //不知道翻译为哪个中文比较合适
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:  
	                    healthString = "过压";  
	                    break;  
	                case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:  
	                    healthString = "未知错误";  
	                    break;  
	                default: break;
	            } 
	            String acString = "无";              
	            switch (plugged) {  
	                case BatteryManager.BATTERY_PLUGGED_AC:  
	                    acString = "AC充电器";  
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
	            text_battery_temperature.setText(temperature/10 + "℃");
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
         unregisterReceiver(batteryReceiver);   //为了省电
    }  
}
