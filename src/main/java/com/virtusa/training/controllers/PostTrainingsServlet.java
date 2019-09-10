package com.virtusa.training.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virtusa.training.dao.implementations.CourseTrainerDaoImpl;
import com.virtusa.training.dao.interfaces.CourseTrainerDao;
import com.virtusa.training.models.CourseTrainer;

/**
 * Servlet implementation class ViewCourseServlet
 */
public class PostTrainingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostTrainingsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		String trainerId=(String)session.getAttribute(("employeeId"));
		String roleName=request.getParameter("roleName")+"/viewCourse.jsp";
		String otherRoleName=request.getParameter("roleName")+"/postTrainings.jsp";
		if(request.getParameter("submit")!=null && request.getParameter("submit").equals("Post Training")){
			CourseTrainerDao courseTrainerDao=new CourseTrainerDaoImpl();
			CourseTrainer courseTrainer=new CourseTrainer();
			trainerId=(String)session.getAttribute("employeeId");
			courseTrainer.setCourseId(Integer.parseInt(request.getParameter("courseId")));
			courseTrainer.setTrainerId(Integer.parseInt(trainerId));
			
			int count;
			try {
				count = courseTrainerDao.addCourseTrainer(courseTrainer);
				
				if(count>0){
					out.println("<script type=\"text/javascript\">");
	                 out.println("alert('Course Posted Successfully');");
	                 out.println("location='"+roleName+"'");
	                 out.println("</script>");		
				}
				else{
					out.println("<script type=\"text/javascript\">");
	                 out.println("alert('Failed to post course');");
	                 out.println("location='"+roleName+"'");
	                 out.println("</script>");		
	                 
				}
			} catch (SQLException e) {
				response.sendRedirect("errorPage.jsp");
				//e.printStackTrace();
			}
			
		}

		if(request.getParameter("submit")!=null && request.getParameter("submit").equals("Others")){
			 response.sendRedirect(otherRoleName);			
	}

}
}
