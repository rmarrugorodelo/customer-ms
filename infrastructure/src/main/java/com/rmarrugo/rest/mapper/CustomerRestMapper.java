package com.rmarrugo.rest.mapper;


import com.rmarrugo.domain.Customer;
import com.rmarrugo.rest.dto.CustomerRequest;
import com.rmarrugo.rest.dto.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CustomerRestMapper {

    Customer toDomain(CustomerRequest dto);

    CustomerResponse toResponse(Customer domain);

}
