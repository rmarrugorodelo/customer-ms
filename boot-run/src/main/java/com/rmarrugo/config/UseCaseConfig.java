package com.rmarrugo.config;

import com.rmarrugo.port.out.CustomerRepository;
import com.rmarrugo.usecase.database.CustomerInDatabaseDeleterUseCase;
import com.rmarrugo.usecase.database.CustomerInDatabaseFinderUseCase;
import com.rmarrugo.usecase.database.CustomerInDatabaseSaverUseCase;
import com.rmarrugo.usecase.inmemory.CustomerInMemoryDeleterUseCase;
import com.rmarrugo.usecase.inmemory.CustomerInMemoryFinderUseCase;
import com.rmarrugo.usecase.inmemory.CustomerInMemorySaverUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean(name="inDatabaseSaverUseCase")
    public CustomerInDatabaseSaverUseCase customerSaverUseCase(@Qualifier("inDatabaseAdapter") CustomerRepository repository) {
        return new CustomerInDatabaseSaverUseCase(repository);
    }

    @Bean(name="inDatabaseFinderUseCase")
    public CustomerInDatabaseFinderUseCase customerFinderUseCase(@Qualifier("inDatabaseAdapter") CustomerRepository repository) {
        return new CustomerInDatabaseFinderUseCase(repository);
    }

    @Bean(name="inDatabaseDeleterUseCase")
    public CustomerInDatabaseDeleterUseCase customerDeleterUseCase(@Qualifier("inDatabaseAdapter") CustomerRepository repository) {
        return new CustomerInDatabaseDeleterUseCase(repository);
    }

    @Bean(name="inMemorySaverUseCase")
    public CustomerInMemorySaverUseCase customerInMemorySaverUseCase(@Qualifier("inMemoryAdapter") CustomerRepository repository) {
        return new CustomerInMemorySaverUseCase(repository);
    }

    @Bean(name="inMemoryFinderUseCase")
    public CustomerInMemoryFinderUseCase customerInMemoryFinderUseCase(@Qualifier("inMemoryAdapter") CustomerRepository repository) {
        return new CustomerInMemoryFinderUseCase(repository);
    }

    @Bean(name="inMemoryDeleterUseCase")
    public CustomerInMemoryDeleterUseCase customerInMemoryDeleterUseCase(@Qualifier("inMemoryAdapter") CustomerRepository repository) {
        return new CustomerInMemoryDeleterUseCase(repository);
    }

}
