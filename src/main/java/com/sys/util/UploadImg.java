package com.sys.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UploadImg {

	/**
	 * 
	 * @param stream
	 * @param filename
	 * @param pathDir
	 * @return imgUrl
	 * @throws IOException
	 */
	public static String uploadFile(InputStream stream, String filename,
			String pathDir) throws IOException {

		File dir = new File(pathDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		Long time = System.currentTimeMillis();
		String imgUrl = "";
		imgUrl = time.toString()
				+ filename.substring(filename.lastIndexOf("."));
		File uploadFile = new File(pathDir, imgUrl);
		OutputStream os = new FileOutputStream(uploadFile);
		byte[] buffer = new byte[1024 * 1024];
		int length;
		while ((length = stream.read(buffer)) > 0) {
			os.write(buffer, 0, length);
			os.flush();
		}

		os.close();
		stream.close();
		return imgUrl;

	}
}
