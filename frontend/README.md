## 🚀 Live Demo

- **Frontend:** [fitness-tracker-ludl.vercel.app](https://fitness-tracker-ludl.vercel.app)
- **Backend API:** [fitness-tracker-backend-z9d2.onrender.com](https://fitness-tracker-backend-z9d2.onrender.com)

## Architecture

```
Angular (Vercel) → Spring Boot REST API + JWT (Render, Docker) → MySQL (Clever Cloud)
```

- **Frontend:** Angular 21, deployed on Vercel with environment-based build configs (`environment.ts` / `environment.prod.ts`) so the app points at the correct API URL per environment.
- **Backend:** Spring Boot 4.1 (Java 25), containerized with a multi-stage Docker build, deployed on Render. Authentication uses Spring Security + JWT, with route-level RBAC.
- **Database:** MySQL 8, hosted on Clever Cloud. Connection pool (HikariCP) tuned to a max of 3 connections to work within the free-tier 5-connection cap.
- **Config:** All secrets (DB credentials, JWT signing key, CORS origin) are injected via environment variables at deploy time — nothing sensitive is committed to the repo.

## Notes on free-tier trade-offs

This is deployed on free infrastructure for demo purposes:
- Render's free web service spins down after inactivity, so the first request after idle can take 30–60s to respond while it cold-starts.
- Clever Cloud's DEV MySQL plan caps storage at 10MB and concurrent connections at 5 — fine for demonstration traffic, not sized for production load.

## Local development

```bash
# Backend
cd backend
./mvnw spring-boot:run
# Runs on localhost:8080, using local MySQL by default

# Frontend
cd frontend
npm install
ng serve
# Runs on localhost:4200
```
