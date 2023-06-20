package com.rmarrugo.rest.inmemory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmarrugo.domain.Customer;
import com.rmarrugo.exception.NotFoundException;
import com.rmarrugo.faker.TestConstant;
import com.rmarrugo.port.in.CustomerFinder;
import com.rmarrugo.rest.ControllerAdvisor;
import com.rmarrugo.rest.controller.CustomerInMemoryController;
import com.rmarrugo.rest.mapper.CustomerRestMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerInMemoryControllerTest {

    private static final String V1_PATH = "/v1/customers";

    @Mock
    CustomerFinder finder;

    @Mock
    CustomerRestMapper mapper;

    @InjectMocks
    CustomerInMemoryController controller;

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerAdvisor()).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testFindByIdentificationInDatabaseOk() throws Exception {
        final String path = V1_PATH + "/{identificationType}/{identificationNumber}";
        EasyRandom easyRandom = new EasyRandom();
        var customer = easyRandom.nextObject(Customer.class);
        Mockito.when(finder.findByIdentification(any(), any()))
                .thenReturn(customer);
        this.mockMvc.perform(get(path, "C", TestConstant.IDENTIFICATION_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void testFindByIdentificationInDatabaseBadRequest() throws Exception {
        final String path = V1_PATH + "/{identificationType}/{identificationNumber}";
        EasyRandom easyRandom = new EasyRandom();
        var customer = easyRandom.nextObject(Customer.class);
        Mockito.when(finder.findByIdentification(any(), any()))
                .thenReturn(customer);
        this.mockMvc.perform(get(path, "C", "34r546fd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void testFindByIdentificationInDatabaseNotFound() throws Exception {
        final String path = V1_PATH + "/{identificationType}/{identificationNumber}";
        Mockito.when(finder.findByIdentification(any(), any()))
                .thenThrow(new NotFoundException(""));
        this.mockMvc.perform(get(path, "C", TestConstant.IDENTIFICATION_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }


    @Test
    void testFindAllInDatabaseOk() throws Exception {
        this.mockMvc.perform(get(V1_PATH, "C", TestConstant.IDENTIFICATION_NUMBER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

}
