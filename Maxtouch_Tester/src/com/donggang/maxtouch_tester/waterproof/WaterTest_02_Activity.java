package com.donggang.maxtouch_tester.waterproof;

import com.donggang.maxtouch_tester.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class WaterTest_02_Activity extends Activity {

	
	private WaterTest_02_View water_test02_view;
	private DisplayMetrics dm;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	    WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.activity_water_two);

		LinearLayout water_two_layout = (LinearLayout) this
				.findViewById(R.id.water_two_layout);
		
		
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		water_test02_view = new WaterTest_02_View(this,dm);
		water_two_layout.addView(water_test02_view);
		
	}

	
	
	
}
