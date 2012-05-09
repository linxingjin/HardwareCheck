package com.lenovo.check.memory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import com.lenovo.check.R;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.widget.TextView;

public class MemoryInformation extends Activity{
	
	private TextView text_memory_total;
	private TextView text_memory_available;
	private TextView text_memory_used;
	private TextView text_sdcard_total;
	private TextView text_sdcard_available;
	private TextView text_sdcard_used;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memory);
		text_memory_total = (TextView) findViewById(R.id.text_memory_total);
		text_memory_available = (TextView) findViewById(R.id.text_memory_available);
		text_memory_used = (TextView) findViewById(R.id.text_memory_used);
		text_sdcard_total = (TextView) findViewById(R.id.text_sdcard_total);
		text_sdcard_available = (TextView) findViewById(R.id.text_sdcard_available);
		text_sdcard_used = (TextView) findViewById(R.id.text_sdcard_used);
		
		this.getMemroyInfo();
	}
	 //android�����ڴ��С��Ϣ�����ϵͳ��/proc/meminfo�ļ����棬����ͨ����ȡ����ļ�����ȡ��Щ��Ϣ
    private void getMemroyInfo(){
    	String totalRam_Size = null;
    	String totalRamSize = null;
    	String availRamSize = null;
    	String usedRamSize = null;
    	try {
			FileReader memFile = new FileReader("/proc/meminfo");
			BufferedReader localBufferedReader = new BufferedReader(memFile, 100);
			totalRamSize = localBufferedReader.readLine();
			//��ȡ��Ҫ���ַ���, ��15�ǹ�������������Ƚϱ����������
		    //totalRamSize = totalRamSize.substring(15, totalRamSize.length());
		    //�˷������ϸ������ĸĽ������Ժö��ˡ�
		    totalRam_Size = totalRamSize.substring(totalRamSize.indexOf(":") + 1, totalRamSize.length()-2).trim();
		    totalRamSize = this.formatSize(Long.parseLong(totalRam_Size)*1024);
	   //   totalRamSize = Formatter.formatFileSize(SelfCheckActivity.this, totalRam_Size); //�˷���Ĭ�Ͻ����ֿ���B��Ȼ��ת��ΪKB��MB
		 //   String totalRamSizes = Formatter.formatFileSize(SelfCheckActivity.this, totalRamSize);
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
    	//avail ram size
    	ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);  
        MemoryInfo ramInfo = new MemoryInfo();  
        am.getMemoryInfo(ramInfo);   
        availRamSize = this.formatSize(ramInfo.availMem);
        //�˷���Ĭ�Ͻ����ֿ���B��Ȼ��ת��ΪKB��MB
        //String availRamSize = Formatter.formatFileSize(SelfCheckActivity.this, availRam_Size);       
        //used ram size    
        usedRamSize= this.formatSize(Long.parseLong(totalRam_Size)*1024 - ramInfo.availMem);
    	//ROM
        File path = Environment.getDataDirectory();
    	StatFs stat = new StatFs(path.getPath());
    	int blockSize = stat.getBlockSize();
    	int allBlockCounts = stat.getBlockCount();
    	int availableBlocks = stat.getAvailableBlocks();
    	//�ַ�����ת��    
    	String totalRomSize = this.formatSize(allBlockCounts*blockSize);
    	String availRomSize = this.formatSize(availableBlocks*blockSize);
    	//String totalRomSize = Formatter.formatFileSize(SelfCheckActivity.this, totalRom_Size);
        //String test = formateFileSize(totalRomSize);
    	
    	//SDCard
    	String totalSDCardSize = null;
    	String availSDCardSize = null;
    	String usedSDCardSize = null;  
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
    			File sdcardDir = Environment.getExternalStorageDirectory();
    			StatFs sf = new StatFs(sdcardDir.getPath());
    			long bSize = sf.getBlockSize();
    			long bCount = sf.getBlockCount();  
    			long availBlocks = sf.getAvailableBlocks();
    			totalSDCardSize = this.formatSize(bSize*bCount);//�ܴ�С
    			availSDCardSize = this.formatSize(bSize*availBlocks);//���ô�С		
    			usedSDCardSize = this.formatSize(bSize*bCount - bSize*availBlocks);  //��ʹ�ô�С
    		}
        //used SDCard size

        text_memory_total.setText(totalRamSize);
        text_memory_available.setText(availRamSize);
        text_memory_used.setText(usedRamSize);
        text_sdcard_total.setText(totalSDCardSize);
        text_sdcard_available.setText(availSDCardSize);
        text_sdcard_used.setText(usedSDCardSize);

    }     
    //���ݸ�ʽ�����
    public String formatSize(long size) {  
        String end = "B";  
        double result = 0;       
        if (size >= 1024) {  
        	end = "kB";
        	result = size/1024;
            if (result >= 1024){
            	end = "MB";
            	result /= 1024;		
            }
            if (result >= 1024) {  
                end = "GB";  
                result /= 1024;  
            }  
        } else {  
        	result = size;  
        }
        //�Ը��������и�ʽ����������λС��
        String finalResult = new DecimalFormat("0.00").format(result);
		return finalResult + end;  
    }
}
