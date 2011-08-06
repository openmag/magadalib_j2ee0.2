package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

public class MAGTieredSuboptionsSet extends JSONObject {
	
	MAGTieredSuboptionsSet(String title) {
		super();
		try {
			if(title != null && title.length() > 0) {
				put("_title", title);
			}
			put("_options", new JSONArray());
		}catch(final Exception e) {
			
		}
	}
	
	private JSONArray getOptions() {
		try {
			return this.getJSONArray("_options");
		}catch(final Exception e) {
			
		}
		return null;
	}
	
	public void addOption(String text) {
		addOption(text, null, null);
	}
	
	public void addOption(String text, String value) {
		addOption(text, value, null);
	}
	
	public void addOption(String text, String value, MAGTieredSuboptionsSet suboption) {
		try {
			JSONArray arr = getOptions();
			if(arr != null) {
				JSONObject obj = new JSONObject();
				obj.put("_text", text);
				if(value != null && value.length() > 0) { 
					obj.put("_value", value);
				}
				if(suboption != null && suboption.length() > 0) {
					obj.put("", value);
				}
				arr.put(obj);
			}
		}catch(final Exception e) {
			
		}
	}
}
