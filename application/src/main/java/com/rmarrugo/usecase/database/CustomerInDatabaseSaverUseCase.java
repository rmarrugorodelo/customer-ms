package com.rmarrugo.usecase.database;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.exception.NotFoundException;
import com.rmarrugo.port.in.CustomerSaver;
import com.rmarrugo.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerInDatabaseSaverUseCase implements CustomerSaver {

    private final CustomerRepository repository;

    @Override
    public void create(Customer customer) {
        repository.create(customer);
    }

    @Override
    public void update(Long id, Customer customer) throws NotFoundException {
        exists(id);
        repository.update(
                customer.withId(id)
        );
    }

    public void exists(Long id) throws NotFoundException {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }
}
