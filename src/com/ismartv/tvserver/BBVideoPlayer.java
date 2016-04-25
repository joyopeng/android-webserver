package com.ismartv.tvserver;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

public class BBVideoPlayer extends Activity implements
		OnGetGeoCoderResultListener {

	public LocationClient mLocationClient;
	public GeofenceClient mGeofenceClient;
	public MyLocationListener mMyLocationListener;

	// GeoCoder mPoiSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mLocationClient = new LocationClient(BBVideoPlayer.this);
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		mGeofenceClient = new GeofenceClient(getApplicationContext());
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		option.setAddrType("all");
		option.setOpenGps(false);
		option.setProdName("ismartv");
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		// mPoiSearch.setOnGetGeoCodeResultListener(this);
		int resultCode = mLocationClient.requestLocation();
		Log.v("BaiduLocationApiDem", "resultCode =" + resultCode);
	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location.getAddrStr() != null)
				mLocationClient.stop();
			else
				mLocationClient.requestLocation();
			// mPoiSearch = GeoCoder.newInstance();
			// LatLng latLng=new
			// LatLng(location.getLatitude(),location.getLongitude());
			// mPoiSearch.reverseGeoCode(new
			// ReverseGeoCodeOption().location(latLng));
			// Receive Location
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\ncity : ");
			sb.append(location.getCity());
			sb.append("\nstreet : ");
			sb.append(location.getStreet());
			sb.append("\ndistrict : ");
			sb.append(location.getDistrict());
			sb.append("\nfloor : ");
			sb.append(location.getFloor());
			sb.append("\npoi : ");
			sb.append(location.getAddrStr());
			sb.append("\nprovince : ");
			sb.append(location.getProvince());
			sb.append("\ncityCode : ");
			sb.append(location.getCityCode());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			Log.i("BaiduLocationApiDem", sb.toString());
		}

	}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult arg0) {
		// TODO Auto-generated method stub
		Log.v("", arg0.getAddress());
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
		// TODO Auto-generated method stub

	}
}