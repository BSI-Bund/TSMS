# yaml2javaclient

*Last updated: 14.02.2022*

The module **yaml2javaclient** creates a JAVA library with data structure and class methods to access the TSM-Backend REST-API specified in section 4.1 of [BSI-TR-03165](https://www.bsi.bund.de/DE/Themen/Unternehmen-und-Organisationen/Standards-und-Zertifizierung/Technische-Richtlinien/TR-nach-Thema-sortiert/tr03165/tr-03165.html). It uses [Swagger Codegen](https://swagger.io/tools/swagger-codegen/) to generate the classes, compiles them and builds a JAR. It is part of the code-generation submodules from [TSM-REST-API](../../README.md).

Content:

<ol>
  <li><a href="README.md#motivation">Motivation</a></li>
  <li><a href="README.md#project_results">Project Results</a></li>
  <li><a href="README.md#documentation">Documentation</a>
    <ol>
      <li><a href="README.md#project_structure">Project Structure</a>
      <li><a href="README.md#build">Build the Project</a></li>
      <li><a href="README.md#swagger_codegen">Swagger Codegen</a></li>
      <li><a href="README.md#javaclient_usage">JavaClient Usage</a></li>
    </ol>
  </li>
</ol>

<a name="motivation"></a>
## 1. Motivation
Part of BSI-TR-03165 is the definition of a REST-API. The idea of this module is to provide a JAVA JAR library which can be used for test automatization and also development automation for service providers using a TSMS as described by the TR. With this jar, it is possible to automatically create Service, Versions, Flavors inside the TSM-Backend. It is also possible to upload or replace JavaCard CAP files and to use it to configure the Flavors. 


<a name="project_results"></a>
## 2. Project Results

The result of this project is a JAR file which can be integrated into a JAVA application. The files are located in the [dist](../../dist) folder of the parent project TSM-REST-API. Naming convention is as follows:

```
../../dist/${VERSION}/tsm-rest-api-java-client-${VERSION}.jar
../../dist/${VERSION}/tsm-rest-api-java-client-${VERSION}-javadoc.jar
../../dist/${VERSION}/tsm-rest-api-java-client-${VERSION}-sources.jar
../../dist/${VERSION}/tsm-rest-api-java-client-${VERSION}-README.zip

../../dist/${VERSION}/generate-tsm-rest-api-java-client-${VERSION}.bat
../../dist/${VERSION}/generate-tsm-rest-api-java-client-${VERSION}-pom.xml
```


<a name="documentation"></a>
## 3. Documentation

<a name="project_structure"></a>
### 3.1 Project Structure

The project contains two Maven files:
* [generate-java-client-pom.xml](generate-java-client-pom.xml)
  * uses Swagger Codegen to generate the JAVA files
* [pom.xml](pom.xml) 
  * main Maven script which calls the other generate-java-client-pom.xml script to generate the JAVA files and afterwards compiles the files to create the resulting artifacts


<a name="build"></a>
### 3.2 Build the Project

Perform the following steps to set up a development environment to build the project.
 
1. Install Apache Maven >= 3.0.0

2. Build the maven project
  ```
  mvn clean install
  ```

*Attention: Actually, it is not needed to build this project. All final artifacts are already in the repository inside the [dist](../../dist) folder.*

<a name="swagger_codegen"></a>
### 3.3 Swagger Codegen

Prerequisites: an OpenApi specification of a REST-API must be available in form of a YAML or JSON file.

This module uses [Swagger Codegen](https://swagger.io/tools/swagger-codegen/) to generate the JAVA code from an OpenApi specification. Since it is a Maven based project, it uses the Maven plugin 
[swagger-codegen-maven-plugin](https://github.com/swagger-api/swagger-codegen/tree/master/modules/swagger-codegen-maven-plugin) via the following configuration:

```xml
    <!-- generate java-client -->
    <plugin>
        <groupId>io.swagger.codegen.v3</groupId>
        <artifactId>swagger-codegen-maven-plugin</artifactId>
        <version>3.0.30</version>
        <executions>
            <execution>
                <phase>compile</phase>
                <goals>
                    <goal>generate</goal>
                </goals>
                <configuration>
                    <!-- specify the swagger yaml -->
                    <inputSpec>${yaml}</inputSpec>

                    <!-- target to generate java client code -->
                    <language>java</language>
                    <addCompileSourceRoot>true</addCompileSourceRoot>
                    <invokerPackage>de.bsi.tsms.tsmrestapi</invokerPackage>
                    <modelPackage>de.bsi.tsms.tsmrestapi.model</modelPackage>
                    <apiPackage>de.bsi.tsms.tsmrestapi.api</apiPackage>
                    <withXml>false</withXml>
                    <output>${dest}</output>
                    <groupId>de.bsi.tsms.tsm-rest-api</groupId>
                    <artifactId>java-client</artifactId>
                    <artifactVersion>${project.version}</artifactVersion>

                    <!-- hint: if you want to generate java server code, e.g. based on Spring Boot,
                         you can use the following target: <language>spring</language> -->

                    <!-- pass any necessary config options -->
                    <configOptions>
                        <dateLibrary>joda</dateLibrary>
                    </configOptions>

                    <!-- override the default library to jersey2 -->
                    <library>jersey2</library>
                </configuration>
            </execution>
        </executions>
    </plugin>
```

<a name="javaclient_usage"></a>
### 3.4 Java-Client Usage
Usage the Java-Client might slightly change depending on the version of BSI-TR-03165 used to generate the JAVA files. To address this problem, there is an automatic generated documentation in form of Markdown files for each version located in the dist directory:

```
../../dist/${VERSION}/tsm-rest-api-java-client-${VERSION}-README.zip
```

The Java-Client Usage documentation for the latest TSM-Backend REST-API can be found inside the parent Section [3.6 API-Methods](../../README.md#api_methods) of the parent README documentation.

[Back to TSM-REST-API](../../README.md)
