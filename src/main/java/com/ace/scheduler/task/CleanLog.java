package com.ace.scheduler.task;

import com.util.FileUtil;
import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.util.OsUtil;
import com.util.PathUtil;

import java.io.File;
import java.io.IOException;

public class CleanLog {
    private static final Logger log = LogManager.getLogger(CleanLog.class.getName());
    private static final String path = PathUtil.getSystemPath();
    private static final String windowsErrorPath = path + "\\log4j\\error\\";
    private static final String windowsInfoPath = path + "\\log4j\\info\\";
    private static final String windowsWarnPath = path + "\\log4j\\warn\\";
    private static final String macErrorPath = path + "/log4j/error/";
    private static final String macInfoPath = path + "/log4j/info/";
    private static final String macWarnPath = path + "/log4j/warn/";

    private static final String linuxErrorPath = path + "/log4j/error/";
    private static final String linuxInfoPath = path + "/log4j/info/";
    private static final String linuxWarnPath = path + "/log4j/warn/";
    private static final String osName = OsUtil.getOsName();

    public void clearLog() throws IOException {
        if (osName.contains(OsUtil.MAC)) {
            FileUtil.mkDirs(macErrorPath);
            FileUtil.mkDirs(macWarnPath);
            FileUtil.mkDirs(macInfoPath);

            File errorFolder = new File(macErrorPath);
            clearingLogFile(errorFolder);

            File infoFolder = new File(macInfoPath);
            clearingLogFile(infoFolder);

            File warnFolder = new File(macWarnPath);
            clearingLogFile(warnFolder);
        } else if (osName.contains(OsUtil.WINDOWS)) {
            FileUtil.mkDirs(windowsErrorPath);
            FileUtil.mkDirs(windowsWarnPath);
            FileUtil.mkDirs(windowsInfoPath);

            File errorFolder = new File(windowsErrorPath);
            clearingLogFile(errorFolder);

            File infoFolder = new File(windowsInfoPath);
            clearingLogFile(infoFolder);

            File warnFolder = new File(windowsWarnPath);
            clearingLogFile(warnFolder);
        } else if (osName.contains(OsUtil.LINUX)) {
            FileUtil.mkDirs(linuxErrorPath);
            FileUtil.mkDirs(linuxWarnPath);
            FileUtil.mkDirs(linuxInfoPath);

            //for docker container
            File errorFolder = new File(linuxErrorPath);
            clearingLogFile(errorFolder);

            File infoFolder = new File(linuxInfoPath);
            clearingLogFile(infoFolder);

            File warnFolder = new File(linuxWarnPath);
            clearingLogFile(warnFolder);
        } else {
            throw new IOException("UNKNOWN OPERATION SYSTEM: " + osName);
        }
    }

    private void clearingLogFile(File file) {
        log.info("clean up log4j: " + file.getAbsolutePath());
        File[] files = file.listFiles();
        if (NullUtil.isNonNull(file)) {
            for (File f : files) {
                f.delete();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CleanLog cleanLog = new CleanLog();
        cleanLog.clearLog();
    }

}
