<%@page import="com.virtusa.training.dao.implementations.LocationDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.LocationDao"%>
<%@page import="com.virtusa.training.dao.implementations.CourseDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.CourseDao"%>
<%@page import="com.virtusa.training.models.Schedule"%>
<%@page import="com.virtusa.training.dao.implementations.ScheduleDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.ScheduleDao"%>
<%@ page errorPage="../errorPage.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Schedule History</title>
</head>
<body>
<jsp:include page="TAHeader.html"></jsp:include>
<section class="site-section">
    <div class="container">
      <table class="table table-striped">
        <thead>
          <tr>
            <th class="text-center" scope="col">Schedule Id</th>
            <th class="text-center" scope="col">Start Date</th>
            <th class="text-center" scope="col">End Date</th>
            <th class="text-center" scope="col">Course Name</th>
            <th class="text-center" scope="col">location Name</th>
            </tr>
        </thead>
        <tbody>
<%
	ScheduleDao dao=new ScheduleDaoImpl();
CourseDao courseDao=new CourseDaoImpl();
LocationDao locationDao=new LocationDaoImpl();
for(Schedule schedule:dao.viewSchedule())
{
%> 
<form name="ScheduleList" method="post" action="viewScheduleHistory.jsp">
			<tr>
			<td class="text-center"><%= schedule.getScheduleId() %></td>
			<td class="text-center"><%= schedule.getFromDate() %></td>
			<td class="text-center"><%= schedule.getToDate() %></td>
			<td class="text-center"><%= courseDao.viewCourseById(schedule.getCourseId()).getCourseName() %></td>
			<td class="text-center"><%= locationDao.viewLocationById(schedule.getLocationId()).getLocationName() %></td>
			</tr>
</form>
			<% 	
	}
%>
  
        </tbody>
      </table>
    </div>
  </section>
   <div id="foot-projects" class="fixed-bottom">
	 	<jsp:include page="TAFooter.html"></jsp:include>
	 </div>


</body>
</html>