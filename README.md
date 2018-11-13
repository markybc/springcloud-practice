#eureka-server

##1、application.properties
    
    server.port=8761
    
    spring.application.name=eureka-server
    
    eureka.instance.hostname=localhost
    eureka.client.register-with-eureka=false
    eureka.client.fetch-registry=false
    eureka.client.service-url.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/

##2、pom.xml

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
    
##3、注解开启

    @EnableEurekaServer


#zipkin-server

##1、application.properties
    
    server.port=9411
    spring.application.name=zipkin-server
    
    #eureka注册地址
    eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
    
    #actuator配置支持 参数刷新
    management.endpoints.web.exposure.include=refresh,health,info
    management.metrics.web.server.auto-time-requests=false
        
##2、pom.xml

    <dependency>
        <groupId>io.zipkin.java</groupId>
        <artifactId>zipkin-server</artifactId>
        <version>2.11.1</version>
        <exclusions>
            <exclusion>
                <artifactId>spring-boot-starter-log4j2</artifactId>
                <groupId>org.springframework.boot</groupId>
            </exclusion>
        </exclusions>
    </dependency>

    <dependency>
        <groupId>io.zipkin.java</groupId>
        <artifactId>zipkin-autoconfigure-ui</artifactId>
        <version>2.11.1</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    
##3、注解开启

    @EnableZipkinServer
    @EnableEurekaClient
