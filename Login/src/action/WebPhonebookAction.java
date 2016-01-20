package action;

import services.IWebPhonebookServices;

import com.opensymphony.xwork2.ActionSupport;

import entity.WebPhonebook;

public class WebPhonebookAction extends ActionSupport{
	private IWebPhonebookServices webphonebookSer;
	private WebPhonebook phone;
	public WebPhonebook getPhone() {
		return phone;
	}
	public void setPhone(WebPhonebook phone) {
		this.phone = phone;
	}
	public void setWebphonebookSer(IWebPhonebookServices webphonebookSer) {
		this.webphonebookSer = webphonebookSer;
	}
	public String add(){
		webphonebookSer.add(phone);
		return "add";
	}

}
