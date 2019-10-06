# Project template

Here is project tempate for all your homeworks.

## Project structure

* **build.gradle** - gradle build script file with required (checkstyle, jacoco) and optional (pmd, findbugs) tasks
* **gradlew, gradlew.bat** - Windows batch and Unix Shell script files (for executing a build with the Gradle Wrapper)  
* **gradle/wrapper/** - gradle wrapper sources folder
* **config/** - folder for config files (checkstyle, etc.)

### Gradle build
```bash
./gradlew clean build
```
### Gradle run test
```bash
./gradlew clean build test
```
### Maven build
```bash
mvn clean install
```
### Maven run test
```bash
mvn clean install test
```
### Ant+Ivy
Install ivy
```bash
ant init-ivy
```
Build project with tests
```bash
ant clean publish-all test-all
```
