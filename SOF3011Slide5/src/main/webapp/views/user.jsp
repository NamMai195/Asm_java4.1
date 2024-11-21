<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${empty sessionScope.user}">
		Welcome you
	</c:when>
	<c:when test="${not empty sessionScope.user}">
		Welcome ${sessionScope.user.fullname}	
		<a href="/SOF3011Slide5/account/sign-out">Đăng xuất</a>
		<c:if test="${sessionScope.user.admin}">
			<a href="/SOF3011Slide5/user/index">Quản trị</a>
		</c:if>	
	</c:when>
	
	<c:otherwise>		
		
	</c:otherwise>
</c:choose>
<br>
Request scope: ${requestScope.reqUser.fullname} 
<hr>
<!-- Thông báo -->
${message}
<c:url var="url" value="/user"/>
<!-- Form -->
<form action="${url}/index" method="post">
	<input value="${form.id}" name="id" placeholder="Username?"><br>
	<input value="${form.password}" name="password" placeholder="Password?"><br>
	<input value="${form.fullname}" name="fullname" placeholder="Fullname?"><br>
	<input value="${form.email}" name="email" placeholder="Email Address?"><br>
	
	<input ${form.admin?'checked':''} name="admin" type="radio" value="true">Admin
	<input ${form.admin?'':'checked'} name="admin" type="radio" value="false">User<br>
	 
	<hr>
	<button formaction="${url}/create">Create</button>
	<button formaction="${url}/update">Update</button>
	<button formaction="${url}/delete">Delete</button>
	<a href="${url}/index">Reset</a>
</form>

<!-- Bảng -->
 
<table border="1" style="width: 100%">
	<c:forEach var="item" items="${items}">
	<tr>
		<td>${item.id}</td>
		<td>${item.password}</td>
		<td>${item.fullname}</td>
		<td>${item.email}</td>
		<td>${item.admin?'Admin':'User'}</td>
		<td> <a href="/SOF3011Slide5/user/edit/${item.id}">Edit</a> </td>
	</tr>
	</c:forEach>
</table>

</body>
</html>