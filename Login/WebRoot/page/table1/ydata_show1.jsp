<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'ydata_show.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
<%--$(function() {
		var j = jQuery.noConflict();
		var demo = j("#form").Validform({
			tiptype : 1,
		//showAllError : true,
		});
	});--%>
	
	 /**var defaultColor="#97CBFF";
	 var clickColor="#CCFFFF";
	 function click_color(obj){
        var tbody=document.getElementById("tbody");
        var length=document.getElementById("tbody").rows.length;
        for(var i=0;i<length;i++){
            tbody.rows[i].style.backgroundColor=defaultColor;
        }
        obj.style.backgroundColor=clickColor;        
    }
      function move(obj){
     obj.style.backgroundColor=defaultColor;
    }
    function showPage(){
	    var j=jQuery.noConflict();
	    j("#divpage").toggle(200,function(){
	        if(document.getElementById("a_page").innerHTML=="▽"){
	          document.getElementById("a_page").innerHTML="△";
	        }else{
	           document.getElementById("a_page").innerHTML="▽";
	        }
	    });	   
	}*/

//沒有提示的修改	
function update_ydata(subform){
   document.getElementById(subform).submit();
}

//有提示的修改	
function checkResult(factNo,yymmdd,subform){
     var date=yymmdd.toString();
     var month_str=date.substr(4,2);
     var month_int=parseInt(month_str);
     sumwebydatajs.findByFactNo2(factNo,yymmdd,function(x){
        if(x.length>0){
           alert("對不起，"+month_int+"月盤點數據已確定，該產量資料不可修改!");
           //alert("注意！修改完數據後,請前往數據盤點頁面,\n更新所屬月份的盤點數據.\n否則導致KPI報表數據不正確")        
           //document.getElementById(subform).submit();          
        }else{          
           document.getElementById(subform).submit();   
        }
    })    
}
function checkResult_delete(factNo,yymmdd,subform){
    var date=yymmdd.toString();
    var month_str=date.substr(4,2);
    var month_int=parseInt(month_str);
    sumwebydatajs.findByFactNo2(factNo,yymmdd,function(x){
         if(x.length>0){
             alert("對不起，"+month_int+"月盤點數據已確定，該產量資料不可刪除!");
         }else{
             var flag=confirm("確定要刪除嗎?");
             if(flag==true){
                document.getElementById(subform).submit();
             }
         }
    })
}
//此方法管理員可用
function checkResult_admin(factNo,yymmdd,subform){
    sumwebydatajs.findByFactNo2(factNo,yymmdd,function(x){
       if(x.length>0){
          alert("請注意，盤點數據已經確定，更改數據後，請及時更新盤點數據!");
          document.getElementById(subform).submit();
       }else{
          document.getElementById(subform).submit();
       }
    })
}
function delete_admin(factNo,yymmdd,subform){
   sumwebydatajs.findByFactNo2(factNo,yymmdd,function(x){
       if(x.lenght>0){
          alert("請注意，盤點數據已經確定，刪除後，請及時更新盤點數據!");
          document.getElementById(subform).submit();
       }else{
          var flag=confirm("確定要刪除嗎？");
          if(flag==true){
             document.getElementById(subform).submit();
          }
       }
   })
}

	
</script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/sumwebydatajs.js'></script>
</head>

