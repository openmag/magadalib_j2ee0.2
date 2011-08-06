package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONObject;

import com.anheinno.magadapter.lib.MAGConfig;
import com.anheinno.magadapter.lib.ui.MAGLinkableComponent.MAGLinkTarget;

public class MAGInfoGridData extends JSONObject {

	public MAGInfoGridData(String summary, String[] data, MAGLinkURL link, String id) {
		this(summary, data, link, MAGLinkTarget.LINK_TARGET_SELF, MAGConfig.getDefaultExpire(), id);
	}
	
	public MAGInfoGridData(String summary, String[] data, MAGLinkURL link, long expire, String id) {
		this(summary, data, link, MAGLinkTarget.LINK_TARGET_SELF, expire, id);
	}

	public MAGInfoGridData(String summary, String[] data, MAGLinkURL link, MAGLinkTarget target, long expire, String id) {
		try {
			put("_summary", summary);
			put("_data", data);
			put("_target", target.toString());
			if (target == MAGLinkTarget.LINK_TARGET_CUSTOMCONTROL)
			{
				put("_link", link.getClassParams());
			}
			else if (target == MAGLinkTarget.LINK_TARGET_SCRIPT)
			{
				put("_link", link.getScripts());
			}
			else
			{
				put("_link", link.getURL());
			}
			put("_expire", expire);
			put("_id", id);
		}catch(final Exception e) {
			
		}
	}
	
	public MAGInfoGridData setSummary1(String summary1) {
		try {
			put("_summary1", summary1);
		}catch(final Exception e) {			
		}
		return this;
	}
	
	public MAGInfoGridData setSummary2(String summary2) {
		try {
			put("_summary2", summary2);
		}catch(final Exception e) {
			
		}
		return this;
	}
	
	public MAGInfoGridData setHint(String hint) {
		try {
			put("_hint", hint);
		}catch(final Exception e) {
		}
		return this;
	}
	
	public MAGInfoGridData setSave(boolean save)
	{
		try {
			put("_save", save?"true":"false");
		}catch(final Exception e) {			
		}
		return this;
	}

}
