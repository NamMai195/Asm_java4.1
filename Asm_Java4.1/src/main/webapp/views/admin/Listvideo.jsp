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
						<h3 class="card-title">Video Management</h3>
					</div>
					<div class="card-body">
						<form id="videoForm">
							<div class="form-group">
								<label for="videoId">Video ID</label> <input type="text"
									id="videoId" name="videoId" class="form-control" required>
							</div>
							<div class="form-group">
								<label for="title">Title:</label> <input type="text"
									class="form-control" id="title" name="title" required>
							</div>
							<div class="form-group">
								<label for="description">Description:</label>
								<textarea class="form-control" id="description"
									name="description"></textarea>
							</div>
							<div class="form-group">
								<label for="href">Href:</label> 
								<input type="text" class="form-control" id="href" name="href" required>
								<div id="videoThumbnail">
									<img id="thumbnailImg" src="" alt="Video Thumbnail" width="200px">
								</div>
							</div>
							<div class="form-group">
								<label for="isActive">isActive:</label> <select
									class="form-control" id="isActive" name="isActive">
									<option value="true">Hoạt Động</option>
									<option value="false">Bị Khóa</option>
								</select>
							</div>
							<button type="button" id="btnAdd" class="btn btn-primary">Add</button>
							<button type="button" id="btnSave" class="btn btn-success">Save</button>

						</form>

						<table id="example1"
							class="table table-bordered table-striped mt-3">
							<thead>
								<tr>
									<th>Id</th>
									<th>Title</th>
									<th>Description</th>
									<th>Views</th>
									<th>Href</th>
									<th>Poster</th>
									<th>isActive</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${videos}" var="video">
									<tr data-video-id="${video.videoId}"
										data-title="${video.title}"
										data-description="${video.description}"
										data-views="${video.views}" data-href="${video.href}"
										data-is-active="${video.isActive}">
										<td>${video.videoId}</td>
										<td>${video.title}</td>
										<td>${video.description}</td>
										<td>${video.views}</td>
										<td>${video.href}</td>
										<td><img
											src="https://img.youtube.com/vi/${video.href}/hqdefault.jpg"
											alt="${video.title}" width="200px"></td>
										<td>${video.isActive ? "Hoạt Động" :"Bị Khóa"}</td>
										<td><button type="button" id="btnDelete" onclick="deleted('${video.href}')" class="btn btn-danger">Delete</button></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Id</th>
									<th>Title</th>
									<th>Description</th>
									<th>Views</th>
									<th>Href</th>
									<th>Poster</th>
									<th>isActive</th>
									<th>Action</th>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</section>
		</div>

		<footer class="main-footer">
			<strong>Copyright © 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.
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
		$(document).ready(function() {
			$('#example1 tbody tr').click(function() {
				var videoId = $(this).data('video-id');
				var title = $(this).data('title');
				var description = $(this).data('description');
				var href = $(this).data('href');
				var isActive = $(this).data('is-active');

				$('#videoId').val(videoId);
				$('#title').val(title);
				$('#description').val(description); 
				$('#href').val(href);
				$('#isActive').val(isActive ? 'true' : 'false');
			});	
		});
		
	</script>
	<script>
	function deleted(videoId) {
		if (confirm("Bạn có chắc chắn muốn xóa video này?")) {
			$.ajax({
				url: '/Asm_Java4.1/admin/video?action=delete&href=' + videoId,
				type: 'GET' // Hoặc 'POST' tùy thuộc vào yêu cầu của server
			}).then(function(data) {
				// Reload lại trang hoặc cập nhật danh sách video
				window.location.href = "http://localhost:8080/Asm_Java4.1/admin/video?action=list";
			}).fail(function(error) {
				alert("Lỗi khi xóa video");
				console.error('Lỗi:', error);
			});
		}
	}
	$('#btnAdd').click(function() {
	    // Lấy dữ liệu từ form
	    var videoId = $('#videoId').val();
	    var title = $('#title').val();
	    var description = $('#description').val();
	    var href = $('#href').val();
	    var isActive = $('#isActive').val();

	    // Kiểm tra dữ liệu (có thể thêm các validation khác)
	    if (!videoId || !title || !href) {
	      alert("Vui lòng điền đầy đủ thông tin video.");
	      return;
	    }

	    // Tạo object video
	    var video = {
	      videoId: videoId,
	      title: title,
	      description: description,
	      href: href,
	      isActive: isActive === 'true'
	    };

	    // Gửi yêu cầu AJAX đến server
	    $.ajax({
	      url: '/Asm_Java4.1/admin/video?action=add', // Thay thế bằng URL của API thêm video
	      type: 'POST', // Hoặc 'GET' tùy thuộc vào yêu cầu của server
	      data: video, // Gửi dữ liệu video
	      success: function(data) {
	        // Xử lý kết quả trả về từ server (ví dụ: reload lại trang)
	        alert('Video đã được thêm!');
	        window.location.href = "http://localhost:8080/Asm_Java4.1/admin/video?action=list";
	      },
	      error: function(error) {
	        // Xử lý lỗi nếu có
	        console.error('Lỗi khi thêm video:', error);
	        alert('Thêm video thất bại!');
	      }
	    });
	  });
	$('#btnSave').click(function() {
        // Lấy dữ liệu từ form
        var videoId = $('#videoId').val();
        var title = $('#title').val();
        var description = $('#description').val();
        var href = $('#href').val();
        var isActive = $('#isActive').val();

        // Kiểm tra dữ liệu (có thể thêm các validation khác)
        if (!videoId || !title || !href) {
          alert("Vui lòng điền đầy đủ thông tin video.");
          return;
        }

        // Tạo object video
        var video = {
          videoId: videoId,
          title: title,
          description: description,
          href: href,
          isActive: isActive === 'true'
        };

        // Gửi yêu cầu AJAX đến server
        $.ajax({
          url: '/Asm_Java4.1/admin/video?action=edit', // Thay thế bằng URL của API sửa video
          type: 'POST', // Hoặc 'GET' tùy thuộc vào yêu cầu của server
          data: video, // Gửi dữ liệu video
          success: function(data) {
            // Xử lý kết quả trả về từ server (ví dụ: reload lại trang)
            alert('Video đã được cập nhật!');
            window.location.href = "http://localhost:8080/Asm_Java4.1/admin/video?action=list";
          },
          error: function(error) {
            // Xử lý lỗi nếu có
            console.error('Lỗi khi cập nhật video:', error);
            alert('Cập nhật video thất bại!');
          }
        });
      });
	  $('#href').on('input', function() {
		var href = $(this).val();
		var videoId = getVideoIdFromUrl(href); // Hàm này sẽ được định nghĩa ở bước 3
		if (videoId) {
		  var thumbnailUrl = 'https://img.youtube.com/vi/' + videoId + '/hqdefault.jpg';
		  $('#thumbnailImg').attr('src', thumbnailUrl);
		} else {
		  $('#thumbnailImg').attr('src', ''); // Xóa ảnh nếu không tìm thấy ID
		}
	  });
	  
	  // Hàm để trích xuất ID video từ URL YouTube
	  function getVideoIdFromUrl(url) {
		var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|&v=)([^#&?]*).*/;
		var match = url.match(regExp);
		if (match && match[2].length === 11) {
		  return match[2];
		} else {
		  return null;
		}
	  }
	</script>
</body>
</html>