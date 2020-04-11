package com.donggang.maxtouch_tester;

import com.donggang.maxtouch_tester.accuracy.AccuracyActivity;
import com.donggang.maxtouch_tester.drawline.DrawLineActivity;
import com.donggang.maxtouch_tester.flick.FlickActivity;
import com.donggang.maxtouch_tester.jitter.JitterActivity;
import com.donggang.maxtouch_tester.separation.SeparationActivity;
import com.donggang.maxtouch_tester.util.SystemUiHider;
import com.donggang.maxtouch_tester.waterproof.WaterproofActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        Button btn_manualEntry = (Button) this.findViewById(R.id.buttonManual);
        btn_manualEntry.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(FullscreenActivity.this, ManualEntry.class);
				startActivity(intent);
			}
		});
		//btn_drawline.setBackgroundColor(Color.argb(0xff, 0xfe, 0x2, 0x73));
        btn_manualEntry.setShadowLayer(10, 5, 5, Color.BLACK);
        
        
        /*
        Button btn_drawline = (Button) this.findViewById(R.id.buttonDraw);
		btn_drawline.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(FullscreenActivity.this, DrawLineActivity.class);
				startActivity(intent);
			}
		});
		//btn_drawline.setBackgroundColor(Color.argb(0xff, 0xfe, 0x2, 0x73));
		btn_drawline.setShadowLayer(10, 5, 5, Color.BLACK);
		
		
		Button btn_accuracy = (Button) this.findViewById(R.id.buttonAccuracy);
		btn_accuracy.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FullscreenActivity.this, AccuracyActivity.class);
				startActivity(intent);
			}
			
		});
		//btn_accuracy.setBackgroundColor(Color.argb(0xff, 0x5a, 0xa4, 0x96));
		btn_accuracy.setShadowLayer(10, 5, 5, Color.BLACK);
        
        
		Button btn_jitter = (Button)this.findViewById(R.id.buttonJitter);
		btn_jitter.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FullscreenActivity.this, JitterActivity.class);
				startActivity(intent);
			}});
		
		//btn_jitter.setBackgroundColor(Color.argb(0x7f, 0xfc, 0xa9, 0x2));
		btn_jitter.setShadowLayer(10, 5, 5, Color.BLACK);
		
		Button btn_flick = (Button)this.findViewById(R.id.buttonFlick);
		btn_flick.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FullscreenActivity.this, FlickActivity.class);
				startActivity(intent);
			}});
		
		//btn_flick.setBackgroundColor(Color.argb(0x7f, 0xf1, 0x3e, 0xd));
		btn_flick.setShadowLayer(10, 5, 5, Color.BLACK);
		
		Button btn_separation = (Button)this.findViewById(R.id.buttonSeparation);
		btn_separation.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FullscreenActivity.this, SeparationActivity.class);
				startActivity(intent);
			}});
		
		//btn_separation.setBackgroundColor(Color.argb(0x7f, 0xf8, 0x6, 0x1d));
		btn_separation.setShadowLayer(10, 5, 5, Color.BLACK);
		
		
		Button btn_info = (Button)this.findViewById(R.id.buttonInfo);
		btn_info.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FullscreenActivity.this, Information.class);
				startActivity(intent);
			}});
		
		
		Button btn_waterproof = (Button)this.findViewById(R.id.buttonWater);
		
		btn_waterproof.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FullscreenActivity.this, WaterproofActivity.class);
				startActivity(intent);
			}});
		
		btn_waterproof.setShadowLayer(10, 5, 5, Color.BLACK);
        
        
        
        */
        
        
        Button btn_info = (Button)this.findViewById(R.id.buttonInfo);
		btn_info.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FullscreenActivity.this, Information.class);
				startActivity(intent);
			}});
        
        
        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.buttonInfo).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
