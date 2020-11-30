package com.example.nanuda;

import android.app.Application;

import com.example.nanuda.objects.Expense;
import com.example.nanuda.objects.Group;
import com.parse.Parse;
import com.parse.ParseObject;

public class Nanuda extends Application {
    // Initialises Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Group.class);
        ParseObject.registerSubclass(Expense.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("L4FSYg055cK0ctpnU9cVO5rjQlJgPai1niNso1wk")
                .clientKey("iXQ7dnfsMAiS6XIb3mAYGrWTDXssWH8QqN1OJ8QH")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
