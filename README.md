
# Rest-Assured API Test Automation (Java + TestNG)

This project contains automated API tests for the Whitesmokehouse API using:

- ✅ Java
- ✅ Maven
- ✅ Rest-Assured
- ✅ TestNG
- ✅ Postman (reference collection included)

---

## 🏗️ How to Create the Project (if starting from scratch)

Use the command below to generate a new Maven project using the `maven-archetype-quickstart`.

### ✅ Windows (PowerShell / CMD):

```bash
mvn "-DgroupId=com.api.automation" "-DartifactId=restassured-testng-project" "-DarchetypeArtifactId=maven-archetype-quickstart" "-DinteractiveMode=false" archetype:generate
```

---

## 📁 Project Structure

```
restassured-testng-project/
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── api/
│                   └── automation/
│                       ├── base/         # BaseTest class (handles login + token)
│                       ├── taskday3/     # All test cases for Auth and Objects
│                       │   ├── AuthTest.java
│                       │   └── ObjectTest.java
├── postman/                              # Reference Postman collection
│   └── whitesmoke-api.postman_collection.json
├── pom.xml
└── README.md
```

---

## 🚀 How to Run Tests

### Prerequisites:
- Java 8+ and Maven installed
- Internet connection (to hit live API)
- VS Code or any IDE

### Run all tests:

```bash
mvn clean test
```

### Run a specific test class:

```bash
mvn "-Dtest=com.api.automation.taskday3.ObjectTest" test
```

### Run a specific test method:

```bash
mvn "-Dtest=com.api.automation.taskday3.ObjectTest#testAddObject" test
```

---

## 🔐 Token Handling

The login token is automatically fetched in `BaseTest.java` and reused in all test classes that `extend BaseTest`.

---

## 📦 Postman Collection

You can find the original API request collection here:

```
postman/whitesmoke-api.postman_collection.json
```

Import into Postman for easy reference.

---

## 🧪 API Tests Covered

- `POST /register` ✅
- `POST /login` ✅
- `GET /objects` ✅
- `POST /objects` ✅
- `PUT /objects/:id` ✅
- `PATCH /objects/:id` ✅
- `DELETE /objects/:id` ✅

---

## ✍️ Author

Destia (API Automation Task using Java + TestNG)
