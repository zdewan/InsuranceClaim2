package com.insuranceclaim.insuranceclaim.Cards;

import android.media.Image;

import com.insuranceclaim.insuranceclaim.insurables.Insurable;

/**
 * Created by kyrel_000 on 2018-06-21.
 * Originally created in InsuranceReport.
 * @author Kyrel Jerome
 * */

public abstract class InsurableCard {
    private String insurableType;
    private String holder;
    private String header;
    private String[] layout;
    private String important;
    private Image logo;
    Insurable insurable;
    /*
    Loads logo image from its assigned location.
     */
    public  void loadLogo (){

    }
    public void loadInsurable(){
        getHeader();
        getImportant();

        //specialLoad();
    }
    protected void setinsurable(Insurable insurable){
        this.insurable = insurable;
    }
    Insurable getinsurable() {
        return insurable;
    }
    public abstract Image getLogo();
    public abstract String getHeader();
    public abstract String getImportant();
    protected abstract void createInsurable();
}
