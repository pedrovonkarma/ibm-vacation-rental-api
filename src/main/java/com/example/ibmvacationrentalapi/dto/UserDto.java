package com.example.ibmvacationrentalapi.dto;

import com.example.ibmvacationrentalapi.domain.UserProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {
    private Integer id;

    private String name;

    private String email;

    private String phoneNumber;

    public UserDto(UserProfile obj){
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
        phoneNumber = obj.getPhoneNumber();
    }
}
