# Stack Overflow 2.0 Documentation

- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [System Architecture](#system-architecture)
- [Database Design](#database-design)
- [Getting Started](#getting-started)

## Introduction
Welcome to  Stack Overflow 2.0. This question-and-answer website aims to serve as a minimalistic alternative to the original website. The main page lists all questions with details and answers. Users are able to create, read, delete and update  their questions and  answers. Main emphasis of this project was to use and practice SOLID, OOP and Clean Code principles within the Java Spring Boot framework.

## Technologies Used
- **Java Spring Boot Framework**
- **React**
- **Postgres**

## System Architecture
Repository contains both the Front and Backend part of the application and its  using Model View Controller layers.

![Alt Text](images/stackoverflow.png)

Flowchart  above demonstrates how data is handled and navigated from the database to the Frontend. Requests from the frontend also use the same logic just backwards. Moreover Frontend doesn't do calculations, only displays the data, SQL handles the data manipulation. 

- QuestionDAOJDBC is directly connected to the Postgres Database.

- QuestionDAO  serves as a crucial abstraction layer bridging the gap between the low-level data access operations implemented in QuestionDAOJDBC and the higher-level business logic encapsulated in QuestionService

- QuestionService:positioned between QuestionDAO and QuestionController, orchestrates question-related operations and ensures coordinated data flow between the Frontend and Backend.

- QuestionController: handles HTTP requests related to questions.
It is responsible for coordinating the flow of data between the frontend and the backend for question-related operations.


## Database Design
 Postgresql is responsible for storing and handling  and manipulating  questions and answers by using two tables.

Questions Schema contains five columns : 
- question_id
- user_id 
- question_title
- question_description 
- question_date

 Similarly Answers Schema  contains five columns:
- answer_id
- user_id 
- answer_title
- answer_description 
- answer_date
- 
## Getting Started

1. Clone this repository to your local machine.
2. Install the necessary dependencies for both the frontend and backend.
3. Set up your Postgres database and configure the connection.
4. Initiate the backend by running the StackoverflowTwApplication (SpringBootApplication).
5. You can start frontend in the ui folder using npm start 
