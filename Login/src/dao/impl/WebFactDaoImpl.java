package dao.impl;

import java.util.List;

import org.hibernate.Query;

import dao.Basedao;
import dao.IWebFactDao;
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

	/**
	 * 廠別代號對應人廠名
	 */
	public String selByid(String id) {
		// TODO Auto-generated method stub
		String hql = "select distinct factSname from WebFact where id.factNo=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		String factname = (String) query.uniqueResult();
		return factname;
	}

	/**
	 * 所有廠別狀態
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
	public List<Object[]> findAllFactCode2_showA() {
		// TODO Auto-generated method stub
		String hql="select distinct id.factArea ,fcodeIndex from WebFact where factShowA='0' order by fcodeIndex";
		return super.findAll(hql, null);
	}
	public List<String> findAllFactNo(){
		String hql="select distinct id.factNo from WebFact order by id.factNo";
		return super.findAll(hql, null);
		
	}
	
    //所有廠別各個廠別狀態的習慣排序
	public List<WebFact> findAllFact_2() {
		// TODO Auto-generated method stub
		String hql="from WebFact where factShow='0' order by orderNo,fcodeIndex";
		return super.findAll(hql, null);
	}

}
