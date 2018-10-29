package com.pxq.simplelauncher;

import java.util.List;

import com.pxq.simplelauncher.bean.App;
import com.pxq.simplelauncher.listener.OnResponseListener;
import com.pxq.simplelauncher.task.BaseTask;
import com.pxq.simplelauncher.utils.AppUtils;
import com.pxq.simplelauncher.utils.AppUtils.MyPackageDeleteObserver;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

public class HomeModel implements HomeContract.Model {

	public static final String TAG = HomeModel.class.getSimpleName();

	private Handler handler = new Handler();

	@Override
	public void getAppList(final Context context, final OnResponseListener<List<App>> onResponseListener) {
		new Thread(new BaseTask(onResponseListener) {
			@Override
			protected void execute() {
				final List<App> apps = AppUtils.getAppLocal(context);
				if (onResponseListener != null) {
					handler.post(new Runnable() {
						@Override
						public void run() {
							onResponseListener.onSuccess(apps);
						}
					});
				}
			}
		}).start();

	}

	@Override
	public void getLaunchIntent(Context context, String pkg, OnResponseListener<Intent> onResponseListener) {
		if (TextUtils.isEmpty(pkg)) {
			onResponseListener.onError(new Exception("pkg == null"));
			return;
		}
		Intent intent = AppUtils.getLaunchIntent(context, pkg);
		if (intent == null) {
			onResponseListener.onError(new Exception("launch intent == null"));
		} else {
			onResponseListener.onSuccess(intent);
		}
	}

	@Override
	public void uninstall(final Context context, final String pkg, final OnResponseListener<Integer> onResponseListener) {
		new Thread(new BaseTask(onResponseListener){
			@Override
			protected void execute() {
				AppUtils.uninstallApkDefaul(context, pkg, new MyPackageDeleteObserver() {

					@Override
					public void packageDeletedSuccess(String pkg) {
						runOnUiThread(onResponseListener, 1);
					}

					@Override
					public void packageDeletedFailed(String pkg, int errorCode) {
						runOnUiThreadFailed(onResponseListener, AppUtils.getAppName(context, pkg) + " errorCode " + errorCode);
					}
				});
			}
		}).start();
		
	}

	private <T> void runOnUiThread(final OnResponseListener<T> onResponseListener, final T result) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				onResponseListener.onSuccess(result);
			}
		});
	}

	private <T> void runOnUiThreadFailed(final OnResponseListener<T> onResponseListener, final String msg) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				onResponseListener.onError(new Exception(msg));
			}
		});
	}

}
