package com.anheinno.magadapter.lib.ui;

public class MAGTextinput extends MAGInputBase
{

	public enum MAGTextinputFilter implements StringEnum
	{
		TEXTINPUT_FILTER_EMAIL("_email"), 
		TEXTINPUT_FILTER_FILENAME("_filename"), 
		TEXTINPUT_FILTER_HEXDECIMAL("_hexdecimal"), 
		TEXTINPUT_FILTER_IP("_ip"), 
		TEXTINPUT_FILTER_LOWERCASE("_lowercase"), 
		TEXTINPUT_FILTER_NUMERIC("_numeric"), 
		TEXTINPUT_FILTER_PHONE("_phone"), 
		TEXTINPUT_FILTER_UPPERCASE("_uppercase"), 
		TEXTINPUT_FILTER_URL("_url"), 
		TEXTINPUT_FILTER_BASIC("_basic"), 
		TEXTINPUT_FILTER_NOTES("");

		private final String _filter;

		private MAGTextinputFilter(String filter)
		{
			_filter = filter;
		}

		public String toString()
		{
			return _filter;
		}
	}

	public MAGTextinput(String title, String id)
	{
		this(title, id, "", MAGTextinputFilter.TEXTINPUT_FILTER_BASIC);
	}

	public MAGTextinput(String title, String id, String value)
	{
		this(title, id, value, MAGTextinputFilter.TEXTINPUT_FILTER_BASIC);
	}

	public MAGTextinput(String title, String id, String value, MAGTextinputFilter filter)
	{
		super(title, id, value);
		setAttr("_filter", filter);
	}

}
