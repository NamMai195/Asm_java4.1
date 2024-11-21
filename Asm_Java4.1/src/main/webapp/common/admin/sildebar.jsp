<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a href="index3.html" class="brand-link"> <img
		src="${pageContext.request.contextPath}/views/admin/dist/img/AdminLTELogo.png"
		alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
		style="opacity: .8"> <span class="brand-text font-weight-light">AdminLTE
			3</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img
					src="${pageContext.request.contextPath}/views/admin/dist/img/user2-160x160.jpg"
					class="img-circle elevation-2" alt="User Image">
			</div>
			<div class="info">
				<a href="#" class="d-block">Alexander Pierce</a>
			</div>
		</div>

		<!-- SidebarSearch Form -->


		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
				<li class="nav-item"><a href="./index.html"
					class="nav-link active"> <i class="fas fa-list nav-icon"></i>
						<p>Home Admin</p>
				</a></li>
				<li class="nav-item"><a href="#" class="nav-link active"> <i
						class="nav-icon fas fa-video"></i>
						<p>
							Video Management <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="./index.html"
							class="nav-link active"> <i class="fas fa-list nav-icon"></i>
								<p>All Videos</p>
						</a></li>
						<li class="nav-item"><a href="./index2.html" class="nav-link">
								<i class="fas fa-sliders-h nav-icon"></i>
								<p>Control Videos</p>
						</a></li>
						<li class="nav-item"><a href="./index3.html" class="nav-link">
								<i class="far fa-chart-bar nav-icon"></i>
								<p>Report Videos</p>
						</a></li>
					</ul></li>

				<li class="nav-item menu-open"><a href="#"
					class="nav-link active"> <i class="nav-icon fas fa-users"></i>
						<p>
							Users Management <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="./index.html"
							class="nav-link active"> <i class="fas fa-user-cog nav-icon"></i>
								<p>Control Users</p>
						</a></li>
						<li class="nav-item"><a href="./index3.html" class="nav-link">
								<i class="far fa-chart-bar nav-icon"></i>
								<p>Report Users</p>
						</a></li>
					</ul></li>
				<li class="nav-item menu-open"><a href="#"
					class="nav-link active"> <i class="nav-icon fas fa-chart-pie"></i>
						<!-- Icon thay đổi -->
						<p>
							Website Statistics <i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="./index.html"
							class="nav-link active"> <i class="fas fa-video nav-icon"></i>
								<!-- Icon thay đổi -->
								<p>Videos Statistics</p>
						</a></li>
						<li class="nav-item"><a href="./index.html" class="nav-link">
								<i class="fas fa-user nav-icon"></i> <!-- Icon thay đổi -->
								<p>Users Statistics</p>
						</a></li>
					</ul></li>



			</ul>
			</li>
			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
</aside>