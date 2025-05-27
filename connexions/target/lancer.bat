
@echo off
echo Démarrage du serveur Spring Boot...

REM Lancer Spring Boot sur le port 8080
start "" java -jar connexions-0.0.1-SNAPSHOT.jar

REM (Optionnel) Attendre 3 secondes
timeout /t 3 >nul

REM (Optionnel) Ouvrir automatiquement la page
start http://localhost:8080/affiche

pause
