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
	private IWebUploadFileServices webuploadSer;
	
	
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


	public InputStream getFileInput() throws UnsupportedEncodingException, FileNotFoundException {
		  WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		  String factNo=user.getFactno();
		  String username=user.getUsername();
		  /**
		   * �A�Ⱦ����|
		   */
		  //return ServletActionContext.getServletContext().getResourceAsStream("upload\\"+factNo+"_"+username+"\\"+fileName);
		  /**
		   * ���a���|
		   */
		  //return ServletActionContext.getServletContext().getResourceAsStream("\\uploadfile\\"+factNo+"_"+username+"\\"+fileName);
		  FileInputStream input=new FileInputStream("d:\\webupload\\"+factNo+"_"+username+"\\"+fileName);
		  return input;
		  
	}


	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}

	public String download() throws UnsupportedEncodingException, FileNotFoundException{
		String result="";
		fileInput=this.getFileInput();
		if(fileInput==null){
			result="input";
		}else{
			result="download";
		}		
		return result;
	}
	
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
		/**
		 * �A�Ⱦ����|
		 */
		//File file=new File(ServletActionContext.getServletContext().getRealPath("upload\\"+factNo+"_"+userName+"\\"+fileName));
		/**
		 * ���a���|
		 */
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
