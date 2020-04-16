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
* 项目名称：MPT_App_Android  <BR> 
* 类名称：SeparationActivity   <BR>
* 类描述： Activity class for touch separation test  <BR>
* 创建人：daviddong   <BR>
* 创建时间：Jul 4, 2014 3:08:42 PM   <BR>
* 修改人：daviddong   <BR>
* 修改时间：Jul 4, 2014 3:08:42 PM   <BR>
* 修改备注：   <BR>
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
