 <%@page import="com.virtusa.training.models.Course"%> 
 <%@page import="com.virtusa.training.dao.implementations.CourseDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.CourseDao"%> 

<%@ page errorPage="../errorPage.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="../js/jquery-1.10.2.js"></script>

</head>
                 <!-- ------------------------------BODY--------------------------------------- -->
<body>
  
 <div id="new-projects"></div>
 <div id="error"></div>
 <jsp:include page="PMHeader.html"></jsp:include>
 
    
    <div class="site-section bg-light">
      <div class="container">
        <div class="row">
          <div class="mb-1 col-md-10 mb-5">

            

            <form method="post" action="../AddCourseServlet" class="p-5 bg-white">
            <%-- <span style="color:green;font-size:20px"><%=(request.getAttribute("message") == null)? "" : request.getAttribute("message") %></span><br> --%>
              <input type="hidden" name="roleName" value="PM">
              <h2 class="h4 text-black mb-5">Add Course</h2> 

              <div class="row form-group">
                  <div class="col-md-6">
                  <label class="text-black" for="courseName">Course Name</label>
                  <input type="text" id="courseName" name="courseName" required placeholder="Enter courseName"class="form-control">
                </div>
               </div>

              <div class="row form-group">
                <div class="col-md-12">
                  <label class="text-black" for="message">Course Description</label> 
                  <textarea  id="description" name="description" required placeholder="Enter description" cols="30" rows="7" class="form-control" ></textarea>
                </div>
              </div>

              <div class="row form-group">
                <div class="col-md-2">
                  <input type="submit" name="submit" value="confirm" class="btn btn-info btn-md text-white">
                </div>
               </div>

  
            </form>
          </div>
          <div class="col-md-5">

          </div>
        </div>
      </div>
    </div>

	 <div id="foot-projects">
	 	<jsp:include page="PMFooter.html"></jsp:include>
	 </div>
	 
  <script src="../js/bootstrap.min.js"></script>
  	<script src="../js/aos.js"></script>
  	<script src="../js/main.js"></script>
    
   
     
  </body>
  


</html>