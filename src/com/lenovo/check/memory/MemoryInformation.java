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
	 //android的总内存大小信息存放在系统的/proc/meminfo文件里面，可以通过读取这个文件来获取这些信息
    private void getMemroyInfo(){
    	String totalRam_Size = null;
    	String totalRamSize = null;
    	String availRamSize = null;
    	String usedRamSize = null;
    	try {
			FileReader memFile = new FileReader("/proc/meminfo");
			BufferedReader localBufferedReader = new BufferedReader(memFile, 100);
			totalRamSize = localBufferedReader.readLine();
			//截取需要的字符串, 但15是估算出来，方法比较笨，不够灵活
		    //totalRamSize = totalRamSize.substring(15, totalRamSize.length());
		    //此方法是上个方法的改进，明显好多了。
		    totalRam_Size = totalRamSize.substring(totalRamSize.indexOf(":") + 1, totalRamSize.length()-2).trim();
		    totalRamSize = this.formatSize(Long.parseLong(totalRam_Size)*1024);
	   //   totalRamSize = Formatter.formatFileSize(SelfCheckActivity.this, totalRam_Size); //此方法默认将数字看成B，然后转换为KB或MB
		 //   String totalRamSizes = Formatter.formatFileSize(SelfCheckActivity.this, totalRamSize);
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
    	//avail ram size
    	ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);  
        MemoryInfo ramInfo = new MemoryInfo();  
        am.getMemoryInfo(ramInfo);   
        availRamSize = this.formatSize(ramInfo.availMem);
        //此方法默认将数字看成B，然后转换为KB或MB
        //String availRamSize = Formatter.formatFileSize(SelfCheckActivity.this, availRam_Size);       
        //used ram size    
        usedRamSize= this.formatSize(Long.parseLong(totalRam_Size)*1024 - ramInfo.availMem);
    	//ROM
        File path = Environment.getDataDirectory();
    	StatFs stat = new StatFs(path.getPath());
    	int blockSize = stat.getBlockSize();
    	int allBlockCounts = stat.getBlockCount();
    	int availableBlocks = stat.getAvailableBlocks();
    	//字符类型转换    
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
    			totalSDCardSize = this.formatSize(bSize*bCount);//总大小
    			availSDCardSize = this.formatSize(bSize*availBlocks);//可用大小		
    			usedSDCardSize = this.formatSize(bSize*bCount - bSize*availBlocks);  //已使用大小
    		}
        //used SDCard size

        text_memory_total.setText(totalRamSize);
        text_memory_available.setText(availRamSize);
        text_memory_used.setText(usedRamSize);
        text_sdcard_total.setText(totalSDCardSize);
        text_sdcard_available.setText(availSDCardSize);
        text_sdcard_used.setText(usedSDCardSize);

    }     
    //数据格式化输出
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
        //对浮点数进行格式化，保留两位小数
        String finalResult = new DecimalFormat("0.00").format(result);
		return finalResult + end;  
    }
}
