# CourZello 

This project is an Angular application for managing courses, chapters, users, departments, and classrooms, integrated with a Spring Boot backend.

Prerequisites

- Node.js (version 14.20.0 or higher)
- npm (version 6.11.0 or higher)
- Java Development Kit (JDK) (version 11 or higher)
- Maven (version 3.6.0 or higher)

Getting Started

Installation

1. Clone the repository:

   git clone 
   cd front-office-pi-dev

2. Install frontend dependencies:

   npm install

3. Install backend dependencies:

   cd backend
   mvn clean install

Development Server

To start the frontend development server, run:

   npm start

The app will be available at http://localhost:4200/.

To start the backend server, run:

   cd backend
   mvn spring-boot:run

The backend server will be available at http://localhost:8095/.

Build

To build the frontend project, run:

   npm run build

The build artifacts will be stored in the dist/ directory. Use the --prod flag for a production build.

To build the backend project, run:

   cd backend
   mvn clean package

Running Unit Tests

To execute the frontend unit tests via Karma, run:

   npm run test

To execute the backend unit tests via Maven, run:

   cd backend
   mvn test

Running End-to-End Tests

To execute the end-to-end tests via Protractor, run:

   npm run e2e

Linting

To lint the frontend project, run:

   npm run lint

Project Structure

- src/app/ - Contains the main application code.
- src/assets/ - Contains static assets (e.g., images, styles).
- src/environments/ - Contains environment configuration files.
- backend/ - Contains the Spring Boot backend application.

Configuration Files

- angular.json - Angular CLI configuration.
{
  "$schema": "./node_modules/@angular/cli/lib/config/schema.json",
  "version": 1,
  "newProjectRoot": "projects",
  "projects": {
    "frontOfficePiDev": {
      "projectType": "application",
      "schematics": {},
      "root": "",
      "sourceRoot": "src",
      "prefix": "app",
      "architect": {
        "build": {
          "builder": "@angular-devkit/build-angular:browser",
          "options": {
            "outputPath": "dist/front-office-pi-dev",
            "index": "src/index.html",
            "main": "src/main.ts",
            "polyfills": [
              "zone.js"
            ],
            "tsConfig": "tsconfig.app.json",
            "assets": [
              "src/favicon.ico",
              "src/assets"
            ],
            "styles": [
              "src/styles.css"
            ],
            "scripts": []
          },
          "configurations": {
            "production": {
              "budgets": [
                {
                  "type": "initial",
                  "maximumWarning": "500kb",
                  "maximumError": "1mb"
                },
                {
                  "type": "anyComponentStyle",
                  "maximumWarning": "2kb",
                  "maximumError": "4kb"
                }
              ],
              "outputHashing": "all"
            },
            "development": {
              "buildOptimizer": false,
              "optimization": false,
              "vendorChunk": true,
              "extractLicenses": false,
              "sourceMap": true,
              "namedChunks": true
            }
          },
          "defaultConfiguration": "production"
        },
        "serve": {
          "builder": "@angular-devkit/build-angular:dev-server",
          "configurations": {
            "production": {
              "browserTarget": "frontOfficePiDev:build:production"
            },
            "development": {
              "browserTarget": "frontOfficePiDev:build:development"
            }
          },
          "defaultConfiguration": "development"
        },
        "extract-i18n": {
          "builder": "@angular-devkit/build-angular:extract-i18n",
          "options": {
            "browserTarget": "frontOfficePiDev:build"
          }
        },
        "test": {
          "builder": "@angular-devkit/build-angular:karma",
          "options": {
            "polyfills": [
              "zone.js",
              "zone.js/testing"
            ],
            "tsConfig": "tsconfig.spec.json",
            "assets": [
              "src/favicon.ico",
              "src/assets"
            ],
            "styles": [
              "src/styles.css"
            ],
            "scripts": []
          }
        }
      }
    }
  },
  "cli": {
    "analytics": "67d0ff20-459b-4566-ae2c-44e9cee3ab95"
  }
}


- package.json - Node package dependencies and scripts.

