package com.donggang.maxtouch_tester.jitter;

import com.donggang.maxtouch_tester.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**   
*    
* ��Ŀ���ƣ�MPT_App_Android  <BR> 
* �����ƣ�JitterActivity   <BR>
* �������� Activity class for jitter test <BR>
* �����ˣ�daviddong   <BR>
* ����ʱ�䣺Jul 3, 2014 2:45:34 PM   <BR>
* �޸��ˣ�daviddong   <BR>
* �޸�ʱ�䣺Jul 3, 2014 2:45:34 PM   <BR>
* �޸ı�ע��   <BR>
* @version   v0.1 <BR>
*    
*/ 
public class JitterActivity extends Activity {

	private JitterView jitter_view;
	private DisplayMetrics dm;

	private JitterUIHandler uiHandler = JitterUIHandler.getInstances();
	
	private ProgressBar progressHorizontal;
	/**
	 * constructor
	 * 
	 * @author daviddong
	 * @param none
	 * @return none
	 */
	public JitterActivity() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public ProgressBar getProgressHorizontal() {
		return progressHorizontal;
	}




	public void setProgressHorizontal(ProgressBar progressHorizontal) {
		this.progressHorizontal = progressHorizontal;
	}




	/**
	 * override onCreate
	 * 
	 * @author daviddong
	 * @param savedInstanceState object of android.os.Bundle
	 * @return none
	 * @see android.os.Bundle
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_PROGRESS);
		
		//ark: 0715 to use full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			    WindowManager.LayoutParams.FLAG_FULLSCREEN);
			    
			    
		this.setContentView(R.layout.activity_jitter);

		LinearLayout jitter_accuracy = (LinearLayout) this
				.findViewById(R.id.jitter_layout);
		
		
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		jitter_view = new JitterView(this,dm,this.progressHorizontal);
		jitter_accuracy.addView(jitter_view);
		
		
		
		setProgressBarVisibility(true);
		progressHorizontal = (ProgressBar) findViewById(R.id.progressBar1);
		progressHorizontal.setMax(100);
		progressHorizontal.setProgress(0);
		
		this.uiHandler.setJa(this);
		
	}

}

