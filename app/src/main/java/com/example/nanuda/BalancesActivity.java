package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.nanuda.objects.DetailsListObject;
import com.example.nanuda.objects.Expense;
import com.example.nanuda.objects.Group;
import com.example.nanuda.objects.SummaryListObject;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Handles behaviour for the Balances Activity.
 */
public class BalancesActivity extends AppCompatActivity {

    private ArrayList<SummaryListObject> summaryList = new ArrayList<SummaryListObject>();
    private ArrayList<DetailsListObject> detailsList = new ArrayList<DetailsListObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balances);

        // select Balances tab by default
        TabLayout tabLayout = (TabLayout) findViewById(R.id.balancesTabLayout);
        TabLayout.Tab balancesTab = tabLayout.getTabAt(1);
        balancesTab.select();

        Intent intent = getIntent();
        // TODO: replace name of extras with static final strings
        Group group = intent.getParcelableExtra("com.example.nanuda.GROUP");
        ArrayList<Expense> expenses = intent.getParcelableArrayListExtra("com.example.nanuda.EXPENSES");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        // TODO: replace SplashScreenActivity.class with the expenses list activity
                        Intent intent = new Intent(BalancesActivity.this, SplashScreenActivity.class);
                        // TODO: replace name of extras with static final strings
                        intent.putExtra("com.example.nanuda.GROUP", group);
                        intent.putParcelableArrayListExtra("com.example.nanuda.EXPENSES", expenses);
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        // set up toolbar
        getSupportActionBar().setTitle("Balances");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpLists(group, expenses);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.balancesList);

        // DUMMY DATA
        // TODO: REMOVE
        /*
        summaryList.add(new SummaryListObject("Abia", (long) 1000));
        summaryList.add(new SummaryListObject("Nadya", (long) 5000));
        summaryList.add(new SummaryListObject("Abia", (long) 3000));

        detailsList.add(new DetailsListObject("Abia", "Nadya", (long) 1000));
        */

        // initialise list
        BalancesAdapter balancesAdapter = new BalancesAdapter();
        balancesAdapter.setSummaryList(summaryList);
        balancesAdapter.setDetailsList(detailsList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(BalancesActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(balancesAdapter);
    }

    /**
     * Sums total amounts expended and owed per participant.
     */
    private void setUpLists(Group group, ArrayList<Expense> expenses) {
        HashMap<String, Long> summaries = new HashMap<String, Long>();

        // cached variables
        List<String> participants = group.getParticipants();
        int participantsSize = participants.size();

        // initialise summary hashmap
        for (int i = 0; i < participantsSize; i++) {
            summaries.put(participants.get(i), (long) 0);
        }

        // cached variables
        int expensesSize = expenses.size();

        // count summaries
        for (int i = 0; i < expensesSize; i++) {
            Expense currExpense = expenses.get(i);
            String currPayer = currExpense.getPayer();
            long oriSum = summaries.get(currPayer);
            long currAmount = currExpense.getAmount();
            long newSum = oriSum + currAmount;

            summaries.put(currPayer, newSum);

            List<String> currPayees = currExpense.getPayees();
            List<Long> currOwedAmounts = currExpense.getOwedAmounts();
            int currPayeesSize = currPayees.size();

            for (int j = 0; j < currPayeesSize; j++) {
                String currPayee = currPayees.get(j);
                long oriCurrPayeeSum = summaries.get(currPayee);
                long currCurrPayeeOwedAmount = currOwedAmounts.get(j);
                long newCurrPayeeSum = oriCurrPayeeSum - currCurrPayeeOwedAmount;

                summaries.put(currPayee, newCurrPayeeSum);
            }
        }

        List<SummaryListObject> posSummaries = new ArrayList<SummaryListObject>();
        List<SummaryListObject> negSummaries = new ArrayList<SummaryListObject>();

        // assign summary list
        for (int i = 0; i < participantsSize; i++) {
            String currParticipant = participants.get(i);
            long currSum = summaries.get(currParticipant);

            SummaryListObject newSummary = new SummaryListObject(currParticipant, currSum, group.getCurrency());

            summaryList.add(newSummary);

            if (currSum > 0) {
                posSummaries.add(newSummary);
            } else if (currSum < 0) {
                negSummaries.add(newSummary);
            }
        }

        Collections.sort(posSummaries, Collections.reverseOrder());
        Collections.sort(negSummaries, Collections.reverseOrder());

        // cached variables
        int posSummariesSize = posSummaries.size();
        int negSummariesIndexer = 0;
        long negSummariesRemains = 0;
        String currOwerName = "";

        // assign details list
        for (int i = 0; i < posSummariesSize; i++) {
            long currSum = posSummaries.get(i).getParticipantSum();
            String currOweeName = posSummaries.get(i).getParticipantName();

            if (negSummariesRemains <= 0) {
                negSummariesRemains = negSummaries.get(negSummariesIndexer).getParticipantSum();
                currOwerName = negSummaries.get(negSummariesIndexer).getParticipantName();
            }

            while (currSum > 0) {
                if (currSum - negSummariesRemains <= 0) {
                    negSummariesRemains += currSum;
                    currSum = 0;
                    detailsList.add(new DetailsListObject(currOwerName, currOweeName, Math.abs(currSum), group.getCurrency()));
                } else {
                    currSum += negSummariesRemains;
                    detailsList.add(new DetailsListObject(currOwerName, currOweeName, Math.abs(negSummariesRemains), group.getCurrency()));
                    negSummariesIndexer++;
                    if (negSummariesIndexer < negSummaries.size()) {
                        negSummariesRemains = negSummaries.get(negSummariesIndexer).getParticipantSum();
                        currOwerName = negSummaries.get(negSummariesIndexer).getParticipantName();
                    }
                }
            }
        }
    }
}