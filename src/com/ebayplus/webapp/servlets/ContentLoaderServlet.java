package com.ebayplus.webapp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContentLoaderServlet
 */
public class ContentLoaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContentLoaderServlet() {
		super();
		// TODO Auto-generated constructor stub
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
			StringBuilder sb = new StringBuilder();
			sb.append("<div id='account_panel'>");
			sb.append("<div id='add_account_button'>Add Acoount</div></div>");
	        sb.append("<div id='account_items'>");
	        sb.append("<div class='account_item'></div>");
	        sb.append("</div>");
	        response.getWriter().print(sb.toString());
	
		} else if(contentID.equals("addAccountDialog")) {
			String buffer = "<div id='add_account_dialog'></div>";
			response.getWriter().print(buffer);
		}
	}

}
