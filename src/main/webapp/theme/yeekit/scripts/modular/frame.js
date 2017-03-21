$(function () {
    var st = 100;
    $('#user').mouseenter(function () {
		$('#user .show-hover').show(1);
		$('#user .yonh').children('i').addClass("icon-caret-up");
    })
		$("#user .show-hover").click(function () {
                $(this).hide(1);
           });	
		$('#user').mouseleave(function () {
        $('#user .show-hover').hide(1);
        $('#user .yonh').children('i').removeClass("icon-caret-up");
    });	
});

//切换语言1
$(function () {
    var st = 100;
    $('#switch').mouseenter(function () {
		$('#switch .switch-hover').show(1);
		$('#switch a').children('i').addClass("icon-caret-up");
    })
		$("#switch .switch-hover").click(function () {
                $(this).hide(1);
           });	
		$('#switch').mouseleave(function () {
        $('#switch .switch-hover').hide(1);
        $('#switch a').children('i').removeClass("icon-caret-up");
    });	
});
 //切换语言2
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
//左侧菜单关闭
$(function(){
$(".left-tplist i").click(function () {
	$(this).parent('.left-tplist').hide();
	});
	}); 
	
//我的订单 点击展开
$(function () {
    $(".query-order ul .right p").click(function () {
		$(this).children('i').toggleClass("icon-angle-down  icon-angle-up");
		$(this).parent().parent().parent().parent().children('.order-hiddn').slideToggle(100);
    });
});	

//我的订单 table
$(function(){
$(".oder-table ul li a").click(function () {
                $(".oder-table ul li a").each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
            });
$('.oder-table ul li a').click(function(){
  var index=$('.oder-table ul li a').index(this);
     if(index==0){
     $('#table-da1').show();
  	 $('#table-da2').hide();
  	 $('#table-da3').hide();
  	 $('#table-da4').hide();
  	 $('#table-da5').hide();
   }
   if(index==1){
   $('#table-da2').show();
   $('#table-da1').hide();
   $('#table-da3').hide();
   $('#table-da4').hide();
   $('#table-da5').hide();
   }
   if(index==2){
   $('#table-da3').show();
   $('#table-da2').hide();
   $('#table-da1').hide();
   $('#table-da4').hide();
   $('#table-da5').hide();
   }
   if(index==3){
   $('#table-da4').show();
   $('#table-da3').hide();
   $('#table-da2').hide();
   $('#table-da1').hide();
   $('#table-da5').hide();
   }
   if(index==4){
   $('#table-da5').show();
   $('#table-da3').hide();
   $('#table-da2').hide();
   $('#table-da1').hide();
   $('#table-da4').hide();
   }
  }); 
});

//我的订单 table
$(function(){
$(".oder-table ul li a").click(function () {
                $(".oder-table ul li a").each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
            });
$('.oder-table ul li a').click(function(){
  var index=$('.oder-table ul li a').index(this);
     if(index==0){
     $('#translate1').show();
  	 $('#translate2').hide();
   }
   if(index==1){
   $('#translate2').show();
   $('#translate1').hide();
   }
  }); 
});

//订单大厅
$(function () {
    var st = 100;
    $('.right-list-table .dinda').mouseenter(function () {
		$('.table-show').show(1);
    })
		$(".table-show").click(function () {
                $(this).hide(1);
            });
			
		$('.right-list-table').mouseleave(function () {
        $('.table-show').hide(1);
    });	
 }); 

//头像显示
$(function(){
  $(".portrait").mouseenter(function(){
  $(".portrait-file").show();
  });
  $(".portrait-file").mouseleave(function(){
  $(".portrait-file").hide();
  });
  });
  
 
//设置密码
$(function(){
  $("#next-bt1").click(function(){
  $(this).hide();
  $("#next1").show();
  });
   $("#next-bt1").click(function(){
  $("#next1").hide();
  $("#next2").show();
  });
  $("#next-bt2").click(function(){
  $("#next2").hide();
  $("#next3").show();
  });
  $("#next-bt4").click(function(){
  $("#next4").hide();
  $("#next5").show();
  });
  $("#next-bt5").click(function(){
  $("#next5").hide();
  $("#next6").show();
  });
  $("#fy-btn").click(function(){
  $("#fy1").hide();
  $("#fy2").show();
  });
  $("#fy-btn1").click(function(){
  $("#fy2").hide();
  $("#fy1").show();
  });
  $("#back-btn").click(function(){
  $("#back-pass").hide();
  $("#back-pass1").show();
  });
});

//设置密码
$(function(){
  $("#screen-a").click(function(){
  $(this).hide();
  $("#screen-b").show();
  });
   $("#screen-b").click(function(){
  $(this).hide();
  $("#screen-a").show();
  });
});

