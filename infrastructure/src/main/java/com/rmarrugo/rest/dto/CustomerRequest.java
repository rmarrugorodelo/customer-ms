package com.rmarrugo.rest.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class CustomerRequest {

    @NotBlank
    String identificationType;

    @NotNull
    Integer identificationNumber;

    @NotBlank
    String firstName;

    String secondName;

    @NotBlank
    String firstLastName;


    String secondLastName;

    @NotNull
    Integer phone;

    @NotBlank
    String address;

    @NotBlank
    String city;
}
