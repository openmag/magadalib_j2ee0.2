package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGPushClient;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGText;

public class SubmitResult implements IMAGHandler
{
	public String getAction()
	{
		return "SUBMITRESULT";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("Submit result");
		
		MAGPushClient.setLocalVar(req, "test_local_var", req.getVar("_textinput"));

		doc.addChild(new MAGText("_textinput", req.getVar("_textinput")));
		doc.addChild(new MAGText("_numberinput", req.getVar("_numberinput")));
		doc.addChild(new MAGText("_password", req.getVar("_password")));
		doc.addChild(new MAGText("_date", req.getVar("_date")));
		doc.addChild(new MAGText("_time", req.getVar("_time")));
		doc.addChild(new MAGText("_datetime", req.getVar("_datetime")));
		doc.addChild(new MAGText("_select", req.getVar("_select")));
		doc.addChild(new MAGText("_mselect", req.getVar("_mselect")));
		doc.addChild(new MAGText("_hidden1", req.getVar("_hidden1")));
		doc.addChild(new MAGText("_hidden2", req.getVar("_hidden2")));
		doc.addChild(new MAGText("_hidden3", req.getVar("_hidden3")));
		doc.addChild(new MAGText("_combo", req.getVar("_combo")));
		doc.addChild(new MAGText("_duplicator", req.getVar("_duplicator")));
		if (req.getVar("radio_group") != null)
		{
			doc.addChild(new MAGText("radio_group", req.getVar("radio_group")));
		}

		req.response(doc);
		return true;
	}

}
