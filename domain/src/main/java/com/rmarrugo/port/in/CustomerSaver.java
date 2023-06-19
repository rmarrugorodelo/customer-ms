package com.rmarrugo.port.in;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.exception.NotFoundException;

public interface CustomerSaver {

    void create(Customer customer);

    void update(Long id, Customer customer) throws NotFoundException;

}
