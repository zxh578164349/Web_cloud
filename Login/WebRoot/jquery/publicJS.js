// JavaScript Document

/********************************【函文申請】【內部聯絡函申請】檢測流程相關方法20170707*************************************************/
function lockbtn(){
	document.getElementById("sub").disabled=true;
    document.getElementById("sub").style.color="red";
    document.getElementById("dwr_kytype").style.color="red";
    jq("#hidden_kytype").val("");
}

function unlockbtn(data){
	document.getElementById("sub").disabled=false;
    document.getElementById("sub").style.color="white";
    document.getElementById("dwr_kytype").style.color="black";
   // document.getElementById("hidden_kytype").value=data;
    jq("#hidden_kytype").val(data);
}

function showdiv(){
	jq("#div_webform").show();
	jq("#div_webform2").show();
	jq("#div_depar").show();
	jq("#div_depar2").show();
}
function hidediv(){
	jq("#div_webform").hide();
	jq("#div_webform2").hide();
	jq("#div_depar").hide();
	jq("#div_depar2").hide();
	
}


//檢測函文流程20170707
function checkType(){
    var dwrFactNo=document.getElementById("dwrFactNo").value;
    var dwremail=document.getElementById("dwr_email").value.toLowerCase(); //登錄人的email要轉化爲小寫,因爲申請人email已全部轉化爲小寫（20151022）     
    var trMk=jq("input[name='trMk']:checked").val();
    var type=jq("#dwr_kytype").val();
    var depId=jq("#sel_depar").val();
	var fid=jq("select[name='fid']").val();
	var factCode=jq("#dwrFactArea").val();
	
	removeobjs();	
    if(dwrFactNo!=""&&type!=""){
        kyzvisaflowjs.findByType_Dwr(dwrFactNo,type,function(x){
           if(x==0){//流程不存在
              layer.alert("該主類型審核流程不存在，請重新選定!");  
              hidediv();
              lockbtn();
           }else{
           	if(trMk=="Y"){          		
           		/*********************修改20190424************************/ 
           		if(fids>0){           			
           			if(depId!=null&&depId!=""||(fid!=null&&fid!="")){
           				if(dwrFactNo=="HC"||dwrFactNo=="V10"){         					
           					if(factCode!=null&&factCode!=""){          						
                   				checkType2(dwrFactNo,type,trMk,depId,fid,factCode);
           					}          					              				           					
           				}else{           					
           					checkType2(dwrFactNo,type,trMk,depId,fid,"null"); 
           				}          				        				
           			}          			
           		}else{          			
           			checkbyEmail(dwrFactNo,type,dwremail,trMk,depId);
           		}
           		
           		/*********************修改20190424************************/
             }else{        
             hidediv();	 
           	 type=type+"_AA"; 
           	 kyzvisaflowjs.findNums(dwrFactNo,type,function(y){
                   if(y==0){                 	
                 	  layer.alert("該流程(不分部門)還沒有建立");                 	                                           
                      lockbtn();
                   }else{                                                                    
                     // document.getElementById("hidden_kytype").value=type; 
                      unlockbtn(type);
                   }                    
                }); 
           }               
          }                               
        });
    }
 }

//選出流程，此流程不會細分到部門 或者小類

function checkbyEmail(dwrFactNo,type,dwremail,trMk,depId){
	hidediv();
	jq.ajax({
			type:"post",
			url:"visaflow_findVisaSort_dwr5",
			dataType:"json",
			data:{factNo:dwrFactNo,visaSort:type,visaSigner:dwremail,trMk:trMk},
			success:function(data){
				if(data==null||data.length==0){
				 layer.alert("對不起，你不是該類別函文申請人，請重新選定!");
				 lockbtn();				
				}
				if(data.length==1){
					//document.getElementById("hidden_kytype").value=data[0];  
                    unlockbtn(data[0]);                    
				}
				if(data.length>1){
					if(depids==0){
						loaddepments(dwrFactNo, type, dwremail, trMk);
					}else{
						checkType2(dwrFactNo,type,trMk,depId,null,"null");
						depids=0;
					}
					
   				}
			},
			error:function(error){
				layer.alert("數據出錯");
				lockbtn();
			}
			
		});
}

