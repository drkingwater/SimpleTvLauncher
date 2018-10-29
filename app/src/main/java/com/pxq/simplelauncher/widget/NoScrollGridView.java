package com.pxq.simplelauncher.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 无滚动gridview 用scrollview实现滚动
 * 缺点：性能低，数据量大时可能导致卡顿
 * @author pxq
 * @date 2018-2-25
 */
public class NoScrollGridView extends GridView {

	public NoScrollGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public NoScrollGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoScrollGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
