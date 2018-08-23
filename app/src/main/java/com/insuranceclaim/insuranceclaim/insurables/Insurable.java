package com.insuranceclaim.insuranceclaim.insurables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import android.support.annotation.NonNull;
import android.util.Log;

import com.insuranceclaim.insuranceclaim.Cards.AutomobileInsurableCard;
import com.insuranceclaim.insuranceclaim.Cards.CustomInsurableCard;
import com.insuranceclaim.insuranceclaim.Cards.DentalInsurableCard;
import com.insuranceclaim.insuranceclaim.Cards.HealthInsurableCard;
import com.insuranceclaim.insuranceclaim.Cards.InsurableCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyrel_000 on 2018-08-01.
 * Originally created in InsuranceClaim2.
 */
@Entity(tableName = "insurable_table")
public class Insurable {
    static final String TAG = "Insurable.class";
    public enum InsurableTypes
            {AUTOMOBILE, HOME,
            HEALTH, PERSONAL,
            DENTAL, CUSTOM,
            TEMPLATED};

    //CONSTRUCTORS
    public Insurable() {
        isEncrypted = false;
        isSaved = false;
        name = null;
        initInsurableArrayLists();
    }
    public Insurable(@NonNull int ID, @NonNull String name, InsurableTypes type, @NonNull String templateID) {
        this.ID = ID;
        this.name = name;
        this.cardType = type.name();
        this.updateTimestamp = System.currentTimeMillis();
        TemplateID = templateID;
        initInsurableArrayLists();
    }
    private void initInsurableArrayLists(){
        data = new ArrayList<>();
    }

    public static InsurableCard generateCard(Insurable insurable){
        Log.d(TAG,"Creating card of type:" + insurable.getCardType());

        switch(insurable.getCardTypeAsType()){
            case AUTOMOBILE:
                Log.d(TAG,"AUTOMOBILE card generated.");
                return new AutomobileInsurableCard(insurable);
            case HOME:
                Log.d(TAG,"Health card generated.");
                return  new HealthInsurableCard(insurable);
            case CUSTOM:
                return new CustomInsurableCard(insurable);
            case DENTAL:
                return new DentalInsurableCard(insurable);
            case TEMPLATED:
                Log.d(TAG,"TEMPLATED card generated.");

                return new CustomInsurableCard(insurable);
            default:
                Log.d(TAG,"default card(custom) generated.");

                return new CustomInsurableCard(insurable);
        }
    }
    //MEMBERS
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    private int ID;
    @NonNull
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    private boolean isEncrypted;
    @NonNull
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    private int AccountID;
    @NonNull
    @ColumnInfo (typeAffinity = ColumnInfo.TEXT)
    private String name;
    @NonNull
    @ColumnInfo (typeAffinity = ColumnInfo.TEXT)
    private String cardType;

    @NonNull
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    private long updateTimestamp;

