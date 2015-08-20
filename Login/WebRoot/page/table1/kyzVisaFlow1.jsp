<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" type="text/css" href="css/mystyle.css" />
<script type="text/javascript">
	  function check(obj){
	   var j = jQuery.noConflict();
	  j(function(){ 	
		var demo=j("#"+obj).Validform({
		//btnSubmit : "#"+obj2,
		tiptype : 3,
		showAllError : true,
		tipSweep : true,
		datatype : {
				"*0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/,
				"*1-6" : /^[1-9]{1}\d{0,8}(\.[0-9]{1,3})?$/,
				"*0-7" : /^\d{0,7}(\.[0-9]{1})?$/

			}
		/* callback:function(){
		  document.getElementById("mydiv").style.display="block";
		   j("#"+obj).submit();
		   return false;
		} */	
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["*1-6"] = "不為0的數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["*0-7"] = "只能數字且不超過7位數,可保留一位以內小數";
	  })		
	}
	
	function showDiv(obj){
	  var j=jQuery.noConflict();
	  j("#"+obj).show(300);
	}
	function hideDiv(obj){
	  var j=jQuery.noConflict();
	  j("#"+obj).hide(300);
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
    	
</script>





</head>
<body>
  <div id="container">
   <div id="content">
	<table id="tb" >
		<caption >審核流程</caption>
		<thead>			
			<tr class="tr_show">
			    <th>序號</th>
				<th>廠別</th>
				<th>類別</th>
				<th>姓名</th>
				<th>項次</th>
				<th>Email地址</th>
				<th>職務</th>
				<th>是否審核</th>
				<s:if test='#session.loginUser.userread!="1"'>			
				<th>操作</th>
				</s:if>
			</tr>
		</thead>
		<tbody id="tbody">
		<s:iterator value="bean.list" status="x" id="temp">
		    <s:if test='id.itemNo!="01"'>		
			<!-- <tr onmousemove="click_color(this)" onmouseout="move(this)"> -->
			<tr>
			</s:if>
			<s:else>
			<tr style="background:yellow">
			</s:else>	 
				<td>${15*(bean.currentPage-1)+x.index+1}</td>
				<td><s:property value="id.factNo" /></td>
				<td>
				   <s:if test='id.visaSort=="W"'>臺灣簽核</s:if>
				   <s:if test='id.visaSort=="Q"'>企劃簽核</s:if>
				   <s:if test='id.visaSort=="Z"'>總務簽核</s:if>
				   <s:if test='id.visaSort=="L"'>實驗室簽核</s:if>
				   <s:if test='id.visaSort=="G"'>工程簽核</s:if>
				   <s:if test='id.visaSort=="I"'>IKT簽核</s:if>
				   <s:if test='id.visaSort=="Y"'>油壓簽核</s:if>
				   <s:if test='id.visaSort=="P"'>品管簽核</s:if>
				   <s:if test='id.visaSort=="S"'>生管簽核</s:if>
				   <s:if test='id.visaSort=="T"'>整理簽核</s:if>
				   <s:if test='id.visaSort=="B"'>備料簽核</s:if>
				   <s:if test='id.visaSort=="F"'>廠務簽核</s:if>
				   <s:if test='id.visaSort=="O"'>業務簽核</s:if>
				   <s:if test='id.visaSort.substring(0,2)=="C1"'>其他費用簽核1(<1000元)</s:if>
				   <s:if test='id.visaSort.substring(0,2)=="C2"'>其他費用簽核2(>=1000元)</s:if>
				   <s:if test='id.visaSort.substring(0,2)=="C3"'>電腦耗材簽核1(<1000元)</s:if>
				   <s:if test='id.visaSort.substring(0,2)=="C4"'>電腦耗材簽核2(>=1000元)</s:if>
				   <s:if test='id.visaSort.substring(0,2)=="C5"'>總務費用簽核1(<1000元)</s:if>
				   <s:if test='id.visaSort.substring(0,2)=="C6"'>總務費用簽核2(>=1000元)</s:if>
				</td>
				<td><s:property value="id.purmanNo" /></td>
				<td><s:property value="id.itemNo"/></td>
				<td><s:property value="visaSigner"/></td>				
				<td><s:property value="visaRank" /></td>
				<td><s:property value="flowMk" /></td>
				<s:if test='#session.loginUser.userread!="1"'>							
				<td >
					<form action="visaflow_findById" method="post" id="subform${x.index}">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.visaSort'/>" name="id.visaSort" />
							<input type="hidden" value="<s:property value='id.purmanNo'/>" name="id.purmanNo"/>
							<input type="hidden" value="<s:property value='id.itemNo'/>" name="id.itemNo"/>							
					</form> 
					
					  <a href="javascript:document.getElementById('subform${x.index}').submit()" onclick="" ><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>&nbsp;														
					<form action="visaflow_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.visaSort'/>" name="id.visaSort" />
							<input type="hidden" value="<s:property value='id.purmanNo'/>" name="id.purmanNo"/>
							<input type="hidden" value="<s:property value='id.itemNo'/>" name="id.itemNo"/>						
					</form>
					
					 <s:if test='id.itemNo!="01"'>
					  <a href="javascript:void(0)" onclick="isDelete('2subform${x.index}')"><img alt="刪除" src="images/icon/minus002.png" title="刪除" ></a>				 
					 </s:if>
					 <s:else>
					   <a disabled style="color:grey" ><img alt="刪除" src="images/icon/minus002_1.png" title="刪除" ></a>
					 </s:else>	
					 
					 <form action="visaflow_addflow" method="post" id="3subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>" name="id.factNo" />
						<input type="hidden" value="<s:property value='id.visaSort'/>" name="id.visaSort" />							
						<input type="hidden" value="<s:property value='id.purmanNo'/>" name="id.purmanNo"/>
						<input type="hidden" value="<s:property value='id.itemNo'/>" name="id.itemNo"/>
						<s:if test='flowMk=="N"'>
						   <input type="hidden" value="N" name="flowmk"/>
						</s:if>
						<s:else>
						   <input type="hidden" value="Y" name="flowmk"/>
						</s:else>							
					  <div id="div_add${x.index}" style="display:none">
					  <table>
					  <tr>
					 <td>姓名:<input type="text" name="purmanNo" id="purmanNo${x.index}" datatype="*" value=""/><span id="spurmanNo${x.index}"></span></td> 
					  <td>Email:<input type="text" name="visaSigner" id="visaSigner${x.index}" datatype="e" value=""/><span id="svisaSigner${x.index}"></span></td>
					    <td>職位:<input type="text" name="visaRank" id="visaRank${x.index}" datatype="*" value=""/><span id="svisaRank${x.index}"></span></td> 
					 </tr>
					  </table>
					  <br/><input type="submit" value="確定" onclick="javascript:check('3subform${x.index}')" id="btn${x.index}"/>
					  <input type="button" value="取消" onclick="javascript:hideDiv('div_add${x.index}')"/>
					  </div>
					  </form>	
					  <s:if test='id.factNo==#attr.loginUser.factno||#attr.loginUser.username=="admin"'>
					    <s:if test='flowMk=="Y"'>
					     <a href="javascript:void(0)" onclick="javascript:showDiv('div_add${x.index}')"><img alt="添加" src="images/icon/add001.png" title="添加" ></a>
					    </s:if>					    
					    <s:else>
					      <a href="javascript:void(0)" onclick="javascript:showDiv('div_add${x.index}')"><img alt="添加知會" src="images/icon/add001.png" title="添加知會" ></a>
					    </s:else> 
					 </s:if>					 
					 <s:else>
					   <a disabled style="color:grey" ><img alt="添加" src="images/icon/add001_1.png" title="添加" ></a>
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
	</center>	
	<hr>
		<div id="mydiv">
		<p>
			<img alt="" src="images/loading004.gif"><br> Loading....
		</p>
	</div> --%>
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
