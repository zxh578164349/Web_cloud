<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML>
<html>
<meta http-equiv=Content-Type content=text/html;charset=utf-8>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>Web系統</title>
<head>
<link href="css/main.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<link rel="shortcut icon" href="images/icon/web_ico.ico" />
<!-- 新 Bootstrap 核心 CSS 文件 -->
<!-- <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">-->
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
</head>


<body>
	<div id="main_container">
		<div id="top">
			<div id="lg_info">

				<div class="dropdown">
					<button type="button"
						class="btn btn-default btn-sm dropdown-toggle " id="dropdownMenu1"
						data-toggle="dropdown">

						<span class="glyphicon glyphicon-user"></span>
						<s:property value="#session.loginUser.name" />
						(
						<s:if test="#session.factNo=='tw'">所有數據</s:if>
						<s:else>
							<s:property value="#session.factName" />
						</s:else>
						) <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownMenu1">
						<li role="presentation"><a role="menuitem" tabindex="-1"
							href="javascript:loadUrl('userinitialUpdate?id=${loginUser.id}')">用戶信息</a></li>
						<li role="presentation" class="divider"></li>
						<li role="presentation"><a role="menuitem" tabindex="-1"
							id="exit" href="javascript:back_judge()" target="_parent">退出登录</a>
						</li>
					</ul>
				</div>
			</div>
			<div id="top_title">
				<h1>東莞加元鞋材制品有限公司</h1>
			</div>

			<div id="currentTime"></div>

		</div>
		<div id="left">
			<div class="panel panel-info">
				<div class="panel-heading">
					<span class="glyphicon glyphicon-file"> <a
						href="javascript:changeTitle('首頁');loadUrl('right.jsp')"
						title="返回首頁"> 網站首頁</a> </span>
				</div>
				<div class="panel-body">
					<s:iterator value="#session.login_menus" status="x" id="menu">
						<div class="mmenu">
							<a href="javascript:showDiv_main('${x.index}')"> <span
								id="a${x.index}"
								class="glyphicon glyphicon-folder-close mmenu_font">&nbsp;<s:property
										value="menuname" />
							</span> </a>
							<div id="submenu${x.index}" style="display:none">
								<s:iterator value="submenus" status="x" id="submenu">
									<div>
										<span class="glyphicon glyphicon-file"> <a name="alink"
											class="smenu_font a_disable"
											title="<s:property value='submenuname'/>"> <s:property
													value="submenuname" /> </a> </span>
									</div>
								</s:iterator>
							</div>
						</div>
					</s:iterator>
					<div class="mmenu">
						<a href="javascript:location.href='judge.jsp'"><span
							class="glyphicon glyphicon-folder-close mmenu_font">&nbsp;退出管理</span>
						</a>
					</div>
					<s:iterator value="#session.loginUser.webJurisdictions">
						<s:iterator value="webSubmenus">
							<input type="hidden"
								value="<s:property value='submenuname'/>,<s:property value='address'/>"
								name="a_hidden" />
						</s:iterator>
					</s:iterator>
				</div>
			</div>
		</div>

		<div id="right">
			<div id="toolbar">
				<span id="t_index" style="display:none">＜＜＜</span> <a
					href="javascript:switchSysBar()"><img
					src="images/icon/arrow.png" border="0" />
				</a>
			</div>

			<div id="r_content">
				<jsp:include page="right.jsp" flush="true" />
			</div>
		</div>

		<div id="bottom">
			<p>
				<jsp:include page="copyright.jsp" /></p>
		</div>
	</div>

	<script type='text/javascript' src='/Login/dwr/engine.js'></script>
	<script type='text/javascript' src='/Login/dwr/util.js'></script>
	<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="jquery/jquery-1.9.1.min.js"><\/script>');
	</script>
	<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>
	<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="jquery/jquery-form.js"><\/script>');
	</script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

	<!--[if lt IE 9]>  
  <script src="bootstrap/html5.js"></script>
  <script src="bootstrap/respond.min.js"></script>
  <![endif]-->
	<script>
		var jq = jQuery.noConflict();
		var loadi;
		jq(document).ajaxStart(function() {
			loadi = layer.load("請稍等...");
		});
		jq(document).ajaxStop(function() {
			layer.close(loadi);
		});

		function switchSysBar() {
			if (jq("#t_index").text() == "＜＜＜") {
				jq("#left").hide();

				jq("#t_index").text("＞＞＞");
			} else {
				jq("#left").show();
				jq("#t_index").text("＜＜＜");

			}
		}

		var currentDate = new Date(
	<%=new java.util.Date().getTime()%>
		);
		function run() {
			currentDate.setSeconds(currentDate.getSeconds() + 1);
			document.getElementById("currentTime").innerHTML = currentDate
					.toLocaleString();
		}
		window.setInterval("run();", 1000);

		function back_judge() {
			location.href = "/Login/judge.jsp";
		}

		function showDiv_main(index) {
			//var sts = document.getElementById("a" + index).innerHTML;
			var sts = jq("#a" + index);

			/*if (sts.text().replace(/(^\s*)|(\s*$)/g, "")=="退出管理") {
				location.href = "judge.jsp";
			}*/
			var divName = jq("#submenu" + index).css("display");
			//var img =jq("#img" + index);
			if (divName == "none") {
				sts.removeClass("glyphicon glyphicon-folder-close");
				sts.addClass("glyphicon glyphicon-folder-open");
				jq("#submenu" + index).show();
				//img.attr("src","image/folderopen.gif");
			} else {
				sts.removeClass("glyphicon glyphicon-folder-open");
				sts.addClass("glyphicon glyphicon-folder-close");
				jq("#submenu" + index).hide();
				//img.attr("src","image/folder.gif");
			}
		}
		jq(document)
				.ready(
						function() {
							jq("a[name='alink']").click(function() {
								jq("a[name='alink']").removeClass("linkbg");
								jq(this).addClass("linkbg");
							});
							jq("a[name='alink']").removeAttr("href");
							jq("a[name='alink']").removeAttr("onClick");

							var alinks = jq("a[name='alink']");
							var ahidens = jq("input[name='a_hidden']");
							for ( var i = 0; i < alinks.length; i++) {
								for ( var j = 0; j < ahidens.length; j++) {
									var array = ahidens.eq(j).val().split(",");
									if (alinks.eq(i).html().replace(
											/(^\s+)|\s+$/g, "") == array[0]) {
										alinks.eq(i).attr(
												"href",
												"javascript:changeTitle('"
														+ array[0]
														+ "');findPageBean('"
														+ array[1] + "')");
										alinks.eq(i).removeClass("a_disable");
										break;
									}
								}
							}

						});

		function changeTitle(title) {
			jq(document).attr("title", title);
		}
		function findPageBean(url) {
			jq.ajax({
				type : "POST",
				dataType : "html",
				url : url,
				success : function(data) {
					jq("#r_content").html(data);
				},
				error : function(error) {
					jq("#r_content").html(error.responseText);
				}
			});
		}
		function loadUrl(url) {
			jq("#r_content").load(url);
		}
		function findById_form(subform, url) {
			jq.ajax({
				type : "POST",
				dataType : "html",
				data : jq("#" + subform).serialize(),
				url : url,
				success : function(data) {
					jq("#r_content").html(data);
				},
				error : function(error) {
					jq("#r_content").html(error.responseText);
				}
			});
		}
	</script>
</body>

</html>
