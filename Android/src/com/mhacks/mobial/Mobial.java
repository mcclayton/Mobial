package com.mhacks.mobial;

import java.io.ByteArrayOutputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;

// TODO: Factor in a unique ANDROID_USER_ID and MOBIAL_COMPANY_USER_ID to JSON/Code.

public class Mobial {

	private static JSONArray activityFlowJSONArray = new JSONArray();
	private static JSONArray touchCoordsJSONArray = new JSONArray();

	public static void startTrackingActivityFlow(Activity act) {		
		JSONObject activityJSON = new JSONObject();

		try {	
			
			activityJSON.put("activityName", act.getLocalClassName());	// Add the current activity name to the JSON
			
			// Take a screenshot, encode it, and add the encoded screenshot to the JSON
			Bitmap screenshot = takeScreenShot(act);
			activityJSON.put("activityScreenshot", bitmapToString(screenshot));
			activityFlowJSONArray.put(activityJSON);

			System.out.println(activityFlowJSONArray.toString());
			//TODO: Make the POST request to the server here.

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public static void startTrackingGestures(Activity act) {
		View view = (View) act.findViewById(android.R.id.content);
		view.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				JSONObject touchCoordJSON = new JSONObject();

				try {
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						// The user started dragging
						touchCoordJSON.put("x_coord", event.getX());
						touchCoordJSON.put("y_coord", event.getY());
						touchCoordJSON.put("state", "start_drag");
						touchCoordsJSONArray.put(touchCoordJSON);
						return true;
					} else if(event.getAction() == MotionEvent.ACTION_MOVE) {
						// The user is dragging
						touchCoordJSON.put("x_coord", event.getX());
						touchCoordJSON.put("y_coord", event.getY());
						touchCoordJSON.put("state", "dragging");
						touchCoordsJSONArray.put(touchCoordJSON);
						return true;
					} else if (event.getAction() == MotionEvent.ACTION_UP) {
						// The user is done dragging
						touchCoordJSON.put("x_coord", event.getX());
						touchCoordJSON.put("y_coord", event.getY());
						touchCoordJSON.put("state", "end_drag");
						touchCoordsJSONArray.put(touchCoordJSON);

						System.out.println(touchCoordsJSONArray.toString());
						//TODO: Make the POST request to the server here.

						return true;
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return true;
			}
		});
	}
	
	private static Bitmap takeScreenShot(Activity act) {
		//View view = (View) act.findViewById(android.R.id.content);
		View view = act.getWindow().getDecorView();
		Bitmap bitmap;
		view.setDrawingCacheEnabled(true);
		bitmap = Bitmap.createBitmap(view.getDrawingCache());
		view.setDrawingCacheEnabled(false);
		return bitmap;
	}

	/**
	 * Encodes a Bitmap picture to a string which can be JSONified.
	**/
	private static String bitmapToString(Bitmap bitmapPicture) {
		final int COMPRESSION_QUALITY = 100;
		String encodedImage;
		ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
		bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
				byteArrayBitmapStream);
		byte[] b = byteArrayBitmapStream.toByteArray();
		encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
		return encodedImage;
	}

}