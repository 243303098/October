//导航选择器
var url = window.location.href;
if (url.indexOf("newsCategoryManage") > 0
		|| url.indexOf("newsCategoryEdit") > 0) {
	$("#news").addClass("active");
	$("#newsCategoryManage").addClass("active");
} else if (url.indexOf("newsManage") > 0 || url.indexOf("newsEdit") > 0) {
	$("#news").addClass("active");
	$("#newsManage").addClass("active");
}

// 提示条配置
toastr.options= {
    "closeButton":false,//显示关闭按钮
    "debug":false,//启用debug
    "positionClass":"toast-top-center",//弹出的位置
    "showDuration":"300",//显示的时间
    "hideDuration":"1000",//消失的时间
    "timeOut":"5000",//停留的时间
    "extendedTimeOut":"1000",//控制时间
    "showEasing":"swing",//显示时的动画缓冲方式
    "hideEasing":"linear",//消失时的动画缓冲方式
    "showMethod":"fadeIn",//显示时的动画方式
    "hideMethod":"fadeOut"//消失时的动画方式
};