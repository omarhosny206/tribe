# Tribe 🚀🚀

# **Service Functionalities** 🧠

- A Backend Rest API designed using MVC Design Architecture.
- Enables users to create posts/comment.
- Implemented Login/Register service.
- Ability to join tribes and bookmark posts.
- Developed follow/unfollow user service.
- Implemented view/clear search history service.
- Email and Password based Authentication.
- Authorization was done using JWT(Json Web Token).
- Error and Exception handling.

```
.
├── pom.xml
├── README.md
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── example
│       │           ├── config
│       │           │   └── SecurityConfig.java
│       │           ├── controller
│       │           │   ├── CommentController.java
│       │           │   ├── HistoryController.java
│       │           │   ├── LoginController.java
│       │           │   ├── PostController.java
│       │           │   ├── RegisterController.java
│       │           │   ├── TribeController.java
│       │           │   └── UserController.java
│       │           ├── dto
│       │           │   ├── CommentDto.java
│       │           │   ├── ContentDto.java
│       │           │   ├── HistoryId.java
│       │           │   ├── LoginRequest.java
│       │           │   ├── LoginResponse.java
│       │           │   ├── PostDto.java
│       │           │   ├── TribeDto.java
│       │           │   └── UserDto.java
│       │           ├── entity
│       │           │   ├── Comment.java
│       │           │   ├── History.java
│       │           │   ├── Post.java
│       │           │   ├── Tribe.java
│       │           │   └── User.java
│       │           ├── exception
│       │           │   ├── ControllerAdvisor.java
│       │           │   └── CustomException.java
│       │           ├── filter
│       │           │   └── JwtFilter.java
│       │           ├── repository
│       │           │   ├── CommentRepository.java
│       │           │   ├── HistoryRepository.java
│       │           │   ├── PostRepository.java
│       │           │   ├── TribeRepository.java
│       │           │   └── UserRepository.java
│       │           ├── response
│       │           │   └── MessageResponse.java
│       │           ├── service
│       │           │   ├── CommentService.java
│       │           │   ├── HistoryService.java
│       │           │   ├── impl
│       │           │   │   ├── CommentServiceImpl.java
│       │           │   │   ├── HistoryServiceImpl.java
│       │           │   │   ├── LoginServiceImpl.java
│       │           │   │   ├── PostServiceImp.java
│       │           │   │   ├── RegistrationServiceImpl.java
│       │           │   │   ├── TribeServiceImpl.java
│       │           │   │   └── UserServiceImpl.java
│       │           │   ├── LoginService.java
│       │           │   ├── PostService.java
│       │           │   ├── RegistrationService.java
│       │           │   ├── TribeService.java
│       │           │   └── UserService.java
│       │           ├── TribeApplication.java
│       │           └── util
│       │               └── JwtUtil.java
│       └── resources
│           └── application.properties
```

# **Database Design 🖊️**
![comment0](https://github.com/omarhosny206/tribe/assets/58389695/59d23377-bb12-40f4-80c1-502c9dd67289)


# **Tech Stack ⚡**

- Programming Language: Java 17
- Backend Framework: Spring Boot v3.1.2
- Database Engine: PostgreSQL
- API Documentation: Swagger via OpenApi 3.0
- Containerization and Orchestration: Docker, Kubernetes
- Cloud: AWS
- Other Frameworks: Spring Security Data JPA, Hibernate

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

[![Maven](https://badgen.net/badge/icon/maven?icon=maven&label)](https://https://maven.apache.org/)

![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

# **Features to add in the future 💭**

- Adding OAuth 2.0.
- Signup with Google, Facebook and other platforms.

# API Documentation 📝 via [|Swagger|](http://localhost:8080/swagger-ui/index.html#/)
