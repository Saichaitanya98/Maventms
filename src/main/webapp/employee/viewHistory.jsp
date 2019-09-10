<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page import="com.virtusa.training.models.Schedule"%>
<%@page import="com.virtusa.training.dao.interfaces.CourseDao"%>
<%@page import="com.virtusa.training.dao.interfaces.ScheduleDao"%>
<%@page import="com.virtusa.training.dao.implementations.CourseDaoImpl"%>
<%@page import="com.virtusa.training.dao.implementations.ScheduleDaoImpl"%>
<%@page import="com.virtusa.training.models.TraineeInfo"%>
<%@page import="com.virtusa.training.dao.implementations.TraineeInfoDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.TraineeInfoDao"%>
<%@ page errorPage="../errorPage.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>History</title>
</head>
<body>
<jsp:include page="employeeHeader.html"></jsp:include>
<%
	String empId=(String)session.getAttribute("employeeId");
int eId=Integer.parseInt(empId);
TraineeInfoDao traineeInfoDao=new TraineeInfoDaoImpl();
ScheduleDao scheduleDao=new ScheduleDaoImpl();
CourseDao courseDao=new CourseDaoImpl();
int courseId=0;
String courseName=null;
String startDate=null;
String endDate=null;
Schedule schedule=null;
%>
 <section class="site-section">
      <div class="container">
          <table class="table table-striped">
              <thead>
                <tr>
                <th scope="col">Schedule Id</th>
                  <th scope="col">Course Name</th>
                  <th scope="col">Start Date</th>
                  <th scope="col">End Date</th>
                  </tr>
              </thead>
              <tbody>
<%
for(int scheduleId:traineeInfoDao.viewScheduleIdByEmpIdStatus(eId)){
	 courseId=scheduleDao.viewScheduleCourseById(scheduleId);
	 courseName=courseDao.viewCourseById(courseId).getCourseName();
	 schedule=scheduleDao.viewScheduleById(scheduleId);
	 if(schedule.getToDate().compareTo(Date.valueOf(LocalDate.now()))<=0){
	 
	%>
	<tr>
	<td><%= scheduleId %></td>
	<td><%= courseName %></td>
	<td><%= schedule.getFromDate() %></td>
	<td><%= schedule.getToDate() %></td>
	</tr>
	
	<%
}
}
%>
		</tbody>
	</table>
</section>


	<div id="foot-projects" class="fixed-bottom">
	<jsp:include page="employeeFooter.html"></jsp:include>
	</div>


</body>
</html>