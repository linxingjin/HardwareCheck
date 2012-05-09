package com.lenovo.check.microphone;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

public class RecordThread extends Thread {
    private AudioRecord ar;
    private int bs;
    private boolean isRun = false;
 
    public RecordThread() {
        super();
        bs = AudioRecord.getMinBufferSize(44100,AudioFormat.CHANNEL_IN_MONO,AudioFormat.ENCODING_PCM_16BIT);
        ar = new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bs);
    }
 
    public void run() {
        super.run();
        ar.startRecording();
                // ���ڶ�ȡ�� buffer
        byte[] buffer = new byte[bs];
        isRun = true;
        while (isRun) {
            int r = ar.read(buffer, 0, bs);
            int v = 0;
                        // �� buffer ����ȡ��������ƽ��������
            for (int i = 0; i < buffer.length; i++) {
                // ����û����������Ż���Ϊ�˸���������չʾ����
                v += buffer[i] * buffer[i];
            }
            // ƽ���ͳ��������ܳ��ȣ��õ�������С�����Ի�ȡ������ֵ��Ȼ���ʵ�ʲ������б�׼����
            // ��������������ֵ���в����������� sendMessage �����׳����� Handler ����д�����
            Log.d("spl", String.valueOf(v / (float) r));
            System.out.println(String.valueOf(v / (float) r) + " aaaaaaaaaaa");
// ������Ϊ��ͨ�����ԣ�����ͨ��������εĲ�ֵ��ȡ�������������Щ���������⡣
        }
        ar.stop();
    }
 
    public void pause() {
                // �ڵ��ñ��̵߳� Activity �� onPause ����ã��Ա� Activity ��ͣʱ�ͷ���˷�
        isRun = false;
    }
 
    public void start() {
                // �ڵ��ñ��̵߳� Activity �� onResume ����ã��Ա� Activity �ָ��������ȡ��˷���������
        if (!isRun) {
            super.start();
        }
    }
}