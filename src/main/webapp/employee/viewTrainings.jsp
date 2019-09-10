<!DOCTYPE html>
<%@page import="java.time.LocalDate"%>
<%@page
	import="com.virtusa.training.dao.implementations.TraineeInfoDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.TraineeInfoDao"%>
<%@page import="com.virtusa.training.models.TraineeInfo"%>
<%@page import="com.virtusa.training.models.CourseTrainer"%>
<%@page
	import="com.virtusa.training.dao.implementations.CourseTrainerDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.CourseTrainerDao"%>
<%@page import="com.virtusa.training.dao.implementations.LocationDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.LocationDao"%>
<%@page import="com.virtusa.training.dao.implementations.ScheduleDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.ScheduleDao"%>
<%@page import="com.virtusa.training.models.Schedule"%>
<%@page import="com.virtusa.training.models.Course"%>
<%@page import="com.virtusa.training.dao.implementations.CourseDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.CourseDao"%>
<%@ page errorPage="../errorPage.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
<script src="../js/jquery-1.10.2.js"></script>

</head>
<!-- ------------------------------BODY--------------------------------------- -->
<body>

	<div id="new-projects"></div>
	<div id="error"></div>
	<jsp:include page="employeeHeader.html"></jsp:include>



<section class="site-section">

		<div class="container">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Course Name</th>
						<th scope="col">Start date</th>
						<th scope="col">End date</th>
						<th scope="col">Location</th>
						<th scope="col">Enroll</th>
					</tr>
				</thead>
				<tbody>
					<%
						ScheduleDao scheduleDao=new ScheduleDaoImpl();
														            CourseDao courseDao=new CourseDaoImpl();
														            LocationDao locationDao=new LocationDaoImpl();
														            String empId=(String)session.getAttribute("employeeId");
														            int eId=Integer.parseInt(empId);
					%>
					<form method="post" action="viewTrainings.jsp"
						name="viewTrainingList">
						<%
							for(Schedule schedule:scheduleDao.viewTrainingsNotEnrolled(eId)){
						          		if(schedule.getFromDate().compareTo(java.sql.Date.valueOf(LocalDate.now()))>0){
						%>
					
					<tr>
						<td><input type="hidden" name="scheduleId"
							value="<%=schedule.getScheduleId()%>"><%=courseDao.viewCourseById(schedule.getCourseId()).getCourseName()%></td>
						<td><%=schedule.getFromDate()%></td>
						<td><%=schedule.getToDate()%></td>
						<td><%=locationDao.viewLocationById(schedule.getLocationId()).getLocationName()%></td>

						<td><input type="submit" name="submit" value="Enroll"
							class="btn btn-info"></td>

					</tr>
					</form>
					<%
						}
					            }
					%>
				</tbody>
			</table>
		</div>
	</section>
<%
	if((request.getParameter("submit")!=null) && request.getParameter("submit").equals("Enroll")){
          		
          		TraineeInfoDao traineeInfoDao = new TraineeInfoDaoImpl();
              	TraineeInfo traineeInfo =new TraineeInfo();
              	 
              	traineeInfo.setEmpId(eId);
              	traineeInfo.setStatus("pending");
              	traineeInfo.setScheduleId(Integer.parseInt(request.getParameter("scheduleId")));
              	int count=traineeInfoDao.addTraineeInfo(traineeInfo);
              	if(count>0){
              		out.println("Enrolled Successfully");
              	}
              	else{
              			out.println("Failed to enroll...!!!");
                
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