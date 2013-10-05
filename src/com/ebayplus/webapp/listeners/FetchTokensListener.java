package com.ebayplus.webapp.listeners;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.call.FetchTokenCall;
import com.ebayplus.webapp.ebay.Global;
import com.ebayplus.webapp.hibernate.dao.AccountsDAO;
import com.ebayplus.webapp.hibernate.entities.Account;
import com.ebayplus.webapp.hibernate.entities.AccountStatusType;
import com.ebayplus.webapp.hibernate.entities.EbayPlusAccount;

public class FetchTokensListener implements ServletContextListener {

	private ScheduledExecutorService scheduler;

	public void contextInitialized(ServletContextEvent e) {
		System.out.println("Application context initialized");
		
		 scheduler = Executors.newSingleThreadScheduledExecutor();
	     scheduler.scheduleAtFixedRate(new FetchTokens(), 0, 5, TimeUnit.SECONDS);
		
		
	}
	
	public void contextDestroyed(ServletContextEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class FetchTokens implements Runnable {

		public void run() {
			
			String devId = Global.getProperty("devId");
			String certId = Global.getProperty("certId");
			String appId = Global.getProperty("appId");
			String runame = Global.getProperty("runame");
			String signInURL = Global.getProperty("ebaySignInUrl");
			String apiServerUrl = Global.getProperty("ebayAPIUrl");
			
			System.out.println("fetching tokens...");
			AccountsDAO accountsDAO = AccountsDAO.getDAO();
			List<Account> accounts = accountsDAO.getAllAccounts();
			for (Account account : accounts) {
				List<EbayPlusAccount> ebayPlusAccounts = account.getEbayPlusAccounts();
				for (EbayPlusAccount ebayPlusAccount : ebayPlusAccounts) {
					System.out.println("fetching for accout with id " + account.getAccountID() + "and ebayAccountID = " + ebayPlusAccount.getAccountID());
					if(ebayPlusAccount.getAccountStatus() == AccountStatusType.WAITING_FOR_ACTIVATION) {
						System.out.println("status is wating");
						String sessionID = ebayPlusAccount.getAccountSession();
						if(sessionID != null) {
							ApiContext apiContext = Global.createApiContext(devId, appId,
									certId, apiServerUrl);
							ApiLogging apiLogging = new ApiLogging();
							apiContext.setApiLogging(apiLogging);
							
							FetchTokenCall apiCall = new FetchTokenCall(apiContext);
							apiCall.setSessionID(sessionID);
							String token = null;
							Calendar tokenExp = null;
							try {
								token = apiCall.fetchToken();
								tokenExp = apiCall.getHardExpirationTime();
							} catch (Exception e) {
								System.out.println(e.getMessage());
								System.out.println("user didnt register the app at ebay yet");
							}
							if(token != null) {
								ebayPlusAccount.setAccountToken(token);
								ebayPlusAccount.setAccountStatus(AccountStatusType.ACTIVE);
								SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
								System.out.println("got token with expiration: " + sdf.format(tokenExp.getTime()));
								accountsDAO.updateAccount(account);
							}
						}else {
							System.out.println("No sessionID !!!");
						}
					}
				}
			}
		}
		
	}

}
