/**
 * 
 */
package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Basedao;
import dao.IWebErpProductinFormationDao;
import entity.VWebErpProductinFormation;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebErpProductinFormationDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/1 上午11:15:25   
 * 修改人：Administrator   
 * 修改时间：2016/11/1 上午11:15:25   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebErpProductinFormationDaoImpl extends Basedao implements IWebErpProductinFormationDao{

	private final static String STATE="state=3 and status<>0";//篩選條件
	
	

	/**
	 * 日期:2016/11/3
	 * 描述:
	 */
	
	
	public List<Object[]> findNamece(String selfchar1){
		// TODO Auto-generated method stub
		String hql="select itemid,selfchar1Name,namec1,namec2 from WebErpProductinFormation where "+STATE+"  and selfchar1=? order by itemid";
		String objs[]={selfchar1};
		return super.findAll(hql,objs);
	}

	/**
	 * 日期:2016/11/7
	 * 描述:
	 */
	
	
	public List<VWebErpProductinFormation> findTypeNo(){
		// TODO Auto-generated method stub
		String hql="from VWebErpProductinFormation order by selfchar1";
		return super.findAll(hql,null);
	}

	/**
	 * 日期:2016/11/8
	 * 描述:
	 */
	
	
	public List<Object[]> findNamece(List<String> selfchar1s){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select itemid,selfchar1Name,namec1,namec2 from WebErpProductinFormation where 1=1 ");
		if(selfchar1s!=null&&selfchar1s.size()>0){
			hql.append(" and selfchar1 in(:selfchar1s) ");
			map.put("selfchar1s",selfchar1s);
		}
		hql.append("and "+STATE+" order by selfchar1Name, itemid ");
		return super.getAllWithNoPage(hql.toString(),map);
	}

	/**
	 * 日期:2017/7/12
	 * 描述:
	 */
	
	
	public List<Object[]> findItemcategory(){
		// TODO Auto-generated method stub
		String hql="select distinct itemcategory,itemcategoryname from WebErpProductinFormation order by itemcategory";
		
		return super.findAll(hql,null);
	}

	/**
	 * 日期:2017/7/12
	 * 描述:
	 */
	
	
	public List<Object[]> findNamece2(String itemcategory){
		// TODO Auto-generated method stub
		String hql="select itemid, namec1,namec2,itemcode from WebErpProductinFormation where itemcategory=? order by namec1,namec2";
		String[]objs={itemcategory};
		return super.findAll(hql,objs);
	}

}
