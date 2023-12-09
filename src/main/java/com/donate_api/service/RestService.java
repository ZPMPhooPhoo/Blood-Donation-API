package com.donate_api.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.donate_api.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class RestService {

    public String postDonate(User users) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("user").document(users.getDocumentId());
        ApiFuture<WriteResult> collectionsApi = documentReference.set(users);
        WriteResult writeResult = collectionsApi.get();
        return "Successfully created/updated document with ID: " + writeResult.getUpdateTime();
    }

    public User getDonate(String documentId) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("user").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        User users;
        if (document.exists()) {
            users = document.toObject(User.class);
            return users;

        } else {
            return null;
        }

    }

    public String putDonate(User users) throws InterruptedException, ExecutionException {
        // Implement your logic for updating user dat
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApi = dbFirestore.collection("user").document(users.getDocumentId())
                .set(users);
        return collectionsApi.get().getUpdateTime().toString();
    }

    public String delDonate(String documentId) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> deleteResult = dbFirestore.collection("user").document(documentId).delete();
        deleteResult.get();
        return "Successfully deleted " + documentId;
    }
}
