package com.virtusa.training.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtusa.training.dao.implementations.LocationDaoImpl;
import com.virtusa.training.dao.interfaces.LocationDao;

/**
 * Servlet implementation class AddLocationServlet
 */
public class AddLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLocationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		if((request.getParameter("submit")!= null) && request.getParameter("submit").equals("AddLocation")){
			LocationDao dao=new LocationDaoImpl();
			try {
				if(dao.addLocation(request.getParameter("locationName"))){
					out.println("<script type=\"text/javascript\">");
	                 out.println("alert('Location Added successfully');");
	                 out.println("location='TA/viewLocation.jsp';");
	                 out.println("</script>");		
				}
				else{
					out.println("<script type=\"text/javascript\">");
	                 out.println("alert('Failed to add location.Try again..!!!!');");
	                 out.println("location='TA/addLocation.jsp';");
	                 out.println("</script>");		
				}
			} catch (SQLException e) {
				response.sendRedirect("errorPage.jsp");
				e.printStackTrace();
			}
			}
	}

}
