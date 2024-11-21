<script type="text/javascript" src="${pageContext.request.contextPath}/views/user/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/user/libs/popper/umd/popper.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/user/libs/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/views/user/libs/owlcarousel/owl.carousel.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/views/user/js/main.js"></script>

<script type="text/javascript">
	$('.carousel')
			.owlCarousel(
					{
						loop : false,
						margin : 0,
						nav : true,
						dots : false,
						responsive : {
							0 : {
								items : 1
							},
							600 : {
								items : 1
							},
							1000 : {
								items : 1
							}
						},
						navText : [
								"<i class='fa fa-chevron-left' aria-hidden='true'></i>",
								"<i class='fa fa-chevron-right' aria-hidden='true'></i>" ]
					})
</script>

<script>
	window.fbAsyncInit = function() {
		FB.init({
			appId : '131375914163212',
			autoLogAppEvents : true,
			xfbml : true,
			version : 'v2.11'
		});
	};
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) {
			return;
		}
		js = d.createElement(s);
		js.id = id;
		js.src = "https://connect.facebook.net/vi_VN/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
</script>