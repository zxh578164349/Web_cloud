package util;

import org.apache.struts2.ServletActionContext;

public class Cookie {
	public static String getCookName() {		
		javax.servlet.http.Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		javax.servlet.http.Cookie cookie=null;
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				cookie=cookies[i];
				if(cookie!=null){
					if(cookie.getName().equals("user")){
						String all=cookie.getValue();
						String username="";
						String[]alls=all.split(",");
						username=alls[1];
						return username;
					}																				
				}
			}
		}else{
			System.out.print("還沒有cookie");
		}			
		return "";
	}
		
	public static String getCookFactNo(){
		javax.servlet.http.Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		javax.servlet.http.Cookie cookie=null;
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				cookie=cookies[i];
				if(cookie!=null){
					if(cookie.getName().equals("user")){
						String all=cookie.getValue();
						String factNo="";
						String[]alls=all.split(",");					
						factNo=alls[0];
						return factNo;
					}																				
				}
			}
		}else{
			System.out.print("還沒有cookie");
		}
		
		return "";
	}
	
	public static String getCookAll() {		
		javax.servlet.http.Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		javax.servlet.http.Cookie cookie=null;
		String result=",,";
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				cookie=cookies[i];
				if(cookie!=null){
					if(cookie.getName().equals("user")){
						result=cookie.getValue();
						return result;
					}																				
				}
			}
		}else{
			System.out.print("還沒有cookie");
		}			
		return result;
	}
	
	public static String clearCookie(){
		javax.servlet.http.Cookie[] cookies=ServletActionContext.getRequest().getCookies();
		//javax.servlet.http.Cookie cookie=null;
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				javax.servlet.http.Cookie cookie=new javax.servlet.http.Cookie(cookies[i].getName(),null);
				cookie.setMaxAge(0);
				ServletActionContext.getResponse().addCookie(cookie);				
			}
		}
		return "null";
	}
}
