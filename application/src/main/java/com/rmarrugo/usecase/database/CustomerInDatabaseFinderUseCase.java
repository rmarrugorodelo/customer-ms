package com.rmarrugo.usecase.database;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.enumeration.IdentificationType;
import com.rmarrugo.exception.NotFoundException;
import com.rmarrugo.exception.PreconditionFailedException;
import com.rmarrugo.port.in.CustomerFinder;
import com.rmarrugo.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CustomerInDatabaseFinderUseCase implements CustomerFinder {

    private final CustomerRepository repository;

    public Customer findByIdentification(String identificationType, Integer identificationNumber) throws NotFoundException, PreconditionFailedException {
        log.info(
                "CustomerInDatabaseFinderUseCase:findByIdentification with identificationType {} and identificationNumber {}",
                identificationType,
                identificationNumber
        );
        IdentificationType type = IdentificationType.findByCode(identificationType);
        return repository.findByIdentification(type, identificationNumber)
                .orElseThrow(() -> {
                    log.warn(
                            "CustomerInDatabaseFinderUseCase:findByIdentification with identificationType {} " +
                                    "and identificationNumber {} no fount",
                            type.getDescription(),
                            identificationNumber
                    );
                    return new NotFoundException("Customer not found");
                });
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }


}
