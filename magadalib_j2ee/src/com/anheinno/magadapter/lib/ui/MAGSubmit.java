package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;

public class MAGSubmit extends MAGInputBase
{

	public MAGSubmit(String title, String action, MAGLinkURL url)
	{
		this(title, action, url, MAGLinkableComponent.MAGLinkTarget.LINK_TARGET_SELF, null);
	}

	public MAGSubmit(String title, String action, MAGLinkURL url, MAGLinkableComponent.MAGLinkTarget target)
	{
		this(title, action, url, target, null);
	}

	public MAGSubmit(String title, String action, MAGLinkURL url, MAGLinkableComponent.MAGLinkTarget target, String id)
	{
		super(title, id, "");
		setAttr("_action", action);
		setAttr("_url", url.getURL());
		setAttr("_target", target);
	}

	public void setConfirm(String msg)
	{
		setAttr("_confirm", msg);
	}

	public void setRequire(MAGInputInterface[] inputs)
	{
		JSONArray list = new JSONArray();
		for (int i = 0; inputs != null && i < inputs.length; i++)
		{
			String id = inputs[i].getId();
			if (id != null && id.length() > 0)
			{
				list.put(id);
			}
		}
		setAttr("_require", list);
	}
}
