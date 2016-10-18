<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'updateData.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>

<script type="text/javascript">
	function checkUserName() {
		var factNo = document.getElementById("factNo").value;
		var userName = document.getElementById("userName").value;
		var userName2 = document.getElementById("hide_userName").value;
		var email = document.getElementById("email").value;
		var email2 = document.getElementById("hidden_email").value;
		/*if(factNo!=""&&userName!=""&&userName!=userName2){
		   userjs.findByIdDWR(factNo,userName,function(x){
		        if(x!=null){
		           alert("該登錄名已存在!");
		           return false;
		        }
		   });
		}*/
		if (factNo != "" && email != "" && email != email2) {
			userjs.findUserByFactNoAEmail(factNo, email, function(x) {
				if (x != null) {
					alert("該Email已存在!");
					return false;
				}
			});
		}
	}
	function gosubmit() {
		jq.ajax({
			type : "POST",
			dataType : "html",
			data : jq("#form").serialize(),
			url : "userupdateUesr",
			success : function(data) {
				jq("#r_content").html(data);
			},
			error : function(error) {
				jq("#r_content").html(error);
			}
		});
	}
	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep : true,
			showAllError : true,
			datatype : {
				"*0-6" : /^-?\d{1,12}(\.[0-9]{1,3})?$/
			},
			ajaxPost : true,
			callback : function(data) {
				if (data == "0") {
					layer.msg("修改成功,請重新登錄!", 3, 1);
					window.setTimeout(function() {
						location.href = "judge.jsp"
					}, 3000);
				} else {
					//alert(data.responseText);
					layer.msg("修改失敗", 3, 3);
				}
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過12位數,可保留三位以內小數";			
	});

	/*禁止空格輸入*/
	/*window.onload=function(){            
	 var inputs=document.getElementsByTagName("input"); 
	 for (var i=0;i<inputs.length; i++) {  
	 if(inputs[i].getAttribute("type")=="text") 
	 inputs[i].onkeyup=function(){ 
	 this.value=this.value.replace(/(^\s+)|\s+$/g,""); 
	 }; 
	 }  
	 }*/
	jq(function() {
		var inputs = document.getElementsByTagName("input");
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].getAttribute("type") == "text")
				inputs[i].onkeyup = function() {
					this.value = this.value.replace(/(^\s+)|\s+$/g, "");
				};
		}
	});
	function back() {
		loadUrl("/Login/userfindPageBean3?backIndex=1");
	}
