package com.example.demo;

import com.example.demo.demos.web.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The tests only test the layer, where Spring handles the incoming HTTP request and hands it off to your controller.
 * Therefore, the server is not started and there is no http traffic at all.
 */
@SpringBootTest
@AutoConfigureMockMvc
class BasicControllerTest {

    private static final String USER_ID = "user_2342";
    private static final String ROLE_ID = "role_1231";


    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/hello")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello unknown user")));
    }

    @Test
    void shouldReturnExpectedMessage() throws Exception {
        this.mockMvc.perform(get("/hello?name=Will"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Will")));
    }

    @Test
    void saveUserReturnTheSavedUser() throws Exception {
        User user = new User("name", 18);
        MvcResult result = this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user))).andExpect(status().isOk()).andReturn();

        // Verify returned response
        ObjectMapper mapper = new ObjectMapper();
        User response = mapper.readValue(result.getResponse().getContentAsString(), User.class);
        assertEquals(user, response);
    }

    @Test
    void shouldReturnExpectedIds() throws Exception {

        this.mockMvc.perform(get("/user/" + USER_ID + "/role/" + ROLE_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("User Id : " + USER_ID + " Role Id : " + ROLE_ID)));
    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}