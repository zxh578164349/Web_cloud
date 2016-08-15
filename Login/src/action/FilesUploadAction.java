package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import services.IWebUploadFileServices;
import util.GlobalMethod;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyzExpectmatmLog;
import entity.WebUploadfiles;
import entity.WebUser;

public class FilesUploadAction extends ActionSupport{
		   
	 private File file; 
	    //文件名称  
	    private String fileFileName;  
	      
	    //文件类型  
	    private String fileContentType;  
	    //注意：文件名称和文件类型的名称前缀必须相同
	    
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
	    
	    
	    
		private IWebUploadFileServices webuploadSer;
		private WebUploadfiles uploadfile;
		private int id;
		private List<File>files;
		private List<String>filesFileName;
		private List<String>filesContentType;
		
						
		public List<String> getFilesContentType() {
			return filesContentType;
		}

		public void setFilesContentType(List<String> filesContentType) {
			this.filesContentType = filesContentType;
		}

		public List<String> getFilesFileName() {
			return filesFileName;
		}

		public void setFilesFileName(List<String> filesFileName) {
			this.filesFileName = filesFileName;
		}

		public List<File> getFiles() {
			return files;
		}

		public void setFiles(List<File> files) {
			this.files = files;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public WebUploadfiles getUploadfile() {
			return uploadfile;
		}
		public void setUploadfile(WebUploadfiles uploadfile) {
			this.uploadfile = uploadfile;
		}
		public void setWebuploadSer(IWebUploadFileServices webuploadSer) {
			this.webuploadSer = webuploadSer;
		}
		
		

		public void uploadFile() throws Exception{     	        	        	         
	        //获取需要上传文件的文件路径  
	        //File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("upload"));
	    	/**
	    	 * 上傳到服務器
	    	 */
	    	//File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("upload\\"+uploadfile.getFilefactno()+"_"+uploadfile.getFileuser()));
	    	/**
	    	 * 上傳到本地
	    	 */
	    	File uploadFile=new File("d:\\webupload\\"+uploadfile.getFilefactno()+"_"+uploadfile.getFileuser());
	    	//File uploadFile=new File("d:\\webupload\\"+user.getFactno()+"_"+user.getUsername());
	        //判断文件是否上传，如果上传的话将会创建该目录  
	        if(!uploadFile.exists()){  
	            //uploadFile.mkdir(); //创建该目录  
	        	uploadFile.mkdirs();
	        } 	       
	        if(files!=null){
	        	for(int j=0;j<files.size();j++){//for
	        		int index=0;
	        		String firstName=filesFileName.get(j);
	        		for(int k=j+1;k<files.size();k++){
	        			String secondName=filesFileName.get(k);
	        			if(firstName.equals(secondName)){
	        				String[]mixName=secondName.split("\\.");
	        				index++;
	        				filesFileName.set(k, mixName[0]+"-"+index+"."+mixName[1]);
	        			}
	        		}	        				       
		        GlobalMethod.uploadFile(files.get(j),uploadFile + "\\" +filesFileName.get(j));
		        uploadfile.setFilename(filesFileName.get(j));
                uploadfile.setFiletype(filesContentType.get(j));
                webuploadSer.add(uploadfile);
	        	}//for
	        }	       
	       // return "uploadFile";  
	    }
	    
		public String findByName(){
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
			String userName=user.getUsername();
			String factNo=user.getFactno();
			List<WebUploadfiles>list=webuploadSer.findByName(userName, factNo);
			ActionContext.getContext().getSession().put("uploadfiles", list);
			return "findByName";
		}
		public String findByName_1(){
			this.findByName();
			return "findByName_1";
		}
		public String urlEncode(String fname) throws UnsupportedEncodingException{
			String tempname=java.net.URLEncoder.encode(fname,"utf-8");
			return tempname;
		}
		
		
		

 


}
