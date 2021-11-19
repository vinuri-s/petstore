# PetStore Application

## Introduction

MicroProfile Starter has generated this MicroProfile application for you.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Packaging and running the application

If you want to build an _??ber-jar_, execute the following command:

    ./gradlew build -Dquarkus.package.type=uber-jar

To run the application:

    java -jar build/petstore-runner.jar

The application can be also packaged using simple:

    ./gradlew build

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it is not an _??ber-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

To launch the test page, open your browser at the following URL

    http://localhost:8080/index.html

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

    ./gradlew quarkusDev

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Creating a native executable

Mind having GRAALVM_HOME set to your Mandrel or GraalVM installation.

You can create a native executable using:

    ./gradlew build -Dquarkus.package.type=native

Or, if you don't have [Mandrel](https://github.com/graalvm/mandrel/releases/) or
[GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases) installed, you can run the native executable
build in a container using:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true

Or to use Mandrel distribution:

    ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel:20.3-java11

You can then execute your native executable with:

    ./build/petstore-runner

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

## Creating a Docker Image

```./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true```

To check built docker image

```docker images```

To run the docker image

```docker -i --rm -p 8080:8080 vinuri/petstore:unspecified```
Specify your relevant Image name & tag.

You can check running containers with below command
```docker ps```

Then, try accessing http://localhost:8080/v1/pets

## Deploying Application

To deploy the demo app on a docker-compose please visit [./deploy](https://github.com/vinuri-s/petstore/blob/master/deploy/README.md)

## CURL Commands

CURL/WGET command to test the APIs once deployed
```curl -v <url>```
e.g. ```curl -v http://localhost:8080/v1/pets```

### ◾ Pets

i. Get all pets

`curl --location --request GET http://localhost:8080/v1/pets`

ii. Get pet by ID

`curl --location --request GET http://localhost:8080/v1/pets/1`

iii. Add a pet

`curl -H "Content-Type: application/json" -X POST http://localhost:8080/v1/pets/add -d "{\"petAge\": \"1\",\"petId\": \"4\",\"petName\": \"Tiki\", \"petType\": \"Fish\"}"`

iv. Delete a Pet

`curl --location --request DELETE http://localhost:8080/v1/pets/delete/3`

v. Update a Pet

`curl -H "Content-Type: application/json" -X PUT http://localhost:8080/v1/pets/4 -d "{\"petAge\": \"2\",\"petName\": \"Kiki\", \"petType\": \"Fish\"}"`

vi. Search pet by name

`curl --location --request GET http://localhost:8080/v1/pets/name/Sudda`

vii. Search pet by age

`curl --location --request GET http://localhost:8080/v1/pets/age/2`

### ◾ Pet Types

i. Get all types

`curl --location --request GET http://localhost:8080/v1/pets-types`

ii. Get pet type by ID

`curl --location --request GET http://localhost:8080/v1/pets-types/1`

iii. Add a pet type

`curl -H "Content-Type: application/json" -X POST http://localhost:8080/v1/pets-types/add -d "{\"petId\": \"4\",\"petTypeType\": \"Fish\"}"`

iv. Delete a Pet type

`curl --location --request DELETE http://localhost:8080/v1/pets-types/delete/2`

v. Update a Pet

`curl -H "Content-Type: application/json" -X PUT http://localhost:8080/v1/pets-types/3 -d "{\"petTypeType\": \"Cat\"}"`

## Testing

To run test suite
`./gradlew test `

You can find the test cases in the [src/test](https://github.com/vinuri-s/petstore/tree/master/src/test/java/org/acme) folder. (PetResourceTest.java & PetTypeResourceTest.java)

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/1a5fbb302c1d8b3249a7?action=collection%2Fimport)


## Specification examples

By default, there is always the creation of a JAX-RS application class to define the path on which the JAX-RS endpoints are available.

Also, a simple Hello world endpoint is created, have a look at the class **HelloController**.

More information on MicroProfile can be found [here](https://microprofile.io/)

### Config

Configuration of your application parameters. Specification [here](https://microprofile.io/project/eclipse/microprofile-config)

The example class **ConfigTestController** shows you how to inject a configuration parameter and how you can retrieve it programmatically.

### Fault tolerance

Add resilient features to your applications like TimeOut, RetryPolicy, Fallback, bulkhead and circuit breaker. Specification [here](https://microprofile.io/project/eclipse/microprofile-fault-tolerance)

The example class **ResilienceController** has an example of a FallBack mechanism where an fallback result is returned when the execution takes too long.

### Health

The health status can be used to determine if the 'computing node' needs to be discarded/restarted or not. Specification [here](https://microprofile.io/project/eclipse/microprofile-health)

The class **ServiceHealthCheck** contains an example of a custom check which can be integrated to health status checks of the instance.  The index page contains a link to the status data.

### Metrics

The Metrics exports _Telemetric_ data in a uniform way of system and custom resources. Specification [here](https://microprofile.io/project/eclipse/microprofile-metrics)

The example class **MetricController** contains an example how you can measure the execution time of a request.  The index page also contains a link to the metric page (with all metric info)

### JWT Auth

Using the OpenId Connect JWT token to pass authentication and authorization information to the JAX-RS endpoint. Specification [here](https://microprofile.io/project/eclipse/microprofile-rest-client)

Have a look at the **TestSecureController** class which calls the protected endpoint on the secondary application.
The **ProtectedController** (secondary application) contains the protected endpoint since it contains the _@RolesAllowed_ annotation on the JAX-RS endpoint method.

The _TestSecureController_ code creates a JWT based on the private key found within the resource directory.
However, any method to send a REST request with an appropriate header will work of course. Please feel free to change this code to your needs.

### Open API

Exposes the information about your endpoints in the format of the OpenAPI v3 specification. Specification [here](https://microprofile.io/project/eclipse/microprofile-open-api)

The index page contains a link to the OpenAPI information of your endpoints.

### Open Tracing

Allow the participation in distributed tracing of your requests through various micro services. Specification [here](https://microprofile.io/project/eclipse/microprofile-opentracing)

To show this capability download [Jaeger](https://www.jaegertracing.io/download/#binaries) and run ```./jaeger-all-in-one```.
Open [http://localhost:16686/](http://localhost:16686/) to see the traces. Mind that you have to access your demo app endpoint for any traces to show on Jaeger UI.


