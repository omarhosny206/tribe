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
├── Dockerfile
├── README.md
├── docker-compose.yml
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── tribe
│       │           ├── TribeApplication.java
│       │           ├── config
│       │           │   ├── BeanConfig.java
│       │           │   └── SecurityConfig.java
│       │           ├── controller
│       │           │   ├── CommentController.java
│       │           │   ├── HistoryController.java
│       │           │   ├── LoginController.java
│       │           │   ├── PostBookmarkController.java
│       │           │   ├── PostController.java
│       │           │   ├── SignupController.java
│       │           │   ├── TribeController.java
│       │           │   ├── UserController.java
│       │           │   └── UserTribeController.java
│       │           ├── dto
│       │           │   ├── CommentRequestDto.java
│       │           │   ├── ContentDto.java
│       │           │   ├── ErrorDto.java
│       │           │   ├── HistoryRequestDto.java
│       │           │   ├── LoginRequestDto.java
│       │           │   ├── LoginResponseDto.java
│       │           │   ├── MessageDto.java
│       │           │   ├── PostBookmarkDto.java
│       │           │   ├── PostRequestDto.java
│       │           │   ├── SignupRequestDto.java
│       │           │   ├── TribeRequestDto.java
│       │           │   ├── UserBlockingDto.java
│       │           │   ├── UserDto.java
│       │           │   ├── UserFollowingDto.java
│       │           │   └── UserTribeDto.java
│       │           ├── entity
│       │           │   ├── Comment.java
│       │           │   ├── History.java
│       │           │   ├── Post.java
│       │           │   ├── PostBookmark.java
│       │           │   ├── Tribe.java
│       │           │   ├── User.java
│       │           │   ├── UserBlocking.java
│       │           │   ├── UserFollowing.java
│       │           │   └── UserTribe.java
│       │           ├── exception
│       │           │   ├── ApiError.java
│       │           │   ├── CustomAuthenticationExceptionEntryPoint.java
│       │           │   ├── CustomException.java
│       │           │   └── CustomExceptionHandler.java
│       │           ├── filter
│       │           │   └── JwtAuthenticationFilter.java
│       │           ├── repository
│       │           │   ├── CommentRepository.java
│       │           │   ├── HistoryRepository.java
│       │           │   ├── PostBookmarkRepository.java
│       │           │   ├── PostRepository.java
│       │           │   ├── TribeRepository.java
│       │           │   ├── UserBlockingRepository.java
│       │           │   ├── UserFollowingRepository.java
│       │           │   ├── UserRepository.java
│       │           │   └── UserTribeRepository.java
│       │           ├── response
│       │           │   └── MessageResponse.java
│       │           ├── service
│       │           │   ├── CommentService.java
│       │           │   ├── HistoryService.java
│       │           │   ├── LoginService.java
│       │           │   ├── PostBookmarkService.java
│       │           │   ├── PostService.java
│       │           │   ├── SignupService.java
│       │           │   ├── TribeService.java
│       │           │   ├── UserBlockingService.java
│       │           │   ├── UserFollowingService.java
│       │           │   ├── UserService.java
│       │           │   ├── UserTribeService.java
│       │           │   └── impl
│       │           │       ├── CommentServiceImpl.java
│       │           │       ├── HistoryServiceImpl.java
│       │           │       ├── LoginServiceImpl.java
│       │           │       ├── PostBookmarkServiceImpl.java
│       │           │       ├── PostServiceImpl.java
│       │           │       ├── SignupServiceImpl.java
│       │           │       ├── TribeServiceImpl.java
│       │           │       ├── UserBlockingServiceImpl.java
│       │           │       ├── UserFollowingServiceImpl.java
│       │           │       ├── UserServiceImpl.java
│       │           │       └── UserTribeServiceImpl.java
│       │           ├── util
│       │           │   ├── AuthenticationUser.java
│       │           │   ├── CustomUser.java
│       │           │   ├── JwtUtil.java
│       │           │   ├── PostBookmarkId.java
│       │           │   ├── Roles.java
│       │           │   ├── UserBlockingId.java
│       │           │   ├── UserFollowingId.java
│       │           │   ├── UserTribeId.java
│       │           │   └── UsernameGenerator.java
│       │           └── validation
│       │               ├── RoleValidator.java
│       │               └── annotation
│       │                   └── ValidRole.java
│       └── resources
│           ├── application-prod.properties
│           └── application.properties
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
