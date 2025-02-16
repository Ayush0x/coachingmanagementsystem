package com.example.university.rest.api.universitydata.utils;

import com.example.university.rest.api.universitydata.entities.enums.Permissions;
import com.example.university.rest.api.universitydata.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.university.rest.api.universitydata.entities.enums.Permissions.*;
import static com.example.university.rest.api.universitydata.entities.enums.Role.*;

/**
 * Provides a mapping between {@link Role} and their associated {@link Permissions}.
 *
 * This class maintains a predefined mapping of roles (e.g., ADMIN, STUDENT, TEACHER) to the set of permissions
 * applicable to each role. These mappings are used to enforce access control and determine
 * the granted authorities for various roles within the application.
 *
 * Features:
 * - A static, immutable map that defines the relationship between {@link Role} and their respective
 *   {@link Permissions}.
 * - The permissions for each role are represented as a {@code Set<Permissions>}.
 * - Build and return a {@code Set<SimpleGrantedAuthority>} that represents the granted authorities
 *   by converting permissions into Spring Security's {@link SimpleGrantedAuthority}.
 *
 * Methods:
 * - {@code getAuthoritiesForRole(Role role)}: Retrieves the corresponding authorities for a given
 *   role by transforming the permissions into Spring Security authorities.
 *
 * This class plays a critical role in the security layer of the application by translating
 * business logic roles and permissions into security-level authority structures.
 */
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
