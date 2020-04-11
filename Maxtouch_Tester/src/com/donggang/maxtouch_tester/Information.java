package com.donggang.maxtouch_tester;

import com.donggang.maxtouch_tester.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Information extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		TextView text_version;
		TextView text_build;
		TextView text_contact;
		TextView text_creater;
		
		String version = "0.1";
		String build = "20140725";
		String contact = "http:\\www.microsoft.com";
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	    WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
		setContentView(R.layout.information);
		//LinearLayout information = (LinearLayout) this
		//		.findViewById(R.id.information);
		
		
		text_version = (TextView) findViewById(R.id.version);
		text_version.setText(version);
		//text_version.
		
		text_build = (TextView) findViewById(R.id.build);
		text_build.setText(build);
		
		text_contact = (TextView) findViewById(R.id.contact);
		text_contact.setText(contact);
		
		text_creater = (TextView) findViewById(R.id.name);
		text_creater.setText("David Dong");
	    
		
	}
	


}
