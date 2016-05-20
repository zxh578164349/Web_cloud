/**
 * 
 */
package dao;

import java.util.List;

import entity.VWebuseless;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IVWebuselessDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 下午2:50:58   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 下午2:50:58   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IVWebuselessDao {
	public List<VWebuseless>findByYymm(String yymm);

}
