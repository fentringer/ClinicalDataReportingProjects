package com.fentringer.clinicals.messages;

public enum ErrorMessages {

    PATIENT_NOT_FOUND("Patient not found"),
    CLINICAL_DATA_NULL("Clinical data is null");

    private final String keyMessage;

    ErrorMessages(final String keyMessage){
        this.keyMessage = keyMessage;
    }

    public String getKeyMessage(){
        return keyMessage;
    }
}
