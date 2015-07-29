package action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IWebFixedLogServices;
import services.IWebFixedServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebFixed;
import entity.WebFixedLog;

public class WebFixedLogAction extends ActionSupport implements
		ServletResponseAware {
	private IWebFixedLogServices fixlogSer;
	private IWebFixedServices fixSer;
	private WebFixedLog log;
	private String fixedassetsId;
	private WebFixed fix;
	private String factNo;
	private String yymm;
	private javax.servlet.http.HttpServletResponse response;

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public WebFixed getFix() {
		return fix;
	}

	public void setFix(WebFixed fix) {
		this.fix = fix;
	}

	public WebFixedLog getLog() {
		return log;
	}

	public void setLog(WebFixedLog log) {
		this.log = log;
	}

	public String getFixedassetsId() {
		return fixedassetsId;
	}

	public void setFixedassetsId(String fixedassetsId) {
		this.fixedassetsId = fixedassetsId;
	}

	public void setFixlogSer(IWebFixedLogServices fixlogSer) {
		this.fixlogSer = fixlogSer;
	}

	public void setFixSer(IWebFixedServices fixSer) {
		this.fixSer = fixSer;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public String add() {
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		List<WebFixed> list = fixSer.findByFactNo(factNo, yymm,null,null);
		String result = null;
		fix = fixSer.findById(fixedassetsId);
		fix.setAddTime(log.getChangedate());
		fix.setFactNo(log.getFactnoTo());
		fix.setFactcode(log.getFactcodeTo());
		fix.setAssetname(log.getAssetname());
		log.setLogtime(new Date());
		for (int i = 0; i < list.size(); i++) {
			if (log.getFixedId().equals(list.get(i).getFixedId())) {
				break;
			} else if (i == list.size() - 1) {
				fix.setFixedId(log.getFixedId());
				fixSer.addWebFixed(fix);
				fixlogSer.add(log);
				result = "add";
			}
		}
		if (result == null) {
			String temp = log.getFixedId();
			response.setContentType("text/html;charset=utf-8");
			try {
				response.getWriter()
						.print("<script>alert('數據庫已存在'"
								+ temp
								+ ",請重新調撥!);window.location.href='saveAndUpdate/webfixedlogSaveOrUpdate.jsp'</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*
		 * if(log.getFixedId()==null){ fix.setFixedId(null); }else{
		 * fix.setFixedId(log.getFixedId()); }
		 */

		/*
		 * fixSer.addWebFixed(fix); fixlogSer.add(log);
		 */
		return result;

	}

}
