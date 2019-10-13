package lucee.runtime.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import lucee.print;
import lucee.commons.io.IOUtil;

public class FileList {

	private static int count = 0;

	public static void list(File file) throws IOException {
		if (file.isFile()) {
			if (file.toString().endsWith(".cfm") || file.toString().endsWith(".cfc")) {
				FileInputStream fis = new FileInputStream(file);
				try {
					String str = IOUtil.toString(fis, "UTF-8");
					//
					// if (str.indexOf("\"upload\"") != -1) print.e(file);
					// else if (str.indexOf("'upload'") != -1) print.e(file);
					if (str.indexOf("\"upload\"") != -1) print.e((++count) + " " + file);
				}
				finally {
					IOUtil.closeEL(fis);
				}

			}

		}
		else if (file.isDirectory()) {
			File[] children = file.listFiles();
			if (children != null) for (File child: children) {
				list(child);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String path = "/Users/mic/Projects/Distrokid/distrokid";
		File f = new File(path);
		list(f);
	}
}