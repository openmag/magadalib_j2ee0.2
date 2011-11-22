package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGRequest;
import com.anheinno.magadapter.lib.ui.MAGDocument;
import com.anheinno.magadapter.lib.ui.MAGInfoGrid;
import com.anheinno.magadapter.lib.ui.MAGInfoGrid.MAGInfoGridFieldType;
import com.anheinno.magadapter.lib.ui.MAGInfoGridData;
import com.anheinno.magadapter.lib.ui.MAGInfoGridDataSet;
import com.anheinno.magadapter.lib.ui.MAGInfoGridFieldSet;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;
import com.anheinno.magadapter.lib.ui.MAGLinkTarget;


public class MAGInfoGridDemo implements IMAGHandler
{

	private static final int DEFAULT_EXPIRE = 7*24;

	public String getAction()
	{
		return "INFOGRIDDEMO";
	}

	public boolean process(MAGRequest req)
	{
		MAGDocument doc = new MAGDocument("申请表单("+req.getUsername()+")");

		MAGInfoGridFieldSet fields = new MAGInfoGridFieldSet();
		fields.addField("单号", MAGInfoGridFieldType.FIELD_TYPE_STRING);
		fields.addField("处理人", MAGInfoGridFieldType.FIELD_TYPE_STRING);
		fields.addField("日期", MAGInfoGridFieldType.FIELD_TYPE_DATE);
		fields.addField("价格", MAGInfoGridFieldType.FIELD_TYPE_NUMBER);
		fields.addField("描述", MAGInfoGridFieldType.FIELD_TYPE_STRING);
		
		
		MAGInfoGridDataSet dataset = new MAGInfoGridDataSet();
		MAGInfoGridData data = new MAGInfoGridData("李四申请购买打印机", new String[] {"SQ001", "李四", "2010-10-08 17:00:00", "2300", "购买打印机"}, (new MAGLinkURL().setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE)), MAGLinkTarget.LINK_TARGET_NEW, "SQ001");
		data.setHint("李四申请购买打印机\n2010-10-08 17:00:00\n2300元");
		dataset.addData(data);
		
		MAGInfoGridData data1 = new MAGInfoGridData("张五申请购买电脑", new String[] {"SQ005", "张五", "2010-10-01 16:00:00", "4200", "购买电脑"}, (new MAGLinkURL().setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE)), MAGLinkTarget.LINK_TARGET_NEW, "SQ001");
		data1.setHint("张五申请购买电脑\n2010-10-01 16:00:00\n4200元");
		dataset.addData(data1);
		
		MAGInfoGridData data2 = new MAGInfoGridData("张三想申请购买电脑", new String[] {"SQ005", "张三", "2010-10-01 16:00:00", "4200", "购买电脑"}, (new MAGLinkURL().setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE)), MAGLinkTarget.LINK_TARGET_NEW, "SQ005");
		data2.setHint("张三申请购买电脑\n2010-10-01 16:00:00\n4200元");
		dataset.addData(data2);
		
		MAGInfoGridData data3 = new MAGInfoGridData("王五申请购买桌子", new String[] {"SQ002", "王五", "2010-10-06 12:00:00", "100", "购买桌子"}, (new MAGLinkURL().setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE)), MAGLinkTarget.LINK_TARGET_NEW, "SQ002");
		data3.setHint("王五申请购买桌子\n2010-10-06 12:00:00\n100元");
		dataset.addData(data3);
		
		MAGInfoGridData data4 = new MAGInfoGridData("李1申请购买椅子, 这是长字符创测试，测试是否会换行活着省略", new String[] {"SQ009", "李先生", "2010-09-08 17:00:00", "23", "购买椅子"}, (new MAGLinkURL().setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE)), MAGLinkTarget.LINK_TARGET_NEW, "SQ009");
		data4.setHint("李先生申请购买椅子\n2010-09-08 17:00:00\n23元");
		dataset.addData(data4);

		MAGInfoGridData data5 = new MAGInfoGridData("李1申请购买椅子, 这是长字符创测试，测试是否会换行活着省略", new String[] {"SQ009", "李先生", "2010-09-08 17:00:00", "23", "购买椅子"}, (new MAGLinkURL().setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE)), MAGLinkTarget.LINK_TARGET_NEW, "SQ009");
		data5.setHint("李先生申请购买椅子\n2010-09-08 17:00:00\n23元");
		dataset.addData(data5);

		MAGInfoGridData data6 = new MAGInfoGridData("李1申请购买椅子, 这是长字符创测试，测试是否会换行活着省略", new String[] {"SQ009", "李先生", "2010-09-08 17:00:00", "23", "购买椅子"}, (new MAGLinkURL().setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE)), MAGLinkTarget.LINK_TARGET_NEW, "SQ009");
		data6.setHint("李先生申请购买椅子\n2010-09-08 17:00:00\n23元");
		dataset.addData(data6);
		
		MAGInfoGridData data7 = new MAGInfoGridData("李1申请购买椅子, 这是长字符创测试，测试是否会换行活着省略", new String[] {"SQ009", "李先生", "2010-09-08 17:00:00", "23", "购买椅子"}, (new MAGLinkURL().setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE)), MAGLinkTarget.LINK_TARGET_NEW, "SQ009");
		data7.setHint("李先生申请购买椅子\n2010-09-08 17:00:00\n23元");
		dataset.addData(data7);
		
		MAGInfoGridData data8 = new MAGInfoGridData("李1申请购买椅子, 这是长字符创测试，测试是否会换行活着省略", new String[] {"SQ009", "李先生", "2010-09-08 17:00:00", "23", "购买椅子"}, (new MAGLinkURL().setHandler("QUOTATION").setExpireHours(DEFAULT_EXPIRE)), MAGLinkTarget.LINK_TARGET_NEW, "SQ009");
		data8.setHint("李先生申请购买椅子\n2010-09-08 17:00:00\n23元");
		dataset.addData(data8);
		
		MAGInfoGrid grid = new MAGInfoGrid("待审批表单", fields, dataset, null, "infogrid");
		grid.setFooter("共3页");
		grid.setItemPerPage(4);
		grid.setStatus("按\"#Q\"键显示详细信息。");
		doc.addChild(grid);
		req.response(doc);
		return true;
	}

}
