package com.eagleforce.robot.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import com.eagleforce.robot.model.CameraMessage;
import org.json.simple.parser.*;

public class CameraMessageParsingService {
	
	public CameraMessage parseJson(String json) {
//		this is where we put json parsing (http://www.java2s.com/Code/Jar/j/Downloadjsonsimple11jar.htm)
		try {
			CameraMessage camMsg = new CameraMessage();
			Object obj;
			obj = new JSONParser().parse(new FileReader(""));
			JSONObject jo = (JSONObject) obj;

			camMsg.setDistanceToTarget(Double.parseDouble(((String) jo.get("distance"))));
			camMsg.setAngleToTarget(Double.parseDouble(((String) jo.get("angle"))));
			camMsg.setTimeOfImage(Double.parseDouble(((String) jo.get("distance"))));
			camMsg.setTracking(Boolean.parseBoolean(((String) jo.get("distance"))));
			
			return camMsg;

		} catch (IOException | ParseException | NumberFormatException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new CameraMessage();
		}
	
	}

}
