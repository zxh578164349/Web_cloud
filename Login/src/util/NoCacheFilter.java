package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter implements Filter{
	private FilterConfig filterConfig=null; 

	public void destroy() {
		// TODO Auto-generated method stub
		this.filterConfig=null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse httpResponse=(HttpServletResponse)response;				 
	      httpResponse.setHeader("Cache-Control","private,no-store,no-cache,must-revalidate"); 
	      httpResponse.setDateHeader("Expires",0); 
	      httpResponse.setHeader("Pragma","No-cache"); 
	      chain.doFilter(request,response); 
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig=filterConfig;
	}

}
