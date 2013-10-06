package com.ebayplus.webapp.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.SdkHTTPException;
import com.ebay.sdk.call.GetSessionIDCall;
import com.ebayplus.webapp.ebay.Global;
import com.ebayplus.webapp.hibernate.dao.AccountsDAO;
import com.ebayplus.webapp.hibernate.entities.Account;
import com.ebayplus.webapp.hibernate.entities.AccountStatusType;
import com.ebayplus.webapp.hibernate.entities.EbayPlusAccount;

/**
 * Servlet implementation class AccountManagerServlet
 */
public class AccountManagerServlet extends HttpServlet {
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
		
		ApiContext apiContext = Global.createApiContext(devId, appId,
				certId, apiServerUrl);
		ApiLogging apiLogging = new ApiLogging();
		apiContext.setApiLogging(apiLogging);
		

		GetSessionIDCall api = new GetSessionIDCall(apiContext);
		api.setRuName(runame);

		String ruParams = "params=" + runame + "-"
				+"Production";

		String sessionID = null;
		try {

			sessionID = api.getSessionID();
			
		} catch (SdkHTTPException ex) {
			System.out.println("Call failed: " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		AccountsDAO accountsDAO = AccountsDAO.getDAO();
		Account account = accountsDAO.getAccountById(accountID);
		
		EbayPlusAccount e = new EbayPlusAccount();
		e.setAccountAdmin(request.getParameter("accountAdmin"));
		e.setAccountAlias(request.getParameter("accountAlias"));
		e.setAccountColor(request.getParameter("accountColor"));
		e.setAccountSession(sessionID);
		e.setAccountStatus(AccountStatusType.WAITING_FOR_ACTIVATION);
	
		
		account.getEbayPlusAccounts().add(e);
		
		accountsDAO.updateAccount(account);
		
		response.getWriter().print("Success");
	}

}

