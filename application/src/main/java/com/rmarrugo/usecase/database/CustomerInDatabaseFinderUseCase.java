package com.rmarrugo.usecase.database;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.enumeration.IdentificationType;
import com.rmarrugo.exception.NotFoundException;
import com.rmarrugo.exception.PreconditionFailedException;
import com.rmarrugo.port.in.CustomerFinder;
import com.rmarrugo.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomerInDatabaseFinderUseCase implements CustomerFinder {

    private final CustomerRepository repository;

    public Customer findByIdentification(String identificationType, Integer identificationNumber) throws NotFoundException, PreconditionFailedException {
        IdentificationType type = IdentificationType.findByCode(identificationType);
        return repository.findByIdentification(type, identificationNumber)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }


}