<body >
 <div id="container">
 <div id="content">
	<table class="table table-striped table-hover table-bordered" >
		<h2>
		<s:if test='#session.loginUser.userread!="1"'>
	    <input value="添加" type="button" class="btn btn-info" onclick="javascript:location.href='saveAndUpdate/Yield_data.jsp'" />
	    </s:if>
		產量資料
		</h2>
		<thead>
			<tr class="tr_show">
				<th>序號</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>日期</th>
				<th>上模數(模)</th>
				<th>人數(人)</th>
				<th>標準產量(模)</th>
				<th>實際產量(模)</th>
				<th>達成率(%)</th>
				<th>天數(天)</th>				
				<th>實際生產雙數(雙)</th>
				<th>客補生產雙數(雙)</th>
				<th>廠補生產雙數(雙)</th>
				<th>樣品生產雙數(雙)</th>
				<th>出貨數(雙)</th>
				<th>退貨數(雙)</th>
				<th>上模總工時(小時)</th>
				<th>工作日/假日</th>
				<th>輸入者</th>
				<s:if test='#session.loginUser.userread!="1"'>
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
			<tr> 
				<td>${25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo" /></td>
				<td><s:property value="id.factCode" /></td>
				<td><s:date name="id.yymmdd" format="yyyyMMdd" /></td>
				<td><s:property value="%{formatDouble(onModulus)}" /></td>
				<td><s:property value="%{formatDouble(personnum)}" /></td>
				<td><s:property value="%{formatDouble(standardOutput)}" /></td>
				<td><s:property value="%{formatDouble(actualYield)}" /></td>
				<td><s:property value="%{formatDouble_percent(achievingRate)}" /></td>
				<td><s:property value="%{formatDouble(daycount)}" /></td>
				<td><s:property value="%{formatDouble(actualpairs)}" /></td>
				<td><s:property value="%{formatDouble(hostpairs)}" /></td>
				<td><s:property value="%{formatDouble(factpairs)}" /></td>
				<td><s:property value="%{formatDouble(samplepairs)}" /></td>
				<td><s:property value="%{formatDouble(outnum)}" /></td>
				<td><s:property value="%{formatDouble(backnum)}" /></td>
				<td><s:property value="%{formatDouble(workhours)}" /></td>
				<td><s:if test="workorholiday==1">
						<font color="red">假日</font>
					</s:if> <s:if test="workorholiday==2">
						<font color="green">未排產</font>
					</s:if> <s:if test="workorholiday==0">
				                           工作日
				  </s:if></td>
				<td><s:property value="username" /></td>
				<s:if test='#session.loginUser.userread!="1"'>
				<td>
					<form action="ydata_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymmdd'/>"
							name="id.yymmdd" />
					</form> 
					<s:if test='username==#attr.loginUser.username||#attr.loginUser.username=="admin"'>					  
					  <a href="javascript:layer.load(0);update_ydata('subform${x.index}');" ><img alt="修改" src="images/icon/edit001.png" title="修改" ></a> 				  	
					</s:if>
					<s:else>
					  <a disabled style="color:grey"><img alt="修改" src="images/icon/edit001_1.png" title="修改" ></a>
					</s:else>				
					<form action="ydata_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.factCode'/>" name="id.factCode" />
						<input type="hidden" value="<s:property value='id.yymmdd'/>"
							name="id.yymmdd" />
					</form>
					 <s:if test='username==#attr.loginUser.username||#attr.loginUser.username=="admin"'>
					  <%-- <a href="javascript:void(0)" onclick="checkResult_delete('${temp.id.factNo}',<s:date name='id.yymmdd' format='yyyyMM'/>,'2subform${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a> --%>
					  <a href="javascript:void(0)" onclick="delete_ydata('2subform${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除"></a>
					 </s:if>
					 <s:else>
					   <a disabled style="color:grey"><img alt="刪除" src="images/icon/delete001_1.jpg" title="刪除"></a>
					 </s:else> 
				</td>
				</s:if>
			</tr>
		</s:iterator>
		</tbody>
	</table>
    </div>
  </div>
	<%-- <hr />
	<center id="center_page">
	　　<a href="javascript:pages(0)">首頁</a>
	    <a href="javascript:pages(<s:property value='bean.currentPage'/>-1)">上一頁</a>	    
	        (第<s:property value="bean.currentPage" />頁 <a href="javascript:void(0)" onclick="showPage()" id="a_page">▽</a>|共<s:property value="bean.totalPage" />頁)
	           <div id="divpage">
	               <c:forEach begin="1"  end="${bean.totalPage}" var="id">
	                   <a href="javascript:pages(${id })">${id}</a>
	               </c:forEach>
	           </div>	  
	    <a href="javascript:pages(<s:property value='bean.currentPage'/>+1)">下一頁</a>
	    <a href="javascript:pages(<s:property value='bean.totalPage'/>)">尾頁</a>		
	</center> --%>
	
	<ul class="pagination" style="padding-left:42%">
		    <li><a href="javascript:pages(0)">首頁</a></li>
			<li><a href="javascript:pages(<s:property value='bean.currentPage'/>-1)">&laquo;</a></li>			
			<li><a href="javascript:pages(<s:property value='bean.currentPage'/>)"><s:property value='bean.currentPage'/></a></li>
			<s:if test="bean.currentPage+1==bean.totalPage||bean.currentPage+1<bean.totalPage">
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+1)"><s:property value='bean.currentPage+1'/></a></li>
			</s:if>
			<s:if test="bean.currentPage+2==bean.totalPage||bean.currentPage+2<bean.totalPage">
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+2)"><s:property value='bean.currentPage+2'/></a></li>
			</s:if>
			<s:if test="bean.currentPage+3==bean.totalPage||bean.currentPage+3<bean.totalPage">
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+3)"><s:property value='bean.currentPage+3'/></a></li>
			</s:if>
			<s:if test="bean.currentPage+4==bean.totalPage||bean.currentPage+4<bean.totalPage">
			    <li><a href="javascript:pages(<s:property value='bean.currentPage'/>+4)"><s:property value='bean.currentPage+4'/></a></li>
			</s:if>									
			<li><a href="javascript:pages(<s:property value='bean.currentPage'/>+1)">&raquo;</a></li>
			<li><a href="javascript:pages(<s:property value='bean.totalPage'/>)">尾頁</a></li>			
		</ul>
	<hr>

</body>


</html>
