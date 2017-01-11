$(function () {
    var st = 100;
    $('#in-switch').mouseenter(function () {
		$('.in-switch-hover').show(1);
		$('#in-switch a').children('i').addClass("icon-caret-up");
    })
		$(".in-switch-hover").click(function () {
                $(this).hide(1);
           });	
		$('.placeorder-breadcrumb').mouseleave(function () {
        $('.in-switch-hover').hide(1);
        $('#in-switch a').children('i').removeClass("icon-caret-up");
    });	
});
$(document).ready(function(){
  $("#int-before").click(function(){
    $(".index-wrapper").animate({top:'100px'},300);
    $(".banner").css({"height":"343px"});
  });
});
$(function () {
    $(".banner-hover p").click(function () {
		$(this).children('i').toggleClass(" icon-angle-down icon-angle-up");
		$(this).parents().parent().children('.banner-sm').slideToggle(50);
    });
});	
//图标遮罩
$(function(){
	$(".item2").hover(
		function(){
			var that=this;
			item2Timer=setTimeout(function(){
				$(that).find('.caption').slideDown(300);
				$(that).find('.item2-txt').fadeOut(200);
			},100);
			
		},
		function(){
			var that=this;
			clearTimeout(item2Timer);
			$(that).find('.caption').slideUp(300);
			$(that).find('.item2-txt').fadeIn(200);
		}
	);
});
//设置密码
$(function(){
  $("#error").click(function(){
  $("#error").hide();
  $("#error-oc").show();preser-btn
  });
  $("#preser-btn").click(function(){
  $("#error-oc").hide();
  $("#error").show();
  });
  $("#preser-close").click(function(){
  $("#error-oc").hide();
  $("#error").show();
  });
});
//首页信息提示
$(function () {
    var st = 100;
    $('.post-cion #sus-top').mouseenter(function () {
		$('.suspension').show(1);
    })
    	$('#sus-top').mouseleave(function () {
        $('.suspension').hide(1);
   }); 
   $('.post-cion #sus-top1').mouseenter(function () {
		$('.suspension1').show(1);
    })
		$('#sus-top1').mouseleave(function () {
        $('.suspension1').hide(1);
   });
    $('.post-cion #sus-top2').mouseenter(function () {
		$('.suspension2').show(1);
    })
		$('#sus-top2').mouseleave(function () {
        $('.suspension2').hide(1);
   });
   $('.post-cion #sus-top3').mouseenter(function () {
		$('.suspension3').show(1);
    })
		$('#sus-top3').mouseleave(function () {
        $('.suspension3').hide(1);
   });
    
  });
  
//
$(function(){
  $("#img1").mouseenter(function(){
  $(".jiaicon1").hide();
  $(".jiaicon2").show();
  $(".jiaicon3").show();
  });
  $("#img2").mouseenter(function(){
  $(".jiaicon2").hide();
  $(".jiaicon1").show();
  $(".jiaicon3").show();
  });
   $("#img3").mouseenter(function(){
  $(".jiaicon3").hide();
  $(".jiaicon1").show();
  $(".jiaicon2").show();
  });
});
//风琴
$(function () {
    var $centerwell_first = $('#centerwell li:first');
    $centerwell_first.animate({ width: '500px' }, 300);
    $centerwell_first.find('h3').addClass("on");

    $('#centerwell li').mouseenter(function () {
        if (!$(this).is(':animated')) {
            $(this).animate({ width: '500px' }, 300).siblings().animate({ width: '216px' }, 300);

            $('#centerwell li h3').removeClass("on");
            $(this).find("h3").addClass("on");
        }
    });
});