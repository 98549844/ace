package com.service;

import com.constant.AceEnvironment;
import com.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Classname: MobilePlayService
 * @Date: 2022/11/15 下午 11:58
 * @Author: kalam_au
 * @Description:
 */

@Service
public class MobilePlayService {
    private static final Logger log = LogManager.getLogger(MobilePlayService.class.getName());

    //  public static final String VIDEO = "/video";

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String VIDEO_CONTENT = "video/";
    private static final String CONTENT_RANGE = "Content-Range";
    private static final String ACCEPT_RANGES = "Accept-Ranges";
    private static final String BYTES = "bytes";
    private static final int CHUNK_SIZE = 314700;
    //  private static final int BYTE_RANGE = 1024;


    private final String videoPath;

    public MobilePlayService() {
        this.videoPath = AceEnvironment.getVideoPath();
    }

    /**
     * Prepare the content.
     *
     * @param fileName String.
     * @param fileType String.
     * @param range    String.
     * @return ResponseEntity.
     */

    public ResponseEntity<byte[]> prepareContent(final String fileName, final String fileType, final String range) throws IOException {
        HttpStatus httpStatus = HttpStatus.PARTIAL_CONTENT;
        try {
            long rangeStart = 0;
            long rangeEnd = CHUNK_SIZE;
            String fileKey = fileName + "." + fileType;
            String videoFile = videoPath + fileKey;
            log.info("Location: {}", videoFile);
            final Long fileSize = FileUtil.getFileSize(videoFile);
            final byte[] videoByte = readByteRangeNew(videoFile, rangeStart, rangeEnd);
            if (range == null) {
                return ResponseEntity
                        .status(httpStatus)
                        .header(CONTENT_TYPE, VIDEO_CONTENT + fileType)
                        .header(ACCEPT_RANGES, BYTES)
                        .header(CONTENT_LENGTH, String.valueOf(rangeEnd))
                        .header(CONTENT_RANGE, BYTES + " " + rangeStart + "-" + rangeEnd + "/" + fileSize)
                        .header(CONTENT_LENGTH, String.valueOf(fileSize))
                        .body(videoByte); // Read the object and convert it as bytes
            }
            String[] ranges = range.split("-");
            rangeStart = Long.parseLong(ranges[0].substring(6));
            if (ranges.length > 1) {
                rangeEnd = Long.parseLong(ranges[1]);
            } else {
                rangeEnd = rangeStart + CHUNK_SIZE;
            }

            rangeEnd = Math.min(rangeEnd, fileSize - 1);
            final byte[] data = readByteRangeNew(videoFile, rangeStart, rangeEnd);
            final String contentLength = String.valueOf((rangeEnd - rangeStart) + 1);
            if (rangeEnd >= fileSize) {
                httpStatus = HttpStatus.OK;
            }
            ResponseEntity responseEntity = ResponseEntity
                    .status(httpStatus)
                    .header(CONTENT_TYPE, VIDEO_CONTENT + fileType)
                    .header(ACCEPT_RANGES, BYTES).header(CONTENT_LENGTH, contentLength)
                    .header(CONTENT_RANGE, BYTES + " " + rangeStart + "-" + rangeEnd + "/" + fileSize)
                    .body(data);
            return responseEntity;
        } catch (IOException e) {
            log.error("Exception while reading the file {}", e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * ready file byte by byte.
     *
     * @param videoFile String.
     * @param start     long.
     * @param end       long.
     * @return byte array.
     * @throws IOException exception.
     */
    public byte[] readByteRangeNew(String videoFile, long start, long end) throws IOException {

        InputStream input = null;
        byte[] data = null;
        try {
            File file = new File(videoFile);
            input = new FileInputStream(file);

            data = new byte[input.available()];

            input.read(data);
        } catch (FileNotFoundException e) {
            log.info("file not find!");
            e.printStackTrace();
        } catch (IOException e) {
            log.info("IOException :" + e);
            e.printStackTrace();
        } finally {
            assert input != null;
            input.close();
        }
        byte[] result = new byte[(int) (end - start) + 1];
        System.arraycopy(data, (int) start, result, 0, (int) (end - start) + 1);
        return result;
    }


}

