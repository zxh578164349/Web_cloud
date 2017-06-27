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

import com.opensymphony.xwork2.ActionContext;

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
		HttpServletResponse httpresponse=(HttpServletResponse)response;
		httpresponse.setContentType("text/html;charset=utf-8");		
		httpresponse.getWriter().print("<script>window.alert('Web系統網址已更改,點擊確定跳轉到新網址\\n請及時保存新網址:http://203.85.73.161/WebLogin\\n" +
				"不便之處請諒解!');" +
				"window.location.href='http://203.85.73.161/WebLogin'</script>");
		//chain.doFilter(request,response);
		
	}

	/**
	 * 日期:2016/5/11
	 * 描述:
	 */
	
	
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		/*url_a=config.getInitParameter("url_a");
		url_b=config.getInitParameter("url_b");
		url_c=config.getInitParameter("url_c");*/
	}
	

}
