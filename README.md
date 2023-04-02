# Real Estate Loan Api

Simplified solution for customers to apply for a real estate loan.

## Functional requirement

- Admin can create new admin
- Anyone can create new user and apply for loan after login
- User can submit the loan request and able to view all their loan applications
- Admin can view all loan applications submitted by users, and approve or reject the loan application
- Login and Logout functionality backed by spring boot security

# Development

## Technology:

* Kotlin
* Spring boot (3.x)
* Maven as build tool

## Prerequisite

* Java 17 and Maven has to be installed on local machine
* You can run application as Docker container, hence Docker has to be installed
* Application uses postgresql database

## Application URLs

#### Local application URL
http://localhost:8089/loan-api

#### TODO: Cloud application URL
- This can be done via [Heroku](https://heroku.com/) or with some other cloud providers

## Startup instructions

### Start application locally as Docker container
```shell
# go to application directory
cd real-estate-loan-api
# build project using maven
mvn clean install
# build docker image (M1 Chip)
docker buildx build . -t loanapi:latest --platform linux/amd64 --load
# build docker image (Intel Chip)
docker build . -t loanapi:latest
# start application as docker container and start database server
docker compose -p loanapi -f docker/docker-compose.yml up
# and docker container and database server
docker compose -p loanapi -f docker/docker-compose.yml rm
```


### Start application locally as normal spring boot application
```shell
# go to application directory
cd real-estate-loan-api
# build project using maven
mvn clean install
# start database
docker compose -p loanapi -f docker/db-docker-compose.yml up
# and stop database
docker compose -p loanapi -f docker/db-docker-compose.yml rm
# Start spring boot application
mvn spring-boot:run
```

## Testing

Application is having 1 admin and 3 users to access the application

Admin details:

- Login id: `23098334576`
- Login Password: `admin`

User details:

- Login id: `29038934576`/`22037934576`/`21038234576`
- Login Password: `user`

## CI/CD

- Application can be build via Github actions and publish the image to Docker hub
- We could run the image as Kubernetes pod using AKS on Azure or other similar cloud platforms.


## What is missing and could be done
- Integration tests
- Realtime loan application loading for admin user as soon as user submits the loan application
- Frontend tests
- Fix CI/CD pipeline using Github actions
- Deployment on cloud 