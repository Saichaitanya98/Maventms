<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.virtusa.training.models.CourseTrainer"%>
<%@page import="com.virtusa.training.dao.interfaces.CourseTrainerDao"%>
<%@page
	import="com.virtusa.training.dao.implementations.CourseTrainerDaoImpl"%>
<%@page import="com.virtusa.training.models.Course"%>
<%@page import="com.virtusa.training.dao.interfaces.CourseDao"%>
<%@page import="com.virtusa.training.dao.implementations.CourseDaoImpl"%>
<%@ page errorPage="../errorPage.jsp" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:include page="PMHeader.html"></jsp:include>
	<section class="site-section">
		<center>
			<div class="w-50 text-center">
				<form action="../PostTrainingsServlet" method="post">
					<%-- <span style="color:green;font-size:20px"><%=(request.getAttribute("message") == null)? "" : request.getAttribute("message") %></span><br> --%>

					<input type="hidden" name="roleName" value="PM"> <label class="text-black h3 mb-0">Course</label>
					<select name="courseId" class="form-control">
						<%
							String trainerId = (String) session.getAttribute("employeeId");
							int tId = Integer.parseInt(trainerId);
							CourseDao dao = new CourseDaoImpl();
						%>
						<option>Select Course</option>
						<%
							for (Course course : dao.viewCourseTrainerById(tId)) {
						%>
						<option value="<%=course.getCourseId()%>"><%=course.getCourseName()%></option>

						<%
							}
						%>
					</select> <br>
					<br>
					<div class="justify-content-center">
						<input class="btn btn-info" type="submit" value="Post Training"
							name="submit" /> <input class="btn btn-warning" type="submit"
							value="Others" name="submit" />

					</div>

				</form>


			</div>
		</center>
	</section>

	<div id="foot-projects" class="fixed-bottom">
		<jsp:include page="PMFooter.html"></jsp:include>
	</div>
</body>
</html>