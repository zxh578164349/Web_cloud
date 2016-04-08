/**
 * 
 */
package dao;

import java.util.List;

import util.PageBean;

import entity.WebydataNoinput;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebydataNoinputDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/8 下午3:37:45   
 * 修改人：Administrator   
 * 修改时间：2016/4/8 下午3:37:45   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebydataNoinputDao {
	public void addLarge(List<WebydataNoinput>list);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymmdd,String yymmdd2);

}
