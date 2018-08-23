package com.insuranceclaim.insuranceclaim.ViewObjects;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.insuranceclaim.insuranceclaim.Cards.InsurableCard;
import com.insuranceclaim.insuranceclaim.databaseobjects.InsurableRepository;
import com.insuranceclaim.insuranceclaim.insurables.Insurable;
import com.insuranceclaim.insuranceclaim.insurables.InsurableDataField;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrel_000 on 2018-08-06.
 * Originally created in InsuranceClaim2.
 */

public class CardListViewModel extends AndroidViewModel{
    private String TAG = "CardListViewModel";
    private InsurableRepository repository;
    private List<InsurableCard> loadedInsurables;
    private LiveData<List<Insurable>> allInsurables;
    public CardListViewModel(Application application) {
        super(application);
        Log.d(TAG, "System: Loading CardListViewModel");
        repository = new InsurableRepository(application);
        allInsurables = repository.getAllInsurables();
        loadedInsurables = new ArrayList<InsurableCard>();
        fullDataUpdate();
        Log.d(TAG, "System: CardListViewModel loading complete");
    }
    public void fullDataUpdate(){
        if ( allInsurables.getValue() != null) {
            Log.d(TAG, "System: CardListViewModel performing fullDataUpdate");
            for (int index = 0; index < allInsurables.getValue().size(); index++) {
                loadedInsurables.add(Insurable.generateCard(allInsurables.getValue().get(index)));
                String[] priorityFields = loadedInsurables.get(index).getPriorityFields();
                LiveData<List<InsurableDataField>> priorityData = repository.getDataFromTitlesAndInsurableID(priorityFields, loadedInsurables.get(index).getInsurable().getID());
                loadedInsurables.get(index).getInsurable().setAllDataFields(priorityData.getValue());
            }
        }
    }
    public LiveData<List<Insurable>> getAllInsurables() {

        fullDataUpdate();
        return allInsurables; }

    public void insert(Insurable newInsurable, List<InsurableDataField> newData) {
        repository.insert(newInsurable);
        for(int index = 0; index < newData.size(); index++){
            repository.insert(newData.get(index));
        }
    }
    public void insert(Insurable newInsurable){
        repository.insert(newInsurable);
    }

    public void insert(List<InsurableDataField> newData)
    {
        for(int index = 0; index < newData.size(); index++){
        repository.insert(newData.get(index));
        }
    }
    public void insert(InsurableDataField newDataField)
    {
            repository.insert(newDataField);
    }

}
