package com.mhacks.mobial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.provider.Settings.Secure;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;

// TODO: Factor in a unique ANDROID_USER_ID and MOBIAL_COMPANY_USER_ID to JSON/Code.

public class Mobial {

	private static JSONArray touchCoordsJSONArray = new JSONArray();

	public static void startTrackingActivityFlow(Activity act) {		
		final JSONObject activityJSON = new JSONObject();

		try {	
			// Add the platform name to the JSON
			activityJSON.put("platform", "Android");
			
			// Add the current activity name to the JSON
			activityJSON.put("activityName", act.getLocalClassName());
			
			// Add the encoded activity screenshot to the JSON
			Bitmap screenshot = takeScreenShot(act);
			activityJSON.put("activityScreenshot", bitmapToString(screenshot));
			
			// Add the android device ID to the JSON
			String android_id = Secure.getString(act.getBaseContext().getContentResolver(), Secure.ANDROID_ID); 
			activityJSON.put("androidDeviceID", android_id);
			
			// Add the unix timestamp to the JSON
			activityJSON.put("timeStamp", System.currentTimeMillis() / 1000L);
			
			//TODO: Make the POST request to the server here.
			new Thread(new Runnable() {
				public void run() {
					postData("activityJSON", activityJSON.toString());
				}
			}).start();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}		
	}

	public static void startTrackingGestures(final Activity act) {
		View view = (View) act.findViewById(android.R.id.content);
		view.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				JSONObject touchCoordJSON = new JSONObject();
				String android_id = Secure.getString(act.getBaseContext().getContentResolver(), Secure.ANDROID_ID);

				try {
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						// The user started dragging
						
						// Add the android coordinates and state to the JSON
						touchCoordJSON.put("x_coord", event.getX());
						touchCoordJSON.put("y_coord", event.getY());
						touchCoordJSON.put("state", "start_drag");
						
						touchCoordsJSONArray.put(touchCoordJSON);
						return true;
					} else if(event.getAction() == MotionEvent.ACTION_MOVE) {
						// The user is dragging
						
						// Add the android coordinates and state to the JSON
						touchCoordJSON.put("x_coord", event.getX());
						touchCoordJSON.put("y_coord", event.getY());
						touchCoordJSON.put("state", "dragging");
						
						touchCoordsJSONArray.put(touchCoordJSON);
						return true;
					} else if (event.getAction() == MotionEvent.ACTION_UP) {
						// The user is done dragging
						
						// Add the android coordinates and state to the JSON
						touchCoordJSON.put("x_coord", event.getX());
						touchCoordJSON.put("y_coord", event.getY());
						touchCoordJSON.put("state", "end_drag");
						touchCoordsJSONArray.put(touchCoordJSON);
						
						final JSONObject gestureJSON = new JSONObject();
						gestureJSON.put("platform", "Android");
						gestureJSON.put("androidDeviceID", android_id);
						gestureJSON.put("activityName", act.getLocalClassName());
						gestureJSON.put("timeStamp", System.currentTimeMillis() / 1000L);
						gestureJSON.put("gestureDataArray", touchCoordsJSONArray);
						touchCoordsJSONArray = new JSONArray();
						
						//TODO: Make the POST request to the server here.
						new Thread(new Runnable() {
							public void run() {
								postData("gestureJSON", gestureJSON.toString());
							}
						}).start();

						return true;
					}
				} catch (JSONException e) {
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
	
	public static void postData(String JSONKey, String JSONValue) {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://mobial.comeze.com/postToMobial.php");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	        nameValuePairs.add(new BasicNameValuePair(JSONKey, JSONValue));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);

	        // Response String
	        HttpEntity httpEntity = response.getEntity();
	        System.out.println(EntityUtils.toString(response.getEntity()));
	        
	    } catch (ClientProtocolException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	} 

}