package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import services.IKyFactServices;
import services.IWebBackMatServices;
import services.IWebBackpfServices;
import services.IWebCpStoreServices;
import services.IWebGwServices;
import services.IWebMachineServices;
import services.IWebMats2Services;
import services.IWebMatsServices;
import services.IWebMatstoreServices;
import services.IWebOutbackServices;
import services.IWebOutputInvServices;
import services.IWebPersonServices;
import services.IWebPfavgPriceServices;
import services.IWebProfitCountServices;
import services.IWebProfitServices;
import services.IWebSideServices;
import services.IWebZwcashServices;
import util.DateHelper;
import util.JasperHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.WebBackmat;
import entity.WebBackpf;
import entity.WebCpstore;
import entity.WebGw;
import entity.WebMachin;
import entity.WebMats;
import entity.WebMats2;
import entity.WebMatstore;
import entity.WebOutback;
import entity.WebOutputinv;
import entity.WebPerson;
import entity.WebPfavgprice;
import entity.WebProfit;
import entity.WebProfitcount;
import entity.WebSide;
import entity.WebZwcash;

public class DataAction extends ActionSupport {
	private IWebMachineServices machineSer;
	private IWebProfitCountServices countSer;
	private IWebProfitServices profitSer;
	private IWebBackMatServices webBackmatSer;
	private IWebBackpfServices backpfSer;
	private IWebCpStoreServices storeSer;
	private IWebGwServices gwSer;
	private IWebMats2Services mats2Ser;
	private IWebMatsServices matSer;
	private IWebMatstoreServices mstoreSer;
	private IWebOutbackServices outSer;
	private IWebOutputInvServices invSer;
	private IWebPersonServices personSer;
	private IWebPfavgPriceServices favgSer;
	private IWebSideServices sideSer;
	private IWebZwcashServices cashSer;

	public void setMachineSer(IWebMachineServices machineSer) {
		this.machineSer = machineSer;
	}

	public void setCountSer(IWebProfitCountServices countSer) {
		this.countSer = countSer;
	}

	public void setProfitSer(IWebProfitServices profitSer) {
		this.profitSer = profitSer;
	}

	public void setWebBackmatSer(IWebBackMatServices webBackmatSer) {
		this.webBackmatSer = webBackmatSer;
	}

	public void setBackpfSer(IWebBackpfServices backpfSer) {
		this.backpfSer = backpfSer;
	}

	public void setStoreSer(IWebCpStoreServices storeSer) {
		this.storeSer = storeSer;
	}

	public void setGwSer(IWebGwServices gwSer) {
		this.gwSer = gwSer;
	}

	public void setMats2Ser(IWebMats2Services mats2Ser) {
		this.mats2Ser = mats2Ser;
	}

	public void setMatSer(IWebMatsServices matSer) {
		this.matSer = matSer;
	}

	public void setMstoreSer(IWebMatstoreServices mstoreSer) {
		this.mstoreSer = mstoreSer;
	}

	public void setOutSer(IWebOutbackServices outSer) {
		this.outSer = outSer;
	}

	public void setInvSer(IWebOutputInvServices invSer) {
		this.invSer = invSer;
	}

	public void setPersonSer(IWebPersonServices personSer) {
		this.personSer = personSer;
	}

	public void setFavgSer(IWebPfavgPriceServices favgSer) {
		this.favgSer = favgSer;
	}

	public void setSideSer(IWebSideServices sideSer) {
		this.sideSer = sideSer;
	}

	public void setCashSer(IWebZwcashServices cashSer) {
		this.cashSer = cashSer;
	}

	private String yymm;

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	private String yymms;

	public void setYymms(String yymms) {
		this.yymms = yymms;
	}

	private String printType;

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	private String factNo;

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	// 莉紅獺
	private IKyFactServices factSer;

	public IKyFactServices getFactSer() {
		return factSer;
	}

	public void setFactSer(IKyFactServices factSer) {
		this.factSer = factSer;
	}

	private Map<String, Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	int machinj = 1;
	int profitcountj = 1;
	int profitj = 1;
	int outputinvj = 1;
	int outbackj = 1;
	int personj = 1;
	int pfavpricej = 1;
	int gwj = 1;
	int sidej = 1;
	int mastorej = 1;
	int backmatj = 1;
	int backcpj = 1;
	int cpstorej = 1;
	int zwcashj = 1;
	int matsj = 1;
	int mat2j = 1;

