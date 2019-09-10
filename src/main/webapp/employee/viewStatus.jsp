<%@page import="com.virtusa.training.dao.implementations.CourseDaoImpl"%>
<%@page import="com.virtusa.training.dao.implementations.ScheduleDaoImpl"%>
<%@page import="com.virtusa.training.models.TraineeInfo"%>
<%@page
	import="com.virtusa.training.dao.implementations.TraineeInfoDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.TraineeInfoDao"%>
<%@ page errorPage="../errorPage.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Status</title>
</head>
<body>
<jsp:include page="employeeHeader.html"></jsp:include>
	<section class="site-section">

		<div class="container">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Course Name</th>
						<th scope="col">Status</th>
						<th scope="col">Cancel Training</th>
					</tr>
				</thead>
				<tbody>
					<%
						TraineeInfoDao traineeInfoDao = new TraineeInfoDaoImpl();
															String empId = (String) session.getAttribute("employeeId");
															int eId = Integer.parseInt(empId);
					%>
					<form method="post" action="viewStatus.jsp" name="viewStatus">
						<%
							for (TraineeInfo traineeInfo : traineeInfoDao.viewStatus(eId)) {
						%>

						<tr>
							<input type="hidden" name="scheduleId" value="<%=traineeInfo.getScheduleId()%>">
							<td><%=new CourseDaoImpl().viewCourseById(new ScheduleDaoImpl().viewScheduleCourseById(traineeInfo.getScheduleId())).getCourseName()%></td>
							<td><%=traineeInfo.getStatus()%></td>
							<td><input type="submit" name="submit" value="Cancel"
								class="btn btn-info"></td>

						</tr>
					</form>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</section>
	
	
<%
if((request.getParameter("submit")!=null) && request.getParameter("submit").equals("Cancel")){
		
	//TraineeInfoDao traineeInfoDao = new TraineeInfoImpl();
  	//TraineeInfo traineeInfo =new TraineeInfo();
  	if(traineeInfoDao.deleteTraineeInfoByIds(Integer.parseInt(request.getParameter("scheduleId")),eId))
  		out.println("you have cancelled the nomination");
  	else
  		out.println("Failed to cancel");
  	 
}
%>	
<div class="fixed-bottom">
<jsp:include page="employeeFooter.html"></jsp:include>
</div>
</body>
</html>