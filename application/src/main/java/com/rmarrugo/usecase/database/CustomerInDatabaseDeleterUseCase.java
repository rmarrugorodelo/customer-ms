package com.rmarrugo.usecase.database;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.exception.NotFoundException;
import com.rmarrugo.port.in.CustomerDeleter;
import com.rmarrugo.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerInDatabaseDeleterUseCase implements CustomerDeleter {

    private final CustomerRepository repository;

    @Override
    public void delete(Long id) throws NotFoundException {
        Customer customer = findById(id);
        repository.delete(customer);
    }

    public Customer findById(Long id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

}
