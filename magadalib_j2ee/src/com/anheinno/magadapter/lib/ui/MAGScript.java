package com.anheinno.magadapter.lib.ui;

public class MAGScript extends MAGComponent {
	
	public MAGScript(String scripts) {
		this(scripts, null);
	}

	public MAGScript(String scripts, String id) {
		super("", id);
		setAttr("_scripts", scripts);
	}
	
}