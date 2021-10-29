package com.project.springproj3;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CourseServ {
    public static String addCourse(Course course) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = db.collection("courses").document(course.name + "" + course.tutor).set(course);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public static Course readCourse(String name) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documentReference = db.collection("courses").document(name);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        Course course;
        if (document.exists()) {
            course = document.toObject(Course.class);
            return course;
        } else {
            return null;
        }
    }
}
