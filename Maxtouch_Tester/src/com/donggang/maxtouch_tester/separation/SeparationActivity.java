package com.donggang.maxtouch_tester.separation;

import com.donggang.maxtouch_tester.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**   
*    
* ��Ŀ���ƣ�MPT_App_Android  <BR> 
* �����ƣ�SeparationActivity   <BR>
* �������� Activity class for touch separation test  <BR>
* �����ˣ�daviddong   <BR>
* ����ʱ�䣺Jul 4, 2014 3:08:42 PM   <BR>
* �޸��ˣ�daviddong   <BR>
* �޸�ʱ�䣺Jul 4, 2014 3:08:42 PM   <BR>
* �޸ı�ע��   <BR>
* @version   v0.1 <BR>
*    
*/ 
public class SeparationActivity extends Activity {

	private SeparationView separation_view;
	private DisplayMetrics dm;
	
	/**
	 * override onCreate method
	 * @author daviddong
	 * @param savedInstanceState object of android.os.Bundle
	 * @return none
	 * @see android.os.Bundle
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// 0715 to use full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			    WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.activity_separation);

		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		
		LinearLayout separation_layout = (LinearLayout) this
				.findViewById(R.id.separation_layout);
		
		separation_view = new SeparationView(this,dm);
		separation_layout.addView(separation_view);
	}
}
