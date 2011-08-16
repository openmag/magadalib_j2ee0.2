package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONObject;

public abstract class MAGComponent extends JSONObject implements MAGComponentInterface
{
	private static final String TRUE = "true";
	private static final String FALSE = "false";

	protected MAGComponent(String title, String id)
	{
		super();
		String classname = getClass().getName();
		if (classname.lastIndexOf('.') >= 0)
		{
			classname = classname.substring(classname.lastIndexOf('.') + 1);
		}
		setAttr("_type", classname);
		setTitle(title);
		setId(id);
	}

	protected Object getAttr(String key)
	{
		try
		{
			if (has(key))
			{
				return get(key);
			}
		}
		catch (final Exception e)
		{

		}
		return null;
	}

	protected void setAttr(String key, boolean val)
	{
		setAttr(key, val ? TRUE : FALSE);
	}

	protected void setAttr(String key, StringEnum val)
	{
		setAttr(key, val.toString());
	}

	/*
	 * protected void setAttr(String key, long val) { setAttr(key, (Object)(new
	 * Long(val))); }
	 * 
	 * protected void setAttr(String key, int val) { setAttr(key, (Object)(new
	 * Integer(val))); }
	 */

	protected void setAttr(String key, String val)
	{
		if (val != null) // && val.length() > 0)
		{
			setAttr(key, (Object) val);
		}
	}

	protected void setAttr(String key, Object val)
	{
		try
		{
			if (val != null)
			{
				put(key, val);
			}
		}
		catch (final Exception e)
		{
			System.out.println(e);
		}
	}

	public void setStyle(MAGStyle style)
	{
		setAttr("_style", style);
	}

	public void setClass(String name)
	{
		setAttr("_class", name);
	}

	public void setHint(String hint)
	{
		setAttr("_hint", hint);
	}

	public void setStatus(String status)
	{
		setAttr("_status", status);
	}

	public void setTitle(String val)
	{
		if (val != null && val.length() > 0)
		{
			setAttr("_title", val);
		}
	}

	public void setId(String val)
	{
		if (val != null && val.length() > 0)
		{
			setAttr("_id", val);
		}
	}

	public String getId()
	{
		return (String) getAttr("_id");
	}

}
