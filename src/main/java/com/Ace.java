package com;

import com.util.SleepUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Classname: Ace
 * @Date: 2022/10/18 下午 04:15
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) {
        List<String> commands = new ArrayList<>();
        commands.add("ffmpeg");
        commands.add("-i");
        commands.add("-c:v");
        commands.add("libx264");                // 视频编码为H264
        commands.add("-c:a");
        commands.add("copy");                    // 音频直接copy
        commands.add("-hls_key_info_file");
        commands.add("-hls_time");
        commands.add("-hls_playlist_type");
        commands.add("vod");                    // 点播模式
        commands.add("-hls_segment_filename");
        commands.add("%06d.ts");

        System.out.println(commands);

    }

    public void test() {
        int a = 1;
        while (a == 1) {
            System.out.println("t1 start");
        }
    }

    public boolean isRunning(Object object) {
        Thread t = new Thread((Runnable) object);
        AtomicBoolean isRunning = new AtomicBoolean(false);
        Thread thread = new Thread(() -> {
            if (!t.getState().equals(Thread.State.RUNNABLE)) {
                System.out.println(t.getState());
                System.out.println("跳过");
                isRunning.set(true);
            } else {
                System.out.println(t.getState());
                System.out.println("t2 start");
                isRunning.set(false);
            }
        });
        thread.start();
        return isRunning.get();
    }
}

