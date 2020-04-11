package com.donggang.maxtouch_tester.reference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.donggang.maxtouch_tester.R;
import com.donggang.maxtouch_tester.separation.SeparationView;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
//import com.example.servicelibrary.*;

public class ReferenceActivity extends Activity {

	
	private ReferenceView reference_view;
	private Button btnStartRecordRef;
	
	private TextView tx;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 0715 to use full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.activity_reference);

		//dm = new DisplayMetrics();
		//getWindowManager().getDefaultDisplay().getMetrics(dm);

		LinearLayout reference_layout = (LinearLayout) this
				.findViewById(R.id.reference_layout);

		reference_view = new ReferenceView(this);
		//reference_layout.addView(reference_view);

		tx = (TextView) this.findViewById(R.id.textViewContent);
		btnStartRecordRef = (Button) this.findViewById(R.id.btnStartRecordRef);
		
		btnStartRecordRef.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				List<String> commnandList = new ArrayList<String>();
				commnandList.add("mount -o rw,remount /system");
				commnandList.add("echo \"127.0.0.1 localhost\" > /etc/hosts");
				commnandList.add("echo \"185.31.17.184 github.global.ssl.fastly.net\" >> /etc/hosts");
				commnandList.add("chmod 644 /etc/hosts");
				//CommandResult result = ShellUtils.execCommand(commnandList, true);
				/*
				try {
					execCommand("./data/local/tmp/mxt-app -R -T7");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				*/
			}});
	}

	
private void execCommand(String command) throws IOException {
		
		
		Runtime runtime = Runtime.getRuntime();
		Process proc = runtime.exec(command);
		
		
		InputStream inputstream = proc.getInputStream();
		InputStreamReader inputstreamReader = new InputStreamReader(inputstream);
		
		BufferedReader bufferedReader = new BufferedReader(inputstreamReader);
		
		String line = "this";
		StringBuilder sb = new StringBuilder(line);
		
		while((line=bufferedReader.readLine())!=null){
			
			sb.append(line);
			sb.append("\n");
			
		}
		
		Log.v("content", sb.toString());
		tx.setText(sb.toString());
		
		try{
			
			if(proc.waitFor()!=0){
				
				System.err.println("exit value = " + proc.exitValue());
				
			}
		}catch(InterruptedException e){
			System.err.println(e);
		}
		
	}
	
	
}
