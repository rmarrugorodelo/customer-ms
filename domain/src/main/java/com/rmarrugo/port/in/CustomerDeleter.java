package com.rmarrugo.port.in;

import com.rmarrugo.exception.NotFoundException;

public interface CustomerDeleter {

    void delete(Long id) throws NotFoundException;

}
