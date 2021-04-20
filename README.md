# SOEN390 Project [Team 11]
[![CircleCI](https://circleci.com/gh/Solunra/SOEN390-team11.svg?style=svg&circle-token=c065fd8efe322775fbfce01e8731da3cdc48a2f0)](https://app.circleci.com/pipelines/github/Solunra/SOEN390-team11) [![codecov](https://codecov.io/gh/Solunra/SOEN390-team11/branch/main/graph/badge.svg?token=GM2SQ4TIGU)](https://codecov.io/gh/Solunra/SOEN390-team11) [![codecov](https://img.shields.io/static/v1?label=codecov&logo=codecov&color=red&message=controllers)](https://codecov.io/gh/Solunra/SOEN390-team11/tree/main/spring/src/main/java/com/soen390/team11/controller)

### Description

---
An ERP solutions project written in Java with Spring and showcased on the web with the help of React.

### Docker
[Docker Frontend Containers](https://hub.docker.com/r/solunra/soen390-fe)  
[Docker Backend Containers](https://hub.docker.com/r/solunra/soen390-be)  
[Docker-Compose File](https://github.com/Solunra/SOEN390-team11/blob/main/docker-compose.yml)

### UI Prototype

---
[UI prototype for Sprint #5 user stories](https://www.figma.com/file/MUdEB0TVcH8xkSz4tea6Xw/ERP-for-Sprint-5?node-id=0%3A1)


### Release Report

---
A visual representation of the Release Report can be seen [here](https://app.zenhub.com/workspaces/team11-60049e0484eafc0011dd9ab2/reports/release?release=606e40cb0e90eb032c776b0c)

### For Demo

---
With the docker-compose file, run `docker-compose up`

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

#### Front End Automatically Reformat Code Style
In terminal, first `cd` to `react` subdirectory and then run `npx prettier --write .`.  
This will format all the source code according to configuration defined in `react/.prettierrc.yml` (see [Configuration File · Prettier](https://prettier.io/docs/en/configuration.html). 

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
