# UserManagementSystem
User Management System
This project demonstrate the user management system. The project is developed with help of Spring Boot technology for the development. The java application will store user profile data. It will also allow Create, Read, Update and Delete operations.

# Features
The developed web application has following features: 
(1) Post endpoint to create user profile.
(2) Get endpoint to search list of existing user profiles by email id. 
(3) Get endpoint to fetch list of all existing user profiles in system.
(4) Put endpoint to update existing user profile.
(5) Delete endpoint to delete user profiles.

# Technology
To develop this web application, the following technology has been used: 
Backend Technology:
(1)	Java version 17
(2)	Spring Boot version 3.4.4

# How to Run ?

Step-1
1)	Clone the repository from GitHub to your local device.

Step-2 Build Java Application
2)	Please perform the following command in power shell terminal to build the entire JAVA application using MVN command.
mvn spring-boot:run

Step-3 Open Java Application
3)	Please open the postman and import all REST endpoints attached JSON file namely ‘User Management System.postman_collection’ from GitHub. 

The testing of JAVA Application is mentioned on next page.
 
# How to Test ?
Create User Profile: http://localhost:8080/users/profiles/createProfile
 
Search By Email: http://localhost:8080/users/profiles/searchByEmail?email=alpha@email.com

List All User Profiles: http://localhost:8080/users/profiles/listProfiles

Update User Profile: http://localhost:8080/users/profiles/updateProfile

Delete User Profile: http://localhost:8080/users/profiles/deleteProfiles?email=kappa@email.com

 

