package com.pxq.simplelauncher.sort;

import java.util.Collections;
import java.util.List;

import com.pxq.simplelauncher.bean.App;


public class UpdateTimeSorter implements ISorter {

	private boolean asc = false;

	public UpdateTimeSorter() {
		super();
	}

	public UpdateTimeSorter(boolean asc) {
		super();
		this.asc = asc;
	}

	@Override
	public List<App> sort(List<App> apps) {
		if (apps == null) {
			return null;
		}
		Collections.sort(apps, new UpdateTimeComparator(asc));
		return apps;
	}

}
