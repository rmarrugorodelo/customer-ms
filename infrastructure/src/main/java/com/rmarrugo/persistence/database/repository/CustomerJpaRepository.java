package com.rmarrugo.persistence.database.repository;

import com.rmarrugo.enumeration.IdentificationType;
import com.rmarrugo.persistence.database.entity.CustomerJpaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerJpaRepository extends CrudRepository<CustomerJpaEntity, Long> {

    Optional<CustomerJpaEntity> findByIdentificationTypeAndIdentificationNumber(IdentificationType identificationType, Integer identificationNumber);

}
