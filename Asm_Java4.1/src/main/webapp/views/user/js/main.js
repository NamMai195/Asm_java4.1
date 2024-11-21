$(document).ready(function(){
	$("#nav-input-search" ).focus(function() {
	  	$( "#nav-input-search" ).addClass('focus');
	});

	$('#new-post-slide').owlCarousel({
	    loop:false,
	    margin:20,
	    nav:true,
	    dots: false,
	    autoplay: true,
	    responsive:{
	        0:{
	            items:1
	        },
	        500:{
	            items:2
	        },
	        1000:{
	            items:2
	        }
	    },
	    navText: ["<i class='fa fa-angle-left' aria-hidden='true'></i>","<i class='fa fa-angle-right' aria-hidden='true'></i>"]
	});

	$('.cate-post-slide').owlCarousel({
	    loop:false,
	    margin:20,
	    nav:true,
	    dots: false,
	    autoplay: true,
	    responsive:{
	        0:{
	            items:2
	        },
	        500:{
	            items:3
	        },
	        1000:{
	            items:4
	        }
	    },
	    navText: ["<i class='fa fa-angle-left' aria-hidden='true'></i>","<i class='fa fa-angle-right' aria-hidden='true'></i>"]
	});

	var height_thumn = $(".cate-post-slide .cate-post-item .post-avatar").height();
	top_nav_slide = (height_thumn / 2) - 15 +'px';
	$(".cate-post-slide .owl-nav .owl-prev").css("top", top_nav_slide);
	$(".cate-post-slide .owl-nav .owl-next").css("top", top_nav_slide);

	// recently post

	$('#recent-post-tab').click(function() {
		$('#recent-tabContent #more-post').removeClass('active');
		$('#recent-tabContent #more-post').removeClass('show');
	});

	$('body').append('<h1 class="sr-only">Blog fuvavi.com - Thủ thuật Frontend - Kinh nghiệm thiết kế web - Design - Share code</h1> <h2 class="sr-only">Bản quyền của FUVAVI BLOG</h2>');
});