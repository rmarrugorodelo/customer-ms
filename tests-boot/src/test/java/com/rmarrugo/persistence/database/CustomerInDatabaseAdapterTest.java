package com.rmarrugo.persistence.database;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.enumeration.IdentificationType;
import com.rmarrugo.faker.TestConstant;
import com.rmarrugo.persistence.database.adapter.CustomerInDatabaseAdapter;
import com.rmarrugo.persistence.database.entity.CustomerJpaEntity;
import com.rmarrugo.persistence.database.mapper.CustomerInDatabaseMapper;
import com.rmarrugo.persistence.database.repository.CustomerJpaRepository;
import com.rmarrugo.port.out.CustomerRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerInDatabaseAdapterTest {

    @Autowired
    private CustomerJpaRepository jpaRepository;

    private CustomerRepository repository;

    @BeforeEach
    public void setup() {
        CustomerInDatabaseMapper mapper = Mappers.getMapper( CustomerInDatabaseMapper.class );
        repository = new CustomerInDatabaseAdapter(jpaRepository, mapper);
        jpaRepository.deleteAll();
    }

    @Test
    void testFindByIdentificationOk() {
        EasyRandom easyRandom = new EasyRandom();
        CustomerJpaEntity entity = easyRandom.nextObject(CustomerJpaEntity.class);
        entity.setId(null);
        entity.setIdentificationType(IdentificationType.IDENTIFICATION);
        entity.setIdentificationNumber(TestConstant.IDENTIFICATION_NUMBER);
        jpaRepository.save(entity);
        Optional<Customer> customer = repository.findByIdentification(
                IdentificationType.IDENTIFICATION, TestConstant.IDENTIFICATION_NUMBER
        );
        Assertions.assertFalse(customer.isEmpty());
    }

    @Test
    void testFindByIdentificationEmpty() {
        Optional<Customer> customer = repository.findByIdentification(
                IdentificationType.IDENTIFICATION, TestConstant.IDENTIFICATION_NUMBER
        );
        Assertions.assertTrue(customer.isEmpty());
    }

}
