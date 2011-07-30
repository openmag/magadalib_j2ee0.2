package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

public class MAGSelect extends MAGInputBase {
	
	public enum MAGSelectUI implements StringEnum {
		MAGSELECT_UI_RADIO("__radio_"),
		MAGSELECT_UI_LIST("__list_"),
		MAGSELECT_UI_AUTO("__auto_");
		
		private final String _ui;
		private MAGSelectUI(String ui) {
			_ui = ui;
		}
		
		public String toString() {
			return _ui;
		}
	}

	public MAGSelect(String title, String id) {
		this(title, id, "", MAGSelectUI.MAGSELECT_UI_AUTO);
	}

	public MAGSelect(String title, String id, String value) {
		this(title, id, value, MAGSelectUI.MAGSELECT_UI_AUTO);
	}

	public MAGSelect(String title, String id, String value, MAGSelectUI ui) {
		super(title, id, value);
		setAttr("_options", new JSONArray());
		setAttr("_ui", ui);
	}

	public void addOption(String text, String val) {
		try {
			JSONArray options = (JSONArray) getAttr("_options");
			JSONObject pair = new JSONObject();
			pair.put("_text", text);
			pair.put("_value", val);
			options.put(pair);
		}catch(final Exception e) {
			
		}
	}
}
