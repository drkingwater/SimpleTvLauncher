package com.pxq.simplelauncher.bean;

import android.graphics.drawable.Drawable;

public class App {

	private String pkg;
	
	private String name;
	
	private Drawable icon;
	
	private boolean isSystemApp;
	
	/**
	 * @Field 应用最后一次更新时间
	 */
	private long updateTime;

	

	public App(String pkg, String name, Drawable icon, boolean isSystemApp, long updateTime) {
		super();
		this.pkg = pkg;
		this.name = name;
		this.icon = icon;
		this.isSystemApp = isSystemApp;
		this.updateTime = updateTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public boolean isSystemApp() {
		return isSystemApp;
	}

	public void setSystemApp(boolean isSystemApp) {
		this.isSystemApp = isSystemApp;
	}

	@Override
	public String toString() {
		return "App [pkg=" + pkg + ", name=" + name + ", icon=" + icon + ", isSystemApp=" + isSystemApp + "]";
	}
	
	
	
}
