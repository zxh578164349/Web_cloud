
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	

</head>
<body>
  
  <form id="public_form" method="post">						
			<table id="tb_search">
			<tr>
			<td>
			<div class="panel-group" id="accordion">
			<div class="panel panel-default">
			<div class="heading">
			<table>
			<tr><th>配方索引</th><th>配方編號</th><th>廠別</th><th>制程類別</th></tr>
			<tr>
			  <td><input type="text" placeholder="配方索引" name="formula.formulaIndex"/></td>
			  <td><input type="text" placeholder="配方編號" name="formula.formulaNo"/>	</td>
			  <td>
			     <s:if test="#session.factNo!='tw'">
				   <input type="text"  name="formula.factNo.factNo" value="${loginUser.factno}__${loginUser.erpfactno}" readonly id="factno" />									
			    </s:if>
			    <s:else>
			      <select  name="formula.factNo.factNo" onchange="makeFormulaIndex()" datatype="*" id="factno"></select>
			    </s:else>
			  </td>
			  <td>
			     <select name="formula.factCode.id" datatype="*" id="factcode" onchange="makeFormulaIndex()"></select>
			     &nbsp;&nbsp;
			     <input value="搜索" type="button" class="btn btn-primary" onclick="submis('public_form')" />
			     <!-- <input value="導出Excel" type="button" class="btn btn-primary" onclick="print('public_form','factNo','yymm','yymm2')"/> -->
			     &nbsp;
			     <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">更多</a>
			  </td>
			</tr>													 																															  			  			  			  			  				   	
			</table>
			  </div>
			  <hr/>
			  <div id="collapseOne" class="panel-collapse collapse">
			   <div class="panel-body">	
			   <table>
			     <tr><th>硬度</th><th>硬度(±值)</th><th>拉力</th><th>延伸</th></tr>
			     <tr>
			     <td><input type="text" placeholder="硬度" name="formula.pom.hardness"/></td>
			     <td><input type="text" placeholder="硬度(±值)" name="formula.pom.hardness2"/></td>
			     <td><input type="text" placeholder="拉力" name="formula.pom.forces"/></td>
			     <td><input type="text" placeholder="延伸" name="formula.pom.extend"/></td>
			     </tr>
			     <tr><th>C型撕裂</th><th>褲型撕裂</th><th>比重</th><th>比重(±值)</th></tr>
			     <tr>
			       <td><input type="text" placeholder="C型撕裂" name="formula.pom.tearingC"/></td>
			       <td><input type="text" placeholder="褲型撕裂" name="formula.pom.tearingK"/></td>
			       <td><input type="text" placeholder="比重" name="formula.pom.proportion"/></td>
			       <td><input type="text" placeholder="比重(±值)" name="formula.pom.proportion2"/>	</td>
			     </tr>
			     <tr><th>AKRON耐磨</th><th>DIN耐磨</th><th>止滑係數</th><th>耐油係數</th></tr>
			     <tr>
			       <td><input type="text" placeholder="AKRON耐磨" name="formula.pom.wresistingAkron"/></td>
			       <td><input type="text" placeholder="DIN耐磨" name="formula.pom.wresistingDin"/></td>
			       <td><input type="text" placeholder="止滑係數" name="formula.pom.ratioA"/></td>
			       <td><input type="text" placeholder="耐油係數" name="formula.pom.ratioB"/></td>
			     </tr>
			     <tr><th>耐彎曲</th><th>耐黃變</th><th>抗高壓</th><th>抗靜電</th></tr>
			     <tr>
			       <td><input type="text" placeholder="耐彎曲" name="formula.pom.ableBend"/></td>
			       <td><input type="text" placeholder="耐黃變" name="formula.pom.ableYellow"/></td>
			       <td><input type="text" placeholder="抗高壓" name="formula.pom.defyPress"/></td>
			       <td><input type="text" placeholder="抗靜電" name="formula.pom.defyEle"/></td>
			     </tr>
			     <tr><th>老化水解</th><th>收縮</th><th>彈性</th><th>壓縮</th></tr>
			     <tr>
			       <td><input type="text" placeholder="老化水解" name="formula.pom.ageing"/></td>
			       <td><input type="text" placeholder="收縮" name="formula.pom.contract"/></td>
			       <td><input type="text" placeholder="彈性" name="formula.pom.elasticity"/></td>
			       <td><input type="text" placeholder="壓縮" name="formula.pom.compression"/></td>
			     </tr>
			     <tr><th>分裂</th><th>300% Modulus</th><th>發行日期(開始)</th><th>發行日期(結束)</th></tr>
			     <tr>
			       <td><input type="text" placeholder="分裂" name="formula.pom.division"/></td>
			       <td><input type="text" placeholder="300% Modulus" name="formula.pom.modulus300"/></td>
			       <td><input type="text" id="yymm" name="issuedDate_a" onClick="WdatePicker({minDate:'{%y-1}-%m',maxDate:'#F{$dp.$D(\'yymm2\',{M:-1})||\'%y-%M-%d\'}',dateFmt:'yyyyMMdd'})" 
			       readonly="readonly" class="Wdate search"/></td>
			       <td><input type="text" id="yymm2" name="issuedDate_b" onClick="WdatePicker({minDate:'#F{$dp.$D(\'yymm\',{M:0})}',maxDate:'%y-%M-%d',dateFmt:'yyyyMMdd'})" 
			       readonly="readonly" class="Wdate search"/></td>
			     </tr>
			     <tr>
			       <th>吐霜</th><th>認證</th><th>品牌指定</th>			       			       
			     </tr>
			     <tr>
			       <td><input type="text" placeholder="吐霜" name="formula.pom.spitCream"/></td>
			       <td>
			                         是<input type="radio" value="0" name="formula.pom.authentications"/>
			                        否<input type="radio" value="1" name="formula.pom.authentications"/>
			       </td>
			       <td>
			                         指定<input type="radio" value="1" name="formula.assignBrand"/>
			                        非指定<input type="radio" value="0" name="formula.assignBrand"/> 
			       </td>
			     </tr>
			   </table>
			   		   
				<!--  <input type="text" placeholder="硬度" name="formula.pom.hardness"/>
				<input type="text" placeholder="硬度(±值)" name="formula.pom.hardness2"/>
			    <input type="text" placeholder="拉力" name="formula.pom.forces"/>
			    <input type="text" placeholder="延伸" name="formula.pom.extend"/>			    
			    <br/>
			    <input type="text" placeholder="C型撕裂" name="formula.pom.tearingC"/>
			    <input type="text" placeholder="褲型撕裂" name="formula.pom.tearingK"/>
			     <input type="text" placeholder="比重" name="formula.pom.proportion"/>
			     <input type="text" placeholder="比重(±值)" name="formula.pom.proportion2"/>			    		           
			    <br/>
			    <input type="text" placeholder="AKRON耐磨" name="formula.pom.wresistingAkron"/>
			    <input type="text" placeholder="DIN耐磨" name="formula.pom.wresistingDin"/>
			    <input type="text" placeholder="止滑係數" name="formula.pom.ratioA"/>
			    <input type="text" placeholder="耐油係數" name="formula.pom.ratioB"/>				          			     
			    </br>
			    <input type="text" placeholder="耐彎曲" name="formula.pom.ableBend"/>
			     <input type="text" placeholder="耐黃變" name="formula.pom.ableYellow"/>
			     <input type="text" placeholder="抗高壓" name="formula.pom.defyPress"/>
			     <input type="text" placeholder="抗靜電" name="formula.pom.defyEle"/>
			     <br/>			     
			     <input type="text" placeholder="老化水解" name="formula.pom.ageing"/>   
			     <input type="text" placeholder="收縮" name="formula.pom.contract"/>
			     <input type="text" placeholder="彈性" name="formula.pom.elasticity"/>
			     <input type="text" placeholder="壓縮" name="formula.pom.compression"/>			     
			    <br/>
			    <input type="text" placeholder="分裂" name="formula.pom.division"/>
			    <input type="text" placeholder="300% Modulus" name="formula.pom.modulus300"/>			    
			    <input type="text" id="yymm" name="issuedDate_a" onClick="WdatePicker({minDate:'{%y-1}-%m',maxDate:'#F{$dp.$D(\'yymm2\',{M:-1})||\'%y-%M-%d\'}',dateFmt:'yyyyMMdd'})" readonly="readonly" class="Wdate search"/>至
			    <input type="text" id="yymm2" name="issuedDate_b" onClick="WdatePicker({minDate:'#F{$dp.$D(\'yymm\',{M:0})}',maxDate:'%y-%M-%d',dateFmt:'yyyyMMdd'})" readonly="readonly" class="Wdate search"/>			           
			    <br/>
                <input type="text" placeholder="吐霜" name="formula.pom.spitCream"/>
			           認證:是<input type="radio" value="0" name="formula.pom.authentications"/>
			                        否<input type="radio" value="1" name="formula.pom.authentications"/>
			    &nbsp;&nbsp;&nbsp;                    
			           品牌指定:指定<input type="radio" value="1" name="formula.assignBrand"/>
			                        非指定<input type="radio" value="0" name="formula.assignBrand"/>                  
			    <br/>-->
			  </div>
		      </div>
			  </div>
			  </div>
			</td>
		    </tr>
		</table>
	</form>
</body>
</html>
