package org.system.core.util;

import java.util.Date;

public class GenerateUtil {

	public static long generateLongID() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return (new Date()).getTime();
	}
}
