package com.anheinno.magadapter.lib;

import java.net.URI;

import javax.servlet.ServletException;

public class MAGConfig {
	private static String _log_dir = null;

	public static String getLogDir() {
		return _log_dir;
	}

	public static void setLogDir(String value) {
		_log_dir = value;
	}

	private static String _push_engine_uri = null;

	public static String getPushEngineURI() {
		return _push_engine_uri;
	}

	public static void setPushEngineURI(URI value) throws ServletException {
		if(value.getScheme() == null || value.getScheme().length() == 0) {
			throw new ServletException("no protocol defined in push-uri");
		}
		if(value.getHost() == null || value.getHost().length() == 0) {
			throw new ServletException("no host address in push-uri");
		}
		_push_engine_uri = value.toString();
	}

	private static long _default_expire = 24 * 3600 * 1000L;

	public static long getDefaultExpire() {
		return _default_expire;
	}

	public static void setDefaultExpireSeconds(int value) {
		_default_expire = value*1000L;
	}
	
	public static void setDefaultExpireHours(int hours) {
		_default_expire = hours*3600*1000L;
	}

	private static boolean _compress_auto = false;

	public static boolean isAutoCompressContent() {
		return _compress_auto;
	}

	public static void enableAutoCompressContent(boolean value) {
		_compress_auto = value;
	}

	private static int _compress_threshold = 2048;

	public static int getAutoCompressThreshold() {
		return _compress_threshold;
	}
	
	public static void setAutoCompressThreashold(int threshold) {
		_compress_threshold = threshold;
	}

}
