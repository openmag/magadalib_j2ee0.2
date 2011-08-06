package com.anheinno.magadapter.lib.ui;


public class MAGKeywordFilterSelect extends MAGInputBase {

	public MAGKeywordFilterSelect(String label, MAGKeywordOptionsSet options, 
			MAGKeywordValueSet keyvalue, String id) {
		super(label, id, keyvalue.toString());
		setAttr("_options", options);
	}
	
	public  MAGKeywordFilterSelect(String label, MAGLinkURL src, 
			MAGKeywordValueSet keyvalue, String id) {
		super(label, id, keyvalue.toString());
		setAttr("_src", src.getURL());
	}
	
	public void setMultichoice(boolean multichoice) {
		setAttr("_multichoice", multichoice);
	}
	
}