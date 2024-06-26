package com.ace.models.common;

import com.google.gson.annotations.SerializedName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @Classname: MediaInfo
 * @Date: 2022/10/8 下午 10:37
 * @Author: kalam_au
 * @Description:
 */


public class MediaInfo {
    private static final Logger log = LogManager.getLogger(MediaInfo.class.getName());

    public static class Format {
        @SerializedName("bit_rate")
        private String bitRate;

        public String getBitRate() {
            return bitRate;
        }

        public void setBitRate(String bitRate) {
            this.bitRate = bitRate;
        }
    }

    public static class Stream {
        @SerializedName("index")
        private int index;
        @SerializedName("codec_name")
        private String codecName;
        @SerializedName("codec_long_name")
        private String codecLongName;
        @SerializedName("profile")
        private String profile;
    }

    @SerializedName("streams")
    private List<Stream> streams;

    @SerializedName("format")
    private Format format;

    public List<Stream> getStreams() {
        return streams;
    }


    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }
}

