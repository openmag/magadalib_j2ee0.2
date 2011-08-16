package com.apps.anheinno.demo;

import java.util.Date;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGCustominput;
import com.anheinno.magadapter.lib.ui.MAGDate;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;
import com.anheinno.magadapter.lib.ui.MAGPanel;
import com.anheinno.magadapter.lib.ui.MAGSelect;
import com.anheinno.magadapter.lib.ui.MAGStyle;
import com.anheinno.magadapter.lib.ui.MAGSubmit;
import com.anheinno.magadapter.lib.ui.MAGTextinput;
import com.anheinno.magadapter.lib.ui.MAGTextinput.MAGTextinputFilter;

public class NewAppointment implements IMAGHandler
{
	public String getAction()
	{
		return "NEWAPPOINTMENT";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("创建新的约会("+req.getUsername()+")");
		MAGStyle doc_style = new MAGStyle();
		doc_style.setBackground("white");
		doc.setStyle(doc_style);

		MAGPanel panel = new MAGPanel("");

		MAGTextinput subj = new MAGTextinput("主题：", "_subject", "");
		subj.nonEmpty();
		panel.addChild(subj); 

		MAGTextinput loc = new MAGTextinput("地点：", "_location", "");
		loc.nonEmpty();
		panel.addChild(loc);
		
		Date d = new Date();
		long tm = d.getTime();
		MAGDate dtstart = new MAGDate("开始时间：", "_dtstart", tm);
		panel.addChild(dtstart);

		String dur_str = "";
		MAGSelect dura = new MAGSelect("持续时间：", "_duration", "1800");
		for(int i = 0; i < 8; i++) 
		{
			if (i > 0) 
			{
				dura.addOption(i + "小时", Integer.toString(i*3600));
				dur_str = i + "小时30分钟";
			}
			else 
			{
				dur_str = "30分钟";
			}
			dura.addOption(dur_str, Integer.toString(i*3600+1800));
		}
		for(int i = 1; i <= 7; i ++) 
		{
			dura.addOption(i + "天", Integer.toString(i*24*3600));
		}
		panel.addChild(dura);

		MAGSelect status = new MAGSelect("状态：", "_busystatus", "BUSY");
		status.addOption("忙", "BUSY");
		status.addOption("闲", "FREE");
		status.addOption("未定", "TENTATIVE");
		status.addOption("外出", "OOF");
		panel.addChild(status);

		MAGSelect alarm = new MAGSelect("提前提醒时间：", "_reminder", "900");
		alarm.addOption("5分钟", "300");
		alarm.addOption("15分钟", "900");
		alarm.addOption("半小时", "1800");
		for(int h = 1; h < 12; h ++) 
		{
			alarm.addOption(h + "小时", Integer.toString(h*3600));
		}
		panel.addChild(alarm);

		String[] params = {
			"http://192.168.0.205:10086/hostingapp/bb/contact_services.php?_action=GROUPDIR",
			"http://192.168.0.205:10086/hostingapp/bb/contact_services.php?_action=USERLIST",
			"http://192.168.0.205:10086/hostingapp/bb/contact_services.php?_action=SEARCH",
			"1"
		};
		MAGCustominput attendees = new MAGCustominput("参会者：", "com.anheinno.libs.mag.controls.contacts.ContactInput", "", params, "_attendees");
		panel.addChild(attendees);

		MAGTextinput note = new MAGTextinput("备注：", "_note", "", MAGTextinputFilter.TEXTINPUT_FILTER_NOTES);
		panel.addChild(note);
		
		MAGStyle btn_style_class = new MAGStyle();
		btn_style_class.setWidthFractorial(1, 3);
		btn_style_class.setIWidth("90%");
		btn_style_class.setAlignCenter();
		doc.addClass("BTN_STYLE_CLASS", btn_style_class);

		MAGSubmit submit = new MAGSubmit("保存", "SAVENEWAPPOINTMENT", new MAGLinkURL());
		submit.setClass("BTN_STYLE_CLASS");
		panel.addChild(submit);
		
		MAGSubmit submit2 = new MAGSubmit("保存2", "SAVENEWAPPOINTMENT", new MAGLinkURL());
		submit2.setClass("BTN_STYLE_CLASS");
		panel.addChild(submit2);

		MAGSubmit submit3 = new MAGSubmit("保存3", "SAVENEWAPPOINTMENT", new MAGLinkURL());
		submit3.setClass("BTN_STYLE_CLASS");
		panel.addChild(submit3);

		doc.addChild(panel);

		req.response(doc);
		return true;
	}

}
