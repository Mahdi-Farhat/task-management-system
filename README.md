# task-management-system
Full-Stack Task Management System

System Architecture:
┌───────────────────────┐
│      React UI         │
│                       │
│ Login                 │
│ Dashboard             │
│ Projects              │
│ Tasks                 │
│ Comments              │
└───────────┬───────────┘
            │ Axios
            ▼
┌───────────────────────┐
│ Spring Boot REST API  │
└───────────┬───────────┘
            │
            ▼
┌───────────────────────┐
│     Controllers       │
│                       │
│ AuthController        │
│ UserController        │
│ ProjectController     │
│ TaskController        │
│ CommentController     │
└───────────┬───────────┘
            │
            ▼
┌───────────────────────┐
│       Services        │
│                       │
│ AuthService           │
│ UserService           │
│ ProjectService        │
│ TaskService           │
└───────────┬───────────┘
            │
            ▼
┌───────────────────────┐
│     Repositories      │
│                       │
│ UserRepository        │
│ ProjectRepository     │
│ TaskRepository        │
└───────────┬───────────┘
            │ JPA
            ▼
┌───────────────────────┐
│      PostgreSQL       │
└───────────────────────┘
