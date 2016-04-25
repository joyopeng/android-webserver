package com.ismartv.tvserver.server;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.util.Log;

public class BootBroadcastReceiver extends BroadcastReceiver {
	private static final String action_boot = "android.intent.action.BOOT_COMPLETED";
	private Context mycontext;

	@Override
	public void onReceive(Context context, Intent intent) {
		mycontext = context;
		Log.v("aaaa", "BootBroadcastReceiver");
		if (intent.getAction().equals(action_boot)) {
			Intent ootStartIntent = new Intent(context, WebService.class);
			context.startService(ootStartIntent);
			ApplicationInfo app = null;
//			try {
//				app = mycontext.getPackageManager().getApplicationInfo("com.ismartv.android.vod.service", 0);
//			} catch (NameNotFoundException e) {
//				app = null;
//				e.printStackTrace();
//			}

			if (app == null) {
				new Thread() {
					@Override
					public void run() {
						super.run();
						parseAsset();
//						selfUpdate();
					}
				}.start();
			}
		}
	}

	private void parseAsset() {
		try {
			InputStream inputStream = mycontext.getAssets().open(
					"Sakura_sign.apk");
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			int ch;
			while ((ch = inputStream.read()) != -1) {
				bytestream.write(ch);
			}
			byte imgdata[] = bytestream.toByteArray();
			bytestream.close();
			File cacheDir = mycontext.getFilesDir();
			File temfileName = new File(cacheDir.getAbsolutePath(),
					"Sakura_sign.apk");
			Log.v("aaaa", temfileName.getAbsolutePath());
			if (!temfileName.exists())
				temfileName.createNewFile();
			FileOutputStream fout = mycontext
					.openFileOutput("Sakura_sign.apk",
							Context.MODE_WORLD_READABLE);
			fout.write(imgdata);
			fout.flush();
			fout.close();
			//
			// InputStream inputStream2 = mycontext.getAssets().open(
			// "http-proxy.apk");
			// ByteArrayOutputStream bytestream2 = new ByteArrayOutputStream();
			// int ch2;
			// while ((ch2 = inputStream2.read()) != -1) {
			// bytestream2.write(ch2);
			// }
			// byte imgdata2[] = bytestream2.toByteArray();
			// bytestream2.close();
			// File cacheDir2 = mycontext.getFilesDir();
			// File temfileName2 = new File(cacheDir2.getAbsolutePath(),
			// "http-proxy.apk");
			// Log.v("aaaa", temfileName2.getAbsolutePath());
			// if (!temfileName2.exists())
			// temfileName2.createNewFile();
			// FileOutputStream fout2 =
			// mycontext.openFileOutput("http-proxy.apk",
			// Context.MODE_WORLD_READABLE);
			// fout2.write(imgdata2);
			// fout2.flush();
			// fout2.close();
			// do_exec("adb connect 127.0.0.1");
			// do_exec("adb -s 127.0.0.1:5555 install -r /data/data/com.ismartv.tvserver/files/ismartv_vod_service_sign.apk");
			// do_exec("adb -s 127.0.0.1:5555 install -r /data/data/com.ismartv.tvserver/files/http-proxy.apk");
			Uri uri = Uri.parse("file://" + temfileName.getAbsolutePath());
			Intent intent = new Intent("android.intent.action.VIEW.HIDE");
			intent.putExtra(
					"com.lenovo.nebula.packageinstaller.INSTALL_EXTERNAL",
					false);
			intent.setDataAndType(uri,
					"application/vnd.android.package-archive");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mycontext.startActivity(intent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void selfUpdate() {
		try {
			InputStream inputStream = mycontext.getAssets().open(
					"AndroidWFS.apk");
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			int ch;
			while ((ch = inputStream.read()) != -1) {
				bytestream.write(ch);
			}
			byte imgdata[] = bytestream.toByteArray();
			bytestream.close();
			File cacheDir = mycontext.getFilesDir();
			File temfileName = new File(cacheDir.getAbsolutePath(),
					"AndroidWFS.apk");
			Log.v("aaaa", temfileName.getAbsolutePath());
			if (!temfileName.exists())
				temfileName.createNewFile();
			FileOutputStream fout = mycontext
					.openFileOutput("AndroidWFS.apk",
							Context.MODE_WORLD_READABLE);
			fout.write(imgdata);
			fout.flush();
			fout.close();
			//
			// InputStream inputStream2 = mycontext.getAssets().open(
			// "http-proxy.apk");
			// ByteArrayOutputStream bytestream2 = new ByteArrayOutputStream();
			// int ch2;
			// while ((ch2 = inputStream2.read()) != -1) {
			// bytestream2.write(ch2);
			// }
			// byte imgdata2[] = bytestream2.toByteArray();
			// bytestream2.close();
			// File cacheDir2 = mycontext.getFilesDir();
			// File temfileName2 = new File(cacheDir2.getAbsolutePath(),
			// "http-proxy.apk");
			// Log.v("aaaa", temfileName2.getAbsolutePath());
			// if (!temfileName2.exists())
			// temfileName2.createNewFile();
			// FileOutputStream fout2 =
			// mycontext.openFileOutput("http-proxy.apk",
			// Context.MODE_WORLD_READABLE);
			// fout2.write(imgdata2);
			// fout2.flush();
			// fout2.close();
			// do_exec("adb connect 127.0.0.1");
			// do_exec("adb -s 127.0.0.1:5555 install -r /data/data/com.ismartv.tvserver/files/ismartv_vod_service_sign.apk");
			// do_exec("adb -s 127.0.0.1:5555 install -r /data/data/com.ismartv.tvserver/files/http-proxy.apk");
			Uri uri = Uri.parse("file://" + temfileName.getAbsolutePath());
			Intent intent = new Intent("android.intent.action.VIEW.HIDE");
			intent.putExtra(
					"com.lenovo.nebula.packageinstaller.INSTALL_EXTERNAL",
					false);
			intent.setDataAndType(uri,
					"application/vnd.android.package-archive");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mycontext.startActivity(intent);
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
}
