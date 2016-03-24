/**
 * 
 */
package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	private IWeballobjServices weballobjser;
	
	

	
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
		Map<String,Object>map=ImportExcel.exportListFromFile(file);
		for(String key:map.keySet()){
			List<String>list=(List<String>)map.get(key);
			int lengtha=list.get(0).split("__").length;
		}
	}
	

}
