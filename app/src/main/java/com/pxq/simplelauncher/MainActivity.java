package com.pxq.simplelauncher;

import java.util.List;

import com.pxq.simplelauncher.adapter.AppAdapter;
import com.pxq.simplelauncher.adapter.AppPagerAdapter;
import com.pxq.simplelauncher.base.BaseActivity;
import com.pxq.simplelauncher.bean.App;
import com.pxq.simplelauncher.bean.AppPagerItem;
import com.pxq.simplelauncher.listener.OnAppClickListener;
import com.pxq.simplelauncher.utils.AppUtils;
import com.pxq.simplelauncher.widget.LoadingDialog;
import com.pxq.simplelauncher.widget.NoScrollGridView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements HomeContract.View {

	private HomeContract.Presenter mPresenter;
	
	private ViewPager mAppPager;

	private AppPagerAdapter mPagerAdapter;
	
	private GridView mAppGrid, mSystemAppGrid;

	private AppAdapter mAppAdapter, mSystemAppApdater;
	
	private LoadingDialog loadingDialog;
	
	private OnAppClickListener onAppClickListener = new OnAppClickListener() {
		
		@Override
		public void onClick(App app) {
			mPresenter.onAppClick(app);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		mPresenter = new HomePresenter(this, this);
		mPresenter.getAppList();
	}

	private void initView() {
		mAppGrid = (GridView) findViewById(R.id.app_grid);
		mAppGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				App app = (App) mAppGrid.getAdapter().getItem(position);
				mPresenter.onAppClick(app);
			}
		});
		mSystemAppGrid = (GridView) findViewById(R.id.system_app_grid);
		mSystemAppGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				App app = (App) mAppGrid.getAdapter().getItem(position);
				mPresenter.onAppClick(app);
			}
		});
		
		mAppPager = (ViewPager) findViewById(R.id.app_pager);
		
		loadingDialog = new LoadingDialog(this);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:  //菜单键卸载模式
			mPresenter.setDelete(true);
			break;
		case KeyEvent.KEYCODE_BACK:  //返回键普通模式
			if (mPresenter.isDelete()) {
				mPresenter.setDelete(false);
				return true;
			}
			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void showUserAppList(List<App> apps) {
		if (mAppAdapter == null) {
			mAppAdapter = new AppAdapter(this, apps);
			mAppGrid.setAdapter(mAppAdapter);
		} else {
			mAppAdapter.setData(apps);
		}
	}
	
	@Override
	public void showSystemAppList(List<App> apps) {
		if (mSystemAppApdater == null) {
			mSystemAppApdater = new AppAdapter(this, apps);
			mSystemAppGrid.setAdapter(mSystemAppApdater);
		} else {
			mSystemAppApdater.setData(apps);
		}
	}

	@Override
	public void launch(Intent intent) {
		AppUtils.launch(this, intent);
	}

	@Override
	public void showInfo(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showDelete(boolean delete) {
		if (mAppAdapter != null) {
			mAppAdapter.setDelete(delete);
		}
	}

	@Override
	public void showLoading() {
		loadingDialog.show();
	}

	@Override
	public void hideLoading() {
		loadingDialog.hide();
	}

	@Override
	public void showAppPager(List<AppPagerItem> pagerItems) {
		mPagerAdapter = new AppPagerAdapter(this, pagerItems);
		mPagerAdapter.setOnAppClickListener(onAppClickListener);
		mAppPager.setAdapter(mPagerAdapter);
	}
	
	@Override
	protected void onAppAdded(String pkg) {
		//TODO refresh apps
	}
	
	@Override
	protected void onAppRemoved(String pkg) {
		//TODO refresh apps
	}
	
	@Override
	protected void onAppReplaced(String pkg) {
		//TODO refresh apps
	}

	
}
