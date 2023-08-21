package com.cydeo.dto;

import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
//Data Transfer Object. We create Entity/Model and according to them DTOs. At the user interface portion we use DTOs. When we need to operate at Database we transfer the data with DTOs and convert it with Mappers to Entities and use them at our database operations.
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private boolean enabled;
    private String phone;
    private RoleDTO role;
    private Gender gender;

}
