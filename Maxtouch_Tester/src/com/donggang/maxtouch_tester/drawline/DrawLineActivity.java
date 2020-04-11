package com.donggang.maxtouch_tester.drawline;

import java.util.Timer;
import java.util.TimerTask;

import com.donggang.maxtouch_tester.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * 
 * 项目名称：MPT_App_Android <BR>
 * 类名称：DrawLineActivity <BR>
 * 类描述：Activity class for draw line test <BR>
 * 创建人：daviddong <BR>
 * 创建时间：Jul 3, 2014 10:27:53 AM <BR>
 * 修改人：daviddong <BR>
 * 修改时间：Jul 3, 2014 10:27:53 AM <BR>
 * 修改备注：
 * 
 * @version v0.1 <BR>
 * 
 */
public class DrawLineActivity extends Activity {

	private Timer nTimer = new Timer();
	private DrawLineView view;
	private DisplayMetrics dm;

	/**
	 * Override constructor
	 * 
	 * @author daviddong
	 * @param none
	 * @return none
	 */
	public DrawLineActivity() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * Override onCreate method
	 * 
	 * @author daviddong
	 * @param savedInstanceState
	 *            - object of android.os.bundle
	 * @see android.os.Bundle
	 * @return none
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//ark: 0715 to use full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		   WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_drawline);
		RelativeLayout test_relative = (RelativeLayout) this
				.findViewById(R.id.test_relative);

		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		// float name = getWindowManager().getDefaultDisplay().getRefreshRate();

		view = new DrawLineView(this, dm);
		view.setMinimumHeight(300);
		view.setMinimumWidth(500);
		view.setBackgroundColor(Color.WHITE);

		test_relative.addView(view);

		setTimerTask();
	}

	/**
	 * Override onDestroy method
	 * 
	 * @author daviddong
	 * @param none
	 * @return none
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// nTimer.cancel();

	}

	@SuppressLint("HandlerLeak")
	private void setTimerTask() {
		nTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				Message message = new Message();
				message.what = 1;
				doActionHandler.sendMessage(message);
			}
		}, 250, 250/* 表示1000毫秒之後，每隔1000毫秒執行一次 */);
	}

	private Handler doActionHandler = new Handler() {
		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int msgId = msg.what;
			switch (msgId) {
			case 1:
				// do some action
				int sum = view.getCount() * 4;
				view.setSum(sum);
				view.setCount(0);

				break;
			default:
				break;
			}
		}
	};

}
