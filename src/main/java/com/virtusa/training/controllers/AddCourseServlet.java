package com.virtusa.training.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtusa.training.dao.implementations.CourseDaoImpl;
import com.virtusa.training.dao.interfaces.CourseDao;
import com.virtusa.training.models.Course;

/**
 * Servlet implementation class PostTrainingsServlet
 */
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCourseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if (request.getParameter("submit") != null && request.getParameter("submit").equals("confirm")) {
			Course course = new Course();
			CourseDao dao = new CourseDaoImpl();

			
			course.setCourseName(request.getParameter("courseName"));
			course.setDescription(request.getParameter("description"));

			int count;
			try {
				count = dao.addCourse(course);
				String roleName = request.getParameter("roleName") + "/viewCourse.jsp";
				if (count > 0) {
					out.println("<script type=\"text/javascript\">");
					  out.println("alert('Course Added Successfully');");
					  out.println("location='"+roleName+"'"); 
					  out.println("</script>"); 
					/*
					 * request.setAttribute("message", "Course Added Successfully");
					 * request.getRequestDispatcher(roleName).include(request, response);
					 */
				} 
				else 
				{
					  out.println("<script type=\"text/javascript\">");
					  out.println("alert('Failed to add Course');");
					  out.println("location='"+roleName+"'"); out.println("</script>");
					/*
					 * request.setAttribute("message", "Failed to add Course.Try Again...!!!");
					 * request.getRequestDispatcher(roleName).include(request, response);
					 */
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("errorPage.jsp");
				///e.printStackTrace();
			}

		}

	}
}