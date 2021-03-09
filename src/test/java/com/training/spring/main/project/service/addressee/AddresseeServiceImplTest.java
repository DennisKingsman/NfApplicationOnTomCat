package com.training.spring.main.project.service.addressee;

import com.trainig.spring.main.project.repository.addressee.AddresseRepositoryImpl;
import com.trainig.spring.main.project.repository.addressee.AddresseeRepository;
import com.trainig.spring.main.project.service.addressee.AddresseeService;
import com.trainig.spring.main.project.service.addressee.AddresseeServiceImpl;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
public class AddresseeServiceImplTest {

    private AddresseeService addresseeService;

    @BeforeClass
    public static void initDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("init.sql")
                .addScript("insert.sql")
                .build();
        AddresseeRepository addresseeRepository = new AddresseRepositoryImpl();
        addresseeRepository.setDataSource(dataSource);
        AddresseeService addresseeService = new AddresseeServiceImpl();
        addresseeService.setAddresseeRepository(addresseeRepository);
    }
    
}
