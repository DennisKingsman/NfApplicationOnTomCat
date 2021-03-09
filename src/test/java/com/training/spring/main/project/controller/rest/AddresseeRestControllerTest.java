package com.training.spring.main.project.controller.rest;

import com.trainig.spring.main.project.controller.rest.AddresseeRestController;
import com.trainig.spring.main.project.repository.addressee.AddresseRepositoryImpl;
import com.trainig.spring.main.project.repository.addressee.AddresseeRepository;
import com.trainig.spring.main.project.service.addressee.AddresseeService;
import com.trainig.spring.main.project.service.addressee.AddresseeServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@WebMvcTest
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

}
