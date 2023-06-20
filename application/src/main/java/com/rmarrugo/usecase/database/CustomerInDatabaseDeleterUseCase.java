package com.rmarrugo.usecase.database;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.exception.NotFoundException;
import com.rmarrugo.port.in.CustomerDeleter;
import com.rmarrugo.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomerInDatabaseDeleterUseCase implements CustomerDeleter {

    private final CustomerRepository repository;

    @Override
    public void delete(Long id) throws NotFoundException {
        Customer customer = findById(id);
        repository.delete(customer);
    }

    public Customer findById(Long id) throws NotFoundException {
        log.info("CustomerInDatabaseDeleterUseCase:findById with id {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.info("CustomerInDatabaseDeleterUseCase:findById with id {} no found", id);
                    return new NotFoundException("Customer not found");
                });
    }

}
