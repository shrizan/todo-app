package shrizan.com.github.todoapp;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import shrizan.com.github.todoapp.config.TestContainerConfig;

/**
 * @author Shreejan Acharya on 5/10/2023
 * @project todo-app
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestContainerConfig.class)
@RunWith(SpringRunner.class)
public abstract class AbstractIntegrationTest {

}

