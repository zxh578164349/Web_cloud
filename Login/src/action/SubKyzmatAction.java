package action;

import java.util.ArrayList;
import java.util.List;

import services.IKyzMatServices;
import services.ISubKyzmatServices;
import services.IWebFactServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzMat;
import entity.SubKyzmat;
import entity.SubKyzmatId;
import entity.WebUser;

/**
 * 物料資料
 * @author ky2-qhtj
 *
 */
public class SubKyzmatAction extends ActionSupport{
	private ISubKyzmatServices subkyzmatSer;
	private IWebFactServices webFactSer;
	private IKyzMatServices kyzmatSer;
	private String matNo;
	private List<String>cb_list;
	private int page;
	private String fromDate;
	private String endDate;
	private String typeBno;
	private String typeMno;
	private String typeSno;
	private String factNo;
	private PageBean bean;
	private String matCname;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMatCname() {
		return matCname;
	}
	public void setMatCname(String matCname) {
		this.matCname = matCname;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTypeBno() {
		return typeBno;
	}
	public void setTypeBno(String typeBno) {
		this.typeBno = typeBno;
	}
	public String getTypeMno() {
		return typeMno;
	}
	public void setTypeMno(String typeMno) {
		this.typeMno = typeMno;
	}
	public String getTypeSno() {
		return typeSno;
	}
	public void setTypeSno(String typeSno) {
		this.typeSno = typeSno;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	public String getMatNo() {
		return matNo;
	}
	public void setMatNo(String matNo) {
		this.matNo = matNo;
	}
	
	public List<String> getCb_list() {
		return cb_list;
	}
	public void setCb_list(List<String> cb_list) {
		this.cb_list = cb_list;
	}
	
	public void setSubkyzmatSer(ISubKyzmatServices subkyzmatSer) {
		this.subkyzmatSer = subkyzmatSer;
	}
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	
	public void setKyzmatSer(IKyzMatServices kyzmatSer) {
		this.kyzmatSer = kyzmatSer;
	}
	/**
	 * SubKyzmat
	 */
	public String addSubKyzmat(){
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");	
		if(cb_list!=null&&cb_list.size()>0){
			for(int i=0;i<cb_list.size();i++){
				String matno=cb_list.get(i);
				SubKyzmat sub=new SubKyzmat();
				SubKyzmatId id=new SubKyzmatId();
				KyzMat mat=kyzmatSer.findById(matno);
				id.setKyzMat(mat);
				id.setFactNo(user.getFactno());
				id.setUsername(user.getUsername());
				sub.setId(id);
				subkyzmatSer.add(sub);			
			}
		}		
		return "addSubKyzmat";
	}
	public String findfactnoByMatno(){
		List<String>factNos=subkyzmatSer.findByMatNo(matNo);
		List<String>facts=new ArrayList<String>();
		if(factNos.size()>0){
			for(int i=0;i<factNos.size();i++){
				String factno=factNos.get(i);
				if(!factno.equals("tw")){
					String factname=webFactSer.selByid(factno);
					String fact=factname+"("+factno+")";
					facts.add(fact);
				}else{
					facts.add(factno);
				}								
			}
		}else{
			facts.add("暫無廠別");
		}
		
		ActionContext.getContext().getSession().put("subkyzmat_facts", facts);
		return "findfactnoByMatno";
	}
	public String findPageBean(){
		ActionContext.getContext().getApplication().clear();
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		bean=subkyzmatSer.findPageBean(25, page, fromDate, endDate,matCname,typeBno,typeMno,typeSno,factNo,matNo);
		return "findPageBean";
	}
	public String findPageBean2(){
		ActionContext.getContext().getApplication().clear();
		bean=subkyzmatSer.findPageBean(25, page, fromDate, endDate,matCname.trim(),typeBno,typeMno,typeSno,factNo,matNo.trim());
		if(fromDate!=null&&!fromDate.equals("")){
			ActionContext.getContext().getApplication().put("fromdate", fromDate);
		}
		if(endDate!=null&&!endDate.equals("")){
			ActionContext.getContext().getApplication().put("enddate", endDate);
		}
		if(matNo!=null&&!matNo.equals("")){
			ActionContext.getContext().getApplication().put("matNo", matNo.trim());
		}
		if(typeBno!=null&&!typeBno.equals("")){
			ActionContext.getContext().getApplication().put("bNo", typeBno);
		}
		if(typeMno!=null&&!typeMno.equals("")){
			ActionContext.getContext().getApplication().put("mNo", typeMno);
		}
		if(typeSno!=null&&!typeSno.equals("")){
			ActionContext.getContext().getApplication().put("sNo", typeSno);
		}
		if(factNo!=null&&!factNo.equals("")){
			ActionContext.getContext().getApplication().put("factno", factNo);
		}
		if(matCname!=null&&!matCname.equals("")){
			ActionContext.getContext().getApplication().put("matcname", matCname.trim());
		}
		return "findPageBean1";
	}
	public String findPageBean3(){
		fromDate=(String)ActionContext.getContext().getApplication().get("fromdate");
		endDate=(String)ActionContext.getContext().getApplication().get("enddate");
		matNo=(String)ActionContext.getContext().getApplication().get("matNo");
		typeBno=(String)ActionContext.getContext().getApplication().get("bNo");
		typeMno=(String)ActionContext.getContext().getApplication().get("mNo");
		typeSno=(String)ActionContext.getContext().getApplication().get("sNo");
		factNo=(String)ActionContext.getContext().getApplication().get("factno");
		matCname=(String)ActionContext.getContext().getApplication().get("matcname");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		bean=subkyzmatSer.findPageBean(25, page, fromDate, endDate,matCname,typeBno,typeMno,typeSno,factNo,matNo);
		return "findPageBean1";
	}
	public String delete(){
		subkyzmatSer.delete(username, factNo, matNo);
		return "delete";
	}

}
