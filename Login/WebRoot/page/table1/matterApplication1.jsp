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

<!-- 分頁樣式 -->
<link rel="stylesheet" type="text/css" href="css/mystyle.css" />
<script type="text/javascript" src="page/jquerys/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/layer/layer.min.js"></script>

<script type="text/javascript">

	
	 var defaultColor="#97CBFF";
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
    
    var jqr=jQuery.noConflict();
    function showDiv(billNo,factNo){
    jqr.layer({
    type: 1,   //0-4的选择,
    title: '函文內容',
    //border: [0],
    closeBtn: [1,true],
    shade: [0],
    shadeClose: false,
     border: [10, 0.3, '#000'],
   // btns:1,
    //fadeIn:300,
    shift:'top',
    offset:['10px',''],
    area: ['750px', '560px'],
    page:{
      url:'kyz_findById_layer?billNo='+billNo+'& factNo='+factNo    
    }
    
           
});
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
	} 
</script>
</head>
<body>
  <div id="container">
  <div id="content">
	<table id="tb" >
		<caption>函文申請</caption>
		<thead>			
			<tr class="tr_show">
				<th>序號</th>
				<th>標題</th>
				<th>電話</th>
				<th>申請單位</th>
				<th>廠別</th>
				<th>廠別狀態</th>
				<th>建立日期</th>
				<th>申請者</th>
				<th>申請單號</th>
				<th>是否緊急</th>
				<th>類別</th>				
				<th>輸入者</th>
				<s:if test='#session.loginUser.userread!="1"'>				
				<th>操作</th>
				</s:if>				
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">		  
		     <tr onmousemove="click_color(this)" onmouseout="move(this)"> 
				<td>${25*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="memoSmk" /></td>
				<td><s:property value="telNo" /></td>
				<td><s:property value="secNo" /></td>
				<td><s:property value="id.factNo"/></td>
				<td><s:property value="factCode"/></td>				
				<td><s:date name="timeCreate" format="yyyyMMdd" /></td>
				<td><s:property value="userNm" /></td>
				<td><s:property value="id.billNo" /></td>
				<td>
				  <s:if test="emerWhether==0">
				    　　 是
				  </s:if>
				  <s:if test="emerWhether==1">
				    　　 否
				  </s:if>				  
				</td>
				<td>
				<s:if test='visaType=="F"'>
				  廠務簽核
				</s:if>
				<s:if test='visaType=="W"'>
				臺灣簽核
				</s:if>
				<s:if test='visaType=="G"'>
				  工程簽核
				</s:if>
				<s:if test='visaType=="I"'>
				IKT簽核
				</s:if>	
				<s:if test='visaType=="L"'>
				  實驗室簽核
				</s:if>
				<s:if test='visaType=="P"'>
				品管簽核
				</s:if>	
				<s:if test='visaType=="Q"'>
				  企劃簽核
				</s:if>
				<s:if test='visaType=="S"'>
				生管簽核
				</s:if>	
				<s:if test='visaType=="T"'>
				  整理簽核
				</s:if>
				<s:if test='visaType=="Y"'>
				油壓簽核
				</s:if>	
				<s:if test='visaType=="Z"'>
				  總務簽核
				</s:if>
				<s:if test='visaType=="B"'>
				  備料簽核
				</s:if>
				<s:if test='visaType=="O"'>
				  業務簽核
				</s:if>
				<s:if test='visaType.substring(0,2)=="C1"'>
				  其他費用簽核1(<1000元)
				</s:if>
				<s:if test='visaType.substring(0,2)=="C2"'>
				 其他費用簽核2(>=1000元)
				</s:if>
				<s:if test='visaType.substring(0,2)=="C3"'>
				  電腦耗材簽核1(<1000元)
				</s:if>
				<s:if test='visaType.substring(0,2)=="C4"'>
				  電腦耗材簽核2(>=1000元)
				</s:if>	
				<s:if test='visaType.substring(0,2)=="C5"'>
				  總務費用簽核1(<1000元)
				</s:if>
				<s:if test='visaType.substring(0,2)=="C6"'>
				  總務費用簽核2(>=1000元)
				</s:if>							
				</td>							
				<td><s:property value="username" /></td>
				<td>
				
				<s:if test='#session.loginUser.userread!="1"'>
					<form action="kyz_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.billNo'/>" name="id.billNo" />						
					</form> 
					<form action="kyz_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.billNo'/>" name="id.billNo" />
							<input type="hidden" value="<s:property value='visaType'/>" name="visaSort"/>						
					</form>
					  <a href="javascript:document.getElementById('subform${x.index}').submit()" onclick=""><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>						  																									
					  <a href="javascript:void(0)" onclick="isDelete('2subform${x.index}')"><img alt="刪除" src="images/icon/delete001.png" title="刪除" ></a>										
					 </s:if>
					 
					  <form action="kyz_print2" method="post" id="3subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.billNo'/>" name="id.billNo" />
							<input type="hidden" value="<s:property value='factCode'/>" name="factCode"/>
							<input type="hidden" value="<s:property value='visaType'/>" name="visaSort"/>
							<input type="hidden" value="look" name="lookordown"/>											
					  </form>
					   <form action="kyz_print2" method="post" id="4subform${x.index}" style="float:left" target="_blank">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.billNo'/>" name="id.billNo" />
							<input type="hidden" value="<s:property value='factCode'/>" name="factCode"/>
							<input type="hidden" value="<s:property value='visaType'/>" name="visaSort"/>
							<input type="hidden" value="down" name="lookordown"/>											
					  </form>
					  <a href="javascript:showDiv('<s:property value='id.billNo'/>','<s:property value='id.factNo'/>')" onclick=""><img alt="查看" src="images/icon/view002.png" title="查看" ></a>
					 <a href="javascript:document.getElementById('3subform${x.index}').submit()"><img alt="預覽" src="images/icon/view001.png" title="預覽" ></a>
					 <a href="javascript:document.getElementById('4subform${x.index}').submit()" ><img alt="打印" src="images/icon/print001.png" title="打印" ></a>						 
				</td>
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
