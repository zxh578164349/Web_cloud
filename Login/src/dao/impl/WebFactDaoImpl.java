package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import dao.Basedao;
import dao.IWebFactDao;
import entity.VWebFact;
import entity.WebFact;

public class WebFactDaoImpl extends Basedao implements IWebFactDao {

	public List<WebFact> findAllFact() {
		// TODO Auto-generated method stub
		String hql = "select distinct id.factNo,factSname,orderNo from WebFact order by orderNo";
		return super.findAll(hql, null);
	}

	public List<WebFact> findFactById(String factNo) {
		// TODO Auto-generated method stub
		String hql = "from WebFact w where w.id. factNo=? order by fcodeIndex";
		String[] objs = { factNo };
		return super.findAll(hql, objs);
	}
	public List<WebFact> findFactById_showA(String factNo) {
		// TODO Auto-generated method stub
		String hql = "from WebFact w where w.id. factNo=? and factShowA='0' order by fcodeIndex";
		String[] objs = { factNo };
		return super.findAll(hql, objs);
	}
	public List<WebFact> findFactById_show(String factNo) {
		// TODO Auto-generated method stub
		String hql = "from WebFact w where w.id. factNo=? and factShow='0' order by fcodeIndex";
		String[] objs = { factNo };
		return super.findAll(hql, objs);
	}

	
	public String selByid(String id) {
		// TODO Auto-generated method stub
		String hql = "select distinct factSname from WebFact where id.factNo=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		String factname = (String) query.uniqueResult();
		return factname;
	}

	/**
	 * �Ҧ��t�O���A
	 */
	public List findAllFactCode() {
		// TODO Auto-generated method stub
		String hql = "select distinct id.factArea from WebFact order by id.factArea";
		return super.findAll(hql, null);
	}
	public List findAllFactCode_show() {
		// TODO Auto-generated method stub
		String hql = "select distinct id.factArea from WebFact where factShow='0' order by id.factArea";
		return super.findAll(hql, null);
	}

	public List findFactCodeByFactNo(String factNo) {
		// TODO Auto-generated method stub
		String hql = "select id.factArea from WebFact where id.factNo=?";
		String[] objs = { factNo };
		return super.findAll(hql, objs);
	}
	
	public List findFactCodeByFactNo_show(String factNo) {
		// TODO Auto-generated method stub
		String hql = "select id.factArea from WebFact where id.factNo=? and factShow='0'";//                            555555555555
		String[] objs = { factNo };
		return super.findAll(hql, objs);
	}
	public List findFactCodeByFactNo_show_dw(String factNo) {
		// TODO Auto-generated method stub
		String hql = "select id.factArea from WebFact where id.factNo=? and factDisable='0'";//                            555555555555
		String[] objs = { factNo };
		return super.findAll(hql, objs);
	}
	
	

