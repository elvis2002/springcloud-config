# 组件说明：Spring Cloud config

    在分布式系统中，spring cloud config 提供一个服务端和客户端去提供可扩展的配置服务。我们可用用配置服务中心区集中的管理所有的服务的各种环境配置文件。Spring Cloud Config基于使用中心配置仓库的思想（版本控制），支持Git（默认）、SVN、File等三种储存方式。

## 创建Config Server

* 创建一个mvn工程，起名为config-server,其pom.xml见实例代码，核心依赖如下：

```
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
        
```

* 在程序的入口Application类加上@EnableConfigServer注解开启配置服务器

```
    
    @EnableConfigServer
    @SpringBootApplication
    public class Application {
    
        public static void main(String[] args) {
            new SpringApplicationBuilder(Application.class).web(true).run(args);
        }
    }
        
```

* 配置文件

```
    
    server:
      port: 8888
    
    spring:
      application:
        name: config-server
      cloud:
        config:
          server:
            git:
              uri: https://github.com/cloudframeworks-springcloud/Spring-Cloud-Config.git
              searchPaths: config
        
```
** uri:配置文件所存放的git地址
** searchPaths:寻找路径
** 获取git上的资源信息遵循如下规则：
    
```

    /{application}/{profile}[/{label}]
    /{application}-{profile}.yml
    /{label}/{application}-{profile}.yml
    /{application}-{profile}.properties
    /{label}/{application}-{profile}.properties  

```

## 创建Config client

* 创建一个mvn工程，起名为config-server,其pom.xml见实例代码，核心依赖如下：

```
    
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-client</artifactId>
    </dependency>
        
```

* 在程序的入口Application类加上@EnableConfigServer注解开启配置服务器

```
    
    @SpringBootApplication
    public class Application {
    
        public static void main(String[] args) {
            new SpringApplicationBuilder(Application.class).web(true).run(args);
        }
    }

        
```

* 创建一个restful接口访问配置文件中属性

```

    @EnableAutoConfiguration
    @RefreshScope
    @RestController
    public class DemoController {
    
        @Value("${from}")
        String from;
        
        
        @RequestMapping("/from")
        public String from() {
            return this.from;
        }
    }

```

* 配置文件

```
    
    server:
      port: 9000
    
    spring:
      application:
        name: config-client
      profiles:
        active: dev
      cloud:
        config:
          uri: http://${CONFIG_HOST}:${CONFIG_PORT}
          label: master
        
```

* 访问地址

   http://DOCKER_HOST:DOCKER_PORT/from

