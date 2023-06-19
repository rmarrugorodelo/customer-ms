package com.rmarrugo.port.out;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.enumeration.IdentificationType;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findByIdentification(IdentificationType identificationType, Integer identificationNumber);

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

    void create(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);

}
