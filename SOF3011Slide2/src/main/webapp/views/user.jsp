<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CRUD User Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <h1 class="text-center mt-4">User Management CRUD</h1>

    <!-- Thông báo -->
    <div class="alert alert-info mt-3" role="alert">
        ${message}
    </div>
    
    <!-- Form -->
    <div class="card mt-4">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/user/index" method="post">
                <div class="mb-3">
                    <label for="id" class="form-label">ID</label>
                    <input type="text" class="form-control" name="id" value="${form.id}" placeholder="Enter ID">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="text" class="form-control" name="password" value="${form.password}" placeholder="Enter Password">
                </div>
                <div class="mb-3">
                    <label for="fullname" class="form-label">Fullname</label>
                    <input type="text" class="form-control" name="fullname" value="${form.fullname}" placeholder="Enter Fullname">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" value="${form.email}" placeholder="Enter Email">
                </div>
                
                <div class="mb-3">
                    <label class="form-label">Role</label><br>
                    <input type="radio" name="admin" value="true" ${form.admin ? 'checked' : ''}> Admin
                    <input type="radio" name="admin" value="false" ${form.admin ? '' : 'checked'}> User
                </div>
                
                <div class="d-flex justify-content-center">
                    <button formaction="${pageContext.request.contextPath}/user/create" class="btn btn-success mx-2">Create</button>
                    <button formaction="${pageContext.request.contextPath}/user/update" class="btn btn-info mx-2">Update</button>
                    <button formaction="${pageContext.request.contextPath}/user/delete" class="btn btn-danger mx-2">Delete</button>
                    <a href="${pageContext.request.contextPath}/user/index" class="btn btn-secondary mx-2">Reset</a>
                </div>
            </form>
        </div>     
    </div>

    <!-- Tìm kiếm -->
    <div class="card mt-4">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/user/search" method="get" class="d-flex">
                <input type="text" name="keyword" placeholder="Nhập thông tin cần tìm pass or id or fullname" class="form-control me-2" value="${param.keyword}">
                <button type="submit" class="btn btn-primary">Find</button>
            </form>
        </div>
    </div>

    <!-- Bảng -->
    <div class="table-responsive mt-5">
        <table class="table table-bordered table-striped text-center">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Password</th>
                    <th>Fullname</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.password}</td>
                        <td>${item.fullname}</td>
                        <td>${item.email}</td>
                        <td>${item.admin ? 'Admin' : 'User'}</td>
                        <td><a href="${pageContext.request.contextPath}/user/edit/${item.id}" class="btn btn-primary btn-sm">Edit</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
