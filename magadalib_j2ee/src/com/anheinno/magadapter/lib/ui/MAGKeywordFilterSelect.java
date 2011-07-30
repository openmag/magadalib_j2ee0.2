package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

public class MAGKeywordFilterSelect extends MAGInputBase {

	public class MAGKeywordOptionsSet extends JSONArray {
		public void addOption(String text, String value, String[] keywords) {
			try {
				JSONObject obj = new JSONObject();
				obj.put("_text", text);
				obj.put("_value", value);
				if(keywords != null && keywords.length > 0) {
					obj.put("_keywords", keywords);
				}
				put(obj);
			}catch(final Exception e) {
				
			}
		}
	}

	public class MAGKeywordValueSet extends JSONArray {
		public void addValue(String text, String value) {
			try {
				JSONObject obj = new JSONObject();
				obj.put("_text", text);
				obj.put("_value", value);
				put(obj);
			}catch(final Exception e) {
			}
		}
	}

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