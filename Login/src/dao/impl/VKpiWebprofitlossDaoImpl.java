/**
 * 
 */
package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Basedao;
import dao.IVKpiWebprofitlossDao;
import entity.VKpiWebprofitloss;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VKpiWebprofitlossDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/9/6 下午1:53:33   
 * 修改人：Administrator   
 * 修改时间：2016/9/6 下午1:53:33   
 * 修改备注：   
 * @version    
 *    
 **/
public class VKpiWebprofitlossDaoImpl extends Basedao implements IVKpiWebprofitlossDao{

	/**
	 * 日期:2016/9/6
	 * 描述:工廠
	 */
	
	
	public List<VKpiWebprofitloss> findVKpiWebprofitloss(String factNo,String yymm,String yymm2){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from VKpiWebprofitloss where 1=1 ");
		if(factNo!=null&&!factNo.equals("")){
			hql.append(" and id.fact.id.factNo=:factno ");
			map.put("factno",factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm>=:yymm ");
			map.put("yymm",yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and id.yymm<=:yymm2 ");
			map.put("yymm2",yymm2);
		}
		List<VKpiWebprofitloss>list=super.getAllWithNoPage(hql.toString(),map);
		return list;
	}

	/**
	 * 日期:2016/9/8
	 * 描述:臺灣
	 */
	
	
	public List<VKpiWebprofitloss> findVKpiWebprofitloss(List<String> list_factcode,String yymm){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from VKpiWebprofitloss where 1=1 ");
		if(list_factcode!=null&&list_factcode.size()>0){
			hql.append(" and id.fact.id.factArea in (:list_factcode) ");
			map.put("list_factcode",list_factcode);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm=:yymm ");
			map.put("yymm",yymm);
		}
		List<VKpiWebprofitloss>list=super.getAllWithNoPage(hql.toString(),map);
		for(VKpiWebprofitloss obj:list){
			obj.getId().getFact().getFactSname();
		}
		return list;
	}

}
