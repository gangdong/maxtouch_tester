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
import android.graphics.Typeface;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class WaterTest_05_View extends View {

	private DisplayMetrics dm;

	private int widthPixels;
	private int heighPixels;
	private float xDPI;
	private float yDPI;
	private int xPixelsPerM;
	private int yPixelsPerM;

	private List<Integer> mColors = new ArrayList<Integer>();
	private List<TouchPointEx> touchPoints = new ArrayList<TouchPointEx>();
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public WaterTest_05_View(Context context, DisplayMetrics dm) {
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

		int widthX = xPixelsPerM * 3;
		boolean isGapX = false;
		PointF originalX = new PointF(widthX, 0);
		PointF terminalX = new PointF(widthX, this.heighPixels);

		List<PointF> pLists = new ArrayList<PointF>();

		do {
			Path path = new Path();
			path.moveTo(originalX.x, originalX.y);
			path.lineTo(terminalX.x, terminalX.y);
			canvas.drawPath(path, p_dashline);

			if (isGapX) {
				isGapX = false;
				float xO = originalX.x + xPixelsPerM * 4;
				float xT = terminalX.x + xPixelsPerM * 4;

				originalX.x = xO;
				terminalX.x = xT;
				widthX += xPixelsPerM * 4;
			} else {
				isGapX = true;
				float xO = originalX.x + xPixelsPerM * 6;
				float xT = terminalX.x + xPixelsPerM * 6;

				originalX.x = xO;
				terminalX.x = xT;
				widthX += xPixelsPerM * 6;

				PointF points = new PointF(xO - xPixelsPerM * 3,
						yPixelsPerM * 3);
				pLists.add(points);
			}

		} while (widthX < widthPixels);

		Paint p_solidline = new Paint(Paint.ANTI_ALIAS_FLAG);
		p_solidline.setAntiAlias(true);
		p_solidline.setStyle(Style.STROKE);
		p_solidline.setColor(Color.RED);
		p_solidline.setStrokeWidth(1);

		for (int i = 0; i < pLists.size(); i++) {

			Path path = new Path();
			path.moveTo(pLists.get(i).x, pLists.get(i).y);
			path.lineTo(pLists.get(i).x, this.heighPixels);
			canvas.drawPath(path, p_solidline);

			canvas.drawCircle(pLists.get(i).x, pLists.get(i).y,
					xPixelsPerM * 3, p_solidline);
		}

		List<PointF> hLists = new ArrayList<PointF>();
		int widthY = yPixelsPerM * 3;
		boolean isGapY = false;
		PointF originalY = new PointF(0, widthY);
		PointF terminalY = new PointF(this.widthPixels, widthY);

		do {
			Path path = new Path();
			path.moveTo(originalY.x, originalY.y);
			path.lineTo(terminalY.x, terminalY.y);
			canvas.drawPath(path, p_dashline);
			if (isGapY) {
				isGapY = false;
				float yO = originalY.y + yPixelsPerM * 4;
				float yT = terminalY.y + yPixelsPerM * 4;

				originalY.y = yO;
				terminalY.y = yT;
				widthY += yPixelsPerM * 4;
			} else {
				isGapY = true;
				float yO = originalY.y + yPixelsPerM * 6;
				float yT = terminalY.y + yPixelsPerM * 6;

				originalY.y = yO;
				terminalY.y = yT;
				widthY += yPixelsPerM * 6;

				PointF points = new PointF(xPixelsPerM * 3, yO - yPixelsPerM
						* 3);
				hLists.add(points);
			}

		} while (widthY < heighPixels);

		for (int i = 0; i < hLists.size(); i++) {

			Path path = new Path();
			path.moveTo(hLists.get(i).x, hLists.get(i).y);
			path.lineTo( this.widthPixels,hLists.get(i).y);
			canvas.drawPath(path, p_solidline);

			canvas.drawCircle(hLists.get(i).x, hLists.get(i).y,
					xPixelsPerM * 3, p_solidline);
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

}
