package com.anheinno.magadapter.lib.ui;

public class MAGDate extends MAGInputBase
{

	public enum MAGDateUI
	{
		MAGDATE_UI_DATE("__date_"), MAGDATE_UI_TIME("__time_"), MAGDATE_UI_DATETIME("__datetime_");

		private final String _ui;

		private MAGDateUI(String ui)
		{
			_ui = ui;
		}

		public String toString()
		{
			return _ui;
		}
	}

	public MAGDate(String title, String id)
	{
		this(title, id, 0, MAGDateUI.MAGDATE_UI_DATETIME);
	}

	public MAGDate(String title, String id, long date)
	{
		this(title, id, date, MAGDateUI.MAGDATE_UI_DATETIME);
	}

	public MAGDate(String title, String id, long date, MAGDateUI ui)
	{
		super(title, id, (date > 0) ? String.valueOf(date) : null);
		setAttr("_ui", ui.toString());
	}

}
