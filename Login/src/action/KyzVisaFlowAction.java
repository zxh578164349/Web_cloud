package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import services.IKyzExpectmatmLogServices;
import services.IKyzVisaFlowServices;
import services.IWebTypeServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyVisabills;
import entity.KyzExpectmatmLog;
import entity.KyzVisaflow;
import entity.KyzVisaflowId;
import entity.WebType;
import entity.WebUser;

public class KyzVisaFlowAction extends ActionSupport implements ServletResponseAware{
	private IKyzVisaFlowServices visaSer;
	private List<KyzVisaflow> flows;
	private String factNo;
	private String visaSort;
	private int page;
	private PageBean bean;
	private KyzVisaflowId id;
	private String isnull;
	private String visaRank;//職位
	private String visaSigner;//Email
	private String purmanNo;//姓名
	private KyzVisaflow flow;
	private String flowmk;//判斷是否知會的
	private int maxItem;//新添加的知會人員的序列號
	private String ajaxResult;//申請函文時返回的ajax結果,   0:提交成功       1:提交失敗
	private int backIndex;//返回標識      0或null:不走返回路徑         1:走返回路徑
	private IKyzExpectmatmLogServices kyzExpLogSer;//刪除記錄
	
	public int getBackIndex() {
		return backIndex;
	}

	public void setBackIndex(int backIndex) {
		this.backIndex = backIndex;
	}

	private javax.servlet.http.HttpServletResponse response;
	

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public String getVisaRank() {
		return visaRank;
	}

	public void setVisaRank(String visaRank) {
		this.visaRank = visaRank;
	}

	public String getVisaSigner() {
		return visaSigner;
	}

	public void setVisaSigner(String visaSigner) {
		this.visaSigner = visaSigner;
	}

	public String getPurmanNo() {
		return purmanNo;
	}

	public void setPurmanNo(String purmanNo) {
		this.purmanNo = purmanNo;
	}

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public List<KyzVisaflow> getFlows() {
		return flows;
	}

	public void setFlows(List<KyzVisaflow> flows) {
		this.flows = flows;
	}

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getVisaSort() {
		return visaSort;
	}

	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean getBean() {
		return bean;
	}

	public void setBean(PageBean bean) {
		this.bean = bean;
	}

	public KyzVisaflowId getId() {
		return id;
	}

	public void setId(KyzVisaflowId id) {
		this.id = id;
	}

	public void setVisaSer(IKyzVisaFlowServices visaSer) {
		this.visaSer = visaSer;
	}
	

	public KyzVisaflow getFlow() {
		return flow;
	}

	public void setFlow(KyzVisaflow flow) {
		this.flow = flow;
	}
	
	

	public String getFlowmk() {
		return flowmk;
	}

	public void setFlowmk(String flowmk) {
		this.flowmk = flowmk;
	}
		
	public void setKyzExpLogSer(IKyzExpectmatmLogServices kyzExpLogSer) {
		this.kyzExpLogSer = kyzExpLogSer;
	}

	public int getMaxItem() {
		return maxItem;
	}

	public void setMaxItem(int maxItem) {
		this.maxItem = maxItem;
	}

	public String add_old() throws IOException{		
		String visaSort_main=flows.get(0).getId().getVisaSort();
		char first_main=visaSort_main.charAt(0);
		if(first_main=='C'){//start if
			String visaSort_sub=visaSort_main+"0";
			//選中"費用簽核"所有的子類別			
			List<String>types_str=visaSer.findVisaSort_C(flows.get(0).getId().getFactNo(),visaSort_main);
			List<Integer>types_int=new ArrayList<Integer>();
			if(types_str.size()>0){
				for(int j=0;j<types_str.size();j++){
					int temp=Integer.parseInt(types_str.get(j).substring(1));
					types_int.add(temp);
				}
				int maxNum=types_int.get(types_int.size()-1);//因為集合已按從小到大的順序排列好的，所以最後一個元素最大
				String str_max=String.valueOf(maxNum);
				if(str_max.substring(str_max.length()-1,str_max.length()).equals("9")){      										
					visaSort_sub="C"+maxNum+"0";  //如果最后一位数是9，则在后面再添加一位数（例：C19-->C190,C29-->C290）
					/*response.setContentType("text/html;charset=utf-8");
					response.getWriter().print("<script>alert('子類型已滿，不可以再添加!');history.back()</script>");
					return null;*/					
				}else{					
					visaSort_sub="C"+(maxNum+1);
				}
			}
			for(int i=0;i<flows.size();i++){
				flows.get(i).getId().setVisaSort(visaSort_sub);
				
			    String purmanNo=flows.get(i).getId().getPurmanNo().trim();
			    String visaSigner=flows.get(i).getVisaSigner().trim();
			    flows.get(i).getId().setPurmanNo(purmanNo);
			    flows.get(i).setVisaSigner(visaSigner);
			    flows.get(i).setFlowMk("Y");
				visaSer.add(flows.get(i));
			}
		}else{//end if
			for(int i=0;i<flows.size();i++){
				 String purmanNo=flows.get(i).getId().getPurmanNo().trim();
				    String visaSigner=flows.get(i).getVisaSigner().trim();
				    flows.get(i).getId().setPurmanNo(purmanNo);
				    flows.get(i).setVisaSigner(visaSigner);
				    flows.get(i).setFlowMk("Y");
					visaSer.add(flows.get(i));
			}
			
		}
		return "add";
	}
	
