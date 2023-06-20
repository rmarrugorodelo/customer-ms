package com.rmarrugo.persistence.inmemory;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.enumeration.IdentificationType;
import com.rmarrugo.faker.TestConstant;
import com.rmarrugo.persistence.inmemory.adapter.CustomerInMemoryAdapter;
import com.rmarrugo.persistence.inmemory.mapper.CustomerInMemoryMapper;
import com.rmarrugo.persistence.inmemory.repository.CustomerInMemoryRepository;
import com.rmarrugo.port.out.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerInMemoryAdapterTest {

    private CustomerRepository repository;

    @BeforeEach
    public void setup() {
        CustomerInMemoryMapper mapper = Mappers.getMapper(CustomerInMemoryMapper.class);
        repository = new CustomerInMemoryAdapter(new CustomerInMemoryRepository(), mapper);
    }

    @Test
    void testFindByIdentificationOk() {
        Optional<Customer> customer = repository.findByIdentification(
                IdentificationType.IDENTIFICATION, TestConstant.IDENTIFICATION_NUMBER
        );
        Assertions.assertFalse(customer.isEmpty());
    }

    @Test
    void testFindByIdentificationEmpty() {
        Optional<Customer> customer = repository.findByIdentification(
                IdentificationType.IDENTIFICATION, TestConstant.BAD_IDENTIFICATION_NUMBER
        );
        Assertions.assertTrue(customer.isEmpty());
    }

}
