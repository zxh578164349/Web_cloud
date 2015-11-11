package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import services.IKyzExpectmatmFileServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmFile;

public class KyzFileAction extends ActionSupport{
	private String billNo;
	private IKyzExpectmatmFileServices kyzexpfileSer;
	private Integer id;
	private List<KyzExpectmatmFile>listfiles;
	private String list_json;
	

	

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
		
		   
        
		//kyzexpfileSer.delete(id);
		List<KyzExpectmatmFile> listfiles=kyzexpfileSer.findByBillNo(billNo);
		/*JSONArray fileJson = new JSONArray();
		JSONObject jo = new JSONObject();
		for(int i=0;i<listfiles.size();i++){
			
		}*/
		/******************list轉json*************************/
		JSONArray fileJson=JSONArray.fromObject(listfiles);
		
		/******************list轉json*************************/
		list_json=fileJson.toString();
		System.out.println(list_json);
		return SUCCESS;

	}
	

}
