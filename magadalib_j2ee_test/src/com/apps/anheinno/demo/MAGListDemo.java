package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGCombo;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGInputList;
import com.anheinno.magadapter.lib.ui.MAGLink;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;
import com.anheinno.magadapter.lib.ui.MAGLinkableComponent.MAGLinkTarget;
import com.anheinno.magadapter.lib.ui.MAGList;
import com.anheinno.magadapter.lib.ui.MAGList.MAGListOrderFieldSet;
import com.anheinno.magadapter.lib.ui.MAGList.MAGListOrderFieldType;
import com.anheinno.magadapter.lib.ui.MAGNote;
import com.anheinno.magadapter.lib.ui.MAGPanel;
import com.anheinno.magadapter.lib.ui.MAGStyle;
import com.anheinno.magadapter.lib.ui.MAGSubmit;
import com.anheinno.magadapter.lib.ui.MAGText;

public class MAGListDemo implements IMAGHandler
{

	public String getAction()
	{
		return "MAGLISTEXAMPLE";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("MAGList Example");

		MAGStyle combo_style = new MAGStyle();
		combo_style.setPadding(10);
		combo_style.setBorderBottom(1, "#999999");
		combo_style.setBackground("color=white");
		combo_style.setFocusBackground("color=#000099");
		doc.addClass("combo_style", combo_style);

		MAGStyle link_style = new MAGStyle();
		link_style.setWidthPercentage(70);
		link_style.setAlignLeft();
		link_style.setStyle("text-style", "color=black");
		link_style.setStyle("focus-text-style", "color=blue text-decoration=underline");
		doc.addClass("link_style", link_style);

		MAGStyle text_style = new MAGStyle();
		text_style.setWidthPercentage(30);
		text_style.setStyle("focus", "none");
		text_style.setStyle("title-text-style", "font-scale=0.7 color=#999999");
		text_style.setStyle("text-style", "font-scale=0.8 font-weight=bold color=green");
		text_style.setAlignRight();
		doc.addClass("text_style", text_style);

		MAGList list = new MAGList("MAGList例子");

		MAGStyle list_style = new MAGStyle();
		list_style.setStyle("footer-text-style", "color=black font-scale=0.7");
		list.setStyle(list_style);

		String[][] data = {
			{"水木社区", "http://www.newsmth.net", "1000", "水木", "2008-05-14"},
			{"京东商城", "http://www.360buy.com", "5000", "京东", "2009-04-14"},
			{"北京缓解拥堵网", "http://www.bjhjyd.gov.cn", "10", "北京市政府", "2010-12-14"},
			{"百度贴吧", "http://tieba.baidu.com", "8000", "白度", "2009-05-14"},
			{"新浪新闻", "http://news.sina.com.cn", "2000", "新浪", "2010-06-23"},
			{"新浪汽车", "http://audo.sina.com.cn", "1200", "新浪", "2011-06-21"},
			{"新浪房产", "http://house.sina.com.cn", "1800", "新浪", "2011-06-23"},
			{"新浪科技", "http://tech.sina.com.cn", "1400", "新浪", "2011-05-23"},
			{"新浪财经", "http://finance.sina.com.cn", "1300", "新浪", "2011-04-23"},
			{"新浪读书", "http://book.sina.com.cn", "800", "新浪", "2010-04-23"},
			{"百度新闻", "http://news.baidu.com", "6000", "白度", "2011-07-08"},
			{"百度知道", "http://zhidao.baidu.com", "4500", "白度", "2011-01-11"},
			{"百度MP3", "http://mp3.baidu.com", "7700", "白度", "2011-03-28"},
			{"百度图片", "http://image.baidu.com", "9800", "白度", "2011-02-09"},
			{"百度视频", "http://video.baidu.com", "2300", "白度", "2011-02-11"},
			{"百度地图", "http://map.baidu.com", "2200", "百度", "2011-03-31"},
		};

		for (String[] d:data) 
		{
			MAGCombo combo = new MAGCombo("");
			combo.setClass("combo_style");
			MAGLink link = new MAGLink(d[0], new MAGLinkURL(d[1]), 0, MAGLinkTarget.LINK_TARGET_BROWSER, "link");
			link.setClass("link_style");
			combo.addChild(link);

			MAGText text = new MAGText("访问量：", d[2], "text");
			text.setClass("text_style");
			combo.addChild(text);

			MAGNote note = new MAGNote(d[3], d[4], "note");
			combo.addChild(note);
			list.addChild(combo);
		}
		
		MAGListOrderFieldSet order_fields = list.new MAGListOrderFieldSet();
		order_fields.addField("链接文字", "link._title", MAGListOrderFieldType.FIELD_TYPE_STRING);
		order_fields.addField("访问量", "text._text", MAGListOrderFieldType.FIELD_TYPE_NUMBER);
		order_fields.addField("类别", "note._title", MAGListOrderFieldType.FIELD_TYPE_STRING);
		order_fields.addField("日期", "note._note", MAGListOrderFieldType.FIELD_TYPE_STRING);	

		list.setOrderBy(order_fields);
		list.setItemsPerPage(3);
		
		int items_per_page = 3;
		double pages = (double)data.length/items_per_page;
		if(pages != (int)(pages)) 
		{
			pages = (int)(pages)+1;
		}
		list.setFooter("共" + (int)pages + "页");
		list.setDescending(true);
		doc.addChild(list);
		
		
		MAGPanel panel = new MAGPanel("MAGInputList示例");
		
		MAGInputList input_list = new MAGInputList("网站列表", "_input_list", null);
		input_list.setStyle(list_style);

		for (int i = 0; i<(data.length-1); i++) 
		{
			MAGCombo combo = new MAGCombo("item_"+i);
			combo.setClass("combo_style");
			MAGLink link = new MAGLink(data[i][0], new MAGLinkURL(data[i][1]), 0, MAGLinkTarget.LINK_TARGET_BROWSER, "link");
			link.setClass("link_style");
			combo.addChild(link);

			MAGText text = new MAGText("访问量：", data[i][2], "text");
			text.setClass("text_style");
			combo.addChild(text);

			MAGNote note = new MAGNote(data[i][3], data[i][4], "note");
			combo.addChild(note);
			
			input_list.addChild(combo);
		}
		
		input_list.setOrderBy(order_fields);
		input_list.setDescending(true);
		
		input_list.setItemsPerPage(items_per_page);
		input_list.setFooter("共" + (int)pages + "页");
		input_list.setDescending(true);
		panel.addChild(input_list);

		
		MAGSubmit submit = new MAGSubmit("提交", "SUBMITINPUTLIST", new MAGLinkURL(), MAGLinkTarget.LINK_TARGET_NEW);
		MAGStyle submit_style = new MAGStyle();
		submit_style.setAlignCenter();
		submit.setStyle(submit_style);
		panel.addChild(submit);
		
		
		doc.addChild(panel);
		
		req.response(doc);
		return true;
	}

}
