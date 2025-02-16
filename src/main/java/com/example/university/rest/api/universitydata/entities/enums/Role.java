package com.example.university.rest.api.universitydata.entities.enums;

/**
 * Represents the roles available within the university management system.
 *
 * Each role defines a specific level of access and set of permissions
 * that determine the actions a user can perform within the system.
 *
 * The available roles are:
 * - ADMIN: Represents administrative users who have the highest access level,
 *   including managing users, courses, admissions, and viewing reports.
 * - STUDENT: Represents students who can view courses, enroll, and manage
 *   their own academic information.
 * - TEACHER: Represents teaching staff who can create, edit, and manage courses
 *   as well as view certain administrative data.
 *
 * This enum helps maintain the role-oriented structure of the application's
 * access control mechanisms and permissions.
 */
public enum Role {
    ADMIN,
    STUDENT,
    TEACHER
}
