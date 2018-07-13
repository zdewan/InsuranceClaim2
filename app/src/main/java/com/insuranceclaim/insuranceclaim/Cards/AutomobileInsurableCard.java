package com.insuranceclaim.insuranceclaim.Cards;

import android.media.Image;

/**
 * Created by kyrel_000 on 2018-06-21.
 * Originally created in InsuranceReport.
 */

public class AutomobileInsurableCard extends InsurableCard{
    public final String[] ImportantTitles = {"VIN",""};
    private String model;
    private String make;
    private String year;
    private String insurerer;
    @Override
    public String getHeader() {
        String header = null;
        if (insurable != null) {
            header = insurable.getName();
            if(header == null){
                return "Auto:" + getinsurable().getSpecificData("model");
            }
        }
        return header;
    }

    @Override
    public void createInsurable() {

    }
    @Override
    public Image getLogo(){
        return null;
    }
    public String getImportant(){
        return null;
    }
}
