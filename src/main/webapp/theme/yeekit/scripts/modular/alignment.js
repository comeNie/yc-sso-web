///**将class转换成ID**/
//$(function () {
//	$(".left-subnav").attr("id","left-subnav");
//  $(".right-list").attr("id","right-list");
//  $(".oder-detailed").attr("id","oder-detailed");
//	});	
//	
///**左右高度对齐**/
//function alignHeight(eleA,eleB){
//if(!document.getElementById(eleA)){return false;}
//if(!document.getElementById(eleB)){return false;}
//var heightA = document.getElementById(eleA).clientHeight;
//var heightB = document.getElementById(eleB).clientHeight;
//if(heightA > heightB){
//  document.getElementById(eleB).style.height = heightA + "px";
//  document.getElementById(eleA).style.height = heightA + "px";
//}else{
//  document.getElementById(eleA).style.height = heightB + "px";
//  document.getElementById(eleB).style.height = heightB + "px";
//}
//}
//window.onload =
//function z_align(){
//alignHeight("left-subnav","right-list"); //只需将需要对齐的两个模块的id写在此处即可。
//alignHeight("left-subnav","oder-detailed"); 
////alignHeight("AAA","BBB") 可依此连续多组。
//}