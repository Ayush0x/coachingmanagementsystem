package com.example.university.rest.api.universitydata.entities;

import com.example.university.rest.api.universitydata.entities.enums.Role;
import com.example.university.rest.api.universitydata.utils.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the UserEntity class which is a JPA entity corresponding to the 'user' table in the database.
 *
 * This class implements the {@link UserDetails} interface from Spring Security and serves as the
 * foundational entity for user authentication and authorization functionalities within the application.
 *
 * Key features:
 * - Each user is uniquely identified by their `id`.
 * - The `email` field is unique and acts as the username for authentication.
 * - Includes user attributes such as `userName`, `password`, and associated roles.
 * - Roles are represented as a collection of {@link Role} enums, stored using JPA's {@code @ElementCollection}.
 * - Overrides `getAuthorities` to provide a dynamically computed collection of {@link GrantedAuthority}
 *   that maps user roles to security permissions.
 *
 * Annotations and Behavior:
 * - Uses Lombok annotations for boilerplate code such as getter/setter, constructors, and builders.
 * - Integrates Spring Security's user detail mechanisms for authentication.
 * - The class is annotated with JPA annotations for entity mapping and persistence.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String userName;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(
                role -> {
                    Set<SimpleGrantedAuthority> permissions = PermissionMapping.getAuthoritiesForRole(role);
                    authorities.addAll(permissions);
                    authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
                }
        );
        return authorities;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String getUsername()
    {
        return this.email;
    }

}
