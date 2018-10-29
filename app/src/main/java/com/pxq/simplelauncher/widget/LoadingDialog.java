package com.pxq.simplelauncher.widget;


import com.pxq.simplelauncher.R;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadingDialog extends Dialog{
	
	private ImageView loadingIv;
	private TextView infoTv;
	private ObjectAnimator animator;

	public LoadingDialog(Context context) {
		super(context, R.style.dialog);
		initView();
		setCancelable(false);
	}
	
	private void initView(){
		setContentView(R.layout.loading_dialog_layout);
		loadingIv = (ImageView) findViewById(R.id.loading_iv);
		initAnim();
	}
	
	private void initAnim(){
		animator = ObjectAnimator.ofFloat(loadingIv, "rotation", 0f, 360f);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		animator.setInterpolator(new LinearInterpolator());
		animator.setDuration(1000);
	}
	
	public void setInfo(String text){
		infoTv.setText(text);
	}
	
	@Override
	public void show() {
		super.show();
		if (animator != null) {
			animator.start();
		}
	}
	
	@Override
	public void dismiss() {
		if (animator != null) {
			animator.cancel();
		}
		super.dismiss();
	}
}
