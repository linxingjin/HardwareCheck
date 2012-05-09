package com.lenovo.check.microphone;

import java.text.DecimalFormat;

import android.R.color;
import android.R.integer;
import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.TextView;

import com.lenovo.check.R;

public class MicrophoneInformation extends Activity {

	private MicrophoneView microphone_voice_dynamic_view;
	private TextView microphone_voice_format;
	private TextView microphone_voice_source;
	private TextView microphone_voice_channel_config;
	private TextView microphone_voice_channel_count;
	private TextView microphone_voice_min_buffer_size;
	private TextView microphone_voice_sample_rate;
	private TextView microphone_voice_state;
	
	private String dataFormat;
	private String dataSource;
	private String channelConfig;
	private RecordThread recordThread;

	private AudioRecord audioRecord;
	private String dataForm = "0.000000000";
	private DecimalFormat df;
	private short[] records;
	private String d;
	private int e;
	private int g;
	private float h;
	private float i;
	private float j;
	private float k;
	private float l = 60.0F;
	private float m = 3.0F;
	private float n = 30.0F;
	private long o;
	private short[] p;
	private int q;
	private int r;
	private int s;
	private int t;
	private int u;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.microphone);

		microphone_voice_dynamic_view = (MicrophoneView) findViewById(R.id.microphone_voice_dynamic_view);
		microphone_voice_format = (TextView) findViewById(R.id.microphone_voice_format);
		microphone_voice_source = (TextView) findViewById(R.id.microphone_voice_source);
		microphone_voice_channel_config = (TextView) findViewById(R.id.microphone_voice_channel_config);
		microphone_voice_channel_count = (TextView) findViewById(R.id.microphone_voice_channel_count);
		microphone_voice_min_buffer_size = (TextView) findViewById(R.id.microphone_voice_min_buffer_size);
		microphone_voice_sample_rate = (TextView) findViewById(R.id.microphone_voice_sample_rate);
		microphone_voice_state = (TextView) findViewById(R.id.microphone_voice_state);

		int minSize = AudioRecord.getMinBufferSize(44100,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT);
		System.out.println(minSize);
		audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, minSize);
		switch (audioRecord.getAudioFormat()) {
		case 0:
			dataForm = "invalid";
			break;
		case 1:
			dataForm = "default";
			break;
		case 2:
			dataForm = "PCM_16 bits";
			break;
		case 3:
			dataForm = "PCM_8 bits";
			break;
		default:
			dataForm = "unkown";
			break;
		}
		switch (audioRecord.getAudioSource()) {
		case 0:
			dataSource = "default";
			break;
		case 1:
			dataSource = "microphone";
			break;
		case 2:
			dataSource = "uplink";
			break;
		case 3:
			dataSource = "downlink";
			break;
		case 4:
			dataSource = "call";
			break;
		case 5:
			dataSource = "camcorder";
			break;
		case 6:
			dataSource = "recognition";
			break;
		case 7:
			dataSource = "communication";
			break;
		default:
			dataSource = "unkown";
			break;
		}
		switch (audioRecord.getChannelConfiguration()) {
		case 0:
			channelConfig = "invalid";
			break;
		case 1:
			channelConfig = "default";
			break;
		case 2:
			channelConfig = "mono";
			break;
		case 3:
			channelConfig = "stereo";
			break;
		case 4:
			channelConfig = "left";
			break;
		case 8:
			channelConfig = "right";
			break;
		case 12:
			channelConfig = "stereo";
			break;
		case 16:
			channelConfig = "mono";
			break;
		case 32:
			channelConfig = "back";
			break;
		case 64:
			channelConfig = "left_processed";
			break;
		case 128:
			channelConfig = "right_processed";
			break;
		case 256:
			channelConfig = "front_processed";
			break;
		case 512:
			channelConfig = "back_processed";
			break;
		case 1024:
			channelConfig = "pressure";
			break;
		case 2048:
			channelConfig = "x_axis";
			break;
		case 4096:
			channelConfig = "y_axis";
			break;
		case 8192:
			channelConfig = "z_axis";
			break;
		case 32768:
			channelConfig = "voice_dnlink";
			break;
		case 16384:
			channelConfig = "voice_uplink";
			break;
		default:
			channelConfig = "unkown";
			break;
		}
		
		microphone_voice_format.setText(dataForm);
		microphone_voice_source.setText(dataSource);
		microphone_voice_channel_config.setText(channelConfig);
		microphone_voice_channel_count.setText(String.valueOf(audioRecord.getChannelCount()));
		microphone_voice_min_buffer_size.setText(minSize + " bytes");
		microphone_voice_sample_rate.setText(audioRecord.getSampleRate() + " Hz");
		
		int ready = audioRecord.getState();
		switch (ready) {
		case 0:
			microphone_voice_state.setText("not ready");
			break;
		case 1:
			microphone_voice_state.setText("is ready");
		default:
			break;
		}
		df = new DecimalFormat(dataForm);
//		recordThread = new RecordThread();
//		recordThread.start();
		PhoneRunnable phoneRunnable = new PhoneRunnable();
		new Thread(phoneRunnable).start();
		
	}

	public void getAudioRecord() {
		try {
			this.audioRecord.startRecording();
			this.g = this.audioRecord.read(this.p, 0, this.u);
			this.j = 0.0F;
			this.k = 0.0F;
			int i1 = 0;
			while (i1 < this.g) {
				this.h = this.p[i1];
				this.k = (Math.abs(this.h) + this.k);
				i1++;
			}
			this.i = (this.k / this.u);
//			MicrophoneView localMicrofonoView = this.microphone_voice_dynamic_view;
			long l1 = this.o;
			float f1 = this.i;
			if (microphone_voice_dynamic_view.d != null)
				microphone_voice_dynamic_view.d[microphone_voice_dynamic_view.b] = f1;
			if (microphone_voice_dynamic_view.e != null) {
				microphone_voice_dynamic_view.e[microphone_voice_dynamic_view.b] = l1;
				for (int i4 = 0; i4 < 4096; i4++)
					microphone_voice_dynamic_view.e[i4] = 0L;
				microphone_voice_dynamic_view.d = new float[4096];
				for (int i2 = 0; i2 < 4096; i2++)
					microphone_voice_dynamic_view.d[i2] = 0.0F;
			} else {
				microphone_voice_dynamic_view.e = new long[4096];
			}
			for (int i3 = 0; i3 < 5; i3++) {
				microphone_voice_dynamic_view.d[(i3 + (1 + microphone_voice_dynamic_view.b))] = 0.0F;
			}
			microphone_voice_dynamic_view.a = (1 + microphone_voice_dynamic_view.a);
			if (microphone_voice_dynamic_view.a == 2) {
				microphone_voice_dynamic_view.invalidate();
				microphone_voice_dynamic_view.a = 0;
			}
			microphone_voice_dynamic_view.b = (1 + microphone_voice_dynamic_view.b);
			if (microphone_voice_dynamic_view.b > microphone_voice_dynamic_view.c)
				microphone_voice_dynamic_view.b = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.audioRecord.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class PhoneRunnable implements Runnable{

		@Override
		public void run() {
			getAudioRecord();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		audioRecord.release();
//		if(recordThread != null){
//			recordThread.pause();
//		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		getAudioRecord();
//		recordThread = new RecordThread();
//		recordThread.start();
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		audioRecord.release();
//		if(recordThread != null){
//			recordThread.interrupt();
//		}
	}
}
