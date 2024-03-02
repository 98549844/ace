package com.ace.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Classname: FirebaseConfig
 * @Date: 1/3/2024 9:04 am
 * @Author: garlam
 * @Description:
 */

@Configuration
public class FirebaseConfig   {
    private static final Logger log = LogManager.getLogger(FirebaseConfig.class.getName());


    @PostConstruct
    public void initialize() throws IOException {
     //   FileInputStream serviceAccount = new FileInputStream("src/main/resources/your-firebase-admin-sdk.json");
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase/firebase-key.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }
}

