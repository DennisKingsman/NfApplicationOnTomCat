package com.training.spring.main.project.service.addressee;

import com.trainig.spring.main.project.repository.addressee.AddresseRepositoryImpl;
import com.trainig.spring.main.project.repository.addressee.AddresseeRepository;
import com.trainig.spring.main.project.service.addressee.AddresseeService;
import com.trainig.spring.main.project.service.addressee.AddresseeServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class AddresseeServiceImplTest {

    private static AddresseeService addresseeService;

    @BeforeClass
    public static void initDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("init.sql")
                .addScript("insert.sql")
                .build();
        AddresseeRepository addresseeRepository = new AddresseRepositoryImpl();
        addresseeRepository.setDataSource(dataSource);
        addresseeService = new AddresseeServiceImpl();
        addresseeService.setAddresseeRepository(addresseeRepository);
    }

    @Test
    public void getAllByUserIdTest() {
        assertNotNull(addresseeService.getAllByUserId(1));
    }

    @Test
    public void deleteTrueTest() {
        assertTrue(addresseeService.delete(2));
    }

    @Test
    public void deleteFalseTest() {
        assertFalse(addresseeService.delete(45));
    }

}
