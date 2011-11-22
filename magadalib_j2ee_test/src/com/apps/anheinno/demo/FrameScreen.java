package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGFrame;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;
import com.anheinno.magadapter.lib.ui.MAGStyle;

public class FrameScreen implements IMAGHandler
{
	private static final int DEFAULT_EXPIRE = 72*24;

	public String getAction()
	{
		return "FRAMESCREEN";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("MAIN");

		MAGStyle doc_style = new MAGStyle();
		doc_style.setBackground("login.jpg");
		doc_style.setStyle("title-background", "top_bg.png #FF0000 #00FF00 duplicate adjust-vertical");
		doc_style.setStyle("title-icon", "icon_read_doc.png");
		doc_style.setStyle("title-height", "56");
		doc_style.setStyle("title-font-weight", "bold");
		doc.setStyle(doc_style);

		MAGFrame menu_frame = new MAGFrame(new MAGLinkURL().setHandler("MAINSCREENPAD").setExpireHours(DEFAULT_EXPIRE), "_menu");
		
		MAGStyle menu_style = new MAGStyle();
		menu_style.setWidthPercentage(30);
		menu_style.setHeightRestOfScreen();
		
		menu_frame.setStyle(menu_style);
		
		doc.addChild(menu_frame);
		
		MAGFrame content_frame = new MAGFrame(new MAGLinkURL().setHandler("SCREEN1").setExpireHours(DEFAULT_EXPIRE), "_content");
		
		MAGStyle content_style = new MAGStyle();
		content_style.setWidthRestOfScreen();
		content_style.setHeightRestOfScreen();
		
		content_frame.setStyle(content_style);
		
		doc.addChild(content_frame);
		
		req.response(doc);
		return true;
	}

}
