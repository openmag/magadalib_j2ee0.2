package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

public class MAGTieredOptionsSet extends JSONArray
{
	public void addOption(String text, String value, MAGTieredSuboptionsSet suboption)
	{
		try
		{
			JSONObject obj = new JSONObject();
			obj.put("_text", text);
			if (value != null && value.length() > 0)
			{
				obj.put("_value", value);
			}
			if (suboption != null && suboption.length() > 0)
			{
				obj.put("_suboption", suboption);
			}
			put(obj);
		}
		catch (final Exception e)
		{

		}
	}
}
