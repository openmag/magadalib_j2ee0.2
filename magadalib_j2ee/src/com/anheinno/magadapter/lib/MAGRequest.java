package com.anheinno.magadapter.lib;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.lite.JSONObject;

import com.anheinno.magadapter.lib.util.SortedVector;

public class MAGRequest
{
	private String __handle = null;
	private String __msg = null;
	private byte[] __binary_data = null;
	private String __pin = null;
	private String __imsi = null;
	private String __device = null;
	private long __expire = 0L;
	private String __module = null;
	private String __push_server = null;
	private String __push_protocol = null;
	private String __soft_ver = null;
	private String __platform_ver = null;
	private String __username = null;
	private String __password = null;
	private String __content_type = null;
	private Hashtable<String, String> __headers = null;
	private boolean __gzip = false;
	private String __ua = null;

	private HttpServletRequest _request = null;

	private String getHeader(String val)
	{
		return _request.getHeader(val);
	}

	private String[] getParam(String key)
	{
		Map<String, String[]> params = _request.getParameterMap();
		if (params.containsKey(key))
		{
			return params.get(key);
		}
		else
		{
			return null;
		}
	}

	public MAGRequest(HttpServletRequest req)
	{
		_request = req;
		__msg = "";

		retrieveInfo();
	}

	private void retrieveInfo()
	{

		String info = getHeader("X-Anhe-Handheld-INFO");
		if (info != null && info.length() > 0)
		{
			String[] vals = info.split(";");
			__pin = vals[0].trim();
			__device = vals[1].trim();
			__platform_ver = vals[2].trim();
			if (vals.length > 3)
			{
				__soft_ver = vals[3].trim();
			}
			if (vals.length > 4)
			{
				__module = vals[4].trim();
			}
			if (vals.length > 5)
			{
				__imsi = vals[5].trim();
			}
		}

		info = getHeader("X-Anhe-Link-Expire");
		if (info != null && info.length() > 0)
		{
			__expire = Long.parseLong(info);
		}

		info = getHeader("X-Anhe-Account-Username");
		if (info != null && info.length() > 0)
		{
			__username = info.trim();
		}
		if (__username == null || __username.length() == 0)
		{
			String[] tmp = getParam("__username");
			if (tmp != null && tmp[0].length() > 0)
			{
				__username = tmp[0].trim();
			}
		}

		info = getHeader("X-Anhe-Account-Password");
		if (info != null && info.length() > 0)
		{
			__password = info;
		}
		if (__password == null || __password.length() == 0)
		{
			String[] tmp = getParam("__password");
			if (tmp != null && tmp[0].length() > 0)
			{
				__password = tmp[0];
			}
		}

		if (__module == null || __module.length() == 0)
		{
			info = getHeader("X-Anhe-MAG-Module");
			if (info != null && info.length() > 0)
			{
				__module = info.trim();
			}
			if (__module == null || __module.length() == 0)
			{
				__module = "magoa";
			}
		}

		info = getHeader("X-Anhe-Push-Protocol");
		if (info != null && info.length() > 0)
		{
			__push_protocol = info.trim();
		}
		if (__push_protocol == null || __push_protocol.length() == 0)
		{
			__push_protocol = "MDS";
		}

		info = getHeader("X-Anhe-Push-Server");
		if (info != null && info.length() > 0)
		{
			__push_server = info.trim();
		}
		if (__push_server == null || __push_server.length() == 0)
		{
			info = _request.getRemoteAddr();
			if (info != null && info.length() > 0)
			{
				__push_server = info.trim();
				if (__push_server.startsWith("::ffff:"))
				{
					__push_server = __push_server.substring(0, __push_server.lastIndexOf(':'));
				}
			}
		}

		info = getHeader("X-Anhe-User-Agent");
		if (info != null && info.length() > 0)
		{
			__ua = info.trim();
		}

		String[] tmp = getParam("_action");
		if (tmp != null && tmp[0].length() > 0)
		{
			__handle = tmp[0].trim();
		}

		MAGLog.log(this, "_url: " + getURL() + " _action: " + __handle + " _pin: " + __pin + " _expire: " + __expire
				+ " _user: " + __username);
	}

	class KeyValuePair
	{
		String _key;
		String _value;

		KeyValuePair(String key, String value)
		{
			_key = key;
			_value = value;
		}

		void appendQueryString(StringBuffer buf)
		{
			try
			{
				String key = URLEncoder.encode(_key, "UTF-8");
				String val = URLEncoder.encode(_value, "UTF-8");
				if (buf.length() > 0)
				{
					buf.append('&');
				}
				buf.append(key);
				buf.append('=');
				buf.append(val);
			}
			catch (final Exception e)
			{
			}
		}
	}

	@SuppressWarnings("serial")
	class KeyValuePairSortedVector extends SortedVector<KeyValuePair>
	{
		protected int compare(KeyValuePair o1, KeyValuePair o2)
		{
			int result = o1._key.compareTo(o2._key);
			if (result != 0)
			{
				return result;
			}
			result = o1._value.compareTo(o2._value);
			return result;
		}
	}

