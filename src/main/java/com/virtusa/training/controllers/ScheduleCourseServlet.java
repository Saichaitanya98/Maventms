package com.virtusa.training.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.virtusa.training.dao.implementations.ScheduleDaoImpl;
import com.virtusa.training.dao.interfaces.ScheduleDao;
import com.virtusa.training.models.Schedule;

/**
 * Servlet implementation class ScheduleCourseServlet
 */
public class ScheduleCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		if(request.getParameter("submit")!=null && request.getParameter("submit").equals("confirm")){
			ScheduleDao dao=new ScheduleDaoImpl();
			Schedule schedule=new Schedule();
			int count;
			String fDate=request.getParameter("fromDate");
			java.sql.Date fromDate=Date.valueOf(fDate);
			
			String tDate=request.getParameter("toDate");
			 java.sql.Date toDate=Date.valueOf(tDate); 
			
			schedule.setNumOfSeats(Integer.parseInt(request.getParameter("numOfSeats")));
			schedule.setFromDate(fromDate);
			schedule.setToDate(toDate);
			schedule.setLocationId(Integer.parseInt(request.getParameter("locationId")));
			schedule.setCourseId(Integer.parseInt(request.getParameter("courseId")));
			try {
				count=dao.addSchedule(schedule);
				if(count>0){
					out.println("<script type=\"text/javascript\">");
	                 out.println("alert('Scheduled Successfully');");
	                 out.println("location='TA/viewCourse.jsp';");
	                 out.println("</script>");		
					
				}
				else {
					out.println("<script type=\"text/javascript\">");
	                 out.println("alert('Failed to schedule');");
	                 out.println("location='TA/scheduleCourse.jsp';");
	                 out.println("</script>");		
					 out.println("Failed to schedule");
				}
			} catch (SQLException e) {
				response.sendRedirect("errorPage.jsp");
				//e.printStackTrace();
			}
			
		
		}
	}
}
