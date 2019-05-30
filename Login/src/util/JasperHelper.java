package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class JasperHelper{
	private static Logger logger=Logger.getLogger(JasperHelper.class);
	public static final String PRINT_TYPE="print";
	public static final String PDF_TYPE="pdf";
	public static final String EXCEL_TYPE="excel";
	public static final String HTML_TYPE="html";
	public static final String WORD_TYPE="word";
	public static final String PDF_TYPE_AUTO="auto";
	public static final String PDF_TYPE_LINE="line";

	public static void prepareReport(JasperReport jasperReport,String type){
		logger.debug("The method======= prepareReport() start.......................");
		if ("excel".equals(type))
			try {
				Field margin=JRBaseReport.class.getDeclaredField("leftMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport,0);
				margin=JRBaseReport.class.getDeclaredField("topMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport,0);
				margin=JRBaseReport.class.getDeclaredField("bottomMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport,0);
				Field pageHeight=JRBaseReport.class.getDeclaredField("pageHeight");
				pageHeight.setAccessible(true);
				pageHeight.setInt(jasperReport,2147483647);
			} catch (Exception exception) {
			}
	}

	/**
	 * 导出excel
	 */
	public static void exportExcel(JasperPrint jasperPrint,String defaultFilename,HttpServletRequest request,HttpServletResponse response) throws IOException,
			JRException{
		logger.debug("执行导出excel  The method======= exportExcel() start.......................");
		response.setContentType("application/vnd.ms-excel");
		String defaultname=null;
		if (defaultFilename.trim() != null && defaultFilename != null) {
			defaultname=defaultFilename + ".xls";
		} else {
			defaultname="export.xls";
		}
		/*
		 * String fileName = new String(defaultname.getBytes("utf-8"), "ISO8859-1");
		 * response.setHeader("Content-disposition", "attachment; filename="
		 * + fileName);
		 */
		String fileName=defaultname;
		response.setHeader("Content-disposition","attachment; filename=" + java.net.URLEncoder.encode(fileName,"utf-8"));
		ServletOutputStream ouputStream=response.getOutputStream();
		 JRXlsExporter exporter = new JRXlsExporter();
		//JExcelApiExporter exporter=new JExcelApiExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,ouputStream);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);//解决单元格数据无法计算的问题20190529
		exporter.exportReport();
		ouputStream.flush();
		ouputStream.close();
	}

	private static void exportPdf(JasperPrint jasperPrint,String defaultFilename,HttpServletRequest request,HttpServletResponse response) throws IOException,
			JRException{
		response.setContentType("application/pdf");
		String defaultname=null;
		if (defaultFilename.trim() != null && defaultFilename != null) {
			defaultname=defaultFilename + ".pdf";
		} else {
			defaultname="export.pdf";
		}
		// String fileName = new String(defaultname.getBytes("utf-8"), "ISO8859-1");
		String fileName=defaultname;
		response.setHeader("Content-disposition","attachment; filename=" + java.net.URLEncoder.encode(fileName,"utf-8"));

		ServletOutputStream ouputStream=response.getOutputStream();
		// OutputStream ouputStream=new FileOutputStream("D:/" + defaultname + ".xls");
		JasperExportManager.exportReportToPdfStream(jasperPrint,ouputStream);
		ouputStream.flush();
		ouputStream.close();

	}

	public static void exportPdf_Line(JasperPrint jasperPrint,String defaultFilename,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("application/pdf");
		String defaultname=null;
		if (defaultFilename.trim() != null && defaultFilename != null) {
			defaultname=defaultFilename + ".pdf";
		} else {
			defaultname="export.pdf";
		}
		// String fileName = new String(defaultname.getBytes("utf-8"), "ISO8859-1");
		String fileName=defaultname;
		response.setHeader("Content-disposition","inline; filename=" + java.net.URLEncoder.encode(fileName,"utf-8"));

		ServletOutputStream ouputStream=response.getOutputStream();
		// OutputStream ouputStream=new FileOutputStream("D:/" + defaultname + ".xls");
		JasperExportManager.exportReportToPdfStream(jasperPrint,ouputStream);
		ouputStream.flush();
		ouputStream.close();

	}

	private static void exportPdf_auto(JasperPrint jasperPrint,String defaultFilename,HttpServletRequest request,HttpServletResponse response)
			throws IOException,JRException{
		response.setContentType("application/pdf");
		String defaultname=null;
		if (defaultFilename.trim() != null && defaultFilename != null) {
			defaultname=defaultFilename + ".pdf";
		} else {
			defaultname="export.pdf";
		}
		String fileName=new String(defaultname.getBytes("utf-8"),"ISO8859-1");
		/*
		 * response.setHeader("Content-disposition", "attachment; filename="
		 * + fileName);
		 */

		// ServletOutputStream ouputStream = response.getOutputStream();
		OutputStream ouputStream=new FileOutputStream("D:/" + defaultname);
		JasperExportManager.exportReportToPdfStream(jasperPrint,ouputStream);
		ouputStream.flush();
		ouputStream.close();

	}

	/**
	 * 导出html
	 */
	private static void exportHtml(JasperPrint jasperPrint,String defaultFilename,HttpServletRequest request,HttpServletResponse response) throws IOException,
			JRException{
		response.setContentType("text/html");
		ServletOutputStream ouputStream=response.getOutputStream();
		JRHtmlExporter exporter=new JRHtmlExporter();

		/*
		 * //5.设置图片文件存放路径，此路径为服务器上的绝对路径
		 * String imageDIR = ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/";
		 * exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, imageDIR);
		 * //6.设置图片请求URI
		 * String imageURI = ServletActionContext.getRequest().getRealPath("/jasper/audit/images/")+ "/";
		 * exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageURI);
		 * exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
		 */

		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,ouputStream);

		exporter.exportReport();
		ouputStream.flush();
		ouputStream.close();
	}

	/**
	 * 导出word
	 */
	private static void exportWord(JasperPrint jasperPrint,String defaultFilename,HttpServletRequest request,HttpServletResponse response) throws JRException,
			IOException{
		response.setContentType("application/msword;charset=utf-8");
		String defaultname=null;
		if (defaultFilename.trim() != null && defaultFilename != null) {
			defaultname=defaultFilename + ".doc";
		} else {
			defaultname="export.doc";
		}
		String fileName=new String(defaultname.getBytes("utf-8"),"ISO8859-1");
		response.setHeader("Content-disposition","attachment; filename=" + fileName);
		JRExporter exporter=new JRRtfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
		exporter.exportReport();
	}

	private static void export(Collection datas,Map a,String type,String defaultFilename,InputStream is,HttpServletRequest request,HttpServletResponse response){
		logger.debug("导出判断     The method======= export() start.......................");
		try {
			JasperReport jasperReport=(JasperReport)JRLoader.loadObject(is);
			prepareReport(jasperReport,type);
			JRDataSource ds=new JRBeanCollectionDataSource(datas);
			// parameters.put("wheresql","and status='3'");
			/*
			 * parameters.put("wheresql",""); String diver =
			 * "oracle.jdbc.driver.OracleDriver"; String url =
			 * "jdbc:oracle:thin:@192.168.1.156:1521:orcl"; String username =
			 * "qqwcrm0625"; String password = "qqwcrm"; ReportDataSource
			 * datasource = new ReportDataSource(); datasource.setDiver(diver);
			 * datasource.setUrl(url); datasource.setUsername(username);
			 * datasource.setPassword(password); Connection
			 * con=getConnection(datasource);
			 */
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,a,ds);
			if (EXCEL_TYPE.equals(type)) {
				exportExcel(jasperPrint,defaultFilename,request,response);
			} else if (PDF_TYPE.equals(type)) {
				exportPdf(jasperPrint,defaultFilename,request,response);
			} else if (HTML_TYPE.equals(type)) {
				exportHtml(jasperPrint,defaultFilename,request,response);
			} else if (WORD_TYPE.equals(type)) {
				exportWord(jasperPrint,defaultFilename,request,response);
			} else if (PDF_TYPE_AUTO.equals(type)) {
				exportPdf_auto(jasperPrint,defaultFilename,request,response);
			} else if (PDF_TYPE_LINE.equals(type)) {
				exportPdf_Line(jasperPrint,defaultFilename,request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exportmain(String exportType,Map parameters,String jaspername,List lists,String defaultFilename,String url){
		logger.debug("进入导出    The method======= exportmain() start.......................");
		ActionContext ct=ActionContext.getContext();
		HttpServletRequest request=(HttpServletRequest)ct.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response=ServletActionContext.getResponse();
		String filenurl=null;
		filenurl=ServletActionContext.getRequest().getRealPath(url + jaspername);// jasper文件放在WebRoot/ireport/xx.jasper</span>

		File file=new File(filenurl);
		InputStream is=null;
		try {
			is=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/*
		 * byte[]bytes=File2byte(filenurl);
		 * is=new ByteArrayInputStream(bytes);
		 */
		export(lists,parameters,exportType,defaultFilename,is,request,response);
		if(is!=null){
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String encodingFileName(String fileName){

		String returnFileName="";
		try {
			returnFileName=URLEncoder.encode(fileName,"UTF-8");
			returnFileName=StringUtils.replace(returnFileName,"+","%20");
			if (returnFileName.length() > 150) {
				returnFileName=new String(fileName.getBytes("GB2312"),"ISO8859-1");
				returnFileName=StringUtils.replace(returnFileName," ","%20");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnFileName;
	}

	/**
	 * 文件流轉字節流
	 * 
	 * @Title: File2byte
	 * @Description: TODO
	 * @param @param filePath
	 * @param @return
	 * @return byte[]
	 * @throws
	 * @author web
	 * @date 2016/7/7
	 */
	public static byte[] File2byte(String filePath){
		byte[] buffer=null;
		try {
			File file=new File(filePath);
			FileInputStream fis=new FileInputStream(file);
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			byte[] b=new byte[1024];
			int n;
			while ((n=fis.read(b)) != -1) {
				bos.write(b,0,n);
			}
			fis.close();
			bos.close();
			buffer=bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
}