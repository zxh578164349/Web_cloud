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
public class SessionTimeOutFilter_guest implements Filter{

	/**
	 * 日期:2016/5/11
	 * 描述:
	 */
	
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
		 * autosendfactorder_:自動發送工廠訂單
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
		if(requestURL.contains("webfact_findAllfact")){//如果登錄后，使用webfact_findAllfact舊地址，則讓user爲null,讓地址失效   20161115
			user=null;
		}
		if(!requestURL.contains("userlogin")&&!requestURL.contains("webfact_findAllWebfact")&&!requestURL.equals("/Login/")&&!requestURL.contains("loginpage")&&
			!requestURL.contains("judge.jsp")&&!requestURL.contains("vbm_findById_email")&&!requestURL.contains("print2Ypoi_print2Y_hb")&&!requestURL.contains("autosendfactorder_")&&
			!requestURL.contains("webfactOrder_print_email")&&!requestURL.equals("/Login_scm/")&&!requestURL.contains("login_guest.jsp")&&!requestURL.contains("judge_guest.jsp")){
			if(user==null){
				if(vbm==null){
					if(requestURL.contains("webfact_findAllfact")){
						httpresponse.getWriter().print("<script>window.parent.alert('地址無效,點擊確定重新登錄');window.location.href='judge_guest.jsp'</script>");//20161115
					}else{
						httpresponse.getWriter().print("<script>window.parent.alert('會話超時,請重新登錄');window.location.href='judge_guest.jsp'</script>");
					}
					
				}else{
					chain.doFilter(request, response);	
				}
			}else{
				chain.doFilter(request, response);				
			}			
		}else{
			chain.doFilter(request, response);	
		}				
	}

	/**
	 * 日期:2016/5/11
	 * 描述:
	 */
	
	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