//選出流程，此流程細分到部門  ，或者小類
function checkType2(dwrFactNo,type,trMk,depId,fid,factCode) {	
	jq.ajax({
		type : "post",		
		url: "visaflow_findVisaSort_dwr_depidAndfid",
		dataType : "json",
		data : {
			factNo : dwrFactNo,
			visaSort : type,
			trMk : trMk,
			depId : depId,
			fid:fid,
			factCode:factCode
		},
		success:function(data){
			if((depId!=null&&depId!="")||(fid!=null&&fid!="")){
				if(data!=null){
					//document.getElementById("hidden_kytype").value=data;
					unlockbtn(data);
				}else{
					layer.alert("該小類流程還沒有建立，請重新選擇");
					lockbtn();
				}
			}									
		},
		error:function(error){
			layer.alert("數據出錯");
			lockbtn();
		}
	});
}

var depids=0;
//加載部門
function loaddepments(dwrFactNo, type, dwremail, trMk) {	
	jq.ajax({
		type : "post",
		dataType : "json",
		url : "visaflow_findVisaSort_dwr3",
		data : {
			factNo : dwrFactNo,
			visaSort : type,
			visaSigner : dwremail,
			trMk : trMk
		},
		success : function(data) {
			depids=data.length;
			jq("#sel_depar").empty();
			layer.alert("當前帳號存在多個部門，請選擇部門");
			var item = "<option value=''>請選擇部門</option>";
			jq.each(data, function(i, obj) {
				item += "<option value='" + obj[0] + "'>" + obj[1]
						+ "</option>";
			});
			jq("#sel_depar").append(item);			
			jq("#div_depar").show();
			jq("#div_depar2").show();
		},
		error : function(error) {
			layer.alert("加載部門數據出錯");
			lockbtn();
		}
	});			
}


//加載小類
var fids=0;
var bigType=0;
function loadwebformtypes(){ 
	jq("select[name='fid']").empty();
	var dwrFactNo=document.getElementById("dwrFactNo").value;        
    var trMk=jq("input[name='trMk']:checked").val();
    var type=jq("#dwr_kytype").val();
    if(dwrFactNo!=""&&type!=""&&trMk=="Y"){
    	jq.ajax({
			type:"post",
			dateType:"json",
			data:{factNo:dwrFactNo,typeNo:type},
			url:"webformtype_findWebformByFactnoTypeno",
			async:false,
			success:function(data){							
				fids=data.length;
				var item="";
				if(data.length>0){					
					item+="<option value=''>請選擇小類別</option>";
					jq.each(data,function(i,obj){
						item+="<option value="+obj[0]+">"+obj[1]+"</option>";						
					});
					jq("select[name='fid']").append(item);
					jq("#div_webform").show();	
					jq("#div_webform2").show();	
				}else{
					jq("#div_webform").hide();	
					jq("#div_webform2").hide();
				}				
				
			},
			error : function(error) {
				layer.alert("加載小類別數據出錯");
				lockbtn();
			}
		});
    }else{
    	hidediv();
    }			
}


function loadfactcode() {
	var factNo=jq("#dwrFactNo").val();
	if(factNo!=null&&factNo!=""){
		jq.ajax({
			type : "post",
			dataType : "json",
			url : "visaflow_findFactCode",
			data : {
				factNo : factNo			
			},
			success : function(data) {
				jq("#dwrFactArea").empty();
				if(data.length>1){
					layer.alert("該工廠存在多個廠別狀態的流程，請選擇廠別狀態");
					var item = "<option value=''>請選擇廠別狀態</option>";
					jq.each(data, function(i, obj) {
						item += "<option value='" + obj+ "'>" + obj
								+ "</option>";
					});
					jq("#dwrFactArea").append(item);			
					jq("#dwrFactArea").removeAttr("disabled");
					jq("#dwrFactArea").css("background-color","");
				}else{
					jq("#dwrFactArea").attr("disabled","disabled");
					jq("#dwrFactArea").css("background-color","#eeeeee");
				}				
			},
			error : function(error) {
				layer.alert("加載廠別狀態數據出錯");
				jq("#dwrFactArea").attr("disabled","disabled");
				jq("#dwrFactArea").css("background-color","#eeeeee");
				lockbtn();
			}
		});
	}				
}

function getFactArea(mid) {
	document.getElementById("dwrFactArea").length = 1;
	webfactjs.findFactCodeByFactNo(mid, function(x) {
		dwr.util.addOptions("dwrFactArea", x);
	});
	
}

function removeobjs(){
	var dwrFactNo=document.getElementById("dwrFactNo").value;
	if(dwrFactNo=="HC"||dwrFactNo=="V10"){
		jq("#dwrFactArea").attr("datatype","*");
		jq("#dwrFactArea").attr("onchange","checkType()");
	}else{
		jq("#dwrFactArea").removeAttr("datatype onchange");
	}
}

