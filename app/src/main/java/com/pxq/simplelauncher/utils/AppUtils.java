package com.pxq.simplelauncher.utils;

import java.util.ArrayList;
import java.util.List;

import com.pxq.simplelauncher.bean.App;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

public class AppUtils {

	public static final String TAG = AppUtils.class.getSimpleName();

	/**
	 * 获取启动intent
	 * @param context
	 * @param pkg
	 * @return
	 */
	public static Intent getLaunchIntent(Context context, String pkg) {
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 0);
		for (ResolveInfo resolveInfo : list) {
			if (resolveInfo.activityInfo.packageName.equals(pkg)) {
				ComponentName cn = new ComponentName(pkg, resolveInfo.activityInfo.name);
				intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_LAUNCHER);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setComponent(cn);
				return intent;
			}
		}
		return null;
	}
	
	/**
	 * 根据包名获取应用名
	 * @param context
	 * @param pkg
	 * @return
	 */
	public static String getAppName(Context context, String pkg){
		PackageManager packageManager = context.getPackageManager();
		String name = null;
		try {
			name = packageManager.getApplicationInfo(pkg, 0).loadLabel(packageManager).toString();
			Log.d(TAG, "getAppName pkg " + pkg + " name " + name);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public static List<App> getAppLocal(Context context){
		List<App> apps = new ArrayList<App>();
		PackageManager packageManager = context.getPackageManager();
		List<ApplicationInfo> applications = packageManager
				.getInstalledApplications(PackageManager.GET_META_DATA);
		for (ApplicationInfo applicationInfo : applications) {
			long updateTime = 0;
			try {
				PackageInfo packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, 0);
				updateTime = packageInfo.lastUpdateTime;
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String packageName = applicationInfo.packageName;
			String name = applicationInfo.loadLabel(packageManager).toString();
			Drawable icon = applicationInfo.loadIcon(packageManager);
			boolean isSystemApp = false;
			if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
				// 系统应用
				isSystemApp = true;
			} else {
				isSystemApp = false;
			}
			App app = new App(packageName, name, icon, isSystemApp, updateTime);
			Log.d(TAG, app.toString());
			apps.add(app);
		}
		return apps;
	}
	
	/**
	 * 获取自身包名
	 * @param context
	 * @return
	 */
	public static final String getPkgSelf(Context context){
		return context.getPackageName();
	}

	/**
	 * 启动应用
	 * @param context
	 * @param intent
	 */
	public static void launch(Context context, Intent intent) {
		context.startActivity(intent);
	}

	/**
	 * 卸载
	 * @param context
	 * @param pkg
	 */
	public static void uninstall(Context context, String pkg) {
		Uri packageURI = Uri.parse("package:" + pkg);
		Intent intent = new Intent(Intent.ACTION_DELETE);
		intent.setData(packageURI);
		context.startActivity(intent);
	}

	/**
	 * 静默卸载
	 * @param context
	 * @param packageName
	 * @param observer
	 */
	public static void uninstallApkDefaul(Context context, String packageName, MyPackageDeleteObserver observer) {
		PackageManager pm = context.getPackageManager();
		pm.deletePackage(packageName, observer, 0);
	}

	/* 静默卸载回调 */
	public static abstract class MyPackageDeleteObserver extends IPackageDeleteObserver.Stub {

		@Override
		public void packageDeleted(String packageName, int returnCode) {
			Log.d(TAG, "returnCode = " + returnCode + ",packageName:" + packageName);// 返回1代表卸载成功
			if (returnCode == 1) {
				packageDeletedSuccess(packageName);
			} else {
				packageDeletedFailed(packageName, returnCode);
			}
		}

		public abstract void packageDeletedSuccess(String pkg);

		public abstract void packageDeletedFailed(String pkg, int errorCode);

	}

}
