package com.insuranceclaim.insuranceclaim.databaseobjects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.insuranceclaim.insuranceclaim.insurables.Insurable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrel_000 on 2018-07-09.
 * Originally created in InsuranceReport.
 */
@Dao
public interface InsurableDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Insurable insurable);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertmultiple(List<Insurable> insurables);

    @Update
    void updateInsurable(Insurable insurable);

    @Query("DELETE FROM insurable_table")
    void deleteAll();

    @Query("SELECT * FROM insurable_table ORDER BY ID ASC")
    LiveData<List<Insurable>> getAllInsurables();

    @Query("SELECT * FROM insurable_table WHERE name == :insurableName ORDER BY ID ASC")
    Insurable getInsurableByName(String insurableName);




}
