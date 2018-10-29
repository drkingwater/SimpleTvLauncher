package com.pxq.simplelauncher.sort;

import java.util.Collections;
import java.util.List;

import com.pxq.simplelauncher.bean.App;

/**
 * 更加应用更新时间排序
 * 
 * @author pxq
 * @date 2018年8月2日
 */
public class UpdateTimeSorter implements ISorter {

	/**
	 * @Field 是否升序，默认降序
	 */
	private boolean asc = false;

	public UpdateTimeSorter() {
		super();
	}

	/**
	 * 设置升序、降序
	 * @param asc true:升序 false:降序
	 */
	public UpdateTimeSorter(boolean asc) {
		super();
		this.asc = asc;
	}

	@Override
	public List<App> sort(List<App> apps) {
		if (apps == null) {
			return null;
		}
		Collections.sort(apps, new UpdateTimeComparetor(asc));
		return apps;
	}

}
