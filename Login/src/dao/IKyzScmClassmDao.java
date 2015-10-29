package dao;

import java.util.List;

public interface IKyzScmClassmDao {
	public List<Object[]> findBN();
	public List<Object[]> findMN(String bigNo);
	public List<Object[]> findSN(String middleNo);

}
