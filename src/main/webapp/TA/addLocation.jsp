<%@page import="com.virtusa.training.models.Location"%>
<%@page
	import="com.virtusa.training.dao.implementations.LocationDaoImpl"%>
<%@page import="com.virtusa.training.dao.interfaces.LocationDao"%>
<%@ page errorPage="../errorPage.jsp" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Location</title>
</head>
<body>
	<jsp:include page="TAHeader.html"></jsp:include>
	<section class="site-section">

		<form id="locationForm" method="post" action="../AddLocationServlet">

			<div class="row form-group">
				<div class="col-md-12">

					<center>
						<label class="" for="locationName"><b>Location Name</b></label>
					</center>
					<center>
						<input type="text" id="locationName" name="locationName"
							class="w-25 form-control" required
							placeholder="Enter location name.. Eg:Apoorthi" />
					</center>
				</div>

			</div>

			<center>
				<input class="btn btn-info" type="submit" name="submit"
					value="AddLocation">
			</center>
		</form>

	</section>

	<div id="foot-projects" class="fixed-bottom">
		<jsp:include page="TAFooter.html"></jsp:include>
	</div>
</body>
</html>