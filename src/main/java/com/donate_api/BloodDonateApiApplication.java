package com.donate_api;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BloodDonateApiApplication {

	public static void main(String[] args) {
		
		try {
           SpringApplication.run(BloodDonateApiApplication.class, args);
    
            ClassLoader classLoader = BloodDonateApiApplication.class.getClassLoader();
            URL resourceUrl = Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json"));
            File file = new File(resourceUrl.toURI());
    
            try (FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath())) {
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://blood-donate-6a673.firebasedatabase.app")
                        .build();
                log.info("Firebase Database Connection is Ok");
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        FirebaseApp.initializeApp();
	}

}
