package com.lenovo.check.altavoz;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.KeyEvent;
import android.view.View;

import com.lenovo.check.R;
import com.lenovo.check.ui.SystemVerification;
import com.lenovo.check.util.ProgressView;

public class AltavozInformation extends Activity implements View.OnClickListener{
	
	private Button btn_altavoz_inner;
//    private ProgressView altavoz_sys_volume;  
//    private ProgressView altavoz_conversation_volume;
//    private ProgressView altavoz_ring_volume;
//    private ProgressView altavoz_notification_volume;
//    private ProgressView altavoz_music_volume;
//    private ProgressView altavoz_alarm_volume;
//    private ProgressView altavoz_dtmf_volume;//　双音多频
	private ProgressBar altavoz_sys_volume;
	private ProgressBar altavoz_conversation_volume;
	private ProgressBar altavoz_ring_volume;
	private ProgressBar altavoz_notification_volume;
	private ProgressBar altavoz_music_volume;
	private ProgressBar altavoz_alarm_volume;
	private ProgressBar altavoz_dtmf_volume;// 　双音多频
	private TextView altavoz_sys_volume_percent;
	private TextView altavoz_conversation_volume_percent;
	private TextView altavoz_ring_volume_percent;
	private TextView altavoz_notification_volume_percent;
	private TextView altavoz_music_volume_percent;
	private TextView altavoz_alarm_volume_percent;
	private TextView altavoz_dtmf_volume_percent;
    private TextView altavoz_multimedia_chip;
    private TextView altavoz_alsa_version;//高级Linux声音架构
    private TextView altavoz_mode;
    private TextView altavoz_ringing_mode;
    private TextView altavoz_bluetooth_a2dp;
    private TextView altavoz_bluetooth_line_control;
    private TextView altavoz_bluetooth_sco;
    private TextView altavoz_mcriophone_quiet;
    private TextView altavoz_music_active;
    private TextView altavoz_speakphone;;
    private TextView altavoz_earphone;
    
    private AudioManager audioManager;
    private String ringerMode;
    private BroadcastReceiver audioReceiver;
    private IntentFilter audioFilter;
    private NumberFormat numberFormat;
    
    private final static String RINGER_MODE_NORMAL_STR = "normal";
    private final static String RINGER_MODE_SILENT_STR = "silent";
    private final static String RINGER_MODE_VIBRATE_STR = "vibrate";
    private final static String NORMAL_STR = "normal";
    private final static String RINGTONE_STR = "ringtone";
    private final static String IN_CALL_STR = "calling";
    private final static String IN_COMMUNICATION_STR = "communicating";
    private final static String MODE_UNKOWN_STR = "unkown";
    
    private Handler handler = new Handler();
    int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.altavoz);
		
		btn_altavoz_inner = (Button)findViewById(R.id.btn_altavoz_inner);
//	    altavoz_sys_volume = (ProgressView)findViewById(R.id.altavoz_sys_volume);
//	    altavoz_conversation_volume = (ProgressView)findViewById(R.id.altavoz_conversation_volume);
//	    altavoz_ring_volume = (ProgressView)findViewById(R.id.altavoz_ring_volume);
//	    altavoz_notification_volume = (ProgressView)findViewById(R.id.altavoz_notification_volume);
//	    altavoz_music_volume = (ProgressView)findViewById(R.id.altavoz_music_volume);
//	    altavoz_alarm_volume = (ProgressView)findViewById(R.id.altavoz_alarm_volume);
//	    altavoz_dtmf_volume = (ProgressView)findViewById(R.id.altavoz_dtmf_volume);
		altavoz_sys_volume = (ProgressBar)findViewById(R.id.altavoz_sys_volume);
	    altavoz_conversation_volume = (ProgressBar)findViewById(R.id.altavoz_conversation_volume);
	    altavoz_ring_volume = (ProgressBar)findViewById(R.id.altavoz_ring_volume);
	    altavoz_notification_volume = (ProgressBar)findViewById(R.id.altavoz_notification_volume);
	    altavoz_music_volume = (ProgressBar)findViewById(R.id.altavoz_music_volume);
	    altavoz_alarm_volume = (ProgressBar)findViewById(R.id.altavoz_alarm_volume);
	    altavoz_dtmf_volume = (ProgressBar)findViewById(R.id.altavoz_dtmf_volume);
	    altavoz_sys_volume_percent = (TextView)findViewById(R.id.altavoz_sys_volume_percent);
		altavoz_conversation_volume_percent = (TextView)findViewById(R.id.altavoz_conversation_volume_percent);
		altavoz_ring_volume_percent = (TextView)findViewById(R.id.altavoz_ring_volume_percent);
		altavoz_notification_volume_percent = (TextView)findViewById(R.id.altavoz_notification_volume_percent);
		altavoz_music_volume_percent = (TextView)findViewById(R.id.altavoz_music_volume_percent);
		altavoz_alarm_volume_percent = (TextView)findViewById(R.id.altavoz_alarm_volume_percent);
		altavoz_dtmf_volume_percent = (TextView)findViewById(R.id.altavoz_dtmf_volume_percent);
	    altavoz_multimedia_chip = (TextView)findViewById(R.id.altavoz_multimedia_chip); 
	    altavoz_alsa_version = (TextView)findViewById(R.id.altavoz_alsa_version);
	    altavoz_mode = (TextView)findViewById(R.id.altavoz_mode);
	    altavoz_ringing_mode = (TextView)findViewById(R.id.altavoz_ringing_mode);
	    altavoz_bluetooth_a2dp = (TextView)findViewById(R.id.altavoz_bluetooth_a2dp);
	    altavoz_bluetooth_line_control = (TextView)findViewById(R.id.altavoz_bluetooth_line_control);
	    altavoz_bluetooth_sco = (TextView)findViewById(R.id.altavoz_bluetooth_sco);
	    altavoz_mcriophone_quiet = (TextView)findViewById(R.id.altavoz_mcriophone_quiet);
	    altavoz_music_active = (TextView)findViewById(R.id.altavoz_music_active);
	    altavoz_speakphone = (TextView)findViewById(R.id.altavoz_speakphone);
	    altavoz_earphone = (TextView)findViewById(R.id.altavoz_earphone);
	    
	    btn_altavoz_inner.setOnClickListener(this);
	    
	    this.getAudioInfo();
