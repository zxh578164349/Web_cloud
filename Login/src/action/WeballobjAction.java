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

import entity.WebFact;
import entity.WebFactId;
import entity.Weballobj;
import entity.WeballobjId;

import services.IWeballobjServices;
import util.ImportExcel;

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
public class WeballobjAction {
	private File file;
    private String fileFileName;
    private String fileContentType;
    private String ajaxResult;
    private String factNo;
    private String yymm;
	private IWeballobjServices weballobjser;
	
	

	
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
	
	public void addMore() throws IOException{
		String path="d:\\Weballobj_backup\\"+new SimpleDateFormat("yyyyMMdd").format(new Date());//Excel文檔存放目錄
		ajaxResult="0";				
		/*文件上傳*/
		if(file!=null){//不為空代表有上傳附檔,不能寫成files.size()>0,否則報空指針
			//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("KyzexpFile\\"+kyz.getId().getBillNo()));//附檔上傳到項目
			File uploadFile_backup=new File(path);//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)
			/*if(!uploadFile.exists()){
				uploadFile.mkdirs();
			}*/
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
		factNo="631";
		yymm="201601";
		file=new File("e:\\導入格式.xls");
		Map<String,Object>map=ImportExcel.exportListFromFile(file);
		List<List<Weballobj>>list_a=new ArrayList<List<Weballobj>>();
		for(String key:map.keySet()){//for a
			List<Weballobj>list_b=new ArrayList<Weballobj>();
			List<String>list=(List<String>)map.get(key);
			String[] array_head =list.get(0).split("__");
			for(int i=4;i<array_head.length;i++){//for b
				WebFact fact=new WebFact(new WebFactId(factNo,array_head[i]));
				Weballobj obj=new Weballobj(new WeballobjId(fact,yymm));								
					obj.setObjA301(Double.valueOf(list.get(1).split("__")[i]));
					obj.setObjA302(Double.valueOf(list.get(2).split("__")[i]));
					obj.setObjA303(Double.valueOf(list.get(3).split("__")[i]));
					obj.setObjA304(Double.valueOf(list.get(4).split("__")[i]));
					obj.setObjA305(Double.valueOf(list.get(5).split("__")[i]));
					obj.setObjA306(Double.valueOf(list.get(6).split("__")[i]));
					obj.setObjA307(Double.valueOf(list.get(7).split("__")[i]));
					obj.setObjA401(Double.valueOf(list.get(8).split("__")[i]));
					obj.setObjA402(Double.valueOf(list.get(9).split("__")[i]));
					obj.setObjA403(Double.valueOf(list.get(10).split("__")[i]));
					obj.setObjA404(Double.valueOf(list.get(11).split("__")[i]));
					obj.setObjA405(Double.valueOf(list.get(12).split("__")[i]));
					obj.setObjA406(Double.valueOf(list.get(13).split("__")[i]));
					obj.setObjA407(Double.valueOf(list.get(14).split("__")[i]));
					obj.setObjA408(Double.valueOf(list.get(15).split("__")[i]));
					obj.setObjA409(Double.valueOf(list.get(16).split("__")[i]));
					obj.setObjA410(Double.valueOf(list.get(17).split("__")[i]));
					obj.setObjA501(Double.valueOf(list.get(18).split("__")[i]));
					obj.setObjA502(Double.valueOf(list.get(19).split("__")[i]));
					obj.setObjA503(Double.valueOf(list.get(20).split("__")[i]));
					obj.setObjA504(Double.valueOf(list.get(21).split("__")[i]));
					obj.setObjA505(Double.valueOf(list.get(22).split("__")[i]));
					obj.setObjA506(Double.valueOf(list.get(23).split("__")[i]));
					obj.setObjA507(Double.valueOf(list.get(24).split("__")[i]));
					obj.setObjA508(Double.valueOf(list.get(25).split("__")[i]));
					obj.setObjA509(Double.valueOf(list.get(26).split("__")[i]));
					obj.setObjA601(Double.valueOf(list.get(27).split("__")[i]));
					obj.setObjA602(Double.valueOf(list.get(28).split("__")[i]));
					obj.setObjA603(Double.valueOf(list.get(29).split("__")[i]));
					obj.setObjA604(Double.valueOf(list.get(30).split("__")[i]));
					obj.setObjA605(Double.valueOf(list.get(31).split("__")[i]));
					obj.setObjA606(Double.valueOf(list.get(32).split("__")[i]));
					obj.setObjA701(Double.valueOf(list.get(33).split("__")[i]));
					obj.setObjA702(Double.valueOf(list.get(34).split("__")[i]));
					obj.setObjA703(Double.valueOf(list.get(35).split("__")[i]));
					obj.setObjA704(Double.valueOf(list.get(36).split("__")[i]));
					obj.setObjA705(Double.valueOf(list.get(37).split("__")[i]));
					obj.setObjA706(Double.valueOf(list.get(38).split("__")[i]));
					obj.setObjA707(Double.valueOf(list.get(39).split("__")[i]));
					obj.setObjA708(Double.valueOf(list.get(40).split("__")[i]));
					obj.setObjA709(Double.valueOf(list.get(41).split("__")[i]));
					obj.setObjA710(Double.valueOf(list.get(42).split("__")[i]));
					obj.setObjA711(Double.valueOf(list.get(43).split("__")[i]));
					obj.setObjA712(Double.valueOf(list.get(44).split("__")[i]));
					obj.setObjA713(Double.valueOf(list.get(45).split("__")[i]));
					obj.setObjA714(Double.valueOf(list.get(46).split("__")[i]));
					obj.setObjA715(Double.valueOf(list.get(47).split("__")[i]));
					obj.setObjA716(Double.valueOf(list.get(48).split("__")[i]));
					obj.setObjA717(Double.valueOf(list.get(49).split("__")[i]));
					obj.setObjA718(Double.valueOf(list.get(50).split("__")[i]));
					obj.setObjA719(Double.valueOf(list.get(51).split("__")[i]));
					obj.setObjA801(Double.valueOf(list.get(52).split("__")[i]));
					obj.setObjA802(Double.valueOf(list.get(53).split("__")[i]));
					obj.setObjA803(Double.valueOf(list.get(54).split("__")[i]));
					obj.setObjA804(Double.valueOf(list.get(55).split("__")[i]));
					obj.setObjA805(Double.valueOf(list.get(56).split("__")[i]));
					obj.setObjA806(Double.valueOf(list.get(57).split("__")[i]));
					obj.setObjA807(Double.valueOf(list.get(58).split("__")[i]));
					obj.setObjA808(Double.valueOf(list.get(59).split("__")[i]));
					obj.setObjA901(Double.valueOf(list.get(60).split("__")[i]));
					obj.setObjA902(Double.valueOf(list.get(61).split("__")[i]));
					obj.setObjA903(Double.valueOf(list.get(62).split("__")[i]));
					obj.setObjA904(Double.valueOf(list.get(63).split("__")[i]));
					obj.setObjA905(Double.valueOf(list.get(64).split("__")[i]));
					obj.setObjA906(Double.valueOf(list.get(65).split("__")[i]));
					obj.setObjA907(Double.valueOf(list.get(66).split("__")[i]));
					obj.setObjA908(Double.valueOf(list.get(67).split("__")[i]));
					obj.setObjA1001(Double.valueOf(list.get(68).split("__")[i]));
					obj.setObjA1002(Double.valueOf(list.get(69).split("__")[i]));
					obj.setObjA1003(Double.valueOf(list.get(70).split("__")[i]));
					obj.setObjA1004(Double.valueOf(list.get(71).split("__")[i]));
					obj.setObjA1005(Double.valueOf(list.get(72).split("__")[i]));
					obj.setObjA1006(Double.valueOf(list.get(73).split("__")[i]));
					obj.setObjA1007(Double.valueOf(list.get(74).split("__")[i]));
					obj.setObjA1008(Double.valueOf(list.get(75).split("__")[i]));
					obj.setObjA1101(Double.valueOf(list.get(76).split("__")[i]));
					obj.setObjA1102(Double.valueOf(list.get(77).split("__")[i]));
					obj.setObjA1103(Double.valueOf(list.get(78).split("__")[i]));
					obj.setObjA1104(Double.valueOf(list.get(79).split("__")[i]));
					obj.setObjA1105(Double.valueOf(list.get(80).split("__")[i]));
					obj.setObjA1106(Double.valueOf(list.get(81).split("__")[i]));
					obj.setObjA1107(Double.valueOf(list.get(82).split("__")[i]));
					obj.setObjA1108(Double.valueOf(list.get(83).split("__")[i]));
					obj.setObjA1109(Double.valueOf(list.get(84).split("__")[i]));
					obj.setObjA1110(Double.valueOf(list.get(85).split("__")[i]));
					obj.setObjA1111(Double.valueOf(list.get(86).split("__")[i]));
					obj.setObjA1112(Double.valueOf(list.get(87).split("__")[i]));
					obj.setObjA1113(Double.valueOf(list.get(88).split("__")[i]));
					obj.setObjA1114(Double.valueOf(list.get(89).split("__")[i]));
					obj.setObjA1115(Double.valueOf(list.get(90).split("__")[i]));
					obj.setObjA1116(Double.valueOf(list.get(91).split("__")[i]));
					obj.setObjA1117(Double.valueOf(list.get(92).split("__")[i]));
					obj.setObjA1118(Double.valueOf(list.get(93).split("__")[i]));
					obj.setObjA1119(Double.valueOf(list.get(94).split("__")[i]));
					obj.setObjA1120(Double.valueOf(list.get(95).split("__")[i]));
					obj.setObjA1121(Double.valueOf(list.get(96).split("__")[i]));
					obj.setObjA1122(Double.valueOf(list.get(97).split("__")[i]));
					obj.setObjA1123(Double.valueOf(list.get(98).split("__")[i]));
					obj.setObjA1124(Double.valueOf(list.get(99).split("__")[i]));
					obj.setObjA1125(Double.valueOf(list.get(100).split("__")[i]));
					obj.setObjA1126(Double.valueOf(list.get(101).split("__")[i]));	
				list_b.add(obj);
			}//for b
			list_a.add(list_b);
		}//for a
		for(List<Weballobj> list_b:list_a){
			weballobjser.addMore(list_b);
		}
	}
	

}
