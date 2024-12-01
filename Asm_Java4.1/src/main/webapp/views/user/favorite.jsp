<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Blog Fuvavi.com</title>
<%@ include file="/common/taglist.jsp"%>
<%@ include file="/common/head.jsp"%>
</head>
<style>
.post-avatar {
	position: relative;
	width: 100%; /* Chiếm toàn bộ chiều rộng container */
	padding-top: 56.25%; /* Tỷ lệ 16:9 (9/16 = 0.5625) */
	overflow: hidden; /* Ẩn phần tràn */
}

.post-avatar iframe {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%; /* Chiếm toàn bộ chiều rộng của .post-avatar */
	height: 100%; /* Tương ứng với chiều cao tính từ padding-top */
	border: none;
}
a {
    text-decoration: none !important;
}

</style>
<body>
	<div class="wrapper">
		<%@ include file="/common/header.jsp"%>
		<%@ include file="/common/menumobile.jsp"%>
		<div class="f-content">
			<div class="container">
				<div class="row flex-xl-nowrap">
					<%@ include file="/common/sidebar.jsp"%>
					<div class="col-12 col-md-9 col-xl-9 fvv-content">
						<div class="fvv-content-box">
							<h2 class="fvv-content-title">Video Đã Thích</h2>
							<div class="fvv-content-body">
								<div class="row">
									<c:forEach items="${videos}" var="video" varStatus="loop">
									    <c:if test="${loop.index < 6}">
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
															<p class="text-gray text-truncate">${video.description}|| Views:
																${video.views}</p>
														</div>
													</a>
												</div>
											</div>
										</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>




						<%@ include file="/common/footer.jsp"%>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/common/linksctrip.jsp"%>
	</div>
</body>
</html>