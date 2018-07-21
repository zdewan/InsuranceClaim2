package com.insuranceclaim.insuranceclaim.insurables;
import java.util.ArrayList;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
/**
 * Created by kyrel_000 on 2018-07-21.
 * Originally created in InsuranceClaim2.
 */
//@Entity(PrimaryKey = "")
public class InsurableDataField {
    private String dataID;
    private String title;
    private String data;
    private String dataCategory;
}
