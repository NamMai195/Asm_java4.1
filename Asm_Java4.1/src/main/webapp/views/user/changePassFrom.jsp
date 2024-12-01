<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1"> 1 
  <title>Đổi mật khẩu</title>
  <%@ include file="/common/taglist.jsp"%>
  <%@ include file="/common/head.jsp"%>
  <style>
    /* ... (CSS) ... */
  </style>
</head>
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
              <h2 class="fvv-content-title">Đổi mật khẩu</h2>
              <div class="fvv-content-body">
                <div class="form-container">
                  <h1>CHANGE PASSWORD</h1>
                  <form action="changepass" method="post"> 
                    <div class="form-group">
                      <label for="oldPassword">Mật khẩu cũ:</label>
                      <input type="password" class="form-control" id="oldPassword" name="oldPassword" required>
                    </div>
                    <div class="form-group">
                      <label for="newPassword">Mật khẩu mới:</label>
                      <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                    </div>
                    <div class="form-group">
                      <label for="confirmPassword">Xác nhận mật khẩu mới:</label>
                      <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Cập nhật</button>
                  </form>
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

  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
  <script>
    $(document).ready(function() {
      $('form').submit(function(event) {
        var newPassword = $('#newPassword').val();
        var confirmPassword = $('#confirmPassword').val();
        if (newPassword !== confirmPassword) {
          event.preventDefault();
          Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Mật khẩu mới và xác nhận mật khẩu không khớp!'
          });
        } else {
          // Hiển thị thông báo SweetAlert2 khi mật khẩu khớp
          event.preventDefault(); // Ngăn form submit mặc định
          Swal.fire({
            icon: 'success',
            title: 'Đổi mật khẩu thành công!',
            text: 'Đang chuyển hướng đến trang xác nhận...',
            showConfirmButton: false,
            timer: 1500 // Đóng sau 1.5 giây
          }).then(function() {
            // Submit form sau khi SweetAlert2 đóng
            $(event.target).unbind('submit').submit(); 
          });
        }
      });
    });
  </script>
</body>
</html>