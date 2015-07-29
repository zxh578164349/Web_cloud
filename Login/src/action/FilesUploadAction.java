package action;

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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

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
	           
	    public String goHome() throws Exception{     
	          
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
	        //判断文件是否上传，如果上传的话将会创建该目录  
	        if(!uploadFile.exists()){  
	            //uploadFile.mkdir(); //创建该目录  
	        	uploadFile.mkdirs();
	        } 	       
	        if(files!=null){
	        	for(int j=0;j<files.size();j++){
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
	        		
	        	 //第一种文件上传的方法  
		        //声明文件输入流，为输入流指定文件路径  
		        FileInputStream input=new FileInputStream(files.get(j));  
		        //获取输出流，获取文件的文件地址及名称  
		        FileOutputStream out=new FileOutputStream(uploadFile + "\\" +filesFileName.get(j));  		          
		        try{		        	
		        		byte[] b=new byte[1024];//每次写入的大小  
			            int i=0;  
			            while((i=input.read(b))>0){  
			                out.write(b,0,i);		               
			            }
			            uploadfile.setFilename(filesFileName.get(j));
		                uploadfile.setFiletype(filesContentType.get(j));
		                webuploadSer.add(uploadfile);
		        			            
		        }catch(Exception e){  
		            e.printStackTrace();
		        }finally{  
		            input.close();  
		            out.close();  
		        }
	        	}
	        }
	        System.out.println(fileContentType);
	        System.out.print(fileFileName);
	        return "success";  
	    }
	    
		public String findByName(){
			WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
			String userName=user.getUsername();
			String factNo=user.getFactno();
			List<WebUploadfiles>list=webuploadSer.findByName(userName, factNo);
			ActionContext.getContext().getSession().put("uploadfiles", list);
			return "findByName";
		}
		public String urlEncode(String fname) throws UnsupportedEncodingException{
			String tempname=java.net.URLEncoder.encode(fname,"utf-8");
			return tempname;
		}
		

 


}
