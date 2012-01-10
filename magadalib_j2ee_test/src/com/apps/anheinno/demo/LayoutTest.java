package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGLink;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;
import com.anheinno.magadapter.lib.ui.MAGStyle;

public class LayoutTest implements IMAGHandler {
	private static final int DEFAULT_EXPIRE = 72*24;
	
	@Override
	public String getAction() {
		return "LAYOUTTEST";
	}

	@Override
	public boolean process(MAGRequest req) {
		MAGDocument doc = new MAGDocument("布局测试");
		
		MAGLinkURL url = new MAGLinkURL();
		url.setHandler("MAGLISTEXAMPLE");
		url.setExpireHours(DEFAULT_EXPIRE);
		
		MAGLink link = new MAGLink("", url);
		
		MAGStyle style = new MAGStyle();
		style.setAlignCenter();
		style.setWidthPixals(150);
		style.setHeightRestOfScreen();
		style.setBorder(1, "black");
		style.setPaddingTop(30);
		style.setPaddingBottom(30);
		style.setIWidth("100%");
		style.setIHeight("100%");
		style.setStyle("body-background", "icon=taikangyinbao_customer.png");
		style.setStyle("focus-body-background", "icon=taikangyinbao_customer_focus.png");
		
		link.setStyle(style);
		
		doc.addChild(link);
		
		req.response(doc);
		return true;
	}

}
