<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglist.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Dashboard</title>
<%@ include file="/common/admin/head.jsp"%>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<div
			class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__shake"
				src="${pageContext.request.contextPath}/views/admin/dist/img/AdminLTELogo.png"
				alt="AdminLTELogo" height="60" width="60">
		</div>

		<!-- Navbar -->
		<%@ include file="/common/admin/navbar.jsp"%>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<%@ include file="/common/admin/sildebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Home Admin</h1>
						</div>
						<!-- /.col -->

						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">Repost Videos</h3>
					</div>
					<!-- /.card-header -->
					<div class="card-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Id</th>
									<th>Title</th>
									<th>Href</th>
									<th>Total Like</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${videos}" var="video">
									<tr>
										<td>${video.videoId }</td>
										<td>${video.title}</td>
										<td>${video.href}</td>
										<td>${video.totalLike }</td>

									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Id</th>
									<th>Title</th>
									<th>Href</th>
									<th>Total Like</th>

								</tr>
							</tfoot>
						</table>
					</div>
					<!-- /.card-body -->
				</div>
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">Repost User</h3>
					</div>
					<!-- /.card-header -->
					<div class="card-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Id</th>
									<th>Username</th>
									<th>Password</th>
									<th>Email</th>
									<th>Role</th>
									<th>isActive</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${users}" var="user">
									<tr>
										<td>${user.id }</td>
										<td>${user.username}</td>
										<td>${user.password}</td>
										<td>${user.email }</td>
										<td>${user.role ? 'Admin' : 'Người Dùng'}</td>
										<td>${user.isActive ? 'Đang hoạt động' : 'Bị khóa'}</td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Id</th>
									<th>Username</th>
									<th>Password</th>
									<th>Email</th>
									<th>Role</th>
									<th>isActive</th>
								</tr>
							</tfoot>
						</table>
					</div>
					<!-- /.card-body -->
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer">
			<strong>Copyright &copy; 2014-2021 <a
				href="https://adminlte.io">AdminLTE.io</a>.
			</strong> All rights reserved.
			<div class="float-right d-none d-sm-inline-block">
				<b>Version</b> 3.1.0
			</div>
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>

	<%@ include file="/common/admin/scrip.jsp"%>
	<%@ include file="/common/admin/datascrip.jsp"%>
</body>
</html>
