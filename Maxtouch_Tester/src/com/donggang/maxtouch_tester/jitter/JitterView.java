package com.donggang.maxtouch_tester.jitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

/**
 * 
 * 项目名称：MPT_App_Android <BR>
 * 类名称：JitterView <BR>
 * 类描述：View class to display the content of com.mds.touch.mptappandroid.jitter <BR>
 * 创建人：daviddong <BR>
 * 创建时间：Jul 3, 2014 2:47:33 PM <BR>
 * 修改人：daviddong <BR>
 * 修改时间：Jul 3, 2014 2:47:33 PM <BR>
 * 修改备注： <BR>
 * 
 * @version v0.1 <BR>
 * 
 */
public class JitterView extends View {

	private DisplayMetrics dm;

	private Timer nTimer = new Timer();
	private boolean timer_run = false;
	private int timer_count = 0;
	private int hold_time = 1; // ark: 0716 to contorl holding time

	private PointF[] points_original_y;
	private PointF[] points_terminal_y;
	private PointF[] points_original_x;
	private PointF[] points_terminal_x;
	private PointF[] test_points;
	private float[] width_array;
	private float[] heigh_array;

	private List<PointF> mPoints = new ArrayList<PointF>();

	private int touch_count = 0;

	private List<Float> jitter_array = new ArrayList<Float>();

	private PointF basePoint = new PointF();

	private List<Integer> testProc = new ArrayList<Integer>();

	private ProgressBar progressHorizontal;

	// ark: 0716 create a timer
	// private Long startTime;
	// private Handler handler = new Handler();

	/**
	 * override constructor
	 * 
	 * @author daviddong
	 * @param context
	 *            object of android.content.Context
	 * @param dm
	 *            object of android.util.DisplayMetrics
	 * @return none
	 * @see android.content.Context
	 * @see android.util.DisplayMetrics
	 */
	public JitterView(Context context, DisplayMetrics dm, ProgressBar p) {
		super(context);
		// TODO Auto-generated constructor stub
		this.dm = dm;

		initDataArray();

		for (int i = 0; i < test_points.length; i++) {

			this.testProc.add(0);
		}

		
	}

	/**
	 * getter method - get class member timer_count
	 * 
	 * @author daviddong
	 * @param none
	 * @return timer_count
	 * 
	 */
	public int getTimer_count() {
		return timer_count;
	}

	/**
	 * setter method - set class member timer_count
	 * 
	 * @author daviddong
	 * @param timer_count
	 *            int value
	 * @return none
	 * 
	 */
	public void setTimer_count(int timer_count) {
		this.timer_count = timer_count;
	}

	/**
	 * getter method - get class member timer_run
	 * 
	 * @author daviddong
	 * @param none
	 * @return timer_run
	 * 
	 */
	public boolean isTimer_run() {
		return timer_run;
	}

	/**
	 * setter method - set class member timer_count
	 * 
	 * @author daviddong
	 * @param timer_run
	 *            boolean value
	 * @return none
	 * 
	 */
	public void setTimer_run(boolean timer_run) {
		this.timer_run = timer_run;
	}

	/**
	 * override onDraw method
	 * 
	 * @author daviddong
	 * @param canvas
	 *            object of android.graphics.Canvas
	 * @return none
	 * @see android.graphics.Canvas
	 */
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		Paint p_dashline = new Paint(Paint.ANTI_ALIAS_FLAG);
		p_dashline.setAntiAlias(true);
		p_dashline.setStyle(Style.STROKE);
		p_dashline.setColor(Color.BLUE);
		p_dashline.setStrokeWidth(1);
		PathEffect effects = new DashPathEffect(new float[] { 15, 15, 15, 15 },
				15);
		p_dashline.setPathEffect(effects);

		initDataArray();


		for (int i = 0; i < points_original_y.length; i++) {

			Path path = new Path();
			path.moveTo(points_original_y[i].x, points_original_y[i].y);
			path.lineTo(points_terminal_y[i].x, points_terminal_y[i].y);
			canvas.drawPath(path, p_dashline);
		}

		for (int i = 0; i < points_original_x.length; i++) {

			Path path = new Path();
			path.moveTo(points_original_x[i].x, points_original_x[i].y);
			path.lineTo(points_terminal_x[i].x, points_terminal_x[i].y);
			canvas.drawPath(path, p_dashline);
		}

