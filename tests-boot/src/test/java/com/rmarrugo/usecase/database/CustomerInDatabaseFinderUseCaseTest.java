package com.rmarrugo.usecase.database;

import com.rmarrugo.domain.Customer;
import com.rmarrugo.enumeration.IdentificationType;
import com.rmarrugo.exception.NotFoundException;
import com.rmarrugo.exception.PreconditionFailedException;
import com.rmarrugo.faker.TestConstant;
import com.rmarrugo.port.out.CustomerRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CustomerInDatabaseFinderUseCaseTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerInDatabaseFinderUseCase useCase;

    @Test
    void testFindByIdentificationInDatabaseOk() throws NotFoundException, PreconditionFailedException {
        EasyRandom easyRandom = new EasyRandom();
        Customer expected = easyRandom.nextObject(Customer.class);
        expected = expected.withIdentificationNumber(TestConstant.IDENTIFICATION_NUMBER);
        Mockito.when(repository.findByIdentification(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(expected));
        Customer customer = useCase.findByIdentification(
                IdentificationType.IDENTIFICATION.getCode(),
                expected.getIdentificationNumber()
        );
        Assertions.assertEquals(expected.getIdentificationNumber(), customer.getIdentificationNumber());
    }

    @Test
    void testFindByIdentificationInDatabaseException() {
        Mockito.when(repository.findByIdentification(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> useCase.findByIdentification(
                        IdentificationType.IDENTIFICATION.getCode(),
                        TestConstant.IDENTIFICATION_NUMBER
                )
        );
    }

    @Test
    void testFindAllInDatabaseOk() {
        EasyRandom easyRandom = new EasyRandom();
        Customer expected = easyRandom.nextObject(Customer.class);
        expected = expected.withIdentificationNumber(TestConstant.IDENTIFICATION_NUMBER);
        Mockito.when(repository.findAll())
                .thenReturn(List.of(expected));
        List<Customer> customers = useCase.findAll();
        Assertions.assertEquals(expected.getIdentificationNumber(), customers.get(0).getIdentificationNumber());
    }

}
