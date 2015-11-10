package action;

import java.util.List;

import net.sf.json.JSONArray;
import services.IKyzExpectmatmFileServices;

import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmFile;

public class KyzFileAction extends ActionSupport{
	private String billNo;
	private IKyzExpectmatmFileServices kyzexpfileSer;
	

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public void setKyzexpfileSer(IKyzExpectmatmFileServices kyzexpfileSer) {
		this.kyzexpfileSer = kyzexpfileSer;
	}
	
	public void findKyzFileJson(){
		List<KyzExpectmatmFile> listfiles=kyzexpfileSer.findByBillNo(billNo);
		/******************list轉json*************************/
		JSONArray fileJson=JSONArray.fromObject(listfiles);
		/******************list轉json*************************/
	}
	

}
