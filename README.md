# Sanad Law Service ( The API )

## Introduction
Sanad is a law service application powered with AI models. Our goal is to facilitate the whole process of finding a suitable lawyer, going to the court, finishing legal papers, asking for legal advice, and more.

## Technologies used
This project is built with : <br> <br>
• Spring-MVC <br>
• PostgreSQL <br>
• Spring JPA <br>
• Socket.io <br>
• Firebase Cloud Messaging <br>
• Spring Security for JWT and stateless authentication and middleware filters <br>
• Firebase Storage for cloud storage <br>
• @Async and CompletableFuture for asynchronous processing <br>

## Database design
You can also view it from this [Database Design Link](https://drawsql.app/teams/zkrallah/diagrams/gaeedy "Database Design Link") <br>

![drawSQL-image-export-2024-09-23](https://github.com/user-attachments/assets/8a066e86-49a9-4d59-a4bb-84cc779b6a94)

## Installation

To start using the API :

• You should make sure you have PostgreSQL installed and running, after that, you need to open a `psql` shell terminal and type :
```sql
CREATE DATABASE sanad;
GRANT ALL PRIVILEGES ON DATABASE sanad TO postgres;
```

• You should clone the repository :
```link
https://github.com/Sanad-Project/sanad-law-backend.git
```
• Email service is disabled for testing environment and the verification OTP is always `111111`. If you want to enable it, go to `MailSenderServiceImpl` class and remove the if condition,
and inside `AuthenticationServiceImpl` class, modify the `generateRandomOtp` function. <br>

• Rename the `env` file in the resources package to `application.yml` and update it with your database credentials and environment information. Make sure to add or update any commented part as per your environment <br>

• Refer to this [Article](https://medium.com/@poojithairosha/image-uploading-with-spring-boot-firebase-cloud-storage-e5ef2fbf942d "Article") 
to know how to integrate your own Firebase Cloud Storage to the project. <br>
Once you have downloaded the JSON file: <br>
1- add it under the resources package. <br>
2- Replace the `CREDENTIALS_FILE_PATH` variable in the `StorageServiceImpl` class with the path of your JSON file.
3- Replace the `ClassPathResource` value in the `ApplicationConfig.java` file at line 56 with the path of yout JSON file.

That's it, now you are good to go! Run the project in Intellij or you can run this command in the root directory if you have maven installed on your machine:
```bash
mvn spring-boot:run
```

## Unit testing
You can run the unit tests by running the following command on you machine if you have maven installed:
```bash
mvn test
```

## Endpoints and examples
You can see the finished endpoints and examples for the requests and their corresponding responses from this [Postman Collection link](https://www.postman.com/zkrallah/sanad/collection "Postman Collection link")


