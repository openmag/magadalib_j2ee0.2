package com.anheinno.magadapter.lib.ui;


public class MAGNote extends MAGComponent {
	public MAGNote(String title, String note) {
		this(title, note, null);
	}

	public MAGNote(String title, String note, String id) {
		super(title, id);
		setAttr("_note", note);
	}
}
