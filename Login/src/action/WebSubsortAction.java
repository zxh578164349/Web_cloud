package action;

import services.IWebSubsortServices;

import com.opensymphony.xwork2.ActionSupport;

public class WebSubsortAction extends ActionSupport {
	private IWebSubsortServices subSer;

	public void setSubSer(IWebSubsortServices subSer) {
		this.subSer = subSer;
	}

}
