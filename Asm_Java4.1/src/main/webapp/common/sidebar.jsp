<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-12 col-md-3 col-xl-3 fvv-sidebar">
	<div class="fvv-sidebar-content">
		<div class="list-f-sidebar">
			<div class=""></div>
		</div>
		<div class="list-f-sidebar">
			<h3>Thể Loại</h3>
			<ul>
				<li><a href="${pageContext.request.contextPath}/index"> <i
						class="fa fa-desktop" aria-hidden="true"></i> Tất Cả
				</a></li>
			</ul>
		</div>
		<c:choose>
			<c:when test="${not empty currentUser}">
				<%-- Hiển thị thông tin cho người dùng đã đăng nhập --%>
				<div class="list-f-sidebar">
					<h3>Danh Sách</h3>
					<ul>
						<li><a href="${pageContext.request.contextPath}/history">
								<i class="fa fa-bookmark" aria-hidden="true"></i> Lịch sử
						</a></li>
						
						<li><a href="${pageContext.request.contextPath}/favorite">
								<i class="fa fa-star" aria-hidden="true"></i> Yêu Thích
						</a></li>
					</ul>
				</div>
			</c:when>
			<c:otherwise>
				<%-- Hiển thị thông tin cho người dùng chưa đăng nhập --%>
				<div class="list-f-sidebar">
					<h3>Danh Sách</h3>
					<ul>
						<li><a href="#"> <i class="fa fa-star" aria-hidden="true"></i>
								Mới Nhất
						</a></li>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>



	</div>
</div>