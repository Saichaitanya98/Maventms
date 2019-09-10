<!DOCTYPE html>
<%@page
	import="com.virtusa.training.dao.implementations.FeedbackDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.FeedbackDao"%>
<%@page import="com.virtusa.training.models.Feedback"%>
<%@page import="com.virtusa.training.dao.implementations.CourseDaoImpl"%>
<%@page
	import="com.virtusa.training.dao.implementations.ScheduleDaoImpl"%>
<%@page
	import="com.virtusa.training.dao.implementations.TraineeInfoDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.TraineeInfoDao"%>
<%@ page errorPage="../errorPage.jsp" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
<script src="../js/jquery-1.10.2.js"></script>

</head>
<!-- ------------------------------BODY--------------------------------------- -->
<body>
	<jsp:include page="employeeHeader.html"></jsp:include>
	<center>
		<section class="">

			<div class="container">
				<div class="row"></div>
			</div>
		</section>
		 
		<form action="#" class="p-5 bg-white">
		<label class="text-black h3 ">View Feedback</label>
			<select name="scheduleIdd" class="w-25 form-control">
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
				<option value="<%=scheduleId%>"><%=new CourseDaoImpl().viewCourseById(courseId).getCourseName()%></option>
				<%
           	  	}
           	  %>
			</select><br>
			<br> <input type="submit" class="btn btn-info" name="submit"
				value="View Feedback">
		</form>
	</center>
	<%
      	if((request.getParameter("submit")!=null) && (request.getParameter("submit").equals("View Feedback"))){
      	  FeedbackDao feedbackDao=new FeedbackDaoImpl();
      	  for(String feedback:feedbackDao.viewFeedback(eId, Integer.parseInt(request.getParameter("scheduleIdd")))){
      		out.println(feedback);
      		%>
      		<br>
      		<%
      	  }
        }
      %>
	<div id="foot-projects" class="fixed-bottom">

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