<%@page import="com.ebayplus.webapp.hibernate.entities.EbayPlusAccount"%>
<%@page import="com.ebayplus.webapp.hibernate.entities.Account"%>
<%@page import="com.ebayplus.webapp.hibernate.dao.AccountsDAO"%>
<%@page import="com.ebayplus.webapp.ebay.Global"%>
<%@ page import="com.ebay.sdk.*"%>
<%@ page import="com.ebay.sdk.call.*"%>
<%@ page import="java.net.URLEncoder"%>




<%
	String reqError = "";
	String devId = Global.getProperty("devId");
	String certId = Global.getProperty("certId");
	String appId = Global.getProperty("appId");
	String signInURL = Global.getProperty("ebaySignInUrl");
	String ApiServerUrl = Global.getProperty("ebayAPIUrl");
	//runame of this sample application
	String runame = Global.getProperty("runame");

	reqError = "";

	ApiContext apiContext = Global.createApiContext(devId, appId,
			certId, ApiServerUrl);
	ApiLogging apiLogging = new ApiLogging();
	apiContext.setApiLogging(apiLogging);
	session.setAttribute("apicontext", apiContext);

	GetSessionIDCall api = new GetSessionIDCall(apiContext);
	api.setRuName(runame);

	String ruParams = "params=" + runame + "-Production";

	try {

		String sessionID = api.getSessionID();
		
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		int eBayPlusAccountID = Integer.parseInt(request.getParameter("eBayPlusAccountID"));
		
		System.out.println("[DEBUG] try set sessionID for accountID:" + accountID + " eBayPlusAcountID=" + eBayPlusAccountID);
		
		AccountsDAO accountsDAO = AccountsDAO.getDAO();
		Account account = accountsDAO.getAccountById(accountID);
		
		EbayPlusAccount eBayPlusAccount = account.getEbayPlusAccountByID(eBayPlusAccountID);
		eBayPlusAccount.setAccountSession(sessionID);
		
		accountsDAO.updateAccount(account);		
				
		String encodedSesssionIDString = URLEncoder.encode(sessionID,
				"UTF-8");

		session.setAttribute("sessionID", sessionID);
		response.sendRedirect(response.encodeRedirectURL(signInURL
				+ "&RuName=" + runame + "&SessID="
				+ encodedSesssionIDString + "&ruparams=" + ruParams));

	} catch (SdkHTTPException ex) {
		reqError = "Call failed: " + ex.getMessage();
	} catch (Exception ex) {
		reqError = ex.getMessage();
	}
%>
