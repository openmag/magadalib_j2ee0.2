package com.anheinno.magadapter.lib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Hashtable;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anheinno.magadapter.lib.ui.MAGLinkURL;

public abstract class MAGServer extends HttpServlet 
{

	private static final long serialVersionUID = 60017349184402628L;

	private final String DEFAULT_CONTENT_TYPE = "application/json";
	//private final String DEFAULT_CONTENT_TYPE = "text/plain";

	private Hashtable<String, IMAGHandler> _handler_table;
	private IMAGHandler _default_handler;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		acceptRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		acceptRequest(req, resp);
	}

	public final String getServletInfo() 
	{
		return "MAG Adaptor Server\nAnhe Innovation Technology";
	}

	@Override
	public void init() throws ServletException 
	{
		super.init();

		ServletConfig config = getServletConfig();
		ServletContext context = getServletContext();
		String log = config.getInitParameter("log");
		if (log != null) 
		{
			MAGConfig.setLogDir(context.getRealPath(log));
		} 
		else 
		{
			MAGConfig.setLogDir(context.getRealPath("log"));
		}
		
		String expire_hours = config.getInitParameter("expire");
		if(expire_hours != null) 
		{
			MAGConfig.setDefaultExpireHours(Integer.parseInt(expire_hours));
		}
		
		String push_uri = config.getInitParameter("push-uri");
		if(push_uri != null) 
		{
			try 
			{
				MAGConfig.setPushEngineURI(new URI(push_uri));
			}
			catch(final Exception e) 
			{
				throw new ServletException("illegal Push Engine Service URI(push-uri): " + push_uri);
			}
		}
		
		String compress_auto = config.getInitParameter("compress-auto");
		if(compress_auto != null) 
		{
			MAGConfig.enableAutoCompressContent(compress_auto.equalsIgnoreCase("TRUE"));
		}
		
		String compress_threshold = config.getInitParameter("compress-threshold");
		if(compress_threshold != null) 
		{
			MAGConfig.setAutoCompressThreashold(Integer.parseInt(compress_threshold));
		}

		_handler_table = new Hashtable<String, IMAGHandler>();
		_default_handler = getDefaultHandler();

		MAGLogin login = new MAGLogin();
		login.registerAuthenticator(getAuthenticator());
		
		MAGLinkURL[] prefetch_urls = getPrefetchURLs();
		if(prefetch_urls != null) {
			for(int i = 0; i < prefetch_urls.length; i ++) {
				//System.out.println("register prefetch url=" + prefetch_urls[i]);
				login.registerPrefetchURL(prefetch_urls[i]);
			}
		}
		
		registerHandler(login);
		registerHandler(new MAGLogout());

		IMAGHandler[] handles = getHandlers();
		if (handles != null) 
		{
			for (int i = 0; i < handles.length; i++) 
			{
				registerHandler(handles[i]);
			}
		}
	}

	// /////////////////////////////////
	// / need to be overrided //////////
	// /////////////////////////////////
	protected IMAGHandler getDefaultHandler() 
	{
		return null;
	}

	protected IMAGAuthenticator getAuthenticator() 
	{
		return null;
	}

	protected abstract IMAGHandler[] getHandlers();
	
	protected MAGLinkURL[] getPrefetchURLs() {
		return null;
	}

	// /////////////////////

	private boolean registerHandler(IMAGHandler handler) 
	{
		if (!_handler_table.containsKey(handler.getAction())) 
		{
			_handler_table.put(handler.getAction(), handler);
			return true;
		} 
		else 
		{
			MAGLog.log("Duplicate handler " + handler.getAction());
			return false;
		}
	}

	private static byte[] Compress(String uncompressedString, boolean compress) throws IOException 
	{
		byte[] byteData = uncompressedString.getBytes();
		byte[] compressData = null;
		if (compress) 
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(byteData);
			gzip.finish();
			gzip.close();
			compressData = bos.toByteArray();
			bos.close();
			return compressData;
		} 
		else 
		{
			return byteData;
		}
	}

	private static void disableCache(HttpServletResponse resp)
	{
		resp.setDateHeader("Expires ", 0);
		resp.setHeader("Cache-Control ", "no-cache ");
		resp.setHeader("Cache-Control ", "no-store ");
		resp.addHeader("Cache-Control", "max-age=0");
	}

	public void acceptRequest(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException 
	{

		MAGRequest req = new MAGRequest(request);
		//PrintWriter out = resp.getWriter();

		//MAGLog.log(req, "Log dir: " + MAGConfig.getLogDir());
		//MAGLog.log(req, "URL: " + req.getURL());
		MAGLog.log(req, "Start!");

		disableCache(resp);

		if (null == req.getHandle()) 
		{
			resp.setContentType(DEFAULT_CONTENT_TYPE);
			req.error("No handler specified, please check _action parameter!");
			resp.addHeader("X-Anhe-MAG-Result", "FALSE");
			resp.setContentLength(req.getResponse().length());
			resp.getWriter().print(req.getResponse());
		} 
		else if (_default_handler != null || _handler_table.containsKey(req.getHandle())) 
		{
			IMAGHandler handler = null;
			if (_handler_table.containsKey(req.getHandle())) 
			{
				handler = (IMAGHandler) _handler_table.get(req.getHandle());
			}
			else 
			{
				handler = _default_handler;
			}
			if (handler.process(req)) 
			{
				req.outputHeaders(resp);
				
				if (null == req.getContentType()) 
				{
					resp.setContentType(DEFAULT_CONTENT_TYPE);
					resp.setCharacterEncoding("UTF-8");
				} 
				else 
				{
					resp.setContentType(req.getContentType());
				}
				
				resp.addHeader("X-Anhe-MAG-Result", "TRUE");

				if (!req.isBinary()) 
				{
					boolean compressed = false;
					
					if (!req.isRequestByPushServer() && (req.isGZip() || (MAGConfig.isAutoCompressContent() && req.getResponse().length() > MAGConfig.getAutoCompressThreshold()))) 
					{
						
						resp.addHeader("X-Anhe-Content-Encoding", "gzip");
						compressed = true;
					}
					
					if (req.isCacheable() && !req.isRequestByPushServer() && MAGConfig.getPushEngineURI() != null) 
					{
						if (!MAGPushClient.registerURL(req)) 
						{
							MAGLog.log(req, "registerURL " + req.getURL() + " failed!");
						}
					}
					
					if(!compressed) 
					{
						resp.setContentLength(req.getResponse().length());
						resp.getWriter().print(req.getResponse());
					}
					else 
					{
						byte[] obytes = Compress(req.getResponse(), compressed);
						resp.setContentLength(obytes.length);
						ServletOutputStream output = resp.getOutputStream();
						try 
						{
							output.write(obytes);
						}
						finally 
						{
							if(output != null) 
							{
								try 
								{
									output.close();
								}
								catch(final Exception e) {}
							}
						}
					}
				} 
				else 
				{
					byte[] obytes = req.getResponseBinary();
					resp.setContentLength(obytes.length);
					ServletOutputStream output = resp.getOutputStream();
					try 
					{
						output.write(obytes);
					}
					finally 
					{
						if(output != null) 
						{
							try 
							{
								output.close();
							}
							catch(final Exception e) {}
						}
					}
				}
				
			} 
			else 
			{
				resp.setContentType(DEFAULT_CONTENT_TYPE);
				resp.addHeader("X-Anhe-MAG-Result", "FALSE");
				resp.setContentLength(req.getResponse().length());
				resp.getWriter().print(req.getResponse());
			}
		} 
		else 
		{
			resp.setContentType(DEFAULT_CONTENT_TYPE);
			req.error("No registered handler for " + req.getHandle());
			resp.addHeader("X-Anhe-MAG-Result", "FALSE");
			resp.setContentLength(req.getResponse().length());
			resp.getWriter().print(req.getResponse());
		}
	}
}
