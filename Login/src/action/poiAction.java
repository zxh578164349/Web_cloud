package action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import entity.Webestproduct;

import services.IWebEstProductServices;

public class poiAction extends ActionSupport implements ServletResponseAware {
	
	private  IWebEstProductServices estProSer;
	
	public IWebEstProductServices getEstProSer() {
		return estProSer;
	}

	public void setEstProSer(IWebEstProductServices estProSer) {
		this.estProSer = estProSer;
	}
	
	private String yymm;
	
	private javax.servlet.http.HttpServletResponse response;
	

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	//public static String outputFile = "e:\\test.xls";

	double  zdRB1=0; double  zdRB2=0;double  zdRB3=0;double  zdRB4=0;double  zdRB5=0;double  zdRB6=0;
	double  zdRB7=0;double  zdRB8=0;double  zdRB9=0;
	double  zdMD1=0;double  zdMD2=0;double  zdMD3=0;double  zdMD4=0;double  zdMD5=0;double  zdMD6=0;
	double  zdMD7=0;double  zdMD8=0;double  zdMD9=0;
	double  zdEVA1=0;double  zdEVA2=0;double  zdEVA3=0;double  zdEVA4=0;double  zdEVA5=0;double  zdEVA6=0;
	double  zdEVA7=0;double  zdEVA8=0;double  zdEVA9=0;
	double  zdPU1=0;double  zdPU2=0;double  zdPU3=0;double  zdPU4=0;double  zdPU5=0;double  zdPU6=0;
	double  zdPU7=0;double  zdPU8=0;double  zdPU9=0;
	double  zdIP1=0;double  zdIP2=0;double  zdIP3=0;double  zdIP4=0;double  zdIP5=0;double  zdIP6=0;
	double  zdIP7=0;double  zdIP8=0;double  zdIP9=0;
	double  zdDJ1=0;double  zdDJ2=0;double  zdDJ3=0;double  zdDJ4=0;double  zdDJ5=0;double  zdDJ6=0;
	double  zdDJ7=0;double  zdDJ8=0;double  zdDJ9=0;
	double  zdJLX1=0;double  zdJLX2=0;double  zdJLX3=0;double  zdJLX4=0;double  zdJLX5=0;double  zdJLX6=0;
	double  zdJLX7=0;double  zdJLX8=0;double  zdJLX9=0;
	double  zdXD1=0;double  zdXD2=0;double  zdXD3=0;double  zdXD4=0;double  zdXD5=0;double  zdXD6=0;
	double  zdXD7=0;double  zdXD8=0;double  zdXD9=0;
	
	
	double  zdShoeMD1=0;double  zdShoeMD2=0;double  zdShoeMD3=0;double  zdShoeMD4=0;double  zdShoeMD5=0;double  zdShoeMD6=0;
	double  zdShoeMD7=0;double  zdShoeMD8=0;double  zdShoeMD9=0;
	double  zdSecGH1=0;double  zdSecGH2=0;double  zdSecGH3=0;double  zdSecGH4=0;double  zdSecGH5=0;double  zdSecGH6=0;
	double  zdSecGH7=0;double  zdSecGH8=0;double  zdSecGH9=0;
	
	
	double  tzRB1=0; double  tzRB2=0;double  tzRB3=0;double  tzRB4=0;double  tzRB5=0;double  tzRB6=0;
	double  tzRB7=0;double  tzRB8=0;double  tzRB9=0;
	double  tzMD1=0;double  tzMD2=0;double  tzMD3=0;double  tzMD4=0;double  tzMD5=0;double  tzMD6=0;
	double  tzMD7=0;double  tzMD8=0;double  tzMD9=0;
	double  tzEVA1=0;double  tzEVA2=0;double  tzEVA3=0;double  tzEVA4=0;double  tzEVA5=0;double  tzEVA6=0;
	double  tzEVA7=0;double  tzEVA8=0;double  tzEVA9=0;
	double  tzPU1=0;double  tzPU2=0;double  tzPU3=0;double  tzPU4=0;double  tzPU5=0;double  tzPU6=0;
	double  tzPU7=0;double  tzPU8=0;double  tzPU9=0;
	double  tzIP1=0;double  tzIP2=0;double  tzIP3=0;double  tzIP4=0;double  tzIP5=0;double  tzIP6=0;
	double  tzIP7=0;double  tzIP8=0;double  tzIP9=0;
	double  tzDJ1=0;double  tzDJ2=0;double  tzDJ3=0;double  tzDJ4=0;double  tzDJ5=0;double  tzDJ6=0;
	double  tzDJ7=0;double  tzDJ8=0;double  tzDJ9=0;
	double  tzJLX1=0;double  tzJLX2=0;double  tzJLX3=0;double  tzJLX4=0;double  tzJLX5=0;double  tzJLX6=0;
	double  tzJLX7=0;double  tzJLX8=0;double  tzJLX9=0;
	double  tzXD1=0;double  tzXD2=0;double  tzXD3=0;double  tzXD4=0;double  tzXD5=0;double  tzXD6=0;
	double  tzXD7=0;double  tzXD8=0;double  tzXD9=0;
	
	double  tzShoeMD1=0;double  tzShoeMD2=0;double  tzShoeMD3=0;double  tzShoeMD4=0;double  tzShoeMD5=0;double  tzShoeMD6=0;
	double  tzShoeMD7=0;double  tzShoeMD8=0;double  tzShoeMD9=0;
	double  tzSecGH1=0;double  tzSecGH2=0;double  tzSecGH3=0;double  tzSecGH4=0;double  tzSecGH5=0;double  tzSecGH6=0;
	double  tzSecGH7=0;double  tzSecGH8=0;double  tzSecGH9=0;
	
	Webestproduct rb=null;Webestproduct md=null;Webestproduct eva=null;Webestproduct pu=null;
	Webestproduct ip=null;Webestproduct dj=null;Webestproduct jlx=null;Webestproduct xd=null;
    Webestproduct ShoeMD=null;Webestproduct SecGH=null;
    
    Webestproduct rbTZ=null;Webestproduct mdTZ=null;Webestproduct evaTZ=null;Webestproduct puTZ=null;
	Webestproduct ipTZ=null;Webestproduct djTZ=null;Webestproduct jlxTZ=null;Webestproduct xdTZ=null;
	Webestproduct ShoeMDTZ=null;Webestproduct SecGHTZ=null;
	
	private void cteateCell(HSSFWorkbook wb, HSSFRow row, short col, String val) {
		HSSFCell cell = row.createCell(col);
		// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(val);
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		cell.setCellStyle(cellstyle);
	}
	
	private  void setRegionBorder(int border, CellRangeAddress region, Sheet sheet,HSSFWorkbook wb){  
        CellStyle cs=wb.createCellStyle();  
        cs.setBorderBottom((short) border);  
        cs.setBorderTop((short) border);  
        cs.setBorderLeft((short) border);  
        cs.setBorderRight((short) border);  
        cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font = wb.createFont();
		// font.setColor(HSSFFont.COLOR_RED);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 14);
		cs.setFont(font);
        setRegionStyle( cs, region, sheet);  
        
        } 
	private  void setRegionBorder1(int border, CellRangeAddress region, Sheet sheet,HSSFWorkbook wb){  
        CellStyle cs=wb.createCellStyle();  
        cs.setBorderBottom((short) border);  
        cs.setBorderTop((short) border);  
        cs.setBorderLeft((short) border);  
        cs.setBorderRight((short) border);  
        cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font = wb.createFont();
		// font.setColor(HSSFFont.COLOR_RED);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 10);
		cs.setFont(font);
		cs.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
        setRegionStyle( cs, region, sheet);  
        
        } 
	
	private  void setRegionBorder2(int border, CellRangeAddress region, Sheet sheet,HSSFWorkbook wb){  
        CellStyle cs=wb.createCellStyle();  
        cs.setBorderBottom((short) border);  
        cs.setBorderTop((short) border);  
        cs.setBorderLeft((short) border);  
        cs.setBorderRight((short) border);  
        cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    
        cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
      
        setRegionStyle( cs, region, sheet);  
        } 
	private  void setRegionBorder3(int border, CellRangeAddress region, Sheet sheet,HSSFWorkbook wb){  
        CellStyle cs=wb.createCellStyle();  
        cs.setBorderBottom((short) border);  
        cs.setBorderTop((short) border);  
        cs.setBorderLeft((short) border);  
        cs.setBorderRight((short) border);  
        cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cs.setWrapText(true);
        setRegionStyle( cs, region, sheet);  
        } 
	
	private  void setRegionStyle(CellStyle cs, CellRangeAddress region, Sheet sheet){  
        for(int i=region.getFirstRow();i<=region.getLastRow();i++){  
            Row row=sheet.getRow(i);  
            if(row==null) row=sheet.createRow(i);  
            for(int j=region.getFirstColumn();j<=region.getLastColumn();j++){  
                Cell cell=row.getCell(j);  
                if( cell==null){  
                    cell=row.createCell(j);  
                    cell.setCellValue("");  
                }  
                 cell.setCellStyle(cs);  
            }  
        }  
    }  
	
