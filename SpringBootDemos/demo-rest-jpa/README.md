# demo-spring-boot project

An example of Spring-boot REST + JPA App with native binary (Windows or Linux)  

Requires GraalVM and native-image.  
For Windows the Visual Studio 2019 is also required.  

Native App requires a external H2 server (see dist\h2_server) and run H2 Server and create a `_DATA_` database.  

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
mvn clean install spring-boot:run
```

## Creating a native executable

You can create a native executable using: 
```shell script
conpile-native.sh
```
>> Note: Need to be tested on Linux.


Or, if you're running on Windows you'll need:  
- Install GraalVM and Native-Image.
- Install Visual Studio 2019
- Open `x64 Native Tools Command Prompt`  

```shell script
compile-native.bat
```
