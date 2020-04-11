package com.donggang.maxtouch_tester.accuracy;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * 
 * 项目名称：MPT_App_Android <BR>
 * 类名称：AccuracyView <BR>
 * 类描述： view class displays the content of <BR>
 * com.mds.touch.mptappandroid.AccuracyActivity <BR>
 * 创建人：daviddong <BR>
 * 创建时间：Jul 2, 2014 3:24:47 PM <BR>
 * 修改人：daviddong <BR>
 * 修改时间：Jul 2, 2014 3:24:47 PM <BR>
 * 修改备注： <BR>
 * 
 * @version v0.1 <BR>
 * 
 */
@SuppressLint("DrawAllocation")
public class AccuracyView extends View implements OnGestureListener{

	private DisplayMetrics dm;
	String notify_str, distance_str, accuracy_str, real_str;

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

	public AccuracyView(Context context, DisplayMetrics dm) {

		super(context);
		this.dm = dm;

		this.notify_str = "touch the test point";
		this.accuracy_str = "";
		this.distance_str = "";
		this.real_str = "";
		
		
		
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

	public AccuracyView(Context context) {
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
	public AccuracyView(Context context, AttributeSet attrs) {
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

		//ark: 0714 draw lines on the edge
		for (int i = 0; i < 5; i++) {

			Path path = new Path();
			path.moveTo(points_original_y[i].x, points_original_y[i].y);
			path.lineTo(points_terminal_y[i].x, points_terminal_y[i].y);
			canvas.drawPath(path, p_dashline);
		}

		//ark: 0714 draw lines on the edge
		for (int i = 0; i < 5; i++) {

			Path path = new Path();
			path.moveTo(points_original_x[i].x, points_original_x[i].y);
			path.lineTo(points_terminal_x[i].x, points_terminal_x[i].y);
			canvas.drawPath(path, p_dashline);
		}

		//ark: 0714 outter circle is using 9mm
		Paint p_test_point = new Paint();
		p_test_point.setColor(Color.RED);
		p_test_point.setStyle(Style.STROKE);

		for (int i = 0; i < test_points.length; i++) {

			canvas.drawCircle(test_points[i].x, test_points[i].y, 54,
					p_test_point);

		}

		//ark: 0714 inner circle is using 6mm
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

		//ark: 0714 modify the text arrangement
		canvas.drawText(notify_str, text_x, text_y, p_str);
		canvas.drawText(real_str, text_x, text_y+40, p_str);
		canvas.drawText(distance_str, text_x, text_y + 80, p_str);

		if (point_touch != null) {
			p_str.setColor(Color.RED);
			p_str.setStyle(Style.FILL);
			canvas.drawCircle(point_touch.x, point_touch.y, 6, p_str);
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

			return true;

		case MotionEvent.ACTION_MOVE:

			int count = event.getPointerCount();

			if (count > 1) {
				notify_str = "please touch one finger";

			} else {

				PointF point = new PointF(event.getX(), event.getY());
				// PointF point = new PointF(event.getRawX(),event.getRawY());
				float distance = this.checkPoint(point, this.test_points)
						.getDistance();
				int index = this.checkPoint(point, this.test_points).getIndex();

				if ((distance == 0) && (index == 0)) {

				} else {

					String actualX = String.valueOf(event.getX());
					String actualY = String.valueOf(event.getY());

					notify_str = "target (" + this.test_points[index].x + ", "
							+ this.test_points[index].y + ") ";
					real_str = "actual ("+ actualX + ", " + actualY + ")";
					distance_str = "accuracy: " + String.valueOf(distance)
							+ "mm";

					//accuracy_str = "xdpi" + String.valueOf(this.dm.xdpi)
					//		+ "ydpi" + String.valueOf(this.dm.ydpi);
				}

			}

			this.invalidate();
			return true;

		case MotionEvent.ACTION_POINTER_DOWN:

			return true;

		case MotionEvent.ACTION_POINTER_UP:

			// this.notify_str = "";
			// this.invalidate();
			return true;

		case MotionEvent.ACTION_UP:
			this.notify_str = "touch the test point";
			this.invalidate();
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

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
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