	 public  Double format(double data,int scope){
		  double tempDouble=Math.pow(10, scope);
		  data=data*tempDouble;
		  int tempInt=(int) data;
		  return tempInt/tempDouble;
		 }
	 
	 
	 public void TillClass(HSSFRow row,Webestproduct clas,List<Webestproduct> list,int yz){
		 if(list!=null&&list.size()!=0){
			 clas=list.get(0);
		 }
		 if(clas!=null){
		 if(clas.getId().getType().equals("zd")){
			 if(yz==0){
		 if(clas.getId().getFactCode().equals("RB")){
      	   zdRB1 +=clas.getMachinepower();
		      zdRB2 +=clas.getEstdays();
		      zdRB3 +=clas.getEstmodel();
		      zdRB4 +=clas.getEstnum();
		      zdRB5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
		      zdRB6 +=clas.getEsteverymodel();
		      zdRB7 +=clas.getEsteverypeople();
		      zdRB8 +=clas.getEstpay();
		      zdRB9 +=clas.getEstmoney();
         }else if(clas.getId().getFactCode().equals("MD")){
      	   zdMD1 +=clas.getMachinepower();
		      zdMD2 +=clas.getEstdays();
		      zdMD3 +=clas.getEstmodel();
		      zdMD4 +=clas.getEstnum();
		      zdMD5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
		      zdMD6 +=clas.getEsteverymodel();
		      zdMD7 +=clas.getEsteverypeople();
		      zdMD8 +=clas.getEstpay();
		      zdMD9 +=clas.getEstmoney();
         }else if(clas.getId().getFactCode().equals("EVA")){
      	   zdEVA1 +=clas.getMachinepower();
 		      zdEVA2 +=clas.getEstdays();
 		      zdEVA3 +=clas.getEstmodel();
 		      zdEVA4 +=clas.getEstnum();
 		      zdEVA5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
 		      zdEVA6 +=clas.getEsteverymodel();
 		      zdEVA7 +=clas.getEsteverypeople();
 		      zdEVA8 +=clas.getEstpay();
 		      zdEVA9 +=clas.getEstmoney();
         }else if(clas.getId().getFactCode().equals("IP")){
      	   zdIP1 +=clas.getMachinepower();
  		   zdIP2 +=clas.getEstdays();
  		   zdIP3 +=clas.getEstmodel();
  		   zdIP4 +=clas.getEstnum();
  		   zdIP5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
  		   zdIP6 +=clas.getEsteverymodel();
  		   zdIP7 +=clas.getEsteverypeople();
  		   zdIP8 +=clas.getEstpay();
  		   zdIP9 +=clas.getEstmoney();
         }else if(clas.getId().getFactCode().equals("PU")){
      	   zdPU1 +=clas.getMachinepower();
  		   zdPU2 +=clas.getEstdays();
  		   zdPU3 +=clas.getEstmodel();
  		   zdPU4 +=clas.getEstnum();
  		   zdPU5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
  		   zdPU6 +=clas.getEsteverymodel();
  		   zdPU7 +=clas.getEsteverypeople();
  		   zdPU8 +=clas.getEstpay();
  		   zdPU9 +=clas.getEstmoney();
         }else if(clas.getId().getFactCode().equals("底加")){
      	   zdDJ1 +=clas.getMachinepower();
  		   zdDJ2 +=clas.getEstdays();
  		   zdDJ3 +=clas.getEstmodel();
  		   zdDJ4 +=clas.getEstnum();
  		   zdDJ5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
  		   zdDJ6 +=clas.getEsteverymodel();
  		   zdDJ7 +=clas.getEsteverypeople();
  		   zdDJ8 +=clas.getEstpay();
  		   zdDJ9 +=clas.getEstmoney();
         }else if(clas.getId().getFactCode().equals("加硫鞋")){
      	   zdJLX1 +=clas.getMachinepower();
  		   zdJLX2 +=clas.getEstdays();
  		   zdJLX3 +=clas.getEstmodel();
  		   zdJLX4 +=clas.getEstnum();
  		   zdJLX5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
  		   zdJLX6 +=clas.getEsteverymodel();
  		   zdJLX7 +=clas.getEsteverypeople();
  		   zdJLX8 +=clas.getEstpay();
  		   zdJLX9 +=clas.getEstmoney();
         }else if(clas.getId().getFactCode().equals("鞋廠MD")){
        	   zdShoeMD1 +=clas.getMachinepower();
      		   zdShoeMD2 +=clas.getEstdays();
      		   zdShoeMD3 +=clas.getEstmodel();
      		   zdShoeMD4 +=clas.getEstnum();
      		   zdShoeMD5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
      		   zdShoeMD6 +=clas.getEsteverymodel();
      		   zdShoeMD7 +=clas.getEsteverypeople();
      		   zdShoeMD8 +=clas.getEstpay();
      		   zdShoeMD9 +=clas.getEstmoney();
         }else if(clas.getId().getFactCode().equals("加九二廠")){
      	       zdSecGH1 +=clas.getMachinepower();
    		   zdSecGH2 +=clas.getEstdays();
    		   zdSecGH3 +=clas.getEstmodel();
    		   zdSecGH4 +=clas.getEstnum();
    		   zdSecGH5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
    		   zdSecGH6 +=clas.getEsteverymodel();
    		   zdSecGH7 +=clas.getEsteverypeople();
    		   zdSecGH8 +=clas.getEstpay();
    		   zdSecGH9 +=clas.getEstmoney();
         }
		}
				row.createCell(2).setCellValue(clas.getMachinepower());
				row.createCell(3).setCellValue(clas.getEstdays());
				row.createCell(4).setCellValue(clas.getEstmodel());
				row.createCell(5).setCellValue(clas.getEstnum());
				row.createCell(6).setCellValue(format((clas.getEstmodel()/clas.getMachinepower()),2));
				row.createCell(7).setCellValue(clas.getEsteverymodel());
				row.createCell(8).setCellValue(clas.getEsteverypeople());
				row.createCell(9).setCellValue(clas.getEstpay());
				row.createCell(10).setCellValue(clas.getEstmoney());
		 }else{
			 if(yz==0){
			 if(clas.getId().getFactCode().equals("RB")){
		      	   tzRB1 +=clas.getMachinepower();
				      tzRB2 +=clas.getEstdays();
				      tzRB3 +=clas.getEstmodel();
				      tzRB4 +=clas.getEstnum();
				      tzRB5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
				      tzRB6 +=clas.getEsteverymodel();
				      tzRB7 +=clas.getEsteverypeople();
				      tzRB8 +=clas.getEstpay();
				      tzRB9 +=clas.getEstmoney();
		         }else if(clas.getId().getFactCode().equals("MD")){
		      	   tzMD1 +=clas.getMachinepower();
				      tzMD2 +=clas.getEstdays();
				      tzMD3 +=clas.getEstmodel();
				      tzMD4 +=clas.getEstnum();
				      tzMD5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
				      tzMD6 +=clas.getEsteverymodel();
				      tzMD7 +=clas.getEsteverypeople();
				      tzMD8 +=clas.getEstpay();
				      tzMD9 +=clas.getEstmoney();
		         }else if(clas.getId().getFactCode().equals("EVA")){
		      	   tzEVA1 +=clas.getMachinepower();
		 		      tzEVA2 +=clas.getEstdays();
		 		      tzEVA3 +=clas.getEstmodel();
		 		      tzEVA4 +=clas.getEstnum();
		 		      tzEVA5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
		 		      tzEVA6 +=clas.getEsteverymodel();
		 		      tzEVA7 +=clas.getEsteverypeople();
		 		      tzEVA8 +=clas.getEstpay();
		 		      tzEVA9 +=clas.getEstmoney();
		         }else if(clas.getId().getFactCode().equals("IP")){
		      	   tzIP1 +=clas.getMachinepower();
		  		   tzIP2 +=clas.getEstdays();
		  		   tzIP3 +=clas.getEstmodel();
		  		   tzIP4 +=clas.getEstnum();
		  		   tzIP5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
		  		   tzIP6 +=clas.getEsteverymodel();
		  		   tzIP7 +=clas.getEsteverypeople();
		  		   tzIP8 +=clas.getEstpay();
		  		   tzIP9 +=clas.getEstmoney();
		         }else if(clas.getId().getFactCode().equals("PU")){
		      	   tzPU1 +=clas.getMachinepower();
		  		   tzPU2 +=clas.getEstdays();
		  		   tzPU3 +=clas.getEstmodel();
		  		   tzPU4 +=clas.getEstnum();
		  		   tzPU5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
		  		   tzPU6 +=clas.getEsteverymodel();
		  		   tzPU7 +=clas.getEsteverypeople();
		  		   tzPU8 +=clas.getEstpay();
		  		   tzPU9 +=clas.getEstmoney();
		         }else if(clas.getId().getFactCode().equals("底加")){
		      	   tzDJ1 +=clas.getMachinepower();
		  		   tzDJ2 +=clas.getEstdays();
		  		   tzDJ3 +=clas.getEstmodel();
		  		   tzDJ4 +=clas.getEstnum();
		  		   tzDJ5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
		  		   tzDJ6 +=clas.getEsteverymodel();
		  		   tzDJ7 +=clas.getEsteverypeople();
		  		   tzDJ8 +=clas.getEstpay();
		  		   tzDJ9 +=clas.getEstmoney();
		         }else if(clas.getId().getFactCode().equals("加硫鞋")){
		      	   tzJLX1 +=clas.getMachinepower();
		  		   tzJLX2 +=clas.getEstdays();
		  		   tzJLX3 +=clas.getEstmodel();
		  		   tzJLX4 +=clas.getEstnum();
		  		   tzJLX5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
		  		   tzJLX6 +=clas.getEsteverymodel();
		  		   tzJLX7 +=clas.getEsteverypeople();
		  		   tzJLX8 +=clas.getEstpay();
		  		   tzJLX9 +=clas.getEstmoney();
		         }else if(clas.getId().getFactCode().equals("鞋廠MD")){
		        	   tzShoeMD1 +=clas.getMachinepower();
		      		   tzShoeMD2 +=clas.getEstdays();
		      		   tzShoeMD3 +=clas.getEstmodel();
		      		   tzShoeMD4 +=clas.getEstnum();
		      		   tzShoeMD5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
		      		   tzShoeMD6 +=clas.getEsteverymodel();
		      		   tzShoeMD7 +=clas.getEsteverypeople();
		      		   tzShoeMD8 +=clas.getEstpay();
		      		   tzShoeMD9 +=clas.getEstmoney();
		         }else if(clas.getId().getFactCode().equals("加九二廠")){
		      	       tzSecGH1 +=clas.getMachinepower();
		    		   tzSecGH2 +=clas.getEstdays();
		    		   tzSecGH3 +=clas.getEstmodel();
		    		   tzSecGH4 +=clas.getEstnum();
		    		   tzSecGH5 +=format((clas.getEstmodel()/clas.getMachinepower()),2);
		    		   tzSecGH6 +=clas.getEsteverymodel();
		    		   tzSecGH7 +=clas.getEsteverypeople();
		    		   tzSecGH8 +=clas.getEstpay();
		    		   tzSecGH9 +=clas.getEstmoney();
		         }
			 }
						row.createCell(11).setCellValue(clas.getMachinepower());
						row.createCell(12).setCellValue(clas.getEstdays());
						row.createCell(13).setCellValue(clas.getEstmodel());
						row.createCell(14).setCellValue(clas.getEstnum());
						row.createCell(15).setCellValue(format((clas.getEstmodel()/clas.getMachinepower()),2));
						row.createCell(16).setCellValue(clas.getEsteverymodel());
						row.createCell(17).setCellValue(clas.getEsteverypeople());
						row.createCell(18).setCellValue(clas.getEstpay());
						row.createCell(19).setCellValue(clas.getEstmoney());
		 }
		 }
	 }
	 
