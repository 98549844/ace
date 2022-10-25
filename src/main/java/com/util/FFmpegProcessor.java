//package com.util;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.bytedeco.ffmpeg.global.avcodec;
//import org.bytedeco.ffmpeg.global.avutil;
//import org.bytedeco.javacv.*;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//
///**
// * @Classname: FFmpegProcessor
// * @Date: 2022/10/11 上午 11:52
// * @Author: kalam_au
// * @Description:
// */
//
//
//public class FFmpegProcessor {
//    private static final Logger log = LogManager.getLogger(FFmpegProcessor.class.getName());
//
//    /**
//     * 这个方法的url地址都必须是一样的类型 同为post
//     */
//    public static void convertMediaToM3u8(InputStream inputStream, String m3u8Url, String infoUrl) throws IOException {
//
//        avutil.av_log_set_level(avutil.AV_LOG_INFO);
//        FFmpegLogCallback.set();
//
//        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputStream);
//        grabber.start();
//
//        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(m3u8Url, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
//
//        recorder.setFormat("hls");
//        recorder.setOption("hls_time", "5");
//        recorder.setOption("hls_list_size", "0");
//        recorder.setOption("hls_flags", "delete_segments");
//        recorder.setOption("hls_delete_threshold", "1");
//        recorder.setOption("hls_segment_type", "mpegts");
//        recorder.setOption("hls_segment_filename", "http://localhost:8088/upload/test-%d.ts");
//        recorder.setOption("hls_key_info_file", infoUrl);
//
//        // http属性
//        recorder.setOption("method", "POST");
//
//        recorder.setFrameRate(25);
//        recorder.setGopSize(2 * 25);
//        recorder.setVideoQuality(1.0);
//        recorder.setVideoBitrate(10 * 1024);
//        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
//        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
//        recorder.start();
//
//        Frame frame;
//        while ((frame = grabber.grabImage()) != null) {
//            try {
//                recorder.record(frame);
//            } catch (FrameRecorder.Exception e) {
//                e.printStackTrace();
//            }
//        }
//        recorder.setTimestamp(grabber.getTimestamp());
//        recorder.close();
//        grabber.close();
//    }
//
//}
//
//
