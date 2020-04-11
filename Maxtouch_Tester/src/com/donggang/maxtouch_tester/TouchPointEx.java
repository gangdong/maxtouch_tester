package com.donggang.maxtouch_tester;

import java.util.ArrayList;
import java.util.List;

import android.graphics.PointF;

public class TouchPointEx extends TouchPoint{

	
	private List<PointF> pointHis = new ArrayList<PointF>();
	private List<Long> timeHis = new ArrayList<Long>();
	
	public TouchPointEx() {
		// TODO Auto-generated constructor stub
	}

	public List<PointF> getPointHis() {
		return pointHis;
	}

	public void setPointHis(List<PointF> pointHis) {
		this.pointHis = pointHis;
	}

	public List<Long> getTimeHis() {
		return timeHis;
	}

	public void setTimeHis(List<Long> timeHis) {
		this.timeHis = timeHis;
	}

	
	
}
