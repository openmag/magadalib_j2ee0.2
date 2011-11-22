package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGLink;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;
import com.anheinno.magadapter.lib.ui.MAGLinkTarget;
import com.anheinno.magadapter.lib.ui.MAGPanel;
import com.anheinno.magadapter.lib.ui.MAGStyle;

public class NineGrid implements IMAGHandler
{
	private static final int DEFAULT_EXPIRE = 72*24;

	public String getAction()
	{
		return "NINEGRID";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("九宫格实例");

		MAGStyle doc_style = new MAGStyle();
		doc_style.setStyle("status-color", "#ffff00");
		doc_style.setStyle("title-background", "top_bg.png duplicate adjust-vertical");
		doc_style.setStyle("title-icon", "icon_read_doc.png");
		doc_style.setStyle("title-height", "56");
		doc_style.setStyle("title-font-weight", "bold");
		doc_style.setBackground("#000066");
		doc.setStyle(doc_style);

		MAGPanel panel = new MAGPanel("");
		MAGStyle panel_style = new MAGStyle();
		panel_style.setBackground("#000066");
		panel_style.setPadding(0);
		panel.setStyle(panel_style);

		MAGStyle imgbtn_style = new MAGStyle();
		imgbtn_style.setAlignCenter();
		imgbtn_style.setWidthFractorial(1, 4);
		imgbtn_style.setIWidth("100%");
		imgbtn_style.setHeight(100);
		imgbtn_style.setBorder(10, "#000066");
		imgbtn_style.setIHeight("100%");
		imgbtn_style.setStyle("text-alignment", "center");
		imgbtn_style.setBackground("start-color=#000000 end-color=#000044 corner=10 icon=icon_unread_doc.png align=center valign=middle duplicate=none");
		imgbtn_style.setFocusBackground("start-color=#000044 end-color=#000000 corner=10 icon=icon_read_doc.png align=center valign=middle duplicate=none");
		imgbtn_style.setStyle("status-text-style", "color=#ff0000 text-align=center");
		imgbtn_style.setStyle("status-background", "#ff0066 #000022");

		doc.addClass("btn_style", imgbtn_style);

		for(int i = 0; i < 7; i ++) 
		{
			MAGLink btn = new MAGLink(Integer.toString(i), new MAGLinkURL().setHandler("SCREEN1").setExpireHours(DEFAULT_EXPIRE).setNotify(true), MAGLinkTarget.LINK_TARGET_NEW);
			btn.setClass("btn_style");
			btn.setHint("Document " + Integer.toString(i) + " shows some useful information");
			btn.setStatus("Document " + Integer.toString(i) + " get focus!");
			panel.addChild(btn);
		}

		doc.addChild(panel);

		MAGLink close_link = new MAGLink("Close window", new MAGLinkURL().setScripts("close();").setExpireHours(0), MAGLinkTarget.LINK_TARGET_SCRIPT);
		MAGStyle cl_style = new MAGStyle();
		cl_style.setAlignCenter();
		cl_style.setStyle("text-style", "color=white");
		cl_style.setStyle("focus-text-style", "color=red");
		close_link.setStyle(cl_style);
		doc.addChild(close_link);

		String open_url = (new MAGLinkURL().setHandler("SCREEN1")).getURL(); 
		MAGLink open_link = new MAGLink("Open window", new MAGLinkURL().setScripts("open("+open_url+");").setExpireHours(0), MAGLinkTarget.LINK_TARGET_SCRIPT);
		open_link.setStyle(cl_style);
		doc.addChild(open_link);

		String popup_url = (new MAGLinkURL().setHandler("SCREEN1")).getURL(); 
		MAGLink popup_link = new MAGLink("Popup window", new MAGLinkURL().setScripts("popup("+popup_url+")").setExpireHours(0), MAGLinkTarget.LINK_TARGET_SCRIPT);
		popup_link.setStyle(cl_style);
		doc.addChild(popup_link);

		req.response(doc);
		return true;
	}

}
