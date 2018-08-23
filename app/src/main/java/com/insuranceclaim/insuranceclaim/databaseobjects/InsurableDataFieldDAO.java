package com.insuranceclaim.insuranceclaim.databaseobjects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.insuranceclaim.insuranceclaim.insurables.Insurable;
import com.insuranceclaim.insuranceclaim.insurables.InsurableDataField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrel_000 on 2018-08-02.
 * Originally created in InsuranceClaim2.
 */
@Dao
public interface InsurableDataFieldDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InsurableDataField dataField);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(List<InsurableDataField> dataFields);

    @Update
    void updateInsurable(InsurableDataField dataField);

    @Query("DELETE FROM insurable_data_table")
    void deleteAll();

    @Query("SELECT * FROM insurable_data_table ORDER BY ID ASC")
    LiveData<List<InsurableDataField>> getAllDataFields();

    @Query("SELECT * FROM insurable_data_table WHERE ID == :ID ORDER BY ID ASC")
    InsurableDataField getSpecificDataByID(int ID);

    @Query("SELECT * FROM insurable_data_table WHERE insurableID == :insurableID ORDER BY title ASC")
    LiveData<List<InsurableDataField>> getDataByInsurableID(int insurableID);

    @Query("SELECT * FROM insurable_data_table WHERE insurableID == :insurableID AND title == :title ORDER BY title ASC")
    InsurableDataField getDataByInsurableAndTitle(int insurableID, String title);

    @Query("SELECT * FROM insurable_data_table Where insurableID == :insurableID AND title IN (:titles) Order By title ASC")
    LiveData<List<InsurableDataField>> getDataFromTitlesetAndInsID(int insurableID, String[] titles);
}
