package com.scheduler.task;

import com.util.PathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.OsUtil;

import java.io.File;

public class ClearLog {
	private static Logger log = LogManager.getLogger(ClearLog.class.getName());
	private static final String path = PathUtil.getSystemPath();
	private static final String windowsErrorPath = path + "\\src\\main\\resources\\log4j\\error\\";
	private static final String windowsInfoPath = path + "\\src\\main\\resources\\log4j\\info\\";
	private static final String windowsWarnPath = path + "\\src\\main\\resources\\log4j\\warn\\";
	private static final String macErrorPath = path + "/src/main/resources/log4j/error/";
	private static final String macInfoPath = path + "/src/main/resources/log4j/info/";
	private static final String macWarnPath = path + "/src/main/resources/log4j/warn/";
	private static final String osName = OsUtil.getOsInfo();

	public void clearLog() throws Exception {
		if (osName.contains("MAC OS")) {
			File errorFolder = new File(macErrorPath);
			clearingLogFile(errorFolder);

			File infoFolder = new File(macInfoPath);
			clearingLogFile(infoFolder);

			File warnFolder = new File(macWarnPath);
			clearingLogFile(warnFolder);
		} else if (osName.contains("WINDOWS")) {
			File errorFolder = new File(windowsErrorPath);
			clearingLogFile(errorFolder);

			File infoFolder = new File(windowsInfoPath);
			clearingLogFile(infoFolder);

			File warnFolder = new File(windowsWarnPath);
			clearingLogFile(warnFolder);
		}
	}

	private void clearingLogFile(File file) {
		File[] Files = file.listFiles();
		if (Files.length > 0) {
			for (File f : Files) {
				f.delete();
			}
		}
	}
}