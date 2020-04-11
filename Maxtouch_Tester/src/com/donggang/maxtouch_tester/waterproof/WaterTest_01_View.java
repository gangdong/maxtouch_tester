package com.donggang.maxtouch_tester.waterproof;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



import com.donggang.maxtouch_tester.TouchPoint;

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
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class WaterTest_01_View extends View {

	private DisplayMetrics dm;
	
	private List<Integer> mColors = new ArrayList<Integer>();
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	
	private Timer nTimer = new Timer();
	private boolean timer_run = false;
	private int timer_count = 0;

	private PointF[] points_original_y;
	private PointF[] points_terminal_y;
	private PointF[] points_original_x;
	private PointF[] points_terminal_x;
	private PointF[] test_points;
	private float[] width_array;
	private float[] heigh_array;

	private List<TouchPoint> touchPoints = new ArrayList<TouchPoint>();

	private int touch_count = 0;
	
	private List<Float> jitter_array = new ArrayList<Float>();
	
	private PointF basePoint = new PointF();
	
	
	public WaterTest_01_View(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public WaterTest_01_View(Context context,DisplayMetrics dm){
		
		super(context);
		this.dm = dm;
		
		mColors.add(Color.RED);
		mColors.add(Color.BLUE);
		mColors.add(Color.GREEN);
		mColors.add(Color.LTGRAY);
		mColors.add(Color.CYAN);
		mColors.add(Color.YELLOW);
		mColors.add(Color.BLACK);
		mColors.add(Color.MAGENTA);
		mColors.add(Color.TRANSPARENT);
		mColors.add(Color.LTGRAY);
		mColors.add(Color.WHITE);

		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(10);
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
	 * @param timer_count int value 
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
	 * @param timer_run boolean value
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
	 * @param canvas object of android.graphics.Canvas
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

		for (int i = 0; i < test_points.length; i++) {

			canvas.drawCircle(test_points[i].x, test_points[i].y, 60,
					p_test_point);

		}

		p_test_point.setStyle(Style.FILL);

		for (int i = 0; i < test_points.length; i++) {

			canvas.drawCircle(test_points[i].x, test_points[i].y, 6,
					p_test_point);

		}

		
		
		Paint pn = new Paint();
		pn.setTextSize(30);
		pn.setColor(Color.BLUE);
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		pn.setTypeface(font);
		canvas.drawText("Touch Single Finger Water Drop Test.", 100, 60, pn);
		pn.setColor(Color.GRAY);
		canvas.drawText("Time Eclipse:" + String.valueOf(this.timer_count)+"s",
				100, 100, pn);
		
		
		pn.setTextSize(300);
		canvas.drawText(String.valueOf(this.touchPoints.size()),
				this.getWidth()/2-75, this.getHeight()/2-75, pn);
		
		Paint pl = new Paint();
		pl.setAntiAlias(true);
		pl.setStyle(Style.STROKE);
		pl.setStrokeWidth(1);
		
		for(int i=0;i<this.touchPoints.size();i++){
			
			//canvas.drawCircle(this.touchPoints.get(i).getPoint().x, this.touchPoints.get(i).getPoint().y, 2, p_test_point);
			mPaint.setColor(Color.GRAY);

			canvas.drawCircle(this.touchPoints.get(i).getPoint().x, this.touchPoints.get(i).getPoint().y, 50, mPaint);
			mPaint.setColor(mColors.get(this.touchPoints.get(i).getTouchId()));
			canvas.drawCircle(this.touchPoints.get(i).getPoint().x, this.touchPoints.get(i).getPoint().y, 60, mPaint);
			Path path = this.touchPoints.get(i).getPath();
			pl.setColor(mColors.get(this.touchPoints.get(i).getTouchId()));
			
			canvas.drawPath(path, pl);
		}
		
		
		boolean isInBound = true;
		for(int i=0;i<this.touchPoints.size();i++){
			
			PointF tp = this.touchPoints.get(i).getPoint();
			
			if(this.checkPoint(tp, this.test_points)){
				isInBound = true;
			}else{
				isInBound = false;
				break;
			};
			
		}
		
		if(isInBound){
			pn.setColor(Color.GRAY);
			pn.setTextSize(30);
			canvas.drawText("Touch is in bound.", 100, 140, pn);
		}
		else{
			
			pn.setColor(Color.RED);
			pn.setTextSize(30);
			canvas.drawText("Touch is out of bound.", 100, 140, pn);
		}
		
		
	}

	private boolean checkPoint(PointF point, PointF[] refPoints) {

		 //CheckPoint checkData = new CheckPoint();
		 
		for (int i = 0; i < refPoints.length; i++) {

			float x = refPoints[i].x;
			float y = refPoints[i].y;

			if ((point.x > x - 60) && (point.x < x + 60)) {

				if ((point.y > y - 60) && (point.y < y + 60)) {

					PointF newPoint = new PointF();
					newPoint.x = point.x;
					newPoint.y = point.y;
					//mPoints.add(newPoint);
					return true;
				}
			} else {

			}

		}
		return false;
	}

	/**
	 * override onTouchEvent method
	 * @author daviddong
	 * @param event object of android.view.MotionEvent
	 * @return b true if event processed false if event no processed
	 * @see android.view.MotionEvent
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub


		int action = event.getActionMasked();
		switch (action) {

		case MotionEvent.ACTION_DOWN:


			this.touchPoints.clear();

			int count = event.getPointerCount();

			for (int i = 0; i < count; i++) {

				int id = event.getPointerId(i);

				if (id >= count) {

					return false;
				}

				float x = event.getX(id);
				float y = event.getY(id);

				PointF p = new PointF(x, y);

				Path path = new Path();
				path.moveTo(x, y);

				TouchPoint tp = new TouchPoint();
				tp.setPoint(p);
				tp.setTouchId(id);
				tp.setPath(path);
				tp.setTouchTime(event.getEventTime());

				this.touchPoints.add(tp);

			}

			this.timer_run = true;
			this.timer_count = 0;
			nTimer = new Timer();
			nTimer.schedule(new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);
				}
			}, 1000, 1000);
			
			this.invalidate();
			return true;
		case MotionEvent.ACTION_POINTER_DOWN:
			Log.v("point", "point down");

			Log.v("action_index", String.valueOf(event.getActionIndex()));
			// Log.v("number", String.valueOf(event.getPointerCount()));

			int actionIndex = event.getActionIndex();
			int id = event.getPointerId(actionIndex);
			Log.v("id", String.valueOf(id));

			float x = event.getX(actionIndex);
			float y = event.getY(actionIndex);

			PointF p = new PointF(x, y);

			Path path = new Path();
			path.moveTo(x, y);

			TouchPoint tp = new TouchPoint();
			tp.setPoint(p);
			tp.setTouchId(id);
			tp.setPath(path);
			tp.setTouchTime(event.getEventTime());

			if (id > touchPoints.size()) {
				this.touchPoints.add(tp);
			} else {

				this.touchPoints.add(id, tp);
			}

			this.invalidate();
			return true;
		case MotionEvent.ACTION_POINTER_UP:

			Log.v("point", "point up");
			Log.v("action_index", String.valueOf(event.getActionIndex()));
			// Log.v("number", String.valueOf(event.getPointerCount()));

			int actionIndex_up = event.getActionIndex();
			int id_up = event.getPointerId(actionIndex_up);
			Log.v("id", String.valueOf(id_up));
			// float x_up = event.getX(id_up);
			// float y_up = event.getY(id_up);

			// PointF p_up = new PointF(x_up, y_up);

			// TouchPoint tp_up = new TouchPoint();
			// tp_up.setPoint(p_up);
			// tp_up.setTouchId(id_up);

			for (int i = 0; i < touchPoints.size(); i++) {
				int id_list = touchPoints.get(i).getTouchId();
				if (id_up == id_list) {

					touchPoints.remove(i);
				}
			}
			this.invalidate();
			return true;
		case MotionEvent.ACTION_MOVE:
			// Log.v("move", String.valueOf(event.getPointerCount()));

			// int cur = event.getPointerCount();

			for (int i = 0; i < this.touchPoints.size(); i++) {

				TouchPoint tp_move = this.touchPoints.get(i);
				// int id_move = tp_move.getTouchId();
				int p_id = event.getPointerId(i);
				float x_move = event.getX(i);
				float y_move = event.getY(i);

				Path path_move = touchPoints.get(i).getPath();
				float x_previous = touchPoints.get(i).getPoint().x;
				float y_previous = touchPoints.get(i).getPoint().y;

				final float dx = Math.abs(x_move - x_previous);
				final float dy = Math.abs(y_move - y_previous);

				if (dx >= 3 || dy >= 3) {
					// 设置贝塞尔曲线的操作点为起点和终点的一半
					float cX = (x_move + x_previous) / 2;
					float cY = (y_move + y_previous) / 2;
					//
					// 二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
					path_move.quadTo(x_previous, y_previous, cX, cY);

					// 第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
					// mX = x1;
					// mY = y1;

				}

				// path_move.quadTo(x_previous, y_previous, x_move, y_move);

				PointF p_move = new PointF(x_move, y_move);
				tp_move.setTouchId(p_id);
				tp_move.setPoint(p_move);
				// tp_move.setPath(path_move);
			}
			/*
			 * int size = event.getHistorySize();
			 * 
			 * 
			 * this.mTouchPoints.clear();
			 * 
			 * for (int i = 0; i < count; i++) {
			 * 
			 * int id = event.getPointerId(i);
			 * 
			 * if (id >= count) {
			 * 
			 * continue; }
			 * 
			 * float x2 = event.getX(id); float y2 = event.getY(id);
			 * 
			 * PointF p = new PointF(x2, y2); this.mTouchPoints.add(p);
			 * 
			 * }
			 */
			// Log.v("num", String.valueOf(mPaths.size()));
			// Log.v("aumu", String.valueOf(mTouchPoints.size()));
			// final float x1 = event.getX();
			// final float y1 = event.getY();

			// final float previousX = mX;
			// final float previousY = mY;

			// final float dx = Math.abs(x1 - previousX);
			// final float dy = Math.abs(y1 - previousY);

			// 两点之间的距离大于等于3时，生成贝塞尔绘制曲线
			// if (dx >= 3 || dy >= 3)
			// {
			// 设置贝塞尔曲线的操作点为起点和终点的一半
			// float cX = (x1 + previousX) / 2;
			// float cY = (y1 + previousY) / 2;
			//
			// 二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
			// mPath.quadTo(previousX, previousY, cX, cY);

			// 第二次执行时，第一次结束调用的坐标值将作为第二次调用的初始坐标值
			// mX = x1;
			// mY = y1;

			// }

			this.invalidate();
			return true;

		case MotionEvent.ACTION_UP:
			
			this.timer_run = false;
			this.timer_count = 0;
			nTimer.cancel();
			
			this.touchPoints.clear();
			this.invalidate();
			return true;

		default:
			// this.mTouchPoints.clear();
			this.touchPoints.clear();
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
				//if (timer_count < 5) {

					timer_count++;
					invalidate();
				//}
				//;
				break;
			}
			super.handleMessage(msg);
		}

	};
	
	/* */
	@SuppressWarnings("unused")
	private List<Float> getMaxDistance(List<PointF> refPoints){
		
		List<Float> results = new ArrayList<Float>();
		if(refPoints.isEmpty()){
			
		}else{
			for(int i=0;i<refPoints.size()-1;i++){
				
				for(int j=1;j<refPoints.size()-i;j++){
					
					float distance = this.getDistance(refPoints.get(i), refPoints.get(i+j));
					results.add(distance);
				}
			}
		}
		
		return results;
	}
	
	
	private float getDistance(PointF point1,PointF point2){
		
		float a;
		
		float deltaX = (float) (((point1.x - point2.x)*25.4)/this.dm.xdpi);
		float deltaY = (float) (((point1.y - point2.y)*25.4)/this.dm.ydpi);
		
		
		a = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		
		return a;
	}
	
	
	
	
	
}
