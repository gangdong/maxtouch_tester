package com.donggang.maxtouch_tester.flick;

import com.donggang.maxtouch_tester.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**   
*    
* ��Ŀ���ƣ�MPT_App_Android  <BR> 
* �����ƣ�FlickActivity   <BR>
* �������� Activity class for touch flick test <BR>
* �����ˣ�daviddong   <BR>
* ����ʱ�䣺Jul 3, 2014 2:30:59 PM   <BR>
* �޸��ˣ�daviddong   <BR>
* �޸�ʱ�䣺Jul 3, 2014 2:30:59 PM   <BR>
* �޸ı�ע��   
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
