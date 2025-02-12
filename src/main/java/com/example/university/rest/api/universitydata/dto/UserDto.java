package com.example.university.rest.api.universitydata.dto;

import com.example.university.rest.api.universitydata.entities.enums.Permissions;
import com.example.university.rest.api.universitydata.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
//    private Set<Role> roles;
//    private Set<Permissions> permissions;
}