package com.insuranceclaim.insuranceclaim.insurables;

/**
 * Created by kyrel_000 on 2018-08-23.
 * Originally created in InsuranceClaim2.
 */
public class NoInsurableException extends Exception {
    @Override
    public String getMessage() {
        return "There is no insurable at this area.";
    }
}
