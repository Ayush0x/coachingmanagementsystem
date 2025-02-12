package com.example.university.rest.api.universitydata.entities.enums;

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
