package com.donggang.maxtouch_tester.accuracy;


import com.donggang.maxtouch_tester.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * 
 * 项目名称：MPT_App_Android <BR>
 * 类名称：AccuracyActivity <BR>
 * 类描述：Activity class for accuracy test <BR>
 * 创建人：daviddong <BR>
 * 创建时间：Jul 3, 2014 9:22:15 AM <BR>
 * 修改人：daviddong <BR>
 * 修改时间：Jul 3, 2014 9:22:15 AM <BR>
 * 修改备注：
 * 
 * @version v0.1 <BR>
 * 
 */
public class AccuracyActivity extends Activity {

	private AccuracyView accuracy_view;

	private DisplayMetrics dm;

	/**
	 * Override constructor
	 * 
	 * @author daviddong
	 * @param none
	 * @return none
	 */
	public AccuracyActivity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Override onCreate
	 * 
	 * @author daviddong
	 * @param savedInstanceState
	 *            - object of android.os.bundle
	 * @return none
	 * @see android.os.bundle
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//ark: 0715 to use full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	    WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.activity_accuracy);

		LinearLayout activity_accuracy = (LinearLayout) this
				.findViewById(R.id.accuracy_layout);

		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		accuracy_view = new AccuracyView(this, dm);

		activity_accuracy.addView(accuracy_view);

		
	}

}
