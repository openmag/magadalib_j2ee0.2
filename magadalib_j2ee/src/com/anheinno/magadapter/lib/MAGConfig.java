package com.anheinno.magadapter.lib;

import java.net.URI;

public class MAGConfig {
	private static String _log_dir = null;

	public static String getLOG_DIR() {
		return _log_dir;
	}

	public static void setLOG_DIR(String value) {
		_log_dir = value;
	}

	private static String _push_engine_uri = null;

	public static String getPUSH_ENGINE_URI() {
		return _push_engine_uri;
	}

	public static void setPUSH_ENGINE_URI(URI value) {
		_push_engine_uri = value.toString();
	}

	private static long _default_expire = 24 * 3600 * 1000L;

	public static long getDEFAULT_EXPIRE() {
		return _default_expire;
	}

	public static void setDEFAULT_EXPIRE(long value) {
		_default_expire = value;
	}
	
	public static void setDefaultExpireHours(int hours) {
		_default_expire = hours*3600*1000L;
	}

	private static boolean _compress_auto = false;

	public static boolean getCOMPRESS_AUTO() {
		return _compress_auto;
	}

	public static void setCOMPRESS_AUTO(boolean value) {
		_compress_auto = value;
	}

	private static int _compress_threshold = 2048;

	public static int getCOMPRESS_THRESHOLD() {
		return _compress_threshold;
	}

	public static void setCOMPRESS_THRESHOLD(int value) {
		_compress_threshold = value;
	}

}
