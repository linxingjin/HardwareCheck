package com.lenovo.check.gsm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lenovo.check.R;

public class GSMInformation extends Activity {

	private TextView gsm_mobile_type;
	private TextView gsm_telephone_number;
	private TextView gsm_imei;
	private TextView gsm_imsi;
	private TextView gsm_sim_country;
	private TextView gsm_sim_serial_number;
	private TextView gsm_sim_state;
	private TextView gsm_software_version;
	private TextView gsm_network_operator_number;
	private TextView gsm_network_operator_name;
//	private TextView gsm_telephone_type;
	private TextView gsm_location;
	private TextView gsm_roame_state;
	private TextView gsm_email_number;
	private TextView gsm_email_type;
	private TextView gsm_neighbor_number;
	private TextView gsm_locate_area_number;
	private TextView gsm_area_network_type;
	private TextView gsm_area_strength;
	private TextView gsm_active_data;
	private TextView gsm_data_state;
	private TextView gsm_call_state;
	private ListView gsm_nearby_msg_list;

	private TelephonyManager teleManager;
	private List<NeighboringCellInfo> neigInfo;

	private final static String DATA_ACTIVITY_IN_STR = "in";
	private final static String DATA_ACTIVITY_OUT_STR = "out";
	private final static String DATA_ACTIVITY_INOUT_STR = "inout";
	private final static String DATA_ACTIVITY_NONE_STR = "none";
	private final static String DATA_ACTIVITY_DORMANT_STR = "dormant";
	private final static String DATA_CONNECTED_STR = "disconnected";
	private final static String DATA_CONNECTING_STR = "connecting";
	private final static String DATA_DISCONNECTED_STR = "connected";
	private final static String DATA_SUSPENDED_STR = "suspended";
	private final static String UNKOWN_STR = "unkown";
	private final static String NETWORK_TYPE_UNKNOWN = "unkown";
	private final static String NETWORK_TYPE_GPRS = "GPRS";
	private final static String NETWORK_TYPE_EDGE = "EDGE";
	private final static String NETWORK_TYPE_UMTS = "UMTS";
	private final static String NETWORK_TYPE_CDMA = "CDMA";
	private final static String NETWORK_TYPE_EVDO_0 = "EVDO_0";
	private final static String NETWORK_TYPE_EVDO_A = "EVDO_A";
	private final static String NETWORK_TYPE_1xRTT = "1xRTT";
	private final static String NETWORK_TYPE_HSDPA = "HSDPA";
	private final static String NETWORK_TYPE_HSUPA = "HSUPA";
	private final static String NETWORK_TYPE_HSPA = "HSPA";
	private final static String NETWORK_TYPE_IDEN = "IDEN";
	private final static String NETWORK_TYPE_EVDO_B = "EVDO_B";
	private final static String NETWORK_TYPE_LTE = "LTE";
	private final static String NETWORK_TYPE_EHRPD = "EHRPD";
	private final static String NETWORK_TYPE_HSPAP = "HSPAP";

	private String phoneType = null;
	private String simState = null;
	private String networkType = null;
	private String dateActivity = null;
	private String dateState = null;
	private String callState = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gsm);

		gsm_mobile_type = (TextView) findViewById(R.id.gsm_mobile_type);
		gsm_telephone_number = (TextView) findViewById(R.id.gsm_telephone_number);
		gsm_imei = (TextView) findViewById(R.id.gsm_imei);
		gsm_imsi = (TextView) findViewById(R.id.gsm_imsi);
		gsm_sim_country = (TextView) findViewById(R.id.gsm_sim_country);
		gsm_sim_serial_number = (TextView) findViewById(R.id.gsm_sim_serial_number);
		gsm_sim_state = (TextView) findViewById(R.id.gsm_sim_state);
		gsm_software_version = (TextView) findViewById(R.id.gsm_software_version);
		gsm_network_operator_number = (TextView) findViewById(R.id.gsm_network_operator_number);
		gsm_network_operator_name = (TextView) findViewById(R.id.gsm_network_operator_name);
