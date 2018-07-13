package com.insuranceclaim.insuranceclaim.insurables;
import java.util.ArrayList;

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
    private boolean hasLoaded;
    private String imageFile;
    private String name;
    private String id;
    private String primaryKey;


    //Insurable-specific objects
    private ArrayList<String> priorityData;// This is data that MUST be in each insurable of that type.
    private ArrayList<String> priorityDataTitles;
    private ArrayList<String> priorityDataDisplayCategory;
    private ArrayList<String> data;
    private ArrayList<String> dataTitles;// Titles are labels for the data to be used as headers or hoverables
    private ArrayList<String> dataDisplayCategory;// Category of display, allows for tags to be created as csv

    public Insurable(String name, String id, String primaryKey, insurableTypes type) {
        this.name = name;
        this.id = id;
        this.primaryKey = primaryKey;
        this.type = type;
        isDraft = true;
        priorityData = new ArrayList<>();// This is data that MUST be in each insurable of that type.
        priorityDataTitles = new ArrayList<>();
        priorityDataDisplayCategory = new ArrayList<>();
        dataTitles = new ArrayList<>();// Titles are labels for the data to be used as headers or hoverables
        data = new ArrayList<>();
        dataDisplayCategory = new ArrayList<>();
    }
    //TODO: Add functions that write new data to the insurable
    //TODO: Solidify insurable structure.
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public boolean getIsDraft() {
        return isDraft;
    }
    public boolean doestitleExist(String title){
        for (String currentString:dataTitles){
            if(currentString.equals(title))
            {
                return true;
            }
        }
            return false;
    }
    //Returns entire list of data
    public ArrayList<String> getData(){
        return data;
    }
    public String getSpecificData(String title){
        for(int index  = 0; index <  data.size();  index ++)
        {
            if(dataTitles.get(index).equals(title)){
            return data.get(index);
            }
        }
        return null;
    }
    public void saveInsurable(){
        //TODO: Complete method to save data
    }

}
