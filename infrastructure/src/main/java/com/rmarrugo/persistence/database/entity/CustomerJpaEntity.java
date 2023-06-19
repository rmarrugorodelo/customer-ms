package com.rmarrugo.persistence.database.entity;

import com.rmarrugo.enumeration.IdentificationType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "customers")
public class CustomerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "identification_type", nullable = false)
    @Enumerated(EnumType.STRING)
    IdentificationType identificationType;

    @Column(name = "identification_number", nullable = false)
    Integer identificationNumber;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "second_name")
    String secondName;

    @Column(name = "first_lastname", nullable = false)
    String firstLastName;

    @Column(name = "second_lastname")
    String secondLastName;

    @Column(name = "phone", nullable = false)
    Integer phone;

    @Column(name = "address", nullable = false)
    String address;

    @Column(name = "city", nullable = false)
    String city;

}
