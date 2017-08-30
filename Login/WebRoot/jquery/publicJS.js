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
              alert("該類型審核流程不存在，請重新選定!");  
              jq("#sel_depar").removeAttr("datatype");
 			  jq("#div_depar").hide();
              lockbtn();
           }else{
           	if(trMk=="Y"){
           		/*kyzvisaflowjs.findVisaSort_dwr2(dwrFactNo,type,dwremail,trMk,function(y){
                   if(y==null){                	 
               	 alert("對不起，你不是該類別函文申請人，請重新選定!");               	                                      
                    lockbtn();
                 }else{                                        
                    document.getElementById("hidden_kytype").value=y;  
                    unlockbtn();
                 }                 
               }); */
           		/*********************修改************************/
           		jq.ajax({
           			type:"post",
           			url:"visaflow_findVisaSort_dwr3",
           			dataType:"json",
           			data:{factNo:dwrFactNo,visaSort:type,visaSigner:dwremail,trMk:trMk},
           			success:function(data){
           				if(data==null||data.length==0){
           				 alert("對不起，你不是該類別函文申請人，請重新選定!");
           				 lockbtn();
           				jq("#sel_depar").removeAttr("datatype");
           				jq("#div_depar").hide();
           				}
           				if(data.length==1){
           					document.getElementById("hidden_kytype").value=data[0][0];  
                            unlockbtn();
                            jq("#sel_depar").removeAttr("datatype");
               				jq("#div_depar").hide();
           				}
           				if(data.length>1){
           					jq("#sel_depar").empty();
           					alert("當前帳號存在多個部門，請選擇部門");
           					var item="<option value=''>請選擇部門</option>";
           					jq.each(data,function(i,obj){
        						item+="<option value='"+obj[0]+"'>"+obj[1]+"</option>";
        					});
           					jq("#sel_depar").append(item);
           					jq("#sel_depar").attr("datatype","*");
           					jq("#div_depar").show();
           				}
           			}
           		});
           		/*********************修改************************/
             }else{
            jq("#sel_depar").removeAttr("datatype");
    		jq("#div_depar").hide();	 
           	 type=type+"_AA"; 
           	 kyzvisaflowjs.findNums(dwrFactNo,type,function(y){
                   if(y==0){                 	
                 	   alert("該流程(不分部門)還沒有建立");                 	                                           
                      lockbtn();
                   }else{                       
                      //document.getElementById("hidden_kytype").value=y;                       
                      document.getElementById("hidden_kytype").value=type; 
                      unlockbtn();
                   }                    
                }); 
           }               
          }                               
        });
    }
 }

function checkType2() {
	var dwrFactNo = document.getElementById("dwrFactNo").value;
	var dwremail = document.getElementById("dwr_email").value.toLowerCase(); // 登錄人的email要轉化爲小寫,因爲申請人email已全部轉化爲小寫（20151022）
	var trMk = jq("input[name='trMk']:checked").val();
	var type = jq("#dwr_kytype").val();
	var depId=jq("#sel_depar").val();

	jq.ajax({
		type : "post",
		url : "visaflow_findVisaSort_dwr4",
		dataType : "json",
		data : {
			factNo : dwrFactNo,
			visaSort : type,
			visaSigner : dwremail,
			trMk : trMk,
			depId : depId
		},
		success:function(data){
			document.getElementById("hidden_kytype").value=data;
			unlockbtn();
		},
		error:function(error){
			alert("錯誤");
			lockbtn();
		}
	});
}
/********************************【函文申請】【內部聯絡函申請】檢測流程相關方法20170707*************************************************/