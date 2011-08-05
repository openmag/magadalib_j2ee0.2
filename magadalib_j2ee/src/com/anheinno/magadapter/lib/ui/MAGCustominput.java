package com.anheinno.magadapter.lib.ui;

public class MAGCustominput extends MAGInputBase
{

	public MAGCustominput(String title, String classname, String value, String[] parameters, String id)
	{
		super(title, id, value);
		setAttr("_classname", classname);
		setAttr("_params", parameters);
	}
}
