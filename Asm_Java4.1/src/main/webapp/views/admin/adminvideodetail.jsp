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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
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
							<h1 class="m-0 text-center">Home Admin</h1>
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
				<!-- Video Player -->
				
				
						<div class="ratio ratio-16x9">
							<iframe src="https://www.youtube.com/embed/${video.href}"
								title="YouTube video player" allowfullscreen 
								></iframe>
						</div>
					
			

				<!-- Video Details and Actions -->
				<div class="row mt-4">
					<div class="col-md-8">
						<!-- Video Title -->
						<h2 class="fw-bold">${video.title}</h2>

						<!-- Actions -->
						<div class="d-flex mb-3">
							<c:if test="${not empty sessionScope.currentUser}">
								<button class="btn btn-danger me-2" id="likeOrunlike">
									<i class="bi bi-hand-thumbs-up"></i>
									<c:choose>
										<c:when test="${flagLikedBtn}">Bỏ thích</c:when>
										<c:otherwise>Thích</c:otherwise>
									</c:choose>
								</button>
								<a class="btn btn-primary"> <i class="bi bi-share"></i> Chia
									sẻ
								</a>
							</c:if>

						</div>

						<!-- Collapsible Details -->
						<div>
							<button class="btn btn-secondary w-100" type="button"
								data-bs-toggle="collapse" data-bs-target="#videoDetails">
								Chi tiết video</button>
							<div class="collapse mt-3" id="videoDetails">
								<p>${video.description }</p>
								<ul>
									<li>Lượt Xem:${video.views}</li>
								</ul>
							</div>
						</div>
					</div>

					<!-- Related Videos -->
					<div class="col-md-4">
						<h5 class="fw-bold">Video Liên Quan</h5>
						<div class="list-group">
							<c:forEach items="${videos}" var="video">
								<div class="item">
									<div class="cate-post-item">
										<div class="cate-post-item-inner">
											<!-- Link dẫn tới trang chi tiết video -->
											<a href="video?action=watch&id=${video.href}">
												<div class="post-avatar">
													<iframe src="https://www.youtube.com/embed/${video.href}"
														title="YouTube video player" frameborder="0"
														allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
														referrerpolicy="strict-origin-when-cross-origin"
														allowfullscreen> </iframe>
												</div>

												<div class="post-content">
													<h6 class="text-default">${video.title}</h6>
													<p class="text-gray text-truncate">${video.description}||
														Views: ${video.views}</p>
												</div>
											</a>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
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
</body>
</html>
