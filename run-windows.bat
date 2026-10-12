@echo off
cd /d "%~dp0\backend"
where mvn >nul 2>nul
if errorlevel 1 (
 echo Maven not found. Install JDK 17+ and Apache Maven, then reopen VS Code.
 pause
 exit /b 1
)
mvn spring-boot:run
pause
