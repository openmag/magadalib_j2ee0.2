package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;

public abstract class MAGContainerBase extends MAGComponent implements MAGContainerInterface
{

	protected MAGContainerBase(String title, String id)
	{
		super(title, id);
		setAttr("_content", new JSONArray());
	}

	public void addChild(MAGComponent comp)
	{
		try
		{
			JSONArray body = (JSONArray) getAttr("_content");
			body.put(comp);
		}
		catch (final Exception e)
		{

		}
	}
}
