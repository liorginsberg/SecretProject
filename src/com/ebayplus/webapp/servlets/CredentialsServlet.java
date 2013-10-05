package com.ebayplus.webapp.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.SdkHTTPException;
import com.ebay.sdk.call.GetSessionIDCall;
import com.ebayplus.webapp.ebay.Global;

/**
 * Servlet implementation class CredentialsServlet
 */
public class CredentialsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("init() for "+ this.getClass().getSimpleName() + " was called");
				
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String devId = Global.getProperty("devId");
		String certId = Global.getProperty("certId");
		String appId = Global.getProperty("appId");
		String runame = Global.getProperty("runame");
		String signInURL = Global.getProperty("ebaySignInUrl");
		String apiServerUrl = Global.getProperty("ebayAPIUrl");
		
		
		if(request.getParameter("action").equals("getSessionID")) {
		
		
			ApiContext apiContext = Global.createApiContext(devId, appId,
					certId, apiServerUrl);
			ApiLogging apiLogging = new ApiLogging();
			apiContext.setApiLogging(apiLogging);
			
	
			GetSessionIDCall api = new GetSessionIDCall(apiContext);
			api.setRuName(runame);
	
			String ruParams = "params=" + runame + "-"
					+"Production";
	
			try {
	
				String sessionID = api.getSessionID();
				String encodedSesssionIDString = URLEncoder.encode(
						sessionID, "UTF-8");
	
				//TODO - enter all information to db
				response.getWriter().print("<a href='"+signInURL + "&RuName=" + runame
						+ "&SessID=" + encodedSesssionIDString
						+ "&ruparams=" + ruParams+"'>Link</a>");
			} catch (SdkHTTPException ex) {
				System.out.println("Call failed: " + ex.getMessage());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		} else if(request.getParameter("action").equals("fetchToken")) {
			
		}
	}

}
