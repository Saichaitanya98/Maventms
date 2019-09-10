package com.virtusa.training.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtusa.training.dao.implementations.EmployeeDaoImpl;
import com.virtusa.training.dao.interfaces.EmployeeDao;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		EmployeeDao dao=EmployeeDaoImpl.getInstance();
		try {
			String pageName = dao.loginCheck(Integer.parseInt(request.getParameter("empId")),
					request.getParameter("password"));
			if (!pageName.equals("Invalid credentials")) {
				session.setAttribute("employeeId", request.getParameter("empId"));
				response.sendRedirect(pageName);
			} else {
				request.setAttribute("message", "Invalid Credentials");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			response.sendRedirect("errorPage.jsp");
			// e.getMessage();
		} catch (NullPointerException np) {
			response.sendRedirect("errorPage.jsp");
			// response.sendRedirect("errorPage.jsp");
		}

	}

}
