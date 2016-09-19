/**
 * 
 */
package services;

import java.util.List;

import util.PageBean;
import entity.WebydataNoinput;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebydataNoinputServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/8 下午4:14:01   
 * 修改人：Administrator   
 * 修改时间：2016/4/8 下午4:14:01   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebydataNoinputServices {
	public void addLarge(List<WebydataNoinput>list);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymmdd,String yymmdd2);
	public List<WebydataNoinput>print(String factNo,String yymmdd,String yymmdd2);

}
