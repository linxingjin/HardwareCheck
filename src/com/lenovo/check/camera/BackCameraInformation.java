package com.lenovo.check.camera;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.lenovo.check.R;

public class BackCameraInformation extends Activity implements SurfaceHolder.Callback{
	private Camera camera;
	private Parameters cameraParameters;
	private SurfaceView preview_back_camera; 
	private SurfaceHolder backSurfaceHolder;
	private TextView back_camera_preview_format;
	private TextView back_camera_supported_preview_formats;
	private TextView back_camera_preview_size;
	private TextView back_camera_supported_preview_sizes;
	private TextView back_camera_preview_framerate;
	private TextView back_camera_supported_preview_framerates;
	private TextView back_camera_jpeg_quality;
	private TextView back_camera_jpeg_thumbnail_quality;
	private TextView back_camera_jpeg_thumbnail_size;
	private TextView back_camera_supported_jpeg_thumbnail_sizes;
	private TextView back_camera_picture_format;
	private TextView back_camera_supported_picture_formats;
	private TextView back_camera_picture_size;
	private TextView back_camera_supported_picture_sizes;
	private TextView back_camera_focal_length;
	private TextView back_camera_zoom;
	private TextView back_camera_maxzoom;
	private TextView back_camera_focus_mode;
	private TextView back_camera_supported_focus_mode;
	private TextView back_camera_exposure_compensation;
	private TextView back_camera_exposure_compensation_step;
	private TextView back_camera_max_exposure_compensation;
	private TextView back_camera_min_exposure_compensation;
	private TextView back_camera_horizontal_view_angle;
	private TextView back_camera_vertical_view_angle;
	private TextView back_camera_flash_mode;
	private TextView back_camera_supported_flash_modes;
	private TextView back_camera_white_balance;
	private TextView back_camera_supported_white_balance;
	private TextView back_camera_color_effect;
	private TextView back_camera_supported_color_effect;
	private TextView back_camera_antibanding;
	private TextView back_camera_supported_antibanding;
	private TextView back_camera_scene_mode;
	private TextView back_camera_supported_scene_modes;

	@Override  
	protected void onCreate(Bundle savedInstanceState) {  
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.back_camera);
		
		preview_back_camera = (SurfaceView) findViewById(R.id.preview_back_camera);

		back_camera_preview_format = (TextView) findViewById(R.id.back_camera_preview_format);
		back_camera_supported_preview_formats = (TextView) findViewById(R.id.back_camera_supported_preview_formats);
		back_camera_preview_size = (TextView) findViewById(R.id.back_camera_preview_size);
		back_camera_supported_preview_sizes = (TextView) findViewById(R.id.back_camera_supported_preview_sizes);
		back_camera_preview_framerate = (TextView) findViewById(R.id.back_camera_preview_framerate);
		back_camera_supported_preview_framerates = (TextView) findViewById(R.id.back_camera_supported_preview_framerates);
		back_camera_jpeg_quality = (TextView) findViewById(R.id.back_camera_jpeg_quality);
		back_camera_jpeg_thumbnail_quality = (TextView) findViewById(R.id.back_camera_jpeg_thumbnail_quality);
		back_camera_jpeg_thumbnail_size = (TextView) findViewById(R.id.back_camera_jpeg_thumbnail_size);
		back_camera_supported_jpeg_thumbnail_sizes = (TextView) findViewById(R.id.back_camera_supported_jpeg_thumbnail_sizes);
		back_camera_picture_format = (TextView) findViewById(R.id.back_camera_picture_format);
		back_camera_supported_picture_formats = (TextView) findViewById(R.id.back_camera_supported_picture_formats);
		back_camera_picture_size = (TextView) findViewById(R.id.back_camera_picture_size);
		back_camera_supported_picture_sizes = (TextView) findViewById(R.id.back_camera_supported_picture_sizes);
		back_camera_focal_length = (TextView) findViewById(R.id.back_camera_focal_length);
		back_camera_zoom = (TextView) findViewById(R.id.back_camera_zoom);
		back_camera_maxzoom = (TextView) findViewById(R.id.back_camera_maxzoom);
		back_camera_focus_mode = (TextView) findViewById(R.id.back_camera_focus_mode);
		back_camera_supported_focus_mode = (TextView) findViewById(R.id.back_camera_supported_focus_mode);
		back_camera_exposure_compensation = (TextView) findViewById(R.id.back_camera_exposure_compensation);
		back_camera_exposure_compensation_step = (TextView) findViewById(R.id.back_camera_exposure_compensation_step);
		back_camera_max_exposure_compensation = (TextView) findViewById(R.id.back_camera_max_exposure_compensation);
		back_camera_min_exposure_compensation = (TextView) findViewById(R.id.back_camera_min_exposure_compensation);
		back_camera_horizontal_view_angle = (TextView) findViewById(R.id.back_camera_horizontal_view_angle);
		back_camera_vertical_view_angle = (TextView) findViewById(R.id.back_camera_vertical_view_angle);
		back_camera_flash_mode = (TextView) findViewById(R.id.back_camera_flash_mode);
		back_camera_supported_flash_modes = (TextView) findViewById(R.id.back_camera_supported_flash_modes);
		back_camera_white_balance = (TextView) findViewById(R.id.back_camera_white_balance);
		back_camera_supported_white_balance = (TextView) findViewById(R.id.back_camera_supported_white_balance);
		back_camera_color_effect = (TextView) findViewById(R.id.back_camera_color_effect);
		back_camera_supported_color_effect = (TextView) findViewById(R.id.back_camera_supported_color_effect);
		back_camera_antibanding = (TextView) findViewById(R.id.back_camera_antibanding);
		back_camera_supported_antibanding = (TextView) findViewById(R.id.back_camera_supported_antibanding);
		back_camera_scene_mode = (TextView) findViewById(R.id.back_camera_scene_mode);
		back_camera_supported_scene_modes = (TextView) findViewById(R.id.back_camera_supported_scene_modes);
				
