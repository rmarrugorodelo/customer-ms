package com.rmarrugo.domain;

import com.rmarrugo.enumeration.IdentificationType;
import lombok.Value;
import lombok.With;

@Value
public class Customer {
    @With
    Long id;
    IdentificationType identificationType;
    Integer identificationNumber;
    String firstName;
    String secondName;
    String firstLastName;
    String secondLastName;
    Integer phone;
    String address;
    String city;
}
