# AMQ Messaging using Artemis Core Protocol in SpringBoot
Sample SpringBoot based application using Red Hat AMQ Spring Boot Starter.

## Configuration
Configuration are based on the provided connection options in the Red Hat AMQ Client Library
````properties
spring.artemis.mode=native
spring.artemis.broker-url=tcp://localhost:61616
spring.artemis.user=admin
spring.artemis.password=secret
````

## Build Process
This application is configured with the following dependency which is referenced through Red Hat repository. 
- Add the Red Hat repository to your Maven settings or POM file.
    ````xml
    <repository>
      <id>red-hat-ga</id>
      <url>https://maven.repository.redhat.com/ga</url>
    </repository>
    ````
  - Add the library dependency to your POM file. 
      ````xml
              <dependency>
                  <groupId>org.apache.activemq</groupId>
                  <artifactId>artemis-core-client</artifactId>
                  <version>2.28.0.redhat-00004</version>
              </dependency>
              <dependency>
                  <groupId>org.apache.activemq</groupId>
                  <artifactId>artemis-selector</artifactId>
                  <version>2.28.0.redhat-00004</version>
              </dependency>
              <dependency>
                  <groupId>org.apache.activemq</groupId>
                  <artifactId>artemis-commons</artifactId>
                  <version>2.28.0.redhat-00004</version>
              </dependency>
              <dependency>
			      <groupId>org.apache.activemq</groupId>
			      <artifactId>artemis-jms-client</artifactId>
			      <version>2.28.0.redhat-00004</version>
              </dependency>
      ````
    **Note:** This dependency is already defined in the pom.xml. 
- Build using ``mvnw`` command
    ````shell
    ./mvnw spring-boot:run
    ````