	public String findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		String hql = "select distinct factCode from WebFact where id.factNo=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, factNo);
		return (String) query.uniqueResult();
	}

	public List<String> findFactNoByFactCode(String factCode) {
		// TODO Auto-generated method stub
		String hql="select id.factNo from WebFact where id.factArea=? order by id.factNo";
		String[]objs={factCode};
		return super.findAll(hql, objs);
	}

	public List<String> findFactNameByFactCode(String factCode) {
		// TODO Auto-generated method stub
		String hql="select factSname from WebFact where id.factArea=?";
		String[]objs={factCode};
		return super.findAll(hql, objs);
	}

	public List<WebFact> findFactByFactCode(String factCode) {
		// TODO Auto-generated method stub
		String hql="from WebFact where id.factArea=? order by factCode";
		String[]objs={factCode};
		return super.findAll(hql, objs);
	}
	public List<WebFact> findFactByFactCode_showA(String factCode) {
		// TODO Auto-generated method stub
		String hql="from WebFact where id.factArea=? and factShowA='0' order by factCode";
		String[]objs={factCode};
		return super.findAll(hql, objs);
	}

	public List<Object[]> findAllWebFact() {
		// TODO Auto-generated method stub
		String hql="select distinct id.factNo,factSname from WebFact order by id.factNo";
		return super.findAll(hql, null);
	}
	public List<Object[]> findAllWebFact_showA() {
		// TODO Auto-generated method stub
		String hql="select distinct id.factNo,factSname from WebFact where factShowA='0' order by id.factNo";
		return super.findAll(hql, null);
	}

	public List<Object[]> findAllFactCode2() {
		// TODO Auto-generated method stub
		String hql="select distinct id.factArea ,fcodeIndex from WebFact order by fcodeIndex";
		return super.findAll(hql, null);
	}
	
	/**
	 * 【KPI報表】,【分形態損益】,【經營評比】
	 * factShowA='0'
	 * 所有廠別狀態
	 * 日期:2016/4/19
	 * 描述:
	 */
	public List<Object[]> findAllFactCode2_showA() {
		// TODO Auto-generated method stub
		String hql="select distinct id.factArea ,fcodeIndex from WebFact where factShowA='0' order by fcodeIndex";
		return super.findAll(hql, null);
	}
	/**
	 * 【KPI報表】,【分形態損益】,【經營評比】
	 * factShowA='0'
	 * 所有廠別
	 * 日期:2016/4/19
	 * 描述:
	 */
	public List<WebFact>findAllFact_showA(){
		String hql="from WebFact where factShowA='0' order by orderNo,fcodeIndex";
		return super.findAll(hql, null);
	}
	
	public List<String> findAllFactNo(){
		String hql="select distinct id.factNo from WebFact order by id.factNo";
		return super.findAll(hql, null);
		
	}
	
    //�Ҧ��t�O�U�Ӽt�O���A���ߺD�Ƨ�
	public List<WebFact> findAllFact_2() {
		// TODO Auto-generated method stub
		String hql="from WebFact where factShow='0' order by orderNo,fcodeIndex";
		return super.findAll(hql, null);
	}

	/**
	 * 所有的工廠  20151231
	 * @return
	 */
	public List<Object[]> findAllFact_obj() {
		// TODO Auto-generated method stub
		String hql="select distinct id.factNo,factSname from WebFact";		
		return super.findAll(hql, null);
	}

	/**
	 * 所有的工廠  20151231
	 * @return
	 */
	public List<Object[]> findAllFactarea_obj() {
		// TODO Auto-generated method stub
		String hql="select distinct id.factArea from WebFact";
		return super.findAll(hql, null);
	}

	/**
	 * 根據用戶所屬的廠別，來加載廠別列錶
	 * 如果是臺灣（tw）用戶，加載所有的的廠別
	 * 如果是工廠用戶，加載所屬工廠
	 * 20160115
	 * @param factNo
	 * @return
	 */
	public List<Object[]> findFactByFactNo(String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select distinct id.factNo,factSname,orderNo from WebFact where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno ");
			map.put("factno", factNo);
		}
		hql.append(" order by orderNo");
		return super.getAllWithNoPage(hql.toString(), map);
	}

	/**
	 * 日期:2016/2/29
	 * 描述:
	 */
	
	
	public List<WebFact> findFactAble() {
		// TODO Auto-generated method stub
		String hql="from WebFact where factDisable='0' order by orderNo,fcodeIndex";
		return super.findAll(hql, null);
	}

	/**
	 * 日期:2016/2/29
	 * 描述:
	 */
	
	
	public List<Object[]> findFactAreaAbled() {
		// TODO Auto-generated method stub
		String hql="select distinct id.factArea ,fcodeIndex from WebFact where factDisable='0' order by fcodeIndex";
		return super.findAll(hql, null);
	}

	/**
	 * 日期:2016/3/28
	 * 描述:
	 */
	
	
	public List<String> findfactAreaByFactNo(String factNo) {
		// TODO Auto-generated method stub
		String hql="select id.factArea from WebFact where id.factNo=?";
		String[]objs={factNo};
		return super.findAll(hql, objs);
	}

	/**
	 * 日期:2016/4/1
	 * 描述:
	 */
	
	
	public List<WebFact> findAll() {
		// TODO Auto-generated method stub
		String hql="from WebFact where factShow='0' order by orderNo,fcodeIndex";
		return super.findAll(hql, null);
	}

	/**
	 * 【經營評比】報表
	 * 日期:2016/4/6
	 * 描述:
	 */	
	public List<Object[]> findFactAble2() {
		// TODO Auto-generated method stub
		String hql="select distinct id.factNo,factSname,orderNo from WebFact where factShowA='0' order by orderNo";
		return super.findAll(hql, null);
	}

	/**
	 * 日期:2016/5/5
	 * 描述:無序
	 */
	public List<String> findByFactNo_showA(String factNo) {
		// TODO Auto-generated method stub
		String hql="select id.factArea from WebFact where id.factNo=? and factShowA='0'";
		String[]objs={factNo};
		return super.findAll(hql, objs);
	}
	/**
	 * 日期:2016/5/5
	 * 描述:有序
	 */
	public List<Object[]> findByFactNo_showA_order(String factNo) {
		// TODO Auto-generated method stub
		String hql="select id.factArea,fcodeIndex from WebFact where id.factNo=? and factShowA='0' order by fcodeIndex";
		String[]objs={factNo};
		return super.findAll(hql, objs);
	}
	public List<Object[]> findByFactNo_show_order(String factNo) {
		// TODO Auto-generated method stub
		String hql="select id.factArea,fcodeIndex from WebFact where id.factNo=? and factShow='0' order by fcodeIndex";
		String[]objs={factNo};
		return super.findAll(hql, objs);
	}
	
	/**
	 * 日期:2016/10/14
	 * 描述:有序
	 */
	public List<Object[]> findByFactNo_order(String factNo) {
		// TODO Auto-generated method stub
		String hql="select id.factArea,fcodeIndex from WebFact where id.factNo=?  order by fcodeIndex";
		String[]objs={factNo};
		return super.findAll(hql, objs);
	}

	/**
	 * 日期:2016/6/7
	 * 描述:
	 */
	
	
	public List<WebFact> findByList(List<String> list) {
		// TODO Auto-generated method stub
		String hql="from WebFact where id.factNo in (:facts) order by factCode,fcodeIndex";
		Query query=getSession().createQuery(hql);
		query.setParameterList("facts", list);
		List<WebFact>list2=(List<WebFact>)query.list();
		return list2;
	}

	public List<String> findFactNoshow() {
		// TODO Auto-generated method stub
		String hql="select distinct id.factNo from WebFact where factShow='0'";
		return super.findAll(hql, null);
	}

	public List<String> findFactCodeshow() {
		// TODO Auto-generated method stub
		String hql="select distinct id.factArea from WebFact where factShow='0'";
		return super.findAll(hql, null);
	}

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public List<Object[]> findAllVwebfact(){
		// TODO Auto-generated method stub
		String hql="select factNo,factSname,orderNo,erpFactcode from VWebFact order by orderNo";
		return super.findAll(hql,null);
	}

	/**
	 * 日期:2017/7/26
	 * 描述:
	 */
	
	
	public List<String> findfactarea(){
		// TODO Auto-generated method stub
		String hql="select distinct id.factArea from WebFact where factShow='0'";
		return super.findAll(hql,null);
	}

	/**
	 * 日期:2017/7/28
	 * 描述:
	 */
	
	
	public List<Object[]> findFnoFcodeShow(){
		// TODO Auto-generated method stub
		String hql="select id.factNo,id.factArea,factSname,orderNo,fcodeIndex from WebFact where factShow='0' order by orderNo,fcodeIndex";		
		return super.findAll(hql,null);
	}
	
	public List<WebFact> findFactCodeByFactNo_disable(String factNo){
		String hql = "from WebFact where id.factNo=? and factDisable='0' order by fcodeIndex";//                            555555555555
		String[] objs = { factNo };
		return super.findAll(hql, objs);
	}

	public List<WebFact> findAsics(String asicsMk) {
		// TODO Auto-generated method stub
		String hql="from WebFact where asicsMk=?";
		String[]objs={asicsMk};
		return super.findAll(hql, objs);
	}

}
