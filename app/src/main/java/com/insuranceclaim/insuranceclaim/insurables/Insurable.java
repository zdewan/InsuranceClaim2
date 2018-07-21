package com.insuranceclaim.insuranceclaim.insurables;
import java.util.ArrayList;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
/**
 * Created by kyrel_000 on 2018-07-09.
 * Originally created in InsuranceReport.
 */
// This class is the actual harborer of data for the insurable, but the insurablecards will know
// how to obtain data from these objects, only having 5 cards rendered at once maximum
// in comfortable view and 10 in condensed view

public class Insurable {

    public enum insurableTypes {AUTOMOBILE, HOME, PERSONAL, HEALTH, CUSTOM}
    private insurableTypes type;
    private boolean isDraft;
    private boolean isSaved;
    private String imageFile;
    private String name;
    private String id;// MUST BE INTERNALLY UNIQUE
    private String customDataKey;
    private String priorityDataKey;
    //ALL TITLES ARE UNIQUE, WHETHER THEY ARE PRIORITY OR NOT
    //Insurable-specific objects
    private ArrayList<String> priorityData;// This is data that MUST be in each insurable of that type.
    private ArrayList<String> priorityDataTitles;
    private ArrayList<String> priorityDisplayCategories;
    private ArrayList<String> customData;
    private ArrayList<String> customDataTitles;// Titles are labels for the data to be used as headers or hoverables
    private ArrayList<String> customDisplayCategories;// Category of display, allows for tags to be created as csv

    //Initialization aids
    public Insurable() {
        isDraft = true;
        isSaved = false;
        imageFile = null;
        name = null;
        id = null;
        initInsurableArrayLists();
    }
    public Insurable(String name, String id, String primaryKey, insurableTypes type) {
        this.name = name;
        this.id = id;
        //TODO: primary key
        //this.primaryKey = primaryKey;
        this.type = type;
        isDraft = true;
        initInsurableArrayLists();

    }
    private void initInsurableArrayLists(){
        priorityData = new ArrayList<>();// This is data that MUST be in each insurable of that type.
        priorityDataTitles = new ArrayList<>();
        priorityDisplayCategories = new ArrayList<>();
        customDataTitles = new ArrayList<>();// Titles are labels for the data to be used as headers or hoverables
        customData = new ArrayList<>();
        customDisplayCategories = new ArrayList<>();
    }
    //TODO: Add functions that write new data to the insurable
    //TODO: Solidify insurable structure.
    // Get data required for saving, this is data every card has, regardless of type.
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public boolean getIsDraft() {
        return isDraft;
    }
//*****************DATA ENTRY AND HANDLING METHODS************************************//
    //************Priority data methods are not labelled as such*****************
    public boolean doesTitleExist(String title){
        for (String currentString:priorityDataTitles){
            if(currentString.equals(title))
            {
                return true;
            }
        }
            return false;
    }
    public boolean doesCustomTitleExist(String title){
        for (String currentString:customDataTitles){
            if(currentString.equals(title))
            {
                return true;
            }
        }
        return false;
    }
    private boolean addData(String title,String type, String data)
    {
        if(!doesTitleExist(title)){
            priorityData.add(data);
            priorityDataTitles.add(title);
            priorityDisplayCategories.add(type);
            return true;
        }
        return false;
    }
    private boolean addCustomData(String title,String type, String data)
    {
        if(!doesCustomTitleExist(title)){
            customData.add(data);
            customDataTitles.add(title);
            customDisplayCategories.add(type);
            return true;
        }
        return false;
    }
    public boolean deleteEntry(String title,String type, String data)
    {
        for(int x = 0; x < priorityDataTitles.size(); x ++){
            if (priorityDataTitles.get(x).equals(title)){
                priorityDataTitles.remove(x);
                priorityData.remove(x);
                priorityDisplayCategories.remove(x);
                return true;
            }
        }
        return false;
    }
    public boolean deleteCustomEntry(String title,String type, String data)
    {
        for(int x = 0; x < customDataTitles.size(); x ++){
            if (customDataTitles.get(x).equals(title)){
                customDataTitles.remove(x);
                customData.remove(x);
                customDisplayCategories.remove(x);
                return true;
            }
        }
        return false;
    }
    public boolean updateData(String title,String type, String data) {
        for (int x = 0; x < priorityDataTitles.size(); x++) {
            if (priorityDataTitles.get(x).equals(title)) {
                priorityData.set(x, data);
                priorityDisplayCategories.set(x, data);
                return true;
            }
        }
        return addData( title, type, data);
    }
    public boolean updateCustomData(String title,String type, String data) {
        for (int x = 0; x < customDataTitles.size(); x++) {
            if (customDataTitles.get(x).equals(title)) {
                customData.set(x, data);
                customDisplayCategories.set(x, data);
                return true;
            }
        }
        return addCustomData( title, type, data);
    }

//***************DATA RETRIEVAL METHODS*****************************************//
    //Returns the specific data entry aligned with an identical title
    public String getSpecificData(String title){
        for(int index  = 0; index <  priorityDataTitles.size();  index ++)
        {
            if(priorityDataTitles.get(index).equals(title)){
                return priorityData.get(index);
            }
        }
        return null;
    }
    public String getSpecificCustomData(String title){
        for(int index  = 0; index <  customDataTitles.size();  index ++)
        {
            if(customDataTitles.get(index).equals(title)){
                return customData.get(index);
            }
        }
        return null;
    }

    //Returns the type of the data entry aligned with "title" as a string
    public String getDataType(String title){
        for(int index  = 0; index <  priorityDataTitles.size();  index ++)
        {
            if(priorityDataTitles.get(index).equals(title)){
                return priorityDisplayCategories.get(index);
            }
        }
        return null;
    }
    //Returns entire list of data
    public ArrayList<String> getData(){
        return priorityData;
    }
    public ArrayList<String> getCustomData(){
        return customData;
    }

    public void saveInsurable(){
        //TODO: Complete method to save data
    }

}
