package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;

public class MAGInfoGridDataSet extends JSONArray {
	public void addData(MAGInfoGridData data) {
		put(data);
	}
}
