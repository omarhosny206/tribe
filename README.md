# **Tribe** 🌐

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
[![Maven](https://badgen.net/badge/icon/maven?icon=maven&label)](https://https://maven.apache.org/)

![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Kubernates](https://img.shields.io/badge/kubernetes-%23326ce5.svg?style=for-the-badge&logo=kubernetes&logoColor=white)
![AWS](https://img.shields.io/badge/Amazon_AWS-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

## **Service Functionalities 🧠** 
- Developed a REST API service about social media world.
- Implemented JWT Authentication and Role-based Authorization with Login/Register services.
- Ability to create posts and make likes/comments.
- Ability to join tribes(groups) and bookmark posts.
- Developed follow, unfollow, block, unblock, search and feed services.
- Implemented view/clear search history service.
- Handled exceptions and input validations.
- Deployed to AWS EC2 using Docker and GitHub Actions CI/CD.

## **Folder Structure 📁**
```
.
├── Dockerfile
├── README.md
├── docker-compose-dev.yaml
├── docker-compose.yaml
├── k8s
│   ├── postgres-k8s.yaml
│   └── tribe-k8s.yaml
├── mvnw
├── mvnw.cmd
├── pom.xml
├── setup-github-actions-runner.sh
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

## **Usage 🛠️**
- **Kubernetes**
    - Run [`deploy.sh`](./k8s/deploy.sh) file:
      ```shell
      cd ./k8s
      chmod +x ./deploy.sh
      sudo ./deploy.sh
      ```
- **Docker**
    - Run in **development** environment:
      ```shell
      docker-compose -f docker-compose-dev.yaml up -d --build
      # to stop --> docker-compose -f docker-compose-dev.yaml down
      ```
    - Run in **production** environment:
      ```shell
      docker-compose up -d --build
      # to stop --> docker-compose down
      ```
      
## **CI/CD 🚀** [`🔗`](./.github/workflows/cicd.yaml)
![CICD](https://github.com/omarhosny206/omarhosny206/assets/58389695/3e00292e-6229-41f2-aad8-2ee1ebfe9ec0)
  - **CI**:
    - Checkout the code.
    - Login to dockerhub.
    -  Build the docker image.
    - Push the docker image to dockerhub. 
  - **CD** (on AWS EC2 Ubuntu machine as GitHub Actions Self-hosted Runner [`🔗`](./setup-github-actions-runner.sh)):
    - Stop & Remove existing containers.
    - Delete existing images.
    - Checkout the code.
    - Run the containers via docker-compose.yaml file.

## **Database Design 📝**
![DB_DIAGRAM](https://github.com/omarhosny206/tribe/assets/58389695/76ddd57c-f3de-4665-a73d-6e1be44bce5d)

## **Tech Stack ⚡**
- Programming Language: Java 17
- Backend Framework: Spring Boot v3.1.2
- Database Engine: PostgreSQL
- Other Frameworks: Spring Security Data JPA, Hibernate
- API Documentation: Swagger via OpenApi 3.0
- Containerization and Orchestration: Docker [`🔗`](./Dockerfile) [`🔗`](./docker-compose.yaml), Kubernetes [`🔗`](./k8s)
- Cloud: AWS (EC2 Ubuntu Machine)
- CI/CD: GitHub Actions [`🔗`](./.github/workflows/cicd.yaml) [`🔗`](./setup-github-actions-runner.sh)

## **Features to add in the future 💭**
- Adding OAuth 2.0.

## **Contribution 🤝**
If you're interested in contributing to this repository, please follow these guidelines:
- Fork the repository.
- Make your changes.
- Submit a Pull Request.
