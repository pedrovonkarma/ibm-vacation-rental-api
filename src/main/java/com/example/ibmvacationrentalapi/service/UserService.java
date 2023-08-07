package com.example.ibmvacationrentalapi.service;

import com.example.ibmvacationrentalapi.domain.UserProfile;
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

    public UserProfile insert(UserProfile obj){
        obj.setId(null);
        obj = userRepository.save(obj);
        return obj;
    }

    public UserProfile find(String email, String name, String phoneNumber) {
        UserProfile foundUserProfile = userRepository.findByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));

        if (!foundUserProfile.getName().equals(name) || !foundUserProfile.getPhoneNumber().equals(phoneNumber)) {
            throw new ObjectNotFoundException("Objeto não encontrado!");
        }

        return foundUserProfile;
    }

    public UserProfile fromDto(UserDto objDto){
        return new UserProfile(objDto.getId(), objDto.getName(), objDto.getEmail(), objDto.getPhoneNumber());
    }

}
