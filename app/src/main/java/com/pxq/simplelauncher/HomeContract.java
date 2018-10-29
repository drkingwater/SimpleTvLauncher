package com.pxq.simplelauncher;

import java.util.List;

import com.pxq.simplelauncher.bean.App;
import com.pxq.simplelauncher.bean.AppPagerItem;
import com.pxq.simplelauncher.listener.OnResponseListener;

import android.content.Context;
import android.content.Intent;

public interface HomeContract {

	interface View {
		void showUserAppList(List<App> apps);

		void showSystemAppList(List<App> apps);
		
		void showAppPager(List<AppPagerItem> pagerItems);

		void launch(Intent intent);

		void showInfo(String msg);

		void showDelete(boolean delete);

		void showLoading();

		void hideLoading();
	}

	interface Presenter {
		void getAppList();

		void onAppClick(App app);

		void setDelete(boolean delete);

		boolean isDelete();
	}

	interface Model {
		void getAppList(Context context, OnResponseListener<List<App>> onResponseListener);

		void getLaunchIntent(Context context, String pkg, OnResponseListener<Intent> onResponseListener);

		void uninstall(Context context, String pkg, OnResponseListener<Integer> onResponseListener);
	}

}
