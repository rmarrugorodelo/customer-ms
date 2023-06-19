package com.rmarrugo.persistence.inmemory.repository;

import com.rmarrugo.enumeration.IdentificationType;
import com.rmarrugo.persistence.inmemory.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerInMemoryRepository {

    private static final List<CustomerEntity> CUSTOMERS = List.of(
            CustomerEntity
                    .builder()
                    .id(1L)
                    .identificationNumber(23445322)
                    .identificationType(IdentificationType.IDENTIFICATION)
                    .phone(23423423)
                    .city("Cartagena")
                    .firstName("Raul")
                    .secondName("Gonzales")
                    .address("Cra 34 # 45-54")
                    .build()
    );

    public Optional<CustomerEntity> findByIdentificationTypeAndIdentificationNumber(IdentificationType identificationType,
                                                                                    Integer identificationNumber) {
        return CUSTOMERS
                .stream()
                .filter(customerEntity ->
                        customerEntity.getIdentificationNumber().equals(identificationNumber)
                        && customerEntity.getIdentificationType().equals(identificationType)
                )
                .findFirst();
    }

    public Optional<CustomerEntity> findById(Long id) {
        return CUSTOMERS
                .stream()
                .filter(customerEntity -> customerEntity.getId().equals(id))
                .findFirst();
    }

    public List<CustomerEntity> findAll() {
        return CUSTOMERS;
    }
}
