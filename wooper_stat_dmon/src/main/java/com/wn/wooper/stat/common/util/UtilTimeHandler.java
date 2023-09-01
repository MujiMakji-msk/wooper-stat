package com.wn.wooper.stat.common.util;

import java.util.Calendar;
import java.util.Date;

public class UtilTimeHandler {

	public static String getyyyy() {
		String yyyy = "";
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		yyyy = Integer.toString(calendar.get(Calendar.YEAR));

		return yyyy;
	}

	public static String getmm() {
		String mm = "";
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		mm = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		if (mm.length() == 1) mm = "0" + mm;

		return mm;
	}

	public static String getdd() {
		String dd = "";
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		dd = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		if (dd.length() == 1) dd = "0" + dd;

		return dd;
	}

	public static String getyyyymm() {
		String yyyy = "", mm = "";
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		yyyy = Integer.toString(calendar.get(Calendar.YEAR));
		mm = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		if (mm.length() == 1) mm = "0" + mm;

		return yyyy + mm;
	}

	public static String getyyyymmdd() {
		String yyyy = "", mm = "", dd = "";
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		yyyy = Integer.toString(calendar.get(Calendar.YEAR));
		mm = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		if (mm.length() == 1) mm = "0" + mm;
		dd = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		if (dd.length() == 1) dd = "0" + dd;

		return yyyy + mm + dd;
	}


	public static String getMonthToEng(int month) {
        switch (month) {
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sep";
            case 10: return "Oct";
            case 11: return "Nov";
            case 12: return "Dec";
            default: break;
        }
        return "";
    }

    public static String getFormatedDate() {
    	String yyyy = "", mm = "", dd = "", ret;
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		yyyy = Integer.toString(calendar.get(Calendar.YEAR));
		mm = getMonthToEng(calendar.get(Calendar.MONTH) + 1);
		//if (mm.length() == 1) mm = "0" + mm;
		dd = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		if (dd.length() == 1) dd = "0" + dd;

    	ret = dd + "/" + mm + "/" + yyyy;

    	return ret;
    }
}
