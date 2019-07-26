# Employee-Portal
This is my assignment Employee Portal.

Technologies used: MySQL, MySQL Workbench 8.0, Java(JDK 1.8 and JRE 1.8), Eclipse Oxygen, Maven, Apache Tomcat(v8.5), Spring MVC(4.3.0), AngularJS, Chrome Developer Tools

## Instructions to SETUP and RUN:
1. Import the "MySQL DB/employee_portal.sql" into the MySQL Server
2. Create a user with the following credentials with full privileges to schema "employee_portal"
    - **Username**: user.employee
    - **Password**: Employee@123
3. Either import the Eclipse Project in "Eclipse Project/EmployeePortal" and run it on Tomcat Server or copy the WAR File to webapps folder of Apache Tomcat and start the Tomcat server.
4. The project must be run on **localhost:8080**
5. Once the project is running, open the login page at "http://localhost:8080/EmployeePortal"
6. You can login using the following credentials
    - **Username**: fred.costner
    - **Password**: fred123

## Instructions to run project preview
I've included a preview of the project in the directory "Project Preview". Run an HTTP Server in that directory for the preview. Python can be used to run an HTTP Server by the following code in command-line:
```
project_preview_directory> python -m http.server 5000
```
This starts an HTTP Server at **localhost:5000**, where you can view the project preview.


I've included the screenshots of the project in "Screenshots" folder
