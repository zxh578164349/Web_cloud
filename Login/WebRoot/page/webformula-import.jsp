<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMdd-hh");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'kongweishu.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>


<body>	
	<hr/>
	<s:if test='#session.loginUser.userread!="1"'>
	<form action="webformula_importFile"  method="post" enctype="multipart/form-data" id="upload_form" target="frameFile">
	  <table id="tb_search">
	      <td>	 
	         <input type="file" name="file"  id="id_file" class="btn btn-info"/>	        	       
	       </td>
				<td>
				<%-- <s:if test="#session.factNo=='tw'">
						<select name="factNo" id="dwr_factno" class="search">
							<option value="nothing">請選擇廠別</option>
							<s:iterator value="#session.facts" id="temp">
								<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>
							</s:iterator>
						</select>
					</s:if> <s:else>
						<select name="factNo" id="dwr_factno" class="search">
							<option value="<s:property value="#session.factNo"/>">
								<s:property value="#session.factName" />
								(
								<s:property value="#session.factNo" />
								)
							</option>
						</select>
					</s:else> --%>
					 
					<s:if test="#session.factNo!='tw'">
						<input type="text" name="factNo"
							value="${loginUser.factno}__${loginUser.erpfactno}" readonly id="dwr_factno" />
					</s:if>
					<s:if test="#session.factNo=='tw'">
						<select name="factNo" onchange="makeFormulaIndex()" datatype="*"
							id="dwr_factno">
						</select>
					</s:if></td>
				<td>
	        <!--  <input type="text" id="yymm_in" name="yymm" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate search"/> -->	       
	        <select name="factCode" datatype="*" id="dwrFactArea" onchange="makeFormulaIndex()"></select>
	       </td>
	       <td>
	         <select name="formula.pom.webBrank.id" id="dwrWebbrank" datatype="*" onchange="makePomNo2('dwrWebbrank','createDate')">	</select>					      					        					        						     
	       </td>
	       <td>	          
	       	   &nbsp;<input value="導入Excel" type=button onclick="checkForm()" id="search_forday" class="btn btn-info"/>
	       </td>
	       <input type="text" value="<%=str_date%>" name="formula.createDate" id="createDate" />
	       <input type="text"  name="formula.formulaIndex"  id="formulaIndex" readonly/>
	       <input type="text" name="formula.pom.pomNo"  id="pomNo"  readonly />	       
	  </table>          	
	</form>
	</s:if>
	<iframe id="frameFile" name="frameFile" style="display: none;"></iframe>
	<hr/>	
	
<script>
jq(function(){
  jq.ajax({
			type:"get",
			dataType:"json",
			url:"webfact_findAllVwebfact",
			success:function(data){
				var item="";
				jq("#dwr_factno").empty();
				jq("#dwr_factno").append("<option value=''>請選擇廠別</option>");
				jq.each(data,function(i,obj){
					item+="<option value='"+obj[0]+"__"+obj[3]+"'>"+obj[1]+"("+obj[3]+")</option>";					
				});
				jq("#dwr_factno").append(item);
			}
		});

   /*加載所有製程*/
  jq.ajax({
			type:"post",
			dataType:"json",
			url:"weberpbp_findObjOp1",
			success:function(data){
				var item="";
				jq("#dwrFactArea").empty();
				jq("#dwrFactArea").append("<option value=''>請選擇製程類別</option>");
				jq.each(data,function(i,obj){
					item+="<option value='"+obj[0]+"__"+obj[1]+"'>"+obj[2]+"</option>";					
				});
				jq("#dwrFactArea").append(item);
			}
		});
		
	    /*加載所有品牌*/
		jq.ajax({
			type:"get",
			url:"weberpbp_findObjOp2",
			dataType:"json",
			success:function(data){
				jq("#dwrWebbrank").empty();
				jq("#dwrWebbrank").append("<option value=''>品牌選擇</option>");
				var item="";
				jq.each(data,function(i,obj){
					item+="<option value='"+obj[0]+"'>"+obj[2]+"</option>";					
				});
				jq("#dwrWebbrank").append(item);
			}
		});	
		
  		
});



	function checkForm(){
		var id_file=jq("#id_file").val();
		var extendName=id_file.substr(id_file.lastIndexOf(".")).toLowerCase();
		if(id_file==""){
			layer.alert("請選擇Excel文檔");
			return false;
		}else if(extendName!=".xls"&&extendName!=".xlsx"){
			layer.alert("僅允許Excel文檔");
			return false;
		}else if(jq("#dwr_factno").val()==""){
			layer.alert("請選擇廠別");
			return false;
		}else if(jq("#dwrFactArea").val()==""){
			layer.alert("請選擇製程類別");
			return false;
		}else if(jq("#dwrWebbrank").val()==""){
			layer.alert("請選擇品牌");
			return false;	
		}else{
			layer.load("請稍等...");
			jq("#upload_form").submit();
		}				
	}
	
function makeFormulaIndex(){
		jq("#formulaIndex").val("");
		//jq("#btn_addwebtabpom").addClass("disabled");
		//jq("#btn_addsec").addClass("disabled");
		var dwr_factno=jq("#dwr_factno");
		var dwrFactArea=jq("#dwrFactArea");
		var createDate=jq("#createDate");
		if(dwr_factno.val()!=""&&dwrFactArea.val()!=""&&createDate.val()!=""){
			jq.ajax({
				type:"get",
				dataType:"json",
				url:"webformula_makeFormulaIndex",
				data:{factNo:dwr_factno.val(),factCode:dwrFactArea.val(),createDate:createDate.val()},
				success:function(data){
					jq("#formulaIndex").val(data);
					//jq("#formulaIndex2").val(data);
					//jq("#div_webformalaitem").css("display","block");
					//jq("#li_webtabpom").css("display","block");
					//checkbtn();
				},
				error:function(){
					layer.msg("生成配方索引失敗",3,3);
					//jq("#div_webformalaitem").css("display","none");
					//jq("#btn_addsec").addClass("disabled");	
					//jq("#li_webtabpom").css("display","none");
				}
			});
		}		
   }
   
/*物性編號*/
	function makePomNo2(dwrWebbrank,createDate){
		var brank=jq("#"+dwrWebbrank).val();
		var createDate=jq("#"+createDate).val();
		if(brank!=""&&brank!=null&&createDate!=""&&createDate!=null){
			jq.ajax({
				type:"POST",
				dataType:"json",
				url:"webtabpom_makePomNo",
				data:{"brank":brank,"tabpomDate":createDate},				
				success:function(data){
					jq("#pomNo").val(data);
					//jq("#btn_upload").removeClass("disabled");
				},
				error:function(){
				    layer.msg("生成物性編號失敗",3,3);
					//jq("#pomNo").val("");
					//jq("#btn_upload").addClass("disabled");
				}
			});
		}	
	}   		
	
function print(public_form,factNo,yymm,yymm2){
	var public_form=jq("#"+public_form);
	public_form.attr("action","weballobj_print");
	public_form.attr("target","_blank");	
	if(jq("#"+factNo).val()=="nothing"||jq("#"+yymm).val()==""||jq("#"+yymm2).val()==""){
		layer.msg("請選擇廠別和日期",3,3);
	}else{
		public_form.submit();
	}	
}


function showDiv(){
jq.layer({
  type: 1,
  title: '樣本格式說明', //不显示标题
  page:{html:"<img src='images/sample-webestpro.jpg'/>"}  //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
});
}
</script>		
</body>
</html>
