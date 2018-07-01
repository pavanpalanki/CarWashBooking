package com.carwash.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carwash.business.*;
import com.carwash.dao.*;

/**
 * Servlet implementation class OwnerController
 */
public class OwnerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OwnerController() {
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
		String email = (String) session.getAttribute("loginEmail");
		String url = "";
		String action = request.getParameter("action");

		if (action.equals("setprice")) {
			float iWash = Float.parseFloat(request.getParameter("iWash"));
			float eWash = Float.parseFloat(request.getParameter("eWash"));
			float fWash = Float.parseFloat(request.getParameter("fWash"));
			ArrayList<Float> prices = new ArrayList<Float>();
			prices.add(iWash);
			prices.add(eWash);
			prices.add(fWash);
			if ((String) session.getAttribute("tempOwner") != null) {
				OwnerDao.setPrices((String) session.getAttribute("tempOwner"), prices);
				url = "/index.jsp";
			}

		} else if (action.equals("viewBookings")) {
			ArrayList<Order> bookings = new ArrayList<Order>();
			bookings = OwnerDao.getAllBookings((String) session.getAttribute("loginEmail"));
			request.setAttribute("bookings", bookings);
			url = "/viewbookings.jsp";
		} else if (action.equals("cancel")) {
			String bookingID = request.getParameter("bookingID");
			OrderDao.emailCancellation(bookingID);
			OrderDao.cancelBooking(bookingID);
			request.setAttribute("bookingID", bookingID);
			ArrayList<Order> bookings = new ArrayList<Order>();
			bookings = OwnerDao.getAllBookings((String) session.getAttribute("loginEmail"));
			request.setAttribute("bookings", bookings);
			url = "/owner.jsp";
		} else if (action.equals("updatePrices")) {
			request.setAttribute("updatePrices", "updatePrices");
			request.setAttribute("oldPrices", OwnerDao.getPrices((String) session.getAttribute("loginEmail")));
			url = "/setprices.jsp";
		} else if (action.equals("updatePrice")) {
			float iWash = Float.parseFloat(request.getParameter("iWash"));
			float eWash = Float.parseFloat(request.getParameter("eWash"));
			float fWash = Float.parseFloat(request.getParameter("fWash"));
			ArrayList<Float> prices = new ArrayList<Float>();
			prices.add(iWash);
			prices.add(eWash);
			prices.add(fWash);
			if ((String) session.getAttribute("loginEmail") != null) {
				OwnerDao.updatePrices((String) session.getAttribute("loginEmail"), prices);
				ArrayList<Order> bookings = OwnerDao.getAllBookings(email);
				request.setAttribute("bookings", bookings);
				url = "/owner.jsp";
			}
		}else if (action.equals("aboutus")) {
			url = "/aboutus.jsp";
		}
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
