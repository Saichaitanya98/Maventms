<%@page import="java.time.LocalDate"%>
<%@page import="com.virtusa.training.models.Location"%>
<%@page import="com.virtusa.training.dao.implementations.LocationDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.LocationDao"%>
<%@page import="com.virtusa.training.dao.implementations.ScheduleDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.ScheduleDao"%>
<%@page import="com.virtusa.training.models.Schedule"%>
<%@page import="java.sql.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page errorPage="../errorPage.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="../js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/aos.css">
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../fonts/flaticon/font/flaticon.css">
<!-- <script>
jQuery(function() {
	  var datepicker = $('#fromDate');

	  if (fromDate.length > 0) {
		  fromDate.fromDate({
	      format: "dd/mm/yyyy",
	      startDate: new Date()
	    });
	  }
	});

</script> -->
</head>
<body>
<jsp:include page="TAHeader.html"></jsp:include>
<center>		  
<div class="">
      <div class="container">
        <div class="">
          <div class="col-md-8 mb-4">

            <form method="post" class="p-5" action="../ScheduleCourseServlet">

		  <div class="row form-group">
		  <div class="col-md-12">
             <label class="text-black" for="courseName" style="float: left;">Course Name</label>
            <input type="hidden" id="courseId" name="courseId" value="<%=request.getParameter("courseId")%>" required placeholder="Enter courseName" class="form-control" readonly />
            
       		<input type="text" id="courseName" name="courseName" value="<%=request.getParameter("courseName")%>" class="form-control" readonly />
        </div>
        </div>

 		
         <div class="row form-group">
		  <div class="col-md-12">
       		<input type="number" id="numOfSeats" value="60" name="numOfSeats" required placeholder="Enter numOfSeats" class="form-control" hidden/>
        </div>
        </div>
        
         <div class="row form-group">
		  <div class="col-md-12">
		     <label class="text-black" for="fromDate" style="float: left;">Start Date</label>
       		<input type="date" id="fromDate" name="fromDate" value="60" min="<%=LocalDate.now()%>" required placeholder="Enter fromDate" class="form-control"/>
        </div>
        </div>
        
          <div class="row form-group">
		  <div class="col-md-12">
		   <label class="text-black" for="toDate" style="float: left;">End Date</label>
       		<input type="date" id="toDate" name="toDate" min="<%=LocalDate.now().plusDays(10)%>" required placeholder="Enter to Date" class="form-control"/>
        </div>
        </div>
        
        <div class="row form-group">
		  <div class="col-md-12">
		   <label class="text-black" for="locationId" style="float: left;">Location Name</label>
	   <center><select name="locationId" class="form-control">
		   <%
		   	LocationDao locationDao=new LocationDaoImpl();
		   %>
			<option>Select Location</option>
			<%
				for(Location location:locationDao.viewLocation())
					   {
			%>
			   <option value="<%=location.getLocationId()%>"><%=location.getLocationName()%></option>
			   <%
			   	}
			   %>
		   </select></center>
       		<!-- <input type="number" id="locationId" name="locationId" required placeholder="Enter locationId" class="form-control"/> -->
        </div>
        </div>      
         <input type="submit" class="btn btn-info"value="confirm" name ="submit" />

</form>
</div>
</div>
</div>
</div>
</center>


<jsp:include page="TAFooter.html"></jsp:include>
</body>
</html>