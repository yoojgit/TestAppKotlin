package com.androidpractice.testappkotlin;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResourceMain {

    private static final ResourceMain instance = new ResourceMain();

    public FirebaseFirestore db;
    public FirebaseAuth mAuth;

    public static ResourceMain getInstance(){
        return instance;
    }

    private ResourceMain(){
        //constructor
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

}
