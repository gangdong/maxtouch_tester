package com.donggang.maxtouch_tester.flick;

import java.util.ArrayList;
import java.util.List;



import com.donggang.maxtouch_tester.TouchPointEx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**   
*    
* 项目名称：MPT_App_Android  <BR> 
* 类名称：FlickView   <BR>
* 类描述：view class that display the content of com.mds.touch.mptappandroid.flick.FlickActivity <BR>
* 创建人：daviddong   <BR>
* 创建时间：Jul 3, 2014 2:32:58 PM   <BR>
* 修改人：daviddong   <BR>
* 修改时间：Jul 3, 2014 2:32:58 PM   <BR>
* 修改备注：   <BR>
* @version   v0.1 <BR>
*    
*/ 
public class FlickView extends View {

	
	private List<Integer> mColors = new ArrayList<Integer>();
	private List<TouchPointEx> touchPoints = new ArrayList<TouchPointEx>();
	private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	
	/**
	 * override constructor
	 * 
	 * @author daviddong
	 * @param context object of android.content.Context
	 * @return none
	 * @see android.content.Context
	 */
	public FlickView(Context context) {
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
		mPaint.setStrokeWidth(2);
	}

	public FlickView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public FlickView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * override onDraw
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
		
		long delay_avg=0;
		int num=0;
		
		Paint pl = new Paint();
		pl.setAntiAlias(true);
		pl.setStyle(Style.STROKE);
		pl.setStrokeWidth(1);
		
		Paint ps = new Paint();
		ps.setTextSize(20);
		ps.setColor(Color.GRAY);
		
		Paint pi = new Paint();
		pi.setTextSize(30);
		pi.setColor(Color.BLUE);
		
		canvas.drawText("Using 1 Finger to Flick", 20, 80, pi);
		
		for (int i = 0; i < this.touchPoints.size(); i++) {
			TouchPointEx p = this.touchPoints.get(i);

			PointF point = p.getPoint();
			int id = p.getTouchId();
			Path path = p.getPath();

			mPaint.setColor(Color.GRAY);

			for(int j=0;j<p.getPointHis().size();j++){
				
				PointF pHis = p.getPointHis().get(j);
				canvas.drawCircle(pHis.x, pHis.y, 4, mPaint);
				long delay = p.getTimeHis().get(j)-p.getTimeHis().get(0);
				canvas.drawText(String.valueOf(delay)+"ms",pHis.x, pHis.y, ps);
				
				num = p.getPointHis().size();
				delay_avg = delay;
			}
			
			//ark: 0717 --> it is not stable? why?
			//num = num-1;
			//delay_avg = delay_avg/num;
			
			
			//mPaint.setColor(mColors.get(id));
			//canvas.drawCircle(point.x, point.y, 120, mPaint);
			//canvas.drawText(String.valueOf(this.touchPoints.size()),
			//		widthPix / 2 - 75, heightPix / 2 - 75, pn);

			//canvas.drawText(String.valueOf(id), point.x - 110, point.y - 110,
			//		ps);

			pl.setColor(mColors.get(id));
			canvas.drawPath(path, pl);
			
			canvas.drawText("Average Moving Latency: " + String.valueOf(delay_avg/num) +"ms", 20, 120, pi);
			canvas.drawText("Flick Latency: " + String.valueOf(delay_avg) +"ms", 20, 160, pi);

			
		}
	}
	


	/**
	 * override onTouchEvent method
	 * 
	 * @author daviddong
	 * @param event object of android.view.MotionEvent
	 * @return b true if event processed false if evebt no processed
	 * @see android.view.MotionEvent
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//return super.onTouchEvent(event);

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
			/* ark: 0717 support only one finger
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
			*/
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
			//this.touchPoints.clear();
			//this.invalidate();
			return true;

		default:
			this.touchPoints.clear();
			return super.onTouchEvent(event);
		}

	}

}
