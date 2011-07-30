package com.anheinno.magadapter.lib.ui;


public class MAGLink extends MAGLinkableComponent {
	public MAGLink(String title, MAGLinkURL link) {
		this(title, link, 0, MAGLinkTarget.LINK_TARGET_SELF, null);
	}

	public MAGLink(String title, MAGLinkURL link, long expire) {
		this(title, link, expire, MAGLinkTarget.LINK_TARGET_SELF, null);
	}

	public MAGLink(String title, MAGLinkURL link, long expire, MAGLinkTarget target) {
		this(title, link, expire, target, null);
	}

	public MAGLink(String title, MAGLinkURL link, long expire, MAGLinkTarget target, String id) {
		super(title, link, expire, target, id);
	}

}

