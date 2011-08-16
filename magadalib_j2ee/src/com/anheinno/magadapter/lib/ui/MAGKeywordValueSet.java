package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

public class MAGKeywordValueSet extends JSONArray
{
	public void addValue(String text, String value)
	{
		try
		{
			JSONObject obj = new JSONObject();
			obj.put("_text", text);
			obj.put("_value", value);
			put(obj);
		}
		catch (final Exception e)
		{
		}
	}
}