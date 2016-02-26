package dao;

import entity.KyzExpectmatmLog;
import entity.KyzExpectmats;
import entity.KyzExpectmatsId;

public interface IKyzExpectmatmsDao {
	public KyzExpectmats findById(String factNo,String billNo, String itemNo);
	public void delete(String factNo,String billNo, String itemNo);

}