		backSurfaceHolder = preview_back_camera.getHolder(); 
		backSurfaceHolder.addCallback(this);    //好像必须有
		
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
		    CameraInfo info = new CameraInfo();
		    Camera.getCameraInfo(i, info);
		    if (info.facing == CameraInfo.CAMERA_FACING_BACK) {//这就是后置摄像头  
		    //	getCameraInfo();
		    	camera = Camera.open(i);
		    }  	 
		}
		// TODO Auto-generated method stub
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub	
			DisplayMetrics metrics = new DisplayMetrics();
		   	getWindowManager().getDefaultDisplay().getMetrics(metrics);
	        try {     
	            camera.setPreviewDisplay(backSurfaceHolder);  
	        } catch (IOException e) { 
	        	camera.release();
	            e.printStackTrace();     
	        } 
	        if (metrics.heightPixels > metrics.widthPixels) {
	        	//设备竖屏的话不行，默认的不行，设置显示旋转90度。如果横屏的话，默认即可，不用设置。
            	camera.setDisplayOrientation(90); 
            } 
	    	camera.startPreview();    
	    	getCameraInformatioin();
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	@Override  
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(camera != null) {
			camera.release();   //此句不能少,退出出必须释放，否则其它东西就无法使用camera了。不加这句，我得重启机器才行。
		}
	}
	private void getCameraInformatioin() {
		String previewFormat = "";
		String pictureFormat = "";
		String flashMode = "";
		String supportedPreviewFormats = "";
		String supportedPictureFormats = "";
		String supportedPreviewFrameRates = "";
		String supportedColorEffect = "";
		String supportedFocusModes = "";
		String supportedFlashModes = "";
		String supportedWhiteBalance = "";
		String supportedAntibanding = "";
		String supportedSceneModes = "";
		String supportedPictureSizes = "";
		String supportedPreviewSizes = "";
		String supportedJpegThumbnailSizes = "";
		
		cameraParameters = camera.getParameters();
		
		//读取当前的预览格式
		switch (cameraParameters.getPreviewFormat()) { 
			case ImageFormat.JPEG: 
				previewFormat = "JPEG";
				break;
			case ImageFormat.NV16:
				previewFormat = "NV16";
				break;
			case ImageFormat.NV21:
				previewFormat = "NV21";
				break;
			case ImageFormat.RGB_565:
				previewFormat = "RGB_565";
				break;
			case ImageFormat.UNKNOWN:
				previewFormat = "unknown";
				break;
			case ImageFormat.YUY2:
				previewFormat = "YUY2";
				break;
			case ImageFormat.YV12:
				previewFormat = "YV12";
				break;
			default: break;	
		}
		//读取当前的图片格式
		switch (cameraParameters.getPictureFormat()) { 
			case ImageFormat.JPEG: 
				pictureFormat = "JPEG";
				break;
			case ImageFormat.NV16:
				pictureFormat = "NV16";
				break;
			case ImageFormat.NV21:
				pictureFormat = "NV21";
				break;
			case ImageFormat.RGB_565:
				pictureFormat = "RGB_565";
				break;
			case ImageFormat.UNKNOWN:
				pictureFormat = "unknown";
				break;
			case ImageFormat.YUY2:
				pictureFormat = "YUY2";
				break;
			case ImageFormat.YV12:
				pictureFormat = "YV12";
				break;
			default: break;	
		}
		//读取当前的闪光灯模式 (必须的，摄像头未必都有闪光灯)
		if (cameraParameters.getFlashMode() != null) {
			flashMode = cameraParameters.getFlashMode();
		} else {
			flashMode = "不支持闪光灯";
		}
		//读取支持的图片尺寸
		Size pictureSizeWH = cameraParameters.getPictureSize();
		String pictureSize = pictureSizeWH.width + "x" + pictureSizeWH.height;
		List<Size> supportedPictureSizesList = cameraParameters.getSupportedPictureSizes();
		for (int i = 0; i < supportedPictureSizesList.size(); i++) {
			Size size = supportedPictureSizesList.get(i);
			supportedPictureSizes += size.width + "x" + size.height;
			if (i < supportedPictureSizesList.size() - 1)
				supportedPictureSizes += "/";
		}
		//读取支持的预览尺寸
		Size previewSizeWH = cameraParameters.getPreviewSize();
		String previewSize = previewSizeWH.width + "x" + previewSizeWH.height;
		List<Size> supportedPreviewSizesList = cameraParameters.getSupportedPreviewSizes();
		for (int i = 0; i < supportedPreviewSizesList.size(); i++) {
			Size size = supportedPreviewSizesList.get(i);
			supportedPreviewSizes += size.width + "x" + size.height;
			if (i < supportedPreviewSizesList.size() - 1)
				supportedPreviewSizes += "/";
		}
		//读取支持的Jpeg缩略图尺寸
		Size JpegThumbnailSizeWH = cameraParameters.getJpegThumbnailSize();
		String jpegThumbnailSize = JpegThumbnailSizeWH.width + "x" + JpegThumbnailSizeWH.height;
		List<Size> supportedJpegThumbnailSizesList = cameraParameters.getSupportedJpegThumbnailSizes();
		for (int i = 0; i < supportedJpegThumbnailSizesList.size(); i++) {
			Size size = supportedJpegThumbnailSizesList.get(i);
			supportedJpegThumbnailSizes += size.width + "x" + size.height;
			if (i < supportedJpegThumbnailSizesList.size() - 1)
				supportedJpegThumbnailSizes  += "/";
		}	
		//读取支持的预览格式
		List<Integer> supportedPreviewFormatsLists = cameraParameters.getSupportedPreviewFormats();
		for (int i = 0; i < supportedPreviewFormatsLists.size(); i++) {
			switch (supportedPreviewFormatsLists.get(i)) {
				case ImageFormat.JPEG: 
					supportedPreviewFormats += "JPEG";
					break;
				case ImageFormat.NV16:
					supportedPreviewFormats += "NV16";
					break;
				case ImageFormat.NV21:
					supportedPreviewFormats += "NV21";
					break;
				case ImageFormat.RGB_565:
					supportedPreviewFormats += "RGB_565";
					break;
				case ImageFormat.UNKNOWN:
					supportedPreviewFormats += "unknown";
					break;
				case ImageFormat.YUY2:
					supportedPreviewFormats += "YUY2";
					break;
				case ImageFormat.YV12:
					supportedPreviewFormats += "YV12";
					break;
				default: break;
			}
			if (i < supportedPreviewFormatsLists.size() - 1)
				supportedPreviewFormats += "/";
		}
		//读取支持的图片格式
		List<Integer> supportedPictureFormatsList = cameraParameters.getSupportedPictureFormats();
		for (int i = 0; i < supportedPictureFormatsList.size(); i++) {
			switch (supportedPictureFormatsList.get(i)) {
				case ImageFormat.JPEG: 
					supportedPictureFormats += "JPEG";
					break;
				case ImageFormat.NV16:
					supportedPictureFormats += "NV16";
					break;
				case ImageFormat.NV21:
					supportedPictureFormats += "NV21";
					break;
				case ImageFormat.RGB_565:
					supportedPictureFormats += "RGB_565";
					break;
				case ImageFormat.UNKNOWN:
					supportedPictureFormats += "unknown";
					break;
				case ImageFormat.YUY2:
					supportedPictureFormats += "YUY2";
					break;
				case ImageFormat.YV12:
					supportedPictureFormats += "YV12";
					break;
				default: break;
			}
			if (i < supportedPictureFormatsList.size() - 1)
				supportedPictureFormats += "/";
		}
		//读取支持的预览帧速率
		List<Integer> supportedPreviewFrameRatesList = cameraParameters.getSupportedPreviewFrameRates();
		for (int i = 0; i < supportedPreviewFrameRatesList.size(); i++) {
			supportedPreviewFrameRates += supportedPreviewFrameRatesList.get(i);
			if (i < supportedPreviewFrameRatesList.size() - 1)
				supportedPreviewFrameRates += "/";
		}
		//读取支持的色彩表现
		List<String> supportedColorEffectList = cameraParameters.getSupportedColorEffects();
		for (int i = 0; i < supportedColorEffectList.size(); i++) {
			supportedColorEffect += supportedColorEffectList.get(i);
			if (i < supportedColorEffectList.size() - 1)
				supportedColorEffect += "/";
		}
		//读取支持的对焦模式
		List<String> supportedFocusModesList = cameraParameters.getSupportedFocusModes();
		for (int i = 0; i < supportedFocusModesList.size(); i++) {
			supportedFocusModes += supportedFocusModesList.get(i);
			if (i < supportedFocusModesList.size() - 1)
				supportedFocusModes += "/";
		}
		//读取支持的闪光灯模式
		List<String> supportedFlashModesList = cameraParameters.getSupportedFlashModes();
		if (supportedFlashModesList != null) {
			for (int i = 0; i < supportedFlashModesList.size(); i++) {
				supportedFlashModes += supportedFlashModesList.get(i);
				if (i < supportedFlashModesList.size() - 1)
					supportedFlashModes += "/";
			}
		} else {
			supportedFlashModes = "不支持闪光灯";
		}
		//读取支持的白平衡
		List<String> supportedWhiteBalanceList = cameraParameters.getSupportedWhiteBalance();
		for (int i = 0; i < supportedWhiteBalanceList.size(); i++) {
			supportedWhiteBalance += supportedWhiteBalanceList.get(i);
			if (i < supportedWhiteBalanceList.size() - 1)
				supportedWhiteBalance += "/";
		}
		//读取支持的边缘锐化
		List<String> supportedAntibandingList = cameraParameters.getSupportedAntibanding();
		for (int i = 0; i < supportedAntibandingList.size(); i++) {
			supportedAntibanding += supportedAntibandingList.get(i);
			if (i < supportedAntibandingList.size() - 1)
				supportedAntibanding += "/";
		}
		//读取支持的场景模式
		List<String> supportedSceneModesList = cameraParameters.getSupportedSceneModes();
		for (int i = 0; i < supportedSceneModesList.size(); i++) {
			supportedSceneModes += supportedSceneModesList.get(i);
			if (i < supportedSceneModesList.size() - 1)
				supportedSceneModes += "/";
		}
		
		back_camera_preview_format.setText(previewFormat);
		back_camera_supported_preview_formats.setText(supportedPreviewFormats);
		back_camera_preview_size.setText(previewSize);
		back_camera_supported_preview_sizes.setText(supportedPreviewSizes);
		back_camera_preview_framerate.setText(cameraParameters.getPreviewFrameRate() + "FPS");
		back_camera_supported_preview_framerates.setText(supportedPreviewFrameRates + "FPS");
		
		back_camera_jpeg_quality.setText(cameraParameters.getJpegQuality() + "%");
		back_camera_jpeg_thumbnail_quality.setText(cameraParameters.getJpegThumbnailQuality() + "%");
		back_camera_jpeg_thumbnail_size.setText(jpegThumbnailSize);
		back_camera_supported_jpeg_thumbnail_sizes.setText(supportedJpegThumbnailSizes);
		
		back_camera_picture_format.setText(pictureFormat);
		back_camera_supported_picture_formats.setText(supportedPictureFormats);
		back_camera_picture_size.setText(pictureSize);
		back_camera_supported_picture_sizes.setText(supportedPictureSizes);
		
		back_camera_focal_length.setText(cameraParameters.getFocalLength() + "");
		back_camera_zoom.setText(cameraParameters.getZoom() + "x");
		back_camera_maxzoom.setText(cameraParameters.getMaxZoom() + "x");
		back_camera_focus_mode.setText(cameraParameters.getFocusMode());
		back_camera_supported_focus_mode.setText(supportedFocusModes);
		
		back_camera_exposure_compensation.setText(cameraParameters.getExposureCompensation() + "");
		back_camera_exposure_compensation_step.setText(cameraParameters.getExposureCompensationStep() + "");
		back_camera_max_exposure_compensation.setText(cameraParameters.getMaxExposureCompensation() + "");
		back_camera_min_exposure_compensation.setText(cameraParameters.getMinExposureCompensation() + "");
		
		back_camera_horizontal_view_angle.setText(cameraParameters.getHorizontalViewAngle() + "°");
		back_camera_vertical_view_angle.setText(cameraParameters.getVerticalViewAngle() + "°");
		back_camera_flash_mode.setText(flashMode);
		back_camera_supported_flash_modes.setText(supportedFlashModes);
		back_camera_white_balance.setText(cameraParameters.getWhiteBalance());
		back_camera_supported_white_balance.setText(supportedWhiteBalance);
		
		back_camera_color_effect.setText(cameraParameters.getColorEffect());
		back_camera_supported_color_effect.setText(supportedColorEffect);
		back_camera_antibanding.setText(cameraParameters.getAntibanding());
		back_camera_supported_antibanding.setText(supportedAntibanding);
		back_camera_scene_mode.setText(cameraParameters.getSceneMode());
		back_camera_supported_scene_modes.setText(supportedSceneModes);
							
	  }

}
