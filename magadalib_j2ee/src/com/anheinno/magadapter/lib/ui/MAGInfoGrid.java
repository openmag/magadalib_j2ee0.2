package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

import com.anheinno.magadapter.lib.ui.MAGLinkableComponent.MAGLinkTarget;


public class MAGInfoGrid extends MAGInputBase {
	
	public enum MAGInfoGridFieldType {
		FIELD_TYPE_DATE("date"),
		FIELD_TYPE_NUMBER("int"),
		FIELD_TYPE_STRING("string");
		private final String _type;
		private MAGInfoGridFieldType(String type) {
			_type = type;
		}
		public String toString() {
			return _type;
		}
	}

	public class MAGInfoGridFieldSet extends JSONArray {
		public void addField(String title, MAGInfoGridFieldType type) {
			try {
				JSONObject obj = new JSONObject();
				obj.put("_title", title);
				obj.put("_type", type.toString());
				put(obj);
			}catch(final Exception e) {
				
			}
		}
	}

	public class MAGInfoGridData extends JSONObject {	
		protected MAGInfoGridData(String summary, JSONArray data, MAGLinkURL link, long expire, MAGLinkTarget target, String id) {
			super();
			try {
				put("_data", data);
				put("_summary", summary);
				put("_id", id);
				put("_target", target);
				if(target == MAGLinkTarget.LINK_TARGET_CUSTOMCONTROL) {
					put("_link", link.getClassParams());
				}else if(target == MAGLinkTarget.LINK_TARGET_SCRIPT) {
					put("_link", link.getScripts());
				}else {
					put("_link", link.getURL());
				}
				put("_expire", expire);
			}catch(final Exception e) {
				
			}
		}
		
		public void setSummary1(String summary1) {
			try {
				put("_summary1", summary1);
			}catch(final Exception e) {
				
			}
		}
		
		public void setSummary2(String summary2) {
			try {
				put("_summary2", summary2);
			}catch(final Exception e) {
				
			}
		}

		/*public void setNotify(boolean notify) {
			try {
				put("_notify", notify?"true":"false");
			}catch(final Exception e) {
				
			}
		}*/
		
		public void setSave(boolean save) {
			try {
				put("_save", save?"true":"false");
			}catch(final Exception e) {
				
			}
		}
		
		public void setHint(String hint) {
			try {
				put("_hint", hint);
			}catch(final Exception e) {
				
			}
		}
	}

	class MAGInfoGridDataSet extends JSONArray {
		public void addData(MAGInfoGridData data) {
			put(data);
		}
	}
	
	public MAGInfoGrid(String label, MAGInfoGridFieldSet fields,
			MAGInfoGridDataSet data, String[] values, String id) {
		super(label, id, MAGInputBase.toValueString(values));
		setAttr("_fields", fields);
		setAttr("_data", data);
	}
	
	public void setItemPerPage(int limit) {
		setAttr("_number", limit);
	}
	
	public void setFooter(String footer) {
		setAttr("_footer", footer);
	}
}