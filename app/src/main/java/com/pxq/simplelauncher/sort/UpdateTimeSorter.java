package com.pxq.simplelauncher.sort;

import java.util.Collections;
import java.util.List;

import com.pxq.simplelauncher.bean.App;

/**
 * ����Ӧ�ø���ʱ������
 * 
 * @author pxq
 * @date 2018��8��2��
 */
public class UpdateTimeSorter implements ISorter {

	/**
	 * @Field �Ƿ�����Ĭ�Ͻ���
	 */
	private boolean asc = false;

	public UpdateTimeSorter() {
		super();
	}

	/**
	 * �������򡢽���
	 * @param asc true:���� false:����
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
		Collections.sort(apps, new UpdateTimeComparator(asc));
		return apps;
	}

}
