package com.rmarrugo.rest.dto;

import lombok.Value;

@Value
public class CustomerResponse {

    String firstName;
    String secondName;
    String firstLastName;
    String secondLastName;
    Integer phone;
    String address;
    String city;

}
