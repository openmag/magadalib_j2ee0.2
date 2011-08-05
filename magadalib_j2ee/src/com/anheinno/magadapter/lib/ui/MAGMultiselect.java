package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

public class MAGMultiselect extends MAGInputBase
{

	public MAGMultiselect(String title, String id, String[] values)
	{
		super(title, id, MAGInputBase.toValueString(values));
		setAttr("_options", new JSONArray());
	}

	public void addOption(String text, String val, boolean ischecked)
	{
		JSONArray options = (JSONArray) getAttr("_options");

		JSONObject dat = new JSONObject();
		try
		{
			dat.put("_text", text);
			dat.put("_value", val);
		}
		catch (final Exception e)
		{
		}
		options.put(dat);

		String values = getValue();

		try
		{
			boolean find = false;
			JSONArray array = new JSONArray(values);
			for (int i = 0; i < array.length(); i++)
			{
				if (array.getString(i).equals(val))
				{
					find = true;
					break;
				}
			}
			if (!find)
			{
				array.put(val);
				setValue(array.toString());
			}
		}
		catch (final Exception e)
		{

		}

	}
}
