package com.cydeo.enums;

public enum Gender {

    MALE("Male"),FEMALE("Female");
//When using I do not want to see MALE but Male because of that I create an instance variable and by constructor I assign value to the ENUMS.
    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
