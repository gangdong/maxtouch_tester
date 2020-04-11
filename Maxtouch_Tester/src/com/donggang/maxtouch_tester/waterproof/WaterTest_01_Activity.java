package com.donggang.maxtouch_tester.waterproof;

import com.donggang.maxtouch_tester.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class WaterTest_01_Activity extends Activity {

	
	private WaterTest_01_View water_test01_view;
	private DisplayMetrics dm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	    WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.activity_water_one);

		LinearLayout water_one_layout = (LinearLayout) this
				.findViewById(R.id.water_one_layout);
		
		
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		water_test01_view = new WaterTest_01_View(this,dm);
		water_one_layout.addView(water_test01_view);
		
	}

	
}
