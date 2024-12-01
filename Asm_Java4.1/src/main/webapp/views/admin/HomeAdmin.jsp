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
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-3 col-6">
							<div class="small-box bg-info">
								<div class="inner">
									<h3>
										<c:out value="${totalUsers}" />
									</h3>
									<p>Total User</p>
								</div>
								<div class="icon">
									<i class="ion ion-person-stalker"></i>
								</div>
								<a
									href="${pageContext.request.contextPath}/Homeadmin/repostuser"
									class="small-box-footer">More info <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<div class="col-lg-3 col-6">
							<div class="small-box bg-success">
								<div class="inner">
									<h3>
										<c:out value="${totalVideos}" />
									</h3>

									<p>ToTal Video</p>
								</div>
								<div class="icon">
									<i class="ion ion-videocamera"></i>
								</div>
								<a
									href="${pageContext.request.contextPath}/Homeadmin/repostvideo"
									class="small-box-footer">More info <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<div class="col-lg-3 col-6">
							<div class="small-box bg-warning">
								<div class="inner">
									<h3>
										<c:out value="${totalViews}" />
									</h3>

									<p>ToTal Views</p>
								</div>
								<div class="icon">
									<i class="ion ion-eye"></i>
								</div>
								<a
									href="${pageContext.request.contextPath}/Homeadmin/repostvideo"
									class="small-box-footer">More info <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<div class="col-lg-3 col-6">
							<div class="small-box bg-danger">
								<div class="inner">
									<h3>
										<c:out value="${totalShares}" />
									</h3>

									<p>Total Share</p>
								</div>
								<div class="icon">
									<i class="ion ion ion-share"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="card card-primary">
								<div class="card-header">
									<h3 class="card-title">Biểu đồ tròn</h3>
								</div>
								<div class="card-body">
									<canvas id="myChart1"></canvas>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="card card-success">
								<div class="card-header">
									<h3 class="card-title">Biểu đồ cột</h3>
								</div>
								<div class="card-body">
									<canvas id="myChart2"></canvas>
								</div>
							</div>
						</div>
					</div>
					<div class="card bg-gradient-primary" hidden>
						<div class="card-header border-0">
							<h3 class="card-title">
								<i class="fas fa-map-marker-alt mr-1"></i> Visitors
							</h3>
							<div class="card-tools">
								<button type="button" class="btn btn-primary btn-sm daterange"
									title="Date range">
									<i class="far fa-calendar-alt"></i>
								</button>
								<button type="button" class="btn btn-primary btn-sm"
									data-card-widget="collapse" title="Collapse">
									<i class="fas fa-minus"></i>
								</button>
							</div>
						</div>
						<div class="card-body">
							<div id="world-map" style="height: 250px; width: 100%;"></div>
						</div>
						<div class="card-footer bg-transparent">
							<div class="row">
								<div class="col-4 text-center">
									<div id="sparkline-1"></div>
									<div class="text-white">Visitors</div>
								</div>
								<div class="col-4 text-center">
									<div id="sparkline-2"></div>
									<div class="text-white">Online</div>
								</div>
								<div class="col-4 text-center">
									<div id="sparkline-3"></div>
									<div class="text-white">Sales</div>
								</div>
							</div>
						</div>
					</div>

					<div class="card bg-gradient-success">
						<div class="card-header border-0">

							<h3 class="card-title">
								<i class="far fa-calendar-alt"></i> Calendar
							</h3>
							<div class="card-tools"></div>
						</div>
						<div class="card-body pt-0">
							<div id="calendar" style="width: 100%"></div>
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

		<aside class="control-sidebar control-sidebar-dark"></aside>
	</div>

	<%@ include file="/common/admin/scrip.jsp"%>

	<script>
    var data = {
      labels: ['Tổng số lượt chia sẻ', 'Tổng lượt xem', 'Tổng số video', 'Tổng số người dùng'],
      datasets: [{
        label: 'Thống kê',
        data: [${totalShares}, ${totalViews}, ${totalVideos}, ${totalUsers}], 
        backgroundColor: [
          'rgba(255, 99, 132, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(255, 206, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)'
        ],
        borderColor: [
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)',
          'rgba(75, 192, 192, 1)'
        ],
        borderWidth: 1   

      }]
    };

    // Vẽ biểu đồ tròn
    var ctx1 = document.getElementById('myChart1').getContext('2d');
    new Chart(ctx1, {
      type: 'pie',
      data: data
    });

    // Vẽ biểu đồ cột
    var ctx2 = document.getElementById('myChart2').getContext('2d');
    new Chart(ctx2, {
      type: 'bar',
      data: data,
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  </script>
</body>
</html>