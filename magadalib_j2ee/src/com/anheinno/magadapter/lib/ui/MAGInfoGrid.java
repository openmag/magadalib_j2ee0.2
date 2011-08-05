package com.anheinno.magadapter.lib.ui;

public class MAGInfoGrid extends MAGInputBase
{

	public enum MAGInfoGridFieldType
	{
		FIELD_TYPE_DATE("date"), 
		FIELD_TYPE_NUMBER("int"), 
		FIELD_TYPE_STRING("string");
		private final String _type;

		private MAGInfoGridFieldType(String type)
		{
			_type = type;
		}

		public String toString()
		{
			return _type;
		}
	}

	public MAGInfoGrid(String label, MAGInfoGridFieldSet fields, MAGInfoGridDataSet data, String[] values, String id)
	{
		super(label, id, MAGInputBase.toValueString(values));
		setAttr("_fields", fields);
		setAttr("_data", data);
	}

	public void setItemPerPage(int limit)
	{
		setAttr("_number", limit);
	}

	public void setFooter(String footer)
	{
		setAttr("_footer", footer);
	}
}