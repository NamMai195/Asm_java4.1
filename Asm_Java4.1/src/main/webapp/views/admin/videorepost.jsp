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
						<div class="col-sm-6">
							<h1 class="m-0">Video</h1>
						</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">Repost Videos</h3>
					</div>
					<div class="card-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Id</th>
									<th>Title</th>
									<th>Href</th>
									<th>Views</th>
									<th>Total Like</th>
									<th>Total Share</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${videos}" var="video">
									<tr>
										<td>${video.videoId }</td>
										<td>${video.title}</td>
										<td>${video.href}</td>
										<td>${video.views}</td>
										<td>${video.totalLike }</td>
										<td>${video.totalShare }</td>
									</tr>
								</c:forEach>

							</tbody>
							<tfoot>
								<tr>
									<th>Id</th>
									<th>Title</th>
									<th>Href</th>
									<th>Views</th>
									<th>Total Like</th>
									<th>Total Share</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">User Favorite Videos</h3>
					</div>
					<div class="card-body">
						<div class="form-group">
							<label>List Video</label> <select class="form-control"
								id="selectVideo">
								<option selected disabled>Choose One</option>
								<c:forEach items="${videos}" var="video">
									<option value="${video.href}">${video.title}</option>
								</c:forEach>
							</select>
						</div>
						<table id="userTable" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Username</th>
									<th>Email</th>
								</tr>
							</thead>
							<tbody>



							</tbody>
							<tfoot>
								<tr>
									<th>Username</th>
									<th>Email</th>
								</tr>
							</tfoot>
						</table>
					</div>
					1
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

		<aside class="control-sidebar control-sidebar-dark"></aside>
	</div>

	<%@ include file="/common/admin/scrip.jsp"%>
	<%@ include file="/common/admin/datascrip.jsp"%>
	<script>
		$('#selectVideo').change(function() {
			var videoHref = $(this).val();
			$.ajax({
				url : 'favorites?href=' + videoHref,
				type : 'GET',
				contentType : 'application/json'
			}).done(function(data) {
				$('#userTable').DataTable({
					destroy : true,
					"paging" : true,
					"lengthChange" : false,
					"searching" : false,
					"ordering" : true,
					"info" : true,
					"autoWidth" : false,
					"responsive" : true,
					"aaData" : data,
					"columns" : [ {
						"data" : "username"
					}, {
						"data" : "email"
					} ]
				});
			}).fail(function(error) {
				$('#userTable').DataTable().clear().draw();
			})
		});
	</script>
</body>
</html>