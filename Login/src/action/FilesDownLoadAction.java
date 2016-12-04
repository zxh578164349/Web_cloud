package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import services.IWebUploadFileServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.WebUser;


public class FilesDownLoadAction extends ActionSupport{
	private String fileName;
	private InputStream fileInput;
	private int id;
	private String attachmentOrInline;
	private String pomNo;
	private IWebUploadFileServices webuploadSer;
	
		
	public String getPomNo() {
		return pomNo;
	}

	public void setPomNo(String pomNo) {
		this.pomNo = pomNo;
	}

	public String getAttachmentOrInline(){
		return attachmentOrInline;
	}

	public void setAttachmentOrInline(String attachmentOrInline){
		this.attachmentOrInline=attachmentOrInline;
	}

	public void setWebuploadSer(IWebUploadFileServices webuploadSer) {
		this.webuploadSer = webuploadSer;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getFileName() throws UnsupportedEncodingException {
		return new String(fileName.getBytes(),"iso-8859-1");
		//return fileName;
	}

	public void setFileName(String fileName) throws UnsupportedEncodingException {
		//this.fileName=new String(fileName.getBytes("ISO-8859-1"), "utf-8");
		this.fileName=fileName;
	}

	public InputStream getFileInput() {
		return fileInput;
	}

	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}
	

	

	/************************************************文件上傳與下載**********************************************************/
	public String download() throws UnsupportedEncodingException, FileNotFoundException{
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		String factNo=user.getFactno();
		String username=user.getUsername();
		String result="";
		this.setFileInput(new FileInputStream("d:\\webupload\\"+factNo+"_"+username+"\\"+fileName));
		if(fileInput==null){
			result="input";
		}else{
			result="download";
		}		
		return result;
	}
	public String inline() throws UnsupportedEncodingException, FileNotFoundException{
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		String factNo=user.getFactno();
		String username=user.getUsername();
		String result="";
		this.setFileInput(new FileInputStream("d:\\webupload\\"+factNo+"_"+username+"\\"+fileName));
		if(fileInput==null){
			result="input";
		}else{
			result="inline";
		}		
		return result;
	}
	/************************************************文件上傳與下載**********************************************************/
	
	
	/************************************************物性資料附檔**********************************************************/
	public String download_pom() throws UnsupportedEncodingException, FileNotFoundException{		
		String result="";
		this.setFileInput(new FileInputStream("d:\\WebtabpomFile_backup\\"+pomNo+"\\"+fileName));
		if(fileInput==null){
			result="input";
		}else{
			result="download";
		}		
		return result;
	}
	public String inline_pom() throws UnsupportedEncodingException, FileNotFoundException{		
		String result="";
		this.setFileInput(new FileInputStream("d:\\WebtabpomFile_backup\\"+pomNo+"\\"+fileName));
		if(fileInput==null){
			result="input";
		}else{
			result="inline";
		}		
		return result;
	}
	/************************************************物性資料附檔**********************************************************/
	
	private String encodingFileName() {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GB2312"),
                        "ISO8859-1");
                returnFileName = StringUtils
                        .replace(returnFileName, " ", "%20");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnFileName;
    }
	
	public String delete(){
		String result="";
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		String factNo=user.getFactno();
		String userName=user.getUsername();		
		KyzExpectmatmLog log=new KyzExpectmatmLog();
		log.setUsername(user.getUsername());
		log.setFactNo(factNo);
		log.setObj("WebUploadfiles");
		log.setContent(fileName);
		File file=new File("d:\\webupload\\"+factNo+"_"+userName+"\\"+fileName);
		Boolean flag=webuploadSer.delete(id,log);
		if(flag==true){
			if(file.isFile()&&file.exists()){
				file.delete();
				result="delete";
			}
			result="delete";
		}else{
			result="input";
		}
		return result;
	}
	

}
