/**
 * 
 */
package action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entity.VWebuseless;
import entity.VWebuselessId;
import entity.WebFact;

import services.IVWebuselessServices;
import services.IWebFactServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebuselessAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 下午2:57:08   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 下午2:57:08   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebuselessAction {
	private IVWebuselessServices vwebuselessSer;
	private IWebFactServices webFactSer;
	private String yymm;
	private String yymm2;//上月日期

	
	public String getYymm2() {
		return yymm2;
	}

	public void setYymm2(String yymm2) {
		this.yymm2 = yymm2;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public void setVwebuselessSer(IVWebuselessServices vwebuselessSer) {
		this.vwebuselessSer = vwebuselessSer;
	}

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	
	public void print() throws ParseException{
		Calendar cal=Calendar.getInstance();
		DateFormat frm=new SimpleDateFormat();
		cal.setTime(frm.parse(yymm));
		cal.add(Calendar.MONTH, -1);
		yymm2=frm.format(cal.getTime());//上月日期		
		List<VWebuseless>list_all=new ArrayList<VWebuseless>();
		List<VWebuseless>list_all2=new ArrayList<VWebuseless>();
		List<WebFact>list_facts=webFactSer.findFactAble();
		List<VWebuseless>lists=vwebuselessSer.findByYymm(yymm);
		List<VWebuseless>lists2=vwebuselessSer.findByYymm(yymm2);
		
		
		for(WebFact fact:list_facts){
			list_all.add(new VWebuseless(new VWebuselessId(fact.getId().getFactNo(),fact.getId().getFactArea(),yymm)));
			list_all2.add(new VWebuseless(new VWebuselessId(fact.getId().getFactNo(),fact.getId().getFactArea(),yymm)));
		}
		//當月數據
		for(int a=0;a<list_all.size();a++){
			for(VWebuseless less_a:lists){
				if(list_all.get(a).getId().getFactNo().equals(less_a.getId().getFactNo())&&list_all.get(a).getId().getFactCode().equals(less_a.getId().getFactCode())){
					list_all.remove(a);
					list_all.add(a,less_a);
				}
			}
		}
		//上月數據
		for(int b=0;b<list_all2.size();b++){
			for(VWebuseless less_a:lists2){
				if(list_all.get(b).getId().getFactNo().equals(less_a.getId().getFactNo())&&list_all.get(b).getId().getFactCode().equals(less_a.getId().getFactCode())){
					list_all2.remove(b);
					list_all2.add(b,less_a);
				}
			}
		}
	}
	

}
