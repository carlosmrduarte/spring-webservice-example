package io.xgeekshq.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.xgeekshq.demo.dto.UserDto;
import io.xgeekshq.demo.model.Gender;
import java.time.LocalDate;
import java.time.Month;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Transactional // to rollback changes to the database
    void createUser(@Autowired ObjectMapper jsonMapper) throws Exception {
        this.mvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$").value(Matchers.hasSize(1)));

        UserDto dto = new UserDto();
        dto.setBirthDate(LocalDate.of(2010, Month.JANUARY, 01));
        dto.setEmail("test@test.test");
        dto.setGender(Gender.FEMALE);
        dto.setLastName("lastName");
        dto.setFirstName("firstName");

        this.mvc.perform(
                post("/api/user").contentType(MediaType.APPLICATION_JSON).content(jsonMapper.writeValueAsString(dto))
            )
            .andExpect(status().isCreated());

        this.mvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$").value(Matchers.hasSize(2)))
            .andExpect(jsonPath("$.[*].firstName").value(Matchers.hasItem(dto.getFirstName())))
            .andExpect(jsonPath("$.[*].lastName").value(Matchers.hasItem(dto.getLastName())))
            .andExpect(jsonPath("$.[*].email").value(Matchers.hasItem(dto.getEmail())))
            .andExpect(jsonPath("$.[*].gender").value(Matchers.hasItem(dto.getGender().getValue())))
            .andExpect(jsonPath("$.[*].birthDate").value(Matchers.hasItem(dto.getBirthDate().toString())));
    }

    @Test
    void getUserById() throws Exception {
        this.mvc.perform(get("/api/user/{userId}", 1).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.firstName").value("Carlos"))
            .andExpect(jsonPath("$.lastName").value("Duarte"))
            .andExpect(jsonPath("$.email").value("c.duarte@xgeeks.io"))
            .andExpect(jsonPath("$.gender").value(Gender.MALE.getValue()))
            .andExpect(jsonPath("$.birthDate").value(LocalDate.of(1992, Month.APRIL, 10).toString()));
    }

    @Test
    void listUsers() throws Exception {
        this.mvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$").value(Matchers.hasSize(1)))
            .andExpect(jsonPath("$.[0].firstName").value("Carlos"))
            .andExpect(jsonPath("$.[0].lastName").value("Duarte"))
            .andExpect(jsonPath("$.[0].email").value("c.duarte@xgeeks.io"))
            .andExpect(jsonPath("$.[0].gender").value(Gender.MALE.getValue()))
            .andExpect(jsonPath("$.[0].birthDate").value(LocalDate.of(1992, Month.APRIL, 10).toString()));
    }

    @Test
    @Transactional // to rollback changes to the database
    void deleteUser() throws Exception {
        this.mvc.perform(delete("/api/user/{userId}", 1)).andExpect(status().isNoContent());
        this.mvc.perform(get("/api/users")).andExpect(status().isOk()).andExpect(jsonPath("$").isEmpty());
    }
}
