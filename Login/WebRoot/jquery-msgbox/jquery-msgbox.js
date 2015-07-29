/**

提示框_李占宏_2012-05-22

log:
2012-07-18 改进 新增:是否显示关闭按钮-显示图标

例句:
1页面引用文件:
<link rel="stylesheet" type="text/css" href="<%= Url.Content("~/Scripts/Plugin/jquery-msgbox/jquery-msgbox.css") %>" />
<script type="text/javascript" src="<%= Url.Content("~/Scripts/Plugin/jquery-msgbox/jquery-msgbox.js") %>"></script>

2页面初始化:
$(document).ready(function () {
	$.msgBox.initMsgBox();//初始化
});

3页面调用:
$.msgBox.showMsgBox({//显示提示框
	title: "系统提示", //标题
	content: "测试提示框!<br/>测试...", //内容
	width: 300, //宽(可选属性,值为0和不写此属性效果相同)
	height: 0, //高(可选属性,值为0和不写此属性效果相同,不建议使用此属性)
	enabled: 1, //是否显示关闭按钮
	ico: "auto",//显示图标(auto:随机选择一个图标,true:随机选择一个积极的图标,false:随机选择一个消极的图标,gx:高兴,gz:鼓掌,han:汗,hao:好,atm:奥特曼,wx:微笑,yw:疑问,wbs:挖鼻屎,sj:时间,loading:加载)
	color:"red"//提示字体颜色,
    closeFun: function () {//关闭事件
        //js语句
    }
});

**/

var _closeFun_msgBox;
jQuery.msgBox = {
    initMsgBox: function () {//初始化提示框
        InitMsgBox_MsgBox();
    },
    showMsgBox: function (args) {//显示提示框(必须传的参数有title\content\enabled\ico\color;width和height可以不传;height不建议传)
        ShowMsgBox_MsgBox(args.title, args.content, args.width, args.height, args.enabled, args.ico, args.color);
        _closeFun_msgBox= args.closeFun;
    },
    closeMsgBox: function () {//关闭提示框
        CloseMsgBox_MsgBox();
    }
}
function InitMsgBox_MsgBox() {
    jQuery(document).find("body").append("<div class=\"divDialogBG_msgBox\" id=\"divDialogBG_msgBox\"></div><div class=\"div_msgBox\" id=\"div_msgBox\"><ul class=\"ui_msgBox\"><li class=\"liTitle_msgBox\" id=\"liTitle_msgBox\">标题</li><li class=\"liClose_msgBox\" id=\"liClose_msgBox\"><a href=\"javascript:CloseMsgBox_MsgBox();\" title=\"关闭\"><div class=\"divClose_msgBox\"></div></a></li></ul><div class=\"divBorder_msgBox\"><div class=\"divContent_msgBox\" id=\"divContent_msgBox\"><center><table><tr><td align=\"center\" width=\"30px\"><div  class=\"divIco_msgBox_wx\" id=\"divIco_msgBox\"></div></td><td id=\"tdContent_msgBox\" align=\"left\">内容</td></tr><tr id=\"trBtn_msgBox\"><td align=\"center\" colspan=\"2\"><a  class=\"aBtn_msgBox\" href=\"javascript:CloseMsgBox_MsgBox()\">确定</a></td></tr></table></center></div></div></div>");
}
function ShowMsgBox_MsgBox(title, content, conWidth, conHeight, enabled, ico,colorCon) {
    if (enabled / 1 == 1) {
        $("#liClose_msgBox").show();
        $("#trBtn_msgBox").show();
    } else {
        $("#liClose_msgBox").hide();
        $("#trBtn_msgBox").hide();
    }

    $("#divIco_msgBox").removeClass(function () {
        return $(this).prev().attr("class");
    });
	if(ico=="auto"){
		var imgs = ["gx","gz","han","hao","atm","wx","yw","wbs","sj","loading"];
		var index = Math.floor(Math.random()*10);
    	$("#divIco_msgBox").addClass("divIco_msgBox_" + imgs[index]);
	}else if(ico=="true"){
		var imgs = ["gx","gz","hao","wx","gx","gz","hao","wx","gx","gz","hao","wx"];
		var index = Math.floor(Math.random()*10);
    	$("#divIco_msgBox").addClass("divIco_msgBox_" + imgs[index]);
	}else if(ico=="false"){
		var imgs = ["han","atm","yw","wbs","han","atm","yw","wbs","han","atm","yw","wbs"];
		var index = Math.floor(Math.random()*10);
    	$("#divIco_msgBox").addClass("divIco_msgBox_" + imgs[index]);
	}else{
    	$("#divIco_msgBox").addClass("divIco_msgBox_" + ico);
	}
	if(colorCon!=""){
        $("#tdContent_msgBox").css({ color: colorCon });
	}else{
        $("#tdContent_msgBox").css({ color: "#333333" });
	}
	
    $("#liTitle_msgBox").html(title);
    $("#tdContent_msgBox").html(content);
    if (conWidth > 0) {
        $("#divContent_msgBox").css({ width: conWidth });
    }
    if (conHeight > 0) {
        $("#divContent_msgBox").css({ height: conHeight });
    }
    SetMsgBoxNum_MsgBox("div_msgBox");

    $("#divDialogBG_msgBox").show();
    $("#div_msgBox").fadeIn("slow");

    $(window).resize(function () { ResetMsgBox_MsgBox() }); //IE下显示无效果
}
function CloseMsgBox_MsgBox() {
    $("#div_msgBox").fadeOut("fast");
    $("#divDialogBG_msgBox").hide();
    if (_closeFun_msgBox != null) {
        _closeFun_msgBox();
    }
}
function SetMsgBoxNum_MsgBox(obj) {
    var st = $(document).scrollTop(); //滚动条距顶部的距离
    var sl = $(document).scrollLeft(); //滚动条距左边的距离
    var wh = $(window).height(); //屏幕的高度
    var ww = $(window).width(); //屏幕的宽度
    var objH = $("#" + obj).height(); //浮动对象的高度
    var objW = $("#" + obj).width(); //浮动对象的宽度
    var objT = Number(st) + (Number(wh) - Number(objH)) / 2;
    var objL = Number(sl) + (Number(ww) - Number(objW)) / 2;
    $("#" + obj).css({ top: objT, left: objL });

    var sh = document.documentElement.scrollHeight; //滚动条的高度
    var sw = document.documentElement.scrollWidth; //滚动条的宽度
    var bgH = Number(sh) + Number(wh);
    var bgW = Number(sw) + Number(ww);
    if (sh < wh) { sh = wh }
    if (sw < ww) { sh = ww }
    $("#divDialogBG_msgBox").css({ height: sh, width: sw });
}
function ResetMsgBox_MsgBox() {
    SetMsgBoxNum_MsgBox("div_msgBox");
}
