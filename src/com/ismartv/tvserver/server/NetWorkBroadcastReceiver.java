package com.ismartv.tvserver.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by <huaijiefeng@gmail.com> on 9/3/14.
 */
public class NetWorkBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "aaaa";
    NetworkInfo.State wifiState = null;
    NetworkInfo.State ethernetState = null;
    public static final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
    	Log.v(TAG, ACTION);
        if (ACTION.equals(intent.getAction())) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            ethernetState = cm.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET).getState();
            if (NetworkInfo.State.CONNECTED == wifiState || NetworkInfo.State.CONNECTED == ethernetState) {
                Log.v(TAG, "tv network connect success!!!");
            }else{
                Log.v(TAG, "can't connect network!!!");
            }
        }
    }
}
