package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGKeywordFilterSelect;
import com.anheinno.magadapter.lib.ui.MAGKeywordOptionsSet;
import com.anheinno.magadapter.lib.ui.MAGKeywordValueSet;

public class MAGKeywordFilterSelectDemo implements IMAGHandler
{

	public String getAction()
	{
		return "MAGKEYWORDFILTERSELECTDEMO";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("MAGKeywordFilterSelect Demo");
		
		
		MAGKeywordOptionsSet options = new MAGKeywordOptionsSet();
		options.addOption("黑龙江省", "heilongjiang", new String[]{"hlj", "HLJ", "heilongjiang", "HEILONGJIANG"});
		options.addOption("吉林省", "jilin", new String[]{"jl", "JL", "jilin", "JILIN"});
		options.addOption("辽宁省", "liaoning", new String[]{"ln", "LN", "liaoning", "LIAONING"});
		options.addOption("内蒙古自治区", "neimenggu", new String[]{"nmg", "NMG", "neimenggu", "NEIMENGGU"});
		options.addOption("河北省", "hebei", new String[]{"hb", "HB", "hebei", "HEBEI"});
		options.addOption("河南省", "henan", new String[]{"hn", "HN", "henan", "HENAN"});
		
		MAGKeywordValueSet keywords = new MAGKeywordValueSet();
		keywords.addValue("", ""	);
//		keywords.addValue("黑龙江省", "heilongjiang"	);
		
		MAGKeywordFilterSelect keywordfilterselect = new MAGKeywordFilterSelect("请选择省份：", options, keywords, "_province_id");
		keywordfilterselect.setMultichoice(false);
		doc.addChild(keywordfilterselect);
		
		req.response(doc);
		return true;
	}

}