	/**
	 * ゴ参璸计沮
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public String print() throws Exception {
		String factName = factSer.selByid(factNo);
		map = new HashMap<String, Object>();
		map.put("SUBREPORT_DIR",
				ServletActionContext.getRequest().getRealPath(
						"/jasper/StatisticalData/")
						+ "/");
		map.put("title", factName + "(" + yymm + "" + yymms + ")计沮参璸");
		List<String> date = DateHelper.dateToDate(yymm, yymms);
		for (int i = 0; i < date.size(); i++) {
			List<WebMachin> machinList = machineSer.selByYymm(date.get(i),
					factNo);
			List<WebProfit> profitList = profitSer.selByYymm(date.get(i),
					factNo);
			List<WebProfitcount> profitcountList = countSer.selByYymm(
					date.get(i), factNo);
			List<WebOutputinv> outinvList = invSer.selByYymm(date.get(i),
					factNo);
			List<WebOutback> outbackList = outSer
					.selByYymm(date.get(i), factNo);
			List<WebPerson> personList = personSer.selByYymm(date.get(i),
					factNo);
			List<WebPfavgprice> pfavgpriceList = favgSer.selByYymm(date.get(i),
					factNo);
			List<WebGw> gwList = gwSer.selByYymm(date.get(i), factNo);
			List<WebSide> sideList = sideSer.selByYymm(date.get(i), factNo);
			List<WebMatstore> matstoreList = mstoreSer.selByYymm(date.get(i),
					factNo);
			List<WebBackmat> backmatList = webBackmatSer.selByYymm(date.get(i),
					factNo);
			List<WebBackpf> backpfList = backpfSer.selByYymm(date.get(i),
					factNo);
			List<WebCpstore> cpstoreList = storeSer.selByYymm(date.get(i),
					factNo);
			List<WebZwcash> zwcashList = cashSer.selByYymm(date.get(i), factNo);
			List<WebMats> matsList = matSer.selByYymm(date.get(i), factNo);
			List<WebMats2> mat2List = mats2Ser.selByYymm(date.get(i), factNo);
			if (machinList.size() != 0) {
				String str = "submachin" + machinj + "List";
				map.put(str, machinList);
				machinj++;
			}
			if (profitcountList.size() != 0) {
				String str = "profitcount" + profitcountj + "List";
				map.put(str, profitcountList);
				profitcountj++;
			}
			if (profitList.size() != 0) {
				String str = "profit" + profitj + "List";
				map.put(str, profitList);
				profitj++;
			}
			if (outinvList.size() != 0) {
				String str = "outputinv" + outputinvj + "List";
				map.put(str, outinvList);
				outputinvj++;
			}
			if (outbackList.size() != 0) {
				String str = "outback" + outbackj + "List";
				map.put(str, outbackList);
				outbackj++;
			}
			if (personList.size() != 0) {
				String str = "person" + personj + "List";
				map.put(str, personList);
				personj++;
			}
			if (pfavgpriceList.size() != 0) {
				String str = "pfavprice" + pfavpricej + "List";
				map.put(str, pfavgpriceList);
				pfavpricej++;
			}
			if (gwList.size() != 0) {
				String str = "gw" + gwj + "List";
				map.put(str, gwList);
				gwj++;
			}
			if (sideList.size() != 0) {
				String str = "side" + sidej + "List";
				map.put(str, sideList);
				sidej++;
			}
			if (matstoreList.size() != 0) {
				String str = "mastore" + mastorej + "List";
				map.put(str, matstoreList);
				mastorej++;
			}
			if (backmatList.size() != 0) {
				String str = "backmat" + backmatj + "List";
				map.put(str, backmatList);
				backmatj++;
			}
			if (backpfList.size() != 0) {
				String str = "backcp" + backcpj + "List";
				map.put(str, backpfList);
				backcpj++;
			}
			if (cpstoreList.size() != 0) {
				String str = "cpstore" + cpstorej + "List";
				map.put(str, cpstoreList);
				cpstorej++;
			}
			if (zwcashList.size() != 0) {
				String str = "zwcash" + zwcashj + "List";
				map.put(str, zwcashList);
				zwcashj++;
			}
			if (matsList.size() != 0) {
				String str = "mats" + matsj + "List";
				map.put(str, matsList);
				matsj++;
			}
			if (mat2List.size() != 0) {
				String str = "mat2" + mat2j + "List";
				map.put(str, mat2List);
				mat2j++;
			}
		}
		List<String> d = new ArrayList<String>();
		if (map != null && !map.isEmpty()) {
			for (String key : map.keySet()) {
				d.add(map.get(key).toString());
			}
		}
		if (d.size() == 2) {
			ActionContext.getContext().getSession()
					.put("tishi", "硂琿丁⊿Τ计沮参璸!");
			return "tishi";
		} else {
			JasperHelper.exportmain(printType, map, "machine.jasper", null,
					yymm + "~" + yymms + "(" + factNo + ")",
					"jasper/StatisticalData/");
			machinj = 1;
			profitcountj = 1;
			profitj = 1;
			outputinvj = 1;
			outbackj = 1;
			personj = 1;
			pfavpricej = 1;
			gwj = 1;
			sidej = 1;
			mastorej = 1;
			backmatj = 1;
			backcpj = 1;
			cpstorej = 1;
			zwcashj = 1;
			matsj = 1;
			mat2j = 1;
			return "format";
		}
	}
}