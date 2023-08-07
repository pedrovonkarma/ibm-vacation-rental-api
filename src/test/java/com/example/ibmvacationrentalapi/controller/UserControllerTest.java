package com.example.ibmvacationrentalapi.controller;

import com.example.ibmvacationrentalapi.domain.UserProfile;
import com.example.ibmvacationrentalapi.dto.UserDto;
import com.example.ibmvacationrentalapi.service.UserService;
import com.example.ibmvacationrentalapi.service.exceptions.ObjectNotFoundException;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;


    private UserDto userDto;

    @BeforeEach
    public void setup(){
        userDto = new UserDto(1, "joao", "joao@gmail.com", "21912345678");
    }

    @Test
    public void insertUserTest() throws Exception {
        UserProfile insertedUser = new UserProfile(1, "joao", "joao@gmail.com", "21912345678");

        when(userService.insert(any(UserProfile.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nome\":\"joao\",\"email\":\"joao@gmail.com\",\"phoneNumber\": \"21912345678\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testSuccessfulLogin() throws Exception {
        UserProfile userProfile = new UserProfile(1, "joao", "joao@gmail.com", "21912345678");

        when(userService.fromDto(any(UserDto.class))).thenReturn(userProfile);
        when(userService.find(anyString(), anyString(), anyString())).thenReturn(userProfile);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nome\":\"joao\",\"email\":\"joao@gmail.com\",\"phoneNumber\":\"21912345678\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFailedLogin() throws Exception {
        when(userService.fromDto(any(UserDto.class))).thenReturn(new UserProfile());
        when(userService.find(anyString(), anyString(), anyString())).thenThrow(ObjectNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nome\":\"joao\",\"email\":\"joao@gmail.com\",\"phoneNumber\":\"21912345678\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Dados incorretos"));
    }

}
