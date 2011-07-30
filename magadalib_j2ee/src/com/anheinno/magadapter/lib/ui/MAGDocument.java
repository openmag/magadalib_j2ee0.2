package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

public class MAGDocument extends MAGContainerBase {
	public MAGDocument() {
		this(null, null);
	}
	
	public MAGDocument(String title) {
		this(title, null);
	}

	public MAGDocument(String title, String id) {
		super(title, id);
	}

	public void addClass(String name, MAGStyle style) {
		JSONArray style_tbl = (JSONArray) getAttr("_style_tbl");
		if (style_tbl == null) {
			style_tbl = new JSONArray();
			setAttr("_style_tbl", style_tbl);
		}
		if (style.length() > 0) {
			try {
				JSONObject style_pair = new JSONObject();
				style_pair.put("_name", name);
				style_pair.put("_style", style);
				style_tbl.put(style_pair);
			}catch(final Exception e) {
				
			}
		}
	}
}
