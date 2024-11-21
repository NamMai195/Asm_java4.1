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
						<h2 class="text-center mb-4">Đăng Nhập</h2>
                        <div class="form-container"> 
							<form action="login" method="post">
								<div class="mb-3">
									<label for="email" class="form-label">Username</label> <input
										type="text" class="form-control" id="username" name="username"
										placeholder="Nhập username" required>
								</div>
								<div class="mb-3">
									<label for="password" class="form-label">Mật khẩu</label> <input
										type="password" class="form-control" id="password" name="password"
										placeholder="Nhập mật khẩu" required>
								</div>
								<div class="mb-3 form-check">
									<input type="checkbox" class="form-check-input" id="rememberMe">
									<label class="form-check-label" for="rememberMe">Ghi nhớ
										tôi</label>
								</div>
								<button type="submit" class="btn btn-primary w-100">Đăng
									Nhập</button>
							</form>
							<div class="text-center mt-3">
								<a href="${pageContext.request.contextPath}/forgot">Quên mật khẩu?</a>
							</div>
						</div>
						<%@ include file="/common/footer.jsp"%>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/common/linksctrip.jsp"%>
</body>
</html>