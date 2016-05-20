/**
 * 
 */
package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Basedao;
import dao.IVWebprofitlossFactEveDao;
import entity.VWebprofitlossFactEve;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebprofitlossFactEveDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/5/10 下午1:40:37   
 * 修改人：Administrator   
 * 修改时间：2016/5/10 下午1:40:37   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebprofitlossFactEveDaoImpl extends Basedao implements IVWebprofitlossFactEveDao{

	/**
	 * 日期:2016/5/10
	 * 描述:
	 */
	
	
	public List<VWebprofitlossFactEve> findByYymm(String factNo,String yymm, String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from VWebprofitlossFactEve where 1=1 ");
		if(factNo!=null&&!factNo.equals("")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm>=:yymm");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and id.yymm<=:yymm2 ");
			map.put("yymm2", yymm2);
		}
		List<VWebprofitlossFactEve>list=super.getAllWithNoPage(hql.toString(), map);
		return list;
	}

	/**
	 * 日期:2016/5/10
	 * 描述:
	 */
	
	
	public List<VWebprofitlossFactEve> findByYymm(String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebprofitlossFactEve where id.yymm=?";
		String[]objs={yymm};
		return super.findAll(hql, objs);
	}

}