		Paint p_test_point = new Paint();
		p_test_point.setColor(Color.RED);
		p_test_point.setStyle(Style.STROKE);

		// ark: 0716 change to 9mm outter and 6mm inner circle
		for (int i = 0; i < test_points.length; i++) {

			canvas.drawCircle(test_points[i].x, test_points[i].y, 54,
					p_test_point);

		}

		for (int i = 0; i < test_points.length; i++) {

			canvas.drawCircle(test_points[i].x, test_points[i].y, 36,
					p_test_point);

		}

		p_test_point.setStyle(Style.FILL);

		for (int i = 0; i < test_points.length; i++) {

			canvas.drawCircle(test_points[i].x, test_points[i].y, 6,
					p_test_point);

		}

		Paint pn = new Paint();
		pn.setTextSize(100);
		pn.setColor(Color.GRAY);
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		pn.setTypeface(font);
		canvas.drawText("Time: " + String.valueOf(this.timer_count),
				this.getWidth() / 2 - 160, this.getHeight() / 2 - 75, pn);

		for (int i = 0; i < this.mPoints.size(); i++) {

			canvas.drawCircle(mPoints.get(i).x, mPoints.get(i).y, 2,
					p_test_point);
		}

		Paint p_str = new Paint();
		p_str.setTextSize(30);
		p_str.setColor(Color.BLACK);

		float text_y = heigh_array[0] + (heigh_array[1] - heigh_array[0]) / 2;
		float text_x = width_array[0] + 30;

		canvas.drawText("Jitter Number: " + String.valueOf(touch_count),
				text_x, text_y, p_str);
		canvas.drawText("Place 1 finger on the circle and hold for 1 second",
				text_x, text_y - 80, p_str);

