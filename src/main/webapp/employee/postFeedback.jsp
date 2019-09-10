<%@page import="com.virtusa.training.dao.implementations.CourseDaoImpl"%>
<%@page import="com.virtusa.training.dao.implementations.ScheduleDaoImpl"%>
<%@page import="com.virtusa.training.dao.implementations.TraineeInfoDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.TraineeInfoDao"%>
<%@ page errorPage="../errorPage.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<script src="../js/jquery-1.10.2.js"></script>
  </head>
  <body>
 
 <jsp:include page="employeeHeader.html"></jsp:include>
 <center>

    <div class="">
      <div class="container">
        <div class="">
          <div class="col-md-8 mb-4">

            
			
            <form method="post" action="../PostFeedbackServlet" class="p-5">
              
              <h2 class="h4 text-black mb-5">Feedback</h2> 
              <select name="scheduleIdd" class="form-control">
              <%
              	String empId=(String)session.getAttribute("employeeId");
                int eId=Integer.parseInt(empId);
                int courseId=0;
              %>
  			<option>Select Course</option>
  			<%
  				TraineeInfoDao dao=new TraineeInfoDaoImpl();
  				for(int scheduleId:dao.viewScheduleIdByEmpIdStatus(eId))
  			 	{
  			  		courseId=new ScheduleDaoImpl().viewScheduleCourseById(scheduleId);
  			%>
            	 <option  class="dropdown" value="<%=scheduleId%>"><%=new CourseDaoImpl().viewCourseById(courseId).getCourseName()%></option>
           	  <%
           	  	}
           	  %>
		</select>	
		<div class="row form-group">
                            <div class="col-md-12">
                            <br>
                                <textarea name="description" id="fname" cols="30" rows="7" class="form-control" placeholder="Write your feedback here..."></textarea>
                            </div>
                        </div>	
			 
			    
              <div class="row form-group">
                <div class="col-md-12">
                  <input type="submit" name="submit" value="Post Feedback" class="btn btn-info btn-md text-white">
                </div>
              </div>
			<div id="result"></div>
  
            </form>
            
          </div>
        </div>
      </div>
    </div>
</center>

 	 <div id="foot-projects">
 	 <jsp:include page="employeeFooter.html"></jsp:include>
 	 </div>

  <script src="../js/jquery-3.3.1.min.js"></script>
  <script src="../js/jquery-migrate-3.0.1.min.js"></script>
  <script src="../js/jquery-ui.js"></script>
  <script src="../js/popper.min.js"></script>
  <script src="../js/bootstrap.min.js"></script>
  <script src="../js/owl.carousel.min.js"></script>
  <script src="../js/jquery.stellar.min.js"></script>
  <script src="../js/jquery.countdown.min.js"></script>
  <script src="../js/jquery.magnific-popup.min.js"></script>
  <script src="../js/bootstrap-datepicker.min.js"></script>
  <script src="../js/aos.js"></script>

  <script src="../js/main.js"></script>
    
  </body>
</html>