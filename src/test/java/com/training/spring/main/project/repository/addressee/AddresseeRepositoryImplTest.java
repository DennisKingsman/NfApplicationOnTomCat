package com.training.spring.main.project.repository.addressee;

import com.trainig.spring.main.project.entity.Addressee;
import com.trainig.spring.main.project.repository.addressee.AddresseRepositoryImpl;
import com.trainig.spring.main.project.repository.addressee.AddresseeRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static com.trainig.spring.main.project.utils.ModelUtil.setupAddressee;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class AddresseeRepositoryImplTest {

    private static AddresseeRepository addresseeRepository;

    @BeforeClass
    public static void initDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("init.sql")
                .addScript("insert.sql")
                .build();
        addresseeRepository = new AddresseRepositoryImpl();
        addresseeRepository.setDataSource(dataSource);
    }

    @Test
    public void getAllByUserIdTest() {
        assertNotNull(addresseeRepository.getAllByUserId(1));
    }

    @Test
    public void updateSuccessfulTest() {
        Addressee addressee = setupAddressee();
        assertEquals(1, addresseeRepository.update(addressee));
    }

    @Test
    public void updateUnsuccessfulTest() {
        Addressee addressee = setupAddressee();
        addressee.setAddresseeId(45);
        assertEquals(0, addresseeRepository.update(addressee));
    }

    @Test
    public void deleteSuccessfulTest() {
        assertEquals(1, addresseeRepository.delete(2));
    }

    @Test
    public void deleteUnsuccessfulTest() {
        assertEquals(0, addresseeRepository.delete(45));
    }

    @Test
    public void isAddresseeExistsSuccessfulTest() {
        assertTrue(addresseeRepository.isAddresseeExists("name2", "mail2"));
    }

    @Test
    public void isAddresseeExistsUnsuccessfulTest() {
        assertFalse(addresseeRepository.isAddresseeExists("failName", "failMail"));
    }

}
