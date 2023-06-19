package com.rmarrugo.persistence.inmemory.entity;

import com.rmarrugo.enumeration.IdentificationType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerEntity {
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
