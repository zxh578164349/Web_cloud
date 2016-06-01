/**
 * 
 */
package services;

import java.util.List;

import entity.WebVwebbussortItemn;
import entity.WebVwebussortSubitem;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebVwebbussortItemnServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/6/1 上午11:13:26   
 * 修改人：Administrator   
 * 修改时间：2016/6/1 上午11:13:26   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebVwebbussortItemnServices {
	public List<WebVwebbussortItemn>findAll();
	public List<WebVwebussortSubitem>findAll2();

}
