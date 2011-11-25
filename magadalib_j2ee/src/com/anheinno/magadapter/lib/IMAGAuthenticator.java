package com.anheinno.magadapter.lib;

import com.anheinno.magadapter.lib.ui.MAGLinkURL;


public interface IMAGAuthenticator
{
	MAGLinkURL authenticate(String username, String password, MAGRequest req);
}

