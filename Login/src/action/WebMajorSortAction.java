package action;

import java.util.List;

import services.IWebMajorSortServices;

import com.opensymphony.xwork2.ActionSupport;

import entity.WebMajorsort;

public class WebMajorSortAction extends ActionSupport {
	private IWebMajorSortServices majorSer;
	private List<WebMajorsort> majorlist;

	public List<WebMajorsort> getMajorlist() {
		return majorlist;
	}

	public void setMajorlist(List<WebMajorsort> majorlist) {
		this.majorlist = majorlist;
	}

	public void setMajorSer(IWebMajorSortServices majorSer) {
		this.majorSer = majorSer;
	}

	public String findAll() {
		majorlist = majorSer.findAll();
		return "findAll";
	}

}
