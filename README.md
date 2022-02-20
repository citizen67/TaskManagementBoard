# 📑 Task Management Board

Task Management Board is a simple task management app built as a pet project using Spring Boot, Spring Security and ReactJS.
The project is intended as a pet project with general crud and security authorization scope, but you are welcome to fork it and turn it into something else!

## 🏃 Quick Example
Demo: https://taskmanagementboard.herokuapp.com/

* Sometimes when you open it the Heroku server takes a long time to boot up, 
because on the free accounts they put everything to sleep when not being used and it takes time to wake up, so please wait a while.

## 👀 Quick Preview



## 📦 Requirements

- [Java JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) or higher to run the project.
- [Maven](http://maven.apache.org/) to build the project.
- [Node.js](https://nodejs.org/en/) to run the React frontend.
- [PostgreSQL](https://www.postgresql.org/) as a database to store the persistent project data.

## 🔀 Endpoints 
#### 🔒 Security actions
Authenticate or register a new user in the system.
Method	| Path	| Description	| User authenticated	| Available from UI
------------- | ------------------------- | ------------- |:-------------:|:----------------:|
POST	| /api/users/login | Login into the existing user account	|  | ×	
POST	| /api/users/register | Register a new user account	|  | ×

#### 📚 Project actions
Contain general project management actions.
Method	| Path	| Description	| User authenticated	| Available from UI
------------- | ------------------------- | ------------- |:-------------:|:----------------:|
POST	| /api/project	| Create a new project	| × | ×	
GET	| /api/project/all	| Get all projects owned by the user	| × | ×
GET	| /api/project/{projectId}	| Get project by ID owned by the user	| × | ×	
PUT	| /api/project/{projectId}	| Update project owned by the user	| × | ×
DELETE	| /api/project/{projectId}	| Delete project owned by the user	| × | ×

#### 📑 Backlog actions
Bind the Project task with the Project through Backlog.
Method	| Path	| Description	| User authenticated	| Available from UI
------------- | ------------------------- | ------------- |:-------------:|:----------------:|
POST	| /api/backlog/{backlog_id}	| Add project task to the backlog	| × | ×	
GET	| /api/backlog/{backlog_id}	| Get project backlog	| × | ×
GET	| /api/backlog/{backlog_id}/{pt_id}	| Get project task backlog belonging to the project| × | ×	
PATCH	| /api/backlog/{backlog_id}/{pt_id}	| Change project task belonging to the project| × | ×
DELETE	| /api/backlog/{backlog_id}/{pt_id}	| Delete project task belonging to the project| × | ×
