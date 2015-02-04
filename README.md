This application is acts as Oauth2 Resource Server which provides a HTTP+Json-based API where Oauth2 clients can query information about
the 'groups' a user may belong to. Such clients may then use that information for their own internal authorization
purposes. The way this information is shared adheres to the [Voot protocol](http://openvoot.org/).

The info below is geared towards developers that need to work on this codebase, not to those that may want to use this API.

# Getting started
This project uses Spring Boot and Maven. To run locally, type:

`mvn spring-boot:run`

# Deployment
The application contains an embedded Tomcat, so it isn't deployed as a .war-file. Instead, the application is
started like any other Java programme (eg. java -jar myjarfile).

Within the OpenConext platform, this application will be fronted by Apache webserver. Apache will use Shibboleth to
secure the application with SAML2.

## Configuration
On its classpath, the application has an [application.properties](src/main/resources/application.properties) file that
contains configuration defaults that are convenient when developing.

When the application actually gets deployed to a meaningful platform, it is started with a config-option so that another
properties file is loaded after the default one, overriding those default values. Example:

`$ java -jar myproject.jar --spring.config.location=/etc/myapp.properties`

For details, see the [Spring Boot manual](http://docs.spring.io/spring-boot/docs/1.2.1.RELEASE/reference/htmlsingle/).