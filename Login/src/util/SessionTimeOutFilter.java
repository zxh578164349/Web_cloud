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
public class SessionTimeOutFilter implements Filter{

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
		HttpServletRequest httprequest=(HttpServletRequest)request;
		HttpServletResponse httpresponse=(HttpServletResponse)response;
		httpresponse.setContentType("text/html;charset=utf-8");
		String requestURL=httprequest.getRequestURI();
		WebUser user=(WebUser)httprequest.getSession().getAttribute("loginUser");
		KyVisabillm vbm=(KyVisabillm)httprequest.getSession().getAttribute("vbm");
		//System.out.println(requestURL);	
		if(!requestURL.contains("userlogin")&&!requestURL.contains("webfact_findAllfact")&&!requestURL.equals("/Login/")&&!requestURL.contains("loginpage")&&
			!requestURL.contains("judge.jsp")&&!requestURL.contains("vbm_findById_email")){
			if(user==null){
				if(vbm==null){
					httpresponse.getWriter().print("<script>window.parent.alert('會話超時,請重新登錄');window.location.href='judge.jsp'</script>");
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
