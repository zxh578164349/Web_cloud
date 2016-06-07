/**
 * 
 */
package dao;

import java.util.List;

import entity.WebVwebbussortItemn;
import entity.WebVwebussortSubitem;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebVwebbussortItemnDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/6/1 上午11:09:59   
 * 修改人：Administrator   
 * 修改时间：2016/6/1 上午11:09:59   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebVwebbussortItemnDao {
	public List<WebVwebbussortItemn>findAll();
	public List<WebVwebussortSubitem>findAll2();

}
