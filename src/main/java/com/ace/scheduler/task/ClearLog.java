package com.ace.scheduler.task;

import com.util.FileUtil;
import com.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.util.OsUtil;
import com.util.PathUtil;

import java.io.File;
import java.io.IOException;

public class ClearLog {
    private static final Logger log = LogManager.getLogger(ClearLog.class.getName());
    private static final String path = PathUtil.getSystemPath();
    private static final String windowsErrorPath = path + "\\src\\main\\resources\\log4j\\error\\";
    private static final String windowsInfoPath = path + "\\src\\main\\resources\\log4j\\info\\";
    private static final String windowsWarnPath = path + "\\src\\main\\resources\\log4j\\warn\\";
    private static final String macErrorPath = path + "/src/main/resources/log4j/error/";
    private static final String macInfoPath = path + "/src/main/resources/log4j/info/";
    private static final String macWarnPath = path + "/src/main/resources/log4j/warn/";

    private static final String linuxErrorPath = path + "/src/main/resources/log4j/error/";
    private static final String linuxInfoPath = path + "/src/main/resources/log4j/info/";
    private static final String linuxWarnPath = path + "/src/main/resources/log4j/warn/";
    private static final String osName = OsUtil.getOsName();

    public void clearLog() throws IOException {
        FileUtil.mkDirs(windowsErrorPath);
        FileUtil.mkDirs(windowsInfoPath);
        FileUtil.mkDirs(windowsWarnPath);

        if (osName.contains(OsUtil.MAC)) {
            File errorFolder = new File(macErrorPath);
            clearingLogFile(errorFolder);

            File infoFolder = new File(macInfoPath);
            clearingLogFile(infoFolder);

            File warnFolder = new File(macWarnPath);
            clearingLogFile(warnFolder);
        } else if (osName.contains(OsUtil.WINDOWS)) {
            File errorFolder = new File(windowsErrorPath);
            clearingLogFile(errorFolder);

            File infoFolder = new File(windowsInfoPath);
            clearingLogFile(infoFolder);

            File warnFolder = new File(windowsWarnPath);
            clearingLogFile(warnFolder);
        }else if (osName.contains(OsUtil.LINUX)) {
            //for docker container
            File errorFolder = new File(linuxErrorPath);
            clearingLogFile(errorFolder);

            File infoFolder = new File(linuxInfoPath);
            clearingLogFile(infoFolder);

            File warnFolder = new File(linuxWarnPath);
            clearingLogFile(warnFolder);
        }

        else {
            throw new IOException("UNKNOWN OPERATION SYSTEM");
        }
    }

    private void clearingLogFile(File file) {
        File[] files = file.listFiles();
        if (NullUtil.isNonNull(file)) {
            for (File f : files) {
                f.delete();
            }
        }
    }
}
