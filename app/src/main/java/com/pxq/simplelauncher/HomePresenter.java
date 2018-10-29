package com.pxq.simplelauncher;

import java.util.ArrayList;
import java.util.List;

import com.pxq.simplelauncher.HomeContract.View;
import com.pxq.simplelauncher.bean.App;
import com.pxq.simplelauncher.bean.AppPagerItem;
import com.pxq.simplelauncher.filter.AppTypeFilter;
import com.pxq.simplelauncher.filter.PkgNameFilter;
import com.pxq.simplelauncher.listener.OnResponseAdapter;
import com.pxq.simplelauncher.sort.UpdateTimeSorter;
import com.pxq.simplelauncher.utils.AppUtils;

import android.content.Context;
import android.content.Intent;

public class HomePresenter implements HomeContract.Presenter {

	private HomeContract.View mView;
	private HomeContract.Model mModel;
	private Context mContext;
	private List<App> mApps;

	private boolean deleteMode = false;

	public HomePresenter(View mView, Context mContext) {
		super();
		this.mView = mView;
		this.mContext = mContext;
		mModel = new HomeModel();
	}

	@Override
	public void getAppList() {
		mModel.getAppList(mContext, new OnResponseAdapter<List<App>>() {

			@Override
			public void onReady() {
				mView.showLoading();
			}

			@Override
			public void onSuccess(List<App> response) {
				// 过滤掉自身
				mApps = new PkgNameFilter(AppUtils.getPkgSelf(mContext)).doFilter(response);
				// 生成viewpager数据
				List<AppPagerItem> pagerItems = new ArrayList<AppPagerItem>();
				// 用户应用按应用更新时间降序排
				pagerItems.add(new AppPagerItem("用户应用",
						new UpdateTimeSorter().sort(new AppTypeFilter(false).doFilter(mApps))));
				pagerItems.add(new AppPagerItem("系统应用", new AppTypeFilter(true).doFilter(mApps)));
				mView.showAppPager(pagerItems);
			}

			@Override
			public void onError(Exception e) {
				mView.showInfo(e.getMessage());
			}

			@Override
			public void onComplete() {
				mView.hideLoading();
			}

		});
	}

	@Override
	public void onAppClick(App app) {
		if (deleteMode) {
			uninstall(app);
		} else {
			launch(app);
		}

	}

	private void uninstall(final App app) {
		mModel.uninstall(mContext, app.getPkg(), new OnResponseAdapter<Integer>() {

			@Override
			public void onReady() {
				mView.showLoading();
			}

			@Override
			public void onSuccess(Integer response) {
				mApps.remove(app);
				mView.showUserAppList(mApps);
			}

			@Override
			public void onError(Exception e) {
				mView.showInfo(e.getMessage());
			}

			@Override
			public void onComplete() {
				mView.hideLoading();
			}

		});
	}

	private void launch(App app) {
		mModel.getLaunchIntent(mContext, app.getPkg(), new OnResponseAdapter<Intent>() {

			@Override
			public void onSuccess(Intent response) {
				mView.launch(response);
			}

			@Override
			public void onError(Exception e) {
				mView.showInfo(e.getMessage());
			}

		});
	}

	@Override
	public void setDelete(boolean delete) {
		if (this.deleteMode == delete) {
			return;
		}
		this.deleteMode = delete;
		mView.showDelete(deleteMode);
	}

	@Override
	public boolean isDelete() {
		return deleteMode;
	}

}