</script>
<script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
<body>

	<!-- <form action="userupdateUesr" method="post" id="form">
		<input type="hidden" name="updateU.id"
			value="<s:property value="#attr.webU.id"/>"> <input
			type="hidden" name="updateU.available"
			value="<s:property value="#attr.webU.available"/>">
		<h2>個人資料修改</h2>
		<table class="table table-condensed">

			<tr>
				<td class="td1">工號:</td>
				<td><input name="updateU.workno" style="border:0px;color:blue"
					readonly="readonly" value="<s:property value="#attr.webU.workno"/>"
					type="text">
				</td>
			</tr>
			<tr>
				<td class="td1">登錄名:</td>
				<td><input name="updateU.username"
					value="<s:property value="#attr.webU.username"/>" id="userName"
					style="border:0px;color:blue" readonly type="text"> <input
					type="hidden" id="hide_userName"
					value="<s:property value="#attr.webU.username"/>" />
				</td>
			</tr>
			<tr>
				<td class="td1">廠別:</td>
				<td><input name="updateU.factno"
					value="<s:property value="#attr.webU.factno"/>" readonly
					id="factNo" style="color:blue;border:0px" type="text"></td>
			</tr>
			<tr>
				<td class="td1">密碼:</td>
				<td><input name="updateU.pwd"
					value="<s:property value="#attr.webU.pwd"/>" type="password"
					datatype="*"></td>
			</tr>
			<tr>
				<td class="td1">密碼確認:</td>
				<td><input name="pwd2"
					value="<s:property value="#attr.webU.pwd"/>" type="password"
					recheck="updateU.pwd" datatype="*"></td>
			</tr>
			<tr>
				<td class="td1">姓名:</td>
				<td><input name="updateU.name"
					value="<s:property value="#attr.webU.name"/>" type="text"
					datatype="*"></td>
			</tr>

			<tr>
				<td class="td1">IP:</td>
				<td class="td_input"><input name="updateU.ip"
					value="<s:property value="#attr.webU.ip"/>" type="text"></td>
			</tr>

			<tr>
				<td class="td1">Email:</td>
				<td><input name="updateU.email"
					value="<s:property value="#attr.webU.email"/>" type="text"
					id="email" datatype="e" onblur="checkUserName()" /> <input
					value="<s:property value="#attr.webU.email"/>" type="hidden"
					id="hidden_email" />
				</td>
			</tr>
			<tr>
				<td class="td1">只讀/修改</td>
				<td>只讀: <s:if test='#attr.webU.userread=="1"'>
						<input type="radio" value="1" name="updateU.userread" checked />
					</s:if> <s:else>
						<input type="radio" value="1" name="updateU.userread" />
					</s:else> &nbsp; 修改: <s:if
						test='#attr.webU.userread=="0"||#attr.webU.userread==null'>
						<input type="radio" value="0" name="updateU.userread" checked />
					</s:if> <s:else>
						<input type="radio" value="0" name="updateU.userread" />
					</s:else> <%-- <input type="hidden" name="updateU.logdate" value="<s:property value="#attr.webU.logdate"/>"/> --%>
				</td>
			</tr>
			<tr>
				<td class="td1">部門</td>
				<td><input type="text"
					value="<s:property value='#attr.webU.department'/>"
					name="updateU.department" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="確認修改"
					class="btn btn-primary" />&nbsp; <input type="button"
					class="btn btn-primary" value="退出" onclick="history.go('main.jsp')" />
				</td>

			</tr>
		</table>
	</form> -->


    
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
			<h2 id="h2_title">個人資料修改</h2>
				<form class="form-horizontal" role="form" id="form"
					action="userupdateUesr" method="post">
					<input type="hidden" name="updateU.id"
						value="<s:property value="#attr.webU.id"/>"> <input
						type="hidden" name="updateU.available"
						value="<s:property value="#attr.webU.available"/>">
						<input type="hidden" name="updateU.adminMk" value="<s:property value='#attr.webU.adminMk'/>"/>  
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">工號</label>
						<div class="col-sm-10">
							<input type="text" class="pinfo_form" name="updateU.workno"
								value="<s:property value="#attr.webU.workno"/>" readonly datatype="*"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">登錄名</label>
						<div class="col-sm-10">
							<input type="text" class="pinfo_form"
								name="updateU.username"
								value="<s:property value="#attr.webU.username"/>" id="userName" readonly datatype="*"/>
							<input type="hidden" id="hide_userName"
								value="<s:property value="#attr.webU.username"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">廠別</label>
						<div class="col-sm-10">
							<input type="text" class="pinfo_form" name="updateU.factno"
								value="<s:property value="#attr.webU.factno"/>" readonly
								id="factNo" datatype="*"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密碼</label>
						<div class="col-sm-10">
							<input type="password" class="pinfo_form" name="updateU.pwd"
								value="<s:property value="#attr.webU.pwd"/>" datatype="*" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">確認密碼</label>
						<div class="col-sm-10">
							<input type="password" class="pinfo_form" name="pwd2"
								value="<s:property value="#attr.webU.pwd"/>"
								recheck="updateU.pwd" datatype="*" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-10">
							<input type="text" class="pinfo_form" name="updateU.name"
								value="<s:property value="#attr.webU.name"/>" datatype="*" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">IP</label>
						<div class="col-sm-10">
							<input type="text" class="pinfo_form" name="updateU.ip"
								value="<s:property value="#attr.webU.ip"/>" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<input type="text" class="pinfo_form" name="updateU.email"
								value="<s:property value="#attr.webU.email"/>" id="email"
								datatype="e" onblur="checkUserName()" /> <input
								value="<s:property value="#attr.webU.email"/>" type="hidden"
								id="hidden_email" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label> 只讀: <s:if test='#attr.webU.userread=="1"'>
										<input type="radio" value="1" name="updateU.userread" checked />
									</s:if> <s:else>
										<input type="radio" value="1" name="updateU.userread" />
									</s:else> &nbsp; 修改: <s:if
										test='#attr.webU.userread=="0"||#attr.webU.userread==null'>
										<input type="radio" value="0" name="updateU.userread" checked />
									</s:if> <s:else>
										<input type="radio" value="0" name="updateU.userread" />
									</s:else> </label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">部門</label>
						<div class="col-sm-10">
							<input type="text" class="pinfo_form"
								value="<s:property value='#attr.webU.department'/>"
								name="updateU.department" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" value="確認修改" class="btn btn-primary" />&nbsp;
							<input type="button" class="btn btn-primary" value="退出"
								onclick="loadUrl('blank.jsp')" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
