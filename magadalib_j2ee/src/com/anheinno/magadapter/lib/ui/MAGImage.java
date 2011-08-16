package com.anheinno.magadapter.lib.ui;

public class MAGImage extends MAGComponent {
	
	public MAGImage(String title, MAGLinkURL src) {
		this(title, src, null);
	}

	public MAGImage(String title, MAGLinkURL src, String id) {
		super(title, id);
		setAttr("_src", src.getURL());
		setAttr("_expire", src.getExpireMilliseconds());
	}
	
}
