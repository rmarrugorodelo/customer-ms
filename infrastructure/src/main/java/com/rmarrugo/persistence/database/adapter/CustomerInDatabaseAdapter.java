package com.rmarrugo.persistence.database.adapter;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.enumeration.IdentificationType;
import com.rmarrugo.persistence.database.entity.CustomerJpaEntity;
import com.rmarrugo.persistence.database.mapper.CustomerInDatabaseMapper;
import com.rmarrugo.persistence.database.repository.CustomerJpaRepository;
import com.rmarrugo.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Qualifier("inDatabaseAdapter")
public class CustomerInDatabaseAdapter implements CustomerRepository {

    private final CustomerJpaRepository jpaRepository;
    private final CustomerInDatabaseMapper mapper;

    @Override
    public Optional<Customer> findByIdentification(IdentificationType identificationType, Integer identificationNumber) {
        return jpaRepository.findByIdentificationTypeAndIdentificationNumber(
                identificationType, identificationNumber
        ).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return ((List<CustomerJpaEntity>) jpaRepository.findAll())
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void create(Customer customer) {
        jpaRepository.save(
                mapper.toEntity(customer)
        );
    }

    @Override
    public void update(Customer customer) {
        jpaRepository.save(
                mapper.toEntity(customer)
        );
    }

    @Override
    public void delete(Customer customer) {
        jpaRepository.delete(
                mapper.toEntity(customer)
        );
    }

}
