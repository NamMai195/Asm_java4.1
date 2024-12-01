<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglist.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | User Management</title>
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
                            <h1 class="m-0">User</h1>
                        </div>
                    </div>
                </div>
            </div>

            <section class="content">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">User Management</h3>
                    </div>
                    <div class="card-body">
                        <form id="userForm">
                            <div class="form-group">
                                <label for="userId">User ID</label> 
                                <input type="text" id="userId" name="userId" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="username">Username:</label> 
                                <input type="text" class="form-control" id="username" name="username" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Password:</label> 
                                <input type="password" class="form-control" id="password" name="password">
                            </div>
                            <div class="form-group">
                                <label for="email">Email:</label> 
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                            <div class="form-group">
                                <label for="isAdmin">isAdmin:</label> 
                                <select class="form-control" id="isAdmin" name="isAdmin">
                                    <option value="true">Admin</option>
                                    <option value="false">User</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="isActive">isActive:</label> 
                                <select class="form-control" id="isActive" name="isActive">
                                    <option value="true">Hoạt động</option>
                                    <option value="false">Bị khóa</option>
                                </select>
                            </div>
                            <button type="button" id="btnAdd" class="btn btn-primary">Add</button>
                            <button type="button" id="btnSave" class="btn btn-success">Save</button>
                        </form>

                        <table id="example1" class="table table-bordered table-striped mt-3">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Username</th>
                                    <th>Password</th>
                                    <th>Email</th>
                                    <th>isAdmin</th>
                                    <th>isActive</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${users}" var="user">
                                    <tr data-user-id="${user.id}"
                                        data-username="${user.username}"
                                        data-password="${user.password}"
                                        data-email="${user.email}"
                                        data-is-admin="${user.role}"
                                        data-is-active="${user.isActive}">
                                        <td>${user.id}</td>
                                        <td>${user.username}</td>
                                        <td>${user.password}</td>
                                        <td>${user.email}</td>
                                        <td>${user.role ? "Admin" : "User"}</td>
                                        <td>${user.isActive ? "Hoạt động" : "Bị khóa"}</td>
                                        <td>
                                            <button type="button" class="btn btn-danger btn-sm" onclick="deleted('${user.id}')">
                                                <i class="fas fa-trash"></i> Delete
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Username</th>
                                    <th>Password</th>
                                    <th>Email</th>
                                    <th>isAdmin</th>
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
                var userId = $(this).data('user-id');
                var username = $(this).data('username');
                var password = $(this).data('password');
                var email = $(this).data('email');
                var isAdmin = $(this).data('is-admin');
                var isActive = $(this).data('is-active'); // Lấy giá trị isActive

                $('#userId').val(userId);
                $('#username').val(username);
                $('#password').val(password); 
                $('#email').val(email);
                $('#isAdmin').val(isAdmin ? 'true' : 'false');
                $('#isActive').val(isActive ? 'true' : 'false'); // Set giá trị cho isActive
            }); 
        });

        function deleted(userId) {
            if (confirm("Bạn có chắc chắn muốn xóa người dùng này?")) {
                $.ajax({
                    url: '/Asm_Java4.1/admin/user?action=delete&id=' + userId,
                    type: 'GET' // Hoặc 'POST' tùy thuộc vào yêu cầu của server
                }).then(function(data) {
                    // Reload lại trang hoặc cập nhật danh sách người dùng
                    window.location.href = "http://localhost:8080/Asm_Java4.1/admin/user?action=list";
                }).fail(function(error) {
                    alert("Lỗi khi xóa người dùng");
                    console.error('Lỗi:', error);
                });
            }
        }

        $('#btnAdd').click(function() {
            // Lấy dữ liệu từ form
            var userId = $('#userId').val();
            var username = $('#username').val();
            var password = $('#password').val();
            var email = $('#email').val();
            var isAdmin = $('#isAdmin').val();
            var isActive = $('#isActive').val(); // Lấy giá trị isActive

            // Kiểm tra dữ liệu (có thể thêm các validation khác)
            if (!userId || !username || !password || !email) {
              alert("Vui lòng điền đầy đủ thông tin người dùng.");
              return;
            }

            // Tạo object user
            var user = {
              userId: userId,
              username: username,
              password: password,
              email: email,
              isAdmin: isAdmin === 'true',
              isActive: isActive === 'true' // Thêm isActive vào object user
            };

            // Gửi yêu cầu AJAX đến server
            $.ajax({
              url: '/Asm_Java4.1/admin/user?action=add', // Thay thế bằng URL của API thêm người dùng
              type: 'POST', // Hoặc 'GET' tùy thuộc vào yêu cầu của server
              data: user, // Gửi dữ liệu người dùng
              success: function(data) {
                // Xử lý kết quả trả về từ server (ví dụ: reload lại trang)
                alert('Người dùng đã được thêm!');
                window.location.href = "http://localhost:8080/Asm_Java4.1/admin/user?action=list";
              },
              error: function(error) {
                // Xử lý lỗi nếu có
                console.error('Lỗi khi thêm người dùng:', error);
                // Hiển thị thông báo lỗi từ server (nếu có)
                if (error.responseJSON && error.responseJSON.error) {
                  alert(error.responseJSON.error);
                } else {
                  alert('Thêm người dùng thất bại!');
                }
              }
            });
          });

        $('#btnSave').click(function() {
            // Lấy dữ liệu từ form
            var userId = $('#userId').val();
            var username = $('#username').val();
            var password = $('#password').val();
            var email = $('#email').val();
            var isAdmin = $('#isAdmin').val();
            var isActive = $('#isActive').val(); // Lấy giá trị isActive

            // Kiểm tra dữ liệu (có thể thêm các validation khác)
            if (!userId || !username || !email) {
              alert("Vui lòng điền đầy đủ thông tin người dùng.");
              return;
            }

            // Tạo object user
            var user = {
              userId: userId,
              username: username,
              password: password,
              email: email,
              isAdmin: isAdmin === 'true',
              isActive: isActive === 'true' // Thêm isActive vào object user
            };

            // Gửi yêu cầu AJAX đến server
            $.ajax({
              url: '/Asm_Java4.1/admin/user?action=edit', // Thay thế bằng URL của API sửa người dùng
              type: 'POST', // Hoặc 'GET' tùy thuộc vào yêu cầu của server
              data: user, // Gửi dữ liệu người dùng
              success: function(data) {
                // Xử lý kết quả trả về từ server (ví dụ: reload lại trang)
                alert('Người dùng đã được cập nhật!');
                window.location.href = "http://localhost:8080/Asm_Java4.1/admin/user?action=list";
              },
              error: function(error) {
                // Xử lý lỗi nếu có
                console.error('Lỗi khi cập nhật người dùng:', error);
                alert('Cập nhật người dùng thất bại!');
              }
            });
          });
    </script>
</body>
</html>