//		gsm_telephone_type = (TextView) findViewById(R.id.gsm_telephone_type);
		gsm_location = (TextView) findViewById(R.id.gsm_location);
		gsm_roame_state = (TextView) findViewById(R.id.gsm_roame_state);
		gsm_email_number = (TextView) findViewById(R.id.gsm_email_number);
		gsm_email_type = (TextView) findViewById(R.id.gsm_email_type);
		gsm_neighbor_number = (TextView) findViewById(R.id.gsm_neighbor_number);
		gsm_locate_area_number = (TextView) findViewById(R.id.gsm_locate_area_number);
		gsm_area_network_type = (TextView) findViewById(R.id.gsm_area_network_type);
		gsm_area_strength = (TextView) findViewById(R.id.gsm_area_strength);
		gsm_active_data = (TextView) findViewById(R.id.gsm_active_data);
		gsm_data_state = (TextView) findViewById(R.id.gsm_data_state);
		gsm_call_state = (TextView) findViewById(R.id.gsm_call_state);
		gsm_nearby_msg_list = (ListView) findViewById(R.id.gsm_nearby_msg_list);

		 this.getGSMInfo();
	}

	public void getGSMInfo() {

		teleManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		// 电话类型
		switch (teleManager.getPhoneType()) {
		case TelephonyManager.PHONE_TYPE_NONE:
			phoneType = "none";
			break;
		case TelephonyManager.PHONE_TYPE_GSM:
			phoneType = "GSM";
			break;
		case TelephonyManager.PHONE_TYPE_CDMA:
			phoneType = "CDMA";
			break;
		default:
			break;
		}
		// SIM卡状态
		switch (teleManager.getSimState()) {
		case TelephonyManager.SIM_STATE_UNKNOWN:
			simState = "unknown";
			break;
		case TelephonyManager.SIM_STATE_ABSENT:
			simState = "absent";
			break;
		case TelephonyManager.SIM_STATE_PIN_REQUIRED:
			simState = "pin required";
			break;
		case TelephonyManager.SIM_STATE_PUK_REQUIRED:
			simState = "puk required";
			break;
		case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
			simState = "newwork locked";
			break;
		case TelephonyManager.SIM_STATE_READY:
			simState = "ready";
			break;
		default:
			break;
		}
		// 网络类型
		switch (teleManager.getNetworkType()) {
			case TelephonyManager.NETWORK_TYPE_UNKNOWN:
				networkType = "unknown";
				break;
			case TelephonyManager.NETWORK_TYPE_GPRS:
				networkType = "GPRS";
				break;
			case TelephonyManager.NETWORK_TYPE_EDGE:
				networkType = "EDGE";
				break;
			case TelephonyManager.NETWORK_TYPE_UMTS:
				networkType = "UMTS";
				break;
			case TelephonyManager.NETWORK_TYPE_CDMA:
				networkType = "CDMA";
				break;
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
				networkType = "EVDO_0";
				break;
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
				networkType = "EVDO_A";
				break;
			case TelephonyManager.NETWORK_TYPE_1xRTT:
				networkType = "1xRTT";
				break;
			case TelephonyManager.NETWORK_TYPE_HSDPA:
				networkType = "HSDPA";
				break;
			case TelephonyManager.NETWORK_TYPE_HSUPA:
				networkType = "HSUPA";
				break;
			case TelephonyManager.NETWORK_TYPE_HSPA:
				networkType = "HSPA";
				break;
			case TelephonyManager.NETWORK_TYPE_IDEN:
				networkType = "IDEN";
				break;
			case TelephonyManager.NETWORK_TYPE_EVDO_B:
				networkType = "EVDO_B";
				break;
			default:
				break;
		}
		// 数据活动
		switch (teleManager.getDataActivity()) {
			case TelephonyManager.DATA_ACTIVITY_NONE:
				dateActivity = "none";
				break;
			case TelephonyManager.DATA_ACTIVITY_IN:
				dateActivity = "in";
				break;
			case TelephonyManager.DATA_ACTIVITY_OUT:
				dateActivity = "out";
				break;
			case TelephonyManager.DATA_ACTIVITY_INOUT:
				dateActivity = "inout";
				break;
			case TelephonyManager.DATA_ACTIVITY_DORMANT:
				dateActivity = "dormant";
				break;
			default:
				break;
		}
		// 数据状态
		switch (teleManager.getDataState()) {
			case TelephonyManager.DATA_DISCONNECTED:
				dateState = "disconnected";
				break;
			case TelephonyManager.DATA_CONNECTING:
				dateState = "connecting";
				break;
			case TelephonyManager.DATA_CONNECTED:
				dateState = "connected";
				break;
			case TelephonyManager.DATA_SUSPENDED:
				dateState = "suspended";
				break;
			default:
				break;
		}
		// 呼叫状态
		switch (teleManager.getCallState()) {
			case TelephonyManager.CALL_STATE_IDLE:
				callState = "idle";
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				callState = "ringing";
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				callState = "offhook";
				break;
			default:
				break;
		}
		gsm_mobile_type.setText(phoneType);
		gsm_telephone_number.setText(teleManager.getLine1Number() != null ? teleManager.getLine1Number() : UNKOWN_STR);
		gsm_imei.setText(teleManager.getDeviceId()!=null ? teleManager.getDeviceId():UNKOWN_STR);
		gsm_imsi.setText(teleManager.getSubscriberId()!=null ? teleManager.getSubscriberId():UNKOWN_STR);// 返回用户唯一标识
		gsm_sim_country.setText(teleManager.getSimCountryIso()!=null ? teleManager.getSimCountryIso():UNKOWN_STR);
		gsm_sim_serial_number.setText(teleManager.getSimSerialNumber()!=null ? teleManager.getSimSerialNumber():UNKOWN_STR);
		gsm_sim_state.setText(simState);
		gsm_software_version.setText(teleManager.getDeviceSoftwareVersion() != null ? teleManager.getDeviceSoftwareVersion() : UNKOWN_STR);
		gsm_network_operator_number.setText(teleManager.getNetworkOperator()!=null ? teleManager.getNetworkOperator():UNKOWN_STR);
		gsm_network_operator_name.setText(teleManager.getNetworkOperatorName()!=null ? teleManager.getNetworkOperatorName():UNKOWN_STR);
//		gsm_telephone_type.setText(phoneType);
		gsm_roame_state.setText(String.valueOf((teleManager.isNetworkRoaming()))!=null ? String.valueOf(teleManager.isNetworkRoaming()):UNKOWN_STR);
		gsm_email_number.setText(teleManager.getVoiceMailAlphaTag()!=null ? teleManager.getVoiceMailAlphaTag():UNKOWN_STR);
		gsm_email_type.setText(teleManager.getVoiceMailNumber()!=null ? teleManager.getVoiceMailNumber():UNKOWN_STR);

		CellLocation location = teleManager.getCellLocation();//更新位置信息
		location.requestLocationUpdate();
		gsm_location.setText(location!=null ? location.toString():UNKOWN_STR);
		gsm_active_data.setText(dateActivity);
		gsm_data_state.setText(dateState);
		gsm_call_state.setText(callState);

		neigInfo = teleManager.getNeighboringCellInfo();
		Iterator<NeighboringCellInfo> it = neigInfo.iterator();
		List<HashMap<String, Integer>> msgLists = new ArrayList<HashMap<String, Integer>>();
		while (it.hasNext()) {
			NeighboringCellInfo info = it.next();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("neighbor_number", info.getCid());
			map.put("locate_area_number", info.getLac());
			map.put("area_network_type", info.getNetworkType());
			map.put("area_strength", info.getRssi());
			map.put("area_scramble", info.getPsc());// 扰频
			msgLists.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, msgLists,
				R.layout.gsm_nearby_msg_item, new String[] { "neighbor_number",
						"locate_area_number", "area_network_type",
						"area_strength", "area_scramble" }, new int[] {
						R.id.gsm_neighbor_number, R.id.gsm_locate_area_number,
						R.id.gsm_area_network_type, R.id.gsm_area_strength,
						R.id.gsm_area_scramble });
		gsm_nearby_msg_list.setAdapter(adapter);
		this.setListViewHeightBasedOnChildren(gsm_nearby_msg_list);
	}

	/**
	 * 设置listview的高度解决scrollview与listview共存问题
	 */
	public void setListViewHeightBasedOnChildren(ListView listView) {
		SimpleAdapter simpleAdapter = (SimpleAdapter) listView.getAdapter();
		if (simpleAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < simpleAdapter.getCount(); i++) {
			View listItem = simpleAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight())
				* (simpleAdapter.getCount() - 1);
		((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
		listView.setLayoutParams(params);
	}
}
