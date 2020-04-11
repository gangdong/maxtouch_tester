package com.donggang.maxtouch_tester.drawline;

import java.util.ArrayList;
import java.util.List;

import com.donggang.maxtouch_tester.TouchPoint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 
 * 项目名称：MPT_App_Android <BR>
 * 类名称：DrawLineView <BR>
 * 类描述：View class to display the content of <BR>
 * com.mds.touch.mptappandroid.drawline.DrawLineActivity <BR>
 * 创建人：daviddong <BR>
 * 创建时间：Jul 3, 2014 10:35:42 AM <BR>
 * 修改人：daviddong <BR>
 * 修改时间：Jul 3, 2014 10:35:42 AM <BR>
 * 修改备注：
 * 
 * @version v0.1 <BR>
 * 
 */
public class DrawLineView extends View {

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
	public DrawLineView(Context context, DisplayMetrics dm) {
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

	public DrawLineView(Context context, AttributeSet attrs) {
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
		canvas.drawText("the screen resolution is: " + String.valueOf(widthPix)
				+ "*" + String.valueOf(heightPix), 30, 30, pt);
		canvas.drawText("DPI: " + String.valueOf(dpi), 30, 60, pt);
		canvas.drawText("Report Rate:" + String.valueOf(sum), 30, 90, pt);

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

		/*
		 * for (int i = 0; i < this.mTouchPoints.size(); i++) {
		 * 
		 * PointF p = mTouchPoints.get(i); mPaint.setColor(Color.GRAY);
		 * 
		 * canvas.drawCircle(p.x, p.y, 100, mPaint);
		 * mPaint.setColor(mColors.get(i)); canvas.drawCircle(p.x, p.y, 120,
		 * mPaint); canvas.drawText(String.valueOf(this.mTouchPoints.size()),
		 * widthPix / 2 - 75, heightPix / 2 - 75, pn);
		 * 
		 * canvas.drawText(String.valueOf(i), p.x - 110, p.y - 110, ps);
		 * 
		 * }
		 */
		// canvas.drawPath(mPaths.get(i).getPath(), mPaint);
		// canvas.drawPath(mPath, mPaint);

		/*
		 * for(int i=0;i<this.mTouchPoints.size();i++){ PointF p =
		 * mTouchPoints.get(i);
		 * 
		 * if(i==0){
		 * 
		 * mPath.moveTo(p.x, p.y); } else { PointF pf = mTouchPoints.get(i-1);
		 * mPath.quadTo(pf.x,pf.y,p.x,p.y);
		 * canvas.drawLine(pf.x,pf.y,p.x,p.y,mPaint); } }
		 */
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

			/*
			 * this.mTouchPoints.clear(); this.mPaths.clear(); for (int i = 0; i
			 * < count; i++) {
			 * 
			 * int id = event.getPointerId(i);
			 * 
			 * if (id >= count) {
			 * 
			 * return false; }
			 * 
			 * float x = event.getX(id); float y = event.getY(id);
			 * 
			 * PointF p = new PointF(x, y);
			 * 
			 * this.mTouchPoints.add(p);
			 * 
			 * }
			 */
			/*
			 * mPaths.clear(); mPath.reset(); float x = event.getX(); float y =
			 * event.getY();
			 * 
			 * mX = x; mY = y;
			 * 
			 * mPath.moveTo(x, y);
			 */

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
			Log.v("up", String.valueOf(action));
			// this.mTouchPoints.clear();
			this.touchPoints.clear();
			this.invalidate();
			return true;

		default:
			// this.mTouchPoints.clear();
			this.touchPoints.clear();
			return super.onTouchEvent(event);
		}

	}

}
