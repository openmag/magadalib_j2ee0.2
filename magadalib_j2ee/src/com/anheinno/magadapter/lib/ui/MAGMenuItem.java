package com.anheinno.magadapter.lib.ui;

public class MAGMenuItem extends MAGLinkableComponent {

	public MAGMenuItem(String title, MAGLinkURL link) {
		this(title, link, MAGLinkTarget.LINK_TARGET_SELF, null);
	}

	public MAGMenuItem(String title, MAGLinkURL link, MAGLinkTarget target) {
		this(title, link, target, null);
	}

	public MAGMenuItem(String title, MAGLinkURL link, MAGLinkTarget target, String id) {
		super(title, link, target, id);
	}

}
