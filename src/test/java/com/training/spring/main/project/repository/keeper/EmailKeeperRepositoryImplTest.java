package com.training.spring.main.project.repository.keeper;

import com.trainig.spring.main.project.entity.EmailKeeper;
import com.trainig.spring.main.project.repository.keeper.EmailKeeperRepository;
import com.trainig.spring.main.project.repository.keeper.EmailKeeperRepositoryImpl;
import com.trainig.spring.main.project.repository.user.UserRepository;
import com.trainig.spring.main.project.repository.user.UserRepositoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static com.trainig.spring.main.project.utils.ModelUtil.setupEmailKeeper;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class EmailKeeperRepositoryImplTest {

    private static DataSource dataSource;
    private static EmailKeeperRepository emailKeeperRepository;

    @BeforeClass
    public static void initDataSource() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("init.sql")
                .addScript("insert.sql")
                .build();
        emailKeeperRepository = new EmailKeeperRepositoryImpl();
        ((EmailKeeperRepositoryImpl) emailKeeperRepository).setDataSource(dataSource);
    }

    @Test
    public void getSchedulerEmailTest() {
        EmailKeeper expected = setupEmailKeeper();
        String name = expected.getKeeperName();
        assertEquals(expected, emailKeeperRepository.getSchedulerEmail(name));
    }

}
