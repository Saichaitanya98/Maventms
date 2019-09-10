<%@page errorPage="errorPage.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Welcome</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Quicksand:300,400,500,700,900" rel="stylesheet">
    <link rel="stylesheet" href="fonts/icomoon/style.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/aos.css">
    <link rel="stylesheet" href="css/style.css">
  
    
  </head>
               <!-- ------------------------------BODY--------------------------------------- -->
  <body>
  
      	 <section>
      	
        <div class="site-blocks-cover" style="background-image: url(images/hero_1.jpg);" data-aos="fade" data-stellar-background-ratio="0.5">
          <div class="container clearfix" >
          <div><center>
          	<h1 style="color: #000; padding-top: 20px;">Virtusa Training Management System</h1></center>
          </div>
            <div  style="width: 400px; float: right; margin-top: 100px; padding: 4em 2em 4em 2em; background: #fff; border-radius: 20px;">
           
                <form id="loginForm" method="post" class="font-weight-bold"  action="LoginServlet">
                	 <span style="color:red;font-size:20px"><%=(request.getAttribute("message") == null)? "" : request.getAttribute("message") %></span><br>
                    <div class="form-group">
                      <label for="empId" class="col-form-label"><b style="color: #000">EmployeeID:</b></label>
                      <input type="text" class="form-control" required placeholder="EmployeeId" id="empId" name="empId">
                    </div>
    
                    <div class="form-group">
                        <label for="password" class="col-form-label"><b style="color: #000">Password:</b></label>
                        <input type="password" class="form-control" required placeholder="Password" id="password" name="password" >
                     </div>
                     
                      <button type="submit" class="btn btn-info" name="submit">Login</button> 
                      <button type="reset" class="btn btn-danger" name="submit">Cancel</button>
                      </form>
              </div>
           
          </div>
        </div>  
        
    </section>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/aos.js"></script>
  <script src="js/main.js"></script>
    
  </body>
</html>