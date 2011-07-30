package com.anheinno.magadapter.lib.ui;

public interface MAGInputInterface extends MAGComponentInterface {
	void readOnly();
	void nonEmpty();
	void verifyMessage(String msg);
}