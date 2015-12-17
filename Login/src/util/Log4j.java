package util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.directwebremoting.util.Logger;

public class  Log4j {
	private static Logger logger=Logger.getLogger(Log4j.class);
	
	 public static String getTrace(Throwable t) {
	        StringWriter stringWriter= new StringWriter();
	        PrintWriter writer= new PrintWriter(stringWriter);
	        t.printStackTrace(writer);
	        StringBuffer buffer= stringWriter.getBuffer();
	        return buffer.toString();
	    }
	 
	 public static void getError(Exception e){		 
			logger.error(getTrace(e));
		 
	 }
	 
	 /*public static void main(String[] args){
		 try{
			 System.out.println(2/0);
		 }catch(Exception e){
			 getError(e);
		 }
	 }*/

}
