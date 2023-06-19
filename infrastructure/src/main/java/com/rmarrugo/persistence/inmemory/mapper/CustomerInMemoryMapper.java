package com.rmarrugo.persistence.inmemory.mapper;


import com.rmarrugo.domain.Customer;
import com.rmarrugo.persistence.inmemory.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CustomerInMemoryMapper {

    Customer toDomain(CustomerEntity entity);

    CustomerEntity toEntity(Customer domain);

}
