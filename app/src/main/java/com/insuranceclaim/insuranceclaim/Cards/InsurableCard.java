package com.insuranceclaim.insuranceclaim.Cards;

import android.media.Image;
import android.support.annotation.NonNull;

import com.insuranceclaim.insuranceclaim.insurables.Insurable;

/**
 * Created by kyrel_000 on 2018-06-21.
 * Originally created in InsuranceReport.
 * @author Kyrel Jerome
 * */

public abstract class InsurableCard {
    private String header;
    private String layout;
    private String important;
    private Image logo;
    Insurable insurable;
    /*
    Loads logo image from its assigned location.
     */

    public InsurableCard(Insurable insurable) {
        this.insurable = insurable;
    }
    public InsurableCard(String header, String layout, String important, Image logo, @NonNull Insurable insurable) {
        this.header = header;
        this.layout = layout;
        this.important = important;
        this.logo = logo;
        this.insurable = insurable;

    }

    public  void loadLogo (){

    }
    public void loadInsurable(){
        getHeader();
        getImportant();

        //specialLoad();
    }
    public Insurable getInsurable() {
        return insurable;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }
    public abstract String[] getPriorityFields();
    public abstract Image getLogo();
    public abstract String getHeader();
    public abstract String getImportant();
    protected abstract void createInsurable();
}
