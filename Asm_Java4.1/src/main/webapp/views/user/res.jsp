<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Blog Fuvavi.com</title>

<%@ include file="/common/head.jsp"%>
<style>
  .form-container {
    width: 50%; /* Điều chỉnh độ rộng của form */
    margin: 0 auto; /* Căn giữa form */
  }
</style>
</head>

<body>
    <div class="wrapper">
        <%@ include file="/common/headeruser.jsp"%>
        <div class="f-content">
            <div class="container">
                <div class="row flex-xl-nowrap">
                    <div class="col-12 fvv-content"> 

                        <h2 class="text-center mb-4">Form Đăng Ký</h2>
                        <div class="form-container"> 
                            <form id="registrationForm" method="post">
                                <div class="mb-3">
                                    <label for="fullName" class="form-label">Nhập Username</label> <input
                                        type="text" class="form-control" id="id" name="id"
                                        placeholder="Nhập username không dấu không cách" required>
                                </div>
                                <div class="mb-3">
                                    <label for="fullName" class="form-label">Họ và tên</label> <input
                                        type="text" class="form-control" id="fullName" name="fullName"
                                        placeholder="Nhập họ và tên" required>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label> <input
                                        type="email" class="form-control" id="email" name="email"
                                        placeholder="Nhập email" required>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Mật khẩu</label> <input
                                        type="password" class="form-control" id="password" name="password"
                                        placeholder="Nhập mật khẩu" required>
                                </div>
                                <div class="mb-3">
                                    <label for="confirmPassword" class="form-label">Xác nhận
                                        mật khẩu</label> <input type="password" class="form-control"
                                        id="confirmPassword" placeholder="Nhập lại mật khẩu" required>
                                    <div id="passwordError" class="text-danger"
                                        style="display: none;">Mật khẩu không khớp</div>
                                </div>
                                <div class="mb-3 form-check">
                                    <input type="checkbox" class="form-check-input" id="terms">
                                    <label class="form-check-label" for="terms">Tôi đồng ý
                                        với các điều khoản</label>
                                </div>
                                <button type="submit" class="btn btn-primary w-100">Đăng
                                    ký</button>
                            </form>
                        </div> 

                        <%@ include file="/common/footer.jsp"%>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/common/linksctrip.jsp"%>
    </div>

    <script>
        document.getElementById('registrationForm').onsubmit = function(event) {
            var password = document.getElementById('password').value;
            var confirmPassword = document.getElementById('confirmPassword').value;
            var passwordError = document.getElementById('passwordError');

            if (password !== confirmPassword) {
                passwordError.style.display = 'block';
                event.preventDefault(); 
            } else {
                passwordError.style.display = 'none';
            }
        };
    </script>
    
</body>
</html>