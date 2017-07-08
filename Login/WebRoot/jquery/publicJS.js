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
              lockbtn();
           }else{
           	if(trMk=="Y"){
           		kyzvisaflowjs.findVisaSort_dwr2(dwrFactNo,type,dwremail,trMk,function(y){
                   if(y==null){                	 
               	 alert("對不起，你不是該類別函文申請人，請重新選定!");               	                                      
                    lockbtn();
                 }else{                                        
                    document.getElementById("hidden_kytype").value=y;  
                    unlockbtn();
                 }                 
               }); 
             }else{
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
/********************************【函文申請】【內部聯絡函申請】檢測流程相關方法20170707*************************************************/