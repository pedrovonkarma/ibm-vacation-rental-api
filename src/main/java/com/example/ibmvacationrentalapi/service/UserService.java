package com.example.ibmvacationrentalapi.service;

import com.example.ibmvacationrentalapi.domain.User;
import com.example.ibmvacationrentalapi.dto.UserDto;
import com.example.ibmvacationrentalapi.repository.UserRepository;
import com.example.ibmvacationrentalapi.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User insert(User obj){
        obj.setId(null);
        obj = userRepository.save(obj);
        return obj;
    }

    public User find(String email){
        Optional<User> obj = userRepository.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado!"));
    }

    public User fromDto(UserDto objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), objDto.getPhoneNumber());
    }

}
