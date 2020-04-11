package com.donggang.maxtouch_tester.waterproof;



import java.util.ArrayList;
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
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;
import android.os.Message;


public class WaterTest_02_View extends View {

	private DisplayMetrics dm;
	
	private List<Integer> mColors = new ArrayList<Integer>();
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	
	private Timer nTimer = new Timer();
	private boolean timer_run = false;
	private int timer_count = 0;
	
	private List<TouchPoint> touchPoints = new ArrayList<TouchPoint>();
	

	PointF[] points_original_y;
	PointF[] points_terminal_y;
	PointF[] points_original_x;
	PointF[] points_terminal_x;
	PointF[] test_points;
	float[] width_array;
	float[] heigh_array;

	PointF point_touch = null;

	/**
	 * Override constructor
	 * 
	 * @author daviddong
	 * @param context
	 *            object of android.content.context
	 * @param dm
	 *            object android.util.DisplayMetics
	 * @return none
	 * @see android.content.context
	 * @see android.util.DisplayMetics
	 */

	public WaterTest_02_View(Context context, DisplayMetrics dm) {

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
	 * Override constructor
	 * 
	 * @author daviddong
	 * @param context
	 *            object of android.content.context
	 * @return none
	 * @see android.content.context
	 * 
	 */

	public WaterTest_02_View(Context context) {
		super(context);

		// TODO Auto-generated constructor stub

	}

	/**
	 * Override constructor
	 * 
	 * @author daviddong
	 * @param context
	 *            object of android.content.context
	 * @param attrs
	 *            object android.util.AttributeSet
	 * @return none
	 * @see android.content.context
	 * @see android.util.AttributeSet
	 * 
	 */
	public WaterTest_02_View(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Override onDraw
	 * 
	 * @author daviddong
	 * @param canvas
	 *            object of android.graphics.canvas
	 * @return none
	 * @see android.graphics.canvas
	 * 
	 */
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

		this.initDataArray();


		for (int i = 0; i < 5; i++) {

			Path path = new Path();
			path.moveTo(points_original_y[i].x, points_original_y[i].y);
			path.lineTo(points_terminal_y[i].x, points_terminal_y[i].y);
			canvas.drawPath(path, p_dashline);
		}

		
		for (int i = 0; i < 5; i++) {

			Path path = new Path();
			path.moveTo(points_original_x[i].x, points_original_x[i].y);
			path.lineTo(points_terminal_x[i].x, points_terminal_x[i].y);
			canvas.drawPath(path, p_dashline);
		}

		
		Paint p_test_point = new Paint();
		p_test_point.setColor(Color.RED);
		p_test_point.setStyle(Style.STROKE);

		for (int i = 0; i < test_points.length; i++) {

			canvas.drawCircle(test_points[i].x, test_points[i].y, 54,
					p_test_point);

		}

		
		p_test_point.setStyle(Style.STROKE);

		for (int i = 0; i < test_points.length; i++) {

			canvas.drawCircle(test_points[i].x, test_points[i].y, 36,
					p_test_point);

		}
		
		p_test_point.setStyle(Style.FILL);

		for (int i = 0; i < test_points.length; i++) {

			canvas.drawCircle(test_points[i].x, test_points[i].y, 6,
					p_test_point);

		}

		Paint p_str = new Paint();
		p_str.setTextSize(30);
		p_str.setColor(Color.BLACK);

		float text_y = heigh_array[0] + (heigh_array[1] - heigh_array[0]) / 3;
		float text_x = width_array[1];

		
	
		
		Paint pn = new Paint();
		pn.setTextSize(30);
		pn.setColor(Color.BLUE);
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		pn.setTypeface(font);
		canvas.drawText("Touch Single Finger Water Spray Test.", 100, 60, pn);
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
			
			canvas.drawCircle(this.touchPoints.get(i).getPoint().x, this.touchPoints.get(i).getPoint().y, 2, p_test_point);
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
			
			if(this.checkPointII(tp, this.test_points)){
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

	/**
	 * Override onTouchEvent
	 * 
	 * @author daviddong
	 * @param event
	 *            object of android.view.Motionevent
	 * @return b true event is processed false event is no processed
	 * @see android.view.Motionevent
	 * 
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// return super.onTouchEvent(event);

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
		//heigh_array = new float[] { 0, heigh / 5, heigh * 2 / 5, heigh * 3 / 5,
		//		heigh * 4 / 5, heigh };
		
		//ark: 0714: change to 13 points
		heigh_array = new float[] { 0, heigh / 4, heigh * 2 / 4, heigh * 3 / 4,
				 heigh };

		points_original_y = new PointF[] {
				new PointF(width_array[0], heigh_array[0]),
				new PointF(width_array[0], heigh_array[1]),
				new PointF(width_array[0], heigh_array[2]),
				new PointF(width_array[0], heigh_array[3]),
				new PointF(width_array[0], heigh_array[4]),
				//new PointF(width_array[0], heigh_array[5]) 
				};

		points_terminal_y = new PointF[] {
				new PointF(width_array[4], heigh_array[0]),
				new PointF(width_array[4], heigh_array[1]),
				new PointF(width_array[4], heigh_array[2]),
				new PointF(width_array[4], heigh_array[3]),
				new PointF(width_array[4], heigh_array[4]),
				//new PointF(width_array[4], heigh_array[5]), 
				};

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
				new PointF(width_array[0], heigh_array[0]),
				new PointF(width_array[2], heigh_array[0]),
				new PointF(width_array[4], heigh_array[0]),
				new PointF(width_array[1], heigh_array[1]),
				new PointF(width_array[3], heigh_array[1]),
				new PointF(width_array[0], heigh_array[2]),
				new PointF(width_array[2], heigh_array[2]),
				new PointF(width_array[4], heigh_array[2]),
				new PointF(width_array[1], heigh_array[3]),
				new PointF(width_array[3], heigh_array[3]),
				new PointF(width_array[0], heigh_array[4]),
				new PointF(width_array[2], heigh_array[4]),
				new PointF(width_array[4], heigh_array[4]),
				//new PointF(width_array[1], heigh_array[5]),
				//new PointF(width_array[3], heigh_array[5]), 
				};

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
	
	
	private boolean checkPointII(PointF point, PointF[] refPoints) {

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
	
	private CheckData checkPoint(PointF point, PointF[] refPoints) {

		CheckData checkData = new CheckData();

		for (int i = 0; i < refPoints.length; i++) {

			float x = refPoints[i].x;
			float y = refPoints[i].y;
			
			double distance_p = Math.sqrt(Math.pow(point.x-x, 2)+Math.pow(point.y-y, 2));
			
			//ark: 0714 fix the bug to calculate a circle
			if (distance_p < 55 ){

			//if ((point.x > x - 51) && (point.x < x + 51)) {

				//if ((point.y > y - 51) && (point.y < y + 51)) {

				/*
					float deltaX = Math.abs(x - point.x);
					float deltaY = Math.abs(y - point.y);

					float xd = this.dm.xdpi;
					float yd = this.dm.ydpi;

					float x_milimeter = (float) ((deltaX / xd) * 25.4);
					float y_milimeter = (float) ((deltaY / yd) * 25.4);
				*/
					//float distance = (float) Math.sqrt(Math.pow(x_milimeter, 2)
					//		+ Math.pow(y_milimeter, 2));
					
					float dpi = this.dm.densityDpi;
				
					float distance = (float) ((distance_p/dpi)*25.4);
					checkData.setDistance(distance);
					checkData.setIndex(i);

					PointF newPoint = new PointF();
					newPoint.x = point.x;
					newPoint.y = point.y;
					this.point_touch = newPoint;

				//}
			} else {

			}

		}
		return checkData;
	}
}

class CheckData {

	private int index;
	private float distance;

	public CheckData() {
		index = 0;
		distance = 0;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}
	
}
