/**
 * 
 */
package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.KyVisabillm;
import entity.WebUser;

/**   
 *    
 * 项目名称：Session過期監聽器 
 * 类名称：SessionTimeOutFilter   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/5/11 上午11:03:19   
 * 修改人：Administrator   
 * 修改时间：2016/5/11 上午11:03:19   
 * 修改备注：   
 * @version    
 *    
 **/
public class SessionTimeOutFilter_Login implements Filter{

	/**
	 * 日期:2016/5/11
	 * 描述:
	 */
	String url_a=null;
	String url_b=null;
	String url_c=null;
	public void destroy() {
		// TODO Auto-generated method stub				
	}

	/**
	 * 日期:2016/5/11
	 * 描述:
	 */
	
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		/**
		 * vbm_findById_email:郵件登錄審核
		 * print2Ypoi_print2Y_hb:自動發送產量報表
		 * webfactOrder_print_email:自動發送工廠訂單
		 * 以上3箇url,用戶user登錄狀態都爲null
		 * 舊地址:webfact_findAllfact
		 * 新地址:webfact_findAllWebfact
		 */
		HttpServletRequest httprequest=(HttpServletRequest)request;
		HttpServletResponse httpresponse=(HttpServletResponse)response;
		httpresponse.setContentType("text/html;charset=utf-8");
		String requestURL=httprequest.getRequestURI();
		WebUser user=(WebUser)httprequest.getSession().getAttribute("loginUser");
		KyVisabillm vbm=(KyVisabillm)httprequest.getSession().getAttribute("vbm");
		System.out.println(requestURL);		
		/*if(requestURL.contains("webfact_findAllfact")){//如果登錄后，使用webfact_findAllfact舊地址，則讓user爲null,讓地址失效   20161115
			user=null;
		}
		if(!requestURL.equals("/")&&!requestURL.contains("userlogin")&&!requestURL.contains("webfact_findAllWebfact")&&!requestURL.equals("/Login/")&&!requestURL.contains("loginpage")&&
			!requestURL.contains("judge.jsp")&&!requestURL.contains("vbm_findById_email")&&!requestURL.contains("print2Ypoi_print2Y_hb")&&
			!requestURL.contains("webfactOrder_print_email")&&!requestURL.equals("/Login_scm/")&&!requestURL.contains("login_guest.jsp")&&!requestURL.contains("judge_guest.jsp")&&
			!requestURL.contains("userlogout")&&
			!requestURL.contains("/image")&&!requestURL.contains(".css")&&!requestURL.contains(".js")&&!requestURL.contains(".html")){
			if(user==null){
				if(vbm==null){
					if(requestURL.contains("/Login")){
						httpresponse.getWriter().print("<script>window.parent.alert('會話超時或地址無效,請重新登錄');window.location.href='judge.jsp'</script>");
					}
					if(requestURL.contains("/Login_scm")){
						httpresponse.getWriter().print("<script>window.parent.alert('會話超時或地址無效,請重新登錄');window.location.href='judge_guest.jsp'</script>");
					}
				}else{
					chain.doFilter(request, response);	
				}
			}else{
				chain.doFilter(request, response);				
			}			
		}else{
			if(requestURL.equals("/Login/")||requestURL.equals("/Login/judge.jsp")){
				httpresponse.getWriter().print("<script>window.parent.alert('網址已更改,點擊確定跳轉到新網址,請及時保存新網址');window.location.href='http://www.gj.com.tw/WebLogin'</script>");	
			}else if(requestURL.equals("/Login_scm/")||requestURL.equals("/Login_scm/judge_guest.jsp")){
				httpresponse.getWriter().print("<script>window.parent.alert('網址已更改,點擊確定跳轉到新網址,請及時保存新網址');window.location.href='http://www.gj.com.tw/WebLogin_scm'</script>");
			}else{
				chain.doFilter(request, response);
			}
			chain.doFilter(request, response);
		}*/
		
		
		String[]arr_a=url_a.split(",");
		String[]arr_b=url_b.split(",");
		if(requestURL.contains(url_c)){
			user=null;
		}
		for(int i=0;i<arr_a.length;i++){
			if(arr_a[i].equals(requestURL)){
				chain.doFilter(request,response);
				break;
			}else if(i==arr_a.length-1){
				for(int j=0;j<arr_b.length;j++){
					if(requestURL.contains(arr_b[j])){
						chain.doFilter(request,response);
						break;
					}else if(j==arr_b.length-1){
						if(user==null){
							if(vbm==null){
								if(requestURL.contains(arr_a[0])){//arr_a[0]:/Login/
									httpresponse.getWriter().print("<script>window.parent.alert('會話超時或地址無效,請重新登錄');window.location.href='judge.jsp'</script>");
								}
								if(requestURL.contains(arr_a[1])){//arr_a[1]:/Login_scm/
									httpresponse.getWriter().print("<script>window.parent.alert('會話超時或地址無效,請重新登錄');window.location.href='judge_guest.jsp'</script>");
								}
							}else{
								chain.doFilter(request,response);
							}
						}else{
							chain.doFilter(request,response);
						}
					}
				}
			}
		}
	}

	/**
	 * 日期:2016/5/11
	 * 描述:
	 */
	
	
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		url_a=config.getInitParameter("url_a");
		url_b=config.getInitParameter("url_b");
		url_c=config.getInitParameter("url_c");
	}
	

}
