/**
 * 
 */
package services;

import java.util.List;

import util.PageBean;
import entity.Weballobj;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWeballobjServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/3/24 下午1:10:30   
 * 修改人：Administrator   
 * 修改时间：2016/3/24 下午1:10:30   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWeballobjServices {
	public void addMore(List<Weballobj>list);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm,String yymm2);
	public List<Weballobj>findAllobj(String factNo,String yymm,String yymm2);

}
