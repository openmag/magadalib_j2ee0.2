package com.anheinno.magadapter.lib.ui;

public class MAGInputList extends MAGList implements MAGInputInterface
{

	public MAGInputList(String title, String id, String[] values)
	{
		super(title, id);
		setAttr("_value", MAGInputBase.toValueString(values));
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

}
