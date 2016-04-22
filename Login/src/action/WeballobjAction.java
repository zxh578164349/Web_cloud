/**
 * 
 */
package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.WebFact;
import entity.WebFactId;
import entity.WebUser;
import entity.Weballobj;
import entity.WeballobjId;

import services.IWebFactServices;
import services.IWeballobjServices;
import util.GlobalMethod;
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
    private String factCode;
    private String yymm;
    private String yymm2;
    private int page;
    private PageBean bean;
	private IWeballobjServices weballobjser;
	private javax.servlet.http.HttpServletResponse response;
	private IWebFactServices webFactSer;

	
	public String getFactCode() {
		return factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

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
	
	public String delete(){
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setFactNo(factNo);
		log.setFactCode(factCode);
		log.setObj("Weballobj");
		log.setYymm(yymm);
		weballobjser.delete(factNo, factCode, yymm,log);
		return "delete";
	}
	
	public void print() throws ParseException, IOException{
		HSSFWorkbook wb=new HSSFWorkbook();
		Map<String,Object>map_cs=findStyles(wb);
		HSSFCellStyle cs=(HSSFCellStyle)map_cs.get("cs");
				
		List<String>list_months=GlobalMethod.findMonths(yymm, yymm2);
		List<String>list_col=findLeftCol();
		List<Weballobj>list_objs=weballobjser.findAllobj(factNo, yymm, yymm2);
		
		System.out.println(list_objs.size());
		switch(list_objs.size()){//switch		
		case 0:
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<script>window.parent.alert('無數據');</script>");			
			break;
		default:
			Map<String,Object>map=new LinkedHashMap<String,Object>();		
			for(String month:list_months){//for
				List<Weballobj>list=new ArrayList<Weballobj>();
				for(Weballobj obj:list_objs){
					if(month.equals(obj.getId().getYymm())){
						list.add(obj);
					}				
				}			
				map.put(month, list);					
			}//for
			
			List<String>list_fcode=new ArrayList<String>();
			for(String month:map.keySet()){
				List<Weballobj>list=(List<Weballobj>)map.get(month);
				if(list.size()>0){
					for(Weballobj obj:list){
						list_fcode.add(obj.getId().getFact().getId().getFactArea());
					}
					break;
				}
			}
			
			Map<String,Object>map_all=new LinkedHashMap<String,Object>();
			for(String month:map.keySet()){
				List<List<Double>>list_a=new ArrayList<List<Double>>();		
				List<Weballobj>list=(List<Weballobj>)map.get(month);
				if (list.size() > 0) {			
					for (Weballobj obj : list) {
						List<Double> list_b = new ArrayList<Double>();
						list_b.add(obj.getObjA100());
						list_b.add(obj.getObjA101());
						list_b.add(obj.getObjA102());
						list_b.add(obj.getObjA103());
						list_b.add(obj.getObjA104());
						list_b.add(obj.getObjA105());
						list_b.add(obj.getObjA106());
						list_b.add(obj.getObjA107());
						list_b.add(obj.getObjA108());
						list_b.add(obj.getObjA109());
						list_b.add(obj.getObjA110());
						list_b.add(obj.getObjA111());
						list_b.add(obj.getObjA112());
						list_b.add(obj.getObjA113());
						list_b.add(obj.getObjA114());
						list_b.add(obj.getObjA115());
						list_b.add(obj.getObjA116());
						list_b.add(obj.getObjA117());
						list_b.add(obj.getObjA118());
						list_b.add(obj.getObjA119());
						list_b.add(obj.getObjA120());
						list_b.add(obj.getObjA121());
						list_b.add(obj.getObjA122());
						list_b.add(obj.getObjA123());
						list_b.add(obj.getObjA124());
						list_b.add(obj.getObjA125());
						list_b.add(obj.getObjA126());
						list_b.add(obj.getObjA127());
						list_b.add(obj.getObjA128());
						list_b.add(obj.getObjA129());
						list_b.add(obj.getObjA130());
						list_b.add(obj.getObjA131());
						list_b.add(obj.getObjA132());
						list_b.add(obj.getObjA133());
						list_b.add(obj.getObjA134());
						list_b.add(obj.getObjA135());
						list_b.add(obj.getObjA136());
						list_b.add(obj.getObjA137());
						list_b.add(obj.getObjA138());
						list_b.add(obj.getObjA139());
						list_b.add(obj.getObjA140());
						list_b.add(obj.getObjA141());
						list_b.add(obj.getObjA142());
						list_b.add(obj.getObjA143());
						list_b.add(obj.getObjA144());
						list_b.add(obj.getObjA145());
						list_b.add(obj.getObjA146());
						list_b.add(obj.getObjA147());
						list_b.add(obj.getObjA148());
						list_b.add(obj.getObjA149());
						list_b.add(obj.getObjA150());
						list_b.add(obj.getObjA151());
						list_b.add(obj.getObjA152());
						list_b.add(obj.getObjA153());
						list_b.add(obj.getObjA154());
						list_b.add(obj.getObjA155());
						list_b.add(obj.getObjA156());
						list_b.add(obj.getObjA157());
						list_b.add(obj.getObjA158());
						list_b.add(obj.getObjA159());
						list_b.add(obj.getObjA160());
						list_b.add(obj.getObjA161());
						list_b.add(obj.getObjA162());
						list_b.add(obj.getObjA163());
						list_b.add(obj.getObjA164());
						list_b.add(obj.getObjA165());
						list_b.add(obj.getObjA166());
						list_b.add(obj.getObjA167());
						list_b.add(obj.getObjA168());
						list_b.add(obj.getObjA169());
						list_b.add(obj.getObjA170());
						list_b.add(obj.getObjA171());
						list_b.add(obj.getObjA172());
						list_b.add(obj.getObjA173());
						list_b.add(obj.getObjA174());
						list_b.add(obj.getObjA175());
						list_b.add(obj.getObjA176());
						list_b.add(obj.getObjA177());
						list_b.add(obj.getObjA178());
						list_b.add(obj.getObjA179());
						list_b.add(obj.getObjA180());
						list_b.add(obj.getObjA181());
						list_b.add(obj.getObjA182());
						list_b.add(obj.getObjA183());
						list_b.add(obj.getObjA184());
						list_b.add(obj.getObjA185());
						list_b.add(obj.getObjA186());
						list_b.add(obj.getObjA187());
						list_b.add(obj.getObjA188());
						list_b.add(obj.getObjA189());
						list_b.add(obj.getObjA190());
						list_b.add(obj.getObjA191());
						list_b.add(obj.getObjA192());
						list_b.add(obj.getObjA193());
						list_b.add(obj.getObjA194());
						list_b.add(obj.getObjA195());
						list_b.add(obj.getObjA196());
						list_b.add(obj.getObjA197());
						list_b.add(obj.getObjA198());
						list_b.add(obj.getObjA199());
						list_b.add(obj.getObjA200());
						list_b.add(obj.getObjA201());
						list_b.add(obj.getObjA202());
						list_a.add(list_b);
					}				
				}
				map_all.put(month, list_a);
			}
			init(wb,map_cs,list_col,factNo,yymm,yymm2,list_months,list_fcode);	
			for(String month:map_all.keySet()){
				HSSFSheet sheet=wb.getSheet(month);
				List<List<Double>>list_a=(List<List<Double>>)map_all.get(month);
				if(list_a.size()>0){
					for(int a=0;a<list_a.size();a++){
						List<Double>list_b=list_a.get(a);
						for(int b=0;b<list_b.size();b++){
							sheet.getRow(3+b).getCell(3+a).setCellValue(list_b.get(b));
							sheet.getRow(3+b).getCell(3+a).setCellStyle(cs);
						}
					}
				}else{
					for(int a=0;a<list_fcode.size();a++){
						for(int b=1;b<list_col.size();b++){
							sheet.getRow(2+b).getCell(3+a).setCellValue("無數據");
							sheet.getRow(2+b).getCell(3+a).setCellStyle(cs);
						}
					}
				}								
			}
			try {
				/*OutputStream os = new FileOutputStream("E:/" + "websort.xls");
				wb.write(os);
				os.close();	*/
				ServletOutputStream os=response.getOutputStream();
				response.setContentType("application/vnd.ms-excel");
				int msie=ServletActionContext.getRequest().getHeader("USER-AGENT").toLowerCase().indexOf("msie");//判斷是否為IE瀏覽器,大於0則為IE瀏覽器
				String fileName="report"+yymm+"-"+yymm2+".xls";
				if(msie>0){
					fileName=java.net.URLEncoder.encode(fileName,"utf-8");//解決IE中文文件不能下載的問題
				}else{
					fileName=new String(fileName.getBytes("utf-8"),"iso-8859-1");//解決非IE中文名亂碼問題
				}		
				response.setHeader("Content-disposition", "attachment;filename="+fileName);					
				wb.write(os);
				os.close();						
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}//switch							
	}
	
	
	public List<String> findLeftCol(){
		List<String>list=new ArrayList<String>();
		list.add("序號__項目__單位");
		list.add("100__銷貨總收入__USD");
		list.add("101__請款雙數__雙");
		list.add("102__雙數請款金額__USD");
		list.add("103__客補請款雙數__雙");
		list.add("104__客補請款金額__USD");
		list.add("105__樣品請款雙數__雙");
		list.add("106__樣品請款金額__USD");
		list.add("107__應收帳款過期金額__USD");
		list.add("108__客戶扣款金額__USD");
		list.add("109__銷售成本金額__USD");
		list.add("110__當月生產成本金額__USD");
		list.add("111__生產產值__USD");
		list.add("112__主材料成本__USD");
		list.add("113__色料藥品成本__USD");
		list.add("114__不良品(損耗)成本__USD");
		list.add("115__當月實際耗料__KG");
		list.add("116__當月生產膠底總毛重__KG");
		list.add("117__當月生產膠底總淨重__KG");
		list.add("118__非膠底產品耗用重量__KG");
		list.add("119__直工發薪人數__人");
		list.add("120__間工發薪人數__人");
		list.add("121__直工正班工時__小時");
		list.add("122__間工正班工時__小時");
		list.add("123__直工加班工時__小時");
		list.add("124__間工加班工時__小時");
		list.add("125__直工離職人數__人");
		list.add("126__間工離職人數__人");
		list.add("127__工傷件數__件");
		list.add("128__直接應領薪資__USD");
		list.add("129__間接應領薪資__USD");
		list.add("130__直工加班費__USD");
		list.add("131__間工加班費__USD");
		list.add("132__獎金金額__USD");
		list.add("133__其加金額__USD");
		list.add("134__費用合計__USD");
		list.add("135__其它收入__USD");
		list.add("136__汽車加油費__USD");
		list.add("137__快遞費__USD");
		list.add("138__雜項購置__USD");
		list.add("139__雜項支出-其他__USD");
		list.add("140__電腦耗材__USD");
		list.add("141__文具用品類__USD");
		list.add("142__修繕類-機器設備__USD");
		list.add("143__修繕費-其它類__USD");
		list.add("144__車輛維修費__USD");
		list.add("145__服裝費__USD");
		list.add("146__清潔/消毒費__USD");
		list.add("147__工程整改費__USD");
		list.add("148__工傷費用__USD");
		list.add("149__模具修理費用__USD");
		list.add("150__差旅費__USD");
		list.add("151__交際費__USD");
		list.add("152__包裝費用__USD");
		list.add("153__用水量__噸");
		list.add("154__用水金額__USD");
		list.add("155__用電量__度");
		list.add("156__用電金額__USD");
		list.add("157__用蒸汽量__噸");
		list.add("158__用蒸汽金額__USD");
		list.add("159__用祡油量__噸");
		list.add("160__用柴油金額__USD");
		list.add("161__邊料重量__KG");
		list.add("162__邊料金額__USD");
		list.add("163__不良雙數__雙");
		list.add("164__不良雙數折算金額__USD");
		list.add("165__不良重量__KG");
		list.add("166__不良重量金額__USD");
		list.add("167__其它報廢重量__KG");
		list.add("168__其它報廢折算金額__USD");
		list.add("169__成倉庫存__雙");
		list.add("170__無訂單庫存__雙");
		list.add("171__整理庫存__雙");
		list.add("172__已出未請雙數__雙");
		list.add("173__已請未出雙數__雙");
		list.add("174__前倉入庫折算可請款雙數__雙");
		list.add("175__生產與請款差異__雙");
		list.add("176__退貨數__雙");
		list.add("177__總原料庫存量__KG");
		list.add("178__總原料庫存金額__USD");
		list.add("179__膠類庫存量__KG");
		list.add("180__膠類庫存金額__USD");
		list.add("181__呆料庫存量__KG");
		list.add("182__呆料庫存金額__USD");
		list.add("183__色料用量__KG");
		list.add("184__色料金額__USD");
		list.add("185__藥品用量__KG");
		list.add("186__藥品金額__USD");
		list.add("187__離型劑用量__KG");
		list.add("188__離型劑用量金額__USD");
		list.add("189__防霜劑用量__KG");
		list.add("190__防霜劑金額__USD");
		list.add("191__防粘劑用量__KG");
		list.add("192__防粘劑金額__USD");
		list.add("193__油漆處理劑用量__KG");
		list.add("194__油漆處理劑金額__USD");
		list.add("195__白色回收粉__KG");
		list.add("196__黑色回收粉__KG");
		list.add("197__生膠回收粉__KG");
		list.add("198__灰色回收粉__KG");
		list.add("199__其它回收粉__KG");
		list.add("200__粗坯用量 __KG");
		list.add("201__裁斷回頭料__KG");
		list.add("202__油壓退料__KG");
		return list;
	}
	
	public void init(HSSFWorkbook wb,Map<String,Object>map_cs,List<String>list_col,String factNo,String yymm,String yymm2,List<String>list_months,List<String>list_fcode) throws ParseException{
		HSSFCellStyle cs=(HSSFCellStyle)map_cs.get("cs");
		HSSFCellStyle cs_head=(HSSFCellStyle)map_cs.get("cs_head");
		HSSFCellStyle cs_column=(HSSFCellStyle)map_cs.get("cs_column");			
		for(String month:list_months){//for
			HSSFSheet sheet=wb.createSheet(month);
			sheet.setColumnWidth(1, 5000);
			for(int a=0;a<2+list_col.size();a++){
				sheet.createRow(a);				
				for(int b=0;b<10;b++){
					sheet.getRow(a).createCell(b);					
				}
			}
			
			CellRangeAddress cra_title=new CellRangeAddress(0,0,0,4);
			sheet.addMergedRegion(cra_title);
			sheet.getRow(0).getCell(0).setCellValue(month+"_"+factNo+"基本數據");
			for(int i=0;i<6;i++){
				sheet.getRow(0).getCell(i).setCellStyle(cs_head);
			}
			for(int a=0;a<2+list_col.size();a++){
				if(a<list_col.size()){
				for(int b=0;b<list_col.get(0).split("__").length;b++){					
						sheet.getRow(a+2).getCell(b).setCellValue(list_col.get(a).split("__")[b]);
						if(a==0){
							sheet.getRow(a+2).getCell(b).setCellStyle(cs_column);
							if(b==list_col.get(0).split("__").length-1){
								for(int c=0;c<list_fcode.size();c++){
									sheet.getRow(a+2).getCell(b+1+c).setCellValue(list_fcode.get(c));
									sheet.getRow(a+2).getCell(b+1+c).setCellStyle(cs_column);
								}
							}
						}else{
							sheet.getRow(a+2).getCell(b).setCellStyle(cs);
						}						
					}
				}
			}
		}//for
	}
	
	
	public Map<String,Object> findStyles(HSSFWorkbook wb){
		/**
		 * 報表相關樣式
		 */
		Map<String,Object>map=new HashMap<String,Object>();
		//標題樣式
		HSSFCellStyle cs_head=wb.createCellStyle();
		HSSFFont font_head=wb.createFont();
		font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_head.setFontHeightInPoints((short)20);
		cs_head.setFont(font_head);
		cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_head.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		map.put("cs_head", cs_head);
		
		//標準單元格樣式
		HSSFCellStyle cs=wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs", cs);
		//表頭樣式
		HSSFCellStyle cs_column=wb.createCellStyle();
		HSSFFont font_column=wb.createFont();
		font_column.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font_column.setFontHeightInPoints((short)12);
		cs_column.setFont(font_column);
		cs_column.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_column.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_column.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_column.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_column.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs_column.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		map.put("cs_column", cs_column);
		
		
		
		//紅色加粗字體
		HSSFFont font_red=wb.createFont();
		font_red.setColor(IndexedColors.RED.getIndex());
		font_red.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		/**********************分類+項目+單位*****************************/
		HSSFFont font_bold = wb.createFont();
		font_bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//font_bold.setFontHeightInPoints((short) 10);
		// 藍色背景粗字體
		HSSFCellStyle cs_blue = wb.createCellStyle();
		cs_blue.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_blue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_blue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_blue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cs_blue.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		cs_blue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cs_blue.setFont(font_bold);
		map.put("cs_blue", cs_blue);
		// 標準粗字體樣式
		HSSFCellStyle cs_bold = wb.createCellStyle();
		cs_bold.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_bold.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_bold.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_bold.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_bold.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_bold.setBorderLeft(HSSFCellStyle.BORDER_THIN);		
		cs_bold.setFont(font_bold);
		map.put("cs_bold", cs_bold);
		
		//紅色粗字體樣式
		HSSFCellStyle cs_red=wb.createCellStyle();
		cs_red.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_red.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		
		cs_red.setFont(font_red);
		map.put("cs_red", cs_red);
		/**********************分類+項目+單位*****************************/
		
		/**
		 * 數字格式（有背景顏色與無背景顏色）
		 */
		
		HSSFDataFormat format=wb.createDataFormat();
		//無背景
		HSSFCellStyle cs_percent=wb.createCellStyle();
		cs_percent.setDataFormat(format.getFormat("0.00%"));
		cs_percent.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_percent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_percent.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_percent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_percent", cs_percent);
		
		HSSFCellStyle cs_poi=wb.createCellStyle();
		cs_poi.setDataFormat(format.getFormat("#,###,0"));
		cs_poi.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi", cs_poi);
		
		HSSFCellStyle cs_poi1=wb.createCellStyle();
		cs_poi1.setDataFormat(format.getFormat("#,###,0.0"));
		cs_poi1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi1", cs_poi1);
		
		HSSFCellStyle cs_poi2=wb.createCellStyle();
		cs_poi2.setDataFormat(format.getFormat("#,###,0.00"));
		cs_poi2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi2", cs_poi2);
		
		HSSFCellStyle cs_poi4=wb.createCellStyle();
		cs_poi4.setDataFormat(format.getFormat("#,###,0.0000"));
		cs_poi4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cs_poi4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cs_poi4.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cs_poi4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		map.put("cs_poi4", cs_poi4);
		
		return map;
	}

	
	
	
	
	

}
