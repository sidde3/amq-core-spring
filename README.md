# AMQP Protocol using SpringBoot
Sample SpringBoot based application using Red Hat AMQ Spring Boot Starter.

## Configuration
Configuration are based on the provided connection options in the amqp library as mentioned [here](https://access.redhat.com/documentation/en-us/red_hat_amq/2021.q3/html-single/using_the_amq_spring_boot_starter/index#configuration). 
````properties
amqphub.amqp10jms.remote-url=amqp://localhost:61616
amqphub.amqp10jms.username=admin
amqphub.amqp10jms.password=secret
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
    ````xmml
    <dependency>
      <groupId>org.amqphub.spring</groupId>
      <artifactId>amqp-10-jms-spring-boot-starter</artifactId>
      <version>2.5.0.redhat-00001</version>
    </dependency>
    ````
  **Note:** This dependency is already defined in the pom.xml. 
- Build using ``mvnw`` command
    ````shell
    ./mvnw spring-boot:run
    ````
