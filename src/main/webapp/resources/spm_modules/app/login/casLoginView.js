
$(function(){
	
	$.i18n.properties({//加载资浏览器语言对应的资源文件
		name: ["messages"], //资源文件名称，可以是数组
		path: _i18n_res, //资源文件路径
		mode: 'both',
        cache: true,
		language: _language
        //checkAvailableLanguages: true,
		//async: false
	});

	if (self != top) {  
	    var messenger = new Messenger('mainFrame', 'MgmtMessage'),
	    input = document.getElementById('message');
		messenger.addTarget(window.parent, 'parent');
		sendMessage();
		function sendMessage() {messenger.targets['parent'].send("caslogin"); }}
	
	var errors=$("#errorMsg").html();
	if(isNull(errors)){
		resetErrMsg();
	}
	else{
		showErrMsg(errors);
	}
	
	
	$("#username").bind("blur",function(){
		resetErrMsg();
	});
	$("#password").bind("blur",function(){
		resetErrMsg();
	});
	
});

function showErrMsg(msg){
	$("#errorMsg").html(msg);
	//$("div.loginErr").show();
}
function resetErrMsg(){
	$("#errorMsg").html("");
	//$("div.loginErr").hide();
}

  function encryptPwd(event){
  	if (event.keyCode == 13){//IE Chrome 回车键
  		dologin(); 
  	}
  	else {
  		if (event.which == 13){//Firefox 回车键
  			dologin(); 
  		}
  	}
  }//end of encryPwd
  
  function dologin() {
  	if(validate()){ 
		var inputPassword = document.getElementById("password").value;
		/*var onceCode = "AIOPT_SALT_KEY";
		var passwordMd5 = hex_md5(onceCode
				+ hex_md5(inputPassword));*/
		document.getElementById("password").value = inputPassword;
		document.getElementById("username").value = $.trim(document
				.getElementById("username").value);

		//提交表单
		document.getElementById('fm1').submit();
		return true;
  	 }
  	else{
  		return false;
  	} 
	
}//end of dologin
 

function validate() {
	var username=$("#username").val();
	var password=$("#password").val();
	var errorNumCCS=$("#errorNumCCS").val();
	var errorNum = $("#errorNum").val();
    if(username=="Username/Mobile/Email"){
    	username="";
		$("#username").val("");
    }
    if(password=="Password"){
    	password="";
		$("#password").val("");
    }
	try {
		if (isNull(username)) {			
			showErrMsg($.i18n.prop('authenticationFailure.UsernameIsNullException'));
			return false;
		}else{
			resetErrMsg();
		}
		if (isNull(password)) {
			showErrMsg($.i18n.prop('authenticationFailure.PasswordIsNullException'));
			return false;
		}else{
			resetErrMsg();
		}

		if(errorNum>=errorNumCCS){
			var captcha=$("#captchaCode").val();	
			if(captcha=="Verification code"){
				captcha="";
				$("#captchaCode").val("");
		    }
			if (isNull(captcha)) {
				showErrMsg($.i18n.prop('authenticationFailure.CaptchaIsNullException'));
				return false;
			}else{
				resetErrMsg();
			}
		}
		
		
		return true;
	} catch (ex) {
		return false;
	} 			
}//end of validate

// 刷新验证码
function reloadImage(url) {
	document.getElementById('pictureVitenfy').src = url+"?id=" + Math.random();
}

function encryptCaptcha(event) {
	if (event.keyCode == 13) {// IE Chrome 回车键
	dologin();
} else {
	if (event.which == 13) {// Firefox 回车键
			dologin();
		}
	}
}// end of encryPwd
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		$(document).ready(function(){
  			$("#errclose").click(function(){
  				$('.loginErr').hide();
  				
  			})
  		})
  		$("#username").focus(function(){
  			$("#username").css({"background-color":"#D6D6FF","border":"1PX #ddd solid"});
  	  });
  		$("#username").blur(function(){
  	    	$("#username").css({"background-color":"#fff","border":"1PX #ddd solid"});
  	    	if($("#username").val()==''){
  	    		$('.NameEmpty').css("display","block");
  	    	};
  	  });
  		$("#password").focus(function(){
  			$("#password").css({"background-color":"#D6D6FF","border":"1PX #ddd solid"});
  	  });
  		$("#password").blur(function(){
  	    	$("#password").css({"background-color":"#fff","border":"1PX #ddd solid"});
  	    	if($("#username").val()==''){
  	    		$('.PassEmpty').css("display","block");
  	    	};
  	  });