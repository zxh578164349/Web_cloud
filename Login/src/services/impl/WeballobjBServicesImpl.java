/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IWeballobjBDao;
import entity.KyzExpectmatmLog;
import entity.VWeballobjbStorage;
import entity.WeballobjB;
import services.IWeballobjBServices;
import util.PageBean;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WeballobjServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/3/24 下午1:11:14   
 * 修改人：Administrator   
 * 修改时间：2016/3/24 下午1:11:14   
 * 修改备注：   
 * @version    
 *    
 **/
public class WeballobjBServicesImpl implements IWeballobjBServices{
	private IWeballobjBDao weballobjbdao;
		
	public void setWeballobjbdao(IWeballobjBDao weballobjbdao) {
		this.weballobjbdao = weballobjbdao;
	}

	/**
	 * 日期:2016/3/24
	 * 描述:
	 */
	
	
	public void addMore(List<WeballobjB> list) {
		// TODO Auto-generated method stub
		weballobjbdao.addMore(list);
	}

	/**
	 * 日期:2016/3/24
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2) {
		// TODO Auto-generated method stub
		return weballobjbdao.findPageBean(pageSize, page, factNo, yymm, yymm2);
	}

	/**
	 * 日期:2016/4/21
	 * 描述:
	 */
	
	
	public List<WeballobjB> findAllobj(String factNo, String yymm, String yymm2) {
		// TODO Auto-generated method stub
		return weballobjbdao.findAllobj(factNo, yymm, yymm2);
	}

	/**
	 * 日期:2016/4/22
	 * 描述:
	 */
	
	
	public WeballobjB findById(String factNo, String factcode, String yymm) {
		// TODO Auto-generated method stub
		return weballobjbdao.findById(factNo, factcode, yymm);
	}

	/**
	 * 日期:2016/4/22
	 * 描述:
	 */
	
	
	public void delete(String factNo, String factCode, String yymm,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		weballobjbdao.delete(factNo, factCode, yymm,log);
	}

	/**
	 * 日期:2016/10/13
	 * 描述:
	 */
	
	
	public List<WeballobjB> findObj(String yymm,String yymm2){
		// TODO Auto-generated method stub
		return weballobjbdao.findObj(yymm,yymm2);
	}

	/**
	 * 日期:2017/1/17
	 * 描述:
	 */
	
	
	public List<String> findFactCodes(String factNo,String yymm,String yymm2){
		// TODO Auto-generated method stub
		return weballobjbdao.findFactCodes(factNo,yymm,yymm2);
	}

	public List<WeballobjB> findWeballobjB(List<String> list_factcode,
			String yymm) {
		// TODO Auto-generated method stub
		return weballobjbdao.findWeballobjB(list_factcode, yymm);
	}

	public List<WeballobjB> findobjA41(String yymm) {
		// TODO Auto-generated method stub
		return weballobjbdao.findobjA41(yymm);
	}

	public List<WeballobjB> findProDiff(String factNo, String yymm, String yymm2) {
		// TODO Auto-generated method stub
		return weballobjbdao.findProDiff(factNo, yymm, yymm2);
	}

	public List<VWeballobjbStorage> findStorage(String yymm) {
		// TODO Auto-generated method stub
		return weballobjbdao.findStorage(yymm);
	}	

}
