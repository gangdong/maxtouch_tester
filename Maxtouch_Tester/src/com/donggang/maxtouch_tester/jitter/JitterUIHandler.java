package com.donggang.maxtouch_tester.jitter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class JitterUIHandler extends Handler {

	private JitterActivity ja = null;

	private static JitterUIHandler uiH = new JitterUIHandler();
	
	private JitterUIHandler() {
		
		
	};
	
	
	public JitterActivity getJa() {
		return ja;
	}


	public void setJa(JitterActivity ja) {
		this.ja = ja;
	}


	public static JitterUIHandler getInstances(){
		
		return uiH;
	}


	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case 1:
			Bundle bd = msg.getData();
			int ii = bd.getInt("process", 0);
			if(ja==null){
				
			}
			else{
				ja.getProgressHorizontal().setProgress(ii);
			}
			
			break;
		}
		super.handleMessage(msg);
	}
	
	
	
}
