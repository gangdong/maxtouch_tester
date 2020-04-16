package com.donggang.maxtouch_tester.flick;

import com.donggang.maxtouch_tester.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**   
*    
* 项目名称：MPT_App_Android  <BR> 
* 类名称：FlickActivity   <BR>
* 类描述： Activity class for touch flick test <BR>
* 创建人：daviddong   <BR>
* 创建时间：Jul 3, 2014 2:30:59 PM   <BR>
* 修改人：daviddong   <BR>
* 修改时间：Jul 3, 2014 2:30:59 PM   <BR>
* 修改备注：   
* @version   v0.1 <BR>
*    
*/ 
public class FlickActivity extends Activity {

	private FlickView flick_view;


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
			    
		this.setContentView(R.layout.activity_flick);

		LinearLayout activity_flick = (LinearLayout) this
				.findViewById(R.id.flick_layout);

		flick_view = new FlickView(this);

		activity_flick.addView(flick_view);
	}

}
