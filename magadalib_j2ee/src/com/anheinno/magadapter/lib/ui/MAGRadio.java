package com.anheinno.magadapter.lib.ui;


public class MAGRadio extends MAGInputBase {

	public MAGRadio(String label, String group, String value, String id) {
		super(label, id, value);
		setAttr("_group", group);
	}

	public void setChecked(boolean checked) {
		setAttr("_checked", checked);
	}

}
