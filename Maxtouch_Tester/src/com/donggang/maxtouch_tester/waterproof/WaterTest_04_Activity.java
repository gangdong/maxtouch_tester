package com.donggang.maxtouch_tester.waterproof;

import com.donggang.maxtouch_tester.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class WaterTest_04_Activity extends Activity {

	
	private WaterTest_04_View water_test04_view;
	private DisplayMetrics dm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	    WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.activity_water_four);

		LinearLayout water_four_layout = (LinearLayout) this
				.findViewById(R.id.water_four_layout);
		
		
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		water_test04_view = new WaterTest_04_View(this,dm);
		water_four_layout.addView(water_test04_view);
	}
	
	
	
}
