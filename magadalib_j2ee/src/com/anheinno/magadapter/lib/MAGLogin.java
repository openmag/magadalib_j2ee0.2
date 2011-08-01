package com.anheinno.magadapter.lib;


import com.anheinno.magadapter.lib.MAGLog;
import com.anheinno.magadapter.lib.MAGPushClient;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;


public class MAGLogin implements IMAGHandler
{
    private IMAGAuthenticator _authenticator;

    public MAGLogin()
    {
        _authenticator = null;
    }

    public void registerAuthenticator(IMAGAuthenticator auth)
    {
        _authenticator = auth;
    }

    public boolean process(MAGRequest req)
    {
        MAGLog.log("User: " + req.getUsername() + " Password: " + req.getPassword());
        if(null != req.getUsername() && req.getUsername().length() > 0) 
        {
            if(_authenticator != null) 
            {
                MAGLinkURL redirect = _authenticator.authenticate(req.getUsername(), req.getPIN(), req.getPassword());
                if(redirect != null) 
                {
                    if(req.getVarBooleanean("_bind")) 
                    {
                        MAGPushClient.registerPush(req);
                    }
                    req.redirect(redirect.getURL());
                    return true;
                }
                else 
                {
                    req.error("错误的用户名或密码！");
                    return false;
                }
            }
            else 
            {
                req.error("没有注册认证方法");
                return false;
            }
        }
        else 
        {
            req.error("没有提供用户名！");
            return false;
        }
    }

    public String getAction()
    {
        return "LOGIN";
    }
}
	

