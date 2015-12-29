package action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import services.IWebFactorderServices;
import util.ImportExcel;

import com.opensymphony.xwork2.ActionSupport;

import entity.WebFactorder;

public class WebFactOrderAction extends ActionSupport{
	private IWebFactorderServices webfactorderSer;
	private WebFactorder factorder;
	public WebFactorder getFactorder() {
		return factorder;
	}
	public void setFactorder(WebFactorder factorder) {
		this.factorder = factorder;
	}
	public void setWebfactorderSer(IWebFactorderServices webfactorderSer) {
		this.webfactorderSer = webfactorderSer;
	}
	public String add() throws IOException{
		String path="d:\\北越&鞋塑2015接單匯總-1201.xls";
		List<String>list_all=ImportExcel.exportListFromExcel(new File(path), 1);
		webfactorderSer.addLarge(list_all);
		return null;
	}

}