package com.anheinno.magadapter.lib.ui;

public class MAGImage extends MAGComponent {
	
	public MAGImage(String title, MAGLinkURL src) {
		this(title, src, 0, null);
	}

	public MAGImage(String title, MAGLinkURL src, long expire) {
		this(title, src, expire, null);
	}

	public MAGImage(String title, MAGLinkURL src, long expire, String id) {
		super(title, id);
		setAttr("_src", src.getURL());
		setAttr("_expire", expire);
	}
	
}
