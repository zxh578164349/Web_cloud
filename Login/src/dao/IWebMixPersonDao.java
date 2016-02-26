package dao;

import java.util.List;

import util.PageBean;

import entity.KyzExpectmatmLog;
import entity.Webmix2;
import entity.Webmixperson;
import entity.WebmixpersonId;


public interface IWebMixPersonDao {
	// �s�W
		public void addWebMixPerson(Webmixperson mixperson);

		// �ھڱ��d�߼ƾ�
		public List<Webmixperson> selectMixperson(String factNo, String yymm, int page,
				int rows);

		// �d�ߥ����ƾڪ��`����
		public int totlePage(String factNo, String yymm);

		public Webmixperson findById(WebmixpersonId id);

		public List<Webmixperson> selByYymm(String yymm, String factNo);

		public void delete(WebmixpersonId id,KyzExpectmatmLog delLog);
		
		public Webmixperson selBycan(String factNo,java.util.Date date,String factCode);
		public PageBean findPageBean(int pageSize,int page,String factNo,String yymm,String yymm2);
		public List<Webmixperson> findByAny(String factNo,String beginDate,String endDate);
		public Webmixperson findById(String factNo,String factCode,String yymm);
		
		public List<Webmixperson> findByFactNoYm(String factNo,String yymm);
		
}
