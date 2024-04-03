package ru.medonline.generation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String city;
    private String street;
    private String house;
    private String flat;

    private Integer cityId;

    private String full;

    public Address(String full) {
        this.full = full;
    }

    public Address(String city, String street, String house, String flat, Integer cityId) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
        this.cityId = cityId;
    }


}
