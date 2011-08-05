package com.anheinno.magadapter.lib.ui;

public class MAGFileLink extends MAGComponent
{

	public MAGFileLink(String title, String icon, MAGLinkURL link)
	{
		this(title, icon, link, null);
	}

	public MAGFileLink(String title, String icon, MAGLinkURL link, String id)
	{
		super(title, id);
		setAttr("_icon", icon);
		setAttr("_link", link.getURL());
	}

}
