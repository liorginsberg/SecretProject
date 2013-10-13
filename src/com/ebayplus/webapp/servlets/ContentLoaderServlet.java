package com.ebayplus.webapp.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.eBayAccount;
import com.ebay.sdk.call.GetMyMessagesCall;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.MyMessagesMessageType;
import com.ebayplus.webapp.ebay.Global;
import com.ebayplus.webapp.hibernate.dao.AccountsDAO;
import com.ebayplus.webapp.hibernate.entities.Account;
import com.ebayplus.webapp.hibernate.entities.AccountStatusType;
import com.ebayplus.webapp.hibernate.entities.EbayPlusAccount;

/**
 * Servlet implementation class ContentLoaderServlet
 */
public class ContentLoaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String tempToken = "AgAAAA**AQAAAA**aAAAAA**CGpYUg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6AFlYapC5GApw6dj6x9nY+seQ**qvEBAA**AAMAAA**jtMBdUla0zi2jpti/m8wKMOP0/z/J5BE5Qc/40KiDNDHTbepBT9FMFYR7uke27eYh8Q4vknKGuN5TBJNpYwCRz50uyVfRUAc3c2c6zp+jLjiIu+FKhNRijA0n6WftvZeLtX6D14EGJaFUIdxzAo5ZIoXZm6wG5lFlS7EPaH1h9KAcdbdxkEGugJl1w5Xn6NQjpXkzFd5UaUdS2Fn/j9u2vG69z4FZ5VIN6yBRH1RfMH4cvbnCsSrkRXoaL1Hf8+2mSbOm5D0xOLzcnq9xrZfW6Fs7Vz/UWQy4kR5NRO2nD1Sk1NddsXRq2H0X517XV9ILSEp/3W5K9tLlJHyFCjbUxeVgxPaZ+UFF2nJnOPMDVcG53P/YcbuU52v4QMYxksyrvW+w754487Ol/QGkSyTnFAGk3GnMZTCP+YbeuuyDLIxSLntmNKLGFE0T9XAO0AvLnKwwvcaMBfNVhXz0M56ZpgQpMfAKUYlnKuWR8t2QlP7fwbIFi+D7cq2I//CgvVgAIQL0IOzMxEBlF6QVD7IxvpOJdX7AKnS5QfQCFjhrzEEgdFP8UQzkBi4anKD11Wgb2E24bXObihiNSqq+IrU4JZsRW9/MEHhpePdGuvdnFyHj8PgyuZeUOXJlxEtd2/rcGSBIuv3S3VieSCj7MxJAKfeNujJqGUp8JznpV/+KFaPJQmsw0Gbk83k+xPo0ll60W5qhvD3/zFKygri8H5TRtUCe1MJ5n/psh4t4njLdz5oTwgxnYSxpVeWlVQXaCbc";
	
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("init() for "+ this.getClass().getSimpleName() + " was called");
				
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("ContentLoaderServlet dopost");
		String contentID = request.getParameter("contentID");

		if (contentID.equals("settingsContent")) {
			String buffer = "<div id='settings_side_menu'>"
					+ "<div id='cssmenu'>"
					+ "<ul><li class='active'>"
					+ "<a href='#'><span>Home</span></a> </li>"
					+ "<li> <a href='#'><span>Products</span></a> </li>"
					+ "<li> <a href='#'><span>About</span></a> </li>"
					+ "<li class='last'> <a href='#'><span>Contact</span></a> </li>"
					+ "</ul></div></div>"
					+ "<div id='settings_content'>"
					+ "<div class='setting_component'>"
					+ "<div class='settings_header'>Notificatsion Settings </div>"
					+ "<div class='setting_content first_content'></div>"
					+ "<div class='setting_content'></div>"
					+ "<div class='setting_content'></div></div>"
					+ "<div class='setting_component'>"
					+ "<div class='settings_header'> Notificatsion Settings </div>"
					+ "<div class='setting_content first_content'> </div>"
					+ "</div><div class='setting_component last_setting_component'>"
					+ "<div class='settings_header'> Notificatsion Settings </div>"
					+ "<div class='setting_content first_content'> </div></div></div>"
					+ "<div class='clear'></div>";

			response.getWriter().print(buffer);
			
		} else if (contentID.equals("accountsContent")) {
			System.out.println("[DEBUG] contentID = accountsContent");
			AccountsDAO accountsDAO = AccountsDAO.getDAO();
			Account account = accountsDAO.getAccountById(1);
			
			List<EbayPlusAccount> accountList = account.getEbayPlusAccounts();
			StringBuilder sb = new StringBuilder();
			sb.append("<div id='accountsActionMenu'>");
			sb.append("<div class='accountsAction'><button id='addAccountBtn'>Add Account</button></div></div>");
			sb.append("<div id='accountsContent'>");
			for(EbayPlusAccount accountItem: accountList) {
				sb.append("<div class='accountItem'>");
				sb.append("<div class='accountHeader'>");
				sb.append(accountItem.getAccountAlias());
				sb.append("</div>");
				sb.append("<table style='width: 85%'>");
				sb.append("<tr><td>Account Name:</td>");
				sb.append("<td>" + accountItem.getAccountName() + "</td></tr>");
				sb.append("<tr><td>Administrator:</td>");
				sb.append("<td>" + accountItem.getAccountAdmin() + "</td></tr>");
				sb.append("<tr><td>Lable Color:</td>");
				//TODO - handle color from db to the widget
				sb.append("<td><input type='text' " + "class='basic' value='"+ accountItem.getAccountColor() +"'/></td></tr>");
				sb.append("<tr><td>Status:</td>");
				sb.append("<td>" + accountItem.getAccountStatus().toString() + "</td>");
				
				if(accountItem.getAccountStatus() == AccountStatusType.WAITING_FOR_ACTIVATION) {
					sb.append("<td><a target='_blank' href='credentials.jsp?accountID="+ account.getAccountID() +"&eBayPlusAccountID="+ accountItem.getAccountID() +"'>Activate</a></td>");
				}
				sb.append("</tr></table></div>");
			}
			sb.append("</div>");
			sb.append("<div class='clear'></div>");
			response.getWriter().print(sb.toString());
		
		} else if(contentID.equals("addAccountDialog")) {
			String buffer = "<div id='addAccountDialog'>" +
							"<h3 id='title'>Add Account</h3>" +
					        "Account's Alias: <input type='text' id='accountAlias'><br>" +
					        "Account's Administrator: <input type='text' id='accountAdmin'><br>" +
					        "Account's color: <input type='text' class='basic' id='accountColor'><br>" +
					        "<input type='button' id='cancelAccount' value='Cancel' style='display: inline;'>" +
					        "<input type='button' id='saveAccount' value='Save' style='display: inline;'></div>";
	
			response.getWriter().print(buffer);
		} else if(contentID.equals("messagesContent")) {
			
			String devId = Global.getProperty("devId");
			String certId = Global.getProperty("certId");
			String appId = Global.getProperty("appId");
			String runame = Global.getProperty("runame");
			String signInURL = Global.getProperty("ebaySignInUrl");
			String apiServerUrl = Global.getProperty("ebayAPIUrl");
	
			ApiContext apiContext = Global.createApiContext(devId, appId, certId, apiServerUrl);
			ApiLogging apiLogging = new ApiLogging();
			apiContext.setApiLogging(apiLogging);
			
			ApiCredential apiCredential = new ApiCredential();
			apiCredential.seteBayToken(tempToken);
			
			apiContext.setApiCredential(apiCredential);
			
			GetMyMessagesCall getMyMessagesCall = new GetMyMessagesCall(apiContext);
			
			getMyMessagesCall.addDetailLevel(DetailLevelCodeType.RETURN_HEADERS);
			
			MyMessagesMessageType[] messages = getMyMessagesCall.getReturnedMyMessages();
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("<div id='messagesLibraries'><h3>Inbox</h3><p>All Messages</p><p>From eBay</p>"
					+ "<p>High Priority!</p><h3>Sent</h3><h3>Trash</h3><h3>Folders</h3>"
					+ "<p>Saved Messages</p><p>Important</p></div><div id='messagesContent'>"
					+ "<div class='simplePagerContainer'><ul id='messagesList'>");
			
			for (MyMessagesMessageType message : messages) {
				sb.append("<li class='messageItem simplePagerPage1' id='");
				sb.append(message.getMessageID());
				sb.append("'><table class='messageTable'><tbody><tr>"
						+"<td class='messageItemleft'>");
				String repliedVisibility = message.isReplied() ? "visible": "hidden";
				sb.append("<img class='messageRepleid' src='images/replied.png' style='display:"+ repliedVisibility + "></td>");
				sb.append("<td class='messageItemMiddle'><p class='messageSender'>");
				sb.append(message.getSender());
				sb.append("</p></p><p class='messageSubject'>");
				sb.append(message.getSubject());
				sb.append("</p><span class='accountLabel'>");
				sb.append(message.getSendToName());
				sb.append("</span></td><td class='messageItemRight'><span class='messageTime'>");
				sb.append("12:05");
				String flagged = message.isFlagged()? "visible" : "hidden";
				sb.append("</span> <img class='messageFlag' "
						+ "src='images/flagged_message.png' " 
						+ flagged + "></td></tr></tbody></table></li>");
			}
			sb.append("</ul><ul class='simplePagerNav'>"
					+ "<li class='currentPage simplePageNav1'><a rel='1' href='#'>1</a>"
					+ "</li><li class='simplePageNav2'><a rel='2' href='#'>2</a></li></ul></div></div>"
						+"<div class='clear'></div>");
			
			response.getWriter().print(sb.toString());
				
		}
	}

}
