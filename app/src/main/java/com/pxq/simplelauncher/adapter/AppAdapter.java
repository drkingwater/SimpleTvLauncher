package com.pxq.simplelauncher.adapter;

import java.util.List;

import com.pxq.simplelauncher.R;
import com.pxq.simplelauncher.bean.App;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppAdapter extends BaseAdapter {

	private Context context;

	private List<App> apps;

	private boolean deleteMode = false;

	public AppAdapter(Context context, List<App> apps) {
		super();
		this.context = context;
		this.apps = apps;
		this.deleteMode = false;
	}
	
	public void setData(List<App> apps){
		this.apps = apps;
		notifyDataSetChanged();
	}

	public void setDelete(boolean deleteMode) {
		if (this.deleteMode == deleteMode) {
			return;
		}
		this.deleteMode = deleteMode;
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return apps == null ? 0 : apps.size();
	}

	@Override
	public App getItem(int position) {
		return apps == null ? null : apps.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AppHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.app_grid_item, null);
			holder = new AppHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (AppHolder) convertView.getTag();
		}

		App app = apps.get(position);

		holder.iconIv.setImageDrawable(app.getIcon());
		holder.nameTv.setText(app.getName());
		holder.deleteIcon.setVisibility(deleteMode ? View.VISIBLE : View.GONE);
		return convertView;
	}

	static class AppHolder {
		ImageView iconIv, deleteIcon;
		TextView nameTv;

		public AppHolder(View view) {
			iconIv = (ImageView) view.findViewById(R.id.app_icon_iv);
			nameTv = (TextView) view.findViewById(R.id.app_name_tv);
			deleteIcon = (ImageView) view.findViewById(R.id.delete_icon_iv);
		}

	}

}
