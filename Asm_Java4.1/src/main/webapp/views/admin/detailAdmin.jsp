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

		<div
			class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__shake"
				src="${pageContext.request.contextPath}/views/admin/dist/img/AdminLTELogo.png"
				alt="AdminLTELogo" height="60" width="60">
		</div>

		<%@ include file="/common/admin/navbar.jsp"%>
		<%@ include file="/common/admin/sildebar.jsp"%>

		<div class="content-wrapper">
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-12">
							<h1 class="m-0 text-center">Home Admin</h1>
						</div>
						</div>
					</div>
				</div>
			<section class="content">
				<div class="fvv-content-box">
							<div class="fvv-content-body">
								<div class="form-container">
									<h1>EDIT PROFILE</h1>
									<form action="edituser" method="post">
										<div class="form-group">
											<label for="username">USERNAME?</label> <input type="text"
												class="form-control" id="username" name="username"
												value="${sessionScope.currentUser.id}" readonly>
										</div>
										<div class="form-group">
											<label for="password">PASSWORD?</label> <input
												type="password" class="form-control" id="password"
												name="password" value="1234567890" disabled>
										</div>
										<div class="form-group">
											<label for="fullname">FULLNAME?</label> <input type="text"
												class="form-control" id="fullname" name="fullname"
												value="${sessionScope.currentUser.username}">
										</div>
										<div class="form-group">
											<label for="email">EMAIL ADDRESS?</label> <input type="email"
												class="form-control" id="email" name="email"
												value="${sessionScope.currentUser.email}">
										</div>
										<button type="submit" class="btn btn-primary">Update</button>
										<a href="${pageContext.request.contextPath}/changepass"
											class="btn btn-warning">Change Password</a>
									</form>
								</div>
							</div>
						</div>
				</section>

			</div>
		<footer class="main-footer">
			<strong>Copyright &copy; 2014-2021 <a
				href="https://adminlte.io">AdminLTE.io</a>.
			</strong> All rights reserved.
			<div class="float-right d-none d-sm-inline-block">
				<b>Version</b> 3.1.0
			</div>
		</footer>

		<aside class="control-sidebar control-sidebar-dark">
			</aside>
		</div>

	<%@ include file="/common/admin/scrip.jsp"%>
</body>
</html>