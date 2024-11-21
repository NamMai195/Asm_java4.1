<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/SOF3011Slide4/search/check" method="get">
		<input type="text" name="username" placeholder="Username?"><br><br>
		<button>Search</button>
	</form>
	<ul>
	<li>Fullname: ${user.fullname}</li>
	<li>Email: ${user.email}</li>
	</ul>
	<hr>
	
	<!-- Bảng -->
 
<table border="1" style="width: 100%">
	<c:forEach var="item" items="${favorites}">
	<tr>
		<td>${item.id}</td>		  
		<td>${item.user.fullname}</td>
		<td>${item.video.title}</td>
		<td>${item.likeDate}</td>		
	</tr>
	</c:forEach>
</table>

</body>
</html>