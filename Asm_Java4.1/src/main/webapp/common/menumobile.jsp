<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="modal left fade" id="fvv-menu-mb" tabindex="-1" role="dialog" aria-labelledby="f-menu-mb-label">
			<div class="modal-dialog" role="document">
				<div class="fvv-menu-mb-header">
					<div class="btn-menu-mb">
						<a href="javascript:void(0)" data-dismiss="modal">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</a>
					</div>
					<div class="logo">
						<a href="#">
							<img src="images/logo-fuvavi.png" alt="">
						</a>
					</div>
				</div>
				<div class="modal-content">
					<div class="fvv-sidebar">
						<div class="fvv-sidebar-content">
							<div class="list-f-sidebar">
								
							</div>
							
							<div class="list-f-sidebar">
								<h3>Chuyên mục</h3>
								<ul>
									<li>
										<a href="${pageContext.request.contextPath}/index">
											<i class="fa fa-desktop" aria-hidden="true"></i> Hài Kịch
										</a>
									</li>
								</ul>
							</div>
							<c:choose>
			<c:when test="${not empty sessionScope.currentUser}">
				<div class="list-f-sidebar">
					<h3>Danh Sách</h3>
					<ul>
						<li><a href="${pageContext.request.contextPath}/history"> <i class="fa fa-bookmark"
								aria-hidden="true"></i> Lịch sử
						</a></li>
						
						<li><a href="${pageContext.request.contextPath}/favorite"> <i class="fa fa-star" aria-hidden="true"></i>
								Yêu Thích
						</a></li>
					</ul>
				</div>

			</c:when>
			<c:otherwise>
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

				</div>
			</div>
		</div>