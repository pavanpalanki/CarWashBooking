package com.carwash.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carwash.business.*;
import com.carwash.dao.*;
import com.carwash.util.*;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerController() {
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
		String url = "";
		String availability = "";
		String action = request.getParameter("action");

		if (action.equals("checkAvailability")) {
			String orderDate = request.getParameter("date");//formatDate(request.getParameter("date"));
			String slotId = request.getParameter("time");
			String washId = request.getParameter("washType");
			String owner = request.getParameter("owner");
			Date order = new Date();
			// declared if the customer wants to book the order after confirming
			// to book
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date today = null;
			try {
				today = dateFormatter.parse(dateFormatter.format(new Date()));
				order = dateFormatter.parse(orderDate);
				System.out.println(today + " " + order);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (order.after(today)) {
				ArrayList actialParams = new ArrayList();
				actialParams.add(owner);
				actialParams.add((String) session.getAttribute("loginEmail"));
				actialParams.add(washId);
				actialParams.add(slotId);
				actialParams.add(orderDate);

				try {
					if (!CustomerDao.checkAvailability(orderDate, slotId, washId, owner)) {
						availability = "false";
						url = "/customer.jsp";
					} else {
						availability = "true";
						// used only to display customers seleted order for
						// review
						ArrayList displayParams = CustomerDao.SetOrderParameters(owner,
								(String) session.getAttribute("loginEmail"), washId, slotId, orderDate, actialParams);
						session.setAttribute("actualOrder", actialParams);
						session.setAttribute("displayOrder", displayParams);
						url = "/reviewbooking.jsp";
					}
					request.setAttribute("availability", availability);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				availability = "pastdate";
				request.setAttribute("availability", availability);
				url = "/customer.jsp";
			}
		} else if (action.equals("quickBook")) {
			url = "/customer.jsp";
		} else if (action.equals("viewBookings")) {
			ArrayList<Order> bookings = new ArrayList<Order>();
			bookings = CustomerDao.getAllBookings((String) session.getAttribute("loginEmail"));
			request.setAttribute("bookings", bookings);
			url = "/viewbookings.jsp";
		} else if (action.equals("cancel")) {
			String bookingID = request.getParameter("bookingID");
			OrderDao.emailCancellation(bookingID);
			OrderDao.cancelBooking(bookingID);
			request.setAttribute("bookingID", bookingID);
			url = "/cancelled.jsp";
		} else if (action.equals("contactUs")) {
			url = "/customer.jsp";
		}else if (action.equals("displayCompanies")) {
			ArrayList<CarWashes> companies = new ArrayList<CarWashes>();
			companies = CustomerDao.getAllCarWashInfo();
			request.setAttribute("companies", companies);
			url = "/companies.jsp";
		}else if (action.equals("aboutus")) {
			url = "/aboutus.jsp";
		}
		request.setAttribute("allOwners", CustomerDao.getAllOwners());
		sc.getRequestDispatcher(url).forward(request, response);
		processRequest(request, response);
	}

	private String formatDate(String date) {
		// TODO Auto-generated method stub	
		String dateParts[] = date.split("-");
		String orderDate = dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
		return orderDate;
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
