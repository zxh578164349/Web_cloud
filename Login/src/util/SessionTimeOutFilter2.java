/**
 * 
 */
package util;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
 * 项目名称：請求超時監聽器 
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
public class SessionTimeOutFilter2 implements Filter{

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
	
	
	public void doFilter(final ServletRequest request, final ServletResponse response,
			final FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub		
		Long d1=new Date().getTime();
		Long d2=null;
		HttpServletRequest httprequest=(HttpServletRequest)request;
		HttpServletResponse httpresponse=(HttpServletResponse)response;
		httpresponse.setContentType("text/html;charset=utf-8");
		String requestURL=httprequest.getRequestURI();		
		System.out.println(requestURL);	
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<Boolean> future=threadPool.submit(new Callable<Boolean>(){
			public Boolean call() throws Exception{
				// TODO Auto-generated method stub
				//Thread.sleep(6000);
				chain.doFilter(request, response);				
				return Boolean.TRUE;
			}			
		});	
		try {
			
			future.get(120,TimeUnit.SECONDS);	
			d2=new Date().getTime();
			System.out.println((d2-d1)/1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			response.getWriter().print("<script>window.parent.alert('請求超時');</script>");
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			response.getWriter().print("<script>window.parent.alert('請求超時');</script>");
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			response.getWriter().print("<script>window.parent.alert('請求超時');</script>");
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
