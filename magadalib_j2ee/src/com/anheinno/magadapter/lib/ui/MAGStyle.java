package com.anheinno.magadapter.lib.ui;

import org.json.lite.JSONObject;

public class MAGStyle extends JSONObject
{

	public void setStyle(String name, String val)
	{
		if (val != null && val.length() > 0)
		{
			try
			{
				put(name, val);
			}
			catch (final Exception e)
			{

			}
		}
	}

	public void setStyle(String name, int val)
	{
		setStyle(name, "" + val);
	}

	public void setStyle(String name, double val)
	{
		setStyle(name, "" + val);
	}

	public void setAlignLeft()
	{
		setStyle("align", "left");
	}

	public void setAlignCenter()
	{
		setStyle("align", "center");
	}

	public void setAlignRight()
	{
		setStyle("align", "right");
	}

	public void setVAlignTop()
	{
		setStyle("valign", "top");
	}

	public void setVAlignMiddle()
	{
		setStyle("valign", "middle");
	}

	public void setVAlignBottom()
	{
		setStyle("valign", "bottom");
	}

	public void setWidthPercentage(int width)
	{
		setStyle("width", width + "%");
	}

	public void setWidthFractorial(int dividee, int divider)
	{
		setStyle("width", dividee + "/" + divider);
	}

	public void setWidthPixals(int width)
	{
		setStyle("width", width);
	}
	
	public void setWidthRestOfScreen() {
		setStyle("width", "*");
	}

	public void setHeight(int height)
	{
		setStyle("height", height);
	}
	
	public void setHeightRestOfScreen() {
		setStyle("height", "*");
	}

	public void setIWidth(String iw)
	{
		setStyle("iwidth", iw);
	}

	public void setIHeight(String ih)
	{
		setStyle("iheight", ih);
	}

	public void setBorder(int w, String clr)
	{
		setBorder(w, clr, null);
	}

	private void setBorder(int w, String clr, String dir)
	{
		if (w > 0)
		{
			String k = "border-width";
			if (dir != null && dir.length() > 0)
			{
				k += "-" + dir;
			}
			setStyle(k, w);
		}
		if (clr != null && clr.length() > 0)
		{
			String k = "border-color";
			if (dir != null && dir.length() > 0)
			{
				k += "-" + dir;
			}
			setStyle(k, clr);
		}
	}

	public void setBorderLeft(int w, String clr)
	{
		setBorder(w, clr, "left");
	}

	public void setBorderTop(int w, String clr)
	{
		setBorder(w, clr, "top");
	}

	public void setBorderRight(int w, String clr)
	{
		setBorder(w, clr, "right");
	}

	public void setBorderBottom(int w, String clr)
	{
		setBorder(w, clr, "bottom");
	}

	public void setFocusBorderColor(String clr)
	{
		setFocusBorderColor(clr, null);
	}

	private void setFocusBorderColor(String clr, String dir)
	{
		String k = "focus-border-color";
		if (dir != null && dir.length() > 0)
		{
			k += '-' + dir;
		}
		setStyle(k, clr);
	}

	public void setFocusBorderColorTop(String clr)
	{
		setFocusBorderColor(clr, "top");
	}

	public void setFocusBorderColorLeft(String clr)
	{
		setFocusBorderColor(clr, "left");
	}

	public void setFocusBorderColorRight(String clr)
	{
		setFocusBorderColor(clr, "right");
	}

	public void setFocusBorderColorBottom(String clr)
	{
		setFocusBorderColor(clr, "bottom");
	}

	public void setPadding(int w)
	{
		setPadding(w, null);
	}

	private void setPadding(int w, String dir)
	{
		String k = "padding";
		if (dir != null && dir.length() > 0)
		{
			k += "-" + dir;
		}
		if (w > 0)
		{
			setStyle(k, w);
		}
	}

	public void setPaddingLeft(int w)
	{
		setPadding(w, "left");
	}

	public void setPaddingTop(int w)
	{
		setPadding(w, "top");
	}

	public void setPaddingRight(int w)
	{
		setPadding(w, "right");
	}

	public void setPaddingBottom(int w)
	{
		setPadding(w, "bottom");
	}

	public void setBackground(String val)
	{
		setStyle("background", val);
	}

	public void setFocusBackground(String val)
	{
		setStyle("focus-background", val);
	}

	public void setBgcolor(String val)
	{
		setStyle("bgcolor", val);
	}

	public void setColor(String val)
	{
		setStyle("color", val);
	}

	public void setFontBold()
	{
		setStyle("font-weight", "bold");
	}

	public void setFontItalic()
	{
		setStyle("font-style", "italic");
	}

	public void setFontUnderline()
	{
		setStyle("text-decoration", "underline");
	}
}
