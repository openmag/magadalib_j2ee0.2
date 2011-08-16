package com.apps.anheinno.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGScript;
import com.anheinno.magadapter.lib.ui.MAGText;

public class SaveNewAppointment implements IMAGHandler
{

	public String getAction()
	{
		return "SAVENEWAPPOINTMENT";
	}

	public boolean process(MAGRequest req)
	{
		long dtstart = Long.parseLong(req.getVar("_dtstart"));
		long dtend = 0L; 
		
		if(dtstart <= 0) 
		{
			req.response("ERROR: 错误的请求！");
			return false;
		}

		String isallday = "0";
		if((Long.parseLong(req.getVar("_duration")) >= 3600*24)) 
		{
			isallday = "1";
			dtstart = ((long)(dtstart/3600/24))*3600*24;
			dtend = dtstart + 1000*Long.parseLong(req.getVar("_duration"));
		}
		
		//2010-08-12T18:00:00.000Z
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); 
        String dtstart_str = sdf.format(dtstart); 
		String dtend_str = sdf.format(dtend);
		
		/*
		attendees = json_decode(stripslashes(req.getVar("_attendees_value")));

		String to_str = "";
		for(int i = 0; i < count(attendees); i ++) 
		{
			if(strlen(to_str) > 0) 
			{
				to_str = to_str + ", ";
			}
			name = attendees[i]._account;
			to_str .= "{name}@blackberryhome.com.cn";
		}
		*/
		String subject = "";
		try
		{
			subject = URLDecoder.decode(req.getVar("_subject"), "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		MAGDocument doc = new MAGDocument("保存成功！");
		
		String to_str = "services@blackberryhome.com.cn";
		MAGText txt1 = new MAGText("_to", to_str);
		doc.addChild(txt1);
		
		MAGText txt2 = new MAGText("_subject", subject);
		doc.addChild(txt2);
		
		MAGText txt3 = new MAGText("_location", req.getVar("_location"));
		doc.addChild(txt3);
		
		MAGText txt4 = new MAGText("_allday", isallday);
		doc.addChild(txt4);
		
		MAGText txt5 = new MAGText("_dtstart", dtstart_str);
		doc.addChild(txt5);
		
		MAGText txt6 = new MAGText("_dtend",   dtend_str);
		doc.addChild(txt6);
		
		MAGText txt7 = new MAGText("_busystatus", req.getVar("_busystatus"));
		doc.addChild(txt7);
		
		MAGText txt8 = new MAGText("_reminder", req.getVar("_reminder"));
		doc.addChild(txt8);
		
		MAGText txt9 = new MAGText("_note", req.getVar("_note"));
		doc.addChild(txt9);
		
		MAGScript script = new MAGScript("alert('保存成功！');");
		//MAGScript script = new MAGScript("alert('保存成功！');close();");
		doc.addChild(script);
		
		req.response(doc);
		return true;
	}
}
