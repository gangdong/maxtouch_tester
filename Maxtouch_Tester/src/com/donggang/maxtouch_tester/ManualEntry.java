package com.donggang.maxtouch_tester;

import com.donggang.maxtouch_tester.accuracy.AccuracyActivity;
import com.donggang.maxtouch_tester.drawline.DrawLineActivity;
import com.donggang.maxtouch_tester.flick.FlickActivity;
import com.donggang.maxtouch_tester.jitter.JitterActivity;
import com.donggang.maxtouch_tester.reference.ReferenceActivity;
import com.donggang.maxtouch_tester.separation.SeparationActivity;
import com.donggang.maxtouch_tester.waterproof.WaterproofActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class ManualEntry extends Activity {

	private Button btn_drawline;
	private Button btn_accuracy;
	private Button btn_jitter;
	private Button btn_flick;
	private Button btn_separation;
	private Button btn_waterproof;
	private Button btn_reference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.manual_entry);

		btn_drawline = (Button) this.findViewById(R.id.buttonDraw);
		btn_drawline.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				// return false;

				int action = event.getActionMasked();

				switch (action) {

				case MotionEvent.ACTION_DOWN:
					btn_drawline.setBackgroundColor(getResources().getColor(
							R.color.colorbackground));
					btn_drawline.setTextColor(getResources().getColor(
							R.color.colorred));
					return true;
				case MotionEvent.ACTION_UP:

					btn_drawline.setBackgroundColor(getResources().getColor(
							R.color.colorDrawline));
					btn_drawline.setTextColor(getResources().getColor(
							R.color.colorwhite));
					Intent intent = new Intent(ManualEntry.this,
							DrawLineActivity.class);
					startActivity(intent);

					return true;

				default:

					return false;
				}

			}
		});

		btn_drawline.setShadowLayer(10, 5, 5, Color.BLACK);

		btn_accuracy = (Button) this.findViewById(R.id.buttonAccuracy);
		btn_accuracy.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				int action = event.getActionMasked();

				switch (action) {

				case MotionEvent.ACTION_DOWN:
					btn_accuracy.setBackgroundColor(getResources().getColor(
							R.color.colorbackground));
					btn_accuracy.setTextColor(getResources().getColor(
							R.color.colorred));
					return true;
				case MotionEvent.ACTION_UP:

					btn_accuracy.setBackgroundColor(getResources().getColor(
							R.color.colorAccuracy));
					btn_accuracy.setTextColor(getResources().getColor(
							R.color.colorwhite));
					Intent intent = new Intent(ManualEntry.this,
							AccuracyActivity.class);
					startActivity(intent);

					return true;

				default:

					return false;
				}
			}
		});

		btn_accuracy.setShadowLayer(10, 5, 5, Color.BLACK);

		btn_jitter = (Button) this.findViewById(R.id.buttonJitter);
		btn_jitter.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				int action = event.getActionMasked();

				switch (action) {

				case MotionEvent.ACTION_DOWN:
					btn_jitter.setBackgroundColor(getResources().getColor(
							R.color.colorbackground));
					btn_jitter.setTextColor(getResources().getColor(
							R.color.colorred));
					return true;
				case MotionEvent.ACTION_UP:

					btn_jitter.setBackgroundColor(getResources().getColor(
							R.color.colorJitter));
					btn_jitter.setTextColor(getResources().getColor(
							R.color.colorwhite));
					Intent intent = new Intent(ManualEntry.this,
							JitterActivity.class);
					startActivity(intent);

					return true;

				default:

					return false;
				}
			}
		});

		btn_jitter.setShadowLayer(10, 5, 5, Color.BLACK);

		btn_flick = (Button) this.findViewById(R.id.buttonFlick);
		btn_flick.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				int action = event.getActionMasked();

				switch (action) {

				case MotionEvent.ACTION_DOWN:
					btn_flick.setBackgroundColor(getResources().getColor(
							R.color.colorbackground));
					btn_flick.setTextColor(getResources().getColor(
							R.color.colorred));
					return true;
				case MotionEvent.ACTION_UP:

					btn_flick.setBackgroundColor(getResources().getColor(
							R.color.colorFlick));
					btn_flick.setTextColor(getResources().getColor(
							R.color.colorwhite));
					Intent intent = new Intent(ManualEntry.this,
							FlickActivity.class);
					startActivity(intent);

					return true;

				default:

					return false;
				}
			}
		});

		btn_flick.setShadowLayer(10, 5, 5, Color.BLACK);

		btn_separation = (Button) this.findViewById(R.id.buttonSeparation);
		btn_separation.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				int action = event.getActionMasked();

				switch (action) {

				case MotionEvent.ACTION_DOWN:
					btn_separation.setBackgroundColor(getResources().getColor(
							R.color.colorbackground));
					btn_separation.setTextColor(getResources().getColor(
							R.color.colorred));
					return true;
				case MotionEvent.ACTION_UP:

					btn_separation.setBackgroundColor(getResources().getColor(
							R.color.colorSeparation));
					btn_separation.setTextColor(getResources().getColor(
							R.color.colorwhite));
					Intent intent = new Intent(ManualEntry.this,
							SeparationActivity.class);
					startActivity(intent);

					return true;

				default:

					return false;
				}
			}
		});

		btn_separation.setShadowLayer(10, 5, 5, Color.BLACK);

		btn_waterproof = (Button) this.findViewById(R.id.buttonWater);

		btn_waterproof.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				int action = event.getActionMasked();

				switch (action) {

				case MotionEvent.ACTION_DOWN:
					btn_waterproof.setBackgroundColor(getResources().getColor(
							R.color.colorbackground));
					btn_waterproof.setTextColor(getResources().getColor(
							R.color.colorred));
					return true;
				case MotionEvent.ACTION_UP:

					btn_waterproof.setBackgroundColor(getResources().getColor(
							R.color.colorWater));
					btn_waterproof.setTextColor(getResources().getColor(
							R.color.colorwhite));
					Intent intent = new Intent(ManualEntry.this,
							WaterproofActivity.class);
					startActivity(intent);

					return true;

				default:

					return false;
				}
			}
		});

		btn_waterproof.setShadowLayer(10, 5, 5, Color.BLACK);

		
		
		btn_reference = (Button) this.findViewById(R.id.buttonReference);

		btn_reference.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				int action = event.getActionMasked();

				switch (action) {

				case MotionEvent.ACTION_DOWN:
					btn_reference.setBackgroundColor(getResources().getColor(
							R.color.colorbackground));
					btn_reference.setTextColor(getResources().getColor(
							R.color.colorred));
					return true;
				case MotionEvent.ACTION_UP:

					btn_reference.setBackgroundColor(getResources().getColor(
							R.color.colorWater));
					btn_reference.setTextColor(getResources().getColor(
							R.color.colorwhite));
					Intent intent = new Intent(ManualEntry.this,
							ReferenceActivity.class);
					startActivity(intent);

					return true;

				default:

					return false;
				}
			}
		});

		btn_reference.setShadowLayer(10, 5, 5, Color.BLACK);

	}

}
