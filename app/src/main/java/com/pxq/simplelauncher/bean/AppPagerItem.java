package com.pxq.simplelauncher.bean;

import java.util.List;

public class AppPagerItem {

	private String title;
	
	private List<App> apps;
	
	public AppPagerItem(String title, List<App> apps) {
		super();
		this.title = title;
		this.apps = apps;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<App> getApps() {
		return apps;
	}

	public void setApps(List<App> apps) {
		this.apps = apps;
	}
	
	
}
