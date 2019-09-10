<%@page import="com.virtusa.training.models.Location"%>
<%@page import="com.virtusa.training.dao.implementations.LocationDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.LocationDao"%>
<%@ page errorPage="../errorPage.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="TAHeader.html"></jsp:include>
<section class="site-section">
    <div class="container">
      <table class="table table-striped">
        <thead>
          <tr>
            <th class="text-center" scope="col">Location Id</th>
            <th class="text-center" scope="col">Location Name</th>
            </tr>
        </thead>
        <tbody>
<%
	LocationDao dao=new LocationDaoImpl();
for(Location location:dao.viewLocation())
{
%> 
<form name="LocationList" method="post" action="viewLocation.jsp">
			<tr>
			<td class="text-center"><%= location.getLocationId() %></td>
			<td class="text-center"><%= location.getLocationName() %></td>
			</tr>
</form>
			<% 	
	}
%>
  
        </tbody>
      </table>
    </div>
  </section>
  
<jsp:include page="TAFooter.html"></jsp:include>
</body>
</html>