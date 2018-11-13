#eureka-server 注册中心

访问地址：http://localhost:8761/

##1、application.properties
    
    server.port=8761
    
    spring.application.name=eureka-server
    
    eureka.instance.hostname=localhost
    eureka.client.register-with-eureka=false
    eureka.client.fetch-registry=false
    eureka.client.service-url.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/

##2、pom.xml

    <!--eureka-server-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
    
##3、注解开启

    @EnableEurekaServer
    
#config-server 配置中心

##1、application.properties
    
    server.port=8888
    spring.application.name=config-server
    
    #eureka注册地址
    eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
    
    #gitlab配置
    ##git地址
    spring.cloud.config.server.git.uri=http://192.168.0.175/java_niwodai_risk/engine-web.git
    ##path
    spring.cloud.config.server.git.search-paths=config-repo
    spring.cloud.config.server.git.username=yumengyao
    spring.cloud.config.server.git.password=yumengyao123
    ##分支
    spring.cloud.config.server.git.default-label=config
    
        
##2、pom.xml

    <!--eureka-client-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <!--config-server-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
    
##3、注解开启

    @EnableEurekaClient
    @EnableConfigServer

#zipkin-server 监控

访问地址：http://localhost:9411/

##1、application.properties
    
    server.port=9411
    spring.application.name=zipkin-server
    
    #eureka注册地址
    eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
    
    #actuator配置
    management.metrics.web.server.auto-time-requests=false
        
##2、pom.xml

    <!--zipkin-server-->
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
    <!--eureka-client-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    
##3、注解开启

    @EnableZipkinServer
    @EnableEurekaClient


#Hystrix Turbine Dashboard 监控

访问地址：http://localhost:2001/hystrix
分析地址：http://localhost:2001/turbine.stream


##1、application.properties
    
    server.port=2001
    spring.application.name=hystrix-dashboard
    
    #eureka注册地址
    eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
    
    #指定聚合哪些集群，多个使用","分割，默认为default
    turbine.aggregator.cluster-config=default
    #表明监控哪些服务
    turbine.app-config=order,stock
    turbine.cluster-name-expression=new String("default")
    #这里和service启动类里的 registrationBean.addUrlMappings("/hystrix.stream")一致
    turbine.instanceUrlSuffix=hystrix.stream
        
##2、pom.xml

    <!--hystrix-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>
    <!--turbine-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-turbine</artifactId>
    </dependency>
    <!--hystrix-dashboard-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
    </dependency>
    <!--eureka-client-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    
##3、注解开启

    @EnableEurekaClient
    //断路由
    @EnableHystrix
    //断路由仪表盘
    @EnableHystrixDashboard
    //Turbine
    @EnableTurbine
    
    
#order 订单服务

访问地址：http://localhost:8081


##1、bootstrap.properties
先于application.properties加载获取配置信息
    
    #default config-server
    spring.cloud.config.uri=http://localhost:8888
    spring.cloud.config.fail-fast=true
    
    #配置动态刷新
    management.endpoints.web.exposure.include=refresh,health,info

 
##2、application.properties
    
    server.port=8081
    spring.application.name=order
    
    #eureka
    eureka.client.serviceUrl.defaultZone= http://localhost:8761/eureka/
    
    #zipkin 地址
    spring.zipkin.base-url=http://localhost:9411
    #zipkin 采集百分比
    spring.sleuth.sampler.probability=1.0
    
    #hystrix 熔断开启
    feign.hystrix.enabled=true

 
##3、pom.xml

    <!--hystrix-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-hystrix</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>
    <!--feign-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    <!--config-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
    </dependency>
    <!--zipkin-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-zipkin</artifactId>
    </dependency>
    <!--eureka-client-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <!--web-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
##4、注解开启

    @EnableFeignClients
    @EnableCircuitBreaker
    @EnableEurekaClient
    
##5、hystrix.stream
    @Bean
	public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
	}
##6、@RefreshScope 参数刷新

在使用参数的类上添加注解 @RefreshScope
@Value("${app}")获取参数配置

##7、Feign调用和熔断配置
@FeignClient(value = "stock",fallback = StockServiceHystric.class)


#zuul getway 网关

访问地址：http://localhost:8080/api/order/index 订单服务
访问地址：http://localhost:8080/api/stock/index 库存服务

##1、application.properties
    
    server.port=8080
    spring.application.name=getway
    
    #eureka
    eureka.client.serviceUrl.defaultZone= http://localhost:8761/eureka/
    
    #zipkin 地址
    spring.zipkin.base-url=http://localhost:9411
    #zipkin 采集百分比
    spring.sleuth.sampler.probability=1.0
    
    #zuul前缀
    zuul.prefix=/api
    ##订单
    zuul.routes.order.path=/order/**
    zuul.routes.order.serviceId=order
    ##库存
    zuul.routes.stock.path=/stock/**
    zuul.routes.stock.serviceId=stock

##2、pom.xml

    <!--zuul-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
    </dependency>
    <!--zipkin-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-zipkin</artifactId>
    </dependency>
    <!--eureka-client-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    
##3、注解开启

    @EnableEurekaClient
    @EnableZuulProxy
    