	public void fenClas(List<Webestproduct> list){
		if(list!=null&&list.size()!=0){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getId().getType().equals("zd")){
			if(list.get(i).getId().getFactCode().equals("RB")){
				rb=new Webestproduct();
				rb=list.get(i);
			}else if(list.get(i).getId().getFactCode().equals("MD")){
				md=new Webestproduct();
				md=list.get(i);
			}else if(list.get(i).getId().getFactCode().equals("IP")){
				ip=new Webestproduct();
				ip=list.get(i);
			}else if(list.get(i).getId().getFactCode().equals("鞋墊")){
				xd=new Webestproduct();
				xd=list.get(i);
			}else if(list.get(i).getId().getFactCode().equals("底加")) {
				dj=new Webestproduct();
				dj=list.get(i);
			}else if(list.get(i).getId().getFactCode().equals("PU")) {
				pu=new Webestproduct();
				pu=list.get(i);
			}else if(list.get(i).getId().getFactCode().equals("加硫鞋")) {
				jlx=new Webestproduct();
				jlx=list.get(i);
			}else if(list.get(i).getId().getFactCode().equals("EVA")) {
				eva=new Webestproduct();
				eva=list.get(i);
			}else if(list.get(i).getId().getFactCode().equals("鞋廠MD")) {
				ShoeMD=new Webestproduct();
				ShoeMD=list.get(i);
			}else if(list.get(i).getId().getFactCode().equals("加九二廠")) {
				SecGH=new Webestproduct();
				SecGH=list.get(i);
			}
			}else{
				if(list.get(i).getId().getFactCode().equals("RB")){
					rbTZ=new Webestproduct();
					rbTZ=list.get(i);
				}else if(list.get(i).getId().getFactCode().equals("MD")){
					mdTZ=new Webestproduct();
					mdTZ=list.get(i);
				}else if(list.get(i).getId().getFactCode().equals("IP")){
					ipTZ=new Webestproduct();
					ipTZ=list.get(i);
				}else if(list.get(i).getId().getFactCode().equals("鞋墊")){
					xdTZ=new Webestproduct();
					xdTZ=list.get(i);
				}else if(list.get(i).getId().getFactCode().equals("底加")) {
					djTZ=new Webestproduct();
					djTZ=list.get(i);
				}else if(list.get(i).getId().getFactCode().equals("PU")) {
					puTZ=new Webestproduct();
					puTZ=list.get(i);
				}else if(list.get(i).getId().getFactCode().equals("加硫鞋")) {
					jlxTZ=new Webestproduct();
					jlxTZ=list.get(i);
				}else if(list.get(i).getId().getFactCode().equals("EVA")) {
					evaTZ=new Webestproduct();
					evaTZ=list.get(i);
				}else if(list.get(i).getId().getFactCode().equals("鞋廠MD")) {
					ShoeMDTZ=new Webestproduct();
					ShoeMDTZ=list.get(i);
				}else if(list.get(i).getId().getFactCode().equals("加九二廠")) {
					SecGHTZ=new Webestproduct();
					SecGHTZ=list.get(i);
				}
			}
		}
		}
	}
	public void fenLeiXioaji(HSSFRow row,String dd){
		Map<String, List<Double>> sd=new HashMap<String, List<Double>>();
		List<Double> rbf=new ArrayList<Double>();
		rbf.add(zdRB1);rbf.add(zdRB2);rbf.add(zdRB3);rbf.add(zdRB4);rbf.add(zdRB5);rbf.add(zdRB6);
		rbf.add(zdRB7);rbf.add(zdRB8);rbf.add(zdRB9);
		List<Double> mdf=new ArrayList<Double>();
		mdf.add(zdMD1);mdf.add(zdMD2);mdf.add(zdMD3);mdf.add(zdMD4);mdf.add(zdMD5);mdf.add(zdMD6);
		mdf.add(zdMD7);mdf.add(zdMD8);mdf.add(zdMD9);
		List<Double> evaf=new ArrayList<Double>();
		evaf.add(zdEVA1);evaf.add(zdEVA2);evaf.add(zdEVA3);evaf.add(zdEVA4);evaf.add(zdEVA5);evaf.add(zdEVA6);
		evaf.add(zdEVA7);evaf.add(zdEVA8);evaf.add(zdEVA9);
		List<Double> ipf=new ArrayList<Double>();
		ipf.add(zdIP1);ipf.add(zdIP2);ipf.add(zdIP3);ipf.add(zdIP4);ipf.add(zdIP5);ipf.add(zdIP6);
		ipf.add(zdIP7);ipf.add(zdIP8);ipf.add(zdIP9);
		List<Double> puf=new ArrayList<Double>();
		puf.add(zdPU1);puf.add(zdPU2);puf.add(zdPU3);puf.add(zdPU4);puf.add(zdPU5);puf.add(zdPU6);
		puf.add(zdPU7);puf.add(zdPU8);puf.add(zdPU9);
		List<Double> djf=new ArrayList<Double>();
		djf.add(zdDJ1);djf.add(zdDJ2);djf.add(zdDJ3);djf.add(zdDJ4);djf.add(zdDJ5);djf.add(zdDJ6);
		djf.add(zdDJ7);djf.add(zdDJ8);djf.add(zdDJ9);
		List<Double> jlxf=new ArrayList<Double>();
		jlxf.add(zdJLX1);jlxf.add(zdJLX2);jlxf.add(zdJLX3);jlxf.add(zdJLX4);jlxf.add(zdJLX5);jlxf.add(zdJLX6);
		jlxf.add(zdJLX7);jlxf.add(zdJLX8);jlxf.add(zdJLX9);
		List<Double>xdlist=new ArrayList<Double>();
		xdlist.add(zdXD1);xdlist.add(zdXD2);xdlist.add(zdXD3);xdlist.add(zdXD4);xdlist.add(zdXD5);xdlist.add(zdXD6);
		xdlist.add(zdXD7);xdlist.add(zdXD8);xdlist.add(zdXD9);
		List<Double>shoemdlist=new ArrayList<Double>();
		shoemdlist.add(zdShoeMD1);shoemdlist.add(zdShoeMD2);shoemdlist.add(zdShoeMD3);shoemdlist.add(zdShoeMD4);shoemdlist.add(zdShoeMD5);shoemdlist.add(zdShoeMD6);
		shoemdlist.add(zdShoeMD7);shoemdlist.add(zdShoeMD8);shoemdlist.add(zdShoeMD9);
		List<Double>secghlist=new ArrayList<Double>();
		secghlist.add(zdSecGH1);secghlist.add(zdSecGH2);secghlist.add(zdSecGH3);secghlist.add(zdSecGH4);secghlist.add(zdSecGH5);secghlist.add(zdSecGH6);
		secghlist.add(zdSecGH7);secghlist.add(zdSecGH8);secghlist.add(zdSecGH9);
		
		
		
		List<Double> rbTZf=new ArrayList<Double>();
		rbTZf.add(tzRB1);rbTZf.add(tzRB2);rbTZf.add(tzRB3);rbTZf.add(tzRB4);rbTZf.add(tzRB5);rbTZf.add(tzRB6);
		rbTZf.add(tzRB7);rbTZf.add(tzRB8);rbTZf.add(tzRB9);
		List<Double> mdTZf=new ArrayList<Double>();
		mdTZf.add(tzMD1);mdTZf.add(tzMD2);mdTZf.add(tzMD3);mdTZf.add(tzMD4);mdTZf.add(tzMD5);mdTZf.add(tzMD6);
		mdTZf.add(tzMD7);mdTZf.add(tzMD8);mdTZf.add(tzMD9);
		List<Double> evaTZf=new ArrayList<Double>();
		evaTZf.add(tzEVA1);evaTZf.add(tzEVA2);evaTZf.add(tzEVA3);evaTZf.add(tzEVA4);evaTZf.add(tzEVA5);evaTZf.add(tzEVA6);
		evaTZf.add(tzEVA7);evaTZf.add(tzEVA8);evaTZf.add(tzEVA9);
		List<Double> ipTZf=new ArrayList<Double>();
		ipTZf.add(tzIP1);ipTZf.add(tzIP2);ipTZf.add(tzIP3);ipTZf.add(tzIP4);ipTZf.add(tzIP5);ipTZf.add(tzIP6);
		ipTZf.add(tzIP7);ipTZf.add(tzIP8);ipTZf.add(tzIP9);
		List<Double> puTZf=new ArrayList<Double>();
		puTZf.add(tzPU1);puTZf.add(tzPU2);puTZf.add(tzPU3);puTZf.add(tzPU4);puTZf.add(tzPU5);puTZf.add(tzPU6);
		puTZf.add(tzPU7);puTZf.add(tzPU8);puTZf.add(tzPU9);
		List<Double> djTZf=new ArrayList<Double>();
		djTZf.add(tzDJ1);djTZf.add(tzDJ2);djTZf.add(tzDJ3);djTZf.add(tzDJ4);djTZf.add(tzDJ5);djTZf.add(tzDJ6);
		djTZf.add(tzDJ7);djTZf.add(tzDJ8);djTZf.add(tzDJ9);
		List<Double> jlxTZf=new ArrayList<Double>();
		jlxTZf.add(tzJLX1);jlxTZf.add(tzJLX2);jlxTZf.add(tzJLX3);jlxTZf.add(tzJLX4);jlxTZf.add(tzJLX5);jlxTZf.add(tzJLX6);
		jlxTZf.add(tzJLX7);jlxTZf.add(tzJLX8);jlxTZf.add(tzJLX9);
		List<Double>xdlist_TZ=new ArrayList<Double>();
		xdlist_TZ.add(tzXD1);xdlist_TZ.add(tzXD2);xdlist_TZ.add(tzXD3);xdlist_TZ.add(tzXD4);xdlist_TZ.add(tzXD5);xdlist_TZ.add(tzXD6);
		xdlist_TZ.add(tzXD7);xdlist_TZ.add(tzXD8);xdlist_TZ.add(tzXD9);
		
		List<Double>shoemdlist_TZ=new ArrayList<Double>();
		shoemdlist_TZ.add(tzShoeMD1);shoemdlist_TZ.add(tzShoeMD2);shoemdlist_TZ.add(tzShoeMD3);shoemdlist_TZ.add(tzShoeMD4);shoemdlist_TZ.add(tzShoeMD5);shoemdlist_TZ.add(tzShoeMD6);
		shoemdlist_TZ.add(tzShoeMD7);shoemdlist_TZ.add(tzShoeMD8);shoemdlist_TZ.add(tzShoeMD9);
		List<Double>secghlist_TZ=new ArrayList<Double>();
		secghlist_TZ.add(tzSecGH1);secghlist_TZ.add(tzSecGH2);secghlist_TZ.add(tzSecGH3);secghlist_TZ.add(tzSecGH4);secghlist_TZ.add(tzSecGH5);secghlist_TZ.add(tzSecGH6);
		secghlist_TZ.add(tzSecGH7);secghlist_TZ.add(tzSecGH8);secghlist_TZ.add(tzSecGH9);
		sd.put("rbf", rbf);sd.put("mdf", mdf);sd.put("evaf", evaf);sd.put("ipf", ipf);sd.put("puf", puf);
		sd.put("djf", djf);sd.put("jlxf", jlxf);sd.put("xdlist", xdlist);sd.put("shoemdlist", shoemdlist);sd.put("secghlist", secghlist);
		sd.put("rbTZf", rbTZf);sd.put("mdTZf", mdTZf);sd.put("evaTZf", evaTZf);
		sd.put("ipTZf", ipTZf);sd.put("puTZf", puTZf);sd.put("djTZf", djTZf);sd.put("jlxTZf", jlxTZf);
		sd.put("xdlistTZf", xdlist_TZ);sd.put("shoemdlistTZf", shoemdlist_TZ);sd.put("secghlistTZf", secghlist_TZ);
		
		if(sd!=null&&!sd.isEmpty()){
		for(String key:sd.keySet()){
			if(key.equals(dd)){
				if(dd.indexOf("TZf")>0){
					for(int i=0;i<sd.get(key).size();i++){
						row.createCell(i+11).setCellValue(sd.get(key).get(i));
						}
				}else{
				for(int i=0;i<sd.get(key).size();i++){
				row.createCell(i+2).setCellValue(sd.get(key).get(i));
				}
				}
			}
		}
		}
	}
	
	public void sum(HSSFRow row){
		row.createCell(2).setCellValue(zdRB1+zdMD1+zdEVA1+zdIP1+zdPU1+zdDJ1+zdJLX1+zdXD1+zdShoeMD1+zdSecGH1);
		row.createCell(3).setCellValue(zdRB2+zdMD2+zdEVA2+zdIP2+zdPU2+zdDJ2+zdJLX2+zdXD2+zdShoeMD2+zdSecGH2);
		row.createCell(4).setCellValue(zdRB3+zdMD3+zdEVA3+zdIP3+zdPU3+zdDJ3+zdJLX3+zdXD3+zdShoeMD3+zdSecGH3);
		row.createCell(5).setCellValue(zdRB4+zdMD4+zdEVA4+zdIP4+zdPU4+zdDJ4+zdJLX4+zdXD4+zdShoeMD4+zdSecGH4);
		row.createCell(6).setCellValue(zdRB5+zdMD5+zdEVA5+zdIP5+zdPU5+zdDJ5+zdJLX5+zdXD5+zdShoeMD5+zdSecGH5);
		row.createCell(7).setCellValue(zdRB6+zdMD6+zdEVA6+zdIP6+zdPU6+zdDJ6+zdJLX6+zdXD6+zdShoeMD6+zdSecGH6);
		row.createCell(8).setCellValue(zdRB7+zdMD7+zdEVA7+zdIP7+zdPU7+zdDJ7+zdJLX7+zdXD7+zdShoeMD7+zdSecGH7);
		row.createCell(9).setCellValue(zdRB8+zdMD8+zdEVA8+zdIP8+zdPU8+zdDJ8+zdJLX8+zdXD8+zdShoeMD8+zdSecGH8);
		row.createCell(10).setCellValue(zdRB9+zdMD9+zdEVA9+zdIP9+zdPU9+zdDJ9+zdJLX9+zdXD9+zdShoeMD9+zdSecGH9);
		row.createCell(11).setCellValue(tzRB1+tzMD1+tzEVA1+tzIP1+tzPU1+tzDJ1+tzJLX1+tzXD1+tzShoeMD1+tzSecGH1);
		row.createCell(12).setCellValue(tzRB2+tzMD2+tzEVA2+tzIP2+tzPU2+tzDJ2+tzJLX2+tzXD2+tzShoeMD2+tzSecGH2);
		row.createCell(13).setCellValue(tzRB3+tzMD3+tzEVA3+tzIP3+tzPU3+tzDJ3+tzJLX3+tzXD3+tzShoeMD3+tzSecGH3);
		row.createCell(14).setCellValue(tzRB4+tzMD4+tzEVA4+tzIP4+tzPU4+tzDJ4+tzJLX4+tzXD4+tzShoeMD4+tzSecGH4);
		row.createCell(15).setCellValue(tzRB5+tzMD5+tzEVA5+tzIP5+tzPU5+tzDJ5+tzJLX5+tzXD5+tzShoeMD5+tzSecGH5);
		row.createCell(16).setCellValue(tzRB6+tzMD6+tzEVA6+tzIP6+tzPU6+tzDJ6+tzJLX6+tzXD6+tzShoeMD6+tzSecGH6);
		row.createCell(17).setCellValue(tzRB7+tzMD7+tzEVA7+tzIP7+tzPU7+tzDJ7+tzJLX7+tzXD7+tzShoeMD7+tzSecGH7);
		row.createCell(18).setCellValue(tzRB8+tzMD8+tzEVA8+tzIP8+tzPU8+tzDJ8+tzJLX8+tzXD8+tzShoeMD8+tzSecGH8);
		row.createCell(19).setCellValue(tzRB9+tzMD9+tzEVA9+tzIP9+tzPU9+tzDJ9+tzJLX9+tzXD9+tzShoeMD9+tzSecGH9);
	}
	 
	public String upload(){
		try {
			ServletOutputStream os = response.getOutputStream();
			response.setContentType("html/csv");
			//--------------改英文名
			String tempName=yymm+".xls";
			String fileName = new String(tempName.toString().getBytes("utf-8"),
					"ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename="+fileName);
			// 创建新的Excel 工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 设置字体
			HSSFFont font = workbook.createFont();
			// font.setColor(HSSFFont.COLOR_RED);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short) 14);
			// 设置样式
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    int border = 1; 
			// HSSFCellStyle cellStyle2= workbook.createCellStyle();
			// cellStyle.setFont(font2);
			// cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 在Excel工作簿中建一工作表，其名为缺省值
			// 如要新建一名为"月报表"的工作表，其语句为：
			HSSFSheet sheet = workbook.createSheet("匯總表");
			// 起始行，结束行，起始列，结束列
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0,
					19);
			sheet.addMergedRegion(cellRangeAddress);
			// 第一行
			// 在索引0的位置创建行（最顶端的行）
			HSSFRow row = sheet.createRow(0);
			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell(0);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(cellStyle);
			// 在单元格中输入一些内容
			cell.setCellValue(new HSSFRichTextString(yymm+"各廠預計生產與請款匯總表"));
			setRegionBorder(border, cellRangeAddress, sheet, workbook);
			
			// 第二行
			cellRangeAddress = new CellRangeAddress(1, 1, 2, 10);
			sheet.addMergedRegion(cellRangeAddress);
			row = sheet.createRow(1);
			HSSFCell datecell = row.createCell(2);
			datecell.setCellType(HSSFCell.CELL_TYPE_STRING);
			datecell.setCellValue("暫訂版");
		    setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress = new CellRangeAddress(1, 1, 11, 19);
			sheet.addMergedRegion(cellRangeAddress);
			HSSFCell cell2 = row.createCell(11);
			cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell2.setCellValue("調整版");
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			
			// 第三行
			row = sheet.createRow(2);
			row.createCell(0).setCellValue("廠別");
			row.createCell(1).setCellValue("形態");
			row.createCell(2).setCellValue("標準生產戰力");
			row.createCell(3).setCellValue("預計生產天數");
			row.createCell(4).setCellValue("預計生產模數");
			row.createCell(5).setCellValue("預計生產雙數");
			row.createCell(6).setCellValue("機臺達成率");
			row.createCell(7).setCellValue("預計上模數");
			row.createCell(8).setCellValue("預計需求人數");
			row.createCell(9).setCellValue("預計請款雙數");
			row.createCell(10).setCellValue("預計請款金額");
			row.createCell(11).setCellValue("標準生產戰力");
			row.createCell(12).setCellValue("預計生產天數");
			row.createCell(13).setCellValue("預計生產模數");
			row.createCell(14).setCellValue("預計生產雙數");
			row.createCell(15).setCellValue("機臺達成率");
			row.createCell(16).setCellValue("預計上模數");
			row.createCell(17).setCellValue("預計需求人數");
			row.createCell(18).setCellValue("預計請款雙數");
			row.createCell(19).setCellValue("預計請款金額");
			for(int i=0;i<20;i++){
				cellRangeAddress = new CellRangeAddress(2, 3, i, i);
				sheet.addMergedRegion(cellRangeAddress);
				setRegionBorder3(border, cellRangeAddress, sheet, workbook);
			}		
			// 一廠
		   List<Webestproduct>	pro631 =estProSer.selByFactNoAndYymm("631", yymm,"zd");
		   List<Webestproduct>	pro631TZ =estProSer.selByFactNoAndYymm("631",yymm,"tz");
			// 第四行
			row = sheet.createRow(4);
			row.createCell(0).setCellValue("一廠");
			row.createCell(1).setCellValue("RB");
			TillClass(row, null, pro631,0);
			TillClass(row, null, pro631TZ,0);
			for(int i=0;i<20;i++){
				cellRangeAddress = new CellRangeAddress(4, 4, i, i);
				sheet.addMergedRegion(cellRangeAddress);
				setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			cellRangeAddress = new CellRangeAddress(5, 5, 0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row = sheet.createRow(5);
			row.createCell(0).setCellValue("小計");
			TillClass(row, null, pro631,1);
			TillClass(row, null, pro631TZ,1);
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			sheet.addMergedRegion(cellRangeAddress);
			for(int i=0;i<20;i++){
				if(i>1){
				cellRangeAddress = new CellRangeAddress(5, 5, i, i);
				setRegionBorder2(border, cellRangeAddress, sheet, workbook);
				}
			}
			
			
			//二廠
			List<Webestproduct>	pro632 =estProSer.selByFactNoAndYymm("632", yymm,"zd");
			List<Webestproduct>	pro632TZ =estProSer.selByFactNoAndYymm("632",yymm,"tz");
			fenClas(pro632);
			fenClas(pro632TZ);
			cellRangeAddress=new CellRangeAddress(6,8,0,0);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(6);
		    row.createCell(0).setCellValue("二廠");
			row.createCell(1).setCellValue("RB");
			TillClass(row, rb,null,0);
			TillClass(row, rbTZ,null,0);
			row=sheet.createRow(7);
			row.createCell(1).setCellValue("MD");
			TillClass(row, md,null,0);
			TillClass(row, mdTZ,null,0);
			row=sheet.createRow(8);
			row.createCell(1).setCellValue("EVA");
			TillClass(row, eva,null,0);
			TillClass(row, evaTZ,null,0);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			for(int q=0;q<20;q++){
				if(q>0){
					cellRangeAddress = new CellRangeAddress(6, 6, q, q);
					setRegionBorder2(border, cellRangeAddress, sheet, workbook);
					cellRangeAddress = new CellRangeAddress(7, 7, q, q);
					setRegionBorder2(border, cellRangeAddress, sheet, workbook);
					cellRangeAddress = new CellRangeAddress(8, 8, q, q);
					setRegionBorder2(border, cellRangeAddress, sheet, workbook);
				}
			}
			cellRangeAddress=new CellRangeAddress(9,9,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(9);
			row.createCell(0).setCellValue("小計");
			row.createCell(2).setCellValue(((rb==null)?0:rb.getMachinepower())+((eva==null)?0:eva.getMachinepower())+((md==null)?0:md.getMachinepower()));
			row.createCell(3).setCellValue(((rb==null)?0:rb.getEstdays())+((eva==null)?0:eva.getEstdays())+((md==null)?0:md.getEstdays()));
			row.createCell(4).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((eva==null)?0:eva.getEsteverymodel())+((md==null)?0:md.getEsteverymodel()));
			row.createCell(5).setCellValue(((rb==null)?0:rb.getEstnum())+((eva==null)?0:eva.getEstnum())+((md==null)?0:md.getEstnum()));
			row.createCell(6).setCellValue(format(((rb==null)?0:rb.getEstmodel()/rb.getMachinepower())+((eva==null)?0:eva.getEstmodel()/eva.getMachinepower())+((md==null)?0:md.getEstmodel()/md.getMachinepower()),2));
			row.createCell(7).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((eva==null)?0:eva.getEsteverymodel())+((md==null)?0:md.getEsteverymodel()));
			row.createCell(8).setCellValue(((rb==null)?0:rb.getEsteverypeople())+((eva==null)?0:eva.getEsteverypeople())+((md==null)?0:md.getEsteverypeople()));
			row.createCell(9).setCellValue(((rb==null)?0:rb.getEstpay())+((eva==null)?0:eva.getEstpay())+((md==null)?0:md.getEstpay()));
			row.createCell(10).setCellValue(((rb==null)?0:rb.getEstmoney())+((eva==null)?0:eva.getEstmoney())+((md==null)?0:md.getEstmoney()));
			row.createCell(11).setCellValue(((rbTZ==null)?0:rbTZ.getMachinepower())+((evaTZ==null)?0:evaTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getMachinepower()));
			row.createCell(12).setCellValue(((rbTZ==null)?0:rbTZ.getEstdays())+((evaTZ==null)?0:evaTZ.getEstdays())+((mdTZ==null)?0:mdTZ.getEstdays()));
			row.createCell(13).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((evaTZ==null)?0:evaTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel()));
			row.createCell(14).setCellValue(((rbTZ==null)?0:rbTZ.getEstnum())+((evaTZ==null)?0:evaTZ.getEstnum())+((mdTZ==null)?0:mdTZ.getEstnum()));
			row.createCell(15).setCellValue(format(((rbTZ==null)?0:rbTZ.getEstmodel()/rbTZ.getMachinepower())+((evaTZ==null)?0:evaTZ.getEstmodel()/evaTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getEstmodel()/mdTZ.getMachinepower()),2));
			row.createCell(16).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((evaTZ==null)?0:evaTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel()));
			row.createCell(17).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverypeople())+((evaTZ==null)?0:evaTZ.getEsteverypeople())+((mdTZ==null)?0:mdTZ.getEsteverypeople()));
			row.createCell(18).setCellValue(((rbTZ==null)?0:rbTZ.getEstpay())+((evaTZ==null)?0:evaTZ.getEstpay())+((mdTZ==null)?0:mdTZ.getEstpay()));
			row.createCell(19).setCellValue(((rbTZ==null)?0:rbTZ.getEstmoney())+((evaTZ==null)?0:evaTZ.getEstmoney())+((mdTZ==null)?0:mdTZ.getEstmoney()));
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int w=0;w<20;w++){
			if(w>1){
					cellRangeAddress=new CellRangeAddress(9,9,w,w);
					setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}
			rb=null;eva=null;md=null;
			rbTZ=null;evaTZ=null;mdTZ=null;
		
			//三廠
			List<Webestproduct>	pro687 =estProSer.selByFactNoAndYymm("687", yymm,"zd");
			List<Webestproduct>	pro687TZ =estProSer.selByFactNoAndYymm("687",yymm,"tz");
			row=sheet.createRow(10);
			row.createCell(0).setCellValue("三廠");
			row.createCell(1).setCellValue("RB");
			TillClass(row, null,pro687,0);
			TillClass(row, null,pro687TZ,0);
			cellRangeAddress=new CellRangeAddress(11,11,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(11);
			row.createCell(0).setCellValue("小計");
			TillClass(row, null,pro687,1);
			TillClass(row, null,pro687TZ,1);
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int e=0;e<20;e++){
				cellRangeAddress=new CellRangeAddress(10,10,e,e);
				setRegionBorder2(border, cellRangeAddress, sheet, workbook);
				if(e>1){
				cellRangeAddress=new CellRangeAddress(11,11,e,e);
				setRegionBorder2(border, cellRangeAddress, sheet, workbook);
				}
			}
			
			//中山宏典
			List<Webestproduct>	proHD =estProSer.selByFactNoAndYymm("HD", yymm,"zd");
			List<Webestproduct>	proHDTZ =estProSer.selByFactNoAndYymm("HD",yymm,"tz");
			fenClas(proHD);
			fenClas(proHDTZ);
			cellRangeAddress=new CellRangeAddress(12,13,0,0);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(12);
			row.createCell(0).setCellValue("中山宏典");
			row.createCell(1).setCellValue("PU");
			TillClass(row, pu, null,0);
			TillClass(row, puTZ, null,0);
			row=sheet.createRow(13);
			row.createCell(1).setCellValue("RB");
			TillClass(row, rb, null,0);
			TillClass(row, rbTZ, null,0);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(14,14,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(14);
			row.createCell(0).setCellValue("小計");
			row.createCell(2).setCellValue(((rb==null)?0:rb.getMachinepower())+((pu==null)?0:pu.getMachinepower()));
			row.createCell(3).setCellValue(((rb==null)?0:rb.getEstdays())+((pu==null)?0:pu.getEstdays()));
			row.createCell(4).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((pu==null)?0:pu.getEsteverymodel()));
			row.createCell(5).setCellValue(((rb==null)?0:rb.getEstnum())+((pu==null)?0:pu.getEstnum()));
			row.createCell(6).setCellValue(format(((rb==null)?0:rb.getEstmodel()/rb.getMachinepower())+((pu==null)?0:pu.getEstmodel()/pu.getMachinepower()),2));
			row.createCell(7).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((pu==null)?0:pu.getEsteverymodel()));
			row.createCell(8).setCellValue(((rb==null)?0:rb.getEsteverypeople())+((pu==null)?0:pu.getEsteverypeople()));
			row.createCell(9).setCellValue(((rb==null)?0:rb.getEstpay())+((pu==null)?0:pu.getEstpay()));
			row.createCell(10).setCellValue(((rb==null)?0:rb.getEstmoney())+((pu==null)?0:pu.getEstmoney()));
			row.createCell(11).setCellValue(((rbTZ==null)?0:rbTZ.getMachinepower())+((puTZ==null)?0:puTZ.getMachinepower()));
			row.createCell(12).setCellValue(((rbTZ==null)?0:rbTZ.getEstdays())+((puTZ==null)?0:puTZ.getEstdays()));
			row.createCell(13).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((puTZ==null)?0:puTZ.getEsteverymodel()));
			row.createCell(14).setCellValue(((rbTZ==null)?0:rbTZ.getEstnum())+((puTZ==null)?0:puTZ.getEstnum()));
			row.createCell(15).setCellValue(format(((rbTZ==null)?0:rbTZ.getEstmodel()/rbTZ.getMachinepower())+((puTZ==null)?0:puTZ.getEstmodel()/puTZ.getMachinepower()),2));
			row.createCell(16).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((puTZ==null)?0:puTZ.getEsteverymodel()));
			row.createCell(17).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverypeople())+((puTZ==null)?0:puTZ.getEsteverypeople()));
			row.createCell(18).setCellValue(((rbTZ==null)?0:rbTZ.getEstpay())+((puTZ==null)?0:puTZ.getEstpay()));
			row.createCell(19).setCellValue(((rbTZ==null)?0:rbTZ.getEstmoney())+((puTZ==null)?0:puTZ.getEstmoney()));
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int r=0;r<20;r++){
			if(r>0){
			cellRangeAddress=new CellRangeAddress(12,12,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(13,13,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			if(r>1){
			cellRangeAddress=new CellRangeAddress(14,14,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}
			pu=null;rb=null;puTZ=null;rbTZ=null;
			
			//陽新加元
			List<Webestproduct>	proB0L7 =estProSer.selByFactNoAndYymm("B0L7", yymm,"zd");
			List<Webestproduct>	proB0L7TZ =estProSer.selByFactNoAndYymm("B0L7",yymm,"tz");
			fenClas(proB0L7);
			fenClas(proB0L7TZ);
			cellRangeAddress=new CellRangeAddress(15,16,0,0);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(15);
			row.createCell(0).setCellValue("陽新加元");
			row.createCell(1).setCellValue("RB");
			TillClass(row, rb,null,0);
			TillClass(row, rbTZ, null,0);
		    row=sheet.createRow(16);
			row.createCell(1).setCellValue("MD");
			TillClass(row, md,null,0);
			TillClass(row, mdTZ, null,0);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(17,17,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(17);
			row.createCell(0).setCellValue("小計");
			row.createCell(2).setCellValue(((rb==null)?0:rb.getMachinepower())+((md==null)?0:md.getMachinepower()));
			row.createCell(3).setCellValue(((rb==null)?0:rb.getEstdays())+((md==null)?0:md.getEstdays()));
			row.createCell(4).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel()));
			row.createCell(5).setCellValue(((rb==null)?0:rb.getEstnum())+((md==null)?0:md.getEstnum()));
			row.createCell(6).setCellValue(format(((rb==null)?0:rb.getEstmodel()/rb.getMachinepower())+((md==null)?0:md.getEstmodel()/md.getMachinepower()),2));
			row.createCell(7).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel()));
			row.createCell(8).setCellValue(((rb==null)?0:rb.getEsteverypeople())+((md==null)?0:md.getEsteverypeople()));
			row.createCell(9).setCellValue(((rb==null)?0:rb.getEstpay())+((md==null)?0:md.getEstpay()));
			row.createCell(10).setCellValue(((rb==null)?0:rb.getEstmoney())+((md==null)?0:md.getEstmoney()));
			row.createCell(11).setCellValue(((rbTZ==null)?0:rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getMachinepower()));
			row.createCell(12).setCellValue(((rbTZ==null)?0:rbTZ.getEstdays())+((mdTZ==null)?0:mdTZ.getEstdays()));
			row.createCell(13).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel()));
			row.createCell(14).setCellValue(((rbTZ==null)?0:rbTZ.getEstnum())+((mdTZ==null)?0:mdTZ.getEstnum()));
			row.createCell(15).setCellValue(format(((rbTZ==null)?0:rbTZ.getEstmodel()/rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getEstmodel()/mdTZ.getMachinepower()),2));
			row.createCell(16).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel()));
			row.createCell(17).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverypeople())+((mdTZ==null)?0:mdTZ.getEsteverypeople()));
			row.createCell(18).setCellValue(((rbTZ==null)?0:rbTZ.getEstpay())+((mdTZ==null)?0:mdTZ.getEstpay()));
			row.createCell(19).setCellValue(((rbTZ==null)?0:rbTZ.getEstmoney())+((mdTZ==null)?0:mdTZ.getEstmoney()));
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int r=0;r<20;r++){
			if(r>0){
			cellRangeAddress=new CellRangeAddress(15,15,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(16,16,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			if(r>1){
			cellRangeAddress=new CellRangeAddress(17,17,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}
			rb=null;md=null;rbTZ=null;mdTZ=null;
		
			//裕盛外銷
			List<Webestproduct>	proB015 =estProSer.selByFactNoAndYymm("B015", yymm,"zd");
			List<Webestproduct>	proB015TZ =estProSer.selByFactNoAndYymm("B015", yymm,"tz");
			row=sheet.createRow(18);
			row.createCell(0).setCellValue("裕盛外銷");
			row.createCell(1).setCellValue("RB");
			TillClass(row, null, proB015,0);
			TillClass(row, null, proB015TZ,0);
			cellRangeAddress=new CellRangeAddress(19,19,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(19);
			row.createCell(0).setCellValue("小計");
			TillClass(row, null, proB015,1);
			TillClass(row, null, proB015TZ,1);
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int e=0;e<20;e++){
				cellRangeAddress=new CellRangeAddress(18,18,e,e);
				setRegionBorder2(border, cellRangeAddress, sheet, workbook);
				if(e>1){
				cellRangeAddress=new CellRangeAddress(19,19,e,e);
				setRegionBorder2(border, cellRangeAddress, sheet, workbook);
				}
			}
			
			//裕盛內銷
			List<Webestproduct>	proB07G =estProSer.selByFactNoAndYymm("B07G", yymm,"zd");
			List<Webestproduct>	proB07GTZ =estProSer.selByFactNoAndYymm("B07G", yymm,"tz");
			row=sheet.createRow(20);
			row.createCell(0).setCellValue("裕盛內銷");
			row.createCell(1).setCellValue("RB");
			TillClass(row,null,proB07G,0);
			TillClass(row, null, proB07GTZ,0);
			cellRangeAddress=new CellRangeAddress(21,21,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(21);
			TillClass(row,null,proB07G,1);
			TillClass(row, null, proB07GTZ,1);
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int e=0;e<20;e++){
				cellRangeAddress=new CellRangeAddress(20,20,e,e);
				setRegionBorder2(border, cellRangeAddress, sheet, workbook);
				if(e>1){
				cellRangeAddress=new CellRangeAddress(21,21,e,e);
				setRegionBorder2(border, cellRangeAddress, sheet, workbook);
				}
			}
			
			//福建鞋墊
			List<Webestproduct>	proXS =estProSer.selByFactNoAndYymm("XS", yymm,"zd");
			List<Webestproduct>	proXSTZ =estProSer.selByFactNoAndYymm("XS", yymm,"tz");
			fenClas(proXS);
			fenClas(proXSTZ);
			cellRangeAddress=new CellRangeAddress(22,26,0,0);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(22);
			row.createCell(0).setCellValue("福建鞋塑");
			row.createCell(1).setCellValue("RB");
			TillClass(row, rb,null,0);
			TillClass(row, rbTZ,null,0);
			row=sheet.createRow(23);
			row.createCell(1).setCellValue("MD");
			TillClass(row, md,null,0);
			TillClass(row, mdTZ,null,0);
			row=sheet.createRow(24);
			row.createCell(1).setCellValue("IP");
			TillClass(row, ip,null,0);
			TillClass(row, ipTZ,null,0);
			row=sheet.createRow(25);
			row.createCell(1).setCellValue("鞋墊");
			TillClass(row, xd,null,0);
			TillClass(row, xdTZ,null,0);
			row=sheet.createRow(26);
			row.createCell(1).setCellValue("底加");
			TillClass(row, dj,null,0);	
			TillClass(row, djTZ,null,0);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(27,27,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(27);
			row.createCell(0).setCellValue("小計");
			row.createCell(2).setCellValue(((rb==null)?0:rb.getMachinepower())+((md==null)?0:md.getMachinepower())+((ip==null)?0:ip.getMachinepower())+((xd==null)?0:xd.getMachinepower())+((dj==null)?0:dj.getMachinepower()));
			row.createCell(3).setCellValue(((rb==null)?0:rb.getEstdays())+((md==null)?0:md.getEstdays())+((ip==null)?0:ip.getEstdays())+((xd==null)?0:xd.getEstdays())+((dj==null)?0:dj.getEstdays()));
			row.createCell(4).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel())+((ip==null)?0:ip.getEsteverymodel())+((xd==null)?0:xd.getEsteverymodel())+((dj==null)?0:dj.getEsteverymodel()));
			row.createCell(5).setCellValue(((rb==null)?0:rb.getEstnum())+((md==null)?0:md.getEstnum())+((ip==null)?0:ip.getEstnum())+((xd==null)?0:xd.getEstnum())+((dj==null)?0:dj.getEstnum()));
			row.createCell(6).setCellValue(format(((rb==null)?0:rb.getEstmodel()/rb.getMachinepower())+((md==null)?0:md.getEstmodel()/md.getMachinepower())+((ip==null)?0:ip.getEstmodel()/ip.getMachinepower())+((xd==null)?0:xd.getEstmodel()/xd.getMachinepower())+((dj==null)?0:dj.getEstmodel()/dj.getMachinepower()),2));
			row.createCell(7).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel())+((ip==null)?0:ip.getEsteverymodel())+((xd==null)?0:xd.getEsteverymodel())+((dj==null)?0:dj.getEsteverymodel()));
			row.createCell(8).setCellValue(((rb==null)?0:rb.getEsteverypeople())+((md==null)?0:md.getEsteverypeople())+((ip==null)?0:ip.getEsteverypeople())+((xd==null)?0:xd.getEsteverypeople())+((dj==null)?0:dj.getEsteverypeople()));
			row.createCell(9).setCellValue(((rb==null)?0:rb.getEstpay())+((md==null)?0:md.getEstpay())+((ip==null)?0:ip.getEstpay())+((xd==null)?0:xd.getEstpay())+((dj==null)?0:dj.getEstpay()));
			row.createCell(10).setCellValue(((rb==null)?0:rb.getEstmoney())+((md==null)?0:md.getEstmoney())+((ip==null)?0:ip.getEstmoney())+((xd==null)?0:xd.getEstmoney())+((dj==null)?0:dj.getEstmoney()));
			row.createCell(11).setCellValue(((rbTZ==null)?0:rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getMachinepower())+((ipTZ==null)?0:ipTZ.getMachinepower())+((xdTZ==null)?0:xdTZ.getMachinepower())+((djTZ==null)?0:djTZ.getMachinepower()));
			row.createCell(12).setCellValue(((rbTZ==null)?0:rbTZ.getEstdays())+((mdTZ==null)?0:mdTZ.getEstdays())+((ipTZ==null)?0:ipTZ.getEstdays())+((xdTZ==null)?0:xdTZ.getEstdays())+((djTZ==null)?0:djTZ.getEstdays()));
			row.createCell(13).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel())+((ipTZ==null)?0:ipTZ.getEsteverymodel())+((xdTZ==null)?0:xdTZ.getEsteverymodel())+((djTZ==null)?0:djTZ.getEsteverymodel()));
			row.createCell(14).setCellValue(((rbTZ==null)?0:rbTZ.getEstnum())+((mdTZ==null)?0:mdTZ.getEstnum())+((ipTZ==null)?0:ipTZ.getEstnum())+((xdTZ==null)?0:xdTZ.getEstnum())+((djTZ==null)?0:djTZ.getEstnum()));
			row.createCell(15).setCellValue(format(((rbTZ==null)?0:rbTZ.getEstmodel()/rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getEstmodel()/mdTZ.getMachinepower())+((ipTZ==null)?0:ipTZ.getEstmodel()/ipTZ.getMachinepower())+((xdTZ==null)?0:xdTZ.getEstmodel()/xdTZ.getMachinepower())+((djTZ==null)?0:djTZ.getEstmodel()/djTZ.getMachinepower()),2));
			row.createCell(16).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel())+((ipTZ==null)?0:ipTZ.getEsteverymodel())+((xdTZ==null)?0:xdTZ.getEsteverymodel())+((djTZ==null)?0:djTZ.getEsteverymodel()));
			row.createCell(17).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverypeople())+((mdTZ==null)?0:mdTZ.getEsteverypeople())+((ipTZ==null)?0:ipTZ.getEsteverypeople())+((xdTZ==null)?0:xdTZ.getEsteverypeople())+((djTZ==null)?0:djTZ.getEsteverypeople()));
			row.createCell(18).setCellValue(((rbTZ==null)?0:rbTZ.getEstpay())+((mdTZ==null)?0:mdTZ.getEstpay())+((ipTZ==null)?0:ipTZ.getEstpay())+((xdTZ==null)?0:xdTZ.getEstpay())+((djTZ==null)?0:djTZ.getEstpay()));
			row.createCell(19).setCellValue(((rbTZ==null)?0:rbTZ.getEstmoney())+((mdTZ==null)?0:mdTZ.getEstmoney())+((ipTZ==null)?0:ipTZ.getEstmoney())+((xdTZ==null)?0:xdTZ.getEstmoney())+((djTZ==null)?0:djTZ.getEstmoney()));
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int r=0;r<20;r++){
			if(r>0){
			for(int t=22;t<27;t++){
			cellRangeAddress=new CellRangeAddress(t,t,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}
			if(r>1){
			cellRangeAddress=new CellRangeAddress(27,27,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}
			rb=null;md=null;ip=null;xd=null;dj=null;
			rbTZ=null;mdTZ=null;ipTZ=null;xdTZ=null;djTZ=null;
			
			//湖南加杰
			List<Webestproduct>	proXw =estProSer.selByFactNoAndYymm("XW", yymm,"zd");
			List<Webestproduct>	proXwTZ =estProSer.selByFactNoAndYymm("XW", yymm,"tz");
			row=sheet.createRow(28);
			row.createCell(0).setCellValue("湖南加杰");
			row.createCell(1).setCellValue("RB");
			TillClass(row,null,proXw,0);
			TillClass(row,null,proXwTZ,0);
			cellRangeAddress=new CellRangeAddress(29,29,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(29);
			row.createCell(0).setCellValue("小計");
			TillClass(row,null,proXw,1);
			TillClass(row,null,proXwTZ,1);
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int r=0;r<20;r++){
				cellRangeAddress=new CellRangeAddress(28,28,r,r);
				setRegionBorder2(border, cellRangeAddress, sheet, workbook);
				if(r>1){
				cellRangeAddress=new CellRangeAddress(29,29,r,r);
				setRegionBorder2(border, cellRangeAddress, sheet, workbook);
				}
			}
			
			//越南加元
			List<Webestproduct>	pro657 =estProSer.selByFactNoAndYymm("657", yymm,"zd");
			List<Webestproduct>	pro657TZ =estProSer.selByFactNoAndYymm("657", yymm,"tz");
			fenClas(pro657);
			fenClas(pro657TZ);
			cellRangeAddress=new CellRangeAddress(30,31,0,0);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(30);
			row.createCell(0).setCellValue("越南加元");
			row.createCell(1).setCellValue("RB");
			TillClass(row, rb,null,0);
			TillClass(row, rbTZ,null,0);
			row=sheet.createRow(31);
			row.createCell(1).setCellValue("MD");
			TillClass(row, md,null,0);
			TillClass(row, mdTZ,null,0);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(32,32,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(32);
			row.createCell(0).setCellValue("小計");
			row.createCell(2).setCellValue(((rb==null)?0:rb.getMachinepower())+((md==null)?0:md.getMachinepower()));
			row.createCell(3).setCellValue(((rb==null)?0:rb.getEstdays())+((md==null)?0:md.getEstdays()));
			row.createCell(4).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel()));
			row.createCell(5).setCellValue(((rb==null)?0:rb.getEstnum())+((md==null)?0:md.getEstnum()));
			row.createCell(6).setCellValue(format(((rb==null)?0:rb.getEstmodel()/rb.getMachinepower())+((md==null)?0:md.getEstmodel()/md.getMachinepower()),2));
			row.createCell(7).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel()));
			row.createCell(8).setCellValue(((rb==null)?0:rb.getEsteverypeople())+((md==null)?0:md.getEsteverypeople()));
			row.createCell(9).setCellValue(((rb==null)?0:rb.getEstpay())+((md==null)?0:md.getEstpay()));
			row.createCell(10).setCellValue(((rb==null)?0:rb.getEstmoney())+((md==null)?0:md.getEstmoney()));
			row.createCell(11).setCellValue(((rbTZ==null)?0:rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getMachinepower()));
			row.createCell(12).setCellValue(((rbTZ==null)?0:rbTZ.getEstdays())+((mdTZ==null)?0:mdTZ.getEstdays()));
			row.createCell(13).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel()));
			row.createCell(14).setCellValue(((rbTZ==null)?0:rbTZ.getEstnum())+((mdTZ==null)?0:mdTZ.getEstnum()));
			row.createCell(15).setCellValue(format(((rbTZ==null)?0:rbTZ.getEstmodel()/rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getEstmodel()/mdTZ.getMachinepower()),2));
			row.createCell(16).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel()));
			row.createCell(17).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverypeople())+((mdTZ==null)?0:mdTZ.getEsteverypeople()));
			row.createCell(18).setCellValue(((rbTZ==null)?0:rbTZ.getEstpay())+((mdTZ==null)?0:mdTZ.getEstpay()));
			row.createCell(19).setCellValue(((rbTZ==null)?0:rbTZ.getEstmoney())+((mdTZ==null)?0:mdTZ.getEstmoney()));
			
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int r=0;r<20;r++){
			if(r>0){
			cellRangeAddress=new CellRangeAddress(30,30,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(31,31,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			if(r>1){
			cellRangeAddress=new CellRangeAddress(32,32,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}
			rb=null;md=null;rbTZ=null;mdTZ=null;
			
			//印尼加元
			List<Webestproduct>	proB055 =estProSer.selByFactNoAndYymm("B055", yymm,"zd");
			List<Webestproduct>	proB055TZ =estProSer.selByFactNoAndYymm("B055", yymm,"tz");
			fenClas(proB055);
			fenClas(proB055TZ);
			cellRangeAddress=new CellRangeAddress(33,35,0,0);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(33);
			row.createCell(0).setCellValue("印尼加元");
			row.createCell(1).setCellValue("RB");
			TillClass(row, rb,null,0);
			TillClass(row, rbTZ,null,0);
			row=sheet.createRow(34);
			row.createCell(1).setCellValue("MD");
			TillClass(row, md,null,0);
			TillClass(row, mdTZ,null,0);
			row=sheet.createRow(35);
			row.createCell(1).setCellValue("加硫鞋");
			TillClass(row, jlx,null,0);
			TillClass(row, jlxTZ,null,0);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(36,36,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(36);
			row.createCell(0).setCellValue("小計");
			row.createCell(2).setCellValue(((rb==null)?0:rb.getMachinepower())+((md==null)?0:md.getMachinepower())+((jlx==null)?0:jlx.getMachinepower()));
			row.createCell(3).setCellValue(((rb==null)?0:rb.getEstdays())+((md==null)?0:md.getEstdays())+((jlx==null)?0:jlx.getEstdays()));
			row.createCell(4).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel())+((jlx==null)?0:jlx.getEsteverymodel()));
			row.createCell(5).setCellValue(((rb==null)?0:rb.getEstnum())+((md==null)?0:md.getEstnum())+((jlx==null)?0:jlx.getEstnum()));
			row.createCell(6).setCellValue(format(((rb==null)?0:rb.getEstmodel()/rb.getMachinepower())+((md==null)?0:md.getEstmodel()/md.getMachinepower())+((jlx==null)?0:jlx.getEstmodel()/jlx.getMachinepower()),2));
			row.createCell(7).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel())+((jlx==null)?0:jlx.getEsteverymodel()));
			row.createCell(8).setCellValue(((rb==null)?0:rb.getEsteverypeople())+((md==null)?0:md.getEsteverypeople())+((jlx==null)?0:jlx.getEsteverypeople()));
			row.createCell(9).setCellValue(((rb==null)?0:rb.getEstpay())+((md==null)?0:md.getEstpay())+((jlx==null)?0:jlx.getEstpay()));
			row.createCell(10).setCellValue(((rb==null)?0:rb.getEstmoney())+((md==null)?0:md.getEstmoney())+((jlx==null)?0:jlx.getEstmoney()));
			row.createCell(11).setCellValue(((rbTZ==null)?0:rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getMachinepower())+((jlxTZ==null)?0:jlxTZ.getMachinepower()));
			row.createCell(12).setCellValue(((rbTZ==null)?0:rbTZ.getEstdays())+((mdTZ==null)?0:mdTZ.getEstdays())+((jlxTZ==null)?0:jlxTZ.getEstdays()));
			row.createCell(13).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel())+((jlxTZ==null)?0:jlxTZ.getEsteverymodel()));
			row.createCell(14).setCellValue(((rbTZ==null)?0:rbTZ.getEstnum())+((mdTZ==null)?0:mdTZ.getEstnum())+((jlxTZ==null)?0:jlxTZ.getEstnum()));
			row.createCell(15).setCellValue(format(((rbTZ==null)?0:rbTZ.getEstmodel()/rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getEstmodel()/mdTZ.getMachinepower())+((jlxTZ==null)?0:jlxTZ.getEstmodel()/jlx.getMachinepower()),2));
			row.createCell(16).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel())+((jlxTZ==null)?0:jlxTZ.getEsteverymodel()));
			row.createCell(17).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverypeople())+((mdTZ==null)?0:mdTZ.getEsteverypeople())+((jlxTZ==null)?0:jlxTZ.getEsteverypeople()));
			row.createCell(18).setCellValue(((rbTZ==null)?0:rbTZ.getEstpay())+((mdTZ==null)?0:mdTZ.getEstpay())+((jlxTZ==null)?0:jlxTZ.getEstpay()));
			row.createCell(19).setCellValue(((rbTZ==null)?0:rbTZ.getEstmoney())+((mdTZ==null)?0:mdTZ.getEstmoney())+((jlxTZ==null)?0:jlxTZ.getEstmoney()));
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int r=0;r<20;r++){
			if(r>0){
			cellRangeAddress=new CellRangeAddress(33,33,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(34,34,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(35,35,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			if(r>1){
			cellRangeAddress=new CellRangeAddress(36,36,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}
			rb=null;md=null;jlx=null;rbTZ=null;mdTZ=null;jlxTZ=null;
	
			//越南加能
			List<Webestproduct>	proTRVN =estProSer.selByFactNoAndYymm("TRVN", yymm,"zd");
			List<Webestproduct>	proTRVNTZ =estProSer.selByFactNoAndYymm("TRVN", yymm,"tz");
			fenClas(proTRVN);
			fenClas(proTRVNTZ);
			cellRangeAddress=new CellRangeAddress(37,38,0,0);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(37);
			row.createCell(0).setCellValue("越南加能");
			row.createCell(1).setCellValue("RB");
			TillClass(row, rb,null,0);
			TillClass(row, rbTZ,null,0);
			row=sheet.createRow(38);
			row.createCell(1).setCellValue("MD");
			TillClass(row, md,null,0);
			TillClass(row, mdTZ,null,0);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(39,39,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(39);
			row.createCell(0).setCellValue("小計");
			row.createCell(2).setCellValue(((rb==null)?0:rb.getMachinepower())+((md==null)?0:md.getMachinepower()));
			row.createCell(3).setCellValue(((rb==null)?0:rb.getEstdays())+((md==null)?0:md.getEstdays()));
			row.createCell(4).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel()));
			row.createCell(5).setCellValue(((rb==null)?0:rb.getEstnum())+((md==null)?0:md.getEstnum()));
			row.createCell(6).setCellValue(format(((rb==null)?0:rb.getEstmodel()/rb.getMachinepower())+((md==null)?0:md.getEstmodel()/md.getMachinepower()),2));
			row.createCell(7).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel()));
			row.createCell(8).setCellValue(((rb==null)?0:rb.getEsteverypeople())+((md==null)?0:md.getEsteverypeople()));
			row.createCell(9).setCellValue(((rb==null)?0:rb.getEstpay())+((md==null)?0:md.getEstpay()));
			row.createCell(10).setCellValue(((rb==null)?0:rb.getEstmoney())+((md==null)?0:md.getEstmoney()));
			row.createCell(11).setCellValue(((rbTZ==null)?0:rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getMachinepower()));
			row.createCell(12).setCellValue(((rbTZ==null)?0:rbTZ.getEstdays())+((mdTZ==null)?0:mdTZ.getEstdays()));
			row.createCell(13).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel()));
			row.createCell(14).setCellValue(((rbTZ==null)?0:rbTZ.getEstnum())+((mdTZ==null)?0:mdTZ.getEstnum()));
			row.createCell(15).setCellValue(format(((rbTZ==null)?0:rbTZ.getEstmodel()/rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getEstmodel()/mdTZ.getMachinepower()),2));
			row.createCell(16).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel()));
			row.createCell(17).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverypeople())+((mdTZ==null)?0:mdTZ.getEsteverypeople()));
			row.createCell(18).setCellValue(((rbTZ==null)?0:rbTZ.getEstpay())+((mdTZ==null)?0:mdTZ.getEstpay()));
			row.createCell(19).setCellValue(((rbTZ==null)?0:rbTZ.getEstmoney())+((mdTZ==null)?0:mdTZ.getEstmoney()));
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int r=0;r<20;r++){
			if(r>0){
			cellRangeAddress=new CellRangeAddress(37,37,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(38,38,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			if(r>1){
			cellRangeAddress=new CellRangeAddress(39,39,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}
			rb=null;md=null;rbTZ=null;mdTZ=null;
			//------------------------------------
			//加能二廠
			List<Webestproduct>	proFVAS =estProSer.selByFactNoAndYymm("FVAS", yymm,"zd");
			List<Webestproduct>	proFVASTZ =estProSer.selByFactNoAndYymm("FVAS", yymm,"tz");
			fenClas(proFVAS);
			fenClas(proFVASTZ);
			cellRangeAddress=new CellRangeAddress(40,42,0,0);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(40);
			row.createCell(0).setCellValue("加能二廠");
			row.createCell(1).setCellValue("RB");
			TillClass(row, rb,null,0);
			TillClass(row, rbTZ,null,0);
			row=sheet.createRow(41);
			row.createCell(1).setCellValue("MD");
		    TillClass(row, md,null,0);
		    TillClass(row, mdTZ,null,0);
		    row=sheet.createRow(42);
			row.createCell(1).setCellValue("鞋墊");
			TillClass(row, xd,null,0);
			TillClass(row, xdTZ,null,0);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(43,43,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(43);
			row.createCell(0).setCellValue("小計");
			row.createCell(2).setCellValue(((rb==null)?0:rb.getMachinepower())+((md==null)?0:md.getMachinepower())+((xd==null)?0:xd.getMachinepower()));
			row.createCell(3).setCellValue(((rb==null)?0:rb.getEstdays())+((md==null)?0:md.getEstdays())+((xd==null)?0:xd.getEstdays()));
			row.createCell(4).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel())+((xd==null)?0:xd.getEsteverymodel()));
			row.createCell(5).setCellValue(((rb==null)?0:rb.getEstnum())+((md==null)?0:md.getEstnum())+((xd==null)?0:xd.getEstnum()));
			row.createCell(6).setCellValue(format(((rb==null)?0:rb.getEstmodel()/rb.getMachinepower())+((md==null)?0:md.getEstmodel()/md.getMachinepower())+((xd==null)?0:xd.getEstmodel()/xd.getMachinepower()),2));
			row.createCell(7).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel())+((xd==null)?0:xd.getEsteverymodel()));
			row.createCell(8).setCellValue(((rb==null)?0:rb.getEsteverypeople())+((md==null)?0:md.getEsteverypeople())+((xd==null)?0:xd.getEsteverypeople()));
			row.createCell(9).setCellValue(((rb==null)?0:rb.getEstpay())+((md==null)?0:md.getEstpay())+((xd==null)?0:xd.getEstpay()));
			row.createCell(10).setCellValue(((rb==null)?0:rb.getEstmoney())+((md==null)?0:md.getEstmoney())+((xd==null)?0:xd.getEstmoney()));
			row.createCell(11).setCellValue(((rbTZ==null)?0:rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getMachinepower())+((xdTZ==null)?0:xdTZ.getMachinepower()));
			row.createCell(12).setCellValue(((rbTZ==null)?0:rbTZ.getEstdays())+((mdTZ==null)?0:mdTZ.getEstdays())+((xdTZ==null)?0:xdTZ.getEstdays()));
			row.createCell(13).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel())+((xdTZ==null)?0:xdTZ.getEsteverymodel()));
			row.createCell(14).setCellValue(((rbTZ==null)?0:rbTZ.getEstnum())+((mdTZ==null)?0:mdTZ.getEstnum())+((xdTZ==null)?0:xdTZ.getEstnum()));
			row.createCell(15).setCellValue(format(((rbTZ==null)?0:rbTZ.getEstmodel()/rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getEstmodel()/mdTZ.getMachinepower())+((xdTZ==null)?0:xdTZ.getEstmodel()/xdTZ.getMachinepower()),2));
			row.createCell(16).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel())+((xdTZ==null)?0:xdTZ.getEsteverymodel()));
			row.createCell(17).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverypeople())+((mdTZ==null)?0:mdTZ.getEsteverypeople())+((xdTZ==null)?0:xdTZ.getEsteverypeople()));
			row.createCell(18).setCellValue(((rbTZ==null)?0:rbTZ.getEstpay())+((mdTZ==null)?0:mdTZ.getEstpay())+((xdTZ==null)?0:xdTZ.getEstpay()));
			row.createCell(19).setCellValue(((rbTZ==null)?0:rbTZ.getEstmoney())+((mdTZ==null)?0:mdTZ.getEstmoney())+((xdTZ==null)?0:xdTZ.getEstmoney()));
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int r=0;r<20;r++){
			if(r>0){
			cellRangeAddress=new CellRangeAddress(40,40,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(41,41,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(42,42,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			if(r>1){
			cellRangeAddress=new CellRangeAddress(43,43,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}
			rb=null;md=null;jlx=null;rbTZ=null;mdTZ=null;jlxTZ=null;
			
			//越南福藝
			List<Webestproduct>	proFY =estProSer.selByFactNoAndYymm("FY", yymm,"zd");
			List<Webestproduct>	proFYTZ =estProSer.selByFactNoAndYymm("FY", yymm,"tz");
			fenClas(proFY);
			fenClas(proFYTZ);
			cellRangeAddress=new CellRangeAddress(44,46,0,0);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(44);
			row.createCell(0).setCellValue("越南福藝");
			row.createCell(1).setCellValue("RB");
			TillClass(row, rb,null,0);
			TillClass(row, rbTZ,null,0);
			row=sheet.createRow(45);
			row.createCell(1).setCellValue("MD");
			TillClass(row, md,null,0);
			TillClass(row, mdTZ,null,0);
			row=sheet.createRow(46);
			row.createCell(1).setCellValue("IP");
			TillClass(row, ip,null,0);
			TillClass(row, ipTZ,null,0);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(47,47,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(47);
			row.createCell(0).setCellValue("小計");
			row.createCell(2).setCellValue(((rb==null)?0:rb.getMachinepower())+((md==null)?0:md.getMachinepower())+((ip==null)?0:ip.getMachinepower()));
			row.createCell(3).setCellValue(((rb==null)?0:rb.getEstdays())+((md==null)?0:md.getEstdays())+((ip==null)?0:ip.getEstdays()));
			row.createCell(4).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel())+((ip==null)?0:ip.getEsteverymodel()));
			row.createCell(5).setCellValue(((rb==null)?0:rb.getEstnum())+((md==null)?0:md.getEstnum())+((ip==null)?0:ip.getEstnum()));
			row.createCell(6).setCellValue(format(((rb==null)?0:rb.getEstmodel()/rb.getMachinepower())+((md==null)?0:md.getEstmodel()/md.getMachinepower())+((ip==null)?0:ip.getEstmodel()/ip.getMachinepower()),2));
			row.createCell(7).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel())+((ip==null)?0:ip.getEsteverymodel()));
			row.createCell(8).setCellValue(((rb==null)?0:rb.getEsteverypeople())+((md==null)?0:md.getEsteverypeople())+((ip==null)?0:ip.getEsteverypeople()));
			row.createCell(9).setCellValue(((rb==null)?0:rb.getEstpay())+((md==null)?0:md.getEstpay())+((ip==null)?0:ip.getEstpay()));
			row.createCell(10).setCellValue(((rb==null)?0:rb.getEstmoney())+((md==null)?0:md.getEstmoney())+((ip==null)?0:ip.getEstmoney()));
			row.createCell(11).setCellValue(((rbTZ==null)?0:rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getMachinepower())+((ipTZ==null)?0:ipTZ.getMachinepower()));
			row.createCell(12).setCellValue(((rbTZ==null)?0:rbTZ.getEstdays())+((mdTZ==null)?0:mdTZ.getEstdays())+((ipTZ==null)?0:ipTZ.getEstdays()));
			row.createCell(13).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel())+((ipTZ==null)?0:ipTZ.getEsteverymodel()));
			row.createCell(14).setCellValue(((rbTZ==null)?0:rbTZ.getEstnum())+((mdTZ==null)?0:mdTZ.getEstnum())+((ipTZ==null)?0:ipTZ.getEstnum()));
			row.createCell(15).setCellValue(format(((rbTZ==null)?0:rbTZ.getEstmodel()/rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getEstmodel()/mdTZ.getMachinepower())+((ipTZ==null)?0:ipTZ.getEstmodel()/ipTZ.getMachinepower()),2));
			row.createCell(16).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel())+((ipTZ==null)?0:ipTZ.getEsteverymodel()));
			row.createCell(17).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverypeople())+((mdTZ==null)?0:mdTZ.getEsteverypeople())+((ipTZ==null)?0:ipTZ.getEsteverypeople()));
			row.createCell(18).setCellValue(((rbTZ==null)?0:rbTZ.getEstpay())+((mdTZ==null)?0:mdTZ.getEstpay())+((ipTZ==null)?0:ipTZ.getEstpay()));
			row.createCell(19).setCellValue(((rbTZ==null)?0:rbTZ.getEstmoney())+((mdTZ==null)?0:mdTZ.getEstmoney())+((ipTZ==null)?0:ipTZ.getEstmoney()));
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int r=0;r<20;r++){
			if(r>0){
			cellRangeAddress=new CellRangeAddress(44,44,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(45,45,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(46,46,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			if(r>1){
			cellRangeAddress=new CellRangeAddress(47,47,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}
			rb=null;md=null;ip=null;rbTZ=null;mdTZ=null;ipTZ=null;
			
			//北越加久
			List<Webestproduct>	proGH =estProSer.selByFactNoAndYymm("GH", yymm,"zd");
			List<Webestproduct>	proGHTZ =estProSer.selByFactNoAndYymm("GH", yymm,"tz");
			fenClas(proGH);
			fenClas(proGHTZ);
			cellRangeAddress=new CellRangeAddress(48,51,0,0);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(48);
			row.createCell(0).setCellValue("北越加久");
			row.createCell(1).setCellValue("RB");
			TillClass(row, rb,null,0);
			TillClass(row, rbTZ,null,0);
			row=sheet.createRow(49);
			row.createCell(1).setCellValue("MD");
			TillClass(row, md,null,0);
			TillClass(row, mdTZ,null,0);
	 		row=sheet.createRow(50);
			row.createCell(1).setCellValue("IP");
			TillClass(row, ip,null,0);
			TillClass(row, ipTZ,null,0);
			row= sheet.createRow(51);
			row.createCell(1).setCellValue("PU");
			TillClass(row, pu,null,0);
			TillClass(row, puTZ,null,0);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(52,52,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(52);
			row.createCell(0).setCellValue("小計");
			row.createCell(2).setCellValue(((rb==null)?0:rb.getMachinepower())+((md==null)?0:md.getMachinepower())+((ip==null)?0:ip.getMachinepower())+((pu==null)?0:pu.getMachinepower()));
			row.createCell(3).setCellValue(((rb==null)?0:rb.getEstdays())+((md==null)?0:md.getEstdays())+((ip==null)?0:ip.getEstdays())+((pu==null)?0:pu.getEstdays()));
			row.createCell(4).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel())+((ip==null)?0:ip.getEsteverymodel())+((pu==null)?0:pu.getEsteverymodel()));
			row.createCell(5).setCellValue(((rb==null)?0:rb.getEstnum())+((md==null)?0:md.getEstnum())+((ip==null)?0:ip.getEstnum())+((pu==null)?0:pu.getEstnum()));
			row.createCell(6).setCellValue(format(((rb==null)?0:rb.getEstmodel()/rb.getMachinepower())+((md==null)?0:md.getEstmodel()/md.getMachinepower())+((ip==null)?0:ip.getEstmodel()/ip.getMachinepower())+((pu==null)?0:pu.getEstmodel()/pu.getMachinepower()),2));
			row.createCell(7).setCellValue(((rb==null)?0:rb.getEsteverymodel())+((md==null)?0:md.getEsteverymodel())+((ip==null)?0:ip.getEsteverymodel())+((pu==null)?0:pu.getEsteverymodel()));
			row.createCell(8).setCellValue(((rb==null)?0:rb.getEsteverypeople())+((md==null)?0:md.getEsteverypeople())+((ip==null)?0:ip.getEsteverypeople())+((pu==null)?0:pu.getEsteverypeople()));
			row.createCell(9).setCellValue(((rb==null)?0:rb.getEstpay())+((md==null)?0:md.getEstpay())+((ip==null)?0:ip.getEstpay())+((pu==null)?0:pu.getEstpay()));
			row.createCell(10).setCellValue(((rb==null)?0:rb.getEstmoney())+((md==null)?0:md.getEstmoney())+((ip==null)?0:ip.getEstmoney())+((pu==null)?0:pu.getEstmoney()));
			row.createCell(11).setCellValue(((rbTZ==null)?0:rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getMachinepower())+((ipTZ==null)?0:ipTZ.getMachinepower())+((puTZ==null)?0:puTZ.getMachinepower()));
			row.createCell(12).setCellValue(((rbTZ==null)?0:rbTZ.getEstdays())+((mdTZ==null)?0:mdTZ.getEstdays())+((ipTZ==null)?0:ipTZ.getEstdays())+((puTZ==null)?0:puTZ.getEstdays()));
			row.createCell(13).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel())+((ipTZ==null)?0:ipTZ.getEsteverymodel())+((puTZ==null)?0:puTZ.getEsteverymodel()));
			row.createCell(14).setCellValue(((rbTZ==null)?0:rbTZ.getEstnum())+((mdTZ==null)?0:mdTZ.getEstnum())+((ipTZ==null)?0:ipTZ.getEstnum())+((puTZ==null)?0:puTZ.getEstnum()));
			row.createCell(15).setCellValue(format(((rbTZ==null)?0:rbTZ.getEstmodel()/rbTZ.getMachinepower())+((mdTZ==null)?0:mdTZ.getEstmodel()/mdTZ.getMachinepower())+((ipTZ==null)?0:ipTZ.getEstmodel()/ipTZ.getMachinepower())+((puTZ==null)?0:puTZ.getEstmodel()/puTZ.getMachinepower()),2));
			row.createCell(16).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverymodel())+((mdTZ==null)?0:mdTZ.getEsteverymodel())+((ipTZ==null)?0:ipTZ.getEsteverymodel())+((puTZ==null)?0:puTZ.getEsteverymodel()));
			row.createCell(17).setCellValue(((rbTZ==null)?0:rbTZ.getEsteverypeople())+((mdTZ==null)?0:mdTZ.getEsteverypeople())+((ipTZ==null)?0:ipTZ.getEsteverypeople())+((puTZ==null)?0:puTZ.getEsteverypeople()));
			row.createCell(18).setCellValue(((rbTZ==null)?0:rbTZ.getEstpay())+((mdTZ==null)?0:mdTZ.getEstpay())+((ipTZ==null)?0:ipTZ.getEstpay())+((puTZ==null)?0:puTZ.getEstpay()));
			row.createCell(19).setCellValue(((rbTZ==null)?0:rbTZ.getEstmoney())+((mdTZ==null)?0:mdTZ.getEstmoney())+((ipTZ==null)?0:ipTZ.getEstmoney())+((puTZ==null)?0:puTZ.getEstmoney()));
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int r=0;r<20;r++){
			if(r>0){
			cellRangeAddress=new CellRangeAddress(48,48,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(49,49,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(50,50,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(51,51,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			if(r>1){
			cellRangeAddress=new CellRangeAddress(52,52,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}
			rb=null;md=null;ip=null;rbTZ=null;mdTZ=null;ipTZ=null;pu=null;puTZ=null;
			
			//分類合計
			cellRangeAddress=new CellRangeAddress(53,59,0,0);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(53);
			row.createCell(0).setCellValue("分類合計");
			row.createCell(1).setCellValue("RB");
			fenLeiXioaji(row, "rbf");
			fenLeiXioaji(row, "rbTZf");
			row=sheet.createRow(54);
			row.createCell(1).setCellValue("MD");
			fenLeiXioaji(row, "mdf");
			fenLeiXioaji(row, "mdTZf");
			row=sheet.createRow(55);
			row.createCell(1).setCellValue("EVA");
			fenLeiXioaji(row, "evaf");
			fenLeiXioaji(row, "evaTZf");
			row=sheet.createRow(56);
			row.createCell(1).setCellValue("IP");
			fenLeiXioaji(row, "ipf");
			fenLeiXioaji(row, "ipTZf");
			row=sheet.createRow(57);
			row.createCell(1).setCellValue("PU");
			fenLeiXioaji(row, "puf");
			fenLeiXioaji(row, "puTZf");
			row=sheet.createRow(58);
			row.createCell(1).setCellValue("DJ");
			fenLeiXioaji(row, "djf");
			fenLeiXioaji(row, "djTZf");
			row=sheet.createRow(59);
			row.createCell(1).setCellValue("加硫鞋");
			fenLeiXioaji(row, "jlxf");
			fenLeiXioaji(row, "jlxTZf");
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			cellRangeAddress=new CellRangeAddress(60,60,0,1);
			sheet.addMergedRegion(cellRangeAddress);
			row=sheet.createRow(60);
			row.createCell(0).setCellValue("總計");
			sum(row);
			setRegionBorder1(border, cellRangeAddress, sheet, workbook);
			for(int r=0;r<20;r++){
			if(r>0){
				for(int w=53;w<60;w++){
			cellRangeAddress=new CellRangeAddress(w,w,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
				}
			}
			if(r>1){
			cellRangeAddress=new CellRangeAddress(60,60,r,r);
			setRegionBorder2(border, cellRangeAddress, sheet, workbook);
			}
			}

			// 新建一输出文件流
			//FileOutputStream fOut = new FileOutputStream(os);
			// 把相应的Excel 工作簿存盘
			workbook.write(os);
			os.flush();
			// 操作结束，关闭文件
			os.close();
			
			System.out.println("文件生成...");
		} catch (Exception e) {
			System.out.println("已运行 xlCreate() : " + e);
		}
		return "ok";
	}

}

