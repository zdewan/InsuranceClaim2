package com.insuranceclaim.insuranceclaim.databaseobjects;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.insuranceclaim.insuranceclaim.insurables.Insurable;
import com.insuranceclaim.insuranceclaim.insurables.InsurableDataField;

/**
 * Created by kyrel_000 on 2018-08-02.
 * Originally created in InsuranceClaim2.
 */
@Database(entities = {Insurable.class, InsurableDataField.class}, version = 2)
public abstract class InsurableRoomDB extends RoomDatabase{
    public abstract InsurableDAO insurableDAO();
    public abstract InsurableDataFieldDAO dataFieldDao();

    private static InsurableRoomDB INSTANCE;
    public static InsurableRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InsurableRoomDB.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InsurableRoomDB.class, "insurable_database")
                            .addCallback(sRoomDatabaseCallback).fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final InsurableDAO mInsDao;
        private final InsurableDataFieldDAO mDataDao;

        PopulateDbAsync(InsurableRoomDB db) {
            mInsDao = db.insurableDAO();
            mDataDao = db.dataFieldDao();

        }

        @Override
        protected Void doInBackground(final Void... params) {
            mInsDao.deleteAll();
            mDataDao.deleteAll();
            int fakeInsurables = 1;
            for(int i = 0; i < fakeInsurables; i ++){
                String name = "testInsurable " + i;
                Insurable testInsurable = new Insurable(i,name,Insurable.InsurableTypes.AUTOMOBILE,"null");
                testInsurable.updateData("model","String","zack");
                testInsurable.updateData("make","String","Ford F150");
                testInsurable.updateData("VIN","String","");
                testInsurable.updateData("year","String","1998 The year of man");
                mInsDao.insert(testInsurable);
                mDataDao.insert(testInsurable.getData());
            }
            return null;
        }
    }
}
