<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
1
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<title>Blog Fuvavi.com</title>
<%@ include file="/common/taglist.jsp"%>
<%@ include file="/common/head.jsp"%>
</head>
<style>
.post-avatar {
	position: relative;
	width: 100%;
	/* Chiếm toàn bộ chiều rộng container */
	padding-top: 56.25%;
	/* Tỷ lệ 16:9 (9/16 = 0.5625) */
	overflow: hidden;
	/* Ẩn phần tràn */
}

.post-avatar iframe {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	/* Chiếm toàn bộ chiều rộng của .post-avatar */
	height: 100%;
	/* Tương ứng với chiều cao tính từ padding-top */
	border: none;
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
						<div class="container mt-4">
							<div class="row">
								<div class="col-12">
									<div class="ratio ratio-16x9">
										<iframe src="https://www.youtube.com/embed/${video.href}"
											title="YouTube video player" allowfullscreen></iframe>
									</div>
								</div>
							</div>

							<div class="row mt-4">
								<div class="col-md-8">
									<h2 class="fw-bold">${video.title}</h2>

									<div class="d-flex mb-3">
               						     <c:if test="${not empty sessionScope.currentUser}">
                 						     <button class="btn btn-danger me-2" id="likeOrunlike">
                  					      <i id="icon" class="${flagLikedBtn ? 'bi bi-hand-thumbs-down-fill' : 'bi bi-hand-thumbs-up'}"></i>
                   						  <span id="likeText">${flagLikedBtn ? 'Bỏ thích' : 'Thích'}</span> <span class="badge bg-light text-dark ms-2" id="likeCount">${like}</span>
                   							   </button>
                  							    <button class="btn btn-primary" onclick="shareVideo()">
                      					  <i class="bi bi-share"></i> Chia sẻ
                     							 </button>

                    					</c:if>
                 				   </div>

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

								<div class="col-md-4">
									<h5 class="fw-bold">Video Liên Quan</h5>
									<div class="list-group">
										<c:forEach items="${videos}" var="video">
											<div class="item">
												<div class="cate-post-item">
													<div class="cate-post-item-inner">
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
            url: 'video?action=like&id=' + videoId,
            type: 'GET',
          }).then(function(data) {
            // Cập nhật icon, text và badge
            var $button = $('#likeOrunlike');
            var $icon = $('#icon');
            var $text = $('#likeText'); 
            var $badge = $('#likeCount'); // Lấy element badge theo ID

            if ($icon.hasClass('bi-hand-thumbs-up')) {
              // Đang là icon like, chuyển sang dislike
              $icon.removeClass('bi-hand-thumbs-up').addClass('bi-hand-thumbs-down-fill');
              $text.text('Bỏ thích '); 
            } else {
              // Đang là icon dislike, chuyển sang like
              $icon.removeClass('bi-hand-thumbs-down-fill').addClass('bi-hand-thumbs-up');
              $text.text('Thích '); 
            }

            // Cập nhật số lượt thích
            $badge.text(data); 

          }).fail(function() {
            alert('Có lỗi xảy ra khi gửi yêu cầu.');
          });

          console.log("Video ID: " + videoId);
        });

      });
      
    </script>
		<div class="modal fade" id="shareModal" tabindex="-1"
			aria-labelledby="shareModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="shareModalLabel">Chia sẻ video</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label for="email" class="form-label">Nhập email người
								nhận:</label> <input type="email" class="form-control" id="email"
								name="email" required>
						</div>
						<button type="button" class="btn btn-primary"
							onclick="submitShare()">Chia sẻ</button>
					</div>
				</div>
			</div>
		</div>

		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<script>
			function shareVideo() {
				$('#shareModal').modal('show');
			}

			function submitShare() {
				var email = $('#email').val();
				var videoHref = $('#videoIdhd').val();

				$.ajax({
					url : 'video?action=share',
					type : 'POST',
					data : {
						href : videoHref,
						email : email
					}
				}).done(function(response) {
					Swal.fire({
						icon : 'success',
						title : 'Chia sẻ thành công!',
						showConfirmButton : false,
						timer : 1500
					});
				}).fail(function(jqXHR, textStatus, errorThrown) {
					Swal.fire({
						icon : 'error',
						title : 'Chia sẻ thất bại!',
						text : 'Đã có lỗi xảy ra.'
					});
					console.error("Error:", textStatus, errorThrown);
				}).always(function() {
					$('#shareModal').modal('hide');
				});
			}
		</script>
	</div>
</body>
</html>