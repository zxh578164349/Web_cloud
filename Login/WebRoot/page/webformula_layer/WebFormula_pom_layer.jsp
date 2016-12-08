<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

<title>My JSP 'WebMixPersonSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
</head>

<body>	   	        	            	           
	            <table class="table table-condensed table-bordered">	              
	                 <tr class="active">
	                    <th>物性編號</th><th>配方索引</th><th>品牌</th>
	                 </tr>	                          	                
	               <tr>
	                  <td>	                    	                   
	                      <s:property value='formula.pom.pomNo'/>						     	                   
	                  </td>
	                  <td>
	                      <s:property value='formula.formulaIndex'/>
	                  </td>
	                  <td>	                     						
						  <s:property value='formula.pom.webBrank.name'/>						   
	                  </td>
	               </tr>
	                   <tr class="active">
	                      <th>名稱</th><th>規格</th><th>測試方式說明</th>	
	                   </tr>	                
	                <tr>
	                   <td>硬度</td>
	                   <td>
	                      <s:property value='formula.pom.hardness'/><br/>
	                      <s:property value='formula.pom.hardness2'/>(±值)
	                   </td>
	                   <td>
	                      <s:property value='formula.pom.hardnessDescription'/>
	                   </td>
	                </tr>
	                <tr>
	                   <td>拉力</td>
	                   <td><s:property value='formula.pom.forces'/></td>
	                   <td><s:property value='formula.pom.forcesDescription'/></td>
	                </tr>
	                <tr>
	                   <td>延伸</td>
	                   <td><s:property value='formula.pom.extend'/></td>
	                   <td><s:property value='formula.pom.extendsDescription'/></td>
	                </tr>
	                <tr>
	                   <td>C型撕裂</td>
	                   <td><s:property value='formula.pom.tearingC'/></td>
	                   <td><s:property value='formula.pom.tearingCDescription'/></td>
	                </tr>
	                <tr>
	                   <td>褲型撕裂</td>
	                   <td><s:property value='formula.pom.tearingK'/></td>
	                   <td><s:property value='formula.pom.tearingKDescription'/></td>
	                </tr>
	                <tr>
	                   <td>比重</td>
	                   <td>
	                      <s:property value='formula.pom.proportion'/><br/>
	                      <s:property value='formula.pom.proportion2'/>(±值)
	                   </td>
	                   <td>
	                     <s:property value='formula.pom.proportionDescription'/>
	                   </td>
	                </tr>
	                <tr>
	                   <td>AKRON耐磨</td>
	                   <td><s:property value='formula.pom.wresistingAkron'/></td>
	                   <td><s:property value='formula.pom.wresistingAkronDes'/></td>
	                </tr>
	                <tr>
	                   <td>DIN耐磨</td>
	                   <td><s:property value='formula.pom.wresistingDin'/></td>
	                   <td><s:property value='formula.pom.wresistingDinDes'/></td>
	                </tr>
	                <tr>
	                   <td>止滑係數</td>
	                   <td><s:property value='formula.pom.ratioA'/></td>
	                   <td><s:property value='formula.pom.ratioADes'/></td>
	                </tr>
	                <tr>
	                   <td>耐油係數</td>
	                   <td><s:property value='formula.pom.ratioB'/></td>
	                   <td><s:property value='formula.pom.ratioBDes'/></td>
	                </tr>
	                <tr>
	                   <td>耐彎曲</td>
	                   <td><s:property value='formula.pom.ableBend'/></td>
	                   <td><s:property value='formula.pom.ableBendDes'/></td>
	                </tr>
	                <tr>
	                   <td>耐黃變</td>
	                   <td><s:property value='formula.pom.ableYellow'/></td>
	                   <td><s:property value='formula.pom.ableYellowDes'/></td>
	                </tr>
	                <tr>
	                   <td>抗高壓</td>
	                   <td><s:property value='formula.pom.defyPress'/></td>
	                   <td><s:property value='formula.pom.defyPressDes'/></td>
	                </tr>
	                <tr>
	                   <td>抗靜電</td>
	                   <td><s:property value='formula.pom.defyEle'/></td>
	                   <td><s:property value='formula.pom.defyEleDes'/></td>
	                </tr>
	                <tr>
	                   <td>老化水解</td>
	                   <td><s:property value='formula.pom.ageing'/></td>
	                   <td><s:property value='formula.pom.ageingDes'/></td>
	                </tr>
	                <tr>
	                   <td>收縮</td>
	                   <td><s:property value='formula.pom.contract'/></td>
	                   <td><s:property value='formula.pom.contractDes'/></td>
	                </tr>
	                <tr>
	                   <td>彈性</td>
	                   <td><s:property value='formula.pom.elasticity'/></td>
	                   <td><s:property value='formula.pom.elasticityDes'/></td>
	                </tr>
	                <tr>
	                   <td>壓縮</td>
	                   <td><s:property value='formula.pom.compression'/></td>
	                   <td><s:property value='formula.pom.compressionDes'/></td>
	                </tr>
	                <tr>
	                   <td>分裂</td>
	                   <td><s:property value='formula.pom.division'/></td>
	                   <td><s:property value='formula.pom.divisionDes'/></td>
	                </tr>
	                <tr>
	                   <td>300% Modulus</td>
	                   <td><s:property value='formula.pom.modulus300'/></td>
	                   <td><s:property value='formula.pom.modulus300Des'/></td>
	                </tr>
	                <tr>
	                   <td>吐霜</td>
	                   <td><s:property value='formula.pom.spitCream'/></td>
	                   <td><s:property value='formula.pom.spitCreamDes'/></td>
	                </tr>
	                <tr>
	                   <td>認證</td>
	                   <td colspan="2">
	                       <s:if test='formula.pom.authentications=="0"'>
					                         是					                      							
						   </s:if> 
						   <s:else>					                      
					                       否
						   </s:else> 						   
	                   </td>
	                </tr>
	                <tr>
	                   <td>特性說明</td>
	                   <td colspan="2">
	                      <textarea style="width:100%;height:100px"><s:property value='formula.pom.instruction'/></textarea>					      							                     	                    
	                   </td>                 
	                </tr>	                                         											
</table>	


	<hr/>		
     <s:if test="formula.pom.webTabpomfiles.size>0">		
      <div id="webtabfiledao">	
	    <b style="color: blue">附檔:</b><br/>
		 <div id="fileJson">
		   <s:iterator value="formula.pom.webTabpomfiles">
		        <a href="/upload_webtabpom/<s:property value='id.webTabpom.pomNo'/>/<s:property value="%{toUrl2(id.filename)}"/>" target="_blank">
		        <s:property value="id.filename"/>&nbsp;
		        </a>		        
		   </s:iterator>
		  </div>	  
     </div>		
    <hr/>
</s:if> 	 
	
</body>
</html>
