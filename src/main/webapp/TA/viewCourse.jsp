<%@page import="com.virtusa.training.models.Course"%>
<%@page import="com.virtusa.training.dao.interfaces.CourseDao"%>
<%@page import="com.virtusa.training.dao.implementations.CourseDaoImpl"%>
<%@ page errorPage="../errorPage.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Course</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/aos.css">

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">
</head>
<body>
<jsp:include page="TAHeader.html"></jsp:include>
<section class="site-section">
    <div class="container">
      <table class="table table-striped">
        <thead>
          <tr>
            <th class="text-center" scope="col">Course Id</th>
            <th class="text-center" scope="col">Course Name</th>
            <th class="text-center" scope="col">Description</th>
            <th scope="col">Schedule</th>
            </tr>
        </thead>
        <tbody>
<%
	CourseDao dao=new CourseDaoImpl();
for(Course course:dao.viewNotScheduled())
{
%> 
<form name="CourseList" method="post" action="scheduleCourse.jsp">
			<tr>
			<td><%=course.getCourseId() %>
			<input type="hidden" class="text-center form-control" name="courseId" value="<%= course.getCourseId() %>" readonly></td>
			<td><input type="text" class="text-center form-control" name="courseName" value="<%= course.getCourseName() %>" readonly></td>
			<%-- <td><a href="viewNotScheduled.jsp?&courseId=<%= course.getCourseId() %>"><%=course.getCourseName() %></a></td> --%>
			<td><input class="text-center form-control" type="text" name="description" value="<%= course.getDescription() %>" readonly></td>
			<%-- <td><%=course.getDescription() %></td> --%>
			<td><input type="submit" class="btn btn-info" name="submit" value="Schedule"></td>
			<%-- <td><%=course.getTrainerId() %></td> --%>
			</tr>
</form>
			<% 	
	}
%>
  
        </tbody>
      </table>
    </div>
  </section>
  
  
  <%-- <%
  if(request.getParameter("submit")!=null && request.getParameter("submit").equals("Schedule")){
	  response.sendRedirect("scheduleCourse.jsp");
  }
  %> --%>
  <jsp:include page="TAFooter.html"></jsp:include>
</body>
</html>