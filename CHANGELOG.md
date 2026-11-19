# Change Log

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/)
and this project adheres to [Semantic Versioning](http://semver.org/).

## 8.1.0

- Removed the custom `CachingOpaqueTokenIntrospector`; access tokens are now introspected on every request with Spring's `SpringOpaqueTokenIntrospector`. The `checkToken.cache` property is no longer used
- Outgoing requests to group providers now send a `User-Agent` header containing the application version (see [#37](https://github.com/OpenConext/OpenConext-voot/issues/37))
- Added the SMTP runtime dependency required by logback's `SMTPAppender` in the production logging configuration
- Docker image now uses the OpenConext-Basecontainers JRE Alpine base image instead of a JDK image
- Upped Spring Boot to 4.1.2
- Upped dependencies: commons-lang3 3.21.0, nimbus-jose-jwt 10.10, rest-assured 6.3.0, maven-compiler-plugin 3.16.0, jacoco-maven-plugin 0.8.16, maven-enforcer-plugin 3.7.0
- Bumped GitHub Actions (including actions/setup-java 6)

## 8.0.0

- Migrated to Spring Boot 4.1.0 (see [#45](https://github.com/OpenConext/OpenConext-voot/issues/45))
- Upped all dependencies
