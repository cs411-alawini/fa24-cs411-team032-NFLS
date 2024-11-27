# fa24-cs411-team032-NFLS

---

RunTrack is a mobile web application designed to help users track and monitor their running sessions. The app will allow users to input essential running metrics such as running time, speed, and pace, providing a detailed overview of each session. Users will be able to log and view their progress over time, helping them optimize their workouts and set personalized fitness goals. The app aims to offer both basic functionality for casual runners and advanced features for serious athletes.

## Tech Stack

### Backend
- **Languages and Frameworks**:
  - Java 17
  - Spring Boot 3.1
- **Libraries**:
  - Spring Data JPA: Data persistence.
  - Spring Security: Authentication and authorization.
  - Spring Web: RESTful API development.
  - Hibernate: ORM for database management.
- **Database**:
  - H2 Database: Development and testing.
  - MySQL: Production environment.
- **Build Tool**:
  - Gradle: Dependency management and build automation.
- **Runtime and Deployment**:
  - Embedded Tomcat server (provided by Spring Boot).

### Frontend
- **Languages and Frameworks**:
  - React.js: Frontend framework.
  - React Router: Client-side routing.
- **UI Library**:
  - Material-UI (MUI): For building responsive UI.
- **State Management**:
  - React's useState hook.
- **HTTP Client**:
  - Axios: For API requests.

### Development Tools
- **Development Environments**:
  - IntelliJ IDEA (Backend)
  - Visual Studio Code (Frontend)
- **Version Control**:
  - Git & GitHub
- **Package Manager**:
  - npm

### Deployment
- **Google Cloud Platform(GCP)**

---

## How to Run the Project

### Prerequisites
- Install [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or higher.
- Install [Gradle](https://gradle.org/) (optional if using Gradle Wrapper).
- Install [Node.js](https://nodejs.org/) and npm.

### Backend Setup
1. **Clone the repository**:
   ```bash
   git clone https://github.com/cs411-alawini/fa24-cs411-team032-NFLS.git
   cd runtrack
   ```
2. **Run the backend**:
   - Using Gradle Wrapper:
     ```bash
     ./gradlew bootRun
     ```
   - Or with a globally installed Gradle:
     ```bash
     gradle bootRun
     ```
3. **Access the API**:
   - Default URL: `http://localhost:8080`

### Frontend Setup
1. **Navigate to the frontend directory**:
   ```bash
   cd runtrack-frontend
   ```
2. **Install dependencies**:
   ```bash
   npm install
   ```
3. **Start the frontend**:
   ```bash
   npm start
   ```
4. **Access the application**:
   - Default URL: `http://localhost:3000`

---

## Project Structure

### Backend
```
src/main/java/com/runtrack
├── controller       # Handles API requests
├── entity           # Database entity classes
├── repository       # Database access interfaces
├── service          # Business logic
├── config           # Configuration files (e.g., CORS, Security)
└── RunTrackApplication.java # Application entry point
```

### Frontend
```

```

---

## Developers

- **Boyu(Maxwell) Liu** - Full-Stack Developer  
- **Ziheng Qi** - Full-Stack Developer
- **Xiaoyang Chen** - Full-Stack Developer
- **Xiaohan Mu** - Full-Stack Developer
