/**
 * 
 */
package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;

import entity.KyzExpectmatm;
import entity.WebUser;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IgnoreFieldProcessorImpl   
 * 类描述： 忽略属性
 * 忽略JAVABEAN的指定属性、是否忽略集合类属性
 * 创建人：Administrator   
 * 创建时间：2017/2/11 上午10:56:25   
 * 修改人：Administrator   
 * 修改时间：2017/2/11 上午10:56:25   
 * 修改备注：   
 * @version    
 *    
 **/
public class IgnoreFieldProcessorImpl implements PropertyFilter{

	Log log = LogFactory.getLog(this.getClass());	 			 
	private String[] fields;//忽略的属性名称	 
	private boolean ignoreColl = false;//是否忽略集合	true:忽略             false:不忽略
	public String[] getFields() {
		return fields;
	} 	
	public void setFields(String[] fields) {
		this.fields = fields;
	}
	public boolean isIgnoreColl() {
		return ignoreColl;
	}	
	public void setIgnoreColl(boolean ignoreColl) {
		this.ignoreColl = ignoreColl;
	}
	
	/**
	 * 空参构造方法<br/>
	 * 默认不忽略集合
	 */
	public IgnoreFieldProcessorImpl() {
		// empty
	}
 
	/**
	 * 构造方法
	 * @param fields 忽略属性名称数组
	 */
	public IgnoreFieldProcessorImpl(String[] fields) {
		this.fields = fields; 
	}
	
	/**
	 * 构造方法
	 * @param ignoreColl 是否忽略集合
	 */
	public IgnoreFieldProcessorImpl(boolean ignoreColl) {
		this.ignoreColl = ignoreColl; 
	}
 
	/**
	 * 构造方法
	 * @param ignoreColl	是否忽略集合
	 * @param fields	忽略属性名称数组
	 */
	public IgnoreFieldProcessorImpl(boolean ignoreColl, String[] fields) {
		this.fields = fields;
		this.ignoreColl = ignoreColl; 
	}
 
	
 
	public boolean apply(Object source, String name, Object value) {
		Field declaredField=null;
		//忽略值为null的属性
		if(value == null){
			return true;
		}
		try {
			if(source.getClass().getName().contains("entity.")){
				declaredField=source.getClass().getDeclaredField(name);
			}			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
								   
		// 忽略集合
		if (declaredField != null) {
			if(ignoreColl) {
				if(declaredField.getType() == Collection.class|| declaredField.getType() == Set.class) {					
					return true;
				}
			}
		}
 
		// 忽略设定的属性
		if(fields != null && fields.length > 0) {
			if(juge(fields,name)) {  
	            return true;  
	        } else {  
	            return false;  
	        } 
		}
		 
		return false;
	}
	/**
	 * 过滤忽略的属性
	 * @param s
	 * @param s2
	 * @return
	 */
	 public boolean juge(String[] s,String s2){  
         boolean b = false;  
         for(String sl : s){  
             if(s2.equals(sl)){  
                 b=true;  
             }  
         }  
         return b;  
     } 
	 
	 public  static void main (String[]args){		 
			List<KyzExpectmatm>list=new ArrayList<KyzExpectmatm>();
			JsonConfig config = new JsonConfig();
			config.setJsonPropertyFilter(new IgnoreFieldProcessorImpl(new String[]{"kyzExpectmatses","list_file","list_logs","id","vbm","timeCreate"}));
			JSONArray jsons=JSONArray.fromObject(list,config);
			System.out.println(jsons);
	 }
	

}
