package old.com.eagleforce.robot.service;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import old.com.eagleforce.robot.model.CameraMessage;

public class CameraMessageParsingService {
	
	private static final String TRACKING_JSON_KEY = "Trk";
	private static final String TIME_JSON_KEY = "Tm";
	private static final String DEGREES_JSON_KEY = "Deg";
	private static final String DISTANCE_JSON_KEY = "Dist";

	public CameraMessage parseJson(String json) {
//		this is where we put json parsing (http://www.java2s.com/Code/Jar/j/Downloadjsonsimple11jar.htm)
//		and http://www.java2s.com/Code/Jar/o/Downloadorgjsonjar.htm
		try {
			CameraMessage camMsg = new CameraMessage();
			Object obj;
			obj = new JSONParser().parse(json.trim());
			JSONObject jo = (JSONObject) obj;

			camMsg.setDistanceToTarget((Double) jo.get(DISTANCE_JSON_KEY));
			camMsg.setAngleToTarget((Double) jo.get(DEGREES_JSON_KEY));
			camMsg.setTimeOfImage((Double)  jo.get(TIME_JSON_KEY));
//			ask bill if this returns a boolean value, and if not see if we can change it
			camMsg.setTracking((Boolean) jo.get(TRACKING_JSON_KEY));
			
			return camMsg;

		} catch (ParseException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new CameraMessage();
		}
	
	}

}
