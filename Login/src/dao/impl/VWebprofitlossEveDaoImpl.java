/**
 * 
 */
package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Basedao;
import dao.IVWebprofitlossEveDao;
import entity.VWebprofitlossEve;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebprofitlossEveDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/5/5 上午8:48:20   
 * 修改人：Administrator   
 * 修改时间：2016/5/5 上午8:48:20   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebprofitlossEveDaoImpl extends Basedao implements IVWebprofitlossEveDao{

	/**
	 * 日期:2016/5/5
	 * 描述:
	 */
	
	
	public List<VWebprofitlossEve> findByYymm(String factNo,String yymm, String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from VWebprofitlossEve where 1=1 ");
		if(factNo!=null&&!factNo.equals("")){
			hql.append(" and id .factNo=:factno");
			map.put("factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm>=:yymm");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and id.yymm<=:yymm2");
			map.put("yymm2", yymm2);
		}
		List<VWebprofitlossEve>list=super.getAllWithNoPage(hql.toString(), map);
		return list;
	}

	/**
	 * 日期:2016/5/9
	 * 描述:
	 */
	
	
	public List<VWebprofitlossEve> findByYymm(String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebprofitlossEve where id.yymm=?";
		String[]objs={yymm};
		return super.findAll(hql, objs);
	}

}
