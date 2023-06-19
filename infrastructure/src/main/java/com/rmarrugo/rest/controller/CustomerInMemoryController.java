package com.rmarrugo.rest.controller;

import com.rmarrugo.exception.NotFoundException;
import com.rmarrugo.exception.PreconditionFailedException;
import com.rmarrugo.port.in.CustomerDeleter;
import com.rmarrugo.port.in.CustomerFinder;
import com.rmarrugo.port.in.CustomerSaver;
import com.rmarrugo.rest.dto.CustomerRequest;
import com.rmarrugo.rest.dto.CustomerResponse;
import com.rmarrugo.rest.mapper.CustomerRestMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("v1/customers")
@RestController
public class CustomerInMemoryController {

    private final CustomerFinder finder;

    private final CustomerSaver saver;

    private final CustomerDeleter deleter;

    private final CustomerRestMapper mapper;

    public CustomerInMemoryController(@Qualifier("inMemoryFinderUseCase") CustomerFinder finder,
                                      @Qualifier("inMemorySaverUseCase") CustomerSaver saver,
                                      @Qualifier("inMemoryDeleterUseCase") CustomerDeleter deleter,
                                      CustomerRestMapper mapper) {
        this.finder = finder;
        this.saver = saver;
        this.deleter = deleter;
        this.mapper = mapper;
    }

    @PostMapping
    public void create(@RequestBody @Valid CustomerRequest request) {
        saver.create(
                mapper.toDomain(request)
        );
    }

    @PutMapping("{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid CustomerRequest request) throws NotFoundException {
        saver.update(
                id,
                mapper.toDomain(request)
        );
    }

    @GetMapping("{identificationType}/{identificationNumber}")
    public CustomerResponse findByIdentificationNumber(
            @PathVariable String identificationType,
            @PathVariable Integer identificationNumber
    ) throws NotFoundException, PreconditionFailedException {
        return mapper.toResponse(
                finder.findByIdentification(identificationType, identificationNumber)
        );
    }

    @GetMapping()
    public List<CustomerResponse> findAll() {
        return finder.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) throws NotFoundException {
        deleter.delete(id);
    }

}
