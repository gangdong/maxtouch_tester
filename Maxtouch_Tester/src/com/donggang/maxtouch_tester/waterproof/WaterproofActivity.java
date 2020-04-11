package com.donggang.maxtouch_tester.waterproof;

import com.donggang.maxtouch_tester.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class WaterproofActivity extends Activity {

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.activity_water_main);

		
		
		LinearLayout water_main_layout = (LinearLayout) this
				.findViewById(R.id.water_main_layout);
		
		
		
		
		Button btn_water_01 = (Button) this.findViewById(R.id.water_button1);
		btn_water_01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(WaterproofActivity.this, WaterTest_01_Activity.class);
				startActivity(intent);
			}
		});
		
		
		Button btn_water_02 = (Button) this.findViewById(R.id.water_button2);
		btn_water_02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(WaterproofActivity.this, WaterTest_02_Activity.class);
				startActivity(intent);
			}
		});
		
		
		Button btn_water_03 = (Button) this.findViewById(R.id.water_button3);
		btn_water_03.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(WaterproofActivity.this, WaterTest_03_Activity.class);
				startActivity(intent);
			}
		});
		
		
		Button btn_water_04 = (Button) this.findViewById(R.id.water_button4);
		btn_water_04.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(WaterproofActivity.this, WaterTest_04_Activity.class);
				startActivity(intent);
			}
		});
		
		
		Button btn_water_05 = (Button) this.findViewById(R.id.water_button5);
		btn_water_05.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(WaterproofActivity.this, WaterTest_05_Activity.class);
				startActivity(intent);
			}
		});
		
		
	}

	
	
}
