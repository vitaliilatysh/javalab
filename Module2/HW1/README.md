# Project template

Here is project tempate for all your homeworks.

## Project structure

* **build.gradle** - gradle build script file with required (checkstyle, jacoco) and optional (pmd, findbugs) tasks
* **gradlew, gradlew.bat** - Windows batch and Unix Shell script files (for executing a build with the Gradle Wrapper)  
* **gradle/wrapper/** - gradle wrapper sources folder
* **config/** - folder for config files (checkstyle, etc.)

### Gradle Build Tasks

Everything is already included into 'build' task. 

Command for Windows based OS:
```
gradlew build
```

Command for Unix based OS:
```
gradle build
```