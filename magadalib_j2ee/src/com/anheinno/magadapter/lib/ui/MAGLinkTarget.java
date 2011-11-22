package com.anheinno.magadapter.lib.ui;

public class MAGLinkTarget {
	private String _target;
	
	public static final MAGLinkTarget LINK_TARGET_SELF;
	public static final MAGLinkTarget LINK_TARGET_NEW;
	public static final MAGLinkTarget LINK_TARGET_BROWSER;
	public static final MAGLinkTarget LINK_TARGET_CUSTOMCONTROL;
	public static final MAGLinkTarget LINK_TARGET_SCRIPT;
	
	static {
		LINK_TARGET_SELF = new MAGLinkTarget("__self_");
		LINK_TARGET_NEW = new MAGLinkTarget("__new_");
		LINK_TARGET_BROWSER = new MAGLinkTarget("__browser_");
		LINK_TARGET_CUSTOMCONTROL = new MAGLinkTarget("__custom_control_");
		LINK_TARGET_SCRIPT = new MAGLinkTarget("__script_");
	}
	
	public MAGLinkTarget(String target) {
		_target = target;
	}
	
	public String toString() {
		return _target;
	}
	
	public boolean equals(Object obj) {
		if(_target.equals(obj.toString())) {
			return true;
		}else {
			return false;
		}
	}
	
}
