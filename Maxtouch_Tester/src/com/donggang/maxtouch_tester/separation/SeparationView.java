package com.donggang.maxtouch_tester.separation;

import java.util.ArrayList;
import java.util.List;



import com.donggang.maxtouch_tester.TouchPoint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**   
*    
* 项目名称：MPT_App_Android  <BR> 
* 类名称：SeparationView   <BR>
* 类描述：View class for touch separation activity  <BR>
* 创建人：daviddong   <BR>
* 创建时间：Jul 7, 2014 10:38:13 AM   <BR>
* 修改人：daviddong   <BR>
* 修改时间：Jul 7, 2014 10:38:13 AM   <BR>
* 修改备注：   <BR>
* @version   v0.1 <BR>
*    
*/ 
public class SeparationView extends View {

	private List<Integer> mColors = new ArrayList<Integer>();
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	private List<TouchPoint> touchPoints = new ArrayList<TouchPoint>();

	public float currentX = 40;
	public float currentY = 50;
	public Paint p = new Paint();
	public int sum = 0;
	public int count = 0;
	public int action = 0;
	public int number = 0;

	float distance = 0;
	float separation = 0;
	boolean is2touch = false;

	private DisplayMetrics dm;

	/**
	 * constructor
	 * @author daviddong
	 * @param context object of android.content.Context
	 * @param dm object of android.util.DisplayMetrics
	 * @return none
	 * @see android.content.Context
	 * @see android.util.DisplayMetrics
	 */
	public SeparationView(Context context, DisplayMetrics dm) {
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

	public SeparationView(Context context, AttributeSet attrs) {
		super(context, attrs);
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

	public SeparationView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
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
	 * @author daviddong
	 * @param canvas object of android.graphics.Canvas
	 * @return none
	 * @see android.graphics.Canvas
	 */
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
			
			//ark: only support two fingers
			if (this.touchPoints.size() <=2){
			TouchPoint p = this.touchPoints.get(i);

			PointF point = p.getPoint();
			int id = p.getTouchId();
			Path path = p.getPath();

			mPaint.setColor(Color.GRAY);

			canvas.drawCircle(point.x, point.y, 50, mPaint);
			mPaint.setColor(mColors.get(id));
			canvas.drawCircle(point.x, point.y,60, mPaint);
			canvas.drawText(String.valueOf(this.touchPoints.size()),
					widthPix / 2 - 75, heightPix / 2 - 75, pn);

			canvas.drawText(String.valueOf(id), point.x - (110 / 1920) * 800,
					point.y - (110 / 1920) * 800, ps);

			pl.setColor(mColors.get(id));
			canvas.drawPath(path, pl);
			}
			
			else{
			canvas.drawText("Please touch 2 fingers only", widthPix/10, 140, pt);
			}
				
		}

		if (separation != 0) {

			canvas.drawText("Touch separation: "+String.valueOf(separation) + "mm", widthPix/10, 100, pt);
		} else {
			canvas.drawText("Two fingers distance: "+String.valueOf(distance) + "mm", widthPix/10, 100, pt);
		}
	}

	/**
	 * override onToucheEvent method
	 * @author daviddong
	 * @param event object of android.view.MotionEvent
	 * @return b true if touch event processed false if touch event no processed
	 * @see android.view.MotionEvent
	 */
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

			if (this.touchPoints.size() == 2) {

				PointF p1 = touchPoints.get(0).getPoint();
				PointF p2 = touchPoints.get(1).getPoint();
				float deltaX = p1.x - p2.x;
				float deltaY = p1.y - p2.y;

				float xd = this.dm.xdpi;
				float yd = this.dm.ydpi;

				float x_milimeter = (float) ((deltaX / xd) * 25.4);
				float y_milimeter = (float) ((deltaY / yd) * 25.4);

				distance = (float) Math.sqrt(Math.pow(x_milimeter, 2)
						+ Math.pow(y_milimeter, 2));
				is2touch = true;
				separation = 0;
			} else {
				is2touch = false;
			}

			this.invalidate();
			return true;
		case MotionEvent.ACTION_POINTER_UP:

			Log.v("point", "point up");
			Log.v("action_index", String.valueOf(event.getActionIndex()));

			int actionIndex_up = event.getActionIndex();
			int id_up = event.getPointerId(actionIndex_up);
			Log.v("id", String.valueOf(id_up));

			for (int i = 0; i < touchPoints.size(); i++) {
				int id_list = touchPoints.get(i).getTouchId();
				if (id_up == id_list) {

					touchPoints.remove(i);
				}
			}

			if (is2touch) {

				is2touch = false;
				separation = distance;
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

			if (is2touch) {

				PointF p1 = touchPoints.get(0).getPoint();
				PointF p2 = touchPoints.get(1).getPoint();
				float deltaX = p1.x - p2.x;
				float deltaY = p1.y - p2.y;

				float xd = this.dm.xdpi;
				float yd = this.dm.ydpi;

				float x_milimeter = (float) ((deltaX / xd) * 25.4);
				float y_milimeter = (float) ((deltaY / yd) * 25.4);

				distance = (float) Math.sqrt(Math.pow(x_milimeter, 2)
						+ Math.pow(y_milimeter, 2));
			}

			this.invalidate();
			return true;

		case MotionEvent.ACTION_UP:
			Log.v("up", String.valueOf(action));
			this.touchPoints.clear();
			this.invalidate();
			return true;

		default:
			this.touchPoints.clear();
			return super.onTouchEvent(event);
		}
	}
}
