package com.example.university.rest.api.universitydata.dto;

import com.example.university.rest.api.universitydata.entities.enums.Permissions;
import com.example.university.rest.api.universitydata.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {
    private String userName;
    private String email;
    private String name;
    private String password;
    private Long contactNumber;
}
