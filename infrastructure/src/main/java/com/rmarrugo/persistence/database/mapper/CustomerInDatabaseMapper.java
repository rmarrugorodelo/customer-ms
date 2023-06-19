package com.rmarrugo.persistence.database.mapper;


import com.rmarrugo.domain.Customer;
import com.rmarrugo.persistence.database.entity.CustomerJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CustomerInDatabaseMapper {

    Customer toDomain(CustomerJpaEntity entity);

    CustomerJpaEntity toEntity(Customer domain);

}
