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
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.call.FetchTokenCall;
import com.ebay.sdk.call.GetUserCall;
import com.ebay.soap.eBLBaseComponents.UserType;
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

			System.out.println("fetching tokens...");
//			String devId = Global.getProperty("devId");
//			String certId = Global.getProperty("certId");
//			String appId = Global.getProperty("appId");
//			String runame = Global.getProperty("runame");
//			String signInURL = Global.getProperty("ebaySignInUrl");
//			String apiServerUrl = Global.getProperty("ebayAPIUrl");
//
//			// System.out.println("fetching tokens...");
//			AccountsDAO accountsDAO = AccountsDAO.getDAO();
//			List<Account> accounts = accountsDAO.getAllAccounts();
//			for (Account account : accounts) {
//				List<EbayPlusAccount> ebayPlusAccounts = account.getEbayPlusAccounts();
//				for (EbayPlusAccount ebayPlusAccount : ebayPlusAccounts) {
//					// System.out.println("fetching for accout with id " +
//					// account.getAccountID() + "and ebayAccountID = " +
//					// ebayPlusAccount.getAccountID());
//					if (ebayPlusAccount.getAccountStatus() == AccountStatusType.WAITING_FOR_ACTIVATION) {
//						// System.out.println("status is wating");
//						String sessionID = ebayPlusAccount.getAccountSession();
//						if (sessionID != null) {
//							ApiContext apiContext = Global.createApiContext(devId, appId, certId, apiServerUrl);
//							ApiLogging apiLogging = new ApiLogging();
//							apiContext.setApiLogging(apiLogging);
//
//							FetchTokenCall fetchTokenApiCall = new FetchTokenCall(apiContext);
//							fetchTokenApiCall.setSessionID(sessionID);
//							String token = null;
//							Calendar tokenExp = null;
//							try {
//								token = fetchTokenApiCall.fetchToken();
//								tokenExp = fetchTokenApiCall.getHardExpirationTime();
//							} catch (Exception e) {
//								System.out.println(e.getMessage());
//							}
//							if (token != null) {
//
//								/**
//								 * get user first... in order to know if this
//								 * user has been added already
//								 */
//
//								ApiCredential apiCredential = new ApiCredential();
//								apiCredential.seteBayToken(token);
//
//								apiContext.setApiCredential(apiCredential);
//
//								GetUserCall getUserApiCall = new GetUserCall(apiContext);
//
//								UserType user = null;
//
//								try {
//									user = getUserApiCall.getUser();
//								} catch (ApiException e) {
//									e.printStackTrace();
//								} catch (SdkException e) {
//									e.printStackTrace();
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//
//								if (user != null) {
//									boolean duplicate = false;
//									for(EbayPlusAccount otherAccount: ebayPlusAccounts) {
//										if(user.getUserID().equals(otherAccount.getAccountName())) {
//											otherAccount.setAccountToken(token);
//											ebayPlusAccount.setAccountStatus(AccountStatusType.DISABLED);
//											ebayPlusAccount.setAccountSession(null);
//											duplicate = true;
//											break;
//										} 
//									}
//									if(!duplicate) {
//										ebayPlusAccount.setAccountToken(token);
//										ebayPlusAccount.setAccountSession(null);
//										ebayPlusAccount.setAccountStatus(AccountStatusType.ACTIVE);
//										ebayPlusAccount.setAccountName(user.getUserID());
//									}
//									
//								}
//
////								SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DDTHH:MM:SS.SSSZ");
////								System.out.println("got token with expiration: " + sdf.format(tokenExp.getTime()));
//								accountsDAO.updateAccount(account);
//							}
//						} else {
//							// System.out.println("No sessionID !!!");
//						}
//					}
//				}
//			}
		}

	}

}
