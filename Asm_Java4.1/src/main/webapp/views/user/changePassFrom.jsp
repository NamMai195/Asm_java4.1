<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Đổi mật khẩu</title>
  <%@ include file="/common/taglist.jsp"%>
  <%@ include file="/common/head.jsp"%>
  <style>
    /* CSS để tạo giao diện giống với hình ảnh */
    .form-container {
      border: 1px solid #ddd; /* Viền xám nhạt */
      padding: 20px;
    }

    .form-container h1 {
      color: #333; /* Màu xám đậm */
      padding: 10px;
      text-align: center;
      margin-bottom: 20px;
    }

    .form-group label {
      font-weight: bold;
    }

    .btn-primary {
      background-color: #007bff; /* Màu xanh dương mặc định của Bootstrap */
      border-color: #007bff;
    }
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
                  <form action="changepass" method="post" onsubmit="return validatePassword()">
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

  <script>
    function validatePassword() {
      var newPassword = document.getElementById("newPassword").value;
      var confirmPassword = document.getElementById("confirmPassword").value;
      if (newPassword !== confirmPassword) {
        alert("Mật khẩu mới và xác nhận mật khẩu không khớp!");
        return false; 
      }
      return true; 
    }
  </script>
</body>
</html>