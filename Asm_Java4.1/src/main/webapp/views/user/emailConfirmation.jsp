<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Xác nhận thay đổi email</title>
  <%@ include file="/common/head.jsp"%> 
  <style>
    .form-container {
      width: 50%;
      margin: 0 auto;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 5px;
    }
  </style>
</head>
<body>
  <div class="wrapper">
    <%@ include file="/common/header.jsp"%> 
    <div class="f-content">
      <div class="container">
        <div class="form-container">
          <h2 class="text-center mb-4">Xác nhận thay đổi email</h2>
          <p>Vui lòng nhập mã xác nhận bạn nhận được trong email để hoàn tất thay đổi địa chỉ email.</p>
          <form action="confirmEmail" method="post"> 
            <div class="form-group">
              <label for="token">Mã xác nhận:</label>
              <input type="text" class="form-control" id="token" name="token" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Xác nhận</button>
          </form>
        </div>
      </div>
    </div>
    <%@ include file="/common/footer.jsp"%> 
  </div>
</body>
</html>