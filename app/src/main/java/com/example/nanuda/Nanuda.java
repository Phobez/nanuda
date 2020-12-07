package com.example.nanuda;

import android.app.Application;

import com.example.nanuda.objects.Expense;
import com.example.nanuda.objects.Group;
import com.parse.Parse;
import com.parse.ParseObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Nanuda extends Application {
    public static final String EXTRA_GROUP = "com.example.nanuda.GROUP";
    public static final String EXTRA_EXPENSES = "com.example.nanuda.EXPENSES";
    public static final String EXTRA_EXPENSE_INDEX = "com.example.nanuda.EXPENSE_INDEX";

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

        /*
        List<String> participants = new ArrayList<String>();
        participants.add("Abia");
        participants.add("Hugo");
        participants.add("Nadya");

        Group group = new Group("Test Group", Group.Currency.KRW, participants);
        group.saveEventually();

        List<String> payees = new ArrayList<String>();
        payees.add("Hugo");
        payees.add("Nadya");

        List<Long> owedAmounts = new ArrayList<Long>();
        owedAmounts.add((long)50000);
        owedAmounts.add((long)50000);

        Expense expense = new Expense("Test Expense", (long)100000, new Date(), "Abia", payees, owedAmounts, group);
        expense.saveEventually();
         */
    }
}
