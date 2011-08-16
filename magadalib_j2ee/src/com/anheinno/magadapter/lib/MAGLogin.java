package com.anheinno.magadapter.lib;

import org.json.lite.JSONObject;

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
		String account = MAGPushClient.getBoundAccount(req);
		if (null == account)
		{
			if (req.getUsername() == null || req.getUsername().length() == 0 || (req.getPIN() != null && req.getUsername().equalsIgnoreCase(req.getPIN())))
			{
				req.error("请提供账号名称！");
				return false;
			}
			else
			{
				req.error("该账号禁止从该设备登录！");
				return false;
			}
		}
		else
		{
			if (req.getUsername() == null || req.getUsername().length() == 0 || (req.getPIN() != null && req.getUsername().equalsIgnoreCase(req.getPIN())))
			{
				req.setUsername(account);
			}
		}

		if (null != req.getUsername() && req.getUsername().length() > 0)
		{
			if (_authenticator != null)
			{
				MAGLinkURL redirect = _authenticator.authenticate(req.getUsername(), req.getPIN(), req.getPassword());
				if (redirect != null)
				{
					JSONObject config = null;
					if (req.getVarBooleanean("_bind"))
					{
						config = MAGPushClient.registerPush(req);
					}
					req.redirect(redirect.getURL(), config);
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
