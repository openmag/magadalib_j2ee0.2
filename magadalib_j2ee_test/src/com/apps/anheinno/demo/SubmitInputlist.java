package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGText;

public class SubmitInputlist implements IMAGHandler
{

	public String getAction()
	{
		return "SUBMITINPUTLIST";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("");

		MAGText text = new MAGText("", req.getVar("_input_list"));
		doc.addChild(text);

		doc.addChild(new MAGText(req.getVar("_test_input"), "say hi!"));
		
		req.response(doc);
		return true;
	}

}
