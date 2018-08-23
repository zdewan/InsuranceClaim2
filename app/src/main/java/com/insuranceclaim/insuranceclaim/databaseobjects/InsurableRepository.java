package com.insuranceclaim.insuranceclaim.databaseobjects;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.insuranceclaim.insuranceclaim.insurables.Insurable;
import com.insuranceclaim.insuranceclaim.insurables.InsurableDataField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrel_000 on 2018-08-02.
 * Originally created in InsuranceClaim2.
 */
public class InsurableRepository {
    private InsurableDAO mInsurableDAO;
    private InsurableDataFieldDAO mDataFieldDao;
    //private LiveData<List<InsurableDataField>> mAllDataFields;
   // private LiveData<List<Insurable>> mAllInsurables;

    public InsurableRepository(Application application){
        InsurableRoomDB db = InsurableRoomDB.getDatabase(application);
        mInsurableDAO = db.insurableDAO();
        //mAllInsurables = mInsurableDAO.getAllInsurables();
        mDataFieldDao = db.dataFieldDao();
        //mAllDataFields = mDataFieldDao.getAllDataFields();
    }
    //NOTE: PROHIBIT USE
    public LiveData<List<Insurable>> getAllInsurables() {
        return mInsurableDAO.getAllInsurables();
    }
    public LiveData<List<InsurableDataField>> getAllDataFields() {
        return mDataFieldDao.getAllDataFields();
    }


    public InsurableDataField getDataFromTitleAndInsurableID(String title, int ID){
        return mDataFieldDao.getDataByInsurableAndTitle(ID, title);
    }
    public LiveData<List<InsurableDataField>> getDataFromTitlesAndInsurableID(String[] titles,int ID){
        return mDataFieldDao.getDataFromTitlesetAndInsID(ID, titles);
    }
    //*****************INSURABLES*****************
    //Inserts insurables
    public void insert(Insurable insurable){
        new insertInsurableAsyncTask(mInsurableDAO).execute(insurable);
    }

    //****Insurable Async
    private static class insertInsurableAsyncTask extends AsyncTask<Insurable, Void, Void> {

        private InsurableDAO mAsyncTaskDao;

        insertInsurableAsyncTask(InsurableDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Insurable... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


    //*****************INSURABLE DATAFIELD*****************
    //Inserts insurable datafields
    public void insert(InsurableDataField insurableDataField){
        new insertDataAsyncTask(mDataFieldDao).execute(insurableDataField);
    }

        //****DATAFIELD ASYNC
    private static class insertDataAsyncTask extends AsyncTask<InsurableDataField, Void, Void> {

        private InsurableDataFieldDAO mAsyncTaskDao;

        insertDataAsyncTask(InsurableDataFieldDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final InsurableDataField... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
