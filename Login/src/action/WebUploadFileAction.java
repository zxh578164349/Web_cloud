package action;

import java.util.List;

import services.IWebUploadFileServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebUploadfiles;
import entity.WebUser;

public class WebUploadFileAction extends ActionSupport{
	private IWebUploadFileServices webuploadSer;
	private WebUploadfiles uploadfile;
	
	
	public WebUploadfiles getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(WebUploadfiles uploadfile) {
		this.uploadfile = uploadfile;
	}
	public void setWebuploadSer(IWebUploadFileServices webuploadSer) {
		this.webuploadSer = webuploadSer;
	}


}
