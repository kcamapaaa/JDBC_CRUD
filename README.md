# JDBC_CRUD
This is a console CRUD app where you can create skills, specialties and developers. 
All information is written to the database and read from it.
When you want to remove developer, it is not actually removed from database but instead 
the status field of developer changes to 0 so you cannot see and manipulate him but he is still present in database.

# How to start
1)Change username and password in application.properties for your actual database<br />

2)Change username and password in pom.xml file in configuration section.<br />

*2.1)You may also change database name (optional).*<br />

3)Open terminal and write mvn liquibase:update.<br />

4)Start src/main/java/com/Vladislav/view/Starter.java

5)Enjoy