//设置密码
//$(function(){
//$("#tab-icon1").click(function(){
//$(this).hide();
//$("#tab-icon2").show();
//$("#tab-icon3").show();
//$("#tab-icon5").show();
//$("#tab-icon7").show();
//$("#tab-icon4").hide();
//$("#tab-icon6").hide();
//$("#tab-icon8").hide();
//});
//$("#tab-icon3").click(function(){
//$(this).hide();
//$("#tab-icon1").show();
//$("#tab-icon5").show();
//$("#tab-icon7").show();
//$("#tab-icon4").show();
//$("#tab-icon2").hide();
//$("#tab-icon6").hide();
//$("#tab-icon8").hide();
//});
//$("#tab-icon5").click(function(){
//$(this).hide();
//$("#tab-icon6").show();
//$("#tab-icon1").show();
//$("#tab-icon3").show();
//$("#tab-icon7").show();
//$("#tab-icon2").hide();
//$("#tab-icon4").hide();
//$("#tab-icon8").hide();
//});
//$("#tab-icon7").click(function(){
//$(this).hide();
//$("#tab-icon8").show();
//$("#tab-icon1").show();
//$("#tab-icon3").show();
//$("#tab-icon5").show();
//$("#tab-icon2").hide();
//$("#tab-icon4").hide();
//$("#tab-icon6").hide();
//});
//});  
////设置密码
//$(function(){
//$("#tab-icon13").click(function(){
//$(this).hide();
//$("#tab-icon14").show();
//$("#tab-icon9").show();
//$("#tab-icon11").show();
//$("#tab-icon10").hide();
//$("#tab-icon12").hide();
//});
//$("#tab-icon9").click(function(){
//$(this).hide();
//$("#tab-icon10").show();
//$("#tab-icon11").show();
//$("#tab-icon13").show();
//$("#tab-icon14").hide();
//$("#tab-icon12").hide();
//});
//$("#tab-icon11").click(function(){
//$(this).hide();
//$("#tab-icon12").show();
//$("#tab-icon13").show();
//$("#tab-icon9").show();
//$("#tab-icon14").hide();
//$("#tab-icon10").hide();
//});
//});  
//设置密码 table
$(function(){
$(".set-up a").click(function () {
                $(".set-up a").each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
            });
$('.set-up a').click(function(){
  var index=$('.set-up a').index(this);
     if(index==0){
     $('#set-table1').show();
  	 $('#set-table2').hide();
   }
   if(index==1){
   $('#set-table2').show();
   $('#set-table1').hide();
   }
  }); 
});
//翻译 table
$(function(){
$(".prompt-center-title ul li a").click(function () {
                $(".prompt-center-title ul li a").each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
            });
$('.prompt-center-title ul li a').click(function(){
  var index=$('.prompt-center-title ul li a').index(this);
     if(index==0){
     $('#tran-tab1').show();
  	 $('#tran-tab2').hide();
  	 $('#tran-tab3').hide();
   }
   if(index==1){
   $('#tran-tab2').show();
   $('#tran-tab1').hide();
   $('#tran-tab3').hide();
   }
   if(index==2){
   $('#tran-tab3').show();
   $('#tran-tab2').hide();
   $('#tran-tab1').hide();
   }
  }); 
});

/**翻译下单去掉最后的线条**/
$(function () {
$(".attachment  ul:last").css("border-bottom","none");
});
//翻译下单关闭附件
$(function(){
$(".attachment ul li i").click(function () {
	$(this).parent().parent('ul').hide();
	});
	}); 
	

