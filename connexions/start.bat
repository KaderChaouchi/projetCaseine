@echo off

start "" java -jar \targetconnexions-0.0.1-SNAPSHOT.jar

timeout /t 3 >nul

start "" npx localtunnel --port8080 --subdomain monserveur

pause