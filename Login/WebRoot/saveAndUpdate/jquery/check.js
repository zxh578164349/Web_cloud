

   function check(){
       var factno=document.getElementById("dwr_factno").value;
       var factcode=document.getElementById("dwrFactArea").value;
       var yymmdd=document.getElementById("dwr_yymmdd").value;
       if(factno!=""&&factcode!=""&&yymmdd!=""){
          webmix1js.check(factno,factcode,yymmdd,function(x){
              if(x==true){
              alert("�ƾڮw�w�s�b("+factno+factcode+yymmdd+")");
              document.getElementById("sub").disabled=true;
              document.getElementById("sub").value="�ƾڭ���";
              document.getElementById("sub").style.color="red";
              document.getElementById("error1").innerHTML="<font color='color'>�I</font>";
              document.getElementById("error2").innerHTML="<font color='color'>�I</font>";
              document.getElementById("error3").innerHTML="<font color='color'>�I</font>";
          }else{
            document.getElementById("sub").disabled=false;
            document.getElementById("sub").value="�T�w";
            document.getElementById("sub").style.color="white";
            document.getElementById("error1").innerHTML="";
            document.getElementById("error2").innerHTML="";
            document.getElementById("error3").innerHTML="";
          }        
          });               
       }                    
   }