<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<jsp:include page="publicHead_webYdate.jsp" flush="true" />
	<hr />
	
	
	<s:if test='#session.loginUser.userread!="1"'>
	<span style="float:right">	  
	  <input type="button" onclick="loadUrl('page/sum_yiele_data.jsp')" class="btn btn-link btn-sm" value="添加每月盤點數據"/>
	</span>
	</s:if>	
	<span style="float:right"> <input type="button"
		onclick="findPageBean('sumwebydata_findPageBean')" 
		class="btn btn-link btn-sm" value="查看每月盤點數據"/>
	</span> 	
	<!-- <span style="float:right"> <input type="button"
		onclick="findPageBean('ydata_findPageBeanForMonth')" 
		class="btn btn-link btn-sm" value="按月合計查看"/>
	</span> -->	
	<span style="float:right"> <input type="button"
		onclick="findPageBean('webyadanoinput_findPageBean')"
		class="btn btn-link btn-sm" value="未按時輸入記錄"/>
	</span>
	<s:if test='#attr.loginUser.username=="admin"'>
	<form method="post" action="timer_print_manual" id="emailform">
	<span style="float:right"> 	
	<input type="text" name="yymm" onClick="WdatePicker()" readonly="readonly" class="Wdate"/>
	<input type="button" value="發送產量匯總郵件" onclick="gosubmit()" class="btn btn-primary btn-sm"/>	
	</span>
	</form>	
	</s:if>	
	<div id="bodyid">
		<jsp:include page="table1/ydata_show1.jsp" />
	</div>
	
<script>
	function pages(page) {	
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "ydata_findPageBean3",
			data : "page=" + page,
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}
	function submis(public_form) {
		var fact = document.getElementById("factNo");
		var ym = document.getElementById("year");
		jq.ajax({
			type : "POST",
			dataType : "Html",
			url : "ydata_findPageBean2",
			data : jq("#"+public_form).serialize(),
			success : function(msg) {
				jq("#bodyid").html(msg);
			},
			error : function(xhr) {
				//alert(xhr.responseText);
				jq("#bodyid").html(xhr.responseText);
			}
		});
	}
	function gosubmit(){
		jq.ajax({
			type:"POST",
			dataType:"json",
			data:jq("#emailform").serialize(),
			url:"timer_print_manual",
			success:function(data){
				if(data=="0"){
					layer.msg("發送成功!",3,1);
				}
				if(data=="1"){
					layer.msg("發送失敗!",3,3);
				}				
			},
			error:function(error){
				alert(error.responseText);
			}		
		});
	  //document.getElementById("emailform").submit();
	}
	

	/*function delete_ydata(subform){
	   var flag=confirm("確定要刪除嗎?");
	   
	   if(flag==true){	   
	      //document.getElementById(subform).submit();
	      jq.ajax({
	    	  type:"POST",
	    	  dataType:"html",
	    	  data:jq("#"+subform).serialize(),
	    	  url:"ydata_delete",
	    	  success:function(data){
	    		  jq("#bodyid").html(data);
	    	  },
	    	  error:function(err){
	    		  jq("#bodyid").html(err.responseText);
	    	  }
	      })
	   }
	}
	

	
function showDiv(){
    jq.layer({
    type: 2,   //0-4的选择,
    title: '每月資料盤點',
    //title:false,
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    fadeIn:300,    
    //shift:'top',
    //move:'.testdiv',
    moveOut:false,
    moveType:1,
    offset:['10px','200px'],
    area: ['650px', '250px'],
    iframe:{
      src:'page/sum_yiele_data.jsp'   
    }
            
});
    }
  function confirm_show(){
       var flag=confirm("注意!添加盤點資料後，該月所有產量資料不可修改和刪除.\r請確保數據正確完整後，再添加盤點資料.\r確定要添加嗎？");
       if(flag==true){
          this.showDiv();
       }
    }*/
  
function print(public_form){
	var public_form=jq("#"+public_form);
	public_form.attr("action","ydata_print");
	public_form.attr("target","_blank");
	public_form.submit();
	
}	
  
</script>	
</body>
</html>
