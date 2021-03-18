# SOEN390 Project [Team 11]
[![Build Status](https://travis-ci.com/Solunra/SOEN390-team11.svg?token=qjefmiJCZRcnxVWWGqs5&branch=main)](https://travis-ci.com/Solunra/SOEN390-team11)
### Description

---
An ERP solutions project written in Java with Spring and showcased on the web with the help of React.

### UI Prototype

---
[UI prototype for Sprint #4 user stories](https://www.figma.com/file/KfSGbD4kVYndR2odTDtEDW/ERP-for-Sprint-%234-user-stories?node-id=0%3A1)

### Code Coverage

---
[![codecov](https://codecov.io/gh/Solunra/SOEN390-team11/branch/main/graph/badge.svg?token=GM2SQ4TIGU)](https://codecov.io/gh/Solunra/SOEN390-team11) \
[Code Coverage for Controllers](https://codecov.io/gh/Solunra/SOEN390-team11/tree/main/spring/src/main/java/com/soen390/team11/controller)

### Release Report

---
A visual representation of the Release Report can be seen [here](https://app.zenhub.com/workspaces/team11-60049e0484eafc0011dd9ab2/reports/release?release=6035e007364f205d9f1fd469)

### For Developing

---
Follow the Guide first.\
After the first run to start up `spring` and `react`:\
Run the commands `gradle bootRun` and `npm start` respectively

**Note that when running it outside the container, in application.properties, update db to localhost**\
**The reverse should be done if you are using the container: aka update localhost to db**
### Guide

---
Go into the subfolder `react` and `spring` on separate terminals\
Run `npm install` and `gradlew assemble` respectively (Mac-users should use `./gradlew` instead of `gradle`)\
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
