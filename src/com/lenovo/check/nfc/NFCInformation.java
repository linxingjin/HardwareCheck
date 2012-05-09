package com.lenovo.check.nfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.widget.TextView;

import com.lenovo.check.R;

public class NFCInformation extends Activity {

	private TextView nfc_support_promt;
	
	private NfcAdapter nfcAdapter;
	private PendingIntent pIntent;
	private IntentFilter[] filters;
	private String[][] techLists;
	private int count;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nfc);
		
		nfc_support_promt = (TextView)findViewById(R.id.nfc_support_promt);
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if(nfcAdapter == null){
			nfc_support_promt.setText("设备不支持NFC");
			finish();
			return;
		}
		if(!nfcAdapter.isEnabled()){
			nfc_support_promt.setText("请在系统设置中先开启NFC功能");
			finish();
			return;
		}
		
		pIntent = PendingIntent.getActivity(this, 0, new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		IntentFilter filter = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
		try {
			filter.addDataType("*/*");
		} catch (Exception e) {
			e.printStackTrace();
		}
		filters = new IntentFilter[]{filter,};
		techLists = new String[][]{new String[]{IsoDep.class.getName()},new String[]{NfcA.class.getName()}, new String[]{NfcB.class.getName()},
				new String[]{NfcF.class.getName()}, new String[]{NfcV.class.getName()}, new String[]{Ndef.class.getName()},
				new String[]{NdefFormatable.class.getName()}, new String[]{MifareClassic.class.getName()}, new String[]{MifareUltralight.class.getName()}};
	}

	@Override
	protected void onResume() {
		super.onResume();
		nfcAdapter.enableForegroundDispatch(this, pIntent, filters, techLists);
	}
	
	

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		nfc_support_promt.setText("发现" + ++count + "个" + intent);
	}

	@Override
	protected void onPause() {
		super.onPause();
		nfcAdapter.disableForegroundDispatch(this);
	}
	
}
