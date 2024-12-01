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
						<div class="col-sm-12">
							<h1 class="m-0 text-center">All Video</h1>
						</div>
						<!-- /.col -->
						<div class="fvv-content-box">
							<h2 class="fvv-content-title">Tất Cả Video</h2>
							<div class="fvv-content-body">
								<div class="row">
									<c:forEach items="${videos}" var="video" varStatus="loop">
										<div class="col-md-4 col-sm-6">
											<div class="cate-post-item">
												<div class="cate-post-item-inner">
													<a href="video?action=watch&id=${video.href}">
														<div class="post-avatar">
															<iframe src="https://www.youtube.com/embed/${video.href}"
																title="YouTube video player" frameborder="0"
																allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
																referrerpolicy="strict-origin-when-cross-origin"
																allowfullscreen> </iframe>
														</div>
														<div class="post-content">
															<h5 class="text-default">${video.title}</h5>
															<p class="text-gray text-truncate">${video.description}||
																Views: ${video.views}</p>
														</div>
													</a>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
								<nav aria-label="Page navigation example">
									<ul class="pagination justify-content-center">
										<c:if test="${currentPage > 1}">
											<li class="page-item"><a class="page-link"
												href="index?page=${currentPage - 1}" aria-label="Previous">
													<span aria-hidden="true">&laquo;</span>
											</a></li>
										</c:if>
										<c:forEach begin="1" end="${totalPages}" var="i">
											<li class="page-item ${currentPage == i ? 'active' : ''}">
												<a class="page-link" href="index?page=${i}">${i}</a>
											</li>
										</c:forEach>
										<c:if test="${currentPage < totalPages}">
											<li class="page-item"><a class="page-link"
												href="index?page=${currentPage + 1}" aria-label="Next">
													<span aria-hidden="true">&raquo;</span>
											</a></li>
										</c:if>
									</ul>
								</nav>
							</div>
						</div>

						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">

				<!-- /.container-fluid -->
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
</body>
</html>
