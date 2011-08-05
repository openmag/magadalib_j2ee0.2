package com.anheinno.magadapter.lib.ui;

public abstract class MAGLinkableComponent extends MAGComponent
{

	public enum MAGLinkTarget implements StringEnum
	{
		LINK_TARGET_SELF("__self_"), 
		LINK_TARGET_NEW("__new_"), 
		LINK_TARGET_BROWSER("__browser_"), 
		LINK_TARGET_CUSTOMCONTROL("__custom_control_"), 
		LINK_TARGET_SCRIPT("__script_");

		private final String _target;

		private MAGLinkTarget(String target)
		{
			_target = target;
		}

		public String toString()
		{
			return _target;
		}
	}

	protected MAGLinkableComponent(String title, MAGLinkURL link, long expire, MAGLinkTarget target, String id)
	{
		super(title, id);
		setAttr("_target", target);
		if (target == MAGLinkTarget.LINK_TARGET_CUSTOMCONTROL)
		{
			setAttr("_link", link.getClassParams());
		}
		else if (target == MAGLinkTarget.LINK_TARGET_SCRIPT)
		{
			setAttr("_link", link.getScripts());
		}
		else
		{
			setAttr("_link", link.getURL());
		}
		setAttr("_expire", expire);
	}

	public void setNotify(boolean notify)
	{
		setAttr("_notify", notify);
	}

	public void setSave(boolean save)
	{
		setAttr("_save", save);
	}

}
