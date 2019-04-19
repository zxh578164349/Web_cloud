// JavaScript Document

/********************************【函文申請】【內部聯絡函申請】檢測流程相關方法20170707*************************************************/
function lockbtn(){
	document.getElementById("sub").disabled=true;
    document.getElementById("sub").style.color="red";
    document.getElementById("dwr_kytype").style.color="red";
}

function unlockbtn(){
	document.getElementById("sub").disabled=false;
    document.getElementById("sub").style.color="white";
    document.getElementById("dwr_kytype").style.color="black";
}


//檢測函文流程20170707
function checkType(){	  
    var dwrFactNo=document.getElementById("dwrFactNo").value;
    var dwremail=document.getElementById("dwr_email").value.toLowerCase(); //登錄人的email要轉化爲小寫,因爲申請人email已全部轉化爲小寫（20151022）     
    var trMk=jq("input[name='trMk']:checked").val();
    var type=jq("#dwr_kytype").val();
    if(dwrFactNo!=""&&type!=""){
        kyzvisaflowjs.findByType_Dwr(dwrFactNo,type,function(x){
           if(x==0){//流程不存在
              layer.alert("該類型審核流程不存在，請重新選定!");  
              jq("#sel_depar").removeAttr("datatype");
 			  jq("#div_depar").hide();
              lockbtn();
           }else{
           	if(trMk=="Y"){          		
           		/*********************修改20170830************************/
           		jq.ajax({
           			type:"post",
           			url:"visaflow_findVisaSort_dwr5",
           			dataType:"json",
           			data:{factNo:dwrFactNo,visaSort:type,visaSigner:dwremail,trMk:trMk},
           			success:function(data){
           				if(data==null||data.length==0){
           				 layer.alert("對不起，你不是該類別函文申請人，請重新選定!");
           				 lockbtn();
           				jq("#sel_depar").removeAttr("datatype");
           				jq("#div_depar").hide();
           				}
           				if(data.length==1){
           					document.getElementById("hidden_kytype").value=data[0];  
                            unlockbtn();
                            jq("#sel_depar").removeAttr("datatype");
               				jq("#div_depar").hide();
           				}
           				if(data.length>1){
           					checkType3(dwrFactNo, type, dwremail, trMk, type);
           				}
           			}
           		});
           		/*********************修改20170830************************/
             }else{
            jq("#sel_depar").removeAttr("datatype");
    		jq("#div_depar").hide();	 
           	 type=type+"_AA"; 
           	 kyzvisaflowjs.findNums(dwrFactNo,type,function(y){
                   if(y==0){                 	
                 	   layer.alert("該流程(不分部門)還沒有建立");                 	                                           
                      lockbtn();
                   }else{                                                                    
                      document.getElementById("hidden_kytype").value=type; 
                      unlockbtn();
                   }                    
                }); 
           }               
          }                               
        });
    }
 }

//選出流程，此流程細分到部門  ，或者小類
function checkType2() {
	var dwrFactNo = document.getElementById("dwrFactNo").value;
	var dwremail = document.getElementById("dwr_email").value.toLowerCase(); // 登錄人的email要轉化爲小寫,因爲申請人email已全部轉化爲小寫（20151022）
	var trMk = jq("input[name='trMk']:checked").val();
	var type = jq("#dwr_kytype").val();
	var depId=jq("#sel_depar").val();
	var fid=jq("select[name='fid']").val();

	jq.ajax({
		type : "post",		
		url: "visaflow_findVisaSort_dwr_depidAndfid",
		dataType : "json",
		data : {
			factNo : dwrFactNo,
			visaSort : type,
			visaSigner : dwremail,
			trMk : trMk,
			depId : depId,
			fid:fid
		},
		success:function(data){
			if(data!=null){
				document.getElementById("hidden_kytype").value=data;
				unlockbtn();
			}else{
				layer.alert("該流程還沒有建立，請重新選擇");
				lockbtn();
			}						
		},
		error:function(error){
			layer.alert("數據出錯");
			lockbtn();
		}
	});
}

//加載部門
function checkType3(dwrFactNo, type, dwremail, trMk, type) {
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
			jq("#sel_depar").empty();
			layer.alert("當前帳號存在多個部門，請選擇部門");
			var item = "<option value=''>請選擇部門</option>";
			jq.each(data, function(i, obj) {
				item += "<option value='" + obj[0] + "'>" + obj[1]
						+ "</option>";
			});
			jq("#sel_depar").append(item);
			jq("#sel_depar").attr("datatype", "*");
			jq("#div_depar").show();
		},
		error : function(error) {
			layer.alert("錯誤checkType3");
			lockbtn();
		}
	});
}


//加載小類
function loadwebformtypes(){
    var trMk=jq("input[name='trMk']:checked").val();
	var factno=jq("#dwrFactNo").val();		
	var visasort=jq("#dwr_kytype").val();
	if(trMk=="Y"&&factno!=""&&visasort!=""){
		jq.ajax({
			type:"post",
			dateType:"json",
			data:{factNo:factno,typeNo:visasort},
			url:"webformtype_findWebformByFactnoTypeno",
			async:false,
			success:function(data){				
				jq("select[name='fid']").empty();
				var item="";
				if(data.length>0){
					item+="<option value=''>請選擇小類別</option>";
					jq.each(data,function(i,obj){
						item+="<option value="+obj[0]+">"+obj[1]+"</option>";						
					});
					jq("select[name='fid']").append(item);
					jq("#div_webform").show();									
				}else{
					jq("#div_webform").hide();						
				}				
				
			}
		});
	}else{
		jq("#div_webform").hide();
	}				
}