package com.donggang.maxtouch_tester.waterproof;

import java.util.ArrayList;
import java.util.List;



import com.donggang.maxtouch_tester.TouchPointEx;

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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class WaterTest_04_View extends View {

	private DisplayMetrics dm;

	private int widthPixels;
	private int heighPixels;
	private float xDPI;
	private float yDPI;
	private int xPixelsPerM;
	private int yPixelsPerM;

	PointF[] points_original_y;
	PointF[] points_terminal_y;
	PointF[] points_original_x;
	PointF[] points_terminal_x;
	PointF[] test_points;

	private List<Integer> mColors = new ArrayList<Integer>();
	private List<TouchPointEx> touchPoints = new ArrayList<TouchPointEx>();
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public WaterTest_04_View(Context context, DisplayMetrics dm) {
		super(context);
		this.dm = dm;

		widthPixels = this.dm.widthPixels;
		heighPixels = this.dm.heightPixels;

		xDPI = this.dm.xdpi;
		yDPI = this.dm.ydpi;

		xPixelsPerM = (int) (xDPI / 25.4);
		yPixelsPerM = (int) (yDPI / 25.4);

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
		mPaint.setStrokeWidth(2);

	}

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

		for (int i = 0; i < 4; i++) {

			Path path = new Path();
			path.moveTo(points_original_y[i].x, points_original_y[i].y);
			path.lineTo(points_terminal_y[i].x, points_terminal_y[i].y);
			canvas.drawPath(path, p_dashline);
		}

		for (int i = 0; i < 4; i++) {

			Path path = new Path();
			path.moveTo(points_original_x[i].x, points_original_x[i].y);
			path.lineTo(points_terminal_x[i].x, points_terminal_x[i].y);
			canvas.drawPath(path, p_dashline);
		}

		Paint p_test_point = new Paint();
		p_test_point.setColor(Color.RED);
		p_test_point.setStyle(Style.STROKE);

		for (int i = 0; i < test_points.length; i++) {

			canvas.drawCircle(test_points[i].x, test_points[i].y,
					xPixelsPerM * 5, p_test_point);

		}

		p_test_point.setColor(Color.RED);
		p_test_point.setStyle(Style.FILL);

		for (int i = 0; i < test_points.length; i++) {

			canvas.drawCircle(test_points[i].x, test_points[i].y, 3,
					p_test_point);

		}

		Paint p_solidline = new Paint(Paint.ANTI_ALIAS_FLAG);
		p_solidline.setAntiAlias(true);
		p_solidline.setStyle(Style.STROKE);
		p_solidline.setColor(Color.RED);
		p_solidline.setStrokeWidth(1);
		
		
		
		
		for (int i = 0; i < test_points.length/2; i++) {

			Path path = new Path();
			path.moveTo(test_points[2*i].x, test_points[2*i].y);
			path.lineTo(test_points[2*i+1].x, test_points[2*i+1].y);
			canvas.drawPath(path, p_solidline);
		}
		
		
		
		
		Paint pn = new Paint();
		pn.setTextSize(30);
		pn.setColor(Color.BLUE);
		Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		pn.setTypeface(font);
		canvas.drawText("Touch Two Fingers water spray tracking test.", 100,
				60, pn);

		Paint pl = new Paint();
		pl.setAntiAlias(true);
		pl.setStyle(Style.STROKE);
		pl.setStrokeWidth(1);

		Paint ps = new Paint();
		ps.setTextSize(30);
		ps.setColor(Color.GRAY);

		for (int i = 0; i < this.touchPoints.size(); i++) {
			TouchPointEx p = this.touchPoints.get(i);

			PointF point = p.getPoint();
			int id = p.getTouchId();
			Path path = p.getPath();

			mPaint.setColor(Color.GRAY);

			for (int j = 0; j < p.getPointHis().size(); j++) {

				PointF pHis = p.getPointHis().get(j);
				canvas.drawCircle(pHis.x, pHis.y, 4, mPaint);
			}

			pl.setColor(mColors.get(id));
			canvas.drawPath(path, pl);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// return super.onTouchEvent(event);

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

				TouchPointEx tp = new TouchPointEx();
				tp.setPoint(p);
				tp.setTouchId(id);
				tp.setPath(path);

				tp.setTouchTime(event.getEventTime());
				tp.getPointHis().add(p);
				tp.getTimeHis().add(event.getEventTime());

				this.touchPoints.add(tp);

			}

			this.invalidate();
			return true;
		case MotionEvent.ACTION_POINTER_DOWN:
			Log.v("point", "point down");

			Log.v("action_index", String.valueOf(event.getActionIndex()));

			int actionIndex = event.getActionIndex();
			int id = event.getPointerId(actionIndex);
			Log.v("id", String.valueOf(id));

			float x = event.getX(actionIndex);
			float y = event.getY(actionIndex);

			PointF p = new PointF(x, y);

			Path path = new Path();
			path.moveTo(x, y);

			TouchPointEx tp = new TouchPointEx();
			tp.setPoint(p);
			tp.setTouchId(id);
			tp.setPath(path);

			tp.setTouchTime(event.getEventTime());

			tp.getPointHis().add(p);
			tp.getTimeHis().add(event.getEventTime());

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

				TouchPointEx tp_move = this.touchPoints.get(i);
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
				tp_move.setTouchTime(event.getEventTime());
				tp_move.getPointHis().add(p_move);
				tp_move.getTimeHis().add(event.getEventTime());
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

	private void initDataArray() {

		int centralXP = this.widthPixels / 2;
		int centralYP = this.getHeight() / 2;

		points_original_y = new PointF[] {
				new PointF(centralXP - xPixelsPerM * 12, 0),
				new PointF(centralXP - xPixelsPerM * 2, 0),
				new PointF(centralXP + xPixelsPerM * 2, 0),
				new PointF(centralXP + xPixelsPerM * 12, 0),

		};

		points_terminal_y = new PointF[] {
				new PointF(centralXP - xPixelsPerM * 12, this.heighPixels),
				new PointF(centralXP - xPixelsPerM * 2, this.heighPixels),
				new PointF(centralXP + xPixelsPerM * 2, this.heighPixels),
				new PointF(centralXP + xPixelsPerM * 12, this.heighPixels), };

		points_original_x = new PointF[] {
				new PointF(0, centralYP - yPixelsPerM * 12),
				new PointF(0, centralYP - yPixelsPerM * 2),
				new PointF(0, centralYP + yPixelsPerM * 2),
				new PointF(0, centralYP + yPixelsPerM * 12), };

		points_terminal_x = new PointF[] {
				new PointF(this.widthPixels, centralYP - yPixelsPerM * 12),
				new PointF(this.widthPixels, centralYP - yPixelsPerM * 2),
				new PointF(this.widthPixels, centralYP + yPixelsPerM * 2),
				new PointF(this.widthPixels, centralYP + yPixelsPerM * 12), };

		test_points = new PointF[] {

				new PointF((float) (centralXP - xPixelsPerM * 7),
						yPixelsPerM * 10),

				new PointF((float) (centralXP - xPixelsPerM * 7),
						this.getHeight() - yPixelsPerM * 10),

				new PointF((float) (centralXP + xPixelsPerM * 7),
						yPixelsPerM * 10),

				new PointF((float) (centralXP + xPixelsPerM * 7),
						this.getHeight() - yPixelsPerM * 10),

				new PointF(xPixelsPerM * 10,
						(float) (centralYP - yPixelsPerM * 7)),

				new PointF(this.getWidth() - xPixelsPerM * 10,
						(float) (centralYP - yPixelsPerM * 7)),

				new PointF(xPixelsPerM * 10,
						(float) (centralYP + yPixelsPerM * 7)),

				new PointF(this.getWidth() - xPixelsPerM * 10,
						(float) (centralYP + yPixelsPerM * 7)),

		};
	}
}
