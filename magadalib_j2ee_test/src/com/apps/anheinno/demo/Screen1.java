package com.apps.anheinno.demo;

import java.util.Hashtable;
import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGPushClient;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDate;
import com.anheinno.magadapter.lib.ui.MAGDate.MAGDateUI;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGFileLink;
import com.anheinno.magadapter.lib.ui.MAGHiddenInput;
import com.anheinno.magadapter.lib.ui.MAGInputCombo;
import com.anheinno.magadapter.lib.ui.MAGInputDuplicator;
import com.anheinno.magadapter.lib.ui.MAGLink;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;
import com.anheinno.magadapter.lib.ui.MAGLinkableComponent.MAGLinkTarget;
import com.anheinno.magadapter.lib.ui.MAGMenuItem;
import com.anheinno.magadapter.lib.ui.MAGMultiselect;
import com.anheinno.magadapter.lib.ui.MAGNote;
import com.anheinno.magadapter.lib.ui.MAGPanel;
import com.anheinno.magadapter.lib.ui.MAGPanel.MAGPanelExpandStatus;
import com.anheinno.magadapter.lib.ui.MAGPassword;
import com.anheinno.magadapter.lib.ui.MAGRadio;
import com.anheinno.magadapter.lib.ui.MAGSelect;
import com.anheinno.magadapter.lib.ui.MAGSelect.MAGSelectUI;
import com.anheinno.magadapter.lib.ui.MAGInputInterface;
import com.anheinno.magadapter.lib.ui.MAGStyle;
import com.anheinno.magadapter.lib.ui.MAGSubmit;
import com.anheinno.magadapter.lib.ui.MAGText;
import com.anheinno.magadapter.lib.ui.MAGTextinput;
import com.anheinno.magadapter.lib.ui.MAGTextinput.MAGTextinputFilter;

public class Screen1 implements IMAGHandler
{

	private static final int DEFAULT_EXPIRE = 72*24;

