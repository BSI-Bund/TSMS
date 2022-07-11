# yaml2json

*Last updated: 14.02.2022*

The module **yaml2json** is a small helper script to convert an OpenApi YAML file to an OpenApi JSON file. It is used to create an OpenApi JSON file for the TSM-Backend REST-API specified in Section 4.1 of [BSI-TR-03165](https://www.bsi.bund.de/DE/Themen/Unternehmen-und-Organisationen/Standards-und-Zertifizierung/Technische-Richtlinien/TR-nach-Thema-sortiert/tr03165/tr-03165.html). It is part of the code-generation submodules from [TSM-REST-API](../../README.md).

Content:

<ol>
  <li><a href="README.md#motivation">Motivation</a></li>
  <li><a href="README.md#project_results">Project Results</a></li>
  <li><a href="README.md#documentation">Documentation</a>
    <ol>
      <li><a href="README.md#project_structure">Project structure</a>
      <li><a href="README.md#build">Build the project</a></li>
      <li><a href="README.md#swagger_codegen">Swagger Codegen</a></li>
    </ol>
  </li>
</ol>

<a name="motivation"></a>
## 1. Motivation
OpenApi was formerly defined only in JSON format. So for some older tools, it is still necessary to use the JSON format instead of the newer YAML format. The goal of this module is to transform an OpenApi YAML file to JSON format.


![TSMS](../../../tsm-graphics/yaml2json.png)

<a name="project_results"></a>
## 2. Project Results

The result of this project is an OpenApi JSON file describing the TSM-Backend REST-API of BSI-TR-03165. The files are located in the [dist](../../dist) folder of the parent project TSM-REST-API. Naming convention is as follows:

```
../../dist/${VERSION}/tsm-rest-api-${VERSION}.json
```


<a name="documentation"></a>
## 3. Documentation

<a name="project_structure"></a>
### 3.1 Project structure

The project contains a Maven [pom.xml](pom.xml) file which performs necessary steps to transform the OpenApi YAML file to an OpenAPI JSON file. 


<a name="build"></a>
### 3.2 Build the project

Perform the following steps to set up a development environment to build the project.
 
1. Install Apache Maven >= 3.0.0

2. Build the maven project
  ```
  mvn clean install
  ```

*Attention: Actually, it is not needed to build this project. All final artifacts are already in the repository inside the [dist](../../dist) folder.*

<a name="swagger_codegen"></a>
### 3.3 Swagger-Codegen

Prerequisites: an OpenApi specification of a REST-API must be available in form of a YAML file.

This module uses [Swagger Codegen](https://swagger.io/tools/swagger-codegen/) to transform an OpenApi YAML file to an OpenAPI JSON file. Since it is a Maven based project, it uses the Maven plugin 
[swagger-codegen-maven-plugin](https://github.com/swagger-api/swagger-codegen/tree/master/modules/swagger-codegen-maven-plugin) herefore via the following configuration:

```xml
    <!-- generate json file -->
    <plugin>
        <groupId>io.swagger.codegen.v3</groupId>
        <artifactId>swagger-codegen-maven-plugin</artifactId>
        <version>3.0.30</version>
        <executions>
            <execution>
                <id>generate-openapi.json</id>
                <phase>compile</phase>
                <goals>
                    <goal>generate</goal>
                </goals>
                <configuration>
                    <!-- specify the swagger yaml -->                
                    <inputSpec>${project.basedir}/../../generated-code/tsm-rest-api.yaml</inputSpec>
                    <!-- target to generate json file -->
                    <language>openapi</language>
                    <output>${project.basedir}/target</output>
                </configuration>
            </execution>
        </executions>
    </plugin>
```

[Back to TSM-REST-API](../../README.md)
