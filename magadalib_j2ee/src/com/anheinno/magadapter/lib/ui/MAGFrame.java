package com.anheinno.magadapter.lib.ui;

public class MAGFrame extends MAGComponent {

	public MAGFrame(MAGLinkURL link, String id) {
		super("", id);
		
		setAttr("_link", link.getURL());
		setAttr("_expire", link.getExpireMilliseconds());
		setAttr("_notify", link.isNotify());
		setAttr("_save", link.isSaveHistory());
	}

}