	public String getAction()
	{
		return "SCREEN1";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("MAG Document 1 for " + req.getUsername());
		MAGPanel panel1 = new MAGPanel("Read-only control panel");
		panel1.setExpandStatus(MAGPanelExpandStatus.MAGPANEL_EXPAND_COLLAPSE);
		
		MAGText text = new MAGText("MAGText", "这是Active Focus, 010-62774256");
		MAGStyle style = new MAGStyle();
		style.setBgcolor("#330000");
		style.setStyle("focus", "active");
		style.setBorderBottom(1, "#00FF00");
		text.setStyle(style);
		panel1.addChild(text);
		
		MAGText text1 = new MAGText("MAGText", "这是Active Focus, http://msmb.mobi");
		text1.setStyle(style);
		panel1.addChild(text1);
		
		MAGText text2 = new MAGText("", "居中对齐(focus=none)");
		MAGStyle style2 = new MAGStyle();
		style2.setAlignCenter();
		style2.setStyle("focus", "none");
		style2.setStyle("text-style", "font-scale=0.8 color=purple");
		text2.setStyle(style2);
		panel1.addChild(text2);
		
		MAGText text3 = new MAGText("", "靠右对齐(focus=normal)");
		MAGStyle style3 = new MAGStyle();
		style3.setAlignRight();
		style3.setStyle("focus", "normal");
		text3.setStyle(style3);
		panel1.addChild(text3);
		
		MAGText text4 = new MAGText("", "focus=active则占据");
		MAGStyle style4 = new MAGStyle();
		style4.setStyle("focus", "active");
		text4.setStyle(style4);
		panel1.addChild(text4);
		
		panel1.addChild(new MAGNote("MAGNote", "这是一个MAGNote"));
		
		MAGLink link = new MAGLink("下一个文档", new MAGLinkURL().setHandler("SCREEN2").addParam("_user", req.getUsername()).setExpireHours(DEFAULT_EXPIRE), MAGLinkTarget.LINK_TARGET_SELF);
		MAGStyle style_link = new MAGStyle();
		style_link.setAlignCenter();
		style_link.setHeight(60);
		style_link.setVAlignMiddle();
		style_link.setBorderBottom(1, "#000000");
		style_link.setBackground("#ff0000");
		style_link.setFocusBackground("#00ff00");
		link.setStyle(style_link);
		panel1.addChild(link);
		
		doc.addChild(panel1);
		
		MAGPanel panel2 = new MAGPanel("Input control panel");
		panel2.setExpandStatus(MAGPanelExpandStatus.MAGPANEL_EXPAND_EXPAND);
		
		MAGPushClient.setLocalVar(req, "test_local_var", "Default text string NY");
		String text_val = MAGPushClient.getLocalVar(req, "test_local_var");
		MAGTextinput text_input = new MAGTextinput("MAGTextinput", "_textinput", text_val);
		MAGStyle textinput_style = new MAGStyle();
		textinput_style.setStyle("title-width", "100");
		textinput_style.setStyle("content-padding", "10");
		text_input.setStyle(textinput_style);
		text_input.nonEmpty();
		panel2.addChild(text_input); 
		
		
		MAGTextinput number = new MAGTextinput("MAGTextinput(Number)", "_numberinput", "", MAGTextinputFilter.TEXTINPUT_FILTER_NUMERIC);
		number.nonEmpty();
		panel2.addChild(number); 
		
		panel2.addChild(new MAGPassword("MAGPassword", "_password"));
		panel2.addChild(new MAGDate("MAGDate/Date", "_date", 0, MAGDateUI.MAGDATE_UI_DATE));
		panel2.addChild(new MAGDate("MAGDate/Time", "_time", 0, MAGDateUI.MAGDATE_UI_TIME));
		panel2.addChild(new MAGDate("MAGDate/DateTime", "_datetime", 0, MAGDateUI.MAGDATE_UI_DATETIME));
		
		MAGSelect sel = new MAGSelect("MAGSelect", "_select", "", MAGSelectUI.MAGSELECT_UI_AUTO);
		sel.addOption("Option1", "Option1");
		sel.addOption("Option2", "Option2");
		sel.addOption("Option3", "Option3");
		sel.addOption("Option4", "Option4");
		sel.setStyle(style_link);
		panel2.addChild(sel);

		MAGMultiselect msel = new MAGMultiselect("MAGMultiselect", "_mselect", null);
		msel.addOption("Option1", "Option1", true);
		msel.addOption("Option2", "Option2", false);
		msel.addOption("Option3", "Option3", false);
		msel.addOption("Option4", "Option4", true);
		style_link.setBgcolor("#00ff00");
		msel.setStyle(style_link);
		panel2.addChild(msel);

		MAGRadio radio = new MAGRadio("Radio 1", "radio_group", "1", "r1");
		panel2.addChild(radio);
		radio = new MAGRadio("Radio 2", "radio_group", "2", "r2");
		panel2.addChild(radio);
		radio = new MAGRadio("Radio 3", "radio_group", "3", "r3");
		panel2.addChild(radio);
		
		panel2.addChild(new MAGHiddenInput("_hidden1", "hiddenVal1"));
		panel2.addChild(new MAGHiddenInput("_hidden2", "hiddenVal2"));
		panel2.addChild(new MAGHiddenInput("_hidden3", "hiddenVal3"));

		MAGStyle submit1_style = new MAGStyle();
		submit1_style.setAlignRight();
		submit1_style.setWidthFractorial(1, 2);
		MAGSubmit submit1 = new MAGSubmit("Submit", "SUBMITRESULT", new MAGLinkURL(), MAGLinkTarget.LINK_TARGET_NEW);
		submit1.setConfirm("您确定提交以上信息吗？");
		submit1.setStyle(submit1_style);
		panel2.addChild(submit1);
		
		MAGStyle submit2_style = new MAGStyle();
		MAGSubmit submit2 = new MAGSubmit("Submit2", "SUBMITRESULT", new MAGLinkURL(), MAGLinkTarget.LINK_TARGET_NEW);
		submit2.setRequire(new MAGInputInterface[]{number});
		submit2_style.setAlignLeft();
		submit2_style.setWidthFractorial(1, 2);
		submit2.setStyle(submit2_style);
		panel2.addChild(submit2);
		
		String init_value = "";
		Hashtable<String, String> inputcombo_value = new Hashtable<String, String>();
		inputcombo_value.put("_firstname", "Qiu");
		inputcombo_value.put("_lastname", "Jian");
		inputcombo_value.put("_notes", "OK");
		init_value = inputcombo_value.toString();
		
		MAGInputCombo combo = new MAGInputCombo(init_value, "_combo");	
		MAGTextinput first_name = new MAGTextinput("名：", "_firstname", "");
		combo.addChild(first_name);
		MAGTextinput last_name  = new MAGTextinput("姓：", "_lastname", "");
		combo.addChild(last_name);
		MAGTextinput notes = new MAGTextinput("备注：", "_notes", "", MAGTextinputFilter.TEXTINPUT_FILTER_NOTES);
		combo.addChild(notes);
		panel2.addChild(combo);

		MAGInputDuplicator duplicator = new MAGInputDuplicator("多输入测试", "_duplicator", combo, init_value);
		duplicator.setSortable(false);
		duplicator.setMaxCount(4);
		panel2.addChild(duplicator);
		
		doc.addChild(panel2);
		
		MAGFileLink download1 = new MAGFileLink("Word下载测试", "", new MAGLinkURL("http://192.168.0.201/download/aog.doc"));
		doc.addChild(download1);
		MAGFileLink download2 = new MAGFileLink("Excel下载测试", "", new MAGLinkURL("http://192.168.0.201/download/citics_txl.xls"));
		doc.addChild(download2);
		MAGFileLink download3 = new MAGFileLink("PowerPoint下载测试", "", new MAGLinkURL("http://192.168.0.201/download/anheintro.ppt"));
		doc.addChild(download3);
		MAGFileLink download4 = new MAGFileLink("Pdf下载测试", "", new MAGLinkURL("http://192.168.0.201/download/exim.pdf"));
		doc.addChild(download4);
		MAGFileLink download5 = new MAGFileLink("Pdf下载测试(<256K)", "", new MAGLinkURL("http://192.168.0.201/download/aw_overview.pdf"));
		doc.addChild(download5);
		
		MAGMenuItem menu = new MAGMenuItem("报价单", new MAGLinkURL().setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE), MAGLinkTarget.LINK_TARGET_NEW);
		doc.addChild(menu);
		
		
		req.response(doc);
		return true;
	}

}
