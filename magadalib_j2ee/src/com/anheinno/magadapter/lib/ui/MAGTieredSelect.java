package com.anheinno.magadapter.lib.ui;

public class MAGTieredSelect extends MAGInputBase {
	
	public MAGTieredSelect(String label, MAGTieredOptionsSet options,
			String[] tvalue, String id) {
		super(label, id, MAGInputBase.toValueString(tvalue));
		setAttr("_options", options);
	}
	
}