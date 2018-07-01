package com.carwash.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carwash.dao.CustomerDao;
import com.carwash.dao.OrderDao;
import com.carwash.util.MailUtilGmail;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		ServletContext sc = getServletContext();
		String url = "/customer.jsp";
		String action = request.getParameter("action");
		if (action.equals("book")) {
			ArrayList<String> actialParams = (ArrayList<String>) session.getAttribute("actualOrder");
			ArrayList email = (ArrayList) session.getAttribute("displayOrder");
			OrderDao.insertOrder(actialParams);
			try {
				String subject = "Booking Confirmation!!";
				String body = "Hello, <br/> Your order is confirmed. Please find the order info below <br/><br/> "+
						"COMPANY: "+email.get(0)+"<br/>WASH: "+email.get(1)+"<br/>DATE: "+email.get(2)+"<br/>TIME: "+email.get(3)+"<br/>PRICE: "+email.get(4);
				String from = "carwashwindsor@gmail.com";
				String to = (String) session.getAttribute("loginEmail");
				boolean isBodyHTML = true;
				MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = "/customer.jsp";
		}

		else if (action.equals("rebook")) {
			url = "/customer.jsp";
		}
		request.setAttribute("allOwners", CustomerDao.getAllOwners());
		sc.getRequestDispatcher(url).forward(request, response);
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet CustomerController</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet CustomerController at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

}
