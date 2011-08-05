package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;

public abstract class MAGInputBase extends MAGComponent implements MAGInputInterface
{

	protected MAGInputBase(String title, String id, String value)
	{
		super(title, id);
		setValue(value);
	}

	public void readOnly()
	{
		setAttr("_readonly", "true");
	}

	public void nonEmpty()
	{
		setAttr("_nonempty", "true");
	}

	public void verifyMessage(String msg)
	{
		setAttr("_vmsg", msg);
	}

	public static String toValueString(Object[] objs)
	{
		JSONArray array = new JSONArray();
		for (int i = 0; objs != null && i < objs.length; i++)
		{
			array.put(objs[i]);
		}
		return array.toString();
	}

	public String getValue()
	{
		return (String) getAttr("_value");
	}

	public void setValue(String value)
	{
		if (value != null && value.length() > 0)
		{
			setAttr("_value", value);
		}
	}

}
