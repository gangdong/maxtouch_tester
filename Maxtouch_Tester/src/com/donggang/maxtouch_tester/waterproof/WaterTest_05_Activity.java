package com.donggang.maxtouch_tester.waterproof;

import com.donggang.maxtouch_tester.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class WaterTest_05_Activity extends Activity {

	private WaterTest_05_View water_test05_view;
	private DisplayMetrics dm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	    WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.activity_water_five);

		LinearLayout water_five_layout = (LinearLayout) this
				.findViewById(R.id.water_five_layout);
		
		
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		water_test05_view = new WaterTest_05_View(this,dm);
		water_five_layout.addView(water_test05_view);
	}
	
	
}
