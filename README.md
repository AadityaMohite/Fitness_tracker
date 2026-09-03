# Fitness Tracker

A full-stack fitness tracking application with JWT-based authentication, workout logging, goal tracking, and activity stats — built with Angular and Spring Boot.

## 🚀 Live Demo

- **Frontend:** [fitness-tracker-ludl.vercel.app](https://fitness-tracker-ludl.vercel.app)
- **Backend API:** [fitness-tracker-backend-z9d2.onrender.com](https://fitness-tracker-backend-z9d2.onrender.com)

> Note: the backend is on a free-tier host that spins down after inactivity — the first request after idle time can take 30–60s to respond while it cold-starts.

## Features

- User registration and login secured with Spring Security + JWT
- Route guards and HTTP interceptors on the frontend to protect authenticated routes and attach tokens automatically
- Workout logging and history
- Goal setting and progress tracking
- Activity and calorie tracking with dashboard stats
- Role-based access patterns on the backend

## Tech Stack

**Frontend**
- Angular 21
- ng-zorro-antd (UI components)
- Chart.js (data visualization)
- RxJS

**Backend**
- Spring Boot 4.1 (Java 25)
- Spring Security + JWT
- Spring Data JPA / Hibernate
- MySQL

**Infrastructure**
- Frontend hosted on Vercel
- Backend containerized with Docker, hosted on Render
- Database hosted on Clever Cloud (MySQL)

## Architecture

```
Angular (Vercel) → Spring Boot REST API + JWT (Render, Docker) → MySQL (Clever Cloud)
```

Config values (DB credentials, JWT signing key, CORS origin) are injected via environment variables at deploy time — nothing sensitive is committed to the repo. The frontend uses Angular's environment files (`environment.ts` / `environment.prod.ts`) to point at the correct API URL depending on build target.

## Project Structure

```
Fitness_tracker/
├── backend/          # Spring Boot REST API
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
└── frontend/         # Angular application
    ├── src/
    ├── angular.json
    └── package.json
```

## Local Development

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

Runs on `localhost:8080` by default. Requires a local MySQL instance — set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` as environment variables, or edit the defaults in `src/main/resources/application.properties`.

### Frontend

```bash
cd frontend
npm install
ng serve
```

Runs on `localhost:4200`. Points at `localhost:8080` for the API by default in dev mode.

## Deployment

- **Backend:** deployed via Docker on Render, root directory set to `backend`. Environment variables `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET`, and `CORS_ALLOWED_ORIGINS` are set in the Render dashboard.
- **Frontend:** deployed on Vercel, root directory set to `frontend`. `environment.prod.ts` is configured with the live backend URL before build.
- **Database:** MySQL on Clever Cloud's free DEV plan. The connection pool (HikariCP) is capped at 3 connections to stay within the plan's 5-connection limit.

## Known Trade-offs (Free-Tier Hosting)

This project is deployed on free infrastructure for demonstration purposes:
- Render's free web service spins down after inactivity, causing a cold-start delay on the first request.
- Clever Cloud's DEV MySQL plan caps storage at 10MB and concurrent connections at 5 — sufficient for demo traffic, not production load.

## Author

**Aaditya Mohite**
- GitHub: [@AadityaMohite](https://github.com/AadityaMohite)
- LinkedIn: [aaditya-mohite-10b6b228a](https://linkedin.com/in/aaditya-mohite-10b6b228a)
