package com.anheinno.magadapter.lib.ui;

public class MAGPanel extends MAGContainerBase {
	
	public enum MAGPanelExpandStatus implements StringEnum {
		MAGPANEL_EXPAND_DISABLE("__disable_"),
		MAGPANEL_EXPAND_EXPAND("__expand_"),
		MAGPANEL_EXPAND_COLLAPSE("__collapse_");
		
		private final String _status;
		private MAGPanelExpandStatus(String status) {
			_status = status;
		}
		
		public String toString() {
			return _status;
		}
	}

	public MAGPanel() {
		super(null, null);
	}
	
	public MAGPanel(String title) {
		super(title, null);
	}

	public MAGPanel(String title, String id) {
		super(title, id);
	}
	
	public void setExpandStatus(MAGPanelExpandStatus state) {
		setAttr("_expand", state.toString());
	}

}
