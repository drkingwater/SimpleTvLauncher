package com.pxq.simplelauncher.sort;

import java.util.Comparator;

import com.pxq.simplelauncher.bean.App;


public class UpdateTimeComparator implements Comparator<App>{
	
	/**
	 * @Field 是否升序，默认降序false
	 */
	private boolean asc = false;

	public UpdateTimeComparator(boolean asc) {
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
