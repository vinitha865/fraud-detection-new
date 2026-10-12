# FraudShield — Full-Stack Fraud Detection Demo

Frontend: HTML, CSS, JavaScript
Backend: Java 17 + Spring Boot + Maven
Database: H2 file database (no MySQL installation required)
Detection: explainable rule-based scoring for academic demonstration.

## Run in VS Code on Windows
1. Install JDK 17+ and Apache Maven 3.8+.
2. Extract this ZIP.
3. Open the extracted `fraud-detection-fullstack` folder in VS Code.
4. Select Terminal → New Terminal.
5. Run:
   ```powershell
   cd backend
   mvn spring-boot:run
   ```
6. When Spring Boot starts, open http://localhost:8083 in Chrome.
7. Keep the terminal open while using the site. Press Ctrl+C to stop.

Check setup with `java -version` and `mvn -version`.
If `mvn` is not recognized, install Maven and reopen VS Code.
If port 8083 is busy, change `server.port` in `backend/src/main/resources/application.properties`.

## API endpoints
- GET `/api/health`
- POST `/api/fraud/check`
- GET `/api/fraud/history`

## Important
This is a rule-based academic demo, not a trained machine-learning model. Do not use it for real banking decisions or enter real payment/card information.
