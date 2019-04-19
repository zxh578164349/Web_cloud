package action;

import java.util.List;

import net.sf.json.JSONArray;
import services.IWebFormtypeServices;

import com.opensymphony.xwork2.ActionSupport;

import entity.WebFormtype;

public class WebFormtypeAction extends ActionSupport{
	private IWebFormtypeServices webformser;
	private String factNo;
	private String typeNo;
	private JSONArray jsons;
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public JSONArray getJsons() {
		return jsons;
	}
	public void setJsons(JSONArray jsons) {
		this.jsons = jsons;
	}
	public void setWebformser(IWebFormtypeServices webformser) {
		this.webformser = webformser;
	}
	
	public String findWebformByFactnoTypeno(){
		List<Object[]>list=webformser.findWebformByFactnoTypeno(factNo, typeNo);
		if(list!=null&&list.size()>0){
			jsons=JSONArray.fromObject(list);
		}else{
			jsons=new JSONArray();
		}		
		return "findWebformByFactnoTypeno";
	}
	
	

}
