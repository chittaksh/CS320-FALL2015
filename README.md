# CS320-Fall-2015

Contains 1 Complete project and 2 Exam Samples for CS320-Fall-2015

  * 320 - Project
  * Finals - Final exam sample
  * Midterm - Midterm exam sample

### Requirements
1. Java v1.7 or above.
2. Java Web  Development IDE (Eclipse or Netbeans).
3. Apache Tomcat Server v7.0 or above.
4. MySQL Database v5.0 or above.
5. mysql-connector-java v5.1.36
6. jakarta-taglibs-standard v1.1.2

### How to run

Step 1. Add Server to Eclipse Servers.

Step 2. Add the mysql & jakarta libraries into the lib folder of the server.

Step 3. Clone the repository to your local system.

Step 4. Import the specific project as "general project" in eclipse.

Step 5. Right click the project, Configure > Convert to Faceted Form.

Step 6. 
  * Right click the project, Properties > Project facets
  
  * Click - Dynamic Web project, Java, JavaScript
  
  * Apply and Close
  
         
Step 7. 

  * Right click the project, Properties > Java Build Path
  
  * Add Library > Server Runtime > Tomcat
  
         
Step 8. Run the project on server.

### Project Description:
#### Lease Management System (http://localhost:8080/320/hw4/Login.jsp)
Lease Management System is a system designed to manage each apartment building. It keeps track all the apartments, lease details, and rent details for each month. It also generates rent receipts for each payment done.


#### Finals (http://localhost:8080/Finals/Final)
Final contains Appointment Management Application. I contains a calender structure display and you can add, edit, delete or modify a given time slot appointment. The application makes sure that their are no overlapping of the appointment and allows a user to make changes into an free time only.


#### Midterm (http://localhost:8080/Midterm/midterm/Main)
Midterm contains a Coupon Management Application. Each usr can add/ update/ delete/ coupons that he has. He can then keep track of each coupon if used or not.  


### Technologies Used:
Java, Servlets, JSTL, Bootstrap, JQuery, MySQL
