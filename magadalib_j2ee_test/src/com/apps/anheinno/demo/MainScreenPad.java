package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGLink;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;
import com.anheinno.magadapter.lib.ui.MAGLinkTarget;
import com.anheinno.magadapter.lib.ui.MAGMenuItem;
import com.anheinno.magadapter.lib.ui.MAGStyle;

public class MainScreenPad implements IMAGHandler
{
	private static final int DEFAULT_EXPIRE = 72*24;

	public String getAction()
	{
		return "MAINSCREENPAD";
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

		MAGStyle imgbtn_style = new MAGStyle();
		imgbtn_style.setAlignCenter();
		imgbtn_style.setIWidth("300");
		imgbtn_style.setIHeight("100%");
		imgbtn_style.setHeight(100);
		imgbtn_style.setStyle("padding", 20);
		imgbtn_style.setStyle("text-style", "icon=icon_unread_doc.png icon-position=right text-color=#ff0000 line-limit=1 text-valign=center icon-valign=center");
		imgbtn_style.setStyle("focus-text-style", "icon=icon_unread_doc.png icon-position=left text-color=#ff0000 text-align=right icon-align=left line-limit=1 text-valign=center");
		imgbtn_style.setStyle("visited-text-style", "icon=icon_read_doc.png line-limit=1 text-valign=center");
		imgbtn_style.setStyle("focus-visited-text-style", "icon=icon_read_doc.png icon-position=top line-limit=1 text-valign=center");
		imgbtn_style.setStyle("body-background", "button_normal_300.png");
		imgbtn_style.setStyle("focus-body-background", "button_over_300.png");
		imgbtn_style.setBackground("image=bitmapborder.png color=#000000 duplicate=bitmap-border border-top=14 border-left=13 border-bottom=16 border-right=17");
		imgbtn_style.setStyle("hint-background", "image=bitmapborder.png color=#000000 duplicate=bitmap-border border-top=14 border-left=13 border-bottom=16 border-right=17");
		imgbtn_style.setFocusBackground("alpha=0");
		imgbtn_style.setStyle("hint-text-style", "font-scale=0.8 text-align=left padding-top=14 padding-bottom=16 padding-left=13 padding-right=17");
		imgbtn_style.setAlignCenter();
		doc.addClass("imgbtn_style", imgbtn_style);

		MAGStyle style = new MAGStyle();
		style.setAlignCenter();
		style.setBackground("start-color=#ff0000 end-color=#880000 corner=10");
		style.setFocusBackground("start-color=#00ff00 end-color=#008800 corner=5");;
		style.setStyle("text-style", "align=center color=#ffe401");
		style.setStyle("visited-text-style", "font-style=italic color=#00e401");
		style.setStyle("focus-text-style", "align=center color=#83530e");
		style.setBorder(5, null);
		style.setHeight(60);

		MAGLink btn1 = new MAGLink("文档一Test", (new MAGLinkURL()).setHandler("SCREEN1").addParam("flag", "中文").setNotify(true).setSaveHistory(false), new MAGLinkTarget("_content"));
		btn1.setClass("imgbtn_style");
		btn1.setHint("Document 1 shows some useful information.\nTooltip ....\nToootip ....\n12312312\n123123\n");
		btn1.setStatus("Document 1 get focus!");
		doc.addChild(btn1);

		MAGStyle btn2_style = new MAGStyle();
		btn2_style.setStyle("hint-text-style", "font-scale=2.0 text-align=right padding=20");
		btn2_style.setStyle("hint-border-width", "0");
		btn2_style.setStyle("hint-background-transparent", "true");
		
		MAGLink btn2 = new MAGLink("文档二", (new MAGLinkURL()).setHandler("SCREEN2").setExpireHours(DEFAULT_EXPIRE).setNotify(true), new MAGLinkTarget("_content"));
		btn2.setClass("imgbtn_style");
		btn2.setStyle(btn2_style);
		btn2.setHint("这是浮动消息窗口，焦点停留一段时间后就会自动弹出。");
		doc.addChild(btn2);

		MAGLink btn3 = new MAGLink("报价单", (new MAGLinkURL()).setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE).setNotify(false), new MAGLinkTarget("_content"));
		btn3.setStyle(style);
		doc.addChild(btn3);

		MAGLink btn4 = new MAGLink("只读组件", (new MAGLinkURL()).setHandler("SCREEN_READONLY").setExpireHours(DEFAULT_EXPIRE), new MAGLinkTarget("_content"));
		btn4.setStyle(style);
		doc.addChild(btn4);

		btn4 = new MAGLink("MAGList示例", (new MAGLinkURL()).setHandler("MAGLISTEXAMPLE").setExpireHours(DEFAULT_EXPIRE), new MAGLinkTarget("_content"));
		btn4.setStyle(style);
		doc.addChild(btn4);

		btn4 = new MAGLink("九宫格", (new MAGLinkURL()).setHandler("NINEGRID").setExpireHours(DEFAULT_EXPIRE), MAGLinkTarget.LINK_TARGET_NEW);
		btn4.setStyle(style);
		doc.addChild(btn4);

		MAGLinkURL classinfo = new MAGLinkURL();
		classinfo.setClass("com.anheinno.libs.mag.controls.calendar.CalendarLinkedControl");
		classinfo.addParam((new MAGLinkURL("services.php")).setHandler("GETCALENDAR").getURL());
		MAGLink btn5 = new MAGLink("日历共享", classinfo.setExpireHours(0), MAGLinkTarget.LINK_TARGET_CUSTOMCONTROL);
		btn5.setStyle(style);
		doc.addChild(btn5);

		MAGLink btn6 = new MAGLink("创建新的约会", (new MAGLinkURL()).setHandler("NEWAPPOINTMENT").setExpireHours(0), MAGLinkTarget.LINK_TARGET_NEW);
		btn6.setStyle(style);
		btn6.setHint("To create new appointment!\nThis is a brand new function, please try it. You will not disappoint!!!");
		doc.addChild(btn6);
		
		MAGLink btn7 = new MAGLink("任意位置测试", (new MAGLinkURL()).setHandler("LAYOUTTEST").setExpireHours(DEFAULT_EXPIRE), MAGLinkTarget.LINK_TARGET_NEW);
		btn7.setStyle(style);
		doc.addChild(btn7);

		MAGMenuItem menu = new MAGMenuItem("访问新浪网", new MAGLinkURL("http://www.sina.com.cn").setExpireHours(0), MAGLinkTarget.LINK_TARGET_BROWSER);
		doc.addChild(menu);
		
		menu = new MAGMenuItem("报价单", (new MAGLinkURL()).setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE), MAGLinkTarget.LINK_TARGET_NEW);
		doc.addChild(menu);
		
		menu = new MAGMenuItem("Info Grid Demo", (new MAGLinkURL()).setHandler("INFOGRIDDEMO").setExpireHours(DEFAULT_EXPIRE), MAGLinkTarget.LINK_TARGET_NEW);
		doc.addChild(menu);
		
		MAGMenuItem tieredselectdemo = new MAGMenuItem("TieredSelected Demo", new MAGLinkURL().setHandler("MAGTIEREDSELECTDEMO"), new MAGLinkTarget("_content"));
		doc.addChild(tieredselectdemo);
		
		MAGMenuItem keywordfilterselect = new MAGMenuItem("KeywordsFilterSelect Demo", new MAGLinkURL().setHandler("MAGKEYWORDFILTERSELECTDEMO"), new MAGLinkTarget("_content"));
		doc.addChild(keywordfilterselect);
		
		//MAGLog.log(doc.toString());
		
		req.response(doc);
		return true;
	}

}
