package action;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import services.IKyzExpectmatmFileServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmLog;

public class KyzFileAction extends ActionSupport{
	private String billNo;
	private IKyzExpectmatmFileServices kyzexpfileSer;
	private Integer id;
	private List<KyzExpectmatmFile>listfiles;
	private String list_json;
	private JSONArray jsons;
	private String filename;
	private String factNo;

	
    
	public String getFactNo(){
		return factNo;
	}

	public void setFactNo(String factNo){
		this.factNo=factNo;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public JSONArray getJsons() {
		return jsons;
	}

	public void setJsons(JSONArray jsons) {
		this.jsons = jsons;
	}

	public String getList_json() {
		return list_json;
	}

	public void setList_json(String list_json) {
		this.list_json = list_json;
	}

	public List<KyzExpectmatmFile> getListfiles() {
		return listfiles;
	}

	public void setListfiles(List<KyzExpectmatmFile> listfiles) {
		this.listfiles = listfiles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public void setKyzexpfileSer(IKyzExpectmatmFileServices kyzexpfileSer) {
		this.kyzexpfileSer = kyzexpfileSer;
	}
	
	
	
	public String findKyzFileJson() throws IOException{
		filename=URLDecoder.decode(filename,"utf-8");
		File file=new File("");
		if(billNo.substring(0,2).equals("EM")){
			file=new File("d:\\KyzexpFile_backup\\"+billNo+"\\"+filename);
		}
		if(billNo.substring(0,2).equals("CM")){
			file=new File("d:\\KyzletterexpFile_backup\\"+billNo+"\\"+filename);
		}
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setBillNo(billNo);
		log.setContent(filename);
		log.setObj("KyzExpectmatmFile");
		log.setFactNo(factNo);
		kyzexpfileSer.delete(new KyzExpectmatmFile(id),log);
		if(file.exists()){
			file.delete();
			//kyzexpfileSer.delete(id);
		}
		List<KyzExpectmatmFile> listfiles=kyzexpfileSer.findByBillNo(billNo);				
		/******************list轉json*************************/
		jsons=JSONArray.fromObject(listfiles);		
		/******************list轉json*************************/
		//list_json=jsons.toString();
		//System.out.println(jsons);
		return SUCCESS;

	}
	

}
