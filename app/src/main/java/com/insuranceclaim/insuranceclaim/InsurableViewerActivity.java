package com.insuranceclaim.insuranceclaim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import com.insuranceclaim.insuranceclaim.Cards.CardRecyclerViewAdapter;
import com.insuranceclaim.insuranceclaim.Cards.InsurableCard;

public class InsurableViewerActivity extends AppCompatActivity {

    private static final String TAG = "InsurableViewerActivity";
    //vars
    private ArrayList<InsurableCard> mCards= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurable_viewer);
        initCards();
    }
    private void initCards(){
        LoadTestCards();
    }
    private void LoadTestCards(){

    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.insuranceRecyclerView);
        CardRecyclerViewAdapter adapter = new CardRecyclerViewAdapter(mCards,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
