/**
 * 
 */
package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Basedao;
import dao.IVWebbussortDao;
import entity.VWebbussort;
import entity.VWebbussortFcode;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebbussortDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/6 上午10:18:45   
 * 修改人：Administrator   
 * 修改时间：2016/4/6 上午10:18:45   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebbussortDaoImpl extends Basedao implements IVWebbussortDao{

	/**
	 * 日期:2016/4/6
	 * 描述:
	 */
	
	
	public List<VWebbussort> findByYymm(String yymm,String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from VWebbussort where 1=1 ");
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm>=:yymm ");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and id.yymm<=:yymm2 ");
			map.put("yymm2", yymm2);
		}
		
		return super.getAllWithNoPage(hql.toString(), map);
	}

	/**
	 * 日期:2016/4/19
	 * 描述:
	 */
	
	
	public List<VWebbussortFcode> findByYymm2(String yymm, String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from VWebbussortFcode where 1=1 ");
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm>=:yymm ");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and id.yymm<=:yymm2 ");
			map.put("yymm2", yymm2);
		}
		List<VWebbussortFcode>list=super.getAllWithNoPage(hql.toString(), map);
		for(VWebbussortFcode obj:list){
			obj.getId().getFact().getFactSname();
		}
		return list;
	}

}
