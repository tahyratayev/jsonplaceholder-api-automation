# Test Plan Outline – JSONPlaceholder API
Repo: https://github.com/tahyratayev/jsonplaceholder-api-automation.git

## Scope
- **System Under Test:** [JSONPlaceholder](https://jsonplaceholder.typicode.com/)
- **Out of scope:** Authentication, persistence (API does not store data permanently)
- **Endpoints in scope:**
    - `GET /posts`
    - `GET /posts/{id}`
    - `POST /posts`
    - `PUT /posts/{id}`
    - `DELETE /posts/{id}`

---

## Test Scenarios (Positive & Negative)

- **TC01 – GET /posts (positive)**  
  Expectation: `200 OK`, `Content-Type: application/json`, list contains at least one record

- **TC02 – GET /posts/{id} with valid id (positive)**  
  Expectation: `200 OK`, response object has matching `id`, `title` and `body` are not null

- **TC03 – POST /posts with valid payload (positive)**  
  Expectation: `201 Created`, response contains the sent `userId/title/body` and a generated `id`

- **TC04 – PUT /posts/{id} with valid id (positive)**  
  Expectation: `200 OK`, response reflects updated values for `title`, `body`, and `userId`

- **TC05 – DELETE /posts/{id} with valid id (positive)**  
  Expectation: `200 OK` or `204 No Content`

- **TC06 – GET /posts/{id} with invalid/non-existing id (negative)**  
  Expectation: `404 Not Found`

- **TC07 – POST /posts with malformed or missing fields (negative)**  
  Expectation: Real APIs return `400/422`; JSONPlaceholder may return `201` → validate schema/fields

- **TC08 – POST /posts with wrong Content-Type (negative)**  
  Expectation: Real APIs return `400/415`; JSONPlaceholder may return `201` → note this behavior

---

## Key Validations
- **Status Codes:**
    - `200` for successful GET/PUT
    - `201` for successful POST
    - `204` or `200` for successful DELETE
    - `404` for invalid IDs
    - `400/415` for malformed requests or wrong content type

- **Response Format:**
    - `Content-Type: application/json`
    - JSON schema validation (`id`, `userId`, `title`, `body`)

- **Body Assertions:**
    - GET by id: returned `id` matches request, `title` and `body` not null
    - POST/PUT: response `title/body/userId` match request, `id` exists and > 0
    - GET all: response array size > 0

- **Headers (basic checks):**
    - `Content-Type`
    - Optional: observe `Cache-Control`, `Pragma`

- **Negative Testing Strategy:**
    - Since JSONPlaceholder does not persist data, a POST followed by GET with the same id may return `404`.
    - This is an expected limitation of the fake API and should be documented in test notes.  
