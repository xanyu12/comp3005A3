# comp3005A3
##Assignment 3 - Database Interaction with PostgreSQL and Application Programming
This program implements a PostgreSQL database using the provided schema and is connected to a Java application to perform specific CRUD operations.

##List of files:
  - readme.txt 
  - database_schema.sql
  - main.java

##Video Link:
  - https://youtu.be/y2r1oZbFVbM

##Launching and Operation:
  1. In the main.java file replace the url "jdbc:postgresql://localhost:5432/your_database_name", the username and password with your actual database connection information\
  2. Open the PostgreSQL database, ensure that the PostgreSQL JDBC driver is in the project's dependecies, compile and run the Java application and it will connect to the PostgreSQL database and execute CRUD operations
  3. When running the user is able to select which operation they would like to perform by inputting a number based on the main menu
  4. 0 = exit, 1 = print all students in the database, 2 = add a student to the database, 3 = update a students email, 4 = delete a student from the database
  5. The program will continue prompting the user for input until they exit
