package org.system.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.system.core.CoreConstants;

public class DateUtil {
	private static DateFormat df = new SimpleDateFormat(CoreConstants.DATE_FORMAT);

	public static boolean isDate(String dateStr) {
		try {
			df.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static String getDateString(Date date) {
		return df.format(date);
	}

	public static Date getDate(String dateStr) {
		try {
			Date d = df.parse(dateStr);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
