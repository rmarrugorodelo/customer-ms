package com.rmarrugo.persistence.inmemory.adapter;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.enumeration.IdentificationType;
import com.rmarrugo.persistence.inmemory.mapper.CustomerInMemoryMapper;
import com.rmarrugo.persistence.inmemory.repository.CustomerInMemoryRepository;
import com.rmarrugo.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Qualifier("inMemoryAdapter")
public class CustomerInMemoryAdapter implements CustomerRepository {

    private static final String UNSUPPORTED_MESSAGE = "Method no implemented yet";

    private final CustomerInMemoryRepository inMemoryRepository;
    private final CustomerInMemoryMapper mapper;

    @Override
    public Optional<Customer> findByIdentification(IdentificationType identificationType, Integer identificationNumber) {
        return inMemoryRepository.findByIdentificationTypeAndIdentificationNumber(
                identificationType, identificationNumber
        ).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return inMemoryRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return inMemoryRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void create(Customer customer) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public void update(Customer customer) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public void delete(Customer customer) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

}
