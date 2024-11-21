<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">
	.custab{
	    border: 1px solid #ccc;
	    padding: 5px;
	    margin: 5% 0;
	    box-shadow: 3px 3px 2px #ccc;
	    transition: 0.5s;
    }
	.custab:hover{
	    box-shadow: 3px 3px 0px transparent;
	    transition: 0.5s;
    }
</style>
</head>
<body>

<div class="container">
<!-- Thông báo -->
${message}
<c:url var="url" value="/user"/>
<!-- Form -->
	<form action="${url}/index" method="post">
		<input value="${form.id}" name="id" placeholder="Id?"><br>
		<input value="${form.password}" name="password" placeholder="Password?"><br>
		<input value="${form.fullname}" name="fullname" placeholder="Fullname?"><br>
		<input value="${form.email}" name="email" placeholder="Email Address?"><br>
		
		<input ${form.admin?'checked':''} name="admin" type="radio" value="true">Admin
		<input ${form.admin?'':'checked'} name="admin" type="radio" value="false">User
		<br> 
		<button formaction="${url}/create">Create</button>
		<button formaction="${url}/update">Update</button>
		<button formaction="${url}/delete">Delete</button>
		<a href="${url}/index">Reset</a>
		<br>	<hr>
	</form>
    <div class="row col-md-6 col-md-offset-2 custyle">
    <table class="table table-striped custab">
    <thead>
    	<a href="#" class="btn btn-primary btn-xs pull-right"><b>+</b> Add new categories</a>
        <tr>
            <th>ID</th>
            <th>Password</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>isAdmin</th>
            <th class="text-center">Action</th>
        </tr>
    </thead>
    	<c:forEach var="item" items="${items}">
		<tr>
			<td>${item.id}</td>
			<td>${item.password}</td>
			<td>${item.fullname}</td>
			<td>${item.email}</td>
			<td>${item.admin?'Admin':'User'}</td>
			<td class="text-center">
				<a class='btn btn-info btn-xs' href="/SOF3011Slide2/user/edit/${item.id}"><span class="glyphicon glyphicon-edit"></span> Edit</a> 
				<a href="#" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> Del</a>
			</td>
		</tr>
		</c:forEach>         
    </table>
    </div>
</div>

</body>
</html>