	private String getQueryString()
	{
		StringBuffer buf = new StringBuffer();
		Map<String, String[]> params = _request.getParameterMap();
		KeyValuePairSortedVector list = new KeyValuePairSortedVector();
		for (String key : params.keySet())
		{
			String[] vals = params.get(key);
			for (String val : vals)
			{
				list.addSorted(new KeyValuePair(key, val));
			}
		}
		for (KeyValuePair pair : list)
		{
			pair.appendQueryString(buf);
		}
		return buf.toString();
	}

	private String retrieveURL(boolean include_query)
	{
		StringBuffer url = _request.getRequestURL();
		if (include_query)
		{
			String querystr = getQueryString();
			if (querystr != null && querystr.length() > 0)
			{
				url.append("?");
				url.append(querystr);
			}
		}
		return url.toString();
	}

	public boolean isCacheable()
	{
		if (__expire > 0 && ((__pin != null && __pin.length() > 0) || (__imsi != null && __imsi.length() > 0)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void setContentType(String type)
	{
		__content_type = type;
	}

	public void setResponseHeader(String name, String val)
	{
		if (name.toUpperCase().equals("CONTENT-TYPE"))
		{
			setContentType(val);
		}
		else if (name.toUpperCase().equals("CONTENT-length"))
		{
			// do nothing
		}
		else
		{
			if (__headers == null)
			{
				__headers = new Hashtable<String, String>();
			}
			__headers.put(name, val);
		}
	}

	public String getHandle()
	{
		return __handle;
	}

	public String getResponse()
	{
		if (__msg == null)
		{
			return "No message specified!";
		}
		else
		{
			return __msg;
		}
	}

	public byte[] getResponseBinary()
	{
		return __binary_data;
	}

	public void redirect(String url)
	{
		try
		{
			JSONObject ret = new JSONObject();
			ret.put("_redirect", url);
			response(ret);
		}
		catch (final Exception e)
		{

		}
	}

	public void resultOK()
	{
		try
		{
			JSONObject ret = new JSONObject();
			ret.put("_result", "OK");
			response(ret);
		}
		catch (final Exception e)
		{

		}
	}

	public void response(JSONObject json)
	{
		response(json.toString());
	}

	public void response(String msg)
	{
		__msg = msg;
		MAGLog.log(this, msg);
	}

	public void responseBinary(byte[] data)
	{
		__binary_data = data;
	}

	public boolean isBinary()
	{
		if (__binary_data != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void error(String msg)
	{
		try
		{
			JSONObject ret = new JSONObject();
			ret.put("_msg", msg);
			response(ret);
		}
		catch (final Exception e)
		{
		}
	}

	public String getPIN()
	{
		return __pin;
	}

	public String getIMSI()
	{
		return __imsi;
	}

	public String getModule()
	{
		return __module;
	}

	public String getSoftwareVersion()
	{
		return __soft_ver;
	}

	public String getPlatformVersion()
	{
		return __platform_ver;
	}

	public String getDevice()
	{
		return __device;
	}

	public String getPushServer()
	{
		return __push_server;
	}

	public String getPushProtocol()
	{
		return __push_protocol;
	}

	public long getExpire()
	{
		return __expire;
	}

	public String getURL()
	{
		return retrieveURL(true);
	}

	public String getScriptURI()
	{
		return retrieveURL(false);
	}

	public String getUsername()
	{
		return __username;
	}

	public String getPassword()
	{
		return __password;
	}

	public void enableGZip()
	{
		__gzip = true;
	}

	public boolean isGZip()
	{
		return __gzip;
	}

	public boolean isRequestByPushServer()
	{
		if (null != __ua && __ua.equalsIgnoreCase("PushServer"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public String getContentType()
	{
		return __content_type;
	}

	public void outputHeaders(HttpServletResponse resp)
	{
		if (__headers != null && __headers.size() > 0)
		{
			Enumeration<String> hashenum = __headers.keys();
			while (hashenum.hasMoreElements())
			{
				String Key = hashenum.nextElement();
				String Value = __headers.get(Key).toString();
				try
				{
					Value = URLEncoder.encode(Value, "utf-8");
					resp.addHeader(Key, Value);
				}
				catch (final Exception e)
				{

				}
			}
		}
	}

	public String getVar(String key)
	{
		String[] info = getParam(key);
		if (info != null && info[0].length() > 0)
		{
			return info[0].trim();
		}
		return null;
	}

	public String[] getVars(String key)
	{
		return getParam(key);
	}

	public int getVarInt(String key)
	{
		String val = getVar(key);
		if (val != null)
		{
			return Integer.parseInt(val);
		}
		return 0;
	}

	public long getVarLong(String key)
	{
		String val = getVar(key);
		if (val != null)
		{
			return Integer.parseInt(val);
		}
		return 0L;
	}

	public boolean getVarBooleanean(String key)
	{
		String val = getVar(key);
		if (val != null && val.toUpperCase().equals("TRUE"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