	public String add() throws IOException{		
		String visaSort_main=flows.get(0).getId().getVisaSort().split("__")[0];
		String visaSort_main2=flows.get(0).getId().getVisaSort().split("__")[1];
					
			try{
				if(visaSort_main2.equals("0")){//【其它類】
					String visaSort_sub=visaSort_main+"0";						
					List<String>types_str=visaSer.findVisaSort_C(flows.get(0).getId().getFactNo(),visaSort_main);
					List<Integer>types_int=new ArrayList<Integer>();
					if(types_str.size()>0){
						for(int j=0;j<types_str.size();j++){
							int temp=Integer.parseInt(types_str.get(j).substring(2));
							types_int.add(temp);
						}
						int maxNum=types_int.get(types_int.size()-1);//因為集合已按從小到大的順序排列好的，所以最後一個元素最大
						String str_max=String.valueOf(maxNum);
						if(str_max.substring(str_max.length()-1,str_max.length()).equals("9")){      											
							visaSort_sub=visaSort_main+maxNum+"0";
						}else{					
							visaSort_sub=visaSort_main+(maxNum+1);
						}				
					}
					for(int i=0;i<flows.size();i++){
						flows.get(i).getId().setVisaSort(visaSort_sub);				
					    String purmanNo=flows.get(i).getId().getPurmanNo().trim();
					    String visaSigner=flows.get(i).getVisaSigner().trim();
					    flows.get(i).getId().setPurmanNo(purmanNo);
					    flows.get(i).setVisaSigner(visaSigner);
					    flows.get(i).setFlowMk("Y");
					    flows.get(i).setVisaSortM(visaSort_main);
					    flows.get(i).setTypeMk(visaSort_main2);
						visaSer.add(flows.get(i));
					}
				}else{//【出差類】,【配方類】
					for(int i=0;i<flows.size();i++){
						flows.get(i).getId().setVisaSort(visaSort_main);
						flows.get(i).getId().setPurmanNo(flows.get(i).getId().getPurmanNo().trim());
					    flows.get(i).setVisaSigner(flows.get(i).getVisaSigner().trim());
						flows.get(i).setFlowMk("Y");
						flows.get(i).setVisaSortM(visaSort_main);
						flows.get(i).setTypeMk(visaSort_main2);
						visaSer.add(flows.get(i));
					}
				}								
				ajaxResult="0";
			}catch(Exception e){
				e.printStackTrace();
				ajaxResult="1";
			}
		return "add";
	}
	public String update(){		
		try{
			flow.setVisaSortM(flow.getId().getVisaSort().substring(0,2));
			visaSer.add(flow);
			ajaxResult="0";
		}catch(Exception e){
			e.printStackTrace();
			ajaxResult="1";
		}
		return "update";
	}
	
