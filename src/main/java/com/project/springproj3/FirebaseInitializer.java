package com.project.springproj3;

import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;


@Service
public class FirebaseInitializer {
    @PostConstruct
    public void initialize() throws IOException {
        try {
            InputStream serviceAccount = new FileInputStream("serviceAccountKey.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

//            Firestore db = FirestoreClient.getFirestore();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
