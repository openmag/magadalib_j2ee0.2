package com.anheinno.magadapter.lib.ui;

public class MAGTabPanel extends MAGContainerBase {
	
	public MAGTabPanel() {
		this(0, null);
	}

	public MAGTabPanel(int defindex) {
		this(defindex, null);
	}

	public MAGTabPanel(int defindex, String id) {
		super("", id);
		if (defindex > 0) {
			setAttr("_default", defindex);
		}
	}

	public void addPanel(MAGPanel panel) {
		super.addChild(panel);
	}
	
}
