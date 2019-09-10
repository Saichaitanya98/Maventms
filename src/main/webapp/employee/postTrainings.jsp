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
 <jsp:include page="employeeHeader.html"></jsp:include>
 
    
    <div class="site-section bg-light">
      <div class="container">
        <div class="row">
          <div class="mb-1 col-md-10 mb-5">

            

            <form method="post" action="../AddCourseServlet" class="p-5 bg-white">
            <%-- <span style="color:green;font-size:20px"><%=(request.getAttribute("message") == null)? "" : request.getAttribute("message") %></span><br> --%>
               <input type="hidden" name="roleName" value="employee">
              <center><h2 class="h4 text-black mb-5">Add Course</h2></center> 

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

	 <div id="foot-projects"></div>
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