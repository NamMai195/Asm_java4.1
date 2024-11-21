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
</style>
<body>
	<div class="wrapper">
		<!-- HEADER -->
		<%@ include file="/common/header.jsp"%>
		<!-- !HEADER -->

		<!-- menu mobile -->
		<%@ include file="/common/menumobile.jsp"%>
		<div class="f-content">
			<div class="container">
				<div class="row flex-xl-nowrap">
					<%@ include file="/common/sidebar.jsp"%>
					<div class="col-12 col-md-9 col-xl-9 fvv-content">
						<div class="container mt-4">
							<!-- Video Player -->
							<div class="row">
								<div class="col-12">
									<div class="ratio ratio-16x9">
										<iframe src="https://www.youtube.com/embed/${video.href}"
											title="YouTube video player" allowfullscreen></iframe>
									</div>
								</div>
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
											<a class="btn btn-primary"> <i class="bi bi-share"></i>
												Chia sẻ
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
																<iframe
																	src="https://www.youtube.com/embed/${video.href}"
																	title="YouTube video player" frameborder="0"
																	allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
																	referrerpolicy="strict-origin-when-cross-origin"
																	allowfullscreen> </iframe>
															</div>

															<div class="post-content">
															<h6 class="text-default">${video.title}</h6>
															<p class="text-gray text-truncate">${video.description}|| Views:
																${video.views}</p>
														</div>
														</a>
													</div>
												</div>
											</div>
										</c:forEach>
										
									</div>
								</div>
							</div>
						</div>



						<input type="hidden" value="${video.href}" id="videoIdhd">
						<%@ include file="/common/footer.jsp"%>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/common/linksctrip.jsp"%>
		<script>
			$(document).ready(function() {
				$('#likeOrunlike').click(function() {
					var videoId = $('#videoIdhd').val();

					if (!videoId) {
						alert("Video ID không được để trống!");
						return;
					}

					$.ajax({
						url : 'video?action=like&id=' + videoId,
						type : 'GET', // hoặc POST tùy thuộc vào logic backend
					}).then(function(data) {
						var text = $('#likeOrunlike').text();

						if (text.indexOf('Thích') !== -1) {
							$('#likeOrunlike').text('Bỏ thích');
						} else {
							$('#likeOrunlike').text('Thích');
						}

						console.log("Trạng thái thích đã được thay đổi.");
					}).fail(function() {
						alert('Có lỗi xảy ra khi gửi yêu cầu.');
					});

					console.log("Video ID: " + videoId);
				});
			});
		</script>

	</div>
</body>
</html>