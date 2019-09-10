<!DOCTYPE html>
<%@page
	import="com.virtusa.training.dao.implementations.ScheduleDaoImpl"%>
<%@page import="com.virtusa.training.dao.implementations.CourseDaoImpl"%>
<%@page
	import="com.virtusa.training.dao.implementations.EmployeeDaoImpl"%>
<%@page import="com.virtusa.training.models.TraineeInfo"%>
<%@page
	import="com.virtusa.training.dao.implementations.TraineeInfoDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.TraineeInfoDao"%>
<%@ page errorPage="../errorPage.jsp" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
<script src="../js/jquery-1.10.2.js"></script>
<title>Request List</title>
</head>
<!-- ------------------------------BODY--------------------------------------- -->
<body>

	<div id="new-projects"></div>
	<div id="error"></div>
	<jsp:include page="PMHeader.html"></jsp:include>


	

					<section class="site-section">

		<div class="container">
			<table class="table table-striped">
				<thead>
					<tr>
										<th scope="col">Employee Id</th>
										<th scope="col">Employee Name</th>
										<th scope="col">Course Name</th>
										<th scope="col">Start Date</th>
										<th scope="col">End Date</th>
										<th scope="col">Approve</th>
										<th scope="col">Reject</th>
									</tr>
								</thead>
								<tbody>
			  <%
              	TraineeInfoDao traineeInfoDao=new TraineeInfoDaoImpl();
				String empId=(String)session.getAttribute("employeeId");
				int eId=Integer.parseInt(empId);
              %>
				<form method="post" action="requestList.jsp" name="requestList">
				<%
			 	for(TraineeInfo traineeInfo:traineeInfoDao.viewTraineeInfoByManagerId(eId))
			 	{
			 	 	 int courseId=new ScheduleDaoImpl().viewScheduleCourseById(traineeInfo.getScheduleId());
			 	 	 String courseName=new CourseDaoImpl().viewCourseById(courseId).getCourseName();
			 	 	 if(traineeInfo.getStatus().equals("pending")) {
			 	%>
						<tr>
							<input type="hidden" name="scheduleId" value="<%=traineeInfo.getScheduleId()%>">
							<input type="hidden" name="employeeIdd" value="<%=traineeInfo.getEmpId()%>">
							<td scope="row"><%=traineeInfo.getEmpId()%></td>
							<td scope="row"><%=new EmployeeDaoImpl().viewEmployeeNameById(traineeInfo.getEmpId())%></td>
							<td scope="row"><%=courseName%></td>
							<td scope="row"><%=new ScheduleDaoImpl().viewScheduleById(traineeInfo.getScheduleId()).getFromDate()%></td>
							<td scope="row"><%=new ScheduleDaoImpl().viewScheduleById(traineeInfo.getScheduleId()).getToDate()%></td>
							<td><input type="submit" name="submit" value="Approve" class="btn btn-primary"></td>
							<td><input type="submit" name="submit" value="Reject" class="btn btn-primary"></td>
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
if((request.getParameter("submit")!=null) && (request.getParameter("submit").equals("Approve") || request.getParameter("submit").equals("Reject"))){
	String status=request.getParameter("submit");
	if(status.equals("Approve")){
		status="Approved";
	}
	else{
		status="Rejected";
	}
	TraineeInfo traineeInfo = new TraineeInfo();
	traineeInfo.setEmpId(Integer.parseInt(request.getParameter("employeeIdd")));
	traineeInfo.setScheduleId(Integer.parseInt(request.getParameter("scheduleId")));
	traineeInfo.setStatus(status);
	if(traineeInfoDao.updateStatus(traineeInfo))
		out.println(status+" successfully");
	else
		out.println("Failed to  "+status);
}
%>
				

	<div id="foot-projects" class="fixed-bottom">
		<jsp:include page="PMFooter.html"></jsp:include>
	</div>


	<script src="../js/bootstrap.min.js"></script>
  	<script src="../js/aos.js"></script>
  	<script src="../js/main.js"></script>

</body>
</html>