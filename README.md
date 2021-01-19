# SOEN390 Project [Team 11]

### Description

---
An ERP solutions project written in Java with Spring and showcased on the web with the help of React.

### Guide

---
Go into the subfolder `react` and `spring` on separate terminals\
Run `npm install` and `gradlew build` respectively (Mac-users should run `./gradlew`)\
Go back to the project root folder, which is one folder up from either `react` or `spring`\
Run `docker-compose build`\
Then, run `docker-compose up`\
\
Note that subsequent runs can be done with just `docker-compose up`\
Updating the docker containers follows the same logic from the start


### Libraries 

---
[Spring](https://spring.io/) \
[React](https://reactjs.org/) \
[Node Package Manager (npm)](https://www.npmjs.com/) \
[Docker](https://www.docker.com/) \
[Flyway](https://flywaydb.org/)

### Troubleshooting

---
#### On occasions, docker-compose up will fail to run because of npm install 
**Alternative Error**: Cannot read property ‘match’ of undefined\
**Fix**: Delete in `react` folder both `node_modules` `package-lock.json` and rerun npm install within the `react` subfolder
