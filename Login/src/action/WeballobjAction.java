/**
 * 
 */
package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebFact;
import entity.WebFactId;
import entity.WebUser;
import entity.Weballobj;
import entity.WeballobjId;

import services.IWebFactServices;
import services.IWeballobjServices;
import util.ImportExcel;
import util.PageBean;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WeballobjAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/3/24 下午1:12:52   
 * 修改人：Administrator   
 * 修改时间：2016/3/24 下午1:12:52   
 * 修改备注：   
 * @version    
 *    
 **/
public class WeballobjAction  extends ActionSupport implements ServletResponseAware{
	private File file;
    private String fileFileName;
    private String fileContentType;
    private String ajaxResult;
    private String factNo;
    private String yymm;
    private String yymm2;
    private int page;
    private PageBean bean;
	private IWeballobjServices weballobjser;
	private javax.servlet.http.HttpServletResponse response;
	private IWebFactServices webFactSer;

	
	public PageBean getBean() {
		return bean;
	}

	public void setBean(PageBean bean) {
		this.bean = bean;
	}

	public String getYymm2() {
		return yymm2;
	}

	public void setYymm2(String yymm2) {
		this.yymm2 = yymm2;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public void setWeballobjser(IWeballobjServices weballobjser) {
		this.weballobjser = weballobjser;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
			
	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}

	public void addMore() throws IOException{
		response.setContentType("text/html;charset=utf-8");
		try{
			String path="d:\\Weballobj_backup\\"+new SimpleDateFormat("yyyyMMdd").format(new Date());//Excel文檔存放目錄
			ajaxResult="0";				
			/*文件上傳*/
			if(file!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
				//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
				File uploadFile_backup=new File(path);//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)			
				if(!uploadFile_backup.exists()){
					uploadFile_backup.mkdirs();
				}																						
						FileInputStream in=new FileInputStream(file);
						FileOutputStream out_backup=new FileOutputStream(uploadFile_backup+"\\"+fileFileName);//備份
						byte[]b=new byte[1024];
						int length=0;
						while((length=in.read(b))>0){
							out_backup.write(b,0,length);//備份
						}																																				
			}
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");			
			//file=new File("e:\\導入格式2.xls");
			Map<String,Object>map=ImportExcel.exportListFromFile(new File(path+"\\"+fileFileName));
			List<String>list_factArea=webFactSer.findfactAreaByFactNo(factNo);
			for(String key:map.keySet()){//for a
				List<Weballobj>list_b=new ArrayList<Weballobj>();
				List<String>list_factcode=new ArrayList<String>();//導入數據所有的factcode
				List<String>list=(List<String>)map.get(key);
				if(!list.get(0).contains("__序號__項目__單位")){				
					response.getWriter().print("<script>window.parent.showDiv();window.parent.layer.msg('表格式不符合要求')</script>");
					//continue;
					break;
				}
				String[] array_head =list.get(0).split("__");
				for(int i=4;i<array_head.length;i++){
					list_factcode.add(array_head[i].trim());
				}
				if(!list_factArea.containsAll(list_factcode)){
					StringBuilder sb=new StringBuilder();
					sb.append("(");					
					for(String factArea:list_factArea){
						sb.append(factArea+" ");
					}
					sb.append(")");
					response.getWriter().print("<script>window.parent.layer.alert('請核對正確的廠別狀態:"+sb.toString()+"',8)</script>");
					break;
				}
				for(int i=4;i<array_head.length;i++){//for b
					WebFact fact=new WebFact(new WebFactId(factNo,array_head[i].trim()));
					Weballobj obj=new Weballobj(new WeballobjId(fact,yymm));								
					obj.setObjA100(Double.valueOf(list.get(1).split("__")[i]));
					obj.setObjA101(Double.valueOf(list.get(2).split("__")[i]));
					obj.setObjA102(Double.valueOf(list.get(3).split("__")[i]));
					obj.setObjA103(Double.valueOf(list.get(4).split("__")[i]));
					obj.setObjA104(Double.valueOf(list.get(5).split("__")[i]));
					obj.setObjA105(Double.valueOf(list.get(6).split("__")[i]));
					obj.setObjA106(Double.valueOf(list.get(7).split("__")[i]));
					obj.setObjA107(Double.valueOf(list.get(8).split("__")[i]));
					obj.setObjA108(Double.valueOf(list.get(9).split("__")[i]));
					obj.setObjA109(Double.valueOf(list.get(10).split("__")[i]));
					obj.setObjA110(Double.valueOf(list.get(11).split("__")[i]));
					obj.setObjA111(Double.valueOf(list.get(12).split("__")[i]));
					obj.setObjA112(Double.valueOf(list.get(13).split("__")[i]));
					obj.setObjA113(Double.valueOf(list.get(14).split("__")[i]));
					obj.setObjA114(Double.valueOf(list.get(15).split("__")[i]));
					obj.setObjA115(Double.valueOf(list.get(16).split("__")[i]));
					obj.setObjA116(Double.valueOf(list.get(17).split("__")[i]));
					obj.setObjA117(Double.valueOf(list.get(18).split("__")[i]));
					obj.setObjA118(Double.valueOf(list.get(19).split("__")[i]));
					obj.setObjA119(Double.valueOf(list.get(20).split("__")[i]));
					obj.setObjA120(Double.valueOf(list.get(21).split("__")[i]));
					obj.setObjA121(Double.valueOf(list.get(22).split("__")[i]));
					obj.setObjA122(Double.valueOf(list.get(23).split("__")[i]));
					obj.setObjA123(Double.valueOf(list.get(24).split("__")[i]));
					obj.setObjA124(Double.valueOf(list.get(25).split("__")[i]));
					obj.setObjA125(Double.valueOf(list.get(26).split("__")[i]));
					obj.setObjA126(Double.valueOf(list.get(27).split("__")[i]));
					obj.setObjA127(Double.valueOf(list.get(28).split("__")[i]));
					obj.setObjA128(Double.valueOf(list.get(29).split("__")[i]));
					obj.setObjA129(Double.valueOf(list.get(30).split("__")[i]));
					obj.setObjA130(Double.valueOf(list.get(31).split("__")[i]));
					obj.setObjA131(Double.valueOf(list.get(32).split("__")[i]));
					obj.setObjA132(Double.valueOf(list.get(33).split("__")[i]));
					obj.setObjA133(Double.valueOf(list.get(34).split("__")[i]));
					obj.setObjA134(Double.valueOf(list.get(35).split("__")[i]));
					obj.setObjA135(Double.valueOf(list.get(36).split("__")[i]));
					obj.setObjA136(Double.valueOf(list.get(37).split("__")[i]));
					obj.setObjA137(Double.valueOf(list.get(38).split("__")[i]));
					obj.setObjA138(Double.valueOf(list.get(39).split("__")[i]));
					obj.setObjA139(Double.valueOf(list.get(40).split("__")[i]));
					obj.setObjA140(Double.valueOf(list.get(41).split("__")[i]));
					obj.setObjA141(Double.valueOf(list.get(42).split("__")[i]));
					obj.setObjA142(Double.valueOf(list.get(43).split("__")[i]));
					obj.setObjA143(Double.valueOf(list.get(44).split("__")[i]));
					obj.setObjA144(Double.valueOf(list.get(45).split("__")[i]));
					obj.setObjA145(Double.valueOf(list.get(46).split("__")[i]));
					obj.setObjA146(Double.valueOf(list.get(47).split("__")[i]));
					obj.setObjA147(Double.valueOf(list.get(48).split("__")[i]));
					obj.setObjA148(Double.valueOf(list.get(49).split("__")[i]));
					obj.setObjA149(Double.valueOf(list.get(50).split("__")[i]));
					obj.setObjA150(Double.valueOf(list.get(51).split("__")[i]));
					obj.setObjA151(Double.valueOf(list.get(52).split("__")[i]));
					obj.setObjA152(Double.valueOf(list.get(53).split("__")[i]));
					obj.setObjA153(Double.valueOf(list.get(54).split("__")[i]));
					obj.setObjA154(Double.valueOf(list.get(55).split("__")[i]));
					obj.setObjA155(Double.valueOf(list.get(56).split("__")[i]));
					obj.setObjA156(Double.valueOf(list.get(57).split("__")[i]));
					obj.setObjA157(Double.valueOf(list.get(58).split("__")[i]));
					obj.setObjA158(Double.valueOf(list.get(59).split("__")[i]));
					obj.setObjA159(Double.valueOf(list.get(60).split("__")[i]));
					obj.setObjA160(Double.valueOf(list.get(61).split("__")[i]));
					obj.setObjA161(Double.valueOf(list.get(62).split("__")[i]));
					obj.setObjA162(Double.valueOf(list.get(63).split("__")[i]));
					obj.setObjA163(Double.valueOf(list.get(64).split("__")[i]));
					obj.setObjA164(Double.valueOf(list.get(65).split("__")[i]));
					obj.setObjA165(Double.valueOf(list.get(66).split("__")[i]));
					obj.setObjA166(Double.valueOf(list.get(67).split("__")[i]));
					obj.setObjA167(Double.valueOf(list.get(68).split("__")[i]));
					obj.setObjA168(Double.valueOf(list.get(69).split("__")[i]));
					obj.setObjA169(Double.valueOf(list.get(70).split("__")[i]));
					obj.setObjA170(Double.valueOf(list.get(71).split("__")[i]));
					obj.setObjA171(Double.valueOf(list.get(72).split("__")[i]));
					obj.setObjA172(Double.valueOf(list.get(73).split("__")[i]));
					obj.setObjA173(Double.valueOf(list.get(74).split("__")[i]));
					obj.setObjA174(Double.valueOf(list.get(75).split("__")[i]));
					obj.setObjA175(Double.valueOf(list.get(76).split("__")[i]));
					obj.setObjA176(Double.valueOf(list.get(77).split("__")[i]));
					obj.setObjA177(Double.valueOf(list.get(78).split("__")[i]));
					obj.setObjA178(Double.valueOf(list.get(79).split("__")[i]));
					obj.setObjA179(Double.valueOf(list.get(80).split("__")[i]));
					obj.setObjA180(Double.valueOf(list.get(81).split("__")[i]));
					obj.setObjA181(Double.valueOf(list.get(82).split("__")[i]));
					obj.setObjA182(Double.valueOf(list.get(83).split("__")[i]));
					obj.setObjA183(Double.valueOf(list.get(84).split("__")[i]));
					obj.setObjA184(Double.valueOf(list.get(85).split("__")[i]));
					obj.setObjA185(Double.valueOf(list.get(86).split("__")[i]));
					obj.setObjA186(Double.valueOf(list.get(87).split("__")[i]));
					obj.setObjA187(Double.valueOf(list.get(88).split("__")[i]));
					obj.setObjA188(Double.valueOf(list.get(89).split("__")[i]));
					obj.setObjA189(Double.valueOf(list.get(90).split("__")[i]));
					obj.setObjA190(Double.valueOf(list.get(91).split("__")[i]));
					obj.setObjA191(Double.valueOf(list.get(92).split("__")[i]));
					obj.setObjA192(Double.valueOf(list.get(93).split("__")[i]));
					obj.setObjA193(Double.valueOf(list.get(94).split("__")[i]));
					obj.setObjA194(Double.valueOf(list.get(95).split("__")[i]));
					obj.setObjA195(Double.valueOf(list.get(96).split("__")[i]));
					obj.setObjA196(Double.valueOf(list.get(97).split("__")[i]));
					obj.setObjA197(Double.valueOf(list.get(98).split("__")[i]));
					obj.setObjA198(Double.valueOf(list.get(99).split("__")[i]));
					obj.setObjA199(Double.valueOf(list.get(100).split("__")[i]));
					obj.setObjA200(Double.valueOf(list.get(101).split("__")[i]));
					obj.setObjA201(Double.valueOf(list.get(102).split("__")[i]));
					obj.setObjA202(Double.valueOf(list.get(103).split("__")[i]));
		
					obj.setUsername(user.getUsername());
					obj.setCreateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
					list_b.add(obj);
				}//for b
				weballobjser.addMore(list_b);
				response.getWriter().print("<script>window.parent.layer.msg('導入成功',3,1)</script>");
			}//for a
						
		}catch(Exception e){
			System.out.println(e);
			response.getWriter().print("<script>window.parent.layer.msg('導入錯誤',3,3);</script>");
		}
		
	}
	
	public String findPageBean(){
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().remove("public_yymm");
		ActionContext.getContext().getSession().remove("public_yymm2");
		factNo=(String)ActionContext.getContext().getSession().get("factNo");
		ActionContext.getContext().getSession().put("public_factno", factNo);
		bean=weballobjser.findPageBean(25, page, factNo, yymm, yymm2);
		return "beanList";
		
	}
	public String findPageBean2(){
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		ActionContext.getContext().getSession().remove("allrow");//dao層
		ActionContext.getContext().getSession().put("public_factno", factNo);
		ActionContext.getContext().getSession().put("public_yymm", yymm);
		ActionContext.getContext().getSession().put("public_yymm2", yymm2);		
		bean=weballobjser.findPageBean(25, page, factNo, yymm, yymm2);
		return "beanList1";
	}
	public String findPageBean3(){
		factNo=(String)ActionContext.getContext().getSession().get("public_factno");
		yymm=(String)ActionContext.getContext().getSession().get("public_yymm");
		yymm2=(String)ActionContext.getContext().getSession().put("public_yymm2", yymm2);
		bean=weballobjser.findPageBean(25, page, factNo, yymm, yymm2);
		return "beanList1";
	}

	
	
	
	
	

}
