package com.anheinno.magadapter.lib;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;

import com.anheinno.magadapter.lib.MAGConfig;
import com.anheinno.magadapter.lib.MAGLog;

import java.net.HttpURLConnection;

public class MAGPushClient {

	private static String buildQuery(Hashtable<String, String> param) {
		String query = "";
		Enumeration<String> hashenum = param.keys();
		while (hashenum.hasMoreElements()) {
			try {
				String key = URLEncoder.encode(hashenum.nextElement(), "utf-8");
				String value = URLEncoder.encode(param.get(key), "utf-8");
				if (query.length() > 0) {
					query += "&";
				}
				query += key;
				query += "=";
				query += value;
			}catch(final Exception e) {
				
			}
		}
		return query;
	}

	private static String post(String urlstr, Hashtable<String, String> param) {
		try {
			URL url = new URL(urlstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//conn.setRequestProperty("charset", "utf-8");
			String data = buildQuery(param);
			byte[] bytes = data.getBytes("UTF-8");
			conn.setRequestProperty("Content-Length", String.valueOf(bytes.length));
			conn.setRequestProperty("Connection", "Close");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			DataOutputStream dout = new DataOutputStream(conn.getOutputStream());
			// BufferedWriter out = new BufferedWriter(new
			// OutputStreamWriter(connection.getOutputStream(), "utf8"));
			dout.write(bytes, 0, bytes.length);
			dout.flush();
			dout.close();
	
			int rc = conn.getResponseCode();
			if (rc == HttpURLConnection.HTTP_OK) {
				String result = conn.getHeaderField("X-Anhe-Result");
				if(result == null) {
					result = conn.getHeaderField("X-Anhe-MAG-Result");
				}
				if (result.trim().equalsIgnoreCase("TRUE")) {
					DataInputStream din = new DataInputStream(conn.getInputStream());
					String res = din.readUTF();
					din.close();
					return res;
				}
			}
			conn.disconnect();
		}catch(final Exception e) {
			
		}
		return null;
	}

	/*
	 * Map headers=connection.getHeaderFields(); Set<String> keys =
	 * headers.keySet(); for( String key : keys ){ String val =
	 * connection.getHeaderField(key); System.out.println(key+"     "+val); }
	 */

	public static boolean registerPush(MAGRequest req) {
		MAGLog.log("registerPush: " + req.getModule() + " " + req.getUsername()
				+ " " + req.getPassword() + " " + req.getPIN() + " "
				+ req.getDevice() + " " + req.getSoftwareVersion() + " "
				+ req.getPlatformVersion() + " " + req.getPushServer());
		if (req.isRequestByPushServer()
				|| MAGConfig.getPUSH_ENGINE_URI() == null) {
			return true;
		}

		if (null != req.getPushServer() && null != req.getPushProtocol()) {
			return __register_device(req.getModule(), req.getUsername(),
					req.getPassword(), req.getPIN(), req.getIMSI(),
					req.getDevice(), req.getSoftwareVersion(),
					req.getPlatformVersion(), req.getPushServer(),
					req.getPushProtocol());
		} else {
			return false;
		}
	}

	private static boolean __register_device(String module, String user,
			String passwd, String pin, String imsi, String device,
			String software, String platform, String mdsserver, String protocol) {
		Hashtable<String, String> query = new Hashtable<String, String>();
		query.put("_action", "REGIST");
		query.put("_module", module);
		query.put("_user", user);
		query.put("_passwd", passwd);
		query.put("_pin", pin);
		query.put("_imsi", imsi);
		query.put("_software", software);
		query.put("_platform", platform);
		query.put("_device", device);
		query.put("_mds", mdsserver);
		query.put("_protocol", protocol);

		String result = post(MAGConfig.getPUSH_ENGINE_URI(), query);
		if (result == null) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean unregisterPush(MAGRequest req) {
		if (req.isRequestByPushServer()
				|| MAGConfig.getPUSH_ENGINE_URI() == null) {
			return true;
		}
		return __unregister_device(req.getModule(), req.getPIN(), req.getIMSI());
	}

	private static boolean __unregister_device(String module, String pin, String imsi)  {
		Hashtable<String, String> query = new Hashtable<String, String>();
		query.put("_action", "UNREG");
		query.put("_module", module);
		query.put("_pin", pin);
		query.put("_imsi", imsi);

		String result = post(MAGConfig.getPUSH_ENGINE_URI(), query);
		if (result == null) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean registerURL(MAGRequest req) {
		if (req.isRequestByPushServer()
				|| MAGConfig.getPUSH_ENGINE_URI() == null) {
			return true;
		}
		return __registerURL(req.getModule(), req.getUsername(), req.getPIN(),
				req.getIMSI(), req.getURL(), req.getExpire());
	}

	private static boolean __registerURL(String module, String user,
			String pin, String imsi, String url, long expire) {
		Hashtable<String, String> query = new Hashtable<String, String>();
		query.put("_action", "CACHE");
		query.put("_module", module);
		query.put("_user", user);
		query.put("_pin", pin);
		query.put("_imsi", imsi);
		query.put("_url", url);
		query.put("_expire", "" + expire);

		String result = post(MAGConfig.getPUSH_ENGINE_URI(), query);
		if (result == null) {
			return false;
		} else {
			return true;
		}
	}

	private static String __getLocalVar(String module, String user, String var) {
		Hashtable<String, String> query = new Hashtable<String, String>();
		query.put("_action", "GETVAR");
		query.put("_module", module);
		query.put("_user", user);
		query.put("_var", var);

		String result = post(MAGConfig.getPUSH_ENGINE_URI(), query);
		if (result == null) {
			return null;
		} else {
			return result;
			// return com.anheinno.mag.magml.MAGJson.json_decode(result);
		}
	}

	public static String getLocalVar(MAGRequest req, String var) {
		if (MAGConfig.getPUSH_ENGINE_URI() == null) {
			return null;
		}
		return __getLocalVar(req.getModule(), req.getUsername(), var);
	}

	private static boolean __setLocalVar(String module, String user,
			String var, String value) {
		Hashtable<String, String> query = new Hashtable<String, String>();
		query.put("_action", "SETVAR");
		query.put("_module", module);
		query.put("_user", user);
		query.put("_var", var);
		query.put("_value", value);

		String result = post(MAGConfig.getPUSH_ENGINE_URI(), query);
		if (result == null) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean setLocalVar(MAGRequest req, String var, String value) {
		if (req.isRequestByPushServer()
				|| MAGConfig.getPUSH_ENGINE_URI() == null) {
			return true;
		}
		return __setLocalVar(req.getModule(), req.getUsername(), var, value);
	}

}