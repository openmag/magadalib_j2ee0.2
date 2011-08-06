package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

import com.anheinno.magadapter.lib.ui.MAGInfoGrid.MAGInfoGridFieldType;

public class MAGInfoGridFieldSet extends JSONArray {
	public void addField(String field_title, MAGInfoGridFieldType type) {
		try {
			JSONObject obj = new JSONObject();
			obj.put("_title", field_title);
			obj.put("_type", type.toString());
			put(obj);
		}catch(final Exception e) {
			
		}
	}
}