{
  "name": "front-office-pi-dev",
  "version": "0.0.0",
  "scripts": {
    "ng": "ng",
    "start": "ng serve",
    "build": "ng build",
    "watch": "ng build --watch --configuration development",
    "test": "ng test"
  },
  "private": true,
  "dependencies": {
    "@angular/animations": "^15.2.0",
    "@angular/common": "^15.2.0",
    "@angular/compiler": "^15.2.0",
    "@angular/core": "^15.2.0",
    "@angular/forms": "^15.2.0",
    "@angular/platform-browser": "^15.2.0",
    "@angular/platform-browser-dynamic": "^15.2.0",
    "@angular/router": "^15.2.0",
    "rxjs": "~7.8.0",
    "tslib": "^2.3.0",
    "zone.js": "~0.12.0"
  },
  "devDependencies": {
    "@angular-devkit/build-angular": "^15.2.10",
    "@angular/cli": "~15.2.10",
    "@angular/compiler-cli": "^15.2.0",
    "@types/jasmine": "~4.3.0",
    "jasmine-core": "~4.5.0",
    "karma": "~6.4.0",
    "karma-chrome-launcher": "~3.1.0",
    "karma-coverage": "~2.2.0",
    "karma-jasmine": "~5.1.0",
    "karma-jasmine-html-reporter": "~2.0.0",
    "typescript": "~4.9.4"
  }
}


- tsconfig.json - TypeScript configuration.
/* To learn more about this file see: https://angular.io/config/tsconfig. */
{
  "extends": "./tsconfig.json",
  "compilerOptions": {
    "outDir": "./out-tsc/app",
    "types": []
  },
  "files": [
    "src/main.ts"
  ],
  "include": [
    "src/**/*.d.ts"
  ]
}

- .editorconfig - Editor configuration for consistent coding styles.
- .gitignore - Specifies which files and directories to ignore in the repository.
- pom.xml - Maven configuration for the Spring Boot backend.

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.2</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.pidev</groupId>
    <artifactId>backend</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>backendPidev</name>
    <description>backendPidev</description>
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mongodb</groupId>
            <artifactId>mongodb-driver-sync</artifactId>
            <version>4.11.0</version> <!-- Replace with the latest version -->
        </dependency>


        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.11.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.15.2</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-websocket</artifactId>
            <version>3.2.2</version>
        </dependency>


        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>

        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-core</artifactId>
            <version>3.4.14</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>










Additional Notes

- This project uses Angular version 15.2.0.
- Ensure you have the correct versions of Node.js, npm, JDK, and Maven installed as specified in the engines field of package.json and the Maven configuration.





# Course Recommendation System

## Overview
This Course Recommendation System is designed to enhance the educational journey of students by providing personalized course suggestions. Utilizing advanced machine learning techniques, this system analyzes student data and preferences to recommend the most suitable courses that align with their academic and career objectives.

## Features
- Personalized Recommendations: Offers tailor-made course suggestions to each student.
- Machine Learning Integration: Employs robust algorithms to accurately predict and suggest courses.
- User-Friendly Interface: Ensures a smooth user experience with a simple and intuitive interface.

## Technologies Used

- Python: For backend logic and machine learning algorithms.
- Jupyter Notebook: For initial prototyping, testing algorithms, and data analysis.
- Spring Boot: For building the backend services.
- Maven: For managing Java dependencies and build automation.

## Setup and Installation
To get this project up and running on your local machine, follow these steps:

1. Clone the repository:
   git clone

2. Navigate to the project directory:
   cd YourRepository

3. Install the frontend dependencies:
   npm install

4. Install the backend dependencies:
   cd backend
   mvn clean install

## Usage
After installation, launch the frontend system:
   npm start

Launch the backend system:
   cd backend
   mvn spring-boot:run

For the Course Recommendation System, launch the Jupyter Notebook:
   jupyter notebook

Navigate to the Main.ipynb file to view the project.

## Contributing
Contributions to enhance the functionality or performance of this system are welcome. Please fork the repository and submit a pull request with your improvements.