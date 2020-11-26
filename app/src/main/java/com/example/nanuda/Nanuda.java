package com.example.nanuda;

import android.app.Application;

import com.parse.Parse;

public class Nanuda extends Application {
    // Initialises Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("L4FSYg055cK0ctpnU9cVO5rjQlJgPai1niNso1wk")
                .clientKey("iXQ7dnfsMAiS6XIb3mAYGrWTDXssWH8QqN1OJ8QH")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
