/**
 * 
 */
package services;

import java.util.List;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebErpBrankProcessServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/1 下午1:17:26   
 * 修改人：Administrator   
 * 修改时间：2016/11/1 下午1:17:26   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebErpBrankProcessServices{
	/**
	 * 
	 * @Title: findsysno
	 * @Description: 查找所有製程類別與品牌(sysCode='OP-1':製程類別    sysCode='OP-2':品牌)
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/11/1
	 */
	public List<Object[]> findObj(String sysCode);

}
