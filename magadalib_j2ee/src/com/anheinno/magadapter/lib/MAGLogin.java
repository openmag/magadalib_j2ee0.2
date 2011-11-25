package com.anheinno.magadapter.lib;

import org.json.lite.JSONArray;
import org.json.lite.JSONObject;

import com.anheinno.magadapter.lib.MAGLog;
import com.anheinno.magadapter.lib.MAGPushClient;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;

public class MAGLogin implements IMAGHandler
{
	private IMAGAuthenticator _authenticator;
	private JSONArray _prefetch_urls;

	public MAGLogin()
	{
		_authenticator = null;
		_prefetch_urls = null;
	}

	public void registerAuthenticator(IMAGAuthenticator auth)
	{
		_authenticator = auth;
	}
	
	public void registerPrefetchURL(MAGLinkURL link) {
		if(_prefetch_urls == null) {
			_prefetch_urls = new JSONArray();
		}
		_prefetch_urls.put(link.toJSONObject());
	}

	public boolean process(MAGRequest req)
	{
		if(req.isRequestByPushServer()) {
			return get_config(req);
		}else {
			return auth_device(req);
		}
	}
	
	private boolean get_config(MAGRequest req) {
		MAGLog.log("Config User: " + req.getUsername() + " Password: " + req.getPassword());
		return _push_auth(req, false);
	}
	
	private boolean auth_device(MAGRequest req) {
		MAGLog.log("Auth User: " + req.getUsername() + " Password: " + req.getPassword());
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
		return _push_auth(req, true);
	}
	
	private boolean _push_auth(MAGRequest req, boolean request_by_client) {

		if (null != req.getUsername() && req.getUsername().length() > 0)
		{
			if (_authenticator != null)
			{
				MAGLinkURL redirect = _authenticator.authenticate(req.getUsername(), req.getPassword(), req);
				if (redirect != null)
				{
					JSONObject config = null;
					if (req.getVarBoolean("_bind"))
					{
						if(request_by_client) {
							//System.out.println("request_by_client");
							config = MAGPushClient.registerPush(req);
						}else {
							//System.out.println("request_by_server");
							config = MAGPushClient.getUserConfig(req);
						}
						if(_prefetch_urls != null) {
							//System.out.println("add prefetch urls " + _prefetch_urls.length());
							try {
								if(config == null) {
									config = new JSONObject();
								}
								config.put("_prefetch", _prefetch_urls);
							}catch(final Exception e) {
								System.out.println("Failed to add prefetch URLs: " + e.toString());
							}
						}
					}
					req.redirect(redirect, config);
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
