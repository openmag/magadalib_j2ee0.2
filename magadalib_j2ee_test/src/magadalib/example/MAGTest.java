package magadalib.example;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;


import com.anheinno.magadapter.lib.IMAGAuthenticator;
import com.anheinno.magadapter.lib.IMAGHandler;
import com.anheinno.magadapter.lib.MAGServer;
import com.anheinno.magadapter.lib.ui.MAGLinkURL;

/**
 * Servlet implementation class MAGTest
 */
@WebServlet(
		urlPatterns = { "/MAGTest" }, 
		initParams = { 
				@WebInitParam(name = "log", value = "log"),
				@WebInitParam(name = "expire", value = "72"),
				@WebInitParam(name = "push-uri", value = "http://192.168.0.201/MAGLIBv0.3/magserver/push/pushengine.php"),
				@WebInitParam(name = "compress-auto", value="true"),
				@WebInitParam(name = "compress-threshold", value="8192")
		})

public class MAGTest extends MAGServer {
	private static final long serialVersionUID = 1L;

	protected IMAGAuthenticator getAuthenticator() {
		return new IMAGAuthenticator() {
			public MAGLinkURL authenticate(String username, String pin, String password) {
				if(username != null && username.equals("admin") && password != null && password.equals("123")) {
					return (new MAGLinkURL("MAGTest2")).setHandler("MAINSCREEN");
				}else {
					return (MAGLinkURL)null;
				}
			}	
		};
	}

	protected IMAGHandler[] getHandlers() {
		return new IMAGHandler[] {
			new MainScreen()
		};
	}

}
