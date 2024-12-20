<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header id="header" class="f-header">
	<nav class="navbar navbar-expand-md fixed-top">
		<div class="container">
			<div class="btn-menu-mb">
				<a href="javascript:void(0)" data-target="#fvv-menu-mb"
					data-toggle="modal"> <i class="fa fa-bars" aria-hidden="true"></i>
				</a>
			</div>
			<div class="col-3 f-logo">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">
					<img
					src="${pageContext.request.contextPath}/views/user/images/logo-fuvavi.png"
					alt="">
				</a>
			</div>

			<div class="navbar-collapse" id="f-navbar-search">
				<div class="mr-auto f-navbar-search">
					<form class="form-inline my-2 my-lg-0" action="search" method="get"> 
						<input class="form-control transition" type="text"
							placeholder="Type to search" aria-label="Search"
							id="nav-input-search" name="query"> 
						<button class="btn button-default my-2 my-sm-0" type="submit">
							<i class="fa fa-search" aria-hidden="true"></i>
						</button>
					</form>
				</div>

				<div class="ml-auto list-action-nav">
					<div class="f-search-mb">
						<a href="#" class="dropdown-toggle" id="dropdown-search"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i
							class="fa fa-search" aria-hidden="true"></i></a>

						<div class="dropdown-menu" aria-labelledby="dropdown-search">
							<form class="dropdown-search-mb" action="search" method="get"> 
								<input class="form-control transition" type="text"
									placeholder="Type to search" aria-label="Search"
									id="nav-input-search-mb" name="query"> 
								<button class="btn button-default" type="submit">
									<i class="fa fa-search" aria-hidden="true"></i>
								</button>
							</form>
						</div>
					</div>
					<div class="action-nav ">
						<a href="javascript:void(0)" class="dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i
							class="fa fa-bell icon-animated-bell" aria-hidden="true"></i><span
							class="badge badge-pill badge-danger">2</span></a>
						<div class="dropdown-menu f-notification">
							<h3>Thông Báo</h3>
							<c:forEach items="${videos}" var="video" begin="0" end="1">
								<a class="dropdown-item"
									href="video?action=watch&id=${video.href}">
									<div class="f-noti-thumn">
										<img
											src="https://img.youtube.com/vi/${video.href}/hqdefault.jpg"
											alt="">
									</div>
									<p>Video mới: ${video.title}</p>
								</a>
							</c:forEach>
						</div>
					</div>
					<div class="action-nav">
						<a href="javascript:void(0)" class="dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<i class="fa fa-user" aria-hidden="true"></i>
						</a>
						<div class="dropdown-menu f-notification">
							<c:choose>
								<c:when test="${not empty sessionScope.currentUser}">
									<h3>Xin chào, ${sessionScope.currentUser.username}!</h3>
									<c:choose>
										<c:when test="${sessionScope.currentUser.role}">
											<a class="dropdown-item"
												href="${pageContext.request.contextPath}/Homeadmin"> <i
												class="fa fa-key" aria-hidden="true"
												style="padding-right: 10px;"></i> Trang Admin
											</a>
										</c:when>
									</c:choose>
									<a class="dropdown-item"
										href="${pageContext.request.contextPath}/changepass"> <i
										class="fa fa-key" aria-hidden="true"
										style="padding-right: 10px;"></i> Đổi Mật Khẩu
									</a>
									<a class="dropdown-item"
										href="${pageContext.request.contextPath}/logout"> <i
										class="fa fa-sign-out" aria-hidden="true"
										style="padding-right: 10px;"></i> Đăng Xuất
									</a>
									<a class="dropdown-item"
										href="${pageContext.request.contextPath}/edituser"> <i
										class="fa fa-sign-out" aria-hidden="true"
										style="padding-right: 10px;"></i> Chỉnh sửa thông tin cá nhân
									</a>
								</c:when>
								<c:otherwise>
									<a class="dropdown-item"
										href="${pageContext.request.contextPath}/register"> <i
										class="fa fa-user-plus" aria-hidden="true"
										style="padding-right: 10px;"></i>Đăng Ký
									</a>
									<a class="dropdown-item"
										href="${pageContext.request.contextPath}/login"> <i
										class="fa fa-sign-in" aria-hidden="true"
										style="padding-right: 10px;"></i> Đăng Nhập
									</a>
									<a class="dropdown-item"
										href="${pageContext.request.contextPath}/forgot"> <i
										class="fa fa-sign-in" aria-hidden="true"
										style="padding-right: 10px;"></i> Quên mật khẩu
									</a>
								</c:otherwise>

							</c:choose>



						</div>
					</div>

				</div>
			</div>
		</div>
	</nav>

</header>