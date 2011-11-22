package com.anheinno.magadapter.lib.ui;

public abstract class MAGLinkableComponent extends MAGComponent
{

	protected MAGLinkableComponent(String title, MAGLinkURL link, MAGLinkTarget target, String id)
	{
		super(title, id);
		setAttr("_target", target.toString());
		if (target == MAGLinkTarget.LINK_TARGET_CUSTOMCONTROL)
		{
			setAttr("_link", link.getClassParams());
		}
		else if (target == MAGLinkTarget.LINK_TARGET_SCRIPT)
		{
			setAttr("_link", link.getScripts());
		}
		else if(target == MAGLinkTarget.LINK_TARGET_BROWSER) 
		{
			setAttr("_link", link.getURL());
		}else
		{
			setAttr("_link", link.getURL());
			setAttr("_expire", link.getExpireMilliseconds());
			setAttr("_notify", link.isNotify());
			setAttr("_save", link.isSaveHistory());
		}
	}

}
