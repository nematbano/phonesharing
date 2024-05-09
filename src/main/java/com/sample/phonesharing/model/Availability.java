package com.sample.phonesharing.model;

import lombok.Getter;

@Getter
public enum Availability {
    YES("Yes"),
    NO("No");

    private final String value;

    Availability(String value) {
        this.value = value;
    }

}
