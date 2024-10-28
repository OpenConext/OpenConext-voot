# Voot

[![Build Status](https://travis-ci.org/OpenConext/OpenConext-voot.svg)](https://travis-ci.org/OpenConext/OpenConext-voot)
[![codecov.io](https://codecov.io/github/OpenConext/OpenConext-voot/coverage.svg)](https://codecov.io/github/OpenConext/OpenConext-voot)

This application acts as Oauth2 Resource Server which provides a HTTP+Json-based API where Oauth2 clients can query information about
the 'groups' a user may belong to. Such clients may then use that information for their own internal authorization
purposes. The way this information is shared adheres to the [Voot protocol](http://openvoot.org/).

The info below is geared towards developers that need to work on this codebase, not to those that may want to use this API.

### [Create test databases](#create-databases)

Connect to your local mysql database: `mysql -uroot`

Execute the following:

```sql
CREATE DATABASE grouper_local DEFAULT CHARACTER SET latin1;
grant all on grouper_local.* to 'root'@'localhost';
```

# Getting started
This project uses Spring Boot and Maven and JDK21. Install JDK21 and point your `JAVA_HOME` to this directory:

`export JAVA_HOME=/Library/Java/JavaVirtualMachines/openjdk-21.jdk/Contents/Home/`

To run locally, type:

`mvn spring-boot:run`

When developing, it's convenient to just execute the applications main-method, which is in [VootServiceApplication](src/main/java/vootservice/VootServiceApplication).

This application exposes a REST-like API that is secured using oAuth2.
To be able to check the tokens that this application is presented with by its clients, it needs to connect to the oAuth server.
The oAuth server requires this application to authenticate itself. This is setup by a couple of
properties mentioned in the [application.properties](src/main/resources/application.properties) file that start with 'oidcng.checkToken'.


# Deployment
The application contains an embedded Tomcat, so it isn't deployed as a .war-file. Instead, the application is
started like any other Java programme (eg. java -jar myjarfile).

Within the OpenConext platform, this application will be fronted by Apache webserver. Apache will use Shibboleth to
secure the application with SAML2.

## Configuration
On its classpath, the application has an [application.properties](src/main/resources/application.properties) file that
contains configuration defaults that are convenient when developing.

When the application actually gets deployed to a meaningful platform, it is pre-provisioned with ansible and the application.properties depends on
environment specific properties in the group_vars

For details, see the [Spring Boot manual](http://docs.spring.io/spring-boot/docs/1.2.1.RELEASE/reference/htmlsingle/).

# Implementation

This application exposes a [VOOT2-compatible](http://openvoot.org/protocol/) HTTP/Json API.

To answer queries, this application talks to other webservices downstream. Those are:

- Any client compatible with [VOOT2](http://openvoot.org/protocol/)
- Any client compatible with [OpenSocial](http://opensocial.github.io/spec/trunk/Social-API-Server.xml#Groups-Service-GetGroups)
- Grouper 1.6 (the persistent store behind OpenConext Teams)

# Testing

To test the VOOT endpoint the authz-playground can be used. The implementation exposes two endpoints secured by Authorization Code:

```
https://voot.example.org/me/groups
https://voot.example.org/me/groups/${fully qualified group name}
```

Some endpoints are implemented for internal use. They are secured by Client Credentials:

```
https://voot.example.org/internal/external-groups/${fully qualified person urn}
https://voot.example.org/internal/groups/${fully qualified person urn}/${fully qualified group name}
https://voot.example.org/internal/groups/${fully qualified person urn}
https://voot.example.org/internal/all-groups
```

The first internal endpoint is used by OpenConext Teams to fetch all external teams.

An additional endpoint (requiring Client Credentials) is implemented to retrieve all members of a group:

```
https://voot.example.org/members/${fully qualified group name}
https://voot.example.org/members/${fully qualified person urn}/${fully qualified group name}
```


