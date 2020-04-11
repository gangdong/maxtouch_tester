package com.donggang.maxtouch_tester;

import android.graphics.Path;
import android.graphics.PointF;

public class TouchPoint {

	private int touchId;
	private PointF point;
	
	private Path path;
	private Long touchTime;
	
	public TouchPoint() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Long getTouchTime() {
		return touchTime;
	}



	public void setTouchTime(Long touchTime) {
		this.touchTime = touchTime;
	}



	public int getTouchId() {
		return touchId;
	}

	public void setTouchId(int touchId) {
		this.touchId = touchId;
	}

	public PointF getPoint() {
		return point;
	}

	public void setPoint(PointF point) {
		this.point = point;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	
	
}
