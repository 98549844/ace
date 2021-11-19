package com.schedule.task;

import com.Ace;
import com.util.PathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClearLog {
	private static Logger log = LogManager.getLogger(Ace.class.getName());
	private static final String path = PathUtil.getSystemPath() + "\\src\\main\\resources\\log4j\\";

	public static void main(String[] args) throws Exception {
		List<String> ls = getSubFolderList(path);
		for (String s : ls) {
			File f = new File(s);

			//occur error
			List<String> files = FileUtil.getFileNames(f.getAbsolutePath());
			//occur error


			for (String fs : files) {
				File file = new File(fs);
				log.info(file.getPath());
				log.info(file.getAbsoluteFile());
				boolean del = file.delete();
				log.info("Log status: {}", del ? "deleted" : "fail to delete");

			}
		}

	}


	private static List getSubFolderList(String path) {
		//1.获取该目录下所有文件的file类对象,目的是能使用获取目录，文件名等方法
		File file = new File(path);
		File[] files = file.listFiles();
		//2.如果是文件，继续获取该目录下所有文件的file类对象，不是文件，先转换为字符串，用字符串的endsWith方法判断是否以。java结尾，输出
		List<String> ls = new ArrayList<>();
		for (File f : files) {
			if (f.isDirectory()) {
				ls.add(f.getAbsolutePath());
			}
		}
		return ls;
	}
}
