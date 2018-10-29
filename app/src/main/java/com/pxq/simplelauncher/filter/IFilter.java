package com.pxq.simplelauncher.filter;

import java.util.List;

import com.pxq.simplelauncher.bean.App;

public interface IFilter {

	List<App> doFilter(List<App> apps);
	
}
