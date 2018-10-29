package com.pxq.simplelauncher.listener;

/**
 * ÍøÂçÇëÇó»Øµ÷
 * 
 * @param <T>
 * @author pxq
 */
public interface OnResponseListener<T> extends OnTaskListener{
	
	void onSuccess(T response);
	
	void onError(Exception e);

}
