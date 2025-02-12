package com.example.university.rest.api.universitydata.utils;

import com.example.university.rest.api.universitydata.entities.enums.Permissions;
import com.example.university.rest.api.universitydata.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.university.rest.api.universitydata.entities.enums.Permissions.*;
import static com.example.university.rest.api.universitydata.entities.enums.Role.*;

public class PermissionMapping {

    private static final Map<Role, Set<Permissions>> map=Map.of(
            ADMIN,Set.of(MANAGE_USERS,MANAGE_ADMISSIONS,MANAGE_COURSES,VIEW_REPORTS,CREATE_COURSES,DELETE_COURSES,VIEW_COURSES,VIEW_ADMISSIONS,EDIT_COURSES),
            STUDENT,Set.of(VIEW_COURSES,VIEW_OWN_ENROLLMENTS,ENROLL_IN_COURSES),
            TEACHER,Set.of(VIEW_COURSES,VIEW_ADMISSIONS)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role)
    {
        return map.get(role).stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .collect(Collectors.toSet());
    }
}
