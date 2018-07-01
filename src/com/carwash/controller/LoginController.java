package com.carwash.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carwash.business.Customer;
import com.carwash.business.Order;
import com.carwash.business.Password;
import com.carwash.dao.CustomerDao;
import com.carwash.dao.LoginDao;
import com.carwash.dao.OwnerDao;
import com.carwash.util.MailUtilGmail;
import com.carwash.util.PasswordUtil;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
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
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		ServletContext sc = getServletContext();
		String url = "";
		String msg = "";
		a: if (action.equals("register")) {

			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String phone = request.getParameter("phone");
			int doorno = Integer.parseInt(request.getParameter("doorno"));
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String province = request.getParameter("province");
			String postalcode = request.getParameter("postalcode");
			String role = request.getParameter("role");
			try {
				PasswordUtil.checkPasswordStrength(password);// greater than
																// 4
																// characters
			} catch (Exception ex) {
				msg = "Password too weak";
				request.setAttribute("msg", msg);
				url = "/register.jsp";
				break a;
				/*
				 * sc.getRequestDispatcher("/register.jsp").forward(request,
				 * response); processRequest(request, response);
				 */
			}
			String passwordhash = null;
			String salt = PasswordUtil.getSalt();
			try {
				passwordhash = PasswordUtil.hashAndSaltPassword(password, salt);
			} catch (NoSuchAlgorithmException ex) {
				ex.printStackTrace();
			}

			Customer cust = new Customer();
			cust.setEmail(email);
			cust.setPassword(passwordhash);
			cust.setFirstName(fname);
			cust.setLastName(lname);
			cust.setPhoneNumber(phone);
			cust.setDoorNo(doorno);
			cust.setStreet(street);
			cust.setCity(city);
			cust.setProvince(province);
			cust.setPostalCode(postalcode);
			cust.setRole(role);
			int i = LoginDao.insertUser(cust);
			if (i != 1) {
				msg = "User already exist! Please register with a different Email";
				request.setAttribute("msg", msg);
				url = "/register.jsp";
				break a;

			} else {
				LoginDao.insertUSerPassword(email, passwordhash, salt);
			}
			String to = email;
			String from = "carwashwindsor@gmail.com";
			String subject = "Welcome to our family!!";
			String body = "Dear " + cust.getFirstName() + ",\n\n"
					+ "Thank you for registering to our car wash services.\n Please login with your email and password to enjoy our services"
					+ "\n\n Regards,\n CarWash Team";
			boolean isBodyHTML = false;
			try {
				MailUtilGmail.sendMail(cust.getEmail(), from, subject, body, isBodyHTML);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (cust.getRole().equals("owner")) {
				session.setAttribute("tempOwner", cust.getEmail());
				url = "/setprices.jsp";
			} else if (cust.getRole().equals("customer")) {
				url = "/index.jsp";
			}
			msg = "Welcome! " + cust.getFirstName() + " ..please login to continue!!";
			request.setAttribute("message", msg);
		} else if (action.equals("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Password pass = new Password();
			pass = LoginDao.getPassword(email);
			String passwordhash = null;
			try {
				String salt = pass.getSalt();
				passwordhash = PasswordUtil.hashPassword(password + salt);
				log("passwordHash" + passwordhash);
			} catch (NoSuchAlgorithmException ex) {
				ex.printStackTrace();
			}

			Boolean value = LoginDao.validateUser(passwordhash, email);
			String checkRole = LoginDao.checkRole(email);
			if (value) {
				session.setAttribute("loginEmail", email);
				if (checkRole.equals("customer")) {
					request.setAttribute("allOwners", CustomerDao.getAllOwners());
					session.setAttribute("role", "customer");
					url = "/customer.jsp";
				} else if (checkRole.equals("owner")) {
					request.setAttribute("bookings", OwnerDao.getAllBookings(email));
					session.setAttribute("role", "owner");
					url = "/owner.jsp";
				}
			} else {
				msg = "Invalid Credentials";
				request.setAttribute("message", msg);
				url = "/index.jsp";
			}

		}else if (action.equals("logout")) {
			request.getSession().invalidate();
			url = "/index.jsp";
		} else if (action.equals("mail")) {
			String subject = request.getParameter("subject");
			String body = request.getParameter("body");
			String from = "carwashwindsor@gmail.com";
			String to="palanki@uwindsor.ca";	//change to email to admin email
			boolean isBodyHTML = false;
			try {				
				MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String role = (String) session.getAttribute("role");
			if (role.equals("customer")) {
				request.setAttribute("allOwners", CustomerDao.getAllOwners());
				url = "/customer.jsp";
			} else if (role.equals("owner")) {
				request.setAttribute("bookings", OwnerDao.getAllBookings((String) session.getAttribute("loginEmail")));
				url = "/owner.jsp";
			}

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
