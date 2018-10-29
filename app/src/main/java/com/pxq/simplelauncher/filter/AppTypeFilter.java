package com.pxq.simplelauncher.filter;

import java.util.ArrayList;
import java.util.List;

import com.pxq.simplelauncher.bean.App;

public class AppTypeFilter implements IFilter{

	private boolean filterSystemApp = false;
	
	public AppTypeFilter(boolean filterSystemApp) {
		super();
		this.filterSystemApp = filterSystemApp;
	}

	@Override
	public List<App> doFilter(List<App> apps) {
		if (apps == null) {
			return null;
		}
		List<App> systemApps = new ArrayList<App>();
		List<App> userApps = new ArrayList<App>();
		if (apps != null) {
			for (App app : apps) {
				if (app.isSystemApp()) {
					systemApps.add(app);
				} else {
					userApps.add(app);
				}
			}
		}
		if (filterSystemApp) {
			return systemApps;
		}
		return userApps;
	}

}
