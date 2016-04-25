package com.ismartv.tvserver;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.VideoView;

public class WFSActivity extends Activity {

	private IsmatvVideoView video1;
	
	// private Intent intent;
	Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.v("aaaa", "handleMessage");
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initViews();
	}

	private void initViews() {
		video1=(IsmatvVideoView)findViewById(R.id.video1);
		parseAsset();
		video1.setVideoPath("/data/data/com.ismartv.tvserver/files/test.mp4");
		video1.start();
	}

	private void parseAsset() {
		try {
			InputStream inputStream = getAssets().open("test.mp4");
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			int ch;
			while ((ch = inputStream.read()) != -1) {
				bytestream.write(ch);
			}
			byte imgdata[] = bytestream.toByteArray();
			bytestream.close();
			File cacheDir = getFilesDir();
			File temfileName = new File(cacheDir.getAbsolutePath(),
					"test.mp4");
			if (!temfileName.exists())
				temfileName.createNewFile();
			FileOutputStream fout = openFileOutput("test.mp4",
					Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE);
			fout.write(imgdata);
			fout.flush();
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void roottest() {
		try {
			InputStream inputStream = getAssets().open("hello-jni");
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			int ch;
			while ((ch = inputStream.read()) != -1) {
				bytestream.write(ch);
			}
			byte imgdata[] = bytestream.toByteArray();
			bytestream.close();
			File cacheDir = getFilesDir();
			cacheDir.setExecutable(true);
			File temfileName = new File(cacheDir.getAbsolutePath(), "sutest");
			Log.v("aaaa", temfileName.getAbsolutePath());
			if (!temfileName.exists())
				temfileName.createNewFile();
			FileOutputStream fout = openFileOutput("sutest",
					Context.MODE_WORLD_READABLE);
			fout.write(imgdata);
			fout.flush();
			fout.close();
			File aaa = new File("data/data/com.ismartv.tvserver/files/sutest");
			aaa.setExecutable(true, true);
			// do_exec("su");
			// do_exec("chmod 777 data/data/com.ismartv.tvserver/files/sutest");
			do_exec("./data/data/com.ismartv.tvserver/files/sutest");
			// do_exec("mount -o remount rw system");
			// do_exec("su");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	String do_exec(String cmd) {
		String s = "/n";
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				s += line + "/n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.v("aaaa", e.getMessage());
			e.printStackTrace();
		}
		// text.setText(s);
		return cmd;
	}

	/** 获取当前IP地址 */
	private String getLocalIpAddress() {
		try {
			// 遍历网络接口
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				// 遍历IP地址
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					// 非回传地址时返回
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		Log.v("aaaa", "onKeyUp");
		if (myHandler.hasMessages(11))
			myHandler.removeMessages(11);
		myHandler.sendEmptyMessageDelayed(11, 2000);
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.v("aaaa", "onKeyDown");
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		// if (intent != null) {
		// stopService(intent);
		// }
		super.onBackPressed();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}