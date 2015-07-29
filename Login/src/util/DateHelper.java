package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateHelper {
	public static List<String> dateToDate(String startTime, String endTime)
			throws Exception {
		List<String> date = new ArrayList<String>();
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat ym = new SimpleDateFormat("yyyyMM");
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.setTime(ymd.parse(startTime));
		endDate.setTime(ymd.parse(endTime));
		String endYm = ym.format(endDate.getTime());
		while (true) {
			String strYm = ym.format(startDate.getTime());
			date.add(strYm);
			if (strYm.equals(endYm)) {
				break;
			}
			startDate.add(Calendar.MONTH, 1);
		}
		return date;
	}
}
