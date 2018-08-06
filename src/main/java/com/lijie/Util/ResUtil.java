package com.lijie.Util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ResUtil {
	public static void ResString(HttpServletResponse response,Object jsonObject){
		JSONObject json=JSONObject.fromObject(jsonObject);
		response.setContentType("application/json; charset=utf-8");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
