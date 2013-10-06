package com.ebayplus.webapp.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebay.sdk.eBayAccount;
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
				sb.append("<td><input type='text' " + "class='basic'/></td></tr>");
				sb.append("<tr><td>Status:</td>");
				sb.append("<td>" + accountItem.getAccountStatus().toString() + "</td>");
				
				if(accountItem.getAccountStatus() == AccountStatusType.WAITING_FOR_ACTIVATION) {
					sb.append("<td><a href='credentials.jsp?accountID="+ account.getAccountID() +"&eBayPlusAccountID="+ accountItem.getAccountID() +"'>Activate</a></td>");
				}
				sb.append("</tr></table></div>");
			}
			sb.append("</div>");
			sb.append("<div class='clear'></div>");
			response.getWriter().print(sb.toString());
		
		} else if(contentID.equals("addAccountDialog")) {
			String buffer = "<div id='add_account_dialog'></div>";
			response.getWriter().print(buffer);
		}
	}

}
