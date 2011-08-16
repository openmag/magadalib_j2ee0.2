package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGTieredOptionsSet;
import com.anheinno.magadapter.lib.ui.MAGTieredSelect;
import com.anheinno.magadapter.lib.ui.MAGTieredSuboptionsSet;

public class MAGTieredSelectDemo implements IMAGHandler
{

	public String getAction()
	{
		return "MAGTIEREDSELECTDEMO";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("MAGTieredSelectDemo");
		
		MAGTieredSuboptionsSet suboption1 = new MAGTieredSuboptionsSet("产品部成员：");
		suboption1.addOption("张三", "zhangsan");
		suboption1.addOption("李四", "lisi");
		suboption1.addOption("王二麻子", "wangermz");
		suboption1.addOption("小淘气", "xiaotq");
		
		MAGTieredSuboptionsSet suboption2 = new MAGTieredSuboptionsSet("商务部成员：");
		suboption2.addOption("张飞", "zhangfei");
		suboption2.addOption("刘备", "liubei");
		suboption2.addOption("关羽", "guanyu");
		
		MAGTieredOptionsSet optionsetSet = new MAGTieredOptionsSet();
		optionsetSet.addOption("产品部", "products", suboption1);
		optionsetSet.addOption("商务部", "commerce", suboption2);
		
		MAGTieredSelect tieredSelect = new MAGTieredSelect("部门架构:", optionsetSet, null, "_tiered_id");
		
		doc.addChild(tieredSelect);
		
		req.response(doc);
		return true;
	}

}
