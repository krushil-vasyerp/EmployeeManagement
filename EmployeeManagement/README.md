## Note
### This project uses **Java enums mapped to database constraints**.

- If you want to **add or modify enum values** (e.g. `Role`, `DepartmentName`, `DeskType`, `TaskStatus`):
    1. Update the corresponding **Java enum**
    2. **Manually delete existing tables**
    3. Restart the application to recreate tables

> Once enum-based tables are created, PostgreSQL will **not allow values outside defined enum keywords**.

---

## API Flow (Execution Order)

### Mandatory Setup (Before Calling APIs)

- Manually insert:
    - **At least one Department**
    - **At least one Employee**
- This is required because:
    - A `User` must be linked to an existing `Employee`
    - Authentication depends on an existing user

---

### Application Flow


    AuthController
        | POST /auth/login
        | JWT Token
        v
    Client (Authenticated)
        v
    Client
        |
        | POST /department
        v
    DepartmentController
        |
        | POST /employee
        v
    EmployeeController
        |
        | POST /user/employee/{employeeId}
        v
    UserController


## Authentication

### Auth API
| Method | Endpoint |
|------|---------|
| POST | `/auth/login` |

- Returns a **JWT token**
- Token is required for accessing secured endpoints

---

## 🏢 Department APIs

| Method | Endpoint |
|------|---------|
| GET | `/department` |
| GET | `/department/{id}` |
| POST | `/department` |
| PUT | `/department/{id}` |
| DELETE | `/department/{id}` |
| GET | `/department/employees` |

- Departments are **enum-controlled**
- One department can have **multiple employees**

---

## 👤 Employee APIs

| Method | Endpoint |
|------|---------|
| GET | `/employee` |
| GET | `/employee/{id}` |
| POST | `/employee` |
| PUT | `/employee/{id}` |
| DELETE | `/employee/{id}` |

- Each employee belongs to **one department**
- Employee is the **core entity** of the system

---

## 👥 User APIs

| Method | Endpoint |
|------|---------|
| GET | `/user` |
| GET | `/user/{id}` |
| POST | `/user/employee/{employeeId}` |
| PUT | `/user/{userId}` |
| DELETE | `/user/{userId}` |
| GET | `/user/ACTIVE` |

- One-to-One mapping with Employee
- Passwords are **BCrypt encrypted**
- Used for authentication and authorization

---

## 💰 Salary APIs

| Method | Endpoint |
|------|---------|
| GET | `/salary` |
| GET | `/salary/employee/{employeeId}` |
| POST | `/salary/employee/{employeeId}` |
| PUT | `/salary/employee/{employeeId}` |
| DELETE | `/salary/employee/{employeeId}` |
| GET | `/salary/highest` |

- One employee can have **only one salary**
- Salary is tightly linked with Employee

---

## 🪑 Desk APIs

| Method | Endpoint |
|------|---------|
| GET | `/desk` |
| GET | `/desk/{id}` |
| POST | `/desk` |
| PUT | `/desk/{id}` |
| DELETE | `/desk/{id}` |
| PUT | `/desk/{deskId}/assign/{employeeId}` |
| PUT | `/desk/{deskId}/unassign` |
| GET | `/desk/available` |

- Desk types are **enum-based** (`OFFICE`, `NORMAL`, `WFH`)
- One desk can be assigned to **only one employee**

---

## 📋 Task APIs

| Method | Endpoint |
|------|---------|
| GET | `/task` |
| GET | `/task/{taskId}` |
| POST | `/task/employee/{employeeId}` |
| PUT | `/task/{taskId}` |
| DELETE | `/task/{taskId}` |
| GET | `/task/employee/{employeeId}` |
| GET | `/task/{status}` |

- One employee can have **multiple tasks**
- Task status is **enum-controlled**
- Tracks assignment and completion dates

---

## 🧠 Key Design Highlights

- Strong relational mappings (`OneToOne`, `ManyToOne`)
- Enum-driven domain validation
- JWT-based authentication
- Transaction-safe delete operations
- Cascade and orphan removal handled correctly
- Clean REST API structure

---

## ✅ Final Notes

- Follow the **API execution order** strictly
- Do not bypass enum constraints
- Always ensure parent data exists before inserting child data
- Designed for **real-world backend practice**



