package com.example.university.rest.api.universitydata.entities.enums;

/**
 * Represents a set of predefined permissions used within the university management system.
 * These permissions are categorized by the roles that utilize them, such as Admin, Teacher, and Student,
 * and dictate the level of access and actions that can be performed within the system.
 *
 * Permissions available:
 *
 * - Admin Permissions:
 *   - MANAGE_USERS: Allows administrators to manage user accounts.
 *   - MANAGE_COURSES: Allows administrators to manage course offerings and content.
 *   - MANAGE_ADMISSIONS: Grants control over admission processes.
 *   - VIEW_REPORTS: Allows viewing of system-generated reports.
 *
 * - Teacher Permissions:
 *   - CREATE_COURSES: Allows teachers to create new course content.
 *   - EDIT_COURSES: Enables editing of existing courses.
 *   - DELETE_COURSES: Allows removal of courses.
 *   - VIEW_ADMISSIONS: Permits access to admission-related information.
 *
 * - Student Permissions:
 *   - VIEW_COURSES: Allows students to view available courses.
 *   - ENROLL_IN_COURSES: Grants the ability to enroll in courses.
 *   - VIEW_OWN_ENROLLMENTS: Enables students to view their own enrollment details.
 *
 * This enum helps standardize and enforce role-specific access control within the system by mapping
 * specific roles to their corresponding permissions.
 */
public enum Permissions {
    //For Admins
    MANAGE_USERS,
    MANAGE_COURSES,
    MANAGE_ADMISSIONS,
    VIEW_REPORTS,

    //For Teachers
    CREATE_COURSES,
    EDIT_COURSES,
    DELETE_COURSES,
    VIEW_ADMISSIONS,

    //For Students
    VIEW_COURSES,
    ENROLL_IN_COURSES,
    VIEW_OWN_ENROLLMENTS

}
