package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDate;
import com.anheinno.magadapter.lib.ui.MAGDate.MAGDateUI;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGHiddenInput;
import com.anheinno.magadapter.lib.ui.MAGLink;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;
import com.anheinno.magadapter.lib.ui.MAGLinkableComponent.MAGLinkTarget;
import com.anheinno.magadapter.lib.ui.MAGMultiselect;
import com.anheinno.magadapter.lib.ui.MAGNote;
import com.anheinno.magadapter.lib.ui.MAGPanel;
import com.anheinno.magadapter.lib.ui.MAGPassword;
import com.anheinno.magadapter.lib.ui.MAGRadio;
import com.anheinno.magadapter.lib.ui.MAGSelect;
import com.anheinno.magadapter.lib.ui.MAGSelect.MAGSelectUI;
import com.anheinno.magadapter.lib.ui.MAGStyle;
import com.anheinno.magadapter.lib.ui.MAGSubmit;
import com.anheinno.magadapter.lib.ui.MAGTabPanel;
import com.anheinno.magadapter.lib.ui.MAGText;
import com.anheinno.magadapter.lib.ui.MAGTextinput;

public class Screen_Readonly implements IMAGHandler
{
	private static final int DEFAULT_EXPIRE = 72*24;

	public String getAction()
	{
		return "SCREEN_READONLY";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("MAG Document 1 for " + req.getUsername());

		MAGPanel panel1 = new MAGPanel("只读控件面板");

		MAGText text = new MAGText("MAGText", "这是Active Focus, 010-62774256");

		MAGStyle style = new MAGStyle();
		style.setBgcolor("#330000");
		style.setStyle("focus", "active");
		style.setBorderBottom(1, "#00FF00");
		text.setStyle(style);
		panel1.addChild(text);

		MAGText text2 = new MAGText("MAGText", "这是Active Focus, http://msmb.mobi");
		text2.setStyle(style);
		panel1.addChild(text2);

		panel1.addChild(new MAGNote("MAGNote", "这是一个MAGNote"));

		MAGLink link = new MAGLink("下一个文档", new MAGLinkURL().setHandler("SCREEN2").addParam("_user", req.getUsername()).setExpireHours(DEFAULT_EXPIRE), MAGLinkTarget.LINK_TARGET_NEW);
		MAGStyle style_link = new MAGStyle();
		style_link.setAlignCenter();
		style_link.setHeight(60);
		style_link.setVAlignMiddle();
		style_link.setBorderBottom(5, "#000000");
		style_link.setFocusBorderColorBottom("#FF0000");
		style_link.setBackground("#ff0000");
		link.setStyle(style_link);
		panel1.addChild(link);
		doc.addChild(panel1);

		MAGTabPanel tabpanel = new MAGTabPanel(2);

		MAGPanel panel2 = new MAGPanel("输入控件面板");

		MAGStyle panel2_style = new MAGStyle();
		panel2_style.setPadding(20);
		panel2_style.setBorder(5, "#ff0000");
		panel2_style.setBackground("start-color=#ffeeee end-color=#888888");
		panel2.setStyle(panel2_style);

		MAGTextinput text_input = new MAGTextinput("MAGTextinput", "_textinput", "");
		text_input.readOnly();
		panel2.addChild(text_input); 
		MAGPassword passwd = new MAGPassword("MAGPassword", "_password");
		passwd.readOnly();
		panel2.addChild(passwd);
		MAGDate date1 = new MAGDate("MAGDate/Date", "_date", 0, MAGDateUI.MAGDATE_UI_DATE);
		date1.readOnly();
		panel2.addChild(date1);
		MAGDate date2 = new MAGDate("MAGDate/Time", "_time", 0, MAGDateUI.MAGDATE_UI_TIME);
		date2.readOnly();
		panel2.addChild(date2);
		MAGDate date3 = new MAGDate("MAGDate/DateTime", "_datetime", 0, MAGDateUI.MAGDATE_UI_DATETIME);
		date3.readOnly();
		panel2.addChild(date3);

		MAGSelect sel = new MAGSelect("MAGSelect", "_select", "", MAGSelectUI.MAGSELECT_UI_AUTO);
		sel.addOption("Option1", "Option1");
		sel.addOption("Option2", "Option2");
		sel.addOption("Option3", "Option3");
		sel.addOption("Option4", "Option4");
		sel.setStyle(style_link);
		sel.readOnly();
		panel2.addChild(sel);

		MAGMultiselect msel = new MAGMultiselect("MAGMultiselect", "_mselect", null);
		msel.addOption("Option1", "Option1", true);
		msel.addOption("Option2", "Option2", false);
		msel.addOption("Option3", "Option3", false);
		msel.addOption("Option4", "Option4", true);
		style_link.setBackground("#00ff00");
		style_link.setFocusBackground("#0000ff");
		msel.setStyle(style_link);
		msel.readOnly();
		panel2.addChild(msel);

		MAGRadio radio = new MAGRadio("Radio 1", "radio_group", "1", "r1");
		panel2.addChild(radio);
		radio = new MAGRadio("Radio 2", "radio_group", "2", "r2");
		panel2.addChild(radio);
		radio = new MAGRadio("Radio 3", "radio_group", "3", "r3");
		panel2.addChild(radio);

		MAGStyle submit_style_class = new MAGStyle();
		submit_style_class.setWidthFractorial(1, 2);
		doc.addClass("submit_style_class", submit_style_class);
		
		MAGStyle submit_style1 = new MAGStyle();
		submit_style1.setAlignRight();
		MAGSubmit submit = new MAGSubmit("Submit", "SUBMITRESULT", new MAGLinkURL(), MAGLinkTarget.LINK_TARGET_NEW);
		submit.readOnly();
		submit.setStyle(submit_style1);
		submit.setClass("submit_style_class");
		panel2.addChild(submit);
		
		MAGStyle submit_style2 = new MAGStyle();
		MAGSubmit submit2 = new MAGSubmit("Submit2", "SUBMITRESULT", new MAGLinkURL(), MAGLinkTarget.LINK_TARGET_NEW);
		submit_style2.setAlignLeft();
		submit2.setStyle(submit_style2);
		submit2.setClass("submit_style_class");
		panel2.addChild(submit2);

		panel2.addChild(new MAGHiddenInput("_hidden1", "hiddenVal1"));
		panel2.addChild(new MAGHiddenInput("_hidden2", "hiddenVal2"));
		panel2.addChild(new MAGHiddenInput("_hidden3", "hiddenVal3"));

		tabpanel.addChild(panel2);

		MAGPanel panel3 = new MAGPanel("面板3");
		panel3.addChild(new MAGText("MAGText", "这是面板3"));
		tabpanel.addChild(panel3);

		MAGPanel panel4 = new MAGPanel("面板4");
		panel4.addChild(new MAGText("MAGText", "这是面板4"));
		tabpanel.addChild(panel4);

		MAGPanel panel5 = new MAGPanel("面板五");
		panel5.addChild(new MAGText("MAGText", "这是面板5"));
		tabpanel.addChild(panel5);

		MAGPanel panel6 = new MAGPanel("面板6");
		panel6.addChild(new MAGText("MAGText", "这是面板6"));
		tabpanel.addChild(panel6);

		MAGPanel panel7 = new MAGPanel("面板7");
		panel7.addChild(new MAGText("MAGText", "这是面板7"));
		tabpanel.addChild(panel7);

		doc.addChild(tabpanel);

//		doc.addChild(new MAGGadget(MAG_GADGET_SIGNAL));
//		doc.addChild(new MAGGadget(MAG_GADGET_BATTERY));
//		doc.addChild(new MAGGadget(MAG_GADGET_TIME));

		req.response(doc);
		return true;
	}

}
