package com.pxq.simplelauncher.task;

import com.pxq.simplelauncher.listener.OnTaskListener;

import android.os.Handler;

public class BaseTask implements Runnable {

	private OnTaskListener onTaskListener;

	private Handler handler = new Handler();

	public BaseTask(OnTaskListener onTaskListener) {
		super();
		this.onTaskListener = onTaskListener;
	}

	@Override
	public void run() {
		onReady();
		execute();
		onComplete();
	}

	protected void execute() {

	}

	private void onReady() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				onTaskListener.onReady();
			}
		});
	}

	private void onComplete() {
		handler.post(new Runnable() {
			@Override
			public void run() {
				onTaskListener.onComplete();
			}
		});
	}

}
