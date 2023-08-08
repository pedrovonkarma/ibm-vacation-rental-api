package com.example.ibmvacationrentalapi.service;

import com.example.ibmvacationrentalapi.domain.UserProfile;
import com.example.ibmvacationrentalapi.dto.UserDto;
import com.example.ibmvacationrentalapi.repository.UserRepository;
import com.example.ibmvacationrentalapi.service.exceptions.ObjectNotFoundException;
import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserProfile userProfile;

    @BeforeEach
    public void setup(){
        userProfile = new UserProfile(1, "Joao", "joao@gmail.com", "21912345678");
    }

    @Test
    public void insertUserTest(){

        when(userRepository.save(any(UserProfile.class))).thenReturn(userProfile);

        UserProfile insertedUser = userService.insert(userProfile);

        assertNotNull(insertedUser);
        assertEquals(userProfile.getName(), insertedUser.getName());
        assertEquals(userProfile.getEmail(), insertedUser.getEmail());
        assertEquals(userProfile.getPhoneNumber(), insertedUser.getPhoneNumber());
        assertNull(insertedUser.getId());

        verify(userRepository, times(1)).save(any(UserProfile.class));
    }

    @Test
    public void findUserByExistentEmailTest(){
        String userEmail = "joao@gmail.com";

        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(userProfile));

        UserProfile foundUser = userService.find(userEmail, userProfile.getName(), userProfile.getPhoneNumber());

        assertNotNull(foundUser);
        assertEquals(userProfile.getId(), foundUser.getId());
        assertEquals(userProfile.getName(), foundUser.getName());
        assertEquals(userProfile.getEmail(), foundUser.getEmail());
        assertEquals(userProfile.getPhoneNumber(), foundUser.getPhoneNumber());
        verify(userRepository, times(1)).findByEmail(userEmail);
    }

    @Test
    public void findUserByNotExistentEmailTest(){
        String userEmail = "Joao";

        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> userService.find(userEmail, userProfile.getName(), userProfile.getPhoneNumber()));
        verify(userRepository, times(1)).findByEmail(userEmail);
    }

}
