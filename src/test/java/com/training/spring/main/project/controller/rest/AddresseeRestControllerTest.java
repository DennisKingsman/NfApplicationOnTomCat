package com.training.spring.main.project.controller.rest;

import com.trainig.spring.main.project.controller.rest.AddresseeRestController;
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
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class AddresseeRestControllerTest {

    private static final String USER_ID = "userId";
    private static final String ADDRESSEES = "addressees";

    private static AddresseeRestController addresseeRestController;

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
        addresseeRestController = new AddresseeRestController();
        addresseeRestController.setAddresseeService(addresseeService);
    }

    @Test
    public void getAllAddresseeTest() {
        ModelAndView modelAndView = addresseeRestController
                .getAllAddressee(1L, new ModelAndView());
        assertEquals(ADDRESSEES, modelAndView.getViewName());
        Map<String, Object> map = modelAndView.getModel();
        assertNotNull(map.get(ADDRESSEES));
        assertNotNull(map.get(USER_ID));
    }

    @Test
    public void deleteAddresseeTest() {
        ModelAndView modelAndView = addresseeRestController
                .deleteAddressee(3L, 2L, new ModelAndView());
        assertEquals(ADDRESSEES, modelAndView.getViewName());
        Map<String, Object> map = modelAndView.getModel();
        assertNotNull(map.get(ADDRESSEES));
        assertNotNull(map.get(USER_ID));
    }

    @Test
    public void getAddAddresseePageTest() {
        ModelAndView modelAndView = addresseeRestController
                .getAddAddresseePage(1L, new ModelAndView());
        assertEquals("newAddressee", modelAndView.getViewName());
        Map<String, Object> map = modelAndView.getModel();
        assertNotNull(map.get(USER_ID));
    }

}
