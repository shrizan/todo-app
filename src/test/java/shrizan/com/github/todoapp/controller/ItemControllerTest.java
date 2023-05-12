package shrizan.com.github.todoapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import shrizan.com.github.todoapp.AbstractIntegrationTest;
import shrizan.com.github.todoapp.domain.user.User;
import shrizan.com.github.todoapp.dto.ItemDTO;
import shrizan.com.github.todoapp.repos.ItemRepo;
import shrizan.com.github.todoapp.repos.UserRepo;

/**
 * @author Shreejan Acharya on 5/11/2023
 * @project todo-app
 */
public class ItemControllerTest extends AbstractIntegrationTest {
    private ObjectMapper objectMapper=new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    void addDefaultUser(){
        User user = new User();
        user.setUserName("john");
        user.setPassword("$2a$10$9.MHA6NGehPU6opqbYOgUO1dl.hUE1XJysj4dZStmZIe2faKYcM5C");
        userRepo.save(user);
    }

    @AfterEach
    void cleanup() {
        itemRepo.deleteAll();
    }

    @Test
    public void create() throws Exception {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setTitle("item_1");
        itemDTO.setCompleted(true);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDTO))
                        .header("jwt-token","eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ0b2RvLWFwcCIsInN1YiI6ImpvaG4iLCJpYXQiOjE2ODM4NDk1MDcsImV4cCI6MTY4MzkzNTkwNywianRpIjoiYmQwZTU4NmQtNmM1Yy00YzEyLWE0YjAtNjRjNWE4OWJiM2FiIn0.E1QLz5F0sB8WmSlbktEHOjqpM4rnM6kLSp7BZv1YfpQ")
        ).andExpect(MockMvcResultMatchers.status().isCreated());

    }

}
