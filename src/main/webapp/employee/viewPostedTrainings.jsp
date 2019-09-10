<%@page import="com.virtusa.training.dao.implementations.CourseTrainerDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.CourseTrainerDao"%>
<%@page import="com.virtusa.training.dao.implementations.CourseDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.CourseDao"%>
<%@page import="com.virtusa.training.models.Course"%>
<%@ page errorPage="../errorPage.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Posted Training</title>
</head>
<body>
<jsp:include page="employeeHeader.html"></jsp:include>
<section class="site-section">
    <div class="container">
      <table class="table table-striped">
        <thead>
          <tr>
            <th class="text-center" scope="col">Course Id</th>
            <th class="text-center" scope="col">Course Name</th>
            <th class="text-center" scope="col">Description</th>
            </tr>
        </thead>
        <tbody>
<%
	String trainerId=(String)session.getAttribute("employeeId");
int tId=Integer.parseInt(trainerId);
CourseDao courseDao=new CourseDaoImpl();
Course course=null;
CourseTrainerDao courseTrainerDao=new CourseTrainerDaoImpl();
for(int courseId:courseTrainerDao.viewPostedByTrainerId(tId))
{
	course=courseDao.viewCourseById(courseId);
%> 
<form name="CourseList" method="post" action="viewPostedTrainings.jsp">
			<tr>
			
			<td class="text-center"><%=courseId %></td>
			<td class="text-center"><%=course.getCourseName() %></td>
			<td class="text-center"><%=course.getDescription() %></td>
			</tr>
</form>
			<% 	
	}
%>
        </tbody>
      </table>
    </div>
  </section>
  
<jsp:include page="employeeFooter.html"></jsp:include>
</body>
</html>