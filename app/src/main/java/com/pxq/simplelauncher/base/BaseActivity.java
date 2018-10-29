package com.pxq.simplelauncher.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

public class BaseActivity extends Activity{
	
	public static final String TAG = BaseActivity.class.getSimpleName();

	private BroadcastReceiver appReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d(TAG, "onReceive : " + intent.getAction());
			String packageName = intent.getData().getSchemeSpecificPart();
			Log.d(TAG, "pkg name : " + packageName);
			if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_ADDED)) {
				onAppAdded(packageName);
			} else if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_REPLACED)) {
				onAppReplaced(packageName);
			} else if (TextUtils.equals(intent.getAction(), Intent.ACTION_PACKAGE_REMOVED)) {
				onAppRemoved(packageName);
			}

		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		registerAppReceiver();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		unregisterAppReceiver();
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void registerAppReceiver() {
		Log.d(TAG, "registerAppReceiver");
		IntentFilter filter = new IntentFilter();
		filter.addDataScheme("package");
		filter.addAction(Intent.ACTION_PACKAGE_ADDED);
		filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		filter.addAction(Intent.ACTION_PACKAGE_REPLACED);
		registerReceiver(appReceiver, filter);
	}

	private void unregisterAppReceiver() {
		Log.d(TAG, "unregisterAppReceiver");
		unregisterReceiver(appReceiver);
	}

	protected void onAppAdded(String pkg) {

	}

	protected void onAppReplaced(String pkg) {

	}

	protected void onAppRemoved(String pkg) {

	}
}
