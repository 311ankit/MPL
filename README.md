# MPL
Mobile premier leage Spring boot Code

Please run below commands to run the project. 
I am assuming you will have maven and java 1.8 version install in you local system. If maven is not install please visit the below link:
      http://maven.apache.org/install.html

After clone:
  1) Go inside the Folder with command:
          cd MPL/fantasy-cricket
  
  2) Build the project with command:
          mvn clean install 
  
  3) Running the project:
          mvn spring-boot:run

  4) Go to browser and tyep below url for swagger:
          http://localhost:8080/swagger-ui.html#/
          
  5) You will be able to see the signature of api in swagger very well try to add modified and get the result.
  
  6) Also, I am using the in-memory database for table.You can see all the table & data related to project in below link:
          http://localhost:8080/h2/
         
        you will be promt to a login screen on the brower itself. there is no need of password directly click on login or you can test the connection as well.
        
