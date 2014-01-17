package com.mhacks.mobial;

import android.view.MotionEvent;
import android.view.View;

public class Mobial {
	// TODO Create a super awesome library of functions that collect and mobile analytics data.
	
	public static void startTrackingGestures(View v) {
		v.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				System.out.println("X: "+event.getX()+"   Y:"+event.getY());
				return false;
			}
		});
	}
}