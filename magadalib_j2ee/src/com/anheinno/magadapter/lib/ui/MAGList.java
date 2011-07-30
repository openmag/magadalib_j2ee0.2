package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

public class MAGList extends MAGContainerBase {

	public enum MAGListOrderFieldType {
		FIELD_TYPE_NUMBER("NUMERIC"),
		FIELD_TYPE_STRING("TEXT");
		
		private final String _type;
		private MAGListOrderFieldType(String type) {
			_type = type;
		}
		public String toString() {
			return _type;
		}
	}
	
	public class MAGListOrderFieldSet extends JSONArray {
		public void addField(String label, String field) {
			addField(label, field, null);
		}
		
		public void addField(String label, String field, MAGListOrderFieldType type) {
			try {
				JSONObject obj = new JSONObject();
				obj.put("_label", label);
				obj.put("_field", field);
				if(type != null) {
					obj.put("_type", type.toString());
				}
				put(obj);
			}catch(final Exception e) {
				
			}
		}
	}
	
	public MAGList(String title) {
		super(title, null);
	}
	
	public MAGList(String title, String id) {
		super(title, id);
	}

	public void setItemsPerPage(int items_per_page) {
		setAttr("_items_per_page", items_per_page);
	}
	
	public void setDescending(boolean descending) {
		setAttr("_descending", descending);
	}
	
	public void setFooter(String footer) {
		setAttr("_footer", footer);
	}
	
	public void setOrderBy(MAGListOrderFieldSet set) {
		setAttr("_orderby", set);
	}
}
