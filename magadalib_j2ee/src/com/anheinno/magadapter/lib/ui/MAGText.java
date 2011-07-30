package com.anheinno.magadapter.lib.ui;

public class MAGText extends MAGComponent {
	
	public MAGText(String title, String text) {
		this(title, text, null);
	}

	public MAGText(String title, String text, String id) {
		super(title, id);
		setAttr("_text", text);
	}

}
