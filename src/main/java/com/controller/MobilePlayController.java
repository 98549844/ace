package com.controller;

import com.controller.common.CommonController;
import com.service.MobilePlayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Classname: DplayerController
 * @Date: 2022/11/12 下午 10:11
 * @Author: kalam_au
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class MobilePlayController extends CommonController {
    private static final Logger log = LogManager.getLogger(MobilePlayController.class.getName());

    private final MobilePlayService mobilePlayService;


    @Autowired
    public MobilePlayController(MobilePlayService mobilePlayService) {
        this.mobilePlayService = mobilePlayService;
    }



    @RequestMapping(value = "/mobile/play.html/{ext}/{playId}", method = RequestMethod.GET)
    public ModelAndView mobilePlay(@PathVariable(value = "playId") String playId,@PathVariable(value = "ext") String ext) {
        log.info("access mobile/play.html/{}/{}",ext, playId);
        String device = getDevice();
        log.info("device type: {}", device);
        ModelAndView modelAndView = page("ace/tool-pages/mobile-play");
        modelAndView.addObject("playId",playId);
        modelAndView.addObject("ext",ext);

        return modelAndView;
    }


    // getOutInputStream Exception
    @RequestMapping(value = "/stream/play.html/{playId}", method = RequestMethod.GET)
    @ResponseBody
    public Mono<ResponseEntity<byte[]>> mobileMonoPlay(@PathVariable(value = "playId") String playId, @RequestHeader(value = "Range", required = false) String httpRangeList) throws IOException {
        log.info("access stream/play.html/{}", playId);
        String device = getDevice();
        log.info("device type: {}", device);

        String fileName = "bbb";
        String fileType = "mp4";

        Mono<ResponseEntity<byte[]>> result = Mono.just(mobilePlayService.prepareContent(fileName, fileType, httpRangeList));
        return result;
    }

    // ClientAbort Exception and IO Exception
    @GetMapping(value = "/stream/play.html/{ext}/{playId}")
    public ResponseEntity<StreamingResponseBody> streamPlay(
            @PathVariable("playId") String playId, @PathVariable("ext") String ext, @RequestHeader(value = "Range", required = false) String rangeHeader) throws IOException {
        log.info("access stream/play.html {} ; {}", ext, playId);
        String mediaName = "C:\\ACE\\videos\\bbb.mp4";
        try {
            StreamingResponseBody responseStream;
            final HttpHeaders responseHeaders = new HttpHeaders();

            //  final Path filePath = getMediaPath(mediaName);
            final Path filePath = Paths.get(mediaName);
            final long fileSize = Files.size(filePath);

            byte[] buffer = new byte[1024];
            if (rangeHeader == null) {
                responseHeaders.add(HEADER_CONTENT_TYPE, CONTENT_TYPE_MP4);
                responseHeaders.add(HEADER_CONTENT_LENGTH, Long.toString(fileSize));
                responseStream = os -> {
                    try (RandomAccessFile file = new RandomAccessFile(filePath.toFile(), "r")) {
                        long pos = 0;
                        file.seek(pos);

                        while (pos < fileSize) {
                            file.read(buffer);
                            os.write(buffer);
                            pos += buffer.length;
                        }

                        os.flush();
                        os.close();
                    } catch (Exception ignored) {
                        ignored.printStackTrace();
                    }
                };
                return new ResponseEntity<>(responseStream, responseHeaders, HttpStatus.OK);
            } else {
                // Handle partial content requests
                String[] ranges = rangeHeader.split("-");
                long rangeStart = Long.parseLong(ranges[0].substring(6));
                long rangeEnd;

                if (ranges.length > 1) {
                    rangeEnd = Long.parseLong(ranges[1]);
                } else {
                    // If range is not found then just request the whole file
                    rangeEnd = fileSize - 1;
                }

                // Check to make sure that content range is not outside the length of the file
                if (fileSize < rangeEnd) {
                    rangeEnd = fileSize - 1;
                }

                log.info("Received request for byte range: {} - {}", rangeStart, rangeEnd);

                String contentLength = String.valueOf((rangeEnd - rangeStart) + 1);
                responseHeaders.add(HEADER_CONTENT_TYPE, CONTENT_TYPE_MP4);
                responseHeaders.add(HEADER_CONTENT_LENGTH, contentLength);
                responseHeaders.add(HEADER_ACCEPT_RANGES, "bytes");
                responseHeaders.add(HEADER_CONTENT_RANGE, "bytes" + " " + rangeStart + "-" + rangeEnd + "/" + fileSize);

                final long _rangeEnd = rangeEnd;
                responseStream = os -> {
                    try (RandomAccessFile file = new RandomAccessFile(filePath.toFile(), "r")) {
                        long pos = rangeStart;
                        file.seek(pos);

                        while (pos < _rangeEnd) {
                            file.read(buffer);
                            os.write(buffer);
                            pos += buffer.length;
                        }

                        os.flush();
                    } catch (Exception ignored) {
                        ignored.printStackTrace();
                    }
                };

                return new ResponseEntity<>(responseStream, responseHeaders, HttpStatus.PARTIAL_CONTENT);
            }
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the path to the example video in the resources directory.
     *
     * @param mediaName name of media file to retrieve
     * @return path to example video in resources directory
     * @throws FileNotFoundException
     */
    private Path getMediaPath(String mediaName) throws FileNotFoundException {
        final URL mediaResource = MediaController.class.getClassLoader().getResource(mediaName);
        log.info("URL:=>  " + mediaResource);
        if (mediaResource != null) {
            try {
                return Paths.get(mediaResource.toURI());
            } catch (URISyntaxException e) {
                throw new FileNotFoundException();
            }
        }

        throw new FileNotFoundException();
    }

    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_CONTENT_LENGTH = "Content-Length";
    private static final String HEADER_ACCEPT_RANGES = "Accept-Ranges";
    private static final String HEADER_CONTENT_RANGE = "Content-Range";
    private static final String CONTENT_TYPE_MP4 = "video/mp4";


}

