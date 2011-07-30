package com.anheinno.magadapter.lib.ui;

public class MAGInputDuplicator extends MAGInputBase {
	
	public MAGInputDuplicator(String title, String id, MAGInputInterface template, String value) {
		super(title, id, value);
		setAttr("_template", template);
	}

	public void setInsertable(boolean insertable) {
		setAttr("_insert", insertable);
	}

	public void setDeleteable(boolean deletable) {
		setAttr("_delete", deletable);
	}

	public void setSortable(boolean sortable) {
		setAttr("_sort", sortable);
	}

	public void setMinCount(int min) {
		setAttr("_min_count", min);
	}

	public void setMaxCount(int max) {
		setAttr("_max_count", max);
	}

}
