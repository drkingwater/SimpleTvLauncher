package com.pxq.simplelauncher.sort;

import java.util.Comparator;

import com.pxq.simplelauncher.bean.App;

/**
 * 更加应用更新时间排序
 * @author pxq
 * @date 2018年8月2日
 */
public class UpdateTimeComparetor implements Comparator<App>{
	
	/**
	 * @Field 是否升序，默认降序
	 */
	private boolean asc = false;

	public UpdateTimeComparetor(boolean asc) {
		super();
		this.asc = asc;
	}

	@Override
	public int compare(App lhs, App rhs) {
		if (asc) {
			return Long.compare(lhs.getUpdateTime(), rhs.getUpdateTime());
		}
		return - Long.compare(lhs.getUpdateTime(), rhs.getUpdateTime());
	}

}