//	    handler.post(new MyRunnable());
	    audioFilter = new IntentFilter();
	    audioFilter.addAction("android.intent.action.HEADSET_PLUG");
	    audioFilter.addAction("android.intent.action.MEDIA_BUTTON");
	    audioReceiver = new AltavozReceiver();
	    registerReceiver(audioReceiver, audioFilter);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(audioReceiver);
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(audioReceiver, audioFilter);
	}

//	private class MyRunnable implements Runnable{
//
//		@Override
//		public void run() {
//			audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
//			altavoz_sys_volume.drawProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM), audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM), "%");
//			altavoz_conversation_volume.drawProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL), audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL), "%");
//			altavoz_ring_volume.drawProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_RING), audioManager.getStreamVolume(AudioManager.STREAM_RING), "%");
//			altavoz_notification_volume.drawProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION), audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION), "%");
//			altavoz_music_volume.drawProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), audioManager.getStreamVolume(AudioManager.STREAM_MUSIC), "%");
//			altavoz_alarm_volume.drawProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM), audioManager.getStreamVolume(AudioManager.STREAM_ALARM), "%");
//			altavoz_dtmf_volume.drawProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_DTMF), audioManager.getStreamVolume(AudioManager.STREAM_DTMF), "%");
//			altavoz_multimedia_chip.setText("unkown");
//			altavoz_alsa_version.setText("");
//			
//			int mode = audioManager.getMode();
//			switch (mode) {
//			case 0:
//				altavoz_mode.setText(NORMAL_STR);
//				break;
//			case 1:
//				altavoz_mode.setText(RINGTONE_STR);
//			case 2:
//				altavoz_mode.setText(IN_CALL_STR);
//			case 3:
//				altavoz_mode.setText(IN_COMMUNICATION_STR);
//				break;
//			default:
//				altavoz_mode.setText(MODE_UNKOWN_STR);
//				break;
//			}
//			
//			int ringMode = audioManager.getRingerMode();
//			switch (ringMode) {
//			case 0:
//				altavoz_ringing_mode.setText(RINGER_MODE_SILENT_STR);
//				break;
//			case 1:
//				altavoz_ringing_mode.setText(RINGER_MODE_VIBRATE_STR);
//				break;
//			case 2:
//				altavoz_ringing_mode.setText(RINGER_MODE_NORMAL_STR);
//				break;
//			default:
//				altavoz_ringing_mode.setText(MODE_UNKOWN_STR);
//				break;
//			}
//			
//			Boolean a2dpBoolean = audioManager.isBluetoothA2dpOn();
//			altavoz_bluetooth_a2dp.setText(Boolean.toString(a2dpBoolean.booleanValue()));
//			Boolean lineBoolean = audioManager.isBluetoothScoAvailableOffCall();
//		    altavoz_bluetooth_line_control.setText(Boolean.toString(lineBoolean.booleanValue()));
//		    Boolean scoBoolean = audioManager.isBluetoothScoOn();
//		    altavoz_bluetooth_sco.setText(Boolean.toString(scoBoolean.booleanValue()));
//		    Boolean quietBoolean = audioManager.isMicrophoneMute();
//		    altavoz_mcriophone_quiet.setText(Boolean.toString(quietBoolean.booleanValue()));
//		    Boolean musicBoolean = audioManager.isMusicActive();
//		    altavoz_music_active.setText(Boolean.toString(musicBoolean.booleanValue()));
//		    Boolean speakBoolean = audioManager.isSpeakerphoneOn();
//		    altavoz_speakphone.setText(Boolean.toString(speakBoolean.booleanValue()));
//		    altavoz_earphone.setText("true");
//		}	
//		
//	}
	
	/**
	 * 获取聲音的基本信息
	 */
	private void getAudioInfo(){
		audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		numberFormat = NumberFormat.getPercentInstance();
		numberFormat.setMaximumFractionDigits(0);
		altavoz_sys_volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM));
		altavoz_conversation_volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL));
		altavoz_ring_volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_RING));
		altavoz_music_volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		altavoz_alarm_volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM));
		altavoz_dtmf_volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_DTMF));
		this.getVolumeProgressView();
		
		
		altavoz_multimedia_chip.setText("unkown");
		altavoz_alsa_version.setText("");
		
		int mode = audioManager.getMode();
		switch (mode) {
		case 0:
			altavoz_mode.setText(NORMAL_STR);
			break;
		case 1:
			altavoz_mode.setText(RINGTONE_STR);
		case 2:
			altavoz_mode.setText(IN_CALL_STR);
		case 3:
			altavoz_mode.setText(IN_COMMUNICATION_STR);
			break;
		default:
			altavoz_mode.setText(MODE_UNKOWN_STR);
			break;
		}
		
		int ringMode = audioManager.getRingerMode();
		switch (ringMode) {
		case 0:
			altavoz_ringing_mode.setText(RINGER_MODE_SILENT_STR);
			break;
		case 1:
			altavoz_ringing_mode.setText(RINGER_MODE_VIBRATE_STR);
			break;
		case 2:
			altavoz_ringing_mode.setText(RINGER_MODE_NORMAL_STR);
			break;
		default:
			altavoz_ringing_mode.setText(MODE_UNKOWN_STR);
			break;
		}
		
		Boolean a2dpBoolean = audioManager.isBluetoothA2dpOn();
		altavoz_bluetooth_a2dp.setText(Boolean.toString(a2dpBoolean.booleanValue()));
		Boolean lineBoolean = audioManager.isBluetoothScoAvailableOffCall();
	    altavoz_bluetooth_line_control.setText(Boolean.toString(lineBoolean.booleanValue()));
	    Boolean scoBoolean = audioManager.isBluetoothScoOn();
	    altavoz_bluetooth_sco.setText(Boolean.toString(scoBoolean.booleanValue()));
	    Boolean quietBoolean = audioManager.isMicrophoneMute();
	    altavoz_mcriophone_quiet.setText(Boolean.toString(quietBoolean.booleanValue()));
	    Boolean musicBoolean = audioManager.isMusicActive();
	    altavoz_music_active.setText(Boolean.toString(musicBoolean.booleanValue()));
	    Boolean speakBoolean = audioManager.isSpeakerphoneOn();
	    altavoz_speakphone.setText(Boolean.toString(speakBoolean.booleanValue()));
	    altavoz_earphone.setText("true");
	}

	@Override
	public void onClick(View v) {
		this.audioManager.playSoundEffect(0, -1.0F);
	}
	
	public class AltavozReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String actionStr = intent.getAction();
				if(actionStr.equals("android.intent.action.MEDIA_BUTTON")){
				getVolumeProgressView();
			}
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
			Intent intent = new Intent(AltavozInformation.this, SystemVerification.class);
			this.finish();
		}
		else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP){
			this.getVolumeProgressView();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * 获取音量大小的进度条显示
	 */
	private void getVolumeProgressView(){
		altavoz_notification_volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION));
		altavoz_sys_volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM));
		altavoz_conversation_volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL));
		altavoz_ring_volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_RING));
		altavoz_notification_volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_RING));
		altavoz_music_volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
		altavoz_alarm_volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_ALARM));
		altavoz_dtmf_volume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_ALARM));
		altavoz_sys_volume_percent.setText(numberFormat.format(altavoz_sys_volume.getProgress()*1.0 / audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM)).toString());
		altavoz_conversation_volume_percent.setText(numberFormat.format(altavoz_conversation_volume.getProgress()*1.0 / audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL)).toString());
		altavoz_ring_volume_percent.setText(numberFormat.format(altavoz_ring_volume.getProgress()*1.0 / audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)).toString());
		altavoz_notification_volume_percent.setText(numberFormat.format(altavoz_notification_volume.getProgress()*1.0 / audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION)).toString());
		altavoz_music_volume_percent.setText(numberFormat.format(altavoz_music_volume.getProgress()*1.0 / audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)).toString());
		altavoz_alarm_volume_percent.setText(numberFormat.format(altavoz_alarm_volume.getProgress()*1.0 / audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM)).toString());
		altavoz_dtmf_volume_percent.setText(numberFormat.format(altavoz_dtmf_volume.getProgress()*1.0 / audioManager.getStreamMaxVolume(AudioManager.STREAM_DTMF)).toString());
	}
}
