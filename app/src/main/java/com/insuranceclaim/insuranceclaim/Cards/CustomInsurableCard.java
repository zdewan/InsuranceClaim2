package com.insuranceclaim.insuranceclaim.Cards;

import android.media.Image;
import android.support.annotation.NonNull;

import com.insuranceclaim.insuranceclaim.insurables.Insurable;

/**
 * Created by kyrel_000 on 2018-06-21.
 * Originally created in InsuranceReport.
 */

public class CustomInsurableCard extends InsurableCard {


    public CustomInsurableCard(Insurable insurable) {
        super(insurable);
    }

    @Override
    public Image getLogo() {
        return null;
    }

    @Override
    public String getHeader() {
        return null;
    }

    @Override
    public String getImportant() {
        return null;
    }

    @Override
    protected void createInsurable() {

    }
}
