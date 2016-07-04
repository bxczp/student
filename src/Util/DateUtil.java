package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date 2016年3月13日 FormmatUtil.java
 * @author CZP
 * @parameter
 */
public class DateUtil {

	public static String formatDateToString(Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String result = "";
		if (date != null) {
			result = dateFormat.format(date);
		}
		return result;
	}

	public static Date formatStringToDate(String str, String format) throws Exception {
		Date result = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		if (StringUtil.isEmpty(str)) {
			return null;
		} else {
			result = dateFormat.parse(str);
			return result;
		}
	}

	public static String getCurrentDateToString() {
		String result = "";
		Date date = new Date();
		// yyyy-MM-dd HH:mm:ss 是24小时制
		// yyyy-MM-dd hh:mm:ss 是12小时制
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		result = dateFormat.format(date);
		return result;
	}

}
