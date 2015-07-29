package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import services.IWebMachineServices;
import services.IWebOutbackServices;
import services.IWebOutputInvServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.*;

public class MchObackOInvAction extends ActionSupport {
	private String factNo;
	private String factCode;
	private String yymm;
	private WebMachin machine;
	private WebOutback back;
	private WebOutputinv inv;
	private IWebOutbackServices outSer;
	private IWebOutputInvServices invSer;
	private IWebMachineServices machineSer;
	private String year;
	private String month;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getFactNo() {
		return factNo;
	}

	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}

	public String getFactCode() {
		return factCode;
	}

	public void setFactCode(String factCode) {
		this.factCode = factCode;
	}

	public String getYymm() {
		return yymm;
	}

	public void setYymm(String yymm) {
		this.yymm = yymm;
	}

	public WebMachin getMachine() {
		return machine;
	}

	public void setMachine(WebMachin machine) {
		this.machine = machine;
	}

	public WebOutback getBack() {
		return back;
	}

	public void setBack(WebOutback back) {
		this.back = back;
	}

	public WebOutputinv getInv() {
		return inv;
	}

	public void setInv(WebOutputinv inv) {
		this.inv = inv;
	}

	public void setOutSer(IWebOutbackServices outSer) {
		this.outSer = outSer;
	}

	public void setInvSer(IWebOutputInvServices invSer) {
		this.invSer = invSer;
	}

	public void setMachineSer(IWebMachineServices machineSer) {
		this.machineSer = machineSer;
	}

	public String add_machineOutbackOutInv() throws ParseException {
		machine.getId().setFactNo(factNo);
		back.getId().setFactNo(factNo);
		inv.getId().setFactNo(factNo);

		machine.setFactCode(factCode);
		back.setFactCode(factCode);
		inv.getId().setFactCode(factCode);

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Date d = null;
		String date = df.format(new Date());
		StringBuffer ym = new StringBuffer();
		ym.append(year);
		ym.append(month);

		if (machine.getId().getBillNo() == null) {
			StringBuffer index = new StringBuffer();
			String typeId = ActionContext.getContext().getSession()
					.get("typeid").toString();
			index.append(typeId);
			// index.append("QQ");
			String factno = machine.getId().getFactNo();
			index.append(factno);
			index.append(date);
			Random rand = new Random();
			int num = 0;
			// this.list();

			num = rand.nextInt(1000);
			String temp = (index.append(num)).toString();
			machine.getId().setBillNo(temp);

			/*
			 * num=rand.nextInt(1000); String
			 * temp=(index.append(num)).toString();
			 * machine.getId().setBillNo(temp);
			 */
			d = format.parse(ym.toString());
			machine.getId().setYymm(d);
			machine.setDateTime(date);
			machineSer.addMachine(machine);
		} else {
			d = format.parse(yymm);
			machine.getId().setYymm(d);
			machine.setDateTime(date);
			machineSer.addMachine(machine);
		}

		if (back.getId().getBillNo() == null) {
			StringBuffer index = new StringBuffer();
			String typeId = ActionContext.getContext().getSession()
					.get("typeid").toString();
			index.append(typeId);
			// index.append("QQ");
			String factno = back.getId().getFactNo();
			index.append(factno);
			index.append(date);
			Random rand = new Random();
			int num = 0;
			// this.list();

			num = rand.nextInt(1000);
			String temp = (index.append(num)).toString();
			back.getId().setBillNo(temp);

			/*
			 * num=rand.nextInt(1000); String
			 * temp=(index.append(num)).toString();
			 * machine.getId().setBillNo(temp);
			 */
			d = format.parse(ym.toString());
			back.getId().setYymm(d);
			back.setDateTime(date);
			outSer.addOutback(back);
		} else {
			d = format.parse(yymm);
			back.getId().setYymm(d);
			back.setDateTime(date);
			outSer.addOutback(back);
		}

		if (inv.getId().getBillNo() == null) {
			StringBuffer index = new StringBuffer();
			String typeId = ActionContext.getContext().getSession()
					.get("typeid").toString();
			index.append(typeId);
			// index.append("QQ");
			String factno = inv.getId().getFactNo();
			index.append(factno);
			index.append(date);
			Random rand = new Random();
			int num = 0;
			// this.list();

			num = rand.nextInt(1000);
			String temp = (index.append(num)).toString();
			inv.getId().setBillNo(temp);

			/*
			 * num=rand.nextInt(1000); String
			 * temp=(index.append(num)).toString();
			 * machine.getId().setBillNo(temp);
			 */
			d = format.parse(ym.toString());
			inv.getId().setYymm(d);
			inv.setDateTime(date);
			invSer.addoutPut(inv);
		} else {
			d = format.parse(yymm);
			inv.getId().setYymm(d);
			inv.setDateTime(date);
			invSer.addoutPut(inv);
		}
		return "add_machineOutbackOutInv";

	}

}
