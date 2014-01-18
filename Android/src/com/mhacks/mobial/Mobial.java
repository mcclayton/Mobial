package com.mhacks.mobial;

import android.view.MotionEvent;
import android.view.View;

public class Mobial {
	
	public static void startTrackingGestures(View v) {
		
		v.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
			    if (event.getAction() == MotionEvent.ACTION_DOWN) {
			    	// The user started dragging
			    	System.out.println("START!");
			    	return true;
			    } else if(event.getAction() == MotionEvent.ACTION_MOVE) {
			    	// The user is likely dragging
			    	System.out.println("X: "+event.getX()+"   Y:"+event.getY());
					return true;
			    } else if (event.getAction() == MotionEvent.ACTION_UP) {
			    	// The user is done dragging
			    	System.out.println("END!");
			    	return true;
			    }
			    return true;
			}
		});
	}
}