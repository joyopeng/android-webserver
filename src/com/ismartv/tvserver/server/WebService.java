package com.ismartv.tvserver.server;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.ismartv.android.vod.service.ISmartvNativeService;

public class WebService extends Service {

	public static final int PORT = 8099;
	public static final String WEBROOT = "/";

	private WebServer webServer;
	private ISmartvNativeService nativeservice;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Intent intent = new Intent("com.ismartv.android.vod.service.keymonitor");
		bindService(intent, mConnection, BIND_AUTO_CREATE);
		webServer = new WebServer(PORT, WEBROOT);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		webServer.setDaemon(true);
		webServer.start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		webServer.close();
		super.onDestroy();
		unbindService(mConnection);
	}

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			nativeservice = ISmartvNativeService.Stub.asInterface(service);
			webServer.setNativeService(nativeservice);
		}

		public void onServiceDisconnected(ComponentName className) {
		}
	};

}
