package com.anheinno.magadapter.lib.ui;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Vector;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

import com.anheinno.magadapter.lib.MAGConfig;

public class MAGLinkURL
{
	private URI _uri;
	private String _handler;
	private Vector<KeyValuePair> _params;
	private JSONArray _class_params;
	private long _expire;
	private boolean _notify;
	private boolean _save_history;

	private class KeyValuePair
	{
		String _key;
		String _value;

		private KeyValuePair(String key, String value)
		{
			_key = key;
			_value = value;
		}
	}

	public MAGLinkURL()
	{
		_uri = null;
		_handler = null;
		_params = null;
		_expire = MAGConfig.getDefaultExpireMilliseconds();
		_notify = true;
		_save_history = true;
	}

	public MAGLinkURL(String url)
	{
		setURL(url);
		_handler = null;
		_params = null;
		_expire = MAGConfig.getDefaultExpireMilliseconds();
		_notify = true;
		_save_history = true;
	}
	
	public MAGLinkURL setURL(String url) {
		try
		{
			_uri = new URI(url);
		}
		catch (final URISyntaxException e)
		{
			_uri = null;
		}
		return this;
	}

	public MAGLinkURL setHandler(String handler)
	{
		_handler = handler;
		return this;
	}

	public MAGLinkURL addParam(String key, String value)
	{
		if (_params == null)
		{
			_params = new Vector<KeyValuePair>();
		}
		_params.addElement(new KeyValuePair(key, value));
		return this;
	}

	public MAGLinkURL setClass(String classname)
	{
		_handler = classname;
		return this;
	}

	public MAGLinkURL addParam(String param)
	{
		if (_class_params == null)
		{
			_class_params = new JSONArray();
		}
		_class_params.put(param);
		return this;
	}

	public MAGLinkURL setScripts(String scripts)
	{
		_handler = scripts;
		return this;
	}

	protected JSONObject getClassParams()
	{
		try
		{
			JSONObject obj = new JSONObject();
			obj.put("class", _handler);
			obj.put("params", _class_params);
			return obj;
		}
		catch (final Exception e)
		{

		}
		return null;
	}

	protected String getScripts()
	{
		return _handler;
	}
	
	public MAGLinkURL setExpireHours(int hours) {
		_expire = hours*3600*1000L;
		return this;
	}
	
	public MAGLinkURL setNotify(boolean notify) {
		_notify = notify;
		return this;
	}
	
	public MAGLinkURL setSaveHistory(boolean save_history) {
		_save_history = save_history;
		return this;
	}
	
	public boolean isNotify() {
		return _notify;
	}
	
	public boolean isSaveHistory() {
		return _save_history;
	}
	
	public long getExpireMilliseconds() {
		return _expire;
	}

	public String getURL()
	{
		StringBuffer query = new StringBuffer();
		
		if (_handler != null)
		{
			try
			{
				String tmp = URLEncoder.encode(_handler, "UTF-8");
				query.append("_action=");
				query.append(tmp);
			}
			catch (final Exception e)
			{

			}
		}
		if (_params != null && _params.size() > 0)
		{
			for (KeyValuePair h : _params)
			{
				try
				{
					String k = URLEncoder.encode(h._key, "UTF-8");
					String v = URLEncoder.encode(h._value, "UTF-8");
					if (query.length() > 0)
					{
						query.append('&');
					}
					query.append(k);
					query.append('=');
					query.append(v);
				}
				catch (final Exception e)
				{

				}
			}
		}

		String url = "";
		if (_uri != null)
		{
			url += _uri.toString();
		}
		if (query.length() > 0)
		{
			if (_uri != null && _uri.getQuery() != null && _uri.getQuery().length() > 0)
			{
				return url + '&' + query.toString();
			}
			else
			{
				return url + '?' + query.toString();
			}
		}
		else
		{
			return url;
		}
	}
	
	public JSONObject toJSONObject()
    {
		try {
	        JSONObject obj = new JSONObject();
	        obj.put("_link", getURL());
	        obj.put("_expire", getExpireMilliseconds());
	        obj.put("_notify", (isNotify() ? "true" : "false"));
	        obj.put("_save", (isSaveHistory() ? "true" : "false"));
	        return obj;
		}catch(final Exception e) {
			
		}
		return null;
    }
}
