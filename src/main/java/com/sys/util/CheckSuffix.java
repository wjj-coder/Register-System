package com.sys.util;

/**
 * check upload picture suffix
 * 
 * @author wjj
 */
public class CheckSuffix {

	
	public static boolean checksuffix(String sx) {
		boolean check = false;

		String[] suffix = { "jpg", "jpeg", "gif", "png", "bmp" };

		String filename = sx.substring(sx.lastIndexOf(".") + 1);

		for (int i = 0; i < suffix.length; i++) {

			if (suffix[i].equals(filename.toLowerCase())) {
				check = true;
			}

		}
		return check;
	}

}
