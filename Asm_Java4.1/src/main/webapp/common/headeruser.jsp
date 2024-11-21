<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<form class="form-inline my-2 my-lg-0">
						<input class="form-control transition" type="text"
							placeholder="Type to search" aria-label="Search"
							id="nav-input-search">
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
							<form class="dropdown-search-mb">
								<input class="form-control transition" type="text"
									placeholder="Type to search" aria-label="Search"
									id="nav-input-search-mb">
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
							<a class="dropdown-item" href="#">
								<div class="f-noti-thumn">
									<img src="images/fuvavi-thumnail.jpg" alt="">
								</div>
								<p>Đăng kí nhận thông báo để cập nhật bài viết mới nhất</p>
							</a> <a class="dropdown-item" href="#">
								<div class="f-noti-thumn">
									<img src="images/fuvavi-thumnail.jpg" alt="">
								</div>
								<p>Đăng kí nhận thông báo để cập nhật bài viết mới nhất</p>
							</a>
						</div>
					</div>
					<div class="action-nav">
						<a href="javascript:void(0)" class="dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<i class="fa fa-user" aria-hidden="true"></i>
						</a>
						<div class="dropdown-menu f-notification">

							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/register"> <i
								class="fa fa-user-plus" aria-hidden="true"
								style="padding-right: 10px;"></i>Đăng Ký
							</a> <a class="dropdown-item"
								href="${pageContext.request.contextPath}/login"> <i
								class="fa fa-sign-in" aria-hidden="true"
								style="padding-right: 10px;"></i> Đăng Nhập
							</a> <a class="dropdown-item"
								href="${pageContext.request.contextPath}/forgot"> <i
								class="fa fa-sign-in" aria-hidden="true"
								style="padding-right: 10px;"></i> Quên mật khẩu
							</a>




						</div>
					</div>

				</div>
			</div>
		</div>
	</nav>

</header>
