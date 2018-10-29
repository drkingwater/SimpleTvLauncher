package com.pxq.simplelauncher.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pxq.simplelauncher.R;
import com.pxq.simplelauncher.bean.App;
import com.pxq.simplelauncher.bean.AppPagerItem;
import com.pxq.simplelauncher.listener.OnAppClickListener;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class AppPagerAdapter extends PagerAdapter {

	public static final String TAG = AppPagerAdapter.class.getSimpleName();

	private Context context;

	private List<AppPagerItem> pagerItems;
	
	private OnAppClickListener onAppClickListener;

	public AppPagerAdapter(Context context, List<AppPagerItem> pagerItems) {
		super();
		this.context = context;
		this.pagerItems = pagerItems;
	}

	
	public void setOnAppClickListener(OnAppClickListener onAppClickListener) {
		this.onAppClickListener = onAppClickListener;
	}


	@Override
	public int getCount() {
		return pagerItems == null ? 0 : pagerItems.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = LayoutInflater.from(context).inflate(R.layout.app_pager_item, null);
		GridView gridView = (GridView) view.findViewById(R.id.app_grid);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				AppAdapter appAdapter = (AppAdapter) parent.getAdapter();
				if (appAdapter != null) {
					App app = (App) appAdapter.getItem(position);
					if (onAppClickListener != null) {
						onAppClickListener.onClick(app);
					}
				}
			}
		});
		Log.d(TAG, "instantiateItem apps : " + pagerItems.get(position).getApps().size());
		gridView.setAdapter(new AppAdapter(context, pagerItems.get(position).getApps()));
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeViewAt(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(pagerItems.get(position).getTitle());
		// 改变字体大小
		spannableStringBuilder.setSpan(new RelativeSizeSpan(1.5f), 0, spannableStringBuilder.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableStringBuilder;
	}

}
