package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGImage;
import com.anheinno.magadapter.lib.ui.MAGLink;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;
import com.anheinno.magadapter.lib.ui.MAGLinkTarget;
import com.anheinno.magadapter.lib.ui.MAGNote;
import com.anheinno.magadapter.lib.ui.MAGPanel;
import com.anheinno.magadapter.lib.ui.MAGStyle;
import com.anheinno.magadapter.lib.ui.MAGText;

public class Screen2 implements IMAGHandler
{
	private static final int DEFAULT_EXPIRE = 72*24;
	
	public String getAction()
	{
		return "SCREEN2";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("MAG Document 2 for " + req.getUsername());

		MAGPanel panel1 = new MAGPanel("Read only component");
		panel1.addChild(new MAGText("MAGText", "这是一个MAGText"));
		panel1.addChild(new MAGNote("MAGNote", "这是一个MAGNote"));
		panel1.addChild(new MAGLink("前一个文档", new MAGLinkURL().setHandler("SCREEN1").addParam("_user", req.getUsername()).setExpireHours(DEFAULT_EXPIRE), MAGLinkTarget.LINK_TARGET_SELF));
		
		MAGStyle imgstyle = new MAGStyle();
		imgstyle.setIWidth("0.6");
		imgstyle.setAlignCenter();

		MAGImage img1 = new MAGImage("图片1", new MAGLinkURL("http://curl.haxx.se/ds-curlicon.png"));
		img1.setStyle(imgstyle);
		panel1.addChild(img1);

		MAGImage img2 = new MAGImage("图片2", new MAGLinkURL("http://static.howstuffworks.com/gif/nuclear-test-7.jpg"));
		img2.setStyle(imgstyle);
		panel1.addChild(img2);

		MAGImage img3 = new MAGImage("图片3", new MAGLinkURL("http://www.textually.org/tv/archives/images/set3/test-pattern-clock_4767.jpg"));
		img3.setStyle(imgstyle);
		panel1.addChild(img3);

		MAGImage img4 = new MAGImage("图片4", new MAGLinkURL("http://www.shybm.com/upload/news/n2008022711430910.jpg"));
		img4.setStyle(imgstyle);
		panel1.addChild(img4);

//		MAGBarcodeScanner scanner = new MAGBarcodeScanner("条码扫描", "_scanner");
//		panel1.addChild(scanner);

		doc.addChild(panel1);

		req.response(doc);
		return true;
	}

}
