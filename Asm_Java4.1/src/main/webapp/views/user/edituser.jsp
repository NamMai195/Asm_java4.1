<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Chỉnh sửa thông tin cá nhân</title>
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
              <h2 class="fvv-content-title">Chỉnh sửa thông tin cá nhân</h2>
              <div class="fvv-content-body">
                <div class="form-container">
                  <h1>EDIT PROFILE</h1>
                  <form action="edituser" method="post">
                    <div class="form-group">
                      <label for="username">USERNAME?</label>
                      <input type="text" class="form-control" id="username" name="username" value="${sessionScope.currentUser.id}" readonly>
                    </div>
                    <div class="form-group">
                      <label for="password">PASSWORD?</label>
                      <input type="password" class="form-control" id="password" name="password" value="1234567890" disabled> 
                    </div>
                    <div class="form-group">
                      <label for="fullname">FULLNAME?</label>
                      <input type="text" class="form-control" id="fullname" name="fullname" value="${sessionScope.currentUser.username}">
                    </div>
                    <div class="form-group">
                      <label for="email">EMAIL ADDRESS?</label>
                      <input type="email" class="form-control" id="email" name="email" value="${sessionScope.currentUser.email}">
                    </div>
                    <button type="submit" class="btn btn-primary">Update</button> <a href="${pageContext.request.contextPath}/" class="btn btn-warning">Change Password</a> 
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
</body>
</html>