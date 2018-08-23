package com.insuranceclaim.insuranceclaim.insurables;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
/**
 * Created by kyrel_000 on 2018-07-21.
 * Originally created in InsuranceClaim2.
 */
@Entity(tableName = "insurable_data_table")
public class InsurableDataField {
    @ForeignKey(entity = Insurable.class, parentColumns =  "ID", childColumns = "insurableID")
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    private int insurableID;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    private int ID;
    @NonNull
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String title;
    @NonNull
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String data;
    @NonNull
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String dataType;
    @NonNull
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    private boolean isCustom = false;// True = priority, False = custom null, null = custom?
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String dataCategory;

    public InsurableDataField(int insurableID, String title, String dataType, boolean isCustom, String dataCategory, String data){
        this.insurableID = insurableID;
        this.title = title;
        this.data = data;
        this.dataType = dataType;
        this.dataCategory = dataCategory;
        this.isCustom = isCustom;
    }

    public int getInsurableID() {
        return insurableID;
    }

    public void setInsurableID(int insurableID) {
        this.insurableID = insurableID;
    }

    @NonNull
    public int getID() {
        return ID;
    }

    public void setID(@NonNull int ID) {
        this.ID = ID;
    }

    @NonNull
    public String getTitle() {
        if( title == null){
            return "";
        }
        return title;
    }

    public void setTitle(@NonNull String title) {
        if(title == null){
            title = "";
        }
        this.title = title;
    }

    @NonNull
    public String getData() {
        return data;
    }

    public void setData(@NonNull String data) {
        this.data = data;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public String getDataCategory() {
        return dataCategory;
    }

    public void setDataCategory(String dataCategory) {
        this.dataCategory = dataCategory;
    }
}
