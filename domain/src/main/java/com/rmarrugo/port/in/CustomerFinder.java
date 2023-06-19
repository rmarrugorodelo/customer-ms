package com.rmarrugo.port.in;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.exception.NotFoundException;
import com.rmarrugo.exception.PreconditionFailedException;

import java.util.List;

public interface CustomerFinder {

    Customer findByIdentification(String identificationType,Integer identificationNumber) throws NotFoundException, PreconditionFailedException;

    List<Customer> findAll();

}
