package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;

public abstract class MAGContainerInputBase extends MAGInputBase implements MAGContainerInterface {

	protected MAGContainerInputBase(String title, String id, String value) {
		super(title, id, value);
		setAttr("_content", new JSONArray());
	}
	
	public void addChild(MAGComponent comp) {
		try {
			JSONArray body = (JSONArray) getAttr("_content");
			body.put(comp);
		}catch(final Exception e) {
			
		}
	}

}
