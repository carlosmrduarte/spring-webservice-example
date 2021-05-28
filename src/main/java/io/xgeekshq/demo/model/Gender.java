package io.xgeekshq.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Gender */
public enum Gender {
    FEMALE("F"),
    MALE("M");

    private String value;

    private Gender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Gender fromValue(String value) {
        if ("F".equalsIgnoreCase(value)) {
            return Gender.FEMALE;
        } else if ("M".equalsIgnoreCase(value)) {
            return Gender.MALE;
        }

        return null;
    }
}
