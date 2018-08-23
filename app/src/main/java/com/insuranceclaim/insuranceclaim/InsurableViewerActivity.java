package com.insuranceclaim.insuranceclaim;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.insuranceclaim.insuranceclaim.Cards.AutomobileInsurableCard;
import com.insuranceclaim.insuranceclaim.ViewObjects.CardListViewAdapter;
import com.insuranceclaim.insuranceclaim.Cards.InsurableCard;
import com.insuranceclaim.insuranceclaim.ViewObjects.CardListViewModel;
import com.insuranceclaim.insuranceclaim.insurables.Insurable;

public class InsurableViewerActivity extends AppCompatActivity {

    private static final String TAG = "InsurableViewerActivity";
    //vars
    private RecyclerView recyclerView;
    private CardListViewAdapter adapter;
    private CardListViewModel cardListViewModel;
    private ArrayList<InsurableCard> mCards= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurable_viewer);
        loadViewModel();
        initCards();
    }
    private void loadViewModel(){
        Log.d(TAG, "SystemBeginning Loading View Model");
        cardListViewModel = ViewModelProviders.of(this).get(CardListViewModel.class);
        Log.d(TAG, "System created View model object");
        cardListViewModel.getAllInsurables().observe(this, new Observer<List<Insurable>>() {
            @Override
            public void onChanged(@Nullable final List<Insurable> insurables) {
                Log.d(TAG, "System setting insurables to adapter");
                adapter.setInsurables(insurables);
            }
        });
    }
    private void initCards(){
        //loadTestCards();
        initRecyclerView();
    }
    private void loadTestCards(){
         for(int x = 0; x <13; x ++){
             String name = "TestInsurable " + x;
             Insurable testInsurable = new Insurable(x,name,Insurable.InsurableTypes.AUTOMOBILE,"");
             testInsurable.updateData("model","String","zack");
             testInsurable.updateData("make","String","Ford F150");
             testInsurable.updateData("VIN","String","");
             testInsurable.updateData("year","String","1998 The year of man");
             AutomobileInsurableCard  card = new AutomobileInsurableCard(testInsurable);
             mCards.add(card);
         }
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        recyclerView = findViewById(R.id.insuranceRecyclerView);
        adapter = new CardListViewAdapter(mCards,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