		if (timer_count == hold_time) {

			// List<Float> distances = this.getMaxDistance(mPoints);
			// Collections.sort(distances);
			// float maxValue = distances.get(distances.size()-1);
			// canvas.drawText("Max distance: " + String.valueOf(maxValue) +
			// " mm", text_x, text_y+100, p_str);
			if (jitter_array.isEmpty()) {

			} else {
				Collections.sort(this.jitter_array);
				float maxValue = this.jitter_array
						.get(this.jitter_array.size() - 1);
				canvas.drawText("Max distance: " + String.valueOf(maxValue)
						+ " mm", text_x, text_y + 40, p_str);
				
				
				
				int completeProc = 0;
				for (int i = 0; i < this.testProc.size(); i++) {

					if (testProc.get(i) == 1) {

						completeProc++;

					}
				}

				float proc =  ((float)completeProc /(float) this.testProc.size()) ;
				int ii = (int) (proc *100);
				canvas.drawText("Test Process: " + String.valueOf(ii) + "%", text_x,
						text_y + 140, p_str);
				
				Message msg = new Message();
				Bundle bundle = new Bundle();
				msg.what = 1;
				bundle.putInt("process", ii);
				msg.setData(bundle);
				JitterUIHandler.getInstances().sendMessage(msg);
				
				
				
			}

		}



	}

	private boolean checkPoint(PointF point, PointF[] refPoints) {

		// CheckPoint checkData = new CheckPoint();

		for (int i = 0; i < refPoints.length; i++) {

			float x = refPoints[i].x;
			float y = refPoints[i].y;

			// ark: 0716 fix the calculation bug
			double distance = Math.sqrt(Math.pow(point.x - x, 2)
					+ Math.pow(point.y - y, 2));

			if (distance < 55) {
				// if ((point.x > x - 60) && (point.x < x + 60)) {

				// if ((point.y > y - 60) && (point.y < y + 60)) {

				PointF newPoint = new PointF();
				newPoint.x = point.x;
				newPoint.y = point.y;
				mPoints.add(newPoint);

				this.testProc.set(i, 1);
				return true;
				// }
			} else {

			}

		}
		return false;
	}

	/**
	 * override onTouchEvent method
	 * 
	 * @author daviddong
	 * @param event
	 *            object of android.view.MotionEvent
	 * @return b true if event processed false if event no processed
	 * @see android.view.MotionEvent
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		int action = event.getActionMasked();

		switch (action) {

		case MotionEvent.ACTION_DOWN:

			this.touch_count = 0;
			this.mPoints.clear();
			this.timer_run = true;
			this.timer_count = 0;
			nTimer = new Timer();
			basePoint.set(event.getX(), event.getY());
			jitter_array.clear();

			nTimer.schedule(new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);
				}
			}, 1000, 1000);

			return true;

		case MotionEvent.ACTION_UP:

			this.timer_run = false;
			this.timer_count = 0;
			
			nTimer.cancel();
			return true;

		case MotionEvent.ACTION_MOVE:

			int count = event.getPointerCount();

			if (count > 1) {
				nTimer.cancel();
			} else {
				PointF touchPoint = new PointF(event.getX(), event.getY());

				if (this.checkPoint(touchPoint, test_points)) {

					if (this.checkPoint(this.basePoint, test_points)) {

						float distance = this.getDistance(this.basePoint,
								touchPoint);

						this.jitter_array.add(distance);
						this.basePoint.set(touchPoint);
					} else {

						this.basePoint.set(touchPoint);
					}

					touch_count++;
				}
				;

				if (timer_count < hold_time) {

					this.invalidate();
				}
			}

			return true;

		default:
			return super.onTouchEvent(event);

		}

	}

	private void initDataArray() {

		float width = this.getWidth();
		float heigh = this.getHeight();

		// float width = this.dm.widthPixels;
		// float heigh = this.dm.heightPixels;

		width_array = new float[] { 0, width / 4, width * 2 / 4, width * 3 / 4,
				width };
		heigh_array = new float[] { 0, heigh / 4, heigh * 2 / 4, heigh * 3 / 4,
				heigh };

		points_original_y = new PointF[] {
				new PointF(width_array[0], heigh_array[0]),
				new PointF(width_array[0], heigh_array[1]),
				new PointF(width_array[0], heigh_array[2]),
				new PointF(width_array[0], heigh_array[3]),
				new PointF(width_array[0], heigh_array[4]) };

		points_terminal_y = new PointF[] {
				new PointF(width_array[4], heigh_array[0]),
				new PointF(width_array[4], heigh_array[1]),
				new PointF(width_array[4], heigh_array[2]),
				new PointF(width_array[4], heigh_array[3]),
				new PointF(width_array[4], heigh_array[4]) };

		points_original_x = new PointF[] {
				new PointF(width_array[0], heigh_array[0]),
				new PointF(width_array[1], heigh_array[0]),
				new PointF(width_array[2], heigh_array[0]),
				new PointF(width_array[3], heigh_array[0]),
				new PointF(width_array[4], heigh_array[0]), };

		points_terminal_x = new PointF[] {
				new PointF(width_array[0], heigh_array[4]),
				new PointF(width_array[1], heigh_array[4]),
				new PointF(width_array[2], heigh_array[4]),
				new PointF(width_array[3], heigh_array[4]),
				new PointF(width_array[4], heigh_array[4]), };

		test_points = new PointF[] {

		new PointF(width_array[1], heigh_array[1]),
				new PointF(width_array[3], heigh_array[1]),
				new PointF(width_array[2], heigh_array[2]),
				new PointF(width_array[1], heigh_array[3]),
				new PointF(width_array[3], heigh_array[3]), };

	}

	@SuppressLint("HandlerLeak")
	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case 1:
				if (timer_count < hold_time) {

					timer_count++;
					invalidate();
				}
				;
				break;
			}
			super.handleMessage(msg);
		}

	};

	/* */
	@SuppressWarnings("unused")
	private List<Float> getMaxDistance(List<PointF> refPoints) {

		List<Float> results = new ArrayList<Float>();
		if (refPoints.isEmpty()) {

		} else {
			for (int i = 0; i < refPoints.size() - 1; i++) {

				for (int j = 1; j < refPoints.size() - i; j++) {

					float distance = this.getDistance(refPoints.get(i),
							refPoints.get(i + j));
					results.add(distance);
				}
			}
		}

		return results;
	}

	private float getDistance(PointF point1, PointF point2) {

		float a;

		float deltaX = (float) (((point1.x - point2.x) * 25.4) / this.dm.xdpi);
		float deltaY = (float) (((point1.y - point2.y) * 25.4) / this.dm.ydpi);

		a = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

		return a;
	}

}