    @NonNull
    private String TemplateID;
    @Ignore
    //@Relation(parentColumn = "ID", entityColumn = "insurableID")
    private List<InsurableDataField> data;
    @Ignore
    private boolean isSaved = false;
    /*****ASSORTED SETTERS AND GETTERS******/
    @NonNull
    public int getID() {
        return ID;
    }
    public void setID(@NonNull int ID) {
        this.ID = ID;
    }
    @NonNull
    public boolean isEncrypted() {
        return isEncrypted;
    }
    public void setEncrypted(@NonNull boolean encrypted) {
        isEncrypted = encrypted;
    }
    @NonNull
    public int getAccountID() {
        return AccountID;
    }
    public void setAccountID(@NonNull int accountID) {
        AccountID = accountID;
    }
    @NonNull
    public String getName() {
        return name;
    }
    public void setName(@NonNull String name) {
        this.name = name;
    }
    @NonNull
    public String getCardTypeAsString() {
        return cardType;
    }
    public InsurableTypes getCardTypeAsType(){
       for(InsurableTypes type: InsurableTypes.values()){
           if(type.name() == cardType.toUpperCase())
           {
               return type;
           }
       }
       return InsurableTypes.CUSTOM;
    }
    public String getCardType(){
        return cardType;
    }
    public void setCardType(@NonNull String cardType) {
        this.cardType = cardType;
    }
    @NonNull
    public long getUpdateTimestamp() {
        return updateTimestamp;
    }
    public void setUpdateTimestamp(@NonNull long updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
    @NonNull
    public String getTemplateID() {
        return TemplateID;
    }
    public void setTemplateID(@NonNull String templateID) {
        TemplateID = templateID;
    }
    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    /*****BOOLEAN OPERATORS*****/
    public boolean doesTitleExist(String title){
        for (int i = 0; i < data.size();i ++){
            if((!data.get(i).isCustom() )&& data.get(i).getTitle().equals(title))
            {
                return true;
            }
        }
        return false;
    }
    public boolean doesCustomTitleExist(String title){
        for (int i = 0; i < data.size();i ++){
            if(data.get(i).isCustom() && data.get(i).getTitle().equals(title))
            {
                return true;
            }
        }
        return false;
    }

    //TODO: CHECK ALL LOGIC IN METHODS.
    //***************DATA SETTING/ CHANGING METHODS********************************//
    private boolean addDataField(InsurableDataField datafield){
        if(!doesTitleExist(datafield.getTitle())){
            data.add(datafield);
            return true;
        }
        return false;
    }
    public void setAllDataFields(List<InsurableDataField> data){
        this.data = data;
    }
    private boolean addData(String title,String type, String data)
    {
        if(!doesTitleExist(title)){
            InsurableDataField newLine = new InsurableDataField(ID, title, type, false, null, data);
            this.data.add(newLine);
            return true;
        }
        return false;
    }

    private boolean addCustomData(String title,String type, String data)
    {
        if(!doesCustomTitleExist(title)){
            InsurableDataField newLine = new InsurableDataField(ID, title, type, true, null, data);
            this.data.add(newLine);
            return true;
        }
        return false;
    }
    public boolean deleteEntry(String title)
    {
        for(int x = 0; x < data.size(); x ++){
            if (data.get(x).getTitle().equals(title)){
                data.remove(x);
                return true;
            }
        }
        return false;
    }
    public boolean deleteCustomEntry(String title)
    {
        for(int x = 0; x < data.size(); x ++){
            if (data.get(x).getTitle().equals(title)){
                data.remove(x);
                return true;
            }
        }
        return false;
    }
    public boolean updateData(String title,String type, String data) {
        for (int x = 0; x < this.data.size(); x++) {
            if (this.data.get(x).getTitle().equals(title) &&!this.data.get(x).isCustom()) {
                this.data.get(x).setData(data);
                this.data.get(x).setDataType(data);
                return true;
            }
        }
        return addData(title, type, data);
    }
    public boolean updateCustomData(String title,String type, String data) {
        for (int x = 0; x < this.data.size(); x++) {
            if (this.data.get(x).getTitle().equals(title)&& this.data.get(x).isCustom()) {
                this.data.get(x).setData(data);
                this.data.get(x).setDataType(data);
                return true;
            }
        }
        return addCustomData( title, type, data);
    }

    //***************DATA RETRIEVAL METHODS*****************************************//
    //Returns the specific data entry aligned with an identical title
    public InsurableDataField getSpecificDataObj(String title){
        for(int index  = 0; index <  data.size();  index ++)
        {
            if(data.get(index).getTitle().equals(title) &&!data.get(index).isCustom()){
                return data.get(index);
            }
        }
        return null;
    }
    public InsurableDataField getSpecificCustomDataObj(String title){
        for(int index  = 0; index <  data.size();  index ++)
        {
            if(data.get(index).getTitle().equals(title)&& data.get(index).isCustom()){
                return data.get(index);
            }
        }
        return null;
    }
    public String getSpecificData(String title){
        for(int index  = 0; index <  data.size();  index ++)
        {
            if(data.get(index).getTitle().equals(title) &&!data.get(index).isCustom()){
                return data.get(index).getData();
            }
        }
        return null;
    }
    public String getSpecificCustomData(String title){
        for(int index  = 0; index <  data.size();  index ++)
        {
            if(data.get(index).getTitle().equals(title)&& data.get(index).isCustom()){
                return data.get(index).getData();
            }
        }
        return null;
    }

    //Returns the type of the data entry aligned with "title" as a string
    public String getpriorityDataType(String title){
        for(int index  = 0; index <  data.size();  index ++)
        {
            if(data.get(index).getTitle().equals(title) &&!data.get(index).isCustom()){
                return data.get(index).getDataType();
            }
        }
        return null;
    }
    public String getCustomDataType(String title){
        for(int index  = 0; index <  data.size();  index ++)
        {
            if(data.get(index).getTitle().equals(title) &&data.get(index).isCustom()){
                return data.get(index).getDataType();
            }
        }
        return null;
    }
    //Returns entire list of data
    public List<InsurableDataField> getData(){
        return data;
    }

}
