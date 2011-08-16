package com.apps.anheinno.demo;

import com.anheinno.magadapter.lib.IMAGAuthenticator;
import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGServer;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(
		urlPatterns = { "/Services" }, 
		initParams = {
		@WebInitParam(name = "log", value = "log"),
		@WebInitParam(name = "expire", value = "72"),
		@WebInitParam(name = "push-uri", value = "http://192.168.0.201/MAGLIBv0.4/magserver/pushserv/pushengine.php"),
		@WebInitParam(name = "compress-auto", value = "false"),
		@WebInitParam(name = "compress-threshold", value = "8192") 
		})

public class Services extends MAGServer
{
	private static final long serialVersionUID = 1L;

	protected IMAGAuthenticator getAuthenticator()
	{
		return new IMAGAuthenticator()
		{
			public MAGLinkURL authenticate(String username, String pin, String password)
			{
				if (username != null && username.equals("admin") && password != null && password.equals("123"))
				{
					return (new MAGLinkURL().setHandler("MAINSCREEN"));
				}
				else 
				{
					return (MAGLinkURL)null;
				}
			}
			
		};
	}



	protected IMAGHandler[] getHandlers()
	{
		return new IMAGHandler[]
		{
			new MainScreen(),
			new Screen1(),
			new Screen2(),
			new Quotation(),
			new Screen_Readonly(),
			new NineGrid(),
			new NewAppointment(),
			new SaveNewAppointment(),
			new MAGInfoGridDemo(),
			new MAGListDemo(),
			new SubmitInputlist(),
			new MAGTieredSelectDemo(),
			new MAGKeywordFilterSelectDemo(),
			new SubmitResult()
		};
	}

}
