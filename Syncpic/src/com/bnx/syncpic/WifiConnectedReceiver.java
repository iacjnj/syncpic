package com.bnx.syncpic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class WifiConnectedReceiver extends BroadcastReceiver {
	private final String TAG = this.getClass().getSimpleName();
	private static final boolean DBG = BuildConfig.DEBUG;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		WifiManager manger = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (DBG)
			Log.i(TAG, " action is " + action);
        // ����WIFI״̬�仯
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)) {
            //WIFI�Ƿ��
            boolean flag = manger.isWifiEnabled();
			if (DBG)
				Log.i(TAG, " wifi is enabled? " + flag);
			if (flag) {
				// Toast.makeText(context, "WIFI enabled", Toast.LENGTH_LONG)
				// .show();
				ConnectivityManager connectMgr = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo wifiNetInfo = connectMgr
						.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                //WIFI�Ƿ����ӳɹ�
                if (wifiNetInfo.isConnected() ) {
					if (DBG)
						Log.i(TAG, " wifi is connected ");
					// ����ע�⿴����� ��������� WIFI���ӳɹ��ܼ�������
					// ���ǻ���˾�ĴΡ�����Ҫ��ô�����ɹ�֮��Ż���˾һ�Ρ�
					Toast.makeText(context,
							"WIFI�Ѿ�������:" + manger.getConnectionInfo().getSSID(),
							Toast.LENGTH_LONG)
							.show();
                
                } else {
					Log.i(TAG, " wifi is NOT connected ");
					Toast.makeText(context, "WIFI�����쳣", Toast.LENGTH_LONG)
							.show();
                }
			} else {
				Toast.makeText(context, "WIFI disabled", Toast.LENGTH_LONG)
						.show();
			}
		} else if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
			// WIFI�Ƿ��
			boolean flag = manger.isWifiEnabled();
			if (DBG)
				Log.i(TAG, " wifi is enabled? " + flag);
			if (flag) {

            }
        }
	}

}
