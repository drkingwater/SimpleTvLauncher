package com.pxq.simplelauncher.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.pxq.simplelauncher.bean.App;

/**
 * 过滤掉指定包名的APK
 * 
 * @author pxq
 * @date 2018年8月2日
 */
public class PkgNameFilter implements IFilter {

	private String[] pkgNames;

	/**
	 * 过滤掉指定包名的APK
	 * 
	 * @param pkgNames
	 *            指定包名列表
	 */
	public PkgNameFilter(String... pkgNames) {
		super();
		this.pkgNames = pkgNames;
	}

	@Override
	public List<App> doFilter(List<App> apps) {
		if (apps == null) {
			return null;
		}
		List<App> filterApp = new ArrayList<App>();
		if (pkgNames != null) {
			List<String> pkgNameList = Arrays.asList(pkgNames);
			for (App app : apps) {
				if (!pkgNameList.contains(app.getPkg())) {
					filterApp.add(app);
				}
			}
		} else {
			return apps;
		}

		return filterApp;
	}

}
