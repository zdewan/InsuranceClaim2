package com.insuranceclaim.insuranceclaim.Cards;

import android.media.Image;
import android.support.annotation.NonNull;

import com.insuranceclaim.insuranceclaim.insurables.Insurable;

/**
 * Created by kyrel_000 on 2018-06-21.
 * Originally created in InsuranceReport.
 */
//TODO: FORMAT CARD TO BE OF THIS CARD TYPE
public class HealthInsurableCard extends InsurableCard{
    //Main information (priority information) that each AutomobileInsurableCard must have
    public final String[] priorityTitles = {"model","make","VIN","year", "insurer","owner", "company"
            ,"phone", "policynumber", "coverage", "deductibles"};
    public String[] priorityData = new String[priorityTitles.length];
    public HealthInsurableCard(@NonNull Insurable insurable) {
        super(insurable);
        updateDataFromInsurable();
        setHeader(getHeader());
        setImportant(getImportant());
        setLogo(getLogo());
    }

    @Override
    public String[] getPriorityFields() {
        return new String[0];
    }

    public void getpriorityData(){

    }
    @Override
    public String getHeader() {
        String header = null;
        if (insurable != null) {
            header = insurable.getName();
            if(header == null){
                return "Auto:" + getInsurable().getSpecificData(priorityTitles[0]);
            }
        }
        return header;
    }

    @Override
    public void createInsurable(){
         Insurable insurable = new Insurable();

     }
     //this is the lazy way
     public void updateDataFromInsurable(){
         for (int i = 0; i < priorityTitles.length; i ++){
             priorityData[i] = insurable.getSpecificData(priorityTitles[i]);
         }
     }
    @Override
    public Image getLogo(){
        return null;
        //TODO: CREATE A SYSTEM FOR LOGOS TO BE SELECTED FOR EACH CARD, OTHERWISE GET THE "LOGOCAR" IMAGE.
    }
    public String getImportant(){
        String header = null;
        if (insurable != null) {
            header = insurable.getName();
            if(header == null){
                return "Auto:" + getInsurable().getSpecificData("model");
            }
        }
        return header;
    }
}
