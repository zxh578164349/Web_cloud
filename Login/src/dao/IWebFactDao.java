package dao;

import java.util.List;

import entity.VWebFact;
import entity.WebFact;

public interface IWebFactDao {
	public List<WebFact> findAllFact();

	public List<WebFact> findFactById(String factNo);
	public List<WebFact> findFactById_showA(String factNo);
	public List<WebFact> findFactById_show(String factNo);

	public String selByid(String id);

	public List findAllFactCode();
	public List findAllFactCode_show();

	public List findFactCodeByFactNo(String factNo);
	public List findFactCodeByFactNo_show(String factNo);
	public List findFactCodeByFactNo_show_dw(String factNo);
	public List<WebFact> findFactCodeByFactNo_disable(String factNo);

	public String findByFactNo(String factNo);
	
	public List<String> findFactNoByFactCode(String factCode);
	public List<String> findFactNameByFactCode(String factCode);
	public List<WebFact> findFactByFactCode(String factCode);
	public List<WebFact> findFactByFactCode_showA(String factCode);
	
	public List<Object[]>findAllWebFact();
	public List<Object[]>findAllWebFact_showA();
	
	public List<Object[]>findAllFactCode2();
	
	/**
	 * 【KPI報表】,【分形態損益】,【經營評比】
	 * factShowA='0'
	 * 所有廠別狀態
	 * @Title: findAllFactCode2_showA
	 * @Description: TODO
	 * @param @return
	 * @return List<Object[]>
	 * @throws
	 * @author web
	 * @date 2016/4/19
	 */
	public List<Object[]>findAllFactCode2_showA();
	
	/**
	 * 【KPI報表】,【分形態損益】,【經營評比】
	 * factShowA='0'
	 * 所有廠別
	 * @Title: findAllFact_showA
	 * @Description: TODO
	 * @param @return
	 * @return List<WebFact>
	 * @throws
	 * @author web
	 * @date 2016/4/19
	 */
	public List<WebFact>findAllFact_showA();
	public List<String> findAllFactNo();
	
	//20150708 �Ҧ��t�O�U�Ӽt�O���A���ߺD�Ƨ�
	public List<WebFact> findAllFact_2();
	
	/**
	 * 所有的工廠  20151231
	 * @return
	 */
	public List<Object[]> findAllFact_obj();
	
	/**
	 * 所有的的廠別狀態   20151231
	 * @return
	 */
	public List<Object[]> findAllFactarea_obj();
	
	/**
	 * 根據用戶所屬的廠別，來加載廠別列錶
	 * 如果是臺灣（tw）用戶，加載所有的的廠別
	 * 如果是工廠用戶，加載所屬工廠
	 * 20160115
	 * @param factNo
	 * @return
	 */
	public List<Object[]>findFactByFactNo(String factNo);
	
	/**
	 * 所有有效的廠別
	 * @Title: findFactAble
	 * @Description: TODO
	 * @param @return
	 * @return List<WebFact>
	 * @throws
	 * @author web
	 * @date 2016/2/29
	 */
	public List<WebFact>findFactAble();
	
	/**
	 * 所有有效的廠別狀態
	 * @Title: findFactAbled
	 * @Description: TODO
	 * @param @return
	 * @return Object[]
	 * @throws
	 * @author web
	 * @date 2016/2/29
	 */
	public List<Object[]> findFactAreaAbled();
	
	/**
	 * 根據factNo找出各箇廠的factArea
	 * @Title: findfactAreaByFactNo
	 * @Description: TODO
	 * @param @param factNo
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2016/3/28
	 */
	public List<String>findfactAreaByFactNo(String factNo);
	
	/**
	 * 產量報表要顯示的廠別和廠狀態
	 * @Title: findAll
	 * @Description: TODO
	 * @param @return
	 * @return List<WebFact>
	 * @throws
	 * @author web
	 * @date 2016/4/1
	 */
	public List<WebFact>findAll();
	
	
	/**
	 * 所有有效的工廠（不重複的工廠）
	 * @Title: findFactAble2
	 * @Description: TODO
	 * @param @return
	 * @return List<Object[]>
	 * @throws
	 * @author web
	 * @date 2016/4/6
	 */
	public List<Object[]>findFactAble2();
	
	public List<String> findByFactNo_showA(String factNo);
	public List<Object[]> findByFactNo_showA_order(String factNo);
	public List<Object[]> findByFactNo_show_order(String factNo);
	public List<Object[]> findByFactNo_order(String factNo);
	
	public List<WebFact>findByList(List<String>list);
	
	public List<String>findFactNoshow();
	public List<String>findFactCodeshow();
	public List<String>findfactarea();
	public List<Object[]>findFnoFcodeShow();
	
	
	/****************************************************  VWebFact  ***********************************************************************/
	public List<Object[]>findAllVwebfact();
	
	
	
	
	
	/****************************************************  VWebFact  ***********************************************************************/

}
