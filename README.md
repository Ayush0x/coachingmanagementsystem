# Coaching Institute Management System

A comprehensive management system designed for coaching institutes to streamline student enrollments, professor assignments, course management, and performance tracking. Built using *Spring Boot* with a focus on modularity and scalability.

## Features

- *Student Management*: 
  - Enroll students in courses.
  - Assign performance records and view grades.
  
- *Professor Management*:
  - Assign professors to specific subjects based on their expertise.
  - Manage designations and roles.

- *Course Management*:
  - Create and manage courses with multiple subjects.
  - Link professors and students to courses seamlessly.

- *Performance Tracking*:
  - Professors can assign marks to students for each subject.
  - Students can view their marks and performance history.
    
- *Attendance Tracking*:
  - Professors can mark attendance for students in their classes.
  - Students can view their attendance records.

- *Subject Management*:
  - Define subjects under each course.
  - Assign professors to subjects based on their expertise.


## Tech Stack

- *Backend*: Spring Boot (Java)
- *Database*: PostgresSQL
- *Frontend*: HTML\CSS and react
- *Tools*:
  - Hibernate/JPA for ORM
  - Maven for dependency management
  - Postman for API testing

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/coaching-institute-management-system.git
   cd coaching-institute-management-system
2. Configure the database:

- Update the application.properties file in src/main/resources/ with your database credentials.
* spring.datasource.url=jdbc:mysql://localhost:3306/coaching_db
* spring.datasource.username=your_username
* spring.datasource.password=your_password

3. Run the application:
- mvn spring-boot:run

4. API Endpoints
1. Student Module
- POST /students - Add a new student.
- GET /students/{id} - Get student details by ID.
- PUT /students/{id} - Update student information.
- DELETE /students/{id} - Remove a student.

2. Professor Module
- POST /professors - Add a new professor.
- GET /professors/{id} - Get professor details by ID.

3. Course Module
- POST /courses - Create a new course.
- GET /courses/{id} - Get course details by ID.

4. Performance Module
- POST /performance - Assign marks to students.
- GET /performance/student/{id} - View student performance.

5. Subject Module
- POST /subjects - Add a new subject to a course.
- GET /subjects/{id} - Get subject details by ID.
- PUT /subjects/{id} - Update subject information.
- DELETE /subjects/{id} - Remove a subject.

6. Attendance Module
- POST /attendance - Mark attendance for students in a course.
- GET /attendance/student/{id} - View attendance records for a specific student.
- GET /attendance/course/{id} - View attendance records for a course.

# Future Scope
- Implementing authentication and role-based access control (RBAC) using Spring Security.
- Adding real-time notifications for assignments and announcements.
- Building a modern frontend using frameworks like React or Angular.
- Integrating analytics dashboards for better insights into student and professor performance.

## Contributions are welcome! Please fork the repository and submit a pull request with your changes.

## Author
# Ayush Sharma
# Connect with me on LinkedIn:- www.linkedin.com/in/ayush-sharma-script-sensei
