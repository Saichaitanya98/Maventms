package com.virtusa.training.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtusa.training.dao.implementations.FeedbackDaoImpl;
import com.virtusa.training.dao.interfaces.FeedbackDao;
import com.virtusa.training.models.Feedback;

/**
 * Servlet implementation class PostFeedbackServlet
 */
public class PostFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostFeedbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		String empId=(String)session.getAttribute("employeeId");
        int eId=Integer.parseInt(empId);
		if(request.getParameter("submit")!=null && request.getParameter("submit").equals("Post Feedback"))
		{
			Feedback feedback=new Feedback();
			FeedbackDao feedbackDao=new FeedbackDaoImpl();
			feedback.setDescription(request.getParameter("description"));
			feedback.setEmpId(eId);
			feedback.setScheduleId(Integer.parseInt(request.getParameter("scheduleIdd")));
			int count;
			try {
				count = feedbackDao.addFeedback(feedback);
				if(count>0)
				{
					out.println("<script type=\"text/javascript\">");
	                 out.println("alert('Posted feedback successfully');");
	                 out.println("location='employee/postFeedback.jsp';");
	                 out.println("</script>");		
					
				}
				else
				{
					out.println("<script type=\"text/javascript\">");
	                 out.println("alert('Failed to post feedback');");
	                 out.println("location='employee/postFeedback.jsp';");
	                 out.println("</script>");		
					
				}
			} catch (SQLException e) {
				response.sendRedirect("errorPage.jsp");
				//e.printStackTrace();
			}
			
		}
	}

}
