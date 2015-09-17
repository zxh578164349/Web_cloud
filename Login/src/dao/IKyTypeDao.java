package dao;

import java.util.List;

import entity.KyType;

public interface IKyTypeDao {
	public List<KyType> findByTypeNo(String typeNo);
	public List<KyType> findByTypeNo2(String typeNo);
	public String getTypeSname(String typNo,String typeSno);

}