//协议tba
$(function(){
$(".subnav-left ul li a").click(function () {
                $(".subnav-left ul li a").each(function () {
                    $(this).removeClass("current");
                });
                $(this).addClass("current");
            });
$('.subnav-left ul li a').click(function(){
  var index=$('.subnav-left ul li a').index(this);
    if(index==0){
     $('#tab-xy1').show();
  	 $('#tab-xy2').hide();
  	 $('#tab-xy3').hide();
  	 $('#tab-xy4').hide();
  	 $('#tab-xy5').hide();
  	 $('#tab-xy6').hide();
  	 $('#tab-xy7').hide();
  	 $('#tab-xy8').hide();
  	 $('#tab-xy9').hide();
  	 $('#tab-xy10').hide();
   }
   if(index==1){
  	 $('#tab-xy2').show();
  	 $('#tab-xy1').hide();
  	 $('#tab-xy3').hide();
  	 $('#tab-xy4').hide();
  	 $('#tab-xy5').hide();
  	 $('#tab-xy6').hide();
  	 $('#tab-xy7').hide();
  	 $('#tab-xy8').hide();
  	 $('#tab-xy9').hide();
  	 $('#tab-xy10').hide();
   }
   if(index==2){
   	 $('#tab-xy3').show();
  	 $('#tab-xy2').hide();
  	 $('#tab-xy1').hide();
  	 $('#tab-xy4').hide();
  	 $('#tab-xy5').hide();
  	 $('#tab-xy6').hide();
  	 $('#tab-xy7').hide();
  	 $('#tab-xy8').hide();
  	 $('#tab-xy9').hide();
  	 $('#tab-xy10').hide();
   }
   if(index==3){
   	 $('#tab-xy4').show();
  	 $('#tab-xy3').hide();
  	 $('#tab-xy2').hide();
  	 $('#tab-xy1').hide();
  	 $('#tab-xy5').hide();
  	 $('#tab-xy6').hide();
  	 $('#tab-xy7').hide();
  	 $('#tab-xy8').hide();
  	 $('#tab-xy9').hide();
  	 $('#tab-xy10').hide();
   }
   if(index==4){
   	 $('#tab-xy5').show();
  	 $('#tab-xy3').hide();
  	 $('#tab-xy2').hide();
  	 $('#tab-xy1').hide();
  	 $('#tab-xy4').hide();
  	 $('#tab-xy6').hide();
  	 $('#tab-xy7').hide();
  	 $('#tab-xy8').hide();
  	 $('#tab-xy9').hide();
  	 $('#tab-xy10').hide();
   }
   if(index==5){
   	 $('#tab-xy6').show();
  	 $('#tab-xy3').hide();
  	 $('#tab-xy2').hide();
  	 $('#tab-xy1').hide();
  	 $('#tab-xy4').hide();
  	 $('#tab-xy5').hide();
  	 $('#tab-xy7').hide();
  	 $('#tab-xy8').hide();
  	 $('#tab-xy9').hide();
  	 $('#tab-xy10').hide();
   }
   if(index==6){
   	 $('#tab-xy7').show();
  	 $('#tab-xy3').hide();
  	 $('#tab-xy2').hide();
  	 $('#tab-xy1').hide();
  	 $('#tab-xy4').hide();
  	 $('#tab-xy5').hide();
  	 $('#tab-xy6').hide();
  	 $('#tab-xy8').hide();
  	 $('#tab-xy9').hide();
  	 $('#tab-xy10').hide();
   }
   if(index==7){
   	 $('#tab-xy8').show();
  	 $('#tab-xy3').hide();
  	 $('#tab-xy2').hide();
  	 $('#tab-xy1').hide();
  	 $('#tab-xy4').hide();
  	 $('#tab-xy5').hide();
  	 $('#tab-xy7').hide();
  	 $('#tab-xy6').hide();
  	 $('#tab-xy9').hide();
  	 $('#tab-xy10').hide();
   }
   if(index==8){
   	 $('#tab-xy9').show();
  	 $('#tab-xy3').hide();
  	 $('#tab-xy2').hide();
  	 $('#tab-xy1').hide();
  	 $('#tab-xy4').hide();
  	 $('#tab-xy5').hide();
  	 $('#tab-xy7').hide();
  	 $('#tab-xy6').hide();
  	 $('#tab-xy8').hide();
  	 $('#tab-xy10').hide();
   }
   if(index==9){
   	 $('#tab-xy10').show();
  	 $('#tab-xy3').hide();
  	 $('#tab-xy2').hide();
  	 $('#tab-xy1').hide();
  	 $('#tab-xy4').hide();
  	 $('#tab-xy5').hide();
  	 $('#tab-xy7').hide();
  	 $('#tab-xy6').hide();
  	 $('#tab-xy8').hide();
  	 $('#tab-xy9').hide();
   }
  }); 
});


//译者咨询
$(function () {
    $(".consultation-list ul li a").click(function () {
		$(this).children('i').toggleClass("icon-angle-down  icon-angle-up");
		$(this).toggleClass("color-blue");
		$(this).parent().parent().parent().parent().children('.consultation-show').slideToggle(100);
		
    });
});	

//译者认证 table
$(function(){
$(".static-tab ul li").click(function () {
                $(".static-tab ul li").each(function () {
                    $(this).removeClass("current");
                });
                $(".static-tab ul li a").each(function () {
                    $(this).find("p").eq(1).hide();
                    $(this).find("p").eq(0).show();
                });
                $(this).find("p").eq(0).hide();
                $(this).find("p").eq(1).show();

                $(this).addClass("current");
            });
$('.static-tab ul li').click(function(){
  var index=$('.static-tab ul li').index(this);
     if(index==0){
     $('#renz-table1').show();
  	 $('#renz-table2').hide();
  	 $('#renz-table3').hide();
  	 $('#renz-table4').hide();
   }
   if(index==1){
   $('#renz-table2').show();
   $('#renz-table1').hide();
   $('#renz-table3').hide();
   $('#renz-table4').hide();
   }
   if(index==2){
   $('#renz-table3').show();
   $('#renz-table2').hide();
   $('#renz-table1').hide();
   $('#renz-table4').hide();
   }
    if(index==3){
   $('#renz-table4').show();
   $('#renz-table3').hide();
   $('#renz-table2').hide();
   $('#renz-table1').hide();
   }
  }); 
});

$(function () {
    $("#inselect-bj").click(function () {
		$('#inselect-bj').css("background","#2361ea");
    });
});	
