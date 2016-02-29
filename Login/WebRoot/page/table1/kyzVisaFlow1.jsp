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
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> 
<script type="text/javascript" src="page/jquerys/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">

	   
	   	function addflow(subform,btn){
	   		var jq = jQuery.noConflict();
	   		var loadi;
	   		jq(document).ajaxStart(function(){
	   			loadi=layer.load(0);
	   		});
	   		jq(document).ajaxStop(function(){
	   			layer.close(loadi);
	   		});
	   		jq(subform).Validform({
	   			btnSubmit : btn,
	   			tiptype : 4,
	   			showAllError : true,
	   			tipSweep : true,
	   			ajaxPost:true,
	   			callback:function(data){	   				
	   					if(data=="0"){
	   						layer.msg("提交成功!",3,1);
	   						location.href="/Login/visaflow_findPageBean";
	   					}
	   					if(data=="1"){
	   						alert(data.responseText);
	   					}
	   			}	   			
	   			});
	   			
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
	<table class="table table-striped table-hover table-bordered" >
		<caption ><h4>審核流程</h4></caption>
		<thead>			
			<tr class="tr_show">			    
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
			<tr class="warning">
			</s:else>	 
				
				<td><s:property value="id.factNo" /></td>
				<td>				  
				   <s:property value="colTemp"/>
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
					
					  <a href="javascript:layer.load(0);document.getElementById('subform${x.index}').submit()"><img alt="修改" src="images/icon/edit001.png" title="修改" ></a>&nbsp;														
					<form action="visaflow_delete" method="post" id="2subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden"
							value="<s:property value='id.visaSort'/>" name="id.visaSort" />
							<input type="hidden" value="<s:property value='id.purmanNo'/>" name="id.purmanNo"/>
							<input type="hidden" value="<s:property value='id.itemNo'/>" name="id.itemNo"/>						
					</form>
					<form action="visaflow_findMaxItem" method="post" id="3subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>" name="factNo" /> 
					    <input type="hidden" value="<s:property value='id.visaSort'/>" name="visaSort" />																				
					</form>
					<form action="visaflow_deleteFirst" method="post" id="4subform${x.index}"
						style="float:left">
						<input type="hidden" value="<s:property value='id.factNo'/>"
							name="id.factNo" /> <input type="hidden" value="<s:property value='id.visaSort'/>" name="id.visaSort" />							
							<input type="hidden" value="<s:property value='id.purmanNo'/>" name="id.purmanNo"/>
							<input type="hidden" value="<s:property value='id.itemNo'/>" name="id.itemNo"/>						
					</form>
					
					 <s:if test='id.itemNo!="01"'>
					  <a href="javascript:void(0)" onclick="isDelete('2subform${x.index}')"><img alt="刪除" src="images/icon/minus002.png" title="刪除" ></a>				 					  
					 </s:if>
					 <s:else>
					   <a href="javascript:void(0)" onclick="isDelete2('${temp.id.factNo}','${temp.id.visaSort}')"><img alt="刪除全部" src="images/icon/delete_all.png" title="刪除全部" ></a>
					   <a href="javascript:document.getElementById('3subform${x.index}').submit()"><img alt="添加知會" src="images/icon/add001_2.png" title="添加知會"></a>
					 </s:else>	
					 
					 <form action="visaflow_addflow" method="post" id="5subform${x.index}"
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
					 <td>姓名:<input type="text" name="purmanNo" id="purmanNo${x.index}" datatype="*" /></td> 
					  <td>Email:<input type="text" name="visaSigner" id="visaSigner${x.index}" datatype="e" /></td>
					    <td>職位:<input type="text" name="visaRank" id="visaRank${x.index}" datatype="*" /></td> 
					 </tr>
					  </table>
					  <br/><input type="submit" value="確定"  id="btn${x.index}" onclick="addflow('#5subform${x.index}','#btn${x.index}')"/>
					  <input type="button" value="取消" onclick="hideDiv('div_add${x.index}')"/>
					  </div>
					  </form>	
					  <s:if test='id.factNo==#attr.loginUser.factno||#attr.loginUser.username=="admin"'>
					    <s:if test='flowMk=="Y"'>
					     <a href="javascript:void(0)" onclick="javascript:showDiv('div_add${x.index}')"><img alt="添加" src="images/icon/add001.png" title="添加" ></a>
					    </s:if>					    
					    <s:else>
					      <a disabled style="color:grey" ><img alt="添加" src="images/icon/add001_1.png" title="添加" ></a>
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
