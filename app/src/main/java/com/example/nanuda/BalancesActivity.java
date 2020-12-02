package com.example.nanuda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.nanuda.objects.DetailsListObject;
import com.example.nanuda.objects.SummaryListObject;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class BalancesActivity extends AppCompatActivity {

    private ArrayList<SummaryListObject> summaryList = new ArrayList<SummaryListObject>();
    private ArrayList<DetailsListObject> detailsList = new ArrayList<DetailsListObject>();

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private BalancesAdapter balancesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balances);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.balancesTabLayout);
        TabLayout.Tab balancesTab = tabLayout.getTabAt(1);
        balancesTab.select();

        getSupportActionBar().setTitle("Balances");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.balancesList);

        summaryList.add(new SummaryListObject("Abia", "1000"));
        summaryList.add(new SummaryListObject("Nadya", "5000"));
        summaryList.add(new SummaryListObject("Abia", "3000"));

        detailsList.add(new DetailsListObject("Abia", "Nadya", "1000"));

        // initialise list
        balancesAdapter = new BalancesAdapter();
        balancesAdapter.setSummaryList(summaryList);
        balancesAdapter.setDetailsList(detailsList);

        layoutManager = new LinearLayoutManager(BalancesActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(balancesAdapter);
    }
}