	public String findPageBean() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_visaSort");
		factNo = (String) ActionContext.getContext().getSession().get("factNo");
		bean = visaSer.findPageBean(20,page, factNo, visaSort);		
		this.getTypeName(bean);//从webtype获取类别名称
		return "beanList";
	}
	

	public String findPageBean2() {
		//ActionContext.getContext().getApplication().clear();
		ActionContext.getContext().getSession().remove("public_factNo");
		ActionContext.getContext().getSession().remove("public_visaSort");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			ActionContext.getContext().getSession().put("public_factNo", factNo);					
		}
		if (visaSort != null && !visaSort.equals("")) {
			ActionContext.getContext().getSession().put("public_visaSort", visaSort);
		}
		bean = visaSer.findPageBean(20,page, factNo, visaSort);
		this.getTypeName(bean);
		return "beanList1";
	}

	public String findPageBean3() {
		String result="beanList1";
		if(backIndex==1){
			result="beanList";
		}
		factNo = (String) ActionContext.getContext().getSession().get("public_factNo");				
		visaSort = (String) ActionContext.getContext().getSession().get("public_visaSort");				
		if (factNo == null || factNo.equals("") || factNo.equals("tw")){
			factNo = (String) ActionContext.getContext().getSession().get("factNo");					
		}
		bean = visaSer.findPageBean(20,page, factNo, visaSort);
		this.getTypeName(bean);
		return result;
	}
	
	
	public String findById(){
		flow=visaSer.findById(id);	
		return "findById";
	}
	
	public String delete(){	
		/**
		 * 繁體版
		 * (1)查出某個流程的全部對象
		 * (2)刪除點擊對象(PS:刪除之後，並不影響第(1)步的查詢結果)
		 * (3)在第(1)步查詢的全部對象，從點擊的對象開始，刪除下一個對象，刪除之後不影響第(1)步的查詢結果
		 * (4)在第(1)步查詢的全部對象，從點擊的對象開始，改變下一個對象的itemNo,然後添加該對象
		 *  
         * (1)简体版
         * (1)查出某个流程的全部对象
         * (2)删除点击对象(PS:删除之后，并不影响第(1)步的查询结果)
         * (3)在第(1)步查询的全部对象，从点击的对象开始，删除下一个对象，删除之后不影响第(1)步的查询结果
         * (4)在第(1)步查询的全部对象，从点击的对象开始，改变下一个对象的itemNo,然后添加该对象
		 */
		
		
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setFactNo(id.getFactNo());
		log.setObj("KyzVisaflow");
		log.setContent(id.getVisaSort()+id.getPurmanNo()+id.getItemNo());
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		List<KyzVisaflow>list=visaSer.findByType(id.getFactNo(),id.getVisaSort());	//(1)查詢	
		visaSer.delete(id,log);//（2）刪除點擊對象
		int startnum=Integer.parseInt(id.getItemNo());		
		for(int i=startnum;i<list.size();i++){
			visaSer.delete(list.get(i).getId());//(3)刪除點擊對象的下一個對象（舊itemNo）
			String itemno="";
			int num_temp=Integer.parseInt(list.get(i).getId().getItemNo());
			if((num_temp-1)<10){
				itemno="0"+(num_temp-1);
			}else{
				itemno=(num_temp-1)+"";
			}
			list.get(i).getId().setItemNo(itemno);	
			visaSer.add(list.get(i));//(4)添加點擊對象的下一個對象（新itemNo）
		}
		
		return "delete";
	}
	
	public String addflow(){
		/**
		 * 繁體版
		 * (1)查出某個流程的全部對象
		 * (2)定義新對象(要添加的對象flow)
		 * (3)循環1:為了避免在循環添加時出現覆蓋舊對象，刪除和添加要分別循環
		 * (4)循環2:在第(1)步查詢的全部對象，從點擊的對象開始，循環刪除下一個對象，刪除之後不影響第(1)步的查詢結果
		 * (5)在第(1)步查詢的全部對象，從點擊的對象開始，循環改變下一個對象的itemNo,然後添加該對象
		 * (6)添加第(2)步的新對象
		 *
		 * 简体版
		 * (1)查出某个流程的全部对象
		 * (2)定义新对象(要添加的对象flow)
		 * (3)为了避免在循环添加时出现覆盖旧对象，删除和添加要分别放在两个循环中(两个循环是一样的)
		 * (4)循环1:在第(1)步查询的全部对象，从点击的对象开始，循环删除下一个对象，删除之后不影响第(1)步的查询结果
		 * (5)循环2:在第(1)步查询的全部对象，从点击的对象开始，循环改变下一个对象的itemNo,然后添加该对象
		 * (6)添加第(2)步的新对象
		 */
		
		try{
			List<KyzVisaflow>list=visaSer.findByType(id.getFactNo(),id.getVisaSort());//(1)查詢	
			KyzVisaflowId flowid=new KyzVisaflowId();
			KyzVisaflow flow=new KyzVisaflow();//(2)定義新對象
			flowid.setFactNo(id.getFactNo());
			flowid.setVisaSort(id.getVisaSort());
			int item_num=Integer.parseInt(id.getItemNo());//傳過來的當前項次
			String item_new="";//定義新項次
			if((item_num+1)<10){
				item_new="0"+(item_num+1);
			}else{
				item_new=(item_num+1)+"";
			}
			flowid.setItemNo(item_new);
			flowid.setPurmanNo(purmanNo.trim());
			flow.setId(flowid);
			flow.setVisaRank(visaRank.trim());
			flow.setVisaSigner(visaSigner.trim());
			if(flowmk.equals("N")){
				flow.setFlowMk("N");
			}else{
				flow.setFlowMk("Y");
			}		
			    //(3)循环刪除點擊對象的下一個對象（舊itemNo）	
				for(int i=item_num;i<list.size();i++){
					visaSer.delete(list.get(i).getId());			
				}
				//(4)循环添加點擊對象的下一個對象（新itemNo）
				for(int k=item_num;k<list.size();k++){
					String item_new2="";
					int item_temp=Integer.parseInt(list.get(k).getId().getItemNo());
					if((item_temp+1)<10){
						item_new2="0"+(item_temp+1);
					}else{
						item_new2=(item_temp+1)+"";
					}
					list.get(k).getId().setItemNo(item_new2);
					visaSer.add(list.get(k));
				}
			flow.setVisaSortM(id.getVisaSort().substring(0,2));	
			visaSer.add(flow);//(5)添加新對象
			ajaxResult="0";
		}catch(Exception e){
			e.printStackTrace();
			ajaxResult="1";
		}
		return "addflow";
		
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	

	
	public void getTypeName(PageBean bean){
		List<KyzVisaflow>list=bean.getList();
		List<WebType>list_type=(List<WebType>)ActionContext.getContext().getSession().get("list_webtype");/********20151029登錄時已經記錄**************/
		for(int i=0;i<list.size();i++){//for1
			KyzVisaflow flow=list.get(i);
			String factno=flow.getId().getFactNo();
			String visaSort=flow.getId().getVisaSort();			
			String typename=visaSort;			
			//typename=webtypeSer.findTypeNameById(factno, visaSort.substring(0, 2));
			if(list_type!=null&&list_type.size()>0){
				for(int j=0;j<list_type.size();j++){//for2
					WebType type=list_type.get(j);
					if(factno.equals(type.getId().getFactNo())&&visaSort.substring(0,2).equals(type.getId().getTypeNo())){
						typename=type.getTypeName();					
						break;
					}
				}//for2
			}			
			flow.setColTemp(typename);
		}//for1	
	}
	
	public String findMaxItem(){
		flow=visaSer.findMaxFlow(factNo, visaSort);
		maxItem=Integer.parseInt(flow.getId().getItemNo())+1;//新的知會人員的序列號爲原有最大序列號+1
		if(maxItem<10){
			flow.getId().setItemNo("0"+maxItem);
		}else{
			flow.getId().setItemNo(""+maxItem);
		}
		return "findMaxItem";
	}
	
	/**
	 * 添加知会人员
	 * @return
	 */
	public String addMaxFlow(){
		try{
			for(int i=0;i<flows.size();i++){
				flows.get(i).setVisaRank("知會");
				flows.get(i).setFlowMk("N");
				flows.get(i).setVisaSortM(flows.get(i).getId().getVisaSort().substring(0,2));
				visaSer.add(flows.get(i));
			}
			ajaxResult="0";
		}catch(Exception e){
			e.printStackTrace();
			ajaxResult="1";
		}		
		return "addMaxFlow";
	}
	
	/**
	 * 如果刪除流程中第一個，那麼就刪除整個流程
	 * @return
	 */
	public String deleteFirst(){
		List<KyzVisaflow>list=visaSer.findByFactNoVisaSort(factNo, visaSort);
		for(int i=0;i<list.size();i++){
			visaSer.delete2(list.get(i));
		}
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setFactNo(factNo);
		log.setObj("KyzVisaflow");
		log.setContent(visaSort+"_del all kyzvisaflow");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		log.setUsername(user.getUsername());
		kyzExpLogSer.add(log);
		return "deleteFirst";
	}
	/**
	 * 找出是否存在出差函文流程（返回的結果>0,則存在）20160203
	 * @return
	 */
	public String findWebbuss(){
		long result=visaSer.findWebbuss(factNo);
		if(result>0){
			ajaxResult="0";
		}else{			
				ajaxResult="1";						
		}
		return "findWebbuss";
	}

}
