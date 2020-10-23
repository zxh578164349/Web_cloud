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
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet"> -->
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
						<s:if test='#session.loginUser.userType=="0"'>
						     <s:property value="#session.loginUser.name" />
						     (
						      <s:if test="#session.factNo=='tw'">所有數據</s:if>
						      <s:else>
							      <s:property value="#session.factName" />
						      </s:else>
						     )
						</s:if>
						<s:else>
						    <s:property value="#session.loginUser.name" />
						</s:else>
					    <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu"
						aria-labelledby="dropdownMenu1">
						<li role="presentation"><a role="menuitem" tabindex="-1"
							href="javascript:loadUrl('userinitialUpdate?id=${loginUser.id}');change_h2_title('');changeTitle('用戶信息')">用戶信息</a></li>
						<li role="presentation" class="divider"></li>
						<li role="presentation"><a role="menuitem" tabindex="-1"
							id="exit" href="javascript:back_judge()" target="_parent">退出登录</a>
						</li>
					</ul>
				</div>
			</div>
			<div id="top_title">
				<h1>加久企業股份有限公司</h1>
			</div>

			<div id="currentTime"></div>

		</div>
		<div id="left">
			<div class="panel panel-info">
				<div class="panel-heading">
					<span class="glyphicon glyphicon-file"> <a
						href="javascript:changeTitle('首頁');loadUrl('right.jsp');change_h2_title('')"
						title="返回首頁"> 網站首頁</a> </span>
				</div>
				<div class="panel-body">
					<s:iterator value="#session.login_menus" status="x" id="menu">
						<div class="mmenu">						
							<a href="javascript:showDiv_main('${x.index}')">
								<span id="a${x.index}" class="glyphicon glyphicon-folder-close mmenu_font">&nbsp;${x.index+1}.<s:property value="menuname" /> </span>
							</a>
							<div id="submenu${x.index}" style="display:none">
								<s:iterator value="submenus" status="x" id="submenu">
									<div>
										<span class="glyphicon glyphicon-file"> 
										    <s:if test='enableMk=="1"'>
										       <a name="alink_1" class="smenu_font a_disable" title="<s:property value='submenuname'/>">
												(${x.index+1 })<s:property value="submenuname" />
											   </a>																						
											   <input type="hidden" value="<s:property value='address'/>" name="alink_address_1"/> 	
						                    </s:if>
										    <s:else>
										        <a name="alink" class="smenu_font a_disable" title="<s:property value='submenuname'/>">
												(${x.index+1 })<s:property value="submenuname" />
											    </a>																						
											   <input type="hidden" value="<s:property value='address'/>" name="alink_address"/> 	
										    </s:else>																					
										</span>
									</div>
								</s:iterator>
							</div>
						</div>
					</s:iterator>
					<div class="mmenu">
						<a href="javascript:back_judge()">
							<span class="glyphicon glyphicon-folder-close mmenu_font">&nbsp;退出管理</span>
						</a>
					</div>
					<s:iterator value="#session.loginUser.webJurisdictions">
						<s:iterator value="webSubmenus">
							<input type="hidden" value="<s:property value='submenuname'/>,<s:property value='address'/>" name="a_hidden" />
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
            <h2 id="h2_title2"></h2>
			<div id="r_content">		
				<jsp:include page="right.jsp" flush="true" />
			</div>
		</div>

		<div id="bottom">
			<p>
				<jsp:include page="copyright.jsp" /></p>
		</div>
	</div>

	<script type='text/javascript' src='jquery/engine.js'></script>
	<script type='text/javascript' src='jquery/util.js'></script>
	<!-- <script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script> -->
	<!--  <script>window.jQuery|| document.write('<script src="jquery/jquery-1.9.1.min.js"><\/script>');</script>-->
	<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
	<!--  <script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>-->	
	<!-- <script type="text/javascript" src="jquery/layer3/layer.js"></script> -->
	<script type="text/javascript" src="jquery/layer/layer.min.js"></script>
	<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="jquery/jquery-form.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<!-- <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script> -->
	<script src="bootstrap/js/bootstrap.min.js"></script>	
	<script src="uploadify/jquery.uploadify.min.js" type="text/javascript"></script>	
	<!--[if lt IE 9]>  
  <script src="bootstrap/html5.js"></script>
  <script src="bootstrap/respond.min.js"></script>
  <![endif]-->  
  <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script> -->
   <script type="text/javascript" src="select2/js/select2.min.js"></script>
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
			location.href = "userlogout";
		}

		function showDiv_main(index) {			
			var sts = jq("#a" + index);			
			var divName = jq("#submenu" + index).css("display");			
			if (divName == "none") {
				sts.removeClass("glyphicon glyphicon-folder-close");
				sts.addClass("glyphicon glyphicon-folder-open");
				jq("#submenu" + index).show();				
			} else {
				sts.removeClass("glyphicon glyphicon-folder-open");
				sts.addClass("glyphicon glyphicon-folder-close");
				jq("#submenu" + index).hide();				
			}
		}
	
		jq(document).ready(function() {
					jq("a[name='alink']").click(function() {
						jq("a[name='alink']").removeClass("linkbg");
						jq(this).addClass("linkbg");
					});
					jq("a[name='alink']").removeAttr("href");
					jq("a[name='alink']").removeAttr("onClick");

					var alinks = jq("a[name='alink']");
					var ahidens = jq("input[name='a_hidden']");
					var alink_addresss = jq("input[name='alink_address']");
					for ( var i = 0; i < alinks.length; i++) {
						for ( var j = 0; j < ahidens.length; j++) {
							var array = ahidens.eq(j).val().split(",");
							if (alink_addresss.eq(i).val() == array[1]) {
								alinks.eq(i).attr(
										"href",
										"javascript:changeTitle('" + array[0]
												+ "');findPageBean('"
												+ array[1] + "','" + array[0]
												+ "')");
								alinks.eq(i).removeClass("a_disable");
								break;
							}
						}
					}
					
					jq.ajax({
						type : "post",
						url : "vbm_findKyVisaBills_Int",
						dataType : "json",
						success : function(data) {
							var temp;
							if (data == "0") {
								temp = "<b>你好，目前暂无函文需要審核!</b>";
							} else {
								temp = "<a href='javascript:goHere()'>你好，目前有" + data
										+ "封函文需要審核!(可點擊進入)</a>"
							}
							jq("#td_content_right").append(temp);
							jq("#divBills2").show();
						},
						error:function(error){
							alert("函文提示發生錯誤");
							//alert(error.responseText);
						}
					})	
		});

		function changeTitle(title) {
			jq(document).attr("title", title);
		}

		function findPageBean(url, title) {
			jq.ajax({
				type : "POST",
				dataType : "html",
				url : url,
				success : function(data) {
					jq("#r_content").html(data);
					jq("#h2_title").text(title);
					jq("#h2_title2").text(title);			
				},
				error : function(error) {
					jq("#r_content").html(error.responseText);
				}
			});	
									
		}
		function loadUrl(url) {
			jq("#r_content").load(url);
		}
		function loadUrl_bodyid(url) {
			jq("#bodyid").load(url);
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

		//你确定要删除吗？
		function isDelete(mid, url) {
			var flag = confirm("確定要刪除嗎?");
			if (flag == true) {
				jq.ajax({
					type : "POST",
					dataType : "html",
					data : jq("#" + mid).serialize(),
					url : url,
					success : function(data) {
						jq("#bodyid").html(data);
					},
					error : function(data) {
						jq("#bodyid").html(data.responseText);
					}
				});
			}
		}
		function isDelete2(mid, url, url2) {
			var flag = confirm("確定要刪除嗎?");
			if (flag == true) {
				jq.ajax({
					type : "POST",
					dataType : "json",
					data : jq("#" + mid).serialize(),
					url : url,
					success : function(data) {
						if (data == "0") {
							layer.msg("刪除成功", 3, 1);
							loadUrl_bodyid(url2);
						} else {
							layer.msg("刪除失敗", 3, 3);
						}
					}
				});
			}
		}

		//禁止輸入空格
		function goTrim() {
			var inputs = document.getElementsByTagName("input");
			for ( var i = 0; i < inputs.length; i++) {
				if (inputs[i].getAttribute("type") == "text") {
					inputs[i].onkeyup = function() {
						if (this.value.indexOf(" " != -1)) {
							//this.value=this.value.trim();
							this.value = this.value.replace(/(^\s+)|\s+$/g, "");
						}
					};
				}
			}
		}

		function hideBills2() {
			jq("#divBills2").hide(300);
		}
		function goHere() {
			loadUrl("vbm_findPageBean?initMain=1");
		}

		/*function findKyVisaBills_Int(){
		   kyvisabillsjs.findKyVisaBills_Int(function(x){
			   var temp;
		       if(x>0){               
		         temp="<a href='javascript:goHere()'>你好，目前有"+x+"封函文需要審核!(可點擊進入)</a>"         
		       }else{         
		          temp="<b>你好，目前暂无函文需要審核!</b>";        
		       }
		       jq("#td_content_right").append(temp);
		       jq("#divBills2").show();
		   })
		}*/

		/*jq(function() {
			jq.ajax({
				type : "post",
				url : "vbm_findKyVisaBills_Int",
				dataType : "json",
				success : function(data) {
					var temp;
					if (data == "0") {
						temp = "<b>你好，目前暂无函文需要審核!</b>";
					} else {
						temp = "<a href='javascript:goHere()'>你好，目前有" + data
								+ "封函文需要審核!(可點擊進入)</a>"
					}
					jq("#td_content_right").append(temp);
					jq("#divBills2").show();
				},
				error:function(error){
					alert("函文提示發生錯誤");
					//alert(error.responseText);
				}
			})
		});*/
		//setTimeout("findKyVisaBills_Int()",1000);
		setTimeout("hideBills2()", 15000);

		function change_h2_title(title) {
			jq("#h2_title2").text(title);
		}
		
		//添加修改頁面返回到數據頁面20170804
		function back_main(url) {
			loadUrl(url);
		}
	</script>
</body>

</html>
