

// JavaScript Document


function checkNull(){
	var sYear=document.getElementById("syymm"); 
	var sYear2=document.getElementById("syymm2");
	var year=document.getElementById("nyear");
	var month=document.getElementById("nmonth");
	var form=document.getElementById("form");
	if(year.value=="-1"||month.value=="-1"){
		sYear.innerHTML="<font color='red'>(年月不能為空)</font>";
		 sYear2.innerHTML="<font color='red'>*</font>";
		 return false;
	}else{
		
		form.submit();
	}
}

function clear1(){
	var sYear=document.getElementById("syymm"); 
	var sYear2=document.getElementById("syymm2");
	var year=document.getElementById("nyear");
	var month=document.getElementById("nmonth");
	if(year.value!=-1&&month.value!=-1){
		sYear.innerHTML="";
		sYear2.innerHTML="";
	}
    /*var cangBie=document.getElementById("factNo");
	var danHao=document.getElementById("billNo");
	var year=document.getElementById("yymm");
	var sCangBie=document.getElementById("sfactNo");
	var sDanHao=document.getElementById("sbillNo"); 
	var sYear=document.getElementById("syymm"); 
	if(cangBie.value!="-1"){
	  sCangBie.innerHTML="";
	}
	
	if(danHao.value!=""){
	  sDanHao.innerHTML="";
	}
	
	if(year.value!=""){
	  sYear.innerHTML="";
	}*/

}	