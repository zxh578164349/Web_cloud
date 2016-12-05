package action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import net.sf.json.JSONArray;
import services.IWebTabpomfileServices;

import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmLog;
import entity.WebTabpomfile;

public class WebTabpomfileAction extends ActionSupport{
	private IWebTabpomfileServices tabpomfileSer;
	private String filename;
	private String pomNo;
	private JSONArray jsons;
	

	public JSONArray getJsons() {
		return jsons;
	}
	public void setJsons(JSONArray jsons) {
		this.jsons = jsons;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getPomNo() {
		return pomNo;
	}
	public void setPomNo(String pomNo) {
		this.pomNo = pomNo;
	}
	public void setTabpomfileSer(IWebTabpomfileServices tabpomfileSer) {
		this.tabpomfileSer = tabpomfileSer;
	}
	public String findwebtabpomFileJson() throws IOException{
		filename=URLDecoder.decode(filename,"utf-8");
		File file=new File("d:\\WebtabpomFile_backup\\"+pomNo+"\\"+filename);
		        
		if(file.exists()){
			file.delete();
			KyzExpectmatmLog log=new KyzExpectmatmLog();
			log.setBillNo(pomNo);
			log.setObj("WebTabpomfile");
			log.setContent(filename);
			tabpomfileSer.delete(pomNo,filename,log);
		}
		List<Object[]> listfiles=tabpomfileSer.findByPomNo(pomNo);				
		/******************list轉json*************************/
		jsons=JSONArray.fromObject(listfiles);		
		/******************list轉json*************************/
		//list_json=jsons.toString();
		//System.out.println(jsons);
		return "findwebtabpomFileJson";

	}
	
	

}
