# JSONPlaceholder API Test Automation

This repository contains automated API tests for the [JSONPlaceholder](https://jsonplaceholder.typicode.com/) fake REST API.  
The tests are implemented using **Java**, **Rest Assured**, **TestNG**, and **Faker** for dynamic test data generation.

---

## 📌 Features
- Covers CRUD operations on `/posts` endpoint:
    - `GET /posts` – Retrieve all posts
    - `GET /posts/{id}` – Retrieve a single post
    - `POST /posts` – Create a new post
    - `PUT /posts/{id}` – Update an existing post
    - `DELETE /posts/{id}` – Delete a post
- Positive and negative test scenarios
- Data-driven test payloads using [JavaFaker](https://github.com/DiUS/java-faker)
- Common setup with **BaseTest** for reusability
- Organized test classes per endpoint
- **Smoke**, **Regression**, and **Full** TestNG suites

---

## 🛠️ Tech Stack
- **Java 11+**
- **Maven**
- **Rest Assured**
- **TestNG**
- **Faker**

---

## 📂 Project Structure
src
└── test
├── java
│ └── api
│ ├── endpoints # Endpoint methods
│ ├── payload # Request/response POJOs
│ └── tests # Test classes
└── resources
└── testng-*.xml # Suite definitions


---

## ⚙️ Setup Instructions
1. Install **Java 11+**
    - Check version: `java -version`

2. Install **Maven**
    - Check version: `mvn -version`

3. Clone the repository:
   ```bash
   git clone https://github.com/your-username/jsonplaceholder-tests.git
   cd jsonplaceholder-tests
Run tests with Maven + TestNG.
-- Run Full Suite (all CRUD tests):
mvn clean test -DsuiteXmlFile=testng-suite.xml

-- Run Smoke Suite (basic API checks):
mvn clean test -DsuiteXmlFile=testng-smoke.xml

-- Run Regression Suite (positive + negative scenarios):
mvn clean test -DsuiteXmlFile=testng-regression.xml

🤖 AI Disclosure

This project was partially assisted using ChatGPT (OpenAI) to:
Create some negative scenarios except basic ones


📧 Author

Tahyr Atayev
Automation QA Engineer (SDET)