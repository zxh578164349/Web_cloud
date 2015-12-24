package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import entity.WebTabpomfile;

public class SwfuploadAction {
	/*public void uploadfile(){
		File uploadFile_backup=new File("d:\\WebtabpomFile_backup\\"+tabpom.getPomNo());//附檔上傳到D盤(為了避免更新項目時丟失附檔,所在上傳到D盤)
		if(!uploadFile.exists()){
			uploadFile.mkdirs();
		}
		if(!uploadFile_backup.exists()){
			uploadFile_backup.mkdirs();
		}
		for(int i=0;i<files.size();i++){							
			if(files.get(i)!=null){									
				FileInputStream in=new FileInputStream(files.get(i));
				//FileOutputStream out=new FileOutputStream(uploadFile+"\\"+filesFileName.get(i));
				FileOutputStream out_backup=new FileOutputStream(uploadFile_backup+"\\"+filesFileName.get(i));//備份
				byte[]b=new byte[1024];
				int length=0;
				while((length=in.read(b))>0){
					//out.write(b,0,length);
					out_backup.write(b,0,length);//備份
				}																									
				WebTabpomfile webtabFile=new WebTabpomfile();//函文附檔
				webtabFile.getId().setFilename(filesFileName.get(i));
				webtabFile.getId().setWebTabpom(tabpom);																			
			}
		}
	}*/

}
