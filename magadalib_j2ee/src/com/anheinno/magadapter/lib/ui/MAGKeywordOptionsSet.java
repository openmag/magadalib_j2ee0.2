package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

public class MAGKeywordOptionsSet extends JSONArray
{
	public void addOption(String text, String value, String[] keywords)
	{
		try
		{
			JSONObject obj = new JSONObject();
			obj.put("_text", text);
			obj.put("_value", value);
			if (keywords != null && keywords.length > 0)
			{
				obj.put("_keywords", keywords);
			}
			put(obj);
		}
		catch (final Exception e)
		{

		}
	}
}
