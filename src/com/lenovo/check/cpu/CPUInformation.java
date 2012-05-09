package com.lenovo.check.cpu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.lenovo.check.R;

public class CPUInformation extends Activity {

	private TextView text_cpu_model;
	private TextView text_cpu_max_frequency;
	private TextView text_cpu_min_frequency;
	private TextView text_cpu_current_frequency;
	private TextView text_cpu_bogo_mips;
	private TextView text_cpu_feature;
	private TextView text_cpu_implementer;
	private TextView text_cpu_architecture;
	private TextView text_cpu_variant;
	private TextView text_cpu_part;
	private TextView text_cpu_revision;
	private TextView text_cpu_hardware;
	private TextView text_cpu_another_revisison;
	private TextView text_cpu_serial;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cpu);
		text_cpu_another_revisison = (TextView) findViewById(R.id.text_cpu_another_revisison);
		text_cpu_architecture = (TextView) findViewById(R.id.text_cpu_architecture);
		text_cpu_bogo_mips = (TextView) findViewById(R.id.text_cpu_bogo_mips);
		text_cpu_current_frequency = (TextView) findViewById(R.id.text_cpu_current_frequency);
		text_cpu_feature = (TextView) findViewById(R.id.text_cpu_feature);
		text_cpu_hardware = (TextView) findViewById(R.id.text_cpu_hardware);
		text_cpu_implementer = (TextView) findViewById(R.id.text_cpu_implementer);
		text_cpu_max_frequency = (TextView) findViewById(R.id.text_cpu_max_frequency);
		text_cpu_min_frequency = (TextView) findViewById(R.id.text_cpu_min_frequency);
		text_cpu_model = (TextView) findViewById(R.id.text_cpu_model);
		text_cpu_part = (TextView) findViewById(R.id.text_cpu_part);
		text_cpu_revision = (TextView) findViewById(R.id.text_cpu_revision);
		text_cpu_serial = (TextView) findViewById(R.id.text_cpu_serial);
		text_cpu_variant = (TextView) findViewById(R.id.text_cpu_variant);
		
		this.getCPUInfo();
	}
	
	  private void getCPUInfo() {
	    	String [] cpuInformation = new String[20]; 
	    	String cpu = null;
			int cpuMaxFrequence = 0;  
			int cpuMinFrequence = 0;
			int cpuCurFrequence = 0;
	    	//String cpuCoreNum = null;
			try {
				FileReader cpuInfoFile = new FileReader("/proc/cpuinfo");
				FileReader cpuMaxFreqFile = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
				FileReader cpuMinFreqFile = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq");
				FileReader cpuCurFreqFile = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
				BufferedReader cpuInfoBuf = new BufferedReader(cpuInfoFile, 8192);
				BufferedReader cpuMaxFreqBuf = new BufferedReader(cpuMaxFreqFile, 10);
				BufferedReader cpuMinFreqBuf = new BufferedReader(cpuMinFreqFile, 10);
				BufferedReader cpuCurFreqBuf = new BufferedReader(cpuCurFreqFile, 10);
				
				for(int i = 0; i < 20; i++) {
					cpu = cpuInfoBuf.readLine();
					if (cpu != null) {
						cpuInformation[i] = cpu; 
						cpuInformation[i] = cpuInformation[i].substring(cpuInformation[i].indexOf(":") + 1, cpuInformation[i].length()).trim();
					}
				}
					
				cpuMaxFrequence = Integer.parseInt(cpuMaxFreqBuf.readLine())/1000;     //字符串转换为整形
				cpuMinFrequence = Integer.parseInt(cpuMinFreqBuf.readLine())/1000; 		   
				cpuCurFrequence = Integer.parseInt(cpuCurFreqBuf.readLine())/1000; 	
			} catch (IOException e) {  
				e.printStackTrace();
			}
				
			text_cpu_current_frequency.setText(cpuCurFrequence + "MHz");
			text_cpu_max_frequency.setText(cpuMaxFrequence + "MHz");
			text_cpu_min_frequency.setText(cpuMinFrequence + "MHz");
			text_cpu_model.setText(cpuInformation[0]);
			text_cpu_bogo_mips.setText(cpuInformation[2]);
			text_cpu_feature.setText(cpuInformation[4]);
			text_cpu_implementer.setText(cpuInformation[5]);
			text_cpu_architecture.setText(cpuInformation[6]);
			text_cpu_variant.setText(cpuInformation[7]);
			text_cpu_part.setText(cpuInformation[8]);
			text_cpu_revision.setText(cpuInformation[9]);
			text_cpu_hardware.setText(cpuInformation[11]);
			text_cpu_another_revisison.setText(cpuInformation[12]);
			text_cpu_serial.setText(cpuInformation[13]);
	    }  
	   
}
