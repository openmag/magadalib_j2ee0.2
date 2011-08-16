package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGPanel;
import com.anheinno.magadapter.lib.ui.MAGStyle;
import com.anheinno.magadapter.lib.ui.MAGText;

public class Quotation implements IMAGHandler
{

	public String getAction()
	{
		return "QUOTATION";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("BlackBerry Quotation");

		MAGPanel panel = new MAGPanel("");

		MAGStyle panel_style = new MAGStyle();
		panel_style.setBgcolor("#DDDDDD");
		panel_style.setBorder(20, "#000000");
		panel_style.setPadding(10);
		panel.setStyle(panel_style);

		MAGStyle style_header = new MAGStyle();
		style_header.setAlignCenter();
		style_header.setBackground("#333333 #666666");
		style_header.setStyle("title-text-style", "text-align=center color=white font-weight=bold");
		style_header.setStyle("title-width", "100%");
		style_header.setBorder(1, "#000000");
		doc.addClass("style_header", style_header);

		MAGStyle style_header1 = new MAGStyle();
		style_header1.setWidthPercentage(31);
		MAGText head1 = new MAGText("Carrier", "");
		head1.setStyle(style_header1);
		head1.setClass("style_header");
		panel.addChild(head1);

		MAGStyle style_header2 = new MAGStyle();
		style_header2.setWidthPercentage(23);
		MAGText head2 = new MAGText("Model", "");
		head2.setStyle(style_header2);
		head2.setClass("style_header");
		panel.addChild(head2);

		MAGStyle style_header3 = new MAGStyle();
		style_header3.setWidthPercentage(23);
		MAGText head3 = new MAGText("Price", "");
		head3.setStyle(style_header3);
		head3.setClass("style_header");
		panel.addChild(head3);

		MAGStyle style_header4 = new MAGStyle();
		style_header4.setWidthPercentage(23);
		MAGText head4 = new MAGText("Retail", "");
		head4.setStyle(style_header4);
		head4.setClass("style_header");
		panel.addChild(head4);

		String[][] valStrings = {
				{"Original", "9700", "5900", "5600"}, 
				{"Original", "9000", "4900", "4600"},
				{"Original", "8900", "4000", "3700"}, 
				{"Carrier", "9000", "4500", "4200"},
				{"Carrier", "8900", "3500", "3200"}, 
				{"Carrier", "9500", "3600", "3300"},
				{"Carrier", "8320", "2800", "2500"}, 
				{"Carrier", "8310", "2400", "2100"},
				{"Carrier", "8800", "2000", "1700"}, 
				{"Original", "9700", "5900", "5600"},
				{"Original", "9000", "4900", "4600"}, 
				{"Original", "8900", "4000", "3700"},
				{"Carrier", "9000", "4500", "4200"}, 
				{"Carrier", "8900", "3500", "3200"},
				{"Carrier", "9500", "3600", "3300"}, 
				{"Carrier", "8320", "2800", "2500"},
				{"Carrier", "8310", "2400", "2100"}, 
				{"Carrier", "8800", "2000", "1700"},
				{"Original", "9700", "5900", "5600"}, 
				{"Original", "9000", "4900", "4600"},
				{"Original", "8900", "4000", "3700"}, 
				{"Carrier", "9000", "4500", "4200"},
				{"Carrier", "8900", "3500", "3200"}, 
				{"Carrier", "9500", "3600", "3300"},
				{"Carrier", "8320", "2800", "2500"}, 
				{"Carrier", "8310", "2400", "2100"},
				{"Carrier", "8800", "2000", "1700"}, 
				{"Original", "9700", "5900", "5600"},
				{"Original", "9000", "4900", "4600"}, 
				{"Original", "8900", "4000", "3700"},
				{"Carrier", "9000", "4500", "4200"}, 
				{"Carrier", "8900", "3500", "3200"},
				{"Carrier", "9500", "3600", "3300"}, 
				{"Carrier", "8320", "2800", "2500"},
				{"Carrier", "8310", "2400", "2100"}, 
				{"Carrier", "8800", "2000", "1700"},
				{"Original", "9700", "5900", "5600"}, 
				{"Original", "9000", "4900", "4600"},
				{"Original", "8900", "4000", "3700"}, 
				{"Carrier", "9000", "4500", "4200"},
				{"Carrier", "8900", "3500", "3200"}, 
				{"Carrier", "9500", "3600", "3300"},
				{"Carrier", "8320", "2800", "2500"}, 
				{"Carrier", "8310", "2400", "2100"},
				{"Carrier", "8800", "2000", "1700"}, 
				{"Carrier", "8700", "1300", "1000"}};

		String[] width = {"31", "23", "23", "23"};

		MAGStyle style = new MAGStyle();
		style.setBorderBottom(1, "#000000");
		style.setStyle("focus", "normal");
		doc.addClass("item_style", style);

		
		MAGStyle style_item = new MAGStyle();
		style_item.setStyle("text-style", "color=red");
		for (int i=0; i<valStrings.length; i++)
		{
			MAGText text = null;
			for (int j = 0; j < valStrings[i].length; j++)
			{
				if (j <= 1)
				{
					text = new MAGText(valStrings[i][j], "");
				}
				else
				{
					text = new MAGText("", valStrings[i][j]);
				}
				MAGStyle width_style = new MAGStyle();
				width_style.setWidthPercentage(Integer.parseInt(width[j]));
				text.setStyle(width_style);
				text.setClass("item_style");
				panel.addChild(text);
			} 
		}		
		
		doc.addChild(panel);

		req.response(doc);
		return true;
	}

}
