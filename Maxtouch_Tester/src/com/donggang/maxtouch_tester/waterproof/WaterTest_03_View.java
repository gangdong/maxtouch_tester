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
import android.graphics.Paint;
import android.graphics.Path;
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

public class WaterTest_03_View extends View {

	
	private List<Integer> mColors = new ArrayList<Integer>();
	// private List<PointF> mTouchPoints = new ArrayList<PointF>();
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	private List<TouchPoint> touchPoints = new ArrayList<TouchPoint>();

	private DisplayMetrics dm;

	public float currentX = 40;
	public float currentY = 50;
	public Paint p = new Paint();
	public int sum = 0;
	public int count = 0;
	public int action = 0;
	public int number = 0;
	
	private Timer nTimer = new Timer();
	private boolean timer_run = false;
	private int timer_count = 0;
	

	/**
	 * getter method get the class member dm
	 * 
	 * @author daviddong
	 * @param none
	 * @return dm DisplayMetric class instance of class member
	 */
	public DisplayMetrics getDm() {
		return dm;
	}

	/**
	 * setter method set the value to class member dm
	 * 
	 * @author daviddong
	 * @param dm
	 *            object of android.util.DisplayMetrics
	 * @return none
	 */
	public void setDm(DisplayMetrics dm) {
		this.dm = dm;
	}

	/**
	 * getter method get class member sum
	 * 
	 * @author daviddong
	 * @param none
	 * @return sum
	 */
	public int getSum() {
		return sum;
	}

	/**
	 * setter method set a value to class member sum
	 * 
	 * @author daviddong
	 * @param sum
	 *            int value
	 * @return none
	 */
	public void setSum(int sum) {
		this.sum = sum;
	}

	/**
	 * getter method get the class member count
	 * 
	 * @author daviddong
	 * @param none
	 * @return count int value
	 */
	public int getCount() {
		return count;
	}

	/**
	 * setter method set a value to class member count
	 * 
	 * @author daviddong
	 * @param count
	 *            int value
	 * @return none
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * constructor
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
	public WaterTest_03_View(Context context, DisplayMetrics dm) {
		super(context);
		// TODO Auto-generated constructor stub

		// RelativeLayout rl = new RelativeLayout(this);
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

	public WaterTest_03_View(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		// this.dm = dm;

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
	 * override onDraw method
	 * 
	 * @author daviddong
	 * @param canvas
	 *            object of android.graphics.Canvas
	 * @return none
	 */
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		Paint pt = new Paint();
		pt.setTextSize(30);
		pt.setColor(Color.RED);
		int widthPix = dm.widthPixels;
		int heightPix = dm.heightPixels;
		int dpi = dm.densityDpi;
		//canvas.drawText("the screen resolution is: " + String.valueOf(widthPix)
		//		+ "*" + String.valueOf(heightPix), 30, 30, pt);
		//canvas.drawText("DPI: " + String.valueOf(dpi), 30, 60, pt);
		//canvas.drawText("Report Rate:" + String.valueOf(sum), 30, 90, pt);

		Paint pn = new Paint();
		pn.setTextSize(300);
		pn.setColor(Color.GRAY);
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		pn.setTypeface(font);

		Paint ps = new Paint();
		ps.setTextSize(60);
		ps.setColor(Color.GRAY);

		Paint pl = new Paint();
		pl.setAntiAlias(true);
		pl.setStyle(Style.STROKE);
		pl.setStrokeWidth(1);

		for (int i = 0; i < this.touchPoints.size(); i++) {
			TouchPoint p = this.touchPoints.get(i);

			PointF point = p.getPoint();
			int id = p.getTouchId();
			Path path = p.getPath();

			mPaint.setColor(Color.GRAY);

			canvas.drawCircle(point.x, point.y, 50, mPaint);
			mPaint.setColor(mColors.get(id));
			canvas.drawCircle(point.x, point.y, 60, mPaint);
			canvas.drawText(String.valueOf(this.touchPoints.size()),
					widthPix / 2 - 75, heightPix / 2 - 75, pn);

			canvas.drawText(String.valueOf(id), point.x - (110 / 1920) * 800,
					point.y - (110 / 1920) * 800, ps);

			pl.setColor(mColors.get(id));
			canvas.drawPath(path, pl);
		}

		
		pn.setTextSize(30);
		pn.setTypeface(font);
		pn.setColor(Color.BLUE);
		canvas.drawText("Palm and Cheek water spray test.", 100, 60, pn);
		pn.setColor(Color.GRAY);
		canvas.drawText("Time Eclipse:" + String.valueOf(this.timer_count)+"s",
				100, 100, pn);
		
	}

	/**
	 * override onTouchEvent method
	 * 
	 * @author daviddong
	 * @param event
	 *            object of android.view.MotionEvent
	 * @return b true touch event processed fals touch event no processed
	 */
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		this.count++;
		Log.v("count", String.valueOf(this.count));

		int action = event.getActionMasked();
		switch (action) {

		case MotionEvent.ACTION_DOWN:

			Log.v("down", String.valueOf(action));

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


			int actionIndex_up = event.getActionIndex();
			int id_up = event.getPointerId(actionIndex_up);
			Log.v("id", String.valueOf(id_up));

			for (int i = 0; i < touchPoints.size(); i++) {
				int id_list = touchPoints.get(i).getTouchId();
				if (id_up == id_list) {

					touchPoints.remove(i);
				}
			}
			this.invalidate();
			return true;
		case MotionEvent.ACTION_MOVE:


			for (int i = 0; i < this.touchPoints.size(); i++) {

				TouchPoint tp_move = this.touchPoints.get(i);
				int p_id = event.getPointerId(i);
				float x_move = event.getX(i);
				float y_move = event.getY(i);

				Path path_move = touchPoints.get(i).getPath();
				float x_previous = touchPoints.get(i).getPoint().x;
				float y_previous = touchPoints.get(i).getPoint().y;

				final float dx = Math.abs(x_move - x_previous);
				final float dy = Math.abs(y_move - y_previous);

				if (dx >= 3 || dy >= 3) {
					float cX = (x_move + x_previous) / 2;
					float cY = (y_move + y_previous) / 2;
					path_move.quadTo(x_previous, y_previous, cX, cY);


				}


				PointF p_move = new PointF(x_move, y_move);
				tp_move.setTouchId(p_id);
				tp_move.setPoint(p_move);
			}


